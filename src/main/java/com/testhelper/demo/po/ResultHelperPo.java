package com.testhelper.demo.po;

import com.testhelper.demo.utils.ConstUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultHelperPo {
    private String code;
    private String msg;
    private Object result;
    private Boolean success;
    public ResultHelperPo(Boolean success, Object result, String msg) {
        this.success = success;
        this.code = this.success ? ConstUtils.CODE_200 : ConstUtils.CODE_500;
        this.result = result;
        this.msg = msg;
    }
}
