package com.testhelper.generator.service.impl;

import com.testhelper.generator.entity.SchemaColumn;
import com.testhelper.generator.entity.SchemaTable;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("BaseService")
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * 获取表的所有字段
     *
     * @param tableName
     * @return
     */
    public List<SchemaColumn> getColumnByTable(String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append("COLUMN_NAME as columnName, ");
        sb.append("DATA_TYPE as dataType, ");
        sb.append("CHARACTER_MAXIMUM_LENGTH as characterMaximumLength, ");
        sb.append("COLUMN_KEY as columnKey, ");
        sb.append("COLUMN_COMMENT as columnComment ");
        sb.append("FROM information_schema.COLUMNS ");
        sb.append("WHERE TABLE_SCHEMA = 'test-helper' AND TABLE_NAME = :tableName ");
        List<SchemaColumn> list = entityManager.createNativeQuery(sb.toString())
                .setParameter("tableName" , tableName)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(SchemaColumn.class))
                .list();
        entityManager.clear();
        return list;
    }

    /**
     * 获取所有表名
     *
     * @return
     */
    public List<SchemaTable> getAllTable() {
        List<SchemaTable> list = entityManager.createNativeQuery("SELECT TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'test-helper'")
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.aliasToBean(SchemaTable.class))
                .list();
        entityManager.clear();
        return list;
    }
}
