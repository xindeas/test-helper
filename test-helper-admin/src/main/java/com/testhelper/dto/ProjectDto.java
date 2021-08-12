package com.testhelper.dto;

import com.testhelper.entity.Project;
import com.testhelper.entity.ProjectAuth;
import com.testhelper.entity.ProjectVersion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class ProjectDto {
    private Project project;
    private List<ProjectAuth> auths;
    private List<ProjectAuthDto> authUsers;
    private Long userCount;
    private List<ProjectVersion> projectVersions;
}
