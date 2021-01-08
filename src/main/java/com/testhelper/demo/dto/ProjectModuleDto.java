package com.testhelper.demo.dto;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.ProjectModule;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xindeas
 * @Date: 2021/1/7 16:14
 */
@Getter
@Setter
public class ProjectModuleDto {
    private Project project;
    private ProjectModule projectModule;
}
