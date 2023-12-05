package gui.net;

import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    MainPanel mainPanel;
    User user;
    int frameW;
    int frameH;

    public MainFrame(User user){
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.user = user;
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        frameW = d.width/2;
        frameH = d.height/2;
        setSize(frameW,frameH);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        if(user.is_server()){
            mainPanel = new ServerPanel(user);
            contentPane.add(mainPanel);
        }else {
            mainPanel = new ClientPanel(user);
            contentPane.add(mainPanel);
        }

    }
}
