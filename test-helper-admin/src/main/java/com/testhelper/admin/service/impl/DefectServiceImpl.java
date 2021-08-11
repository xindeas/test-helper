package com.testhelper.admin.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.admin.dto.DefectDto;
import com.testhelper.admin.entity.Defect;
import com.testhelper.admin.entity.QDefect;
import com.testhelper.admin.entity.QProject;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.DefectPo;
import com.testhelper.admin.repository.DefectRepository;
import com.testhelper.admin.service.DefectService;
import com.testhelper.common.service.impl.BaseServiceImpl;
import com.testhelper.common.utils.EntityUtils;
import com.testhelper.common.utils.LogUtils;
import com.testhelper.common.utils.NoUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date:
 */
@Service("DefectService")
@Transactional(rollbackFor = Exception.class)
public class DefectServiceImpl extends BaseServiceImpl implements DefectService {
    @Autowired
    private DefectRepository defectRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<DefectDto, DefectPo> query(PageHelperPo<DefectDto, DefectPo> page) {
        if (null == page) {
            return null;
        }
        QDefect qClass = QDefect.defect;
        QProject qProject = QProject.project;
        DefectPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<DefectDto> query = queryFactory
                .select(Projections.bean(DefectDto.class, qClass.as("defect"), qProject.as("project")))
                .from(qClass)
                .leftJoin(qProject)
                .on(qClass.projectId.eq(qProject.id))
                .where(builder);
        query = sortCreator(qClass, DefectPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public Defect load(Long id) {
        Defect defect = defectRepository.findDefectById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(defect);
        return defect;
    }

    @Override
    public Defect save(Defect defect, String userLogin) {
        Defect old = defectRepository.findDefectById(defect.getId());

        String msg = EntityUtils.compareEntity(old, defect);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_defect", defect.getId(), msg, userLogin);
        }
        defect.setModifyBy(userLogin);
        defect.setModifyDate(new Date());
        return defectRepository.save(defect);
    }

    @Override
    public Defect add(Defect defect, String userLogin) {
        String defectNo = NoUtils.getOrderNoPureNumber("DF");

        defect.setDefectNo(defectNo);
        defect.setCreateBy(userLogin);
        defect.setCreateDate(new Date());
        defect.setModifyBy(userLogin);
        defect.setModifyDate(new Date());
        Defect p = defectRepository.save(defect);
        LogUtils.log("tb_defect", p.getId(), "创建一条新纪录", userLogin);
        return p;
    }

    @Override
    public void delete(Long id) {
        defectRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(DefectPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QDefect qClass = QDefect.defect;
            if (!CollectionUtils.isEmpty(po.getAssignTo())) {
                builder.and(qClass.assignTo.in(po.getAssignTo()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (StringUtils.isNotBlank(po.getDefectNo())) {
                builder.and(qClass.defectNo.eq(po.getDefectNo()));
            }
            if (!CollectionUtils.isEmpty(po.getFindBy())) {
                builder.and(qClass.findBy.in(po.getFindBy()));
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
            if (!CollectionUtils.isEmpty(po.getModuleId())) {
                builder.and(qClass.moduleId.in(po.getModuleId()));
            }
            if (null != po.getProjectId()) {
                builder.and(qClass.projectId.eq(po.getProjectId()));
            }
            if (StringUtils.isNotBlank(po.getRemark())) {
                builder.and(qClass.remark.like(po.getRemark()));
            }
            if (!CollectionUtils.isEmpty(po.getStatus())) {
                builder.and(qClass.status.in(po.getStatus()));
            }
            if (!CollectionUtils.isEmpty(po.getTargetVer())) {
                builder.and(qClass.targetVer.in(po.getTargetVer()));
            }
            if (!CollectionUtils.isEmpty(po.getTestBy())) {
                builder.and(qClass.testBy.in(po.getTestBy()));
            }
            if (StringUtils.isNotBlank(po.getTitle())) {
                builder.and(qClass.title.like(po.getTitle()));
            }
        }
        return builder;
    }
}
