import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    IndexPanel indexPanel;
    public MainFrame(){
        indexPanel = new IndexPanel();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth*2/3, screenHeight*2/3);
        setLocation(screenWidth/6, screenHeight/6);
        this.add(indexPanel);
        //닫기
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
