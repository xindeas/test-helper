package com.testhelper.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.testhelper.demo.config.ReflectAnno;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.SortHelperPo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("BaseService")
public class BaseServiceImpl {
    @PersistenceContext
    EntityManager entityManager;
    protected PageHelperPo paginationQuery(JPAQuery<?> query, PageHelperPo page) {
        Session session = entityManager.unwrap(Session.class);
        long total = query.fetchCount();
        page.setTotalCount((int)total);
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
                }
                else {
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

    public void entityCreator(String tableName) {
        Query query = entityManager.createNativeQuery("DESCRIBE " + tableName);
        List<Object> list = query.getResultList();
        for (Object lo : list) {
            List l = JSON.parseArray(JSON.toJSONString(lo));
            System.out.println(l.get(0));
        }
    }
}
