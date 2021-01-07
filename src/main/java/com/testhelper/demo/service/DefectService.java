package com.testhelper.demo.service;

import com.testhelper.demo.entity.Defect;
import com.testhelper.demo.po.PageHelperPo;
import com.testhelper.demo.pojo.DefectPo;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 15:17
 */
public interface DefectService {
    /**
     * 分页查询
     *
     * @param page 分页参数
     * @return
     */
    public PageHelperPo<Defect, DefectPo> query(PageHelperPo<Defect, DefectPo> page);

    /**
     * 根据ID加载实体
     *
     * @param id 流水号
     * @return
     */
    public Defect load(Long id);

    /**
     * 编辑
     *
     * @param defect
     * @return
     */
    public Defect save(Defect defect);

    /**
     * 新增
     *
     * @param defect
     * @return
     */
    public Defect add(Defect defect);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id);
}
