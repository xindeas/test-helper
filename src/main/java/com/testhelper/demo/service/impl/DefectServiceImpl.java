package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.entity.QDefect;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.DefectPo;
import com.testhelper.demo.repository.DefectRepository;
import com.testhelper.demo.service.DefectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:18
 */
@Service
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
        return defectRepository.findDefectById(id);
    }

    @Override
    public Defect save(Defect defect) {
        return defectRepository.save(defect);
    }

    @Override
    public Defect add(Defect defect) {
        return defectRepository.save(defect);
    }

    @Override
    public void delete(Long id) {
        defectRepository.deleteById(id);
    }
    private BooleanBuilder whereCreator(DefectPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QDefect qClass = QDefect.defect;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (StringUtils.isNotBlank(po.getTitle())) {
                builder.and(qClass.title.eq(po.getTitle()));
            }
            if (StringUtils.isNotBlank(po.getDefectNo())) {
                builder.and(qClass.defectNo.eq(po.getDefectNo()));
            }
            if (StringUtils.isNotBlank(po.getRemark())) {
                builder.and(qClass.remark.eq(po.getRemark()));
            }
            if (StringUtils.isNotBlank(po.getTargetVer())) {
                builder.and(qClass.targetVer.eq(po.getTargetVer()));
            }
            if (StringUtils.isNotBlank(po.getModule())) {
                builder.and(qClass.module.eq(po.getModule()));
            }
            if (null != po.getAssignTo()) {
                builder.and(qClass.assignTo.eq(po.getAssignTo()));
            }
            if (null != po.getFindBy()) {
                builder.and(qClass.findBy.eq(po.getFindBy()));
            }
            if (null != po.getTestBy()) {
                builder.and(qClass.testBy.eq(po.getTestBy()));
            }
            if (StringUtils.isNotBlank(po.getStatus())) {
                builder.and(qClass.status.eq(po.getStatus()));
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
