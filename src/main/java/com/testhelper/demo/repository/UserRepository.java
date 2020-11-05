package com.testhelper.demo.repository;

import com.testhelper.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findUserById(Long id);
    User findByLogin(String login);
}
