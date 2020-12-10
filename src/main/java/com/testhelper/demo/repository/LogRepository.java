package com.testhelper.demo.repository;

import com.testhelper.demo.entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends BaseRepository<Log, Long> {
    Log findLogById(Long id);

}
