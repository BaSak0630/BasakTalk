package net;

import gui.net.MainPanel;
import gui.net.Message;
import gui.net.ServerPanel;
import user.User;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;
import java.util.stream.Stream;

import static gui.net.MainPanel.writeMessage;

public class Server extends Thread implements KeyListener {
    ServerPanel mainPanel;
    ServerSocket myPort;
    Socket clientSocket;
    User user;
    String clientIP;

    public Server(ServerPanel mainPanel, User user) {
        this.mainPanel = mainPanel;
        this.user = user;
    }

    public void run() {
        try {
            myPort = new ServerSocket(7000);
            clientIP = "";

            for (; ; ) {
                clientSocket = myPort.accept();
                clientSocket.setSoTimeout(5000);

                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println(fromClient.lines());
                //Message message = makeMessage(fromClient.lines());

                clientIP = clientSocket.getInetAddress().getHostAddress();
                System.out.println(clientIP);

                writeMessage("Connected with [" + clientIP + "]...");

                toClient.println("Welcome!");
                toClient.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   /* private Message makeMessage(Stream<String> lines) {
        Spliterator<String> tmps = lines.spliterator();


        //Message message = new Message(tmps[0], tmps[1], tmps[2], tmps[3]);
        return message;
    }
*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String str = mainPanel.inputPanel.getTextInput().getText();
        if (keyCode == KeyEvent.VK_ENTER) {
            try {
                clientIP = clientSocket.getInetAddress().getHostAddress();
                Socket sendToClient = new Socket(clientIP, 7001);
                PrintWriter toClient = new PrintWriter(new OutputStreamWriter(sendToClient.getOutputStream()));
                LocalDateTime now = LocalDateTime.now();
                String nowDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                Message message = new Message(nowDateTime, user.get_userID(), "", str);
                toClient.println(message);
                toClient.flush();
                writeMessage(message);

                mainPanel.inputPanel.getTextInput().setText(null);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    @Override
    public void keyReleased (KeyEvent e){
    }
}

