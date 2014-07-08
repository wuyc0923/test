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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类MultiUser.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年6月17日 上午11:06:21
 */
public class MultiUser extends Thread {

    private Socket client;

    public MultiUser(Socket c){
        this.client = c;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            while (true) {
                String str = in.readLine();
                System.out.println(str);
                out.println("has receive..." + str);
                out.flush();
                if ("end".equals(str)) {
                    break;
                }

            }

        } catch (Exception e) {
            try {
                client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5554);
        while (true) {
            MultiUser mu = new MultiUser(server.accept());
            mu.start();
        }

    }

}
