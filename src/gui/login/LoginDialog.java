package gui.login;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private IDPWFindPanel idpwFindPanel;
    public LoginDialog() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth * 2 / 9, screenHeight * 3 / 5);
        setLocation(screenWidth * 2 / 5, screenHeight / 6);
        this.setTitle("로그인");

        loginPanel = new LoginPanel(this);
        signUpPanel = new SignUpPanel(this);
        idpwFindPanel = new IDPWFindPanel(this);

        this.add(loginPanel);
        this.setVisible(true);

        //닫기
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    public void changePanel(String pannelName){
        if(pannelName.equals("loginPanel")){
            getContentPane().removeAll();
            getContentPane().add(loginPanel);
            revalidate();
            repaint();
        }else if(pannelName.equals("signUpPanel")){
            getContentPane().removeAll();
            getContentPane().add(signUpPanel);
            revalidate();
            repaint();
        }else if(pannelName.equals("idpwFindPanel")){
            getContentPane().removeAll();
            getContentPane().add(idpwFindPanel);
            revalidate();
            repaint();
        }
    }
}
