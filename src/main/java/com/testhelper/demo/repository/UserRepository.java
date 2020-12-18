package com.testhelper.demo.repository;

import com.testhelper.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 根据账号加载实体
     * @param login
     * @return
     */
    User findByLogin(String login);
}
