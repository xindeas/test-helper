package com.testhelper.demo.component;

import com.testhelper.demo.entity.DefectCommentAt;
import com.testhelper.demo.entity.User;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.pojo.DefectCommentAtPo;
import com.testhelper.demo.service.DefectCommentAtService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2020/12/21 10:01
 */
@Component
public class DefectCommentAtComponent {
    @Autowired
    private DefectCommentAtService defectCommentAtService;

    public ResultHelperPo query(PageHelperPo<DefectCommentAt, DefectCommentAtPo> page) {
        return new ResultHelperPo(true, defectCommentAtService.query(page), "");
    }

    public ResultHelperPo load(Long id) {
        return new ResultHelperPo(true, defectCommentAtService.load(id), "");
    }
    public ResultHelperPo save(DefectCommentAt defectCommentAt) {
        if (null == defectCommentAt.getId()) {
            return new ResultHelperPo(false, defectCommentAt, "修改异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectCommentAtService.save(defectCommentAt, user.getLogin()), "");
    }
    public ResultHelperPo add(DefectCommentAt defectCommentAt) {
        if (null != defectCommentAt.getId()) {
            return new ResultHelperPo(false, defectCommentAt, "新增异常");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return new ResultHelperPo(true, defectCommentAtService.add(defectCommentAt, user.getLogin()), "");
    }
    public ResultHelperPo delete(Long id) {
        defectCommentAtService.delete(id);
        return new ResultHelperPo(true, id, "");
    }
}
