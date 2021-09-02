package com.testhelper.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.testhelper.entity.Permission;
import com.testhelper.entity.QPermission;
import com.testhelper.po.PageHelperPo;
import com.testhelper.pojo.PermissionPo;
import com.testhelper.repository.PermissionRepository;
import com.testhelper.service.PermissionService;
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
 * @Date: 2021 年 09 月 02 日 星期四 09 点 00 分 26 秒
 */
@Service("PermissionService")
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public PageHelperPo<Permission, PermissionPo> query(PageHelperPo<Permission, PermissionPo> page) {
        if (null == page) {
            return null;
        }
        QPermission qClass = QPermission.permission;
        PermissionPo po = page.getFilter();
        BooleanBuilder builder = whereCreator(po);
        JPAQuery<Permission> query = queryFactory
                .selectFrom(qClass)
                .where(builder);
        query = sortCreator(qClass, PermissionPo.class, query, page.getSorts());
        return this.paginationQuery(query, page);
    }

    @Override
    public Permission load(Long id) {
        Permission permission =
                permissionRepository.findPermissionById(id);
        Session session = entityManager.unwrap(Session.class);
        session.evict(permission);
        return permission;
    }

    @Override
    public Permission save(Permission permission, String userLogin) {
        Permission
                old = permissionRepository.findPermissionById(permission.getId());

        String msg = EntityUtils.compareEntity(old, permission);
        if (StringUtils.isNotBlank(msg)) {
            LogUtils.log("tb_project_module", permission.getId(), msg, userLogin);
        }
        permission.setModifyBy(userLogin);
        permission.setModifyDate(new Date());
        return permissionRepository.save(permission);
    }

    @Override
    public Permission add(Permission permission, String userLogin) {
        permission.setCreateBy(userLogin);
        permission.setCreateDate(new Date());
        permission.setModifyBy(userLogin);
        permission.setModifyDate(new Date());
        Permission p = permissionRepository.save(permission);
        LogUtils.log("tb_project_module", p.getId(), "创建一条新纪录", userLogin);
        return p;
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }

    private BooleanBuilder whereCreator(PermissionPo po) {
        BooleanBuilder builder = new BooleanBuilder();
        if (null != po) {
            QPermission qClass = QPermission.permission;
            if (null != po.getId()) {
                builder.and(qClass.id.eq(po.getId()));
            }
            if (null != po.getParentId()) {
                builder.and(qClass.parentId.eq(po.getParentId()));
            }
            if (StringUtils.isNotBlank(po.getName())) {
                builder.and(qClass.name.eq(po.getName()));
            }
            if (StringUtils.isNotBlank(po.getType())) {
                builder.and(qClass.type.eq(po.getType()));
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
