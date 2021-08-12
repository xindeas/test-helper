package com.testhelper.repository;

import com.testhelper.entity.Defect;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:11
 */
@Repository
public interface DefectRepository extends org.springframework.data.jpa.repository.JpaRepository<Defect, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    Defect findDefectById(Long id);
}
