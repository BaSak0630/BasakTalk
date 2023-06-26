import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    JButton backButton = new JButton("로그아웃");

    MainPanel(){
        this.setVisible(true);
        this.setLayout(null);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setSize(100,30);
        backButton.setLocation(0,0);
        this.add(backButton);

        //뒤로가기 버튼
        backButton.addActionListener(e -> {
        });
    }





}
