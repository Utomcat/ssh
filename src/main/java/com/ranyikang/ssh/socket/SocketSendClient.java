package com.ranyikang.ssh.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * CLASS_NAME: SocketSendClient.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: Socket客户端发送程序  <br/>
 * @date: 2022-12-28 <br/>
 */
@Slf4j
public class SocketSendClient extends Thread {

    private Socket client;

    public SocketSendClient(String ip, int port) {
        try {
            // 建立连线(ip 为同服务器端的 ip, port 为同服务器端开启的 port)
            client = new Socket(ip, port);
        } catch (IOException e) {
            log.error("Socket 连线有问题.");
            log.error("异常信息为: {}", e.toString());
        }
    }

    @Override
    public void run() {
        try {
            if (client != null) {
                PrintStream writer = new PrintStream(client.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer.println("Send From Client");
                writer.flush();
                StringBuilder getData = new StringBuilder();
                while (reader.readLine() != null) {
                    getData.append(reader.readLine());
                }
                log.info("客户端接收到的信息: {}", getData);
                writer.close();
                reader.close();
                client.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new SocketSendClient("127.0.0.1", 8765).start();
    }
}
