package com.testhelper.demo.component;

import com.testhelper.demo.dto.UserDto;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.UserPo;
import com.testhelper.demo.service.UserService;
import com.testhelper.demo.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.testhelper.demo.utils.Base64Utils;

@Component
public class UserComponent {
    @Autowired
    private UserService userService;

    public ResultHelperPo query(PageHelperPo<UserDto, UserPo> page) {
        return new ResultHelperPo(true, userService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, userService.load(id), "");
    }
    public ResultHelperPo save(User user) {
        if (null == user.getId()) {
            return new ResultHelperPo(false, user, "修改异常");
        }
        return new ResultHelperPo(true, userService.save(user), "");
    }
    public ResultHelperPo add(User user) {
        if (null != user.getId()) {
            return new ResultHelperPo(false, user, "新增异常");
        }
        return new ResultHelperPo(true, userService.add(user), "");
    }
    public ResultHelperPo delete(Long id) {
        userService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
    public ResultHelperPo login(User user) {
        String encoded = Base64Utils.encode(user.getPwd());
        User userData = userService.login(user.getLogin());
        if (null == userData) {
            return new ResultHelperPo(false, user, "账号或密码不正确");
        }
        if (StrUtils.nullToEmpty(encoded).equals(userData.getPwd())) {
            return new ResultHelperPo(true, user, "登陆成功");
        }
        return new ResultHelperPo(false, user, "登陆失败");
    }
}
