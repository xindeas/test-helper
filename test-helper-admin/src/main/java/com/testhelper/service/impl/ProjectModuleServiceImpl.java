package com.testhelper.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.dto.ProjectModuleDto;
import com.testhelper.entity.ProjectModule;
import com.testhelper.entity.QProject;
import com.testhelper.entity.QProjectModule;
import com.testhelper.po.PageHelperPo;
import com.testhelper.pojo.ProjectModulePo;
import com.testhelper.repository.ProjectModuleRepository;
import com.testhelper.service.ProjectModuleService;
import com.testhelper.utils.EntityUtils;
import com.testhelper.utils.LogUtils;
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
@Service("ProjectModuleService")
@Transactional(rollbackFor = Exception.class)
public class ProjectModuleServiceImpl extends BaseServiceImpl implements ProjectModuleService {
    @Autowired
    private ProjectModuleRepository projectModuleRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<ProjectModule, ProjectModulePo> query(PageHelperPo<ProjectModule, ProjectModulePo> page) {
        if (null == page) {
            return null;
        }
        QProjectModule qClass = QProjectModule.projectModule;
        QProject qProject = QProject.project;
        ProjectModulePo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<ProjectModuleDto> query = queryFactory
                .select(Projections.bean(ProjectModuleDto.class, qClass.as("projectModule"), qProject.as("project")))
                .from(qClass)
                .leftJoin(qProject)
                .on(qClass.projectId.eq(qProject.id))
                .where(builder);
        query = sortCreator(qClass, ProjectModulePo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public ProjectModule load(Long id) {
        ProjectModule projectModule = projectModuleRepository.findProjectModuleById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(projectModule);
        return projectModule;
    }

    @Override
    public ProjectModule save(ProjectModule projectModule, String userLogin) {
        ProjectModule old = projectModuleRepository.findProjectModuleById(projectModule.getId());

        String msg = EntityUtils.compareEntity(old, projectModule);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_project_module", projectModule.getId(), msg, userLogin);
        }
        projectModule.setModifyBy(userLogin);
        projectModule.setModifyDate(new Date());
        return projectModuleRepository.save(projectModule);
    }

    @Override
    public ProjectModule add(ProjectModule projectModule, String userLogin) {
        projectModule.setCreateBy(userLogin);
        projectModule.setCreateDate(new Date());
        projectModule.setModifyBy(userLogin);
        projectModule.setModifyDate(new Date());
        ProjectModule p = projectModuleRepository.save(projectModule);
        LogUtils.log("tb_project_module", p.getId(), "创建一条新纪录", userLogin);
        return p;
    }

    @Override
    public void delete(Long id) {
        projectModuleRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(ProjectModulePo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QProjectModule qClass = QProjectModule.projectModule;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getProjectId()) {
                builder.and(qClass.projectId.eq(po.getProjectId()));
            }
            if (StringUtils.isNotBlank(po.getModuleName())) {
                builder.and(qClass.moduleName.eq(po.getModuleName()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (StringUtils.isNotBlank(po.getModifyBy())) {
                builder.and(qClass.modifyBy.eq(po.getModifyBy()));
            }
            if (null != po.getModifyDate()) {
                builder.and(qClass.modifyDate.eq(po.getModifyDate()));
            }
        }
        return builder;
    }
}
