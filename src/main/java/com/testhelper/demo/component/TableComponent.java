package com.testhelper.demo.component;

import com.testhelper.demo.po.ResultHelperPo;
import com.testhelper.demo.service.impl.BaseServiceImpl;
import com.testhelper.demo.utils.CodeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2021/1/6 15:16
 */
@Component
public class TableComponent {
    @Autowired
    @Qualifier("BaseService")
    private BaseServiceImpl baseServiceImpl;
    public ResultHelperPo createCode (String tableName) {
        CodeCreator.entityCreator(tableName);
        return new ResultHelperPo(true, null, "");
    }
    public ResultHelperPo getAllTable () {
        return new ResultHelperPo(true, baseServiceImpl.getAllTable(), "");
    }
}
