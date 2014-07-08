/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 类JConsole.java的实现描述：console
 * 
 * @author yuci.wyc 2014年6月23日 下午3:52:17
 */
public class JConsole {

    public static void fillHeep(int num) throws InterruptedException {
        List<ooMObject> list = new ArrayList<ooMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(100);
            list.add(new ooMObject());
        }
        System.out.println("---start gc------");
        System.gc();
        System.out.println("---end ------");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        try {
            fillHeep(1000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

/**
 * 类JConsole.java的实现描述：内存占位对象，一个ooMObject 大小1K
 * 
 * @author yuci.wyc 2014年6月23日 下午3:53:52
 */
class ooMObject {

    public byte[] placeHolder = new byte[1024];
}
