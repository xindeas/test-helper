package com.testhelper.component;

import com.testhelper.po.ResultHelperPo;
import com.testhelper.service.impl.TableServiceImpl;
import com.testhelper.utils.CodeCreator;
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
    @Qualifier("TableService")
    private TableServiceImpl baseServiceImpl;

    public ResultHelperPo createCode(String tableName) {
        CodeCreator.entityCreator(tableName);
        return new ResultHelperPo(true, null, "");
    }

    public ResultHelperPo getAllTable() {
        return new ResultHelperPo(true, baseServiceImpl.getAllTable(), "");
    }
}
