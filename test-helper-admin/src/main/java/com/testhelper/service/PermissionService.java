package com.testhelper.service;


import com.testhelper.entity.Permission;
import com.testhelper.pojo.PermissionPo;
import com.testhelper.po.PageHelperPo;

/**
 * @Author: Xindeas
 * @Date: 2021 年 09 月 02 日 星期四 08 点 49 分 50 秒
 */
public interface PermissionService {

    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<Permission, PermissionPo> query(PageHelperPo<Permission, PermissionPo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public Permission load(Long id);

    /**
     * 编辑
     *
     * @param permission
     * @return
     */
    public Permission save(Permission permission, String userLogin);

    /**
     * 新增
     *
     * @param permission
     * @return
     */
    public Permission add(Permission permission, String userLogin);

    /**
     * 删除
     *
     * @param id 流水号
     */
    public void delete(Long id);
}
