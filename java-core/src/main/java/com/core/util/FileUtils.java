/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 类FileUtils.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年6月17日 下午1:39:53
 */
public class FileUtils {

    public static String readInStream(InputStream in) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        return readBufferdReader(buf);
    }

    public static String readBufferdReader(BufferedReader in) {
        StringBuffer retBuf = new StringBuffer();
        try {
            String line = "";
            while ((line = in.readLine()) != null) {
                retBuf.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return retBuf.toString();

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
