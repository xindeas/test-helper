package com.testhelper.admin.repository;

import com.testhelper.admin.entity.DefectComment;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:02
 */
@Repository
public interface DefectCommentRepository extends org.springframework.data.jpa.repository.JpaRepository<DefectComment, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    DefectComment findDefectCommentById(Long id);
}
