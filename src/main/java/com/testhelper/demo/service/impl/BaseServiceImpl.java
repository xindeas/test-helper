package com.testhelper.demo.service.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.testhelper.demo.config.ReflectAnno;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.SortHelperPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础分页查询
 * @param <T> 出参，通常是dto
 */
public class BaseServiceImpl<T> {
    protected PageHelperPo paginationQuery(JPAQuery<T> query, PageHelperPo page) {
        long total = query.fetchCount();
        page.setTotalCount((int)total);
        if (page.getPagination()) {
            List<T> resultList = query
                    .offset(page.getPageIndex() * page.getPageSize())
                    .limit(page.getPageSize())
                    .fetch();
            page.setResult(resultList);
        } else {
            List<T> resultList = query.fetch();
            page.setResult(resultList);
        }
        return page;
    }
    protected JPAQuery sortCreator(EntityPathBase mainTbClass, Class poClass, JPAQuery query, List<SortHelperPo> sortList) throws Exception {
        if (null != sortList && !CollectionUtils.isEmpty(sortList)) {
            QUser qClass = QUser.user;
            for (SortHelperPo po : sortList) {
                Field f = poClass.getDeclaredField(po.getColumn());
                ReflectAnno ra = f.getAnnotation(ReflectAnno.class);
                ComparableExpressionBase ceb;
                if (null == ra) {
                    Field col = mainTbClass.getClass().getField(po.getColumn());
                    ceb = (ComparableExpressionBase) col.get(mainTbClass);
                }
                else {
                    Class reClass = ra.reClass();
                    String column = StringUtils.isNotBlank(ra.column()) ? ra.column() : po.getColumn();
                    String instance = ra.instance();

                    Field col = reClass.getField(column);
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
}
