package com.testhelper.admin.component;

import com.testhelper.admin.dto.UserDto;
import com.testhelper.admin.entity.User;
import com.testhelper.common.po.PageHelperPo;
import com.testhelper.common.po.ResultHelperPo;
import com.testhelper.admin.pojo.UserPo;
import com.testhelper.admin.service.UserService;
import com.testhelper.common.utils.Base64Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
public class UserComponent {
    @Autowired
    private UserService userService;

    public ResultHelperPo query(PageHelperPo<User, UserPo> page) {
        return new ResultHelperPo(true, userService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, userService.load(id), "");
    }

    public ResultHelperPo save(User user) {
        if (null == user.getId()) {
            return new ResultHelperPo(false, user, "修改异常");
        }
        User oper = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, userService.save(user), "");
    }

    public ResultHelperPo add(User user) {
        if (null != user.getId()) {
            return new ResultHelperPo(false, user, "新增异常");
        }
        User oper = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, userService.add(user), "");
    }

    public ResultHelperPo delete(Long id) {
        userService.delete(id);
        return new ResultHelperPo(true, id, "");
    }

    public ResultHelperPo login(User user) {
        String encoded = Base64Utils.encode(user.getPwd());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLogin(), encoded);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return new ResultHelperPo(false, user, "账号或密码不正确");
        } catch (UnknownAccountException uae) {
            return new ResultHelperPo(false, user, "账户不存在");
        }
        subject.getPrincipal();
        User userData = userService.login(user.getLogin());
        UserDto dto = new UserDto();
        dto.setUser(userData);
        dto.setToken(subject.getSession().getId().toString());

        subject.getSession().setAttribute(subject.getSession().getId().toString(), userData);
        return new ResultHelperPo(true, dto, "登陆成功");
    }
}
