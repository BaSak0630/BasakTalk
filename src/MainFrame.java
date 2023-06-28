import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    MainPanel mainPanel;
    public MainFrame(){
        mainPanel = new MainPanel(this);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3, screenHeight*2/3);
        setLocation(screenWidth/6, screenHeight/6);
        this.add(mainPanel);
    }
}
