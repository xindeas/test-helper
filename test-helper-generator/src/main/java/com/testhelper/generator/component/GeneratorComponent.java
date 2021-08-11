package com.testhelper.generator.component;

import com.testhelper.common.po.ResultHelperPo;
import com.testhelper.generator.service.impl.BaseServiceImpl;
import com.testhelper.generator.utils.CodeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @Author: Xindeas
 * @Date: 2021/1/6 15:16
 */
@Component
public class GeneratorComponent {
    @Autowired
    @Qualifier("BaseService")
    private BaseServiceImpl baseServiceImpl;

    public ResultHelperPo createCode(String tableName) {
        CodeCreator.entityCreator(tableName);
        return new ResultHelperPo(true, null, "");
    }

    public ResultHelperPo getAllTable() {
        return new ResultHelperPo(true, baseServiceImpl.getAllTable(), "");
    }
}
