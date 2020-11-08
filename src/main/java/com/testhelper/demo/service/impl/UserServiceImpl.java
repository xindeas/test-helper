package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.config.ReflectAnno;
import com.testhelper.demo.dto.UserDto;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.SortHelperPo;
import com.testhelper.demo.pojo.UserPo;
import com.testhelper.demo.repository.UserRepository;
import com.testhelper.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public User load(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String login) {
        return userRepository.findByLogin(login);
    }

    public PageHelperPo<User, UserPo> query(PageHelperPo<User, UserPo> page) {
        if (null == page) {
            return null;
        }
        QUser qClass = QUser.user;

        UserPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<User> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        try {
            query = sortCreator(qClass, UserPo.class, query, page.getSorts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.paginationQuery(query, page);
    }
    private BooleanBuilder whereCreator(UserPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QUser qClass = QUser.user;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (StringUtils.isNotBlank(po.getName())) {
                builder.and(qClass.name.eq(po.getName()));
            }
            if (StringUtils.isNotBlank(po.getLogin())) {
                builder.and(qClass.login.eq(po.getLogin()));
            }
            if (StringUtils.isNotBlank(po.getMobile())) {
                builder.and(qClass.mobile.eq(po.getMobile()));
            }
            if (StringUtils.isNotBlank(po.getEmail())) {
                builder.and(qClass.email.eq(po.getEmail()));
            }
            if (StringUtils.isNotBlank(po.getRole())) {
                builder.and(qClass.role.eq(po.getRole()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (StringUtils.isNotBlank(po.getModifyBy())) {
                builder.and(qClass.modifyBy.eq(po.getModifyBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (null != po.getModifyDate()) {
                builder.and(qClass.modifyDate.eq(po.getModifyDate()));
            }
        }
        return builder;
    }
}
