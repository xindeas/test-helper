package com.testhelper.controller;

import com.testhelper.component.UserComponent;
import com.testhelper.entity.User;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.UserPo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserComponent userComponent;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query(@RequestBody PageHelperPo<User, UserPo> page) {
        return userComponent.query(page);
    }

    /**
     * 加载
     *
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load(@PathVariable("id") Long id) {
        return userComponent.load(id);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save(@RequestBody User user) {
        return userComponent.save(user);
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add(@RequestBody User user) {
        return userComponent.add(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete(@PathVariable("id") Long id) {
        return userComponent.delete(id);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    private ResultHelperPo login(@RequestBody User user) {
        return userComponent.login(user);
    }

    @RequestMapping("/logout")
    public ResultHelperPo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultHelperPo(true, null, "退出登录");
    }
}
