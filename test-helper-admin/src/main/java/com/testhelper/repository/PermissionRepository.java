package com.testhelper.repository;

import com.testhelper.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2021 年 09 月 02 日 星期四 08 点 49 分 50 秒
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
        /**
         * 根据ID加载实体
         *
         * @param id
         * @return
         */
    Permission findPermissionById(Long id);
}
