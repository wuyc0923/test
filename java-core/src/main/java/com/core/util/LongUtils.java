/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.util;

import java.text.DecimalFormat;

/**
 * 类LongUtils.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年6月18日 下午4:48:33
 */
public class LongUtils {

    public static String divide(Long d1, Long d2) {

        if (d1 == null || d2 == null || d2.equals(0l)) {
            return "0";
        }

        double percent = (double) d1 / d2;
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(percent);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Long d1 = 22l;
        Long d2 = 111l;
        System.out.println(divide(d1, d2));

    }

}
