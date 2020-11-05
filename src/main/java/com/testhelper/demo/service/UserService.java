package com.testhelper.demo.service;

import com.testhelper.demo.dto.UserDto;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.UserPo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public PageHelperPo<UserDto, UserPo> query(PageHelperPo<UserDto, UserPo> page);
    public User load(Long id);
    public User save(User user);
    public User add(User user);
    public void delete(Long id);
    public User login(String login);
}
