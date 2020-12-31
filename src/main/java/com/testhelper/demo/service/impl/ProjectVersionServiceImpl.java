package com.testhelper.demo.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.ProjectVersion;
import com.testhelper.demo.entity.QProjectVersion;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.ProjectVersionPo;
import com.testhelper.demo.repository.ProjectVersionRepository;
import com.testhelper.demo.service.ProjectService;
import com.testhelper.demo.service.ProjectVersionService;
import com.testhelper.demo.utils.EntityUtils;
import com.testhelper.demo.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 13:44
 */
@Service("ProjectVersionService")
@Transactional(rollbackFor = Exception.class)
public class ProjectVersionServiceImpl extends BaseServiceImpl implements ProjectVersionService {
    @Autowired
    private ProjectVersionRepository projectVersionRepository;
    @Autowired
    private ProjectService projectService;
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
    public ProjectVersion findProjectVersionByProjectIdAndVersionNo(Long projectId, String versionNo) {
        return projectVersionRepository.findProjectVersionByProjectIdAndVersionNo(projectId, versionNo);
    }

    @Override
    public ProjectVersion save(ProjectVersion projectVersion) {
        ProjectVersion old = projectVersionRepository.findProjectVersionById(projectVersion.getId());
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        String msg = EntityUtils.compareEntity(old, projectVersion);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_project", projectVersion.getProjectId(), "修改" + old.getVersionNo() + "版本;" + msg, "admin");
        }
        Project project = projectService.load(projectVersion.getProjectId());
        if (null != project && project.getVersionNo().equals(old.getVersionNo()) && !projectVersion.getVersionNo().equals(old.getVersionNo())) {
            project.setVersionNo(projectVersion.getVersionNo());
            projectService.save(project);
        }
        projectVersion.setModifyBy("admin");
        projectVersion.setModifyDate(new Date());
        return projectVersionRepository.save(projectVersion);
    }

    @Override
    public ProjectVersion add(ProjectVersion projectVersion) {
        projectVersion.setCreateBy("admin");
        projectVersion.setCreateDate(new Date());
        projectVersion.setModifyBy("admin");
        projectVersion.setModifyDate(new Date());
        ProjectVersion p = projectVersionRepository.save(projectVersion);
        LogUtils.log("tb_project", p.getProjectId(), "添加新版本" + projectVersion.getVersionNo(), "admin");
        return p;
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
            if (StringUtils.isNotBlank(po.getRemark())) {
                builder.and(qClass.remark.eq(po.getRemark()));
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
