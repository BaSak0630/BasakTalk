package gui.net;
import net.Server;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerPanel extends MainPanel {
    public ServerPanel(User user) {
        super(user);
        buttonPanel = new ButtonPanel(this);
        chatPanel = new ChatPanel();
        chatPane = new JScrollPane(chatPanel);
        inputPanel = new InputPanel();
        Server server = new Server(this,user);
        buttonPanel.startBut.addActionListener(e -> {
            server.start();
            inputPanel.textInput.setEditable(true);
        });
        inputPanel.addKeyListener(server);
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(chatPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
}
