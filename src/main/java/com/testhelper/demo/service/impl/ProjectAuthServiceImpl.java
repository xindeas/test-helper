package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.dto.ProjectAuthDto;
import com.testhelper.demo.entity.ProjectAuth;
import com.testhelper.demo.entity.QProject;
import com.testhelper.demo.entity.QProjectAuth;
import com.testhelper.demo.entity.QUser;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectAuthPo;
import com.testhelper.demo.repository.ProjectAuthRepository;
import com.testhelper.demo.service.ProjectAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Service("ProjectAuthService")
@Transactional(rollbackFor = Exception.class)
public class ProjectAuthServiceImpl extends BaseServiceImpl implements ProjectAuthService {
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
        query = sortCreator(qClass, ProjectAuthPo.class, query, page.getSorts());
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
    public List<ProjectAuth> saveOrAdd(Long projectId, List<ProjectAuth> list) {
        // 全部删除
        projectAuthRepository.deleteProjectAuthsByProjectId(projectId);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        for (ProjectAuth item : list) {
            item.setId(null);
            item.setProjectId(projectId);
            item = projectAuthRepository.save(item);
        }
        return list;
    }

    @Override
    public List<ProjectAuth> findAllByProjectId(Long projectId) {
        return projectAuthRepository.findProjectAuthByProjectId(projectId);
    }

    @Override
    public List<ProjectAuthDto> findAuthUsersByProjectId(Long projectId) {
        QProjectAuth qClass = QProjectAuth.projectAuth;
        QUser qUser = QUser.user;

        JPAQuery<ProjectAuthDto> query = queryFactory
                .select(Projections.bean(ProjectAuthDto.class,
                        qClass.projectId.as("projectId"),
                        qUser.id.as("userId"),
                        qUser.avatar.as("userAvatar"),
                        qUser.name.as("userName"),
                        qUser.login.as("userLogin"),
                        qUser.mobile.as("userMobile"),
                        qUser.email.as("userEmail")
                ))
                .from(qClass)
                .leftJoin(qUser).on(qUser.id.eq(qClass.userId))
                .where(qClass.projectId.eq(projectId));
        return query.fetch();
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
