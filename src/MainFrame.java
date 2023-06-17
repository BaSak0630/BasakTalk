import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel mainPanel = new JPanel();
    public MainFrame(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3, screenHeight*2/3);
        setLocation(screenWidth/6, screenHeight/6);
        this.add(mainPanel);
        mainPanel.setLayout(null);

        JButton backButton = new JButton("<-");

        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setSize(40,30);
        backButton.setLocation(0,0);
        mainPanel.add(backButton);
    }
}
