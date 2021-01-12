package com.testhelper.demo.repository;

import com.testhelper.demo.entity.DefectCommentAt;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:02
 */
@Repository
public interface DefectCommentAtRepository extends BaseRepository<DefectCommentAt, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    DefectCommentAt findDefectCommentAtById(Long id);
}
