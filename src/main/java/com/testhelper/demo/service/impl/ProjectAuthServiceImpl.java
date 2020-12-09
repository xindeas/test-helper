package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.ProjectAuth;
import com.testhelper.demo.entity.QProject;
import com.testhelper.demo.entity.QProjectAuth;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectAuthPo;
import com.testhelper.demo.repository.ProjectAuthRepository;
import com.testhelper.demo.repository.ProjectRepository;
import com.testhelper.demo.service.ProjectAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectAuthServiceImpl extends BaseServiceImpl<ProjectAuth> implements ProjectAuthService {
    @Autowired
    private ProjectAuthRepository projectAuthRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<ProjectAuth, ProjectAuthPo> query(PageHelperPo<ProjectAuth, ProjectAuthPo> page) {
        if (null == page) {
            return null;
        }
        QProjectAuth qClass = QProjectAuth.projectAuth;

        ProjectAuthPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<ProjectAuth> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        try {
            query = sortCreator(qClass, ProjectAuthPo.class, query, page.getSorts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.paginationQuery(query, page);
    }

    @Override
    public ProjectAuth load(Long id) {
        return projectAuthRepository.findProjectAuthById(id);
    }

    @Override
    public ProjectAuth save(ProjectAuth project) {
        return projectAuthRepository.save(project);
    }

    @Override
    public ProjectAuth add(ProjectAuth project) {
        return projectAuthRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectAuthRepository.deleteById(id);
    }
    private BooleanBuilder whereCreator(ProjectAuthPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QProjectAuth qClass = QProjectAuth.projectAuth;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getProjectId()) {
                builder.and(qClass.id.eq(po.getProjectId()));
            }
            if (null != po.getUserId()) {
                builder.and(qClass.id.eq(po.getUserId()));
            }
        }
        return builder;
    }
}
