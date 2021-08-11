package com.testhelper.admin.dto;

import com.testhelper.admin.entity.Defect;
import com.testhelper.admin.entity.Project;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 9:26
 */
@Getter
@Setter
public class DefectDto {
    private Defect defect;
    private Project project;
}
