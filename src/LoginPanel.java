import javax.swing.*;
import java.awt.*;

public class LoginPanel extends SubPanel {
    private JLabel idLabel = new JLabel("아이디 ");
    private JLabel pwLabel = new JLabel("비밀번호 ");
    private JTextField idText = new JTextField();
    private JPasswordField pwText = new JPasswordField();
    private JButton loginBtn = new JButton("로그인");
    private JButton idpwSearchBtn = new JButton("아이디/비밀번호 찾기");
    private JButton singUp = new JButton("회원가입");
    private UserDAO _DAO;
    LoginPanel(LoginDialog loginDialog){
        _DAO = new UserDAO();
        this.setVisible(true);
        this.setLayout(null);

        idLabel.setSize(80,30);
        idLabel.setLocation(55,170);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(idLabel); //JFrame에 JLabel를 추가

        idText.setSize(130,30);
        idText.setLocation(120,170);
        this.add(idText);//JFrame에 JTextField를 추가

        pwLabel.setSize(80,30);
        pwLabel.setLocation(65,200);
        this.add(pwLabel);

        pwText.setSize(130,30);
        pwText.setLocation(120,200);
        this.add(pwText);


        idpwSearchBtn.setForeground(Color.WHITE);
        idpwSearchBtn.setBackground(Color.BLACK);
        idpwSearchBtn.setSize(200,40);
        idpwSearchBtn.setLocation(60,350);
        this.add(idpwSearchBtn);

        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setSize(200,40);
        loginBtn.setLocation(60,250);
        this.add(loginBtn);

        singUp.setForeground(Color.WHITE);
        singUp.setBackground(Color.BLACK);
        singUp.setSize(200,40);
        singUp.setLocation(60,400);
        this.add(singUp);

        loginBtn.addActionListener(event ->{
            System.out.println("로그인 시도");
            String id = idText.getText().trim();
            String pw = pwText.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "로그인 확인!",
                        JOptionPane.PLAIN_MESSAGE);

            } else if (pw.isEmpty()) {
                JOptionPane.showMessageDialog(null, "비밀 번호를 입력해주세요", "로그인 확인!",
                        JOptionPane.PLAIN_MESSAGE);

            } else if(id != null && pw != null) {
                if(_DAO.logincheck(id,pw)){
                    loginDialog.dispose();
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                }else {
                    System.out.println("로그인 실패");
                    JOptionPane.showMessageDialog(null, "등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력했습니다.",
                            "로그인 확인!", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력했습니다.",
                        "로그인 확인!", JOptionPane.PLAIN_MESSAGE);
            }
        });


        idpwSearchBtn.addActionListener(event ->{
            loginDialog.changePanel("idpwFindPanel");
        });

        singUp.addActionListener(event->{
            //회원가입 판넬로 바꿈
            loginDialog.changePanel("signUpPanel");
        });
    }
}
