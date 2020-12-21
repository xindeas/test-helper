package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.DefectComment;
import com.testhelper.demo.entity.QDefectComment;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.DefectCommentPo;
import com.testhelper.demo.repository.DefectCommentRepository;
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
public class DefectCommentServiceImpl extends BaseServiceImpl implements DefectCommentService {
    @Autowired
    private DefectCommentRepository defectCommentRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<DefectComment, DefectCommentPo> query(PageHelperPo<DefectComment, DefectCommentPo> page) {
        if (null == page) {
            return null;
        }
        QDefectComment qClass = QDefectComment.defectComment;

        DefectCommentPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<DefectComment> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, DefectCommentPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public DefectComment load(Long id) {
        return defectCommentRepository.findDefectCommentById(id);
    }

    @Override
    public DefectComment save(DefectComment defectComment) {
        return defectCommentRepository.save(defectComment);
    }

    @Override
    public DefectComment add(DefectComment defectComment) {
        return defectCommentRepository.save(defectComment);
    }

    @Override
    public void delete(Long id) {
        defectCommentRepository.deleteById(id);
    }
    private BooleanBuilder whereCreator(DefectCommentPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QDefectComment qClass = QDefectComment.defectComment;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getDefectId()) {
                builder.and(qClass.defectId.eq(po.getDefectId()));
            }
            if (null != po.getUserId()) {
                builder.and(qClass.userId.eq(po.getUserId()));
            }
            if (StringUtils.isNotBlank(po.getDesc())) {
                builder.and(qClass.desc.eq(po.getDesc()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (null != po.getModifyDate()) {
                builder.and(qClass.modifyDate.eq(po.getModifyDate()));
            }
            if (StringUtils.isNotBlank(po.getModifyBy())) {
                builder.and(qClass.modifyBy.eq(po.getModifyBy()));
            }
        }
        return builder;
    }
}
