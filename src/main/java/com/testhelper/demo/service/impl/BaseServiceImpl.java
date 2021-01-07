package com.testhelper.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.testhelper.demo.config.ReflectAnno;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.entity.SchemaColumn;
import com.testhelper.demo.entity.SchemaTable;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.SortHelperPo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
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

    protected PageHelperPo paginationQuery(JPAQuery<?> query, PageHelperPo page) {
        Session session = entityManager.unwrap(Session.class);
        long total = query.fetchCount();
        page.setTotalCount((int) total);
        if (page.getPagination()) {
            List<?> resultList = query
                    .offset(page.getPageIndex() * page.getPageSize())
                    .limit(page.getPageSize())
                    .fetch();
            page.setResult(resultList);
        } else {
            List<?> resultList = query.fetch();
            resultList.forEach(item -> {
                session.evict(item);
            });
            page.setResult(resultList);
        }
        entityManager.clear();
        return page;
    }

    @SneakyThrows
    protected JPAQuery sortCreator(EntityPathBase mainTbClass, Class poClass, JPAQuery query, List<SortHelperPo> sortList) {
        if (null != sortList && !CollectionUtils.isEmpty(sortList)) {
            QUser qClass = QUser.user;
            for (SortHelperPo po : sortList) {
                Field f = poClass.getDeclaredField(po.getColumn());
                ReflectAnno ra = f.getAnnotation(ReflectAnno.class);
                ComparableExpressionBase ceb;
                if (null == ra) {
                    Field col = mainTbClass.getClass().getDeclaredField(po.getColumn());
                    ceb = (ComparableExpressionBase) col.get(mainTbClass);
                } else {
                    Class reClass = ra.reClass();
                    String column = StringUtils.isNotBlank(ra.column()) ? ra.column() : po.getColumn();
                    String instance = ra.instance();

                    Field col = reClass.getDeclaredField(column);
                    ceb = (ComparableExpressionBase) col.get(reClass.getDeclaredConstructor().newInstance(instance));
                }
                if (Order.DESC.equals(po.getOrder())) {
                    query.orderBy(ceb.desc());
                }
                // 默认升序
                else {
                    query.orderBy(ceb.asc());
                }
            }
        }

        return query;
    }

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
                .setParameter("tableName", tableName)
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
