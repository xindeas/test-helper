package com.testhelper.demo.service.impl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.utils.NoUtils;
import org.apache.commons.lang3.StringUtils;
import com.testhelper.demo.utils.LogUtils;
import com.testhelper.demo.utils.EntityUtils;
import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.entity.QDefect;
import com.testhelper.demo.pojo.DefectPo;
import com.testhelper.demo.repository.DefectRepository;
import com.testhelper.demo.service.DefectService;
/**
 * @Author: Xindeas
 * @Date:  */
@Service("DefectService")
@Transactional(rollbackFor = Exception.class)
public class DefectServiceImpl extends BaseServiceImpl implements DefectService {
    @Autowired
    private DefectRepository defectRepository;
    @Autowired
    JPAQueryFactory queryFactory;
    @Override
    public PageHelperPo<Defect, DefectPo> query(PageHelperPo<Defect, DefectPo> page) {
        if (null == page) {
            return null;
        }
        QDefect qClass = QDefect.defect;
        DefectPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<Defect> query = queryFactory
                .selectFrom(qClass)
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
    public Defect save(Defect defect) {
        Defect old = defectRepository.findDefectById(defect.getId());

        String msg = EntityUtils.compareEntity(old, defect);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_defect", defect.getId(), msg, "admin");
        }
        defect.setModifyBy("admin");
        defect.setModifyDate(new Date());
        return defectRepository.save(defect);
    }
    @Override
    public Defect add(Defect defect) {
        String defectNo = NoUtils.getOrderNoPureNumber("DF");

        defect.setDefectNo(defectNo);
        defect.setCreateBy("admin");
        defect.setCreateDate(new Date());
        defect.setModifyBy("admin");
        defect.setModifyDate(new Date());
        Defect p = defectRepository.save(defect);
        LogUtils.log("tb_defect", p.getId(), "创建一条新纪录", "admin");
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
            if (null != po.getAssignTo()) {
                builder.and(qClass.assignTo.eq(po.getAssignTo()));
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
            if (null != po.getFindBy()) {
                builder.and(qClass.findBy.eq(po.getFindBy()));
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
            if (null != po.getModuleId()) {
                builder.and(qClass.moduleId.eq(po.getModuleId()));
            }
            if (null != po.getProjectId()) {
                builder.and(qClass.projectId.eq(po.getProjectId()));
            }
            if (StringUtils.isNotBlank(po.getRemark())) {
                builder.and(qClass.remark.eq(po.getRemark()));
            }
            if (StringUtils.isNotBlank(po.getStatus())) {
                builder.and(qClass.status.eq(po.getStatus()));
            }
            if (StringUtils.isNotBlank(po.getTargetVer())) {
                builder.and(qClass.targetVer.eq(po.getTargetVer()));
            }
            if (null != po.getTestBy()) {
                builder.and(qClass.testBy.eq(po.getTestBy()));
            }
            if (StringUtils.isNotBlank(po.getTitle())) {
                builder.and(qClass.title.eq(po.getTitle()));
            }
    }
        return builder;
  }
}
