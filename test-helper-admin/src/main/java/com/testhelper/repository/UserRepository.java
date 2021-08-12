package com.testhelper.repository;

import com.testhelper.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
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
