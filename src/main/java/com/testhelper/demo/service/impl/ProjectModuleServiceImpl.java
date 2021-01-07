package com.testhelper.demo.service.impl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.demo.po.PageHelperPo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.testhelper.demo.entity.ProjectModule;
import com.testhelper.demo.entity.QProjectModule;
import com.testhelper.demo.pojo.ProjectModulePo;
import com.testhelper.demo.repository.ProjectModuleRepository;
import com.testhelper.demo.service.ProjectModuleService;
/**
 * @Author: Xindeas
 * @Date:  */
@Service("ProjectModuleService")
@Transactional(rollbackFor = Exception.class)
public class ProjectModuleServiceImpl extends BaseServiceImpl implements ProjectModuleService {
    @Autowired
    private ProjectModuleRepository projectModuleRepository;
    @Autowired
    JPAQueryFactory queryFactory;
    @Override
    public PageHelperPo<ProjectModule, ProjectModulePo> query(PageHelperPo<ProjectModule, ProjectModulePo> page) {        if (null == page) {
            return null;
        }
        QProjectModule qClass = QProjectModule.projectModule;
        ProjectModulePo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<ProjectModule> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, ProjectModulePo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }
    @Override
    public ProjectModule load(Long id) {
        return projectModuleRepository.findProjectModuleById(id);
    }
    @Override
    public ProjectModule save(ProjectModule projectModule) {
        return projectModuleRepository.save(projectModule);
    }
    @Override
    public ProjectModule add(ProjectModule projectModule) {
        return projectModuleRepository.save(projectModule);
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
