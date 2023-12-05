package gui.net;

import net.Client;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientPanel extends MainPanel{
    Client client;
    public ClientPanel(User user) {
        super(user);
        buttonPanel = new ButtonPanel(this);
        chatPanel = new ChatPanel();
        chatPane = new JScrollPane(chatPanel);
        inputPanel = new InputPanel();
        inputPanel.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    String msgToSend = inputPanel.textInput.getText();
                    System.out.println(inputPanel.textInput.getX());
                    inputPanel.textInput.setText(null);
                    try {
                        String serverIP = user.get_ip();
                        Socket server = new Socket(serverIP, 7000);
                        PrintWriter toServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
                        LocalDateTime now = LocalDateTime.now();
                        String nowDateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                        Message message = new Message(nowDateTime, user.get_userID(), "", msgToSend);
                        toServer.println(message.msg);
                        toServer.flush();
                        writeMessage(message);
                        server.close();
                        toServer.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(chatPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
    Client createClient(String text) {
        Client tmp = new Client(this, user, text);
        return tmp;
    }
}
