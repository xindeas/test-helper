package com.testhelper.demo.dto;

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
    private Long id;
    private String name;
}
