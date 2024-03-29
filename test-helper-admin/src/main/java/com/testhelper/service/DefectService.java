package com.testhelper.service;

import com.testhelper.dto.DefectDto;
import com.testhelper.entity.Defect;
import com.testhelper.pojo.DefectPo;
import com.testhelper.po.PageHelperPo;

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
    public PageHelperPo<DefectDto, DefectPo> query(PageHelperPo<DefectDto, DefectPo> page);

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
    public Defect save(Defect defect, String userLogin);

    /**
     * 新增
     *
     * @param defect
     * @return
     */
    public Defect add(Defect defect, String userLogin);

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id);
}
