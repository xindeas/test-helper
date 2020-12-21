package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.ProjectVersion;
import com.testhelper.demo.entity.QProjectVersion;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectVersionPo;
import com.testhelper.demo.repository.ProjectVersionRepository;
import com.testhelper.demo.service.ProjectVersionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 13:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectVersionServiceImpl extends BaseServiceImpl implements ProjectVersionService {
    @Autowired
    private ProjectVersionRepository projectVersionRepository;
    @Autowired
    JPAQueryFactory queryFactory;
    @Override
    public PageHelperPo<ProjectVersion, ProjectVersionPo> query(PageHelperPo<ProjectVersion, ProjectVersionPo> page) {
        if (null == page) {
            return null;
        }
        QProjectVersion qClass = QProjectVersion.projectVersion;

        ProjectVersionPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);

        JPAQuery<ProjectVersion> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, ProjectVersionPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public ProjectVersion load(Long id) {
        return projectVersionRepository.findProjectVersionById(id);
    }

    @Override
    public ProjectVersion save(ProjectVersion projectVersion) {
        return projectVersionRepository.save(projectVersion);
    }

    @Override
    public ProjectVersion add(ProjectVersion projectVersion) {
        return projectVersionRepository.save(projectVersion);
    }

    @Override
    public void delete(Long id) {
        projectVersionRepository.deleteById(id);
    }
    private BooleanBuilder whereCreator(ProjectVersionPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QProjectVersion qClass = QProjectVersion.projectVersion;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getProjectId()) {
                builder.and(qClass.projectId.eq(po.getProjectId()));
            }
            if (StringUtils.isNotBlank(po.getVersionNo())) {
                builder.and(qClass.versionNo.eq(po.getVersionNo()));
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
