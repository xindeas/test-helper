package com.testhelper.demo.component;

import com.testhelper.demo.dto.DefectDto;
import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.DefectPo;
import com.testhelper.demo.service.DefectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:26
 */
@Component
public class DefectComponent {
    @Autowired
    private DefectService defectService;

    public ResultHelperPo query(PageHelperPo<DefectDto, DefectPo> page) {
        return new ResultHelperPo(true, defectService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, defectService.load(id), "");
    }
    public ResultHelperPo save(Defect defect) {
        if (null == defect.getId()) {
            return new ResultHelperPo(false, defect, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectService.save(defect, user.getLogin()), "");
    }
    public ResultHelperPo add(Defect defect) {
        if (null != defect.getId()) {
            return new ResultHelperPo(false, defect, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectService.add(defect, user.getLogin()), "");
    }
    public ResultHelperPo delete(Long id) {
        defectService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
