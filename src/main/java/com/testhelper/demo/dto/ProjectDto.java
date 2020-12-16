package com.testhelper.demo.dto;

import com.testhelper.demo.entity.Project;
import com.testhelper.demo.entity.ProjectAuth;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Project project;
    private List<ProjectAuth> auths;
}
