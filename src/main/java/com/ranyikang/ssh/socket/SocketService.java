package com.ranyikang.ssh.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CLASS_NAME: SocketService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: Socket服务端程序  <br/>
 * @date: 2022-12-28 <br/>
 */
@Slf4j
public class SocketService extends Thread {

    private final ServerSocket server;
    private Socket socket;

    public SocketService(int port) {
        try {
            // 建立同服务器端的 Socket , 并且设置 port
            server = new ServerSocket(port);
        } catch (IOException e) {
            log.error("创建 Socket 对象发生错误,错误异常为 {} , 抛错类为 {} , 抛错行数为 {}", e.getMessage(), e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                log.info("Socket 服务端进程启动,等待连线!");
                socket = server.accept();
                log.info("连线成功,获取连线: InetAddress ==> {} ", socket.getInetAddress());
                // 获得连线且无需在接收其他连线,则可关闭 ServerSocket
                server.close();
                PrintStream writer;
                BufferedReader reader;
                writer = new PrintStream(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder getData = new StringBuilder();
                getData.append(reader.readLine());
                log.info("获取到的数据为 {}", getData);
                writer.println(getResponseData());
                writer.flush();
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                log.error("Socket 连线存在异常,异常信息为 {}, 异常类为 {}, 异常行数为 {}", e.getMessage(), e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber());
                log.error("异常为: {}", e.toString());
            }
            i++;
            if (i>= 10){
                break;
            }
        }
    }


    private String getResponseData() {
        return "<?xml version=\"1.0\" encoding=\"gb2312\"?>" +
                "<msg>" +
                "<msg_head>" +
                "<msg_type>1</msg_type" +
                "<msg_id>1005</msg_id>" +
                "<msg_sn>0</msg_sn>" +
                "<version>1</version>" +
                "</msg_head>" +
                "<msg_body>" +
                "<signed_data>MzAWMZAWMDAZ0HWXMDk5MDEyMDAWMDM5Mjk1MnwyODgNTEwOS01</signed_data>" +
                "</msg_body>" +
                "</msg>";
    }


    public static void main(String[] args) {
        new SocketService(8765).start();
    }
}
