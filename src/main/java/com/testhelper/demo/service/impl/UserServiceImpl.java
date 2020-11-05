package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.dto.UserDto;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.UserPo;
import com.testhelper.demo.repository.UserRepository;
import com.testhelper.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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

//    public PageHelperPo<UserDto, UserPo> query(PageHelperPo<UserDto, UserPo> page) {
//        if (null == page) {
//            return null;
//        }
//        QUser qClass = QUser.user;
//
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qClass.login.eq("admin"));
//
////        JPAQuery<User> query = queryFactory.selectFrom(qClass)
////                .where(builder);
//        JPAQuery<UserDto> query = queryFactory.select(Projections.constructor(UserDto.class, qClass.id, qClass.name))
//                .from(qClass)
//                .where(qClass.name.eq("admin").and(qClass.login.eq("admin")));
////                .orderBy(qClass.id.asc(), qClass.createDate.desc())
//
//        long total = query.fetchCount();
//        page.setTotalCount((int)total);
//        if (page.getPagination()) {
//            List<UserDto> resultList = query.offset(page.getPageIndex()).limit(page.getPageSize()).fetch();
//            page.setResult(resultList);
//        } else {
//            List<UserDto> resultList = query.fetch();
//            page.setResult(resultList);
//        }
//        return page;
//    }
    public PageHelperPo<UserDto, UserPo> query(PageHelperPo<UserDto, UserPo> page) {

        QUser qm = QUser.user;
        //使用booleanTemplate充当where子句或where子句的一部分
        List<User> list = queryFactory.selectFrom(qm).where(Expressions.booleanTemplate("{} = \"tofu\"", qm.name)).fetch();
        //上面的写法，当booleanTemplate中需要用到多个占位时
        List<User> list1 = queryFactory.selectFrom(qm).where(Expressions.booleanTemplate("{0} = \"tofu\" and {1} = \"Amoy\"", qm.name,qm.pwd)).fetch();

        //使用stringTemplate充当查询语句的某一部分
        String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.createDate)).from(qm).fetchFirst();
        //在where子句中使用stringTemplate
        Long id = queryFactory.select(qm.id).from(qm).where(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.createDate).eq("2018-03-19")).fetchFirst();

        return null;
    }
}
