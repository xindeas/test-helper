package com.testhelper.admin.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.admin.entity.DefectCommentAt;
import com.testhelper.admin.entity.QDefectCommentAt;
import com.testhelper.admin.pojo.DefectCommentAtPo;
import com.testhelper.admin.repository.DefectCommentAtRepository;
import com.testhelper.admin.service.DefectCommentAtService;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 11:34
 */
@Service("DefectCommentAtService")
@Transactional(rollbackFor = Exception.class)
public class DefectCommentAtServiceImpl extends BaseServiceImpl implements DefectCommentAtService {
    @Autowired
    private DefectCommentAtRepository defectCommentAtRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<DefectCommentAt, DefectCommentAtPo> query(PageHelperPo<DefectCommentAt, DefectCommentAtPo> page) {
        if (null == page) {
            return null;
        }
        QDefectCommentAt qClass = QDefectCommentAt.defectCommentAt;

        DefectCommentAtPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<DefectCommentAt> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, DefectCommentAtPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public DefectCommentAt load(Long id) {
        DefectCommentAt defectComment = defectCommentAtRepository.findDefectCommentAtById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(defectComment);
        return defectComment;
    }

    @Override
    public DefectCommentAt save(DefectCommentAt defectCommentAt, String userLogin) {
        defectCommentAt.setCreateBy(userLogin);
        defectCommentAt.setCreateDate(new Date());
        return defectCommentAtRepository.save(defectCommentAt);
    }

    @Override
    public DefectCommentAt add(DefectCommentAt defectCommentAt, String userLogin) {
        defectCommentAt.setCreateBy(userLogin);
        defectCommentAt.setCreateDate(new Date());
        return defectCommentAtRepository.save(defectCommentAt);
    }

    @Override
    public void delete(Long id) {
        defectCommentAtRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(DefectCommentAtPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QDefectCommentAt qClass = QDefectCommentAt.defectCommentAt;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getCommentId()) {
                builder.and(qClass.commentId.eq(po.getCommentId()));
            }
            if (null != po.getUserId()) {
                builder.and(qClass.userId.eq(po.getUserId()));
            }
            if (null != po.getReferUserId()) {
                builder.and(qClass.referUserId.eq(po.getReferUserId()));
            }
            if (null != po.getReadFlag()) {
                builder.and(qClass.readFlag.eq(po.getReadFlag()));
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
