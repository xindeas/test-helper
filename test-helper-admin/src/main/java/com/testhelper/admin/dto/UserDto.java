package com.testhelper.admin.dto;

import com.testhelper.admin.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class UserDto implements Serializable {
    private User user;
    private String token;
}
