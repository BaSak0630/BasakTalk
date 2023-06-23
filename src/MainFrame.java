import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel mainPanel = new JPanel();
    boolean logined;
    public MainFrame(){
        logined = false;
        this.setVisible(false);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3, screenHeight*2/3);
        setLocation(screenWidth/6, screenHeight/6);
        this.add(mainPanel);
        mainPanel.setLayout(null);

        JButton backButton = new JButton("로그아웃");

        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setSize(100,30);
        backButton.setLocation(0,0);
        mainPanel.add(backButton);

        backButton.addActionListener(e -> {
            LogInFrame logInFrame = new LogInFrame();
            logInFrame.setVisible(true);
            this.dispose();
        });
    }
}
