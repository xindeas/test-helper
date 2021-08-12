package com.testhelper.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.dto.ProjectDto;
import com.testhelper.entity.Project;
import com.testhelper.entity.QProject;
import com.testhelper.entity.QProjectAuth;
import com.testhelper.po.PageHelperPo;
import com.testhelper.pojo.ProjectPo;
import com.testhelper.repository.ProjectRepository;
import com.testhelper.service.ProjectService;
import com.testhelper.utils.EntityUtils;
import com.testhelper.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("ProjectService")
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<ProjectDto, ProjectPo> query(PageHelperPo<ProjectDto, ProjectPo> page) {
        if (null == page) {
            return null;
        }
        QProject qClass = QProject.project;
        QProjectAuth qAuth = QProjectAuth.projectAuth;

        ProjectPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<ProjectDto> query = queryFactory
                .select(Projections.bean(ProjectDto.class, qClass.as("project"), qAuth.count().as("userCount")))
                .from(qClass)
                .leftJoin(qAuth)
                .on(qClass.id.eq(qAuth.projectId))
                .where(builder)
                .groupBy(qClass.id);
        query = sortCreator(qClass, ProjectPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public List<Project> queryForOptions(Long userId) {
        if (null == userId) {
            return null;
        }
        QProject qClass = QProject.project;
        QProjectAuth qAuth = QProjectAuth.projectAuth;

        JPAQuery<Project> query = queryFactory
                .selectFrom(qClass)
                .leftJoin(qAuth)
                .on(qClass.id.eq(qAuth.projectId))
                .where(
                        qClass.belongsTo.eq(userId)
                                .or(qAuth.userId.eq(userId))
                                .and(qClass.enabled.eq(true))
                );
        return query.fetch();
    }

    @Override
    public Project load(Long id) {
        Project project = projectRepository.findProjectById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(project);
        return project;
    }

    @Override
    public Project save(Project project, String userLogin) {
        Project old = projectRepository.findProjectById(project.getId());

        String msg = EntityUtils.compareEntity(old, project);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_project", project.getId(), msg, userLogin);
        }
        project.setModifyBy(userLogin);
        project.setModifyDate(new Date());
        return projectRepository.save(project);
    }

    @Override
    public Project add(Project project, String userLogin) {
        project.setEnabled(true);
        project.setCreateBy(userLogin);
        project.setCreateDate(new Date());
        project.setModifyBy(userLogin);
        project.setModifyDate(new Date());
        Project p = projectRepository.save(project);
        LogUtils.log("tb_project", p.getId(), "创建一条新纪录", userLogin);
        return p;
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(ProjectPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QProject qClass = QProject.project;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (StringUtils.isNotBlank(po.getName())) {
                builder.and(qClass.name.eq(po.getName()));
            }
            if (StringUtils.isNotBlank(po.getVersionNo())) {
                builder.and(qClass.versionNo.eq(po.getVersionNo()));
            }
            if (null != po.getBelongsTo()) {
                builder.and(qClass.belongsTo.eq(po.getBelongsTo()));
            }
            if (null != po.getEnabled()) {
                builder.and(qClass.enabled.eq(po.getEnabled()));
            }
            if (StringUtils.isNotBlank(po.getCreateBy())) {
                builder.and(qClass.createBy.eq(po.getCreateBy()));
            }
            if (StringUtils.isNotBlank(po.getModifyBy())) {
                builder.and(qClass.modifyBy.eq(po.getModifyBy()));
            }
            if (null != po.getCreateDate()) {
                builder.and(qClass.createDate.eq(po.getCreateDate()));
            }
            if (null != po.getModifyDate()) {
                builder.and(qClass.modifyDate.eq(po.getModifyDate()));
            }
        }
        return builder;
    }
}
