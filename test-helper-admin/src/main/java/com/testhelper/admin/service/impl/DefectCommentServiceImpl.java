package com.testhelper.admin.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.admin.dto.DefectCommentDto;
import com.testhelper.admin.entity.DefectComment;
import com.testhelper.admin.entity.QDefectComment;
import com.testhelper.admin.entity.QUser;
import com.testhelper.admin.entity.User;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.DefectCommentPo;
import com.testhelper.admin.repository.DefectCommentRepository;
import com.testhelper.admin.repository.UserRepository;
import com.testhelper.admin.service.DefectCommentService;
import com.testhelper.common.service.impl.BaseServiceImpl;
import com.testhelper.common.utils.EntityUtils;
import com.testhelper.common.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date:
 */
@Service("DefectCommentService")
@Transactional(rollbackFor = Exception.class)
public class DefectCommentServiceImpl extends BaseServiceImpl implements DefectCommentService {
    @Autowired
    private DefectCommentRepository defectCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<DefectComment, DefectCommentPo> query(PageHelperPo<DefectComment, DefectCommentPo> page) {
        if (null == page) {
            return null;
        }
        QDefectComment defectComment = new QDefectComment("defectComment");
        QDefectComment referDefectComment = new QDefectComment("referDefectComment");
        QUser user = QUser.user;
        DefectCommentPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<DefectCommentDto> query = queryFactory
                .select(Projections.bean(DefectCommentDto.class, defectComment.as("defectComment"), user.as("user"), referDefectComment.as("referDefectComment")))
                .from(defectComment)
                .leftJoin(user)
                .on(defectComment.userId.eq(user.id))
                .leftJoin(referDefectComment)
                .on(defectComment.reactCommentId.eq(referDefectComment.id))
                .where(builder);
        query = sortCreator(defectComment, DefectCommentPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public DefectComment load(Long id) {
        DefectComment defectComment = defectCommentRepository.findDefectCommentById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(defectComment);
        return defectComment;
    }

    @Override
    public DefectComment save(DefectComment defectComment, String userLogin) {
        DefectComment old = defectCommentRepository.findDefectCommentById(defectComment.getId());

        String msg = EntityUtils.compareEntity(old, defectComment);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_defect_comment", defectComment.getId(), msg, userLogin);
        }
        defectComment.setModifyBy(userLogin);
        defectComment.setModifyDate(new Date());
        return defectCommentRepository.save(defectComment);
    }

    @Override
    public DefectCommentDto add(DefectCommentDto dto, String userLogin) {
        DefectComment dc = dto.getDefectComment();
        dc.setCreateDate(new Date());
        dc.setCreateBy(userLogin);
        dc.setModifyDate(new Date());
        dc.setModifyBy(userLogin);
        DefectComment p = defectCommentRepository.save(dc);
        dto.setDefectComment(p);
        User user = userRepository.findUserById(dc.getUserId());
        LogUtils.log("tb_defect", p.getDefectId(), user.getName() + "评论说：" + p.getRemark(), userLogin);
        return dto;
    }

    @Override
    public void delete(Long id) {
        defectCommentRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(DefectCommentPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QDefectComment qClass = QDefectComment.defectComment;
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (null != po.getDefectId()) {
                builder.and(qClass.defectId.eq(po.getDefectId()));
            }
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (StringUtils.isNotBlank(po.getModifyBy())) {
                builder.and(qClass.modifyBy.eq(po.getModifyBy()));
            }
            if (null != po.getModifyDate()) {
                builder.and(qClass.modifyDate.eq(po.getModifyDate()));
            }
            if (null != po.getReactCommentId()) {
                builder.and(qClass.reactCommentId.eq(po.getReactCommentId()));
            }
            if (StringUtils.isNotBlank(po.getRemark())) {
                builder.and(qClass.remark.eq(po.getRemark()));
            }
            if (null != po.getUserId()) {
                builder.and(qClass.userId.eq(po.getUserId()));
            }
        }
        return builder;
    }
}
