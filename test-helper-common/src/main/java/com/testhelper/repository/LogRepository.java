package com.testhelper.repository;

import com.testhelper.entity.Log;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Repository
public interface LogRepository extends BaseRepository<Log, Long> {
    /**
     * 根据id加载实体
     *
     * @param id 流水号
     * @return
     */
    Log findLogById(Long id);

}
