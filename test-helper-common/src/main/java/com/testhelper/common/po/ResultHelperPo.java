package com.testhelper.common.po;

import com.testhelper.common.utils.ConstUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Getter
@Setter
public class ResultHelperPo {
    private String code;
    private String msg;
    private Object result;
    private Boolean success;
    public ResultHelperPo(Boolean success, Object result, String msg) {
        this.success = success;
        this.code = this.success ? ConstUtils.CODE_SUCCESS : ConstUtils.CODE_FAILD;
        this.result = result;
        this.msg = msg;
    }
}
