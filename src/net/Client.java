package net;

import gui.net.ClientPanel;
import gui.net.Message;
import user.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Client extends Thread {
    ClientPanel mainPanel;
    User user;
    ServerSocket receiveSocket;
    String roomCode;
    public Client(ClientPanel mainPanel, User user, String roomCode){
        this.mainPanel = mainPanel;
        this.user = user;
        this.roomCode = roomCode;
    }
    public void run(){
        try {
            receiveSocket = new ServerSocket(7001);
            for (; ; ) {
                Socket server = receiveSocket.accept();
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
                String fromServerStr = fromServer.readLine();
                System.out.println(fromServerStr);

                LocalDateTime now = LocalDateTime.now();
                String nowDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

                Message message = new Message(nowDateTime,user.get_ip(),roomCode,fromServerStr);
                ClientPanel.writeMessage("[Server] : " + message.getMsg());

                server.close();
                fromServer.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
