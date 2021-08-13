package com.testhelper.repository;

import com.testhelper.entity.DefectCommentAt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:02
 */
@Repository
public interface DefectCommentAtRepository extends JpaRepository<DefectCommentAt, Long> {
    /**
     * 根据ID加载实体
     *
     * @param id
     * @return
     */
    DefectCommentAt findDefectCommentAtById(Long id);
}
