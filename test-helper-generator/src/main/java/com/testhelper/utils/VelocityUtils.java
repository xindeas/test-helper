package com.testhelper.utils;

import com.testhelper.entity.SchemaColumn;
import com.testhelper.service.impl.TableServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class VelocityUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
    /**
     * 作者签名
     */
    private static String authorSign = "/**\n" +
            " * @Author: Xindeas\n" +
            " * @Date: " +
            " */\n";
    private static String tableName = "";
    private static String entityName = "";
    private static String poName = "";
    private static String serviceName = "";
    private static String serviceImplName = "";
    private static String repositoryName = "";
    private static String controllerName = "";
    private static String componentName = "";
    private static SchemaColumn primary = null;

    @SneakyThrows
    public static void entityCreator(String tbName) {
        TableServiceImpl baseService = (TableServiceImpl) SpringBeanUtils.getBean("BaseService");
        List<SchemaColumn> list = baseService.getColumnByTable(tbName);

        tableName = tbName;
        entityName = getEntityName(tableName);
        poName = getEntityName(tableName) + "Po";
        serviceName = getEntityName(tableName) + "Service";
        serviceImplName = getEntityName(tableName) + "ServiceImpl";
        repositoryName = getEntityName(tableName) + "Repository";
        controllerName = getEntityName(tableName) + "Controller";
        componentName = getEntityName(tableName) + "Component";
        // 主键
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

        createEntity(list);
        createPo(list);
        createRepository();
        createBaseService();
        createController();
        createComponent();
        createServiceImpl(list);
        createJsService();
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
     * @param list
     */
    @SneakyThrows
    public static void createEntity(List<SchemaColumn> list) {
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.entity;\n");
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
        str.append("package com.testhelper.admin.pojo;\n");
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
     */
    @SneakyThrows
    public static void createRepository() {
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.repository;\n");
        str.append("\n");
        str.append("\n");
        str.append("import com.testhelper.admin.entity.").append(entityName).append(";\n");
        str.append("import org.springframework.stereotype.Repository;\n");
        str.append("\n");
        str.append(authorSign);
        str.append("@Repository\n");
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
     */
    @SneakyThrows
    public static void createBaseService() {
        String entiryParam = StrUtils.firstWordToLower(entityName);
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.service;\n");
        str.append("\n");
        str.append("\n");
        str.append("import com.testhelper.common.po.PageHelperPo;");
        str.append("import com.testhelper.admin.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.admin.pojo.").append(poName).append(";\n");
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
        str.append("    public ").append(entityName).append(" save(").append(entityName).append(" ").append(entiryParam).append(", String userLogin);\n");
        str.append("\n");
        str.append("    /**\n");
        str.append("     * 新增\n");
        str.append("     *\n");
        str.append("     * @param ").append(entiryParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ").append(entityName).append(" add(").append(entityName).append(" ").append(entiryParam).append(", String userLogin);\n");
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
     */
    @SneakyThrows
    public static void createController() {
        String componentParam = StrUtils.firstWordToLower(componentName);
        String entiryParam = StrUtils.firstWordToLower(entityName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        String baseUrl = getBaseUrl(tableName);
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.controller;\n");
        str.append("import com.testhelper.common.po.PageHelperPo;\n");
        str.append("import com.testhelper.common.po.ResultHelperPo;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.web.bind.annotation.*;\n");
        str.append("import com.testhelper.admin.component.").append(componentName).append(";\n");
        str.append("import com.testhelper.admin.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.admin.pojo.").append(poName).append(";\n");
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
     */
    @SneakyThrows
    public static void createComponent() {
        String serviceParam = StrUtils.firstWordToLower(serviceName);
        String entityParam = StrUtils.firstWordToLower(entityName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.component;\n");
        str.append("import com.testhelper.common.po.PageHelperPo;\n");
        str.append("import com.testhelper.common.po.ResultHelperPo;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.stereotype.Component;\n");
        str.append("import com.testhelper.admin.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.admin.pojo.").append(poName).append(";\n");
        str.append("import com.testhelper.admin.service.").append(serviceName).append(";\n");
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
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".query(page), \"\");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 加载\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo load (").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".load(id), \"\");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 修改\n");
        str.append("     * @param ").append(entityParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo save (").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        if (null == ").append(entityParam).append(".getId()) {\n");
        str.append("            return new ResultHelperPo(false, ").append(entityParam).append(", \"修改异常\");\n");
        str.append("        }\n");
        str.append("        User user = (User) SecurityUtils.getSubject().getPrincipal();");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".save(").append(entityParam).append(", user.getLogin()), \"\");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 新增\n");
        str.append("     * @param ").append(entityParam).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo add (").append(entityName).append(" ").append(entityParam).append(") {\n");
        str.append("        if (null != ").append(entityParam).append(".getId()) {\n");
        str.append("            return new ResultHelperPo(false, ").append(entityParam).append(", \"新增异常\");\n");
        str.append("        }\n");
        str.append("        User user = (User) SecurityUtils.getSubject().getPrincipal();");
        str.append("        return new ResultHelperPo(true, ").append(serviceParam).append(".add(").append(entityParam).append(", user.getLogin()), \"\");\n");
        str.append("    }\n");

        str.append("    /**\n");
        str.append("     * 删除\n");
        str.append("     * @param ").append(primaryName).append("\n");
        str.append("     * @return\n");
        str.append("     */\n");
        str.append("    public ResultHelperPo delete (").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        ").append(serviceParam).append(".delete(").append(primaryName).append(");\n");
        str.append("        return new ResultHelperPo(true, ").append(primaryName).append(", \"\");\n");
        str.append("    }\n");

        str.append("}\n");
        createFile("D:\\codeCreator\\" + componentName + ".java", str.toString());
    }

    /**
     * 创建serviceImpl
     *
     * @param list
     */
    @SneakyThrows
    public static void createServiceImpl(List<SchemaColumn> list) {
        String entityParam = StrUtils.firstWordToLower(entityName);
        String repositoryParam = StrUtils.firstWordToLower(repositoryName);
        String primaryName = StrUtils.lineLinkToLowerCamel(primary.getColumnName());
        String primaryNameUpper = StrUtils.firstWordToUpper(primaryName);
        StringBuilder str = new StringBuilder();
        str.append("package com.testhelper.admin.service.impl;\n");
        str.append("import com.querydsl.core.BooleanBuilder;\n");
        str.append("import com.querydsl.jpa.impl.JPAQuery;\n");
        str.append("import com.querydsl.jpa.impl.JPAQueryFactory;\n");
        str.append("import com.testhelper.common.po.PageHelperPo;\n");
        str.append("import org.apache.commons.lang3.StringUtils;\n");
        str.append("import com.testhelper.common.utils.LogUtils;\n");
        str.append("import com.testhelper.common.utils.EntityUtils;\n");
        str.append("import java.util.Date;\n");
        str.append("import org.hibernate.Session;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.stereotype.Service;\n");
        str.append("import org.springframework.transaction.annotation.Transactional;\n");
        str.append("import com.testhelper.admin.entity.").append(entityName).append(";\n");
        str.append("import com.testhelper.admin.entity.").append("Q").append(entityName).append(";\n");
        str.append("import com.testhelper.admin.pojo.").append(poName).append(";\n");
        str.append("import com.testhelper.admin.repository.").append(repositoryName).append(";\n");
        str.append("import com.testhelper.admin.service.").append(serviceName).append(";\n");
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
                .append("> page) {\n");
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
        str.append("        ").append(entityName).append(" ").append(entityParam).append(" = ")
                .append(repositoryParam).append(".find").append(entityName).append("By")
                .append(primaryNameUpper).append("(").append(primaryName).append(");\n");
        str.append("        Session session = entityManager.unwrap(Session.class);\n");
        str.append("        session.evict(").append(entityParam).append(");\n");
        str.append("        return ").append(entityParam).append(";\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public ").append(entityName).append(" save(").append(entityName).append(" ").append(entityParam).append(", String userLogin) {\n");
        str.append("        ").append(entityName).append(" old = ").append(repositoryParam).append(".find").append(entityName).append("By")
                .append(primaryNameUpper).append("(").append(entityParam).append(".get").append(primaryNameUpper).append("());\n");
        str.append("\n");
        str.append("        String msg = EntityUtils.compareEntity(old, ").append(entityParam).append(");\n");
        str.append("        if (StringUtils.isNotBlank(msg)) {\n");
        str.append("            LogUtils.log(\"").append(tableName).append("\", ").append(entityParam).append(".get").append(primaryNameUpper).append("(), msg, userLogin);\n");
        str.append("        }\n");
        str.append("        ").append(entityParam).append(".setModifyBy(userLogin);\n");
        str.append("        ").append(entityParam).append(".setModifyDate(new Date());\n");
        str.append("        return ").append(repositoryParam).append(".save(").append(entityParam).append(");\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public ").append(entityName).append(" add(").append(entityName).append(" ").append(entityParam).append(", String userLogin) {\n");
        str.append("        ").append(entityParam).append(".setCreateBy(userLogin);\n");
        str.append("        ").append(entityParam).append(".setCreateDate(new Date());\n");
        str.append("        ").append(entityParam).append(".setModifyBy(userLogin);\n");
        str.append("        ").append(entityParam).append(".setModifyDate(new Date());\n");
        str.append("        ").append(entityName).append(" p = ").append(repositoryParam).append(".save(").append(entityParam).append(");\n");
        str.append("        LogUtils.log(\"").append(tableName).append("\", p.get").append(primaryNameUpper).append("(), \"创建一条新纪录\", userLogin);\n");
        str.append("        return p;\n");
        str.append("    }\n");

        str.append("    @Override\n");
        str.append("    public void delete(").append(primary.getJavaType()).append(" ").append(primaryName).append(") {\n");
        str.append("        ").append(repositoryParam).append(".delete").append("By")
                .append(primaryNameUpper).append("(").append(primaryName).append(");\n");
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
     */
    @SneakyThrows
    public static void createJsService() {
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
        str.append("    return instance.get('/").append(baseUrl).append("/load/' + ").append(primaryName).append(");\n");
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
     *
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


    public static String previewCode() {
        initVelocity();
        VelocityContext context = VelocityUtils.prepareContext(18, "mrdjun");
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate("vm/test.xml.vm", "UTF-8");
        tpl.merge(context, sw);
        return sw.toString();
    }

    public static byte[] downloadCode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public static void generatorCode(ZipOutputStream zip) {
        initVelocity();
        VelocityContext context = VelocityUtils.prepareContext(18, "mrdjun");

        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate("vm/test.xml.vm", "UTF-8");
        tpl.merge(context, sw);
        try {
            // 添加到zip
            zip.putNextEntry(new ZipEntry("test.xml"));
            IOUtils.write(sw.toString(), zip, "UTF-8");
            IOUtils.closeQuietly(sw);
            zip.flush();
            zip.closeEntry();
        } catch (IOException e) {
            System.out.println("渲染模板失败：{0}" + e);
        }

    }

    public static VelocityContext prepareContext(int age, String name) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("age", age);
        velocityContext.put("name", name);
        return velocityContext;
    }

    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
