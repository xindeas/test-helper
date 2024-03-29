package com.testhelper.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.entity.Log;
import com.testhelper.entity.QLog;
import com.testhelper.po.PageHelperPo;
import com.testhelper.pojo.LogPo;
import com.testhelper.repository.LogRepository;
import com.testhelper.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("LogService")
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl extends BaseServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<Log, LogPo> query(PageHelperPo<Log, LogPo> page) {
        if (null == page) {
            return null;
        }
        QLog qClass = QLog.log;

        LogPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<Log> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, LogPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public Log load(Long id) {
        Log log = logRepository.findLogById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(log);
        return log;
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log add(Log log) {
        return logRepository.save(log);
    }

    @Override
    public void delete(Long id) {
        logRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(LogPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QLog qClass = QLog.log;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getTargetId()) {
                builder.and(qClass.targetId.eq(po.getTargetId()));
            }
            if (StringUtils.isNotBlank(po.getTargetTb())) {
                builder.and(qClass.targetTb.eq(po.getTargetTb()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
        }
        return builder;
    }
}
