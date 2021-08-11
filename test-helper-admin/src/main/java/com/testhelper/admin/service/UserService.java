package com.testhelper.admin.service;

import com.testhelper.admin.entity.User;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.admin.pojo.UserPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public interface UserService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    public PageHelperPo<User, UserPo> query(PageHelperPo<User, UserPo> page);

    /**
     * 根据ID加载实体
     * @param id
     * @return
     */
    public User load(Long id);

    /**
     * 编辑
     * @param user
     * @return
     */
    public User save(User user);

    /**
     * 新增
     * @param user
     * @return
     */
    public User add(User user);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据账号加载实体
     * @param login
     * @return
     */
    public User login(String login);
}
