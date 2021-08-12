package com.testhelper.component;

import com.testhelper.dto.DefectCommentDto;
import com.testhelper.entity.DefectComment;
import com.testhelper.entity.User;
import com.testhelper.po.PageHelperPo;
import com.testhelper.po.ResultHelperPo;
import com.testhelper.pojo.DefectCommentPo;
import com.testhelper.service.DefectCommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:01
 */
@Component
public class DefectCommentComponent {
    @Autowired
    private DefectCommentService defectCommentService;

    public ResultHelperPo query(PageHelperPo<DefectComment, DefectCommentPo> page) {
        return new ResultHelperPo(true, defectCommentService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, defectCommentService.load(id), "");
    }

    public ResultHelperPo save(DefectComment defectComment) {
        if (null == defectComment.getId()) {
            return new ResultHelperPo(false, defectComment, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectCommentService.save(defectComment, user.getLogin()), "");
    }

    public ResultHelperPo add(DefectCommentDto dto) {
        if (null == dto.getDefectComment() || null != dto.getDefectComment().getId()) {
            return new ResultHelperPo(false, dto, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectCommentService.add(dto, user.getLogin()), "");
    }

    public ResultHelperPo delete(Long id) {
        defectCommentService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
