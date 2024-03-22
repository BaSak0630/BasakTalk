package gui.login;

import user.UserDAO;

import javax.swing.*;

public class IDPWFindPanel extends SubPanel {
    private JLabel emailLabel = new JLabel("이메일을 입력하세요");
    private JTextField emailTextField = new JTextField();
    private JButton findBut = new JButton("아이디 비밀번호 찾기");
    private JButton backBut = new JButton("뒤로가기");
    private UserDAO _DAO;
    IDPWFindPanel(LoginDialog loginDialog){
        _DAO = new UserDAO();
        this.setLayout(null);

        emailLabel.setSize(200,20);
        emailLabel.setLocation(100,150);
        this.add(emailLabel);

        emailTextField.setSize(200,30);
        emailTextField.setLocation(60,180);
        this.add(emailTextField);

        findBut.setSize(200,30);
        findBut.setLocation(60,220);
        this.add(findBut);

        backBut.setSize(200,30);
        backBut.setLocation(60,300);
        this.add(backBut);

        findBut.addActionListener(e -> {
            String email = emailTextField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "이메일을 입력해주세요", "아이디 비밀번혼 찾기",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                if(_DAO.findingCheck(email)){
                    String idBuffer = _DAO.returnID(email);
                    String pwBuffer = _DAO.returnPW(email);
                    JOptionPane.showMessageDialog(null,"ID: "+idBuffer +"PW: "+pwBuffer +"\n", "아이디 비밀번혼 찾기",
                            JOptionPane.PLAIN_MESSAGE);
                    loginDialog.changePanel("loginPanel");
                }else {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다.", "아이디 비밀번혼 찾기",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        backBut.addActionListener(e -> {
            loginDialog.changePanel("loginPanel");
        });
    }
}
