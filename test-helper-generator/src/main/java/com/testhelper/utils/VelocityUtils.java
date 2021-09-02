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
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class VelocityUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
    private static String tableName = "";
    private static String entityName = "";

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

    public static byte[] downloadCode(String tbName) {

        tableName = tbName;
        entityName = getEntityName(tableName);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public static void generatorCode(ZipOutputStream zip) {

        initVelocity();
        VelocityContext context = VelocityUtils.prepareContext();

        addToZip(zip, context, "vm/entity.java.vm", entityName + ".java");
        addToZip(zip, context, "vm/po.java.vm", getEntityName(tableName) + "Po.java");
        addToZip(zip, context, "vm/repository.java.vm", getEntityName(tableName) + "Repository.java");
        addToZip(zip, context, "vm/component.java.vm", getEntityName(tableName) + "Component.java");
        addToZip(zip, context, "vm/service.java.vm", getEntityName(tableName) + "Service.java");
        addToZip(zip, context, "vm/serviceImpl.java.vm", getEntityName(tableName) + "ServiceImpl.java");
        try {
            zip.closeEntry();
        } catch (IOException e) {
            System.out.println("渲染模板失败：" + e);
        }

    }

    /**
     * 添加到压缩包
     * @param zip 压缩包
     * @param context 参数
     * @param vmPath 模板路径
     * @param fileName 文件名
     */
    public static void addToZip(ZipOutputStream zip, VelocityContext context, String vmPath, String fileName) {
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(vmPath, "UTF-8");
        tpl.merge(context, sw);

        try {
            // 添加到zip
            zip.putNextEntry(new ZipEntry(fileName));
            IOUtils.write(sw.toString(), zip, "UTF-8");
            IOUtils.closeQuietly(sw);
            zip.flush();
        } catch (IOException e) {
            System.out.println("渲染模板失败：" + e);
        }
    }

    public static VelocityContext prepareContext() {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("author", "Xindeas");
        velocityContext.put("dateTime", format.format(new Date()));
        velocityContext.put("tableName", tableName);
        velocityContext.put("entityName", getEntityName(tableName));
        velocityContext.put("lowerCamelName", StrUtils.firstWordToLower(getEntityName(tableName)));

        TableServiceImpl tableService = (TableServiceImpl) SpringBeanUtils.getBean("TableService");
        List<SchemaColumn> list = tableService.getColumnByTable(tableName);
        // 主键
        for (SchemaColumn sc : list) {
            sc.setColumnEntityName(StrUtils.lineLinkToLowerCamel(sc.getColumnName()));
            sc.setUpperEntityName(StrUtils.lineLinkToUpperCamel(sc.getColumnName()));
            if ("pri".equalsIgnoreCase(sc.getColumnKey())) {
                velocityContext.put("primary", sc);
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
        velocityContext.put("list", list);
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
