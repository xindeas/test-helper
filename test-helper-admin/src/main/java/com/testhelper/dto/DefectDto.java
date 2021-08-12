package com.testhelper.dto;

import com.testhelper.entity.Defect;
import com.testhelper.entity.Project;
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
