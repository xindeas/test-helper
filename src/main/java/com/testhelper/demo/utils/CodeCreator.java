package com.testhelper.demo.utils;

import com.testhelper.demo.entity.SchemaColumn;
import com.testhelper.demo.service.impl.BaseServiceImpl;
import lombok.SneakyThrows;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class CodeCreator {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
    /**
     * 作者签名
     */
    private static String authorSign = "/**\n" +
            " * @Author: Xindeas\n" +
            " * @Date: " +
            " */\n";
    private static String entityName = "";
    private static String poName = "";
    private static String serviceName = "";
    private static String serviceImplName = "";
    private static String repositoryName = "";
    private static String controllerName = "";
    private static String componentName = "";

    @SneakyThrows
    public static void entityCreator(String tableName) {
        BaseServiceImpl baseService = (BaseServiceImpl) SpringBeanUtils.getBean("BaseService");
        List<SchemaColumn> list = baseService.getColumnByTable(tableName);

        entityName = getEntityName(tableName);
        poName = getEntityName(tableName) + "Po";
        serviceName = getEntityName(tableName) + "Service";
        serviceImplName = getEntityName(tableName) + "ServiceImpl";
        repositoryName = getEntityName(tableName) + "Repository";
        controllerName = getEntityName(tableName) + "Controller";
        componentName = getEntityName(tableName) + "Component";
        // 主键
        SchemaColumn primary = new SchemaColumn();
        for (SchemaColumn sc : list) {
            if ("pri".equalsIgnoreCase(sc.getColumnKey())) {
                primary = sc;
            }

            if ("bigint".equalsIgnoreCase(sc.getDataType())) {
                sc.setJavaType("Long");
            } else if ("tinyint".equalsIgnoreCase(sc.getDataType())) {
                sc.setJavaType("Boolean");
            } else if ("datetime".equalsIgnoreCase(sc.getDataType())) {
                sc.setJavaType("Date");
            } else {
                sc.setJavaType("String");
            }
        }

        //得到输出流
        File dir = new File("D:\\codeCreator");
        if (dir.exists()) {
            deleteAll(dir);
        }
        dir = new File("D:\\codeCreator");
        dir.mkdirs();

        createEntity(tableName, list);
        createPo(list);
        createRepository(primary);
        createBaseService(primary);
        createController(tableName, primary);
        createComponent(primary);
        createServiceImpl(primary, list);
        createJsService(tableName, primary);
    }

    /**
     * 删除文件夹
     *
     * @param file
     */
    public static void deleteAll(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            for (File f : file.listFiles()) {
                // 递归删除每一个文件
                deleteAll(f);
            }
            // 删除文件夹
            file.delete();
        }
    }

    /**
     * 创建实体类
     *
     * @param tableName
     * @param list
     */
    @SneakyThrows
    public static void createEntity(String tableName, List<SchemaColumn> list) {
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.entity;\n");
        str.append("\n");
        str.append("\n");
        str.append("import lombok.Setter;\n");
        str.append("import lombok.Getter;\n");
        str.append("\n");
        str.append("import javax.persistence.*;\n");
        str.append("import java.io.Serializable;\n");
        str.append("import java.util.Date;\n");
        str.append("\n");
        str.append(authorSign);
        str.append("@Setter\n");
        str.append("@Getter\n");
        str.append("@Entity\n");
        str.append("@Table(name=\"").append(tableName).append("\")\n");
        str.append("public class ").append(entityName).append(" implements Serializable {\n");

        for (SchemaColumn column : list) {
            if ("PRI".equalsIgnoreCase(column.getColumnKey())) {
                str.append("    @Id\n");
                str.append("    /**\n");
                str.append("     * @GeneratedValue(generator = \"idGenerator\")\n");
                str.append("     * @GenericGenerator(name = \"idGenerator\", strategy = \"uuid\")\n");
                str.append("     */\n");
                str.append("    @GeneratedValue(strategy=GenerationType.IDENTITY)\n");
            }
            str.append("    @Column(name=\"").append(column.getColumnName()).append("\"");
            str.append(", columnDefinition=\"").append(column.getColumnComment()).append("\"");
            if (null != column.getCharacterMaximumLength()) {
                str.append(", length=").append(column.getCharacterMaximumLength());
            }
            str.append(")\n");
            str.append("    private ")
                    .append(column.getJavaType())
                    .append(" ")
                    .append(StrUtils.lineLinkToLowerCamel(column.getColumnName()))
                    .append(";\n");
        }
        str.append("}\n");
        createFile("D:\\codeCreator\\" + entityName + ".java", str.toString());
    }

    /**
     * 创建pojo
     *
     * @param list
     */
    @SneakyThrows
    public static void createPo(List<SchemaColumn> list) {
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.pojo;\n");
        str.append("\n");
        str.append("\n");
        str.append("import lombok.Setter;\n");
        str.append("import lombok.Getter;\n");
        str.append("\n");
        str.append("import java.io.Serializable;\n");
        str.append("import java.util.Date;\n");
        str.append("\n");
        str.append(authorSign);
        str.append("@Setter\n");
        str.append("@Getter\n");
        str.append("public class ").append(poName).append(" implements Serializable {\n");

        for (SchemaColumn column : list) {
            str.append("    private");
            if ("bigint".equalsIgnoreCase(column.getDataType())) {
                str.append(" Long ");
            } else if ("tinyint".equalsIgnoreCase(column.getDataType())) {
                str.append(" Boolean ");
            } else if ("datetime".equalsIgnoreCase(column.getDataType())) {
                str.append(" Date ");
            } else {
                str.append(" String ");
            }
            str.append(StrUtils.lineLinkToLowerCamel(column.getColumnName())).append(";\n");
        }
        str.append("}\n");
        createFile("D:\\codeCreator\\" + poName + ".java", str.toString());
    }

    /**
     * 创建repository
     *
     * @param primary
     */
    @SneakyThrows
    public static void createRepository(SchemaColumn primary) {
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.repository;\n");
        str.append("\n");
        str.append("\n");
        str.append("import com.testhelper.demo.entity.").append(entityName).append(";\n");
        str.append("\n");
        str.append(authorSign);
        str.append("public interface ").append(repositoryName).append(" extends BaseRepository<").append(entityName).append(", ").append(primary.getJavaType()).append("> {\n");
        str.append("\n");
        str.append("    /**\n");
        str.append("     * 根据ID加载实体\n");
        str.append("     * @param ").append(StrUtils.lineLinkToLowerCamel(primary.getColumnName())).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    ").append(entityName).append(" find").append(entityName).append("By")
                .append(StrUtils.lineLinkToUpperCamel(primary.getColumnName()))
                .append("(").append(primary.getJavaType())
                .append(" ").append(StrUtils.lineLinkToLowerCamel(primary.getColumnName()))
                .append(");\n");
        str.append("}\n");
        createFile("D:\\codeCreator\\" + repositoryName + ".java", str.toString());
    }

    /**
     * 创建service
     *
     * @param primary
     */
    @SneakyThrows
    public static void createBaseService(SchemaColumn primary) {
        String entiryParam = StrUtils.firstWordToLower(entityName);
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.service;\n");
        str.append("\n");
        str.append("\n");
        str.append("import com.testhelper.demo.po.PageHelperPo;");
        str.append("import com.testhelper.demo.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.demo.pojo.").append(poName).append(";\n");
        str.append("\n");
        str.append(authorSign);
        str.append("public interface ").append(serviceName).append(" {\n");
        str.append("\n");

        str.append("/**\n");
        str.append("     * 分页查询\n");
        str.append("     *\n");
        str.append("     * @param page 分页参数\n");
        str.append("     * @return\n");
        str.append("     */");
        str.append("public PageHelperPo<")
                .append(entityName)
                .append(", ")
                .append(poName)
                .append("> query(PageHelperPo<")
                .append(entityName)
                .append(", ")
                .append(poName)
                .append("> page);");
        str.append("\n");
        str.append("/**\n");
        str.append("     * 根据ID加载实体\n");
        str.append("     *\n");
        str.append("     * @param ").append(StrUtils.lineLinkToLowerCamel(primary.getColumnName())).append(" ").append(primary.getColumnComment()).append(" \n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ").append(entityName)
                .append(" load(")
                .append(primary.getJavaType()).append(" ")
                .append(StrUtils.lineLinkToLowerCamel(primary.getColumnName()))
                .append(");\n");
        str.append("\n");
        str.append("    /**\n");
        str.append("     * 编辑\n");
        str.append("     *\n");
        str.append("     * @param ").append(entiryParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ").append(entityName).append(" save(").append(entityName).append(" ").append(entiryParam).append(");\n");
        str.append("\n");
        str.append("    /**\n");
        str.append("     * 新增\n");
        str.append("     *\n");
        str.append("     * @param ").append(entiryParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ").append(entityName).append(" add(").append(entityName).append(" ").append(entiryParam).append(");\n");
        str.append("\n");
        str.append("    /**\n");
        str.append("     * 删除\n");
        str.append("     *\n");
        str.append("     * @param ").append(StrUtils.lineLinkToLowerCamel(primary.getColumnName())).append(" ").append(primary.getColumnComment()).append(" \n");
        str.append("     */\n");
        str.append("    public void delete(").append(primary.getJavaType()).append(" ").append(StrUtils.lineLinkToLowerCamel(primary.getColumnName())).append(");\n");

        str.append("}\n");
        createFile("D:\\codeCreator\\" + serviceName + ".java", str.toString());
    }

    /**
     * 创建controller
     *
     * @param primary
     */
    @SneakyThrows
    public static void createController(String tableName, SchemaColumn primary) {
        String componentParam = StrUtils.firstWordToLower(componentName);
        String entiryParam = StrUtils.firstWordToLower(entityName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        String baseUrl = getBaseUrl(tableName);
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.controller;\n");
        str.append("import com.testhelper.demo.po.PageHelperPo;\n");
        str.append("import com.testhelper.demo.po.ResultHelperPo;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.web.bind.annotation.*;\n");
        str.append("import com.testhelper.demo.component.").append(componentName).append(";\n");
        str.append("import com.testhelper.demo.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.demo.pojo.").append(poName).append(";\n");
        str.append(authorSign);
        str.append("@RestController\n");
        str.append("@RequestMapping(\"/").append(baseUrl).append("\")\n");
        str.append("@CrossOrigin(origins = \"*\", maxAge=3600)\n");
        str.append("public class ").append(controllerName).append(" {\n");
        str.append("    @Autowired\n");
        str.append("    private ").append(componentName).append(" ").append(componentParam).append(";\n");
        str.append("    /**\n");
        str.append("     * 分页查询\n");
        str.append("     * @param page\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    @PostMapping(\"/query\")\n");
        str.append("    private ResultHelperPo query (@RequestBody PageHelperPo<").append(entityName).append(", ").append(poName).append("> page) {\n");
        str.append("        return ").append(componentParam).append(".query(page);\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 加载\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    @GetMapping(\"/load/{").append(primaryName).append("}\")\n");
        str.append("    private ResultHelperPo load (@PathVariable(\"").append(primaryName).append("\") ").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        return ").append(componentParam).append(".load(").append(primaryName).append(");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 修改\n");
        str.append("     * @param ").append(entiryParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    @PostMapping(\"/save\")\n");
        str.append("    private ResultHelperPo save (@RequestBody ").append(entityName).append(" ").append(entiryParam).append(") {\n");
        str.append("        return ").append(componentParam).append(".save(").append(entiryParam).append(");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 新增\n");
        str.append("     * @param ").append(entiryParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    @PostMapping(\"/add\")\n");
        str.append("    private ResultHelperPo add (@RequestBody ").append(entityName).append(" ").append(entiryParam).append(") {\n");
        str.append("        return ").append(componentParam).append(".add(").append(entiryParam).append(");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 删除\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    @DeleteMapping(\"/delete/{").append(primaryName).append("}\")\n");
        str.append("    private ResultHelperPo delete (@PathVariable(\"").append(primaryName).append("\") ").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        return ").append(componentParam).append(".delete(").append(primaryName).append(");\n");
        str.append("    }\n");

        str.append("}\n");
        createFile("D:\\codeCreator\\" + controllerName + ".java", str.toString());
    }

    /**
     * 创建component
     *
     * @param primary
     */
    @SneakyThrows
    public static void createComponent(SchemaColumn primary) {
        String serviceParam = StrUtils.firstWordToLower(serviceName);
        String entityParam = StrUtils.firstWordToLower(entityName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.component;\n");
        str.append("import com.testhelper.demo.po.PageHelperPo;\n");
        str.append("import com.testhelper.demo.po.ResultHelperPo;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.stereotype.Component;\n");
        str.append("import com.testhelper.demo.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.demo.pojo.").append(poName).append(";\n");
        str.append("import com.testhelper.demo.service.").append(serviceName).append(";\n");
        str.append(authorSign);
        str.append("@Component\n");
        str.append("public class ").append(componentName).append(" {\n");
        str.append("    @Autowired\n");
        str.append("    private ").append(serviceName).append(" ").append(serviceParam).append(";\n");
        str.append("    /**\n");
        str.append("     * 分页查询\n");
        str.append("     * @param page\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo query (PageHelperPo<").append(entityName).append(", ").append(poName).append("> page) {\n");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".query(page), \"\");");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 加载\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo load (").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".load(id), \"\");");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 修改\n");
        str.append("     * @param ").append(entityParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo save (").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        if (null == ").append(entityParam).append(".getId()) {");
        str.append("            return new ResultHelperPo(false, ").append(entityParam).append(", \"修改异常\");");
        str.append("        }");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".save(").append(entityParam).append("), \"\");");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 新增\n");
        str.append("     * @param ").append(entityParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo add (").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        if (null == ").append(entityParam).append(".getId()) {");
        str.append("            return new ResultHelperPo(false, ").append(entityParam).append(", \"新增异常\");");
        str.append("        }");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".add(").append(entityParam).append("), \"\");");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 删除\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo delete (").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        ").append(serviceParam).append(".delete(").append(primaryName).append(");");
        str.append("        return new ResultHelperPo(true, ").append(primaryName).append(", \"\");");
        str.append("    }\n");

        str.append("}\n");
        createFile("D:\\codeCreator\\" + componentName + ".java", str.toString());
    }

    /**
     * 创建serviceImpl
     * @param primary
     * @param list
     */
    @SneakyThrows
    public static void createServiceImpl(SchemaColumn primary, List<SchemaColumn> list) {
        String entityParam = StrUtils.firstWordToLower(entityName);
        String repositoryParam = StrUtils.firstWordToLower(repositoryName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.demo.service.impl;\n");
        str.append("import com.querydsl.core.BooleanBuilder;\n");
        str.append("import com.querydsl.jpa.impl.JPAQuery;\n");
        str.append("import com.querydsl.jpa.impl.JPAQueryFactory;\n");
        str.append("import com.testhelper.demo.po.PageHelperPo;\n");
        str.append("import org.apache.commons.lang3.StringUtils;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.stereotype.Service;\n");
        str.append("import org.springframework.transaction.annotation.Transactional;\n");
        str.append("import com.testhelper.demo.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.demo.entity.").append("Q").append(entityName).append(";\n");
        str.append("import com.testhelper.demo.pojo.").append(poName).append(";\n");
        str.append("import com.testhelper.demo.repository.").append(repositoryName).append(";\n");
        str.append("import com.testhelper.demo.service.").append(serviceName).append(";\n");
        str.append(authorSign);
        str.append("@Service(\"").append(serviceName).append("\")\n");
        str.append("@Transactional(rollbackFor = Exception.class)\n");
        str.append("public class ").append(serviceImplName).append(" extends BaseServiceImpl implements ").append(serviceName).append(" {\n");
        str.append("    @Autowired\n");
        str.append("    private ").append(repositoryName).append(" ").append(repositoryParam).append(";\n");
        str.append("    @Autowired\n");
        str.append("    JPAQueryFactory queryFactory;\n");

        str.append("    @Override\n");
        str.append("    public PageHelperPo<")
                .append(entityName)
                .append(", ")
                .append(poName).append("> query(PageHelperPo<")
                .append(entityName)
                .append(", ")
                .append(poName)
                .append("> page) {");
        str.append("        if (null == page) {\n");
        str.append("            return null;\n");
        str.append("        }\n");
        str.append("        Q").append(entityName).append(" qClass = ").append("Q").append(entityName).append(".").append(entityParam).append(";\n");
        str.append("        ").append(poName).append(" po = page.getFilter();\n");
        str.append("        BooleanBuilder builder = whereCreator(po);\n");
        str.append("        JPAQuery<").append(entityName).append("> query = queryFactory\n");
        str.append("                .selectFrom(qClass)\n");
        str.append("                .where(builder);\n");
        str.append("        query = sortCreator(qClass, ").append(poName).append(".class, query, page.getSorts());\n");
        str.append("        return this.paginationQuery(query, page);\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public ").append(entityName).append(" load(").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        return ").append(repositoryParam).append(".find").append(entityName).append("By")
                .append(StrUtils.lineLinkToUpperCamel(primary.getColumnName())).append("(").append(primaryName).append(");\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public ").append(entityName).append(" save(").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        return ").append(repositoryParam).append(".save(").append(entityParam).append(");\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public ").append(entityName).append(" add(").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        return ").append(repositoryParam).append(".save(").append(entityParam).append(");\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public void delete(").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        ").append(repositoryParam).append(".delete").append("By")
                .append(StrUtils.lineLinkToUpperCamel(primary.getColumnName())).append("(").append(primaryName).append(");\n");
        str.append("    }\n");

        str.append("    private BooleanBuilder whereCreator(").append(poName).append(" po) {\n");
        str.append("        BooleanBuilder builder = new BooleanBuilder();\n");
        str.append("        if (null != po) {\n");
        str.append("            Q").append(entityName).append(" qClass = ").append("Q").append(entityName).append(".").append(entityParam).append(";\n");
        for (SchemaColumn sc : list) {
            if ("String".equalsIgnoreCase(sc.getJavaType())) {
                str.append("            if (StringUtils.isNotBlank(po.get").append(StrUtils.lineLinkToUpperCamel(sc.getColumnName())).append("())) {\n");
            } else {
                str.append("            if (null != po.get").append(StrUtils.lineLinkToUpperCamel(sc.getColumnName())).append("()) {\n");
            }

            str.append("                builder.and(qClass.").append(StrUtils.lineLinkToLowerCamel(sc.getColumnName())).append(".eq(po.get").append(StrUtils.lineLinkToUpperCamel(sc.getColumnName())).append("()));\n");
            str.append("            }\n");
        }


        str.append("    }\n");
        str.append("        return builder;\n");
        str.append("  }\n");

        str.append("}\n");
        createFile("D:\\codeCreator\\" + serviceImplName + ".java", str.toString());
    }

    /**
     * 创建js请求文件
     * @param tableName
     * @param primary
     */
    @SneakyThrows
    public static void createJsService(String tableName, SchemaColumn primary) {
        String entityParam = StrUtils.firstWordToLower(entityName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        String baseUrl = getBaseUrl(tableName);
        StringBuilder str = new StringBuilder();
        str.append("import instance from '@/utils/serviceUtils'\n");
        str.append("\n");
        str.append("export function query").append(entityName).append(" (param) {\n");
        str.append("    return instance.post('/").append(baseUrl).append("/query', param);\n");
        str.append("}\n");
        str.append("\n");
        str.append("export function add").append(entityName).append(" (").append(entityParam).append(") {\n");
        str.append("    return instance.post('/").append(baseUrl).append("/add', ").append(entityParam).append(");\n");
        str.append("}\n");
        str.append("export function save").append(entityName).append(" (").append(entityParam).append(") {\n");
        str.append("    return instance.post('/").append(baseUrl).append("/save', ").append(entityParam).append(");\n");
        str.append("}\n");
        str.append("export function load").append(entityName).append(" (").append(primaryName).append(") {\n");
        str.append("    return instance.post('/").append(baseUrl).append("/load/' + ").append(primaryName).append(");\n");
        str.append("}\n");
        createFile("D:\\codeCreator\\" + serviceName + ".js", str.toString());
    }

    /**
     * 创建文件
     *
     * @param url
     * @param content
     */
    @SneakyThrows
    public static void createFile(String url, String content) {
        File file = new File(url);
        file.createNewFile();
        FileWriter out = new FileWriter(file, true);
        out.write(content);
        if (out != null) {
            out.close();
        }
    }

    /**
     * 获取实体类名
     *
     * @param tableName 表名
     * @return
     */
    public static String getEntityName(String tableName) {
        String result = tableName;
        if (tableName.startsWith("tb_")) {
            result = result.substring(3);
        }
        return StrUtils.lineLinkToUpperCamel(result);
    }

    /**
     * 获取基本路径
     * @param ori
     * @return
     */
    public static String getBaseUrl(String ori) {
        String result = ori;
        if (ori.startsWith("tb_")) {
            result = result.substring(3);
        }
        return result.replaceAll("_", "-");
    }
}
