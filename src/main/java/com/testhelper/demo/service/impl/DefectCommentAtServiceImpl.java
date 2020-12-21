package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.DefectComment;
import com.testhelper.demo.entity.DefectCommentAt;
import com.testhelper.demo.entity.QDefectComment;
import com.testhelper.demo.entity.QDefectCommentAt;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.DefectCommentAtPo;
import com.testhelper.demo.pojo.DefectCommentPo;
import com.testhelper.demo.repository.DefectCommentAtRepository;
import com.testhelper.demo.repository.DefectCommentRepository;
import com.testhelper.demo.service.DefectCommentAtService;
import com.testhelper.demo.service.DefectCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 11:34
 */
@Service
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
        return defectCommentAtRepository.findDefectCommentAtById(id);
    }

    @Override
    public DefectCommentAt save(DefectCommentAt defectCommentAt) {
        return defectCommentAtRepository.save(defectCommentAt);
    }

    @Override
    public DefectCommentAt add(DefectCommentAt defectCommentAt) {
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
