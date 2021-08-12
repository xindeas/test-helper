package com.testhelper.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Xindeas
 * @Date: 2021/1/8 13:22
 */
public class NoUtils {
    public static String getOrderNo(String prefix) {
        String orderNo = "";
        UUID uuid = UUID.randomUUID();
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = uuid.toString().substring(0, 8);
        orderNo = prefix + orderNo + sdf;
        return orderNo;
    }

    //生成19随机单号 纯数字
    public static String getOrderNoPureNumber(String prefix) {
        String orderNo = "";
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = prefix + orderNo + sdf;
        return orderNo;
    }
}
