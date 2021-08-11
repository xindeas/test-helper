package com.testhelper.admin.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.admin.entity.QUser;
import com.testhelper.admin.entity.User;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.UserPo;
import com.testhelper.admin.repository.UserRepository;
import com.testhelper.admin.service.UserService;
import com.testhelper.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("UserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public User load(Long id) {
        User user = userRepository.findUserById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(user);
        return user;
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
        User user = userRepository.findByLogin(login);
        Session session = entityManager.unwrap(Session.class);
        session.evict(user);
        return user;
    }

    @Override
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
        query = sortCreator(qClass, UserPo.class, query, page.getSorts());
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
            if (null != po.getIdNotIn()) {
                builder.and(qClass.id.notIn(po.getIdNotIn()));
            }
        }
        return builder;
    }
}
