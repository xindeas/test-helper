package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.QProject;
import com.testhelper.demo.entity.QProjectAuth;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectPo;
import com.testhelper.demo.repository.ProjectRepository;
import com.testhelper.demo.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<Project, ProjectPo> query(PageHelperPo<Project, ProjectPo> page) {
        if (null == page) {
            return null;
        }
        QProject qClass = QProject.project;

        ProjectPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<Project> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        try {
            query = sortCreator(qClass, ProjectPo.class, query, page.getSorts());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return projectRepository.findProjectById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project add(Project project) {
        return projectRepository.save(project);
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
