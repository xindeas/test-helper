package com.testhelper.demo.component;

import com.testhelper.demo.dto.DefectCommentDto;
import com.testhelper.demo.entity.DefectComment;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.DefectCommentPo;
import com.testhelper.demo.service.DefectCommentService;
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
        return new ResultHelperPo(true, defectCommentService.save(defectComment), "");
    }

    public ResultHelperPo add(DefectCommentDto dto) {
        if (null == dto.getDefectComment() || null != dto.getDefectComment().getId()) {
            return new ResultHelperPo(false, dto, "新增异常");
        }
        return new ResultHelperPo(true, defectCommentService.add(dto), "");
    }

    public ResultHelperPo delete(Long id) {
        defectCommentService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
