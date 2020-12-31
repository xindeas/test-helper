package com.testhelper.demo.utils;

import lombok.SneakyThrows;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实体类工具
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class EntityUtils {
    /**
     * 两个对象比较获取修改的内容
     * @param old 旧对象
     * @param current 新对象
     * @return
     */
    @SneakyThrows
    public static String compareEntity(Object old, Object current) {
        if (old.getClass().getName() != current.getClass().getName()) {
            throw new Exception("比较失败，类型不一致");
        }
        StringBuilder result = new StringBuilder();
        for (Field f : old.getClass().getDeclaredFields()) {
            String columnName = f.getAnnotation(Column.class).columnDefinition();
            f.setAccessible(true);
            if (null == f.get(old) && null == f.get(current)) {
                continue;
            }
            // 忽略创建人创建时间修改人修改时间
            if ("createDate".equals(f.getName()) ||
                    "createBy".equals(f.getName()) ||
                    "modifyDate".equals(f.getName()) ||
                    "modifyBy".equals(f.getName())) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            if (Boolean.class == f.getType()) {
                Boolean oldVal = (Boolean) f.get(old);
                Boolean newVal = (Boolean) f.get(current);
                if (null == oldVal) {
                    sb.append("修改了").append(columnName).append("：从空修改为【").append(specialBoolean(f.getName(), newVal)).append("】；");
                } else if (null == newVal) {
                    sb.append("修改了").append(columnName).append("：从【").append(specialBoolean(f.getName(), oldVal)).append("】修改为空；");
                } else if (!oldVal.equals(newVal)) {
                    sb.append("修改了").append(columnName).append("：从【").append(specialBoolean(f.getName(), oldVal))
                            .append("】修改为【").append(specialBoolean(f.getName(), newVal)).append("】；");
                }
                result.append(sb);
            } else if (Long.class == f.getType()) {
                Long oldVal = (Long) f.get(old);
                Long newVal = (Long) f.get(current);
                if (null == oldVal) {
                    sb.append("修改了").append(columnName).append("：从空修改为【").append(newVal).append("】；");
                } else if (null == newVal) {
                    sb.append("修改了").append(columnName).append("：从【").append(oldVal).append("】修改为空；");
                } else if (!oldVal.equals(newVal)) {
                    sb.append("修改了").append(columnName).append("：从【").append(oldVal).append("】修改为【").append(newVal).append("】；");
                }
                result.append(sb);
            } else if (Date.class == f.getType()) {
                Date oldVal = (Date) f.get(old);
                Date newVal = (Date) f.get(current);
                SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
                if (null == oldVal) {
                    sb.append("修改了").append(columnName).append("：从空修改为【").append(newVal).append("】；");
                } else if (null == newVal) {
                    sb.append("修改了").append(columnName).append("：从【").append(format.format(oldVal)).append("】修改为空；");
                } else if (oldVal.getTime() != newVal.getTime()) {
                    sb.append("修改了").append(columnName).append("：从【").append(format.format(oldVal)).append("】修改为【")
                            .append(format.format(newVal)).append("】；");
                }
                result.append(sb);
            } else {
                String oldVal = (String) f.get(old);
                String newVal = (String) f.get(current);
                if (null == oldVal) {
                    sb.append("修改了").append(columnName).append("：从空修改为【").append(newVal).append("】；");
                } else if (null == newVal) {
                    sb.append("修改了").append(columnName).append("：从【").append(oldVal).append("】修改为空；");
                } else if (!oldVal.equals(newVal)) {
                    sb.append("修改了").append(columnName).append("：从【").append(oldVal).append("】修改为【").append(newVal).append("】；");
                }
                result.append(sb);
            }
        }
      return result.toString();
    }

    /**
     * 特殊的布尔值说明
     * @param name 字段名
     * @param value 值
     * @return
     */
    private static String specialBoolean(String name, Boolean value) {
        String result = "";
        switch (name) {
            case "enabled":
                result = value ? "启用" : "禁用";
                break;
            default:
                result = value ? "是" : "否";
                break;
        }
        return result;
    }
}
