import javax.swing.*;

public class IndexPanel extends MainPanel{
    IndexPanel(){
        JLabel main = new JLabel("Success");
        this.setLayout(null);
        main.setLocation(screenWidth/2,screenWidth/2);
        this.add(main);
    }
}
