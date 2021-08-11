package com.testhelper.common.service.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.testhelper.common.config.ReflectAnno;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.po.SortHelperPo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
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
    public EntityManager entityManager;

    protected PageHelperPo paginationQuery(JPAQuery<?> query, PageHelperPo page) {
//        Session session = entityManager.unwrap(Session.class);
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
//            resultList.forEach(item -> {
//                session.evict(item);
//            });
            page.setResult(resultList);
        }
        entityManager.clear();
        return page;
    }

    @SneakyThrows
    protected JPAQuery sortCreator(EntityPathBase mainTbClass, Class poClass, JPAQuery query, List<SortHelperPo> sortList) {
        if (null != sortList && !CollectionUtils.isEmpty(sortList)) {
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

}
