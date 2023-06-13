import javax.swing.*;
import java.awt.*;

public class SingUpFrame extends JFrame {
    JPanel singUpPanel = new JPanel();
    JLabel nameLabel = new JLabel("이름(필수)");
    JLabel emailLabel = new JLabel("Email(필수)");
    JLabel passwordLabel = new JLabel("비밀번호 6자리 이상입력");
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();
    JTextField passwordText = new JTextField();
    JButton singUpbut = new JButton("회원가입");
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

        nameLabel.setSize(100,20);
        nameLabel.setLocation(70,100);
        singUpPanel.add(nameLabel);

        nameText.setSize(100,20);
        nameText.setLocation(100,100);
        singUpPanel.add(nameText);

        emailLabel.setSize(100,20);
        emailLabel.setLocation(70,150);
        singUpPanel.add(emailLabel);
        emailText.setSize(100,20);
        emailText.setLocation(100,150);
        singUpPanel.add(emailText);
        singUpPanel.add(passwordLabel);
        singUpPanel.add(passwordText);
        singUpPanel.add(singUpbut);

    }
}
