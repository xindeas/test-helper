package com.testhelper.component;

import com.testhelper.entity.Permission;
import com.testhelper.entity.User;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.PermissionPo;
import com.testhelper.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2021 年 09 月 02 日 星期四 08 点 49 分 50 秒
 */
@Component
public class PermissionComponent {
    @Autowired
    private PermissionService permissionservice;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    public ResultHelperPo query(PageHelperPo<Permission, PermissionPo> page) {
        return new ResultHelperPo(true, permissionservice.query(page), "");
    }

    /**
     * 加载
     *
     * @param id
     * @return
     */
    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, permissionservice.load(id), "");
    }

    /**
     * 修改
     *
     * @param permission
     * @return
     */
    public ResultHelperPo save(Permission permission) {
        if (null == permission.getId()) {
            return new ResultHelperPo(false, permission, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, permissionservice.save(permission, user.getLogin()), "");
    }

    /**
     * 新增
     *
     * @param permission
     * @return
     */
    public ResultHelperPo add(Permission permission) {
        if (null != permission.getId()) {
            return new ResultHelperPo(false, permission, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, permissionservice.add(permission, user.getLogin()), "");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public ResultHelperPo delete(Long id) {
        permissionservice.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
