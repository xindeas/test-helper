package com.testhelper.demo.controller;

import com.testhelper.demo.component.UserComponent;
import com.testhelper.demo.dto.UserDto;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge=3600)
public class UserController {
    @Autowired
    private UserComponent userComponent;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/query")
    private ResultHelperPo query (@RequestBody PageHelperPo<User, UserPo> page) {
        return userComponent.query(page);
    }

    /**
     * 加载
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    private ResultHelperPo load (@PathVariable("id") Long id) {
        return userComponent.load(id);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("/save")
    private ResultHelperPo save (@RequestBody User user) {
        return userComponent.save(user);
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/add")
    private ResultHelperPo add (@RequestBody User user) {
        return userComponent.add(user);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    private ResultHelperPo delete (@PathVariable("id") Long id) {
        return userComponent.delete(id);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    private ResultHelperPo login (@RequestBody User user) {
        return userComponent.login(user);
    }
}
