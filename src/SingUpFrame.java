import javax.swing.*;
import java.awt.*;

public class SingUpFrame extends JFrame {
    JPanel singUpPanel = new JPanel();
    JLabel mainLabel = new JLabel("회원가입 ");
    JLabel nameLabel = new JLabel("이름(필수)");
    JLabel emailLabel = new JLabel("Email(필수)");
    JLabel passwordLabel = new JLabel("비밀번호 6자리 이상입력");
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();
    JTextField passwordText = new JTextField();
    JButton singUpbut = new JButton("회원가입");
    JButton duplicatedbut =new JButton("중복 확인");
    SingUpFrame(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth * 2 / 9, screenHeight * 3 / 5);
        setLocation(screenWidth * 2 / 5, screenHeight / 6);
        this.setTitle("회원가입");

        this.add(singUpPanel);
        singUpPanel.setVisible(true);
        singUpPanel.setLayout(null);

        mainLabel.setSize(200, 40);
        mainLabel.setLocation(30,50);
        singUpPanel.add(mainLabel);

        nameLabel.setSize(100,20);
        nameLabel.setLocation(40,100);
        singUpPanel.add(nameLabel);

        nameText.setSize(200,30);
        nameText.setLocation(100,100);
        singUpPanel.add(nameText);

        passwordLabel.setSize(200,20);
        passwordLabel.setLocation(70,180);
        singUpPanel.add(passwordLabel);

        passwordText.setSize(100,20);
        passwordText.setLocation(100,200);
        singUpPanel.add(passwordText);

        emailLabel.setSize(100,20);
        emailLabel.setLocation(70,220);
        singUpPanel.add(emailLabel);

        emailText.setSize(100,20);
        emailText.setLocation(100,240);
        singUpPanel.add(emailText);

        singUpbut.setForeground(Color.WHITE);
        singUpbut.setBackground(Color.BLACK);
        singUpbut.setSize(100,20);
        singUpbut.setLocation(100,260);
        singUpPanel.add(singUpbut);

        duplicatedbut.setForeground(Color.WHITE);
        duplicatedbut.setBackground(Color.BLACK);
        duplicatedbut.setSize(70,17);
        duplicatedbut.setLocation(205,100);
        singUpPanel.add(duplicatedbut);

    }
}
