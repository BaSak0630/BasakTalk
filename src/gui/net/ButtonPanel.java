package gui.net;

import net.Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ButtonPanel extends JPanel {
    JTextField roomCode;
    JButton connetionBut;
    JButton startBut;
    JPanel mainPanel;
    public ButtonPanel(ClientPanel mainPanel) {
        this.mainPanel = mainPanel;
        roomCode = new JTextField(10);
        connetionBut = new JButton("connetion");
        connetionBut.addActionListener(e -> {
            try{
                Client receiveSocket = mainPanel.createClient(roomCode.getText());
                receiveSocket.start();

                String serverIP = "192.168.72.1";
                Socket serverSocket = new Socket(serverIP, 7000);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                PrintWriter toServer = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()));

                String msgFromServer = fromServer.readLine();
                mainPanel.writeMessage(msgFromServer);
                toServer.println(roomCode);

                toServer.close();
                serverSocket.close();
                mainPanel.inputPanel.textInput.setEditable(true);
            }catch (Exception ex){
                System.out.println(ex);
            }
        });
        add(roomCode);
        add(connetionBut);
    }

    public ButtonPanel(ServerPanel mainPanel) {
        startBut = new JButton("Start");
        add(startBut);
    }
}
