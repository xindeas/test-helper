package com.testhelper.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    private Long id;
    private String name;

    public UserDto (Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
