/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.util;

import java.util.UUID;

/**
 * 类RandomUtil.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年7月1日 下午1:30:45
 */
public class RandomUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }

    }

}
