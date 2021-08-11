package com.testhelper.admin.dto;

import com.testhelper.admin.entity.Project;
import com.testhelper.admin.entity.ProjectModule;
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
