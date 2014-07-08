/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.core.util.FileUtils;

/**
 * 类Client.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年6月17日 上午10:43:18
 */
public class Client {

    static Socket server;

    /**
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) {
        try {
            server = new Socket(InetAddress.getLocalHost(), 5554);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream());

            BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String str = wt.readLine();
                out.println(str);
                out.flush();
                if ("end".equals(str)) {
                    break;
                }
                System.out.println("服务器返回信息" + in.readLine());
            }
        } catch (Exception e) {
            try {
                server.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
