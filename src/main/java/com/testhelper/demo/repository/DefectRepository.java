package com.testhelper.demo.repository;

import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.entity.Log;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:11
 */
public interface DefectRepository extends BaseRepository<Defect, Long> {
    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    Defect findDefectById(Long id);
}
