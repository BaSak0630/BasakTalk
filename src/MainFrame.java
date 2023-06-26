import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    MainPanel mainPanel = new MainPanel();
    LoginDialog loginDialog = new LoginDialog();
    boolean logined = false;
    public MainFrame(){
        if(logined){
            this.setVisible(true);
        } else {
            loginDialog.setVisible(true);
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3, screenHeight*2/3);
        setLocation(screenWidth/6, screenHeight/6);
        this.add(mainPanel);
    }
}
