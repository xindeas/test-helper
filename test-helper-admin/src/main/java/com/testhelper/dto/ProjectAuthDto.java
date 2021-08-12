package com.testhelper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class ProjectAuthDto {
    private Long projectId;
    private Long userId;
    private String userAvatar;
    private String userName;
    private String userLogin;
    private String userMobile;
    private String userEmail;
}
