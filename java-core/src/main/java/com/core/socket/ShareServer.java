/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.core.socket;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * 类share.java的实现描述：TODO 类实现描述
 * 
 * @author yuci.wyc 2014年6月17日 上午11:22:14
 */
public class ShareServer extends ServerSocket {

    private static ArrayList   USER_LIST      = new ArrayList();

    private static ArrayList   threader       = new ArrayList();

    private static LinkedList  Message_Array  = new LinkedList();

    private static int         Thread_Counter = 0;

    private static boolean     isClear        = true;

    protected static final int SERVER_PORT    = 5554;

    protected FileOutputStream LOG_FILE       = new FileOutputStream("d:/socket.log", true);

    /**
     * @throws IOException
     */
    public ShareServer() throws IOException{
        super(SERVER_PORT);
        new Broadcast();
        Calendar now = Calendar.getInstance();
        String str = "[" + now.getTime().toString() + "] Accepted a connection \n";
        byte[] tmp = str.getBytes();
        LOG_FILE.write(tmp);
        try {
            while (true) {
                Socket socket = accept();
                new CreateServerThread(socket);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            close();
        }

    }

    public static void main(String[] args) throws IOException {
        new ShareServer();
    }

    class Broadcast extends Thread {

        public Broadcast(){
            start();
        }

        public void run() {
            while (true) {
                if (!isClear) {
                    System.out.println("----broadcast----" + threader.size());
                    String tmp = (String) Message_Array.getFirst();
                    for (int i = 0; i < threader.size(); i++) {
                        CreateServerThread client = (CreateServerThread) threader.get(i);
                        client.sendMessage("公共消息：" + tmp);
                    }
                    Message_Array.removeFirst();
                    isClear = Message_Array.size() > 0 ? false : true;
                }
            }
        }
    }

    class CreateServerThread extends Thread {

        private Socket         client;
        private BufferedReader in;
        private PrintWriter    out;
        private String         userName;

        public CreateServerThread(Socket s) throws IOException{
            client = s;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            out.println("-----------Welcome to this chatroom");
            out.println("Input you nickName:");
            start();

        }

        public void sendMessage(String msg) {
            out.println(msg);
        }

        public void run() {
            try {
                System.out.println("CreateServerThread running....");
                int flag = 0;
                Thread_Counter++;
                String line = in.readLine();

                while (!line.equals("bye")) {
                    if (flag == 0) {
                        userName = line;
                        USER_LIST.add(userName);
                        out.println(listOnlineUsers());

                        threader.add(this);

                        pushMessage("[<" + userName + "come on in >]");
                        flag++;
                    } else {
                        pushMessage("<" + userName + ">" + line);

                    }
                    // line = in.readLine();

                }

                out.println("------See you,bye!------");
                // client.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Thread_Counter--;
                threader.remove(this);
                USER_LIST.remove(userName);
                pushMessage("[<" + userName + " left >]");

            }
        }

        private String listOnlineUsers() {
            String s = "---------------Online list------------";

            for (int i = 0; i < USER_LIST.size(); i++) {
                s += "[" + USER_LIST.get(i) + "]";

            }
            s += "---------------------------------";
            return s;

        }

        private void pushMessage(String msg) {
            Message_Array.addLast(msg);
            isClear = false;
        }
    }

}
