import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInFrame extends JFrame {
    private JPanel loginPanel = new JPanel();

    private JLabel idLabel = new JLabel("아이디 ");

    private JLabel pwLabel = new JLabel("비밀번호 ");

    private JTextField idText = new JTextField();

    private JPasswordField pwText = new JPasswordField();

    private JButton loginBtn = new JButton("로그인");
    private JButton idpwSearchBtn = new JButton("아이디/비밀번호 찾기");
    private JButton singUp = new JButton("회원가입");
    private UserDAO _DAO;
    LogInFrame(UserDAO DAO) {
        _DAO = DAO;

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth * 2 / 9, screenHeight * 3 / 5);
        setLocation(screenWidth * 2 / 5, screenHeight / 6);
        this.setTitle("로그인");

        this.add(loginPanel);
        loginPanel.setVisible(true);
        loginPanel.setLayout(null);

        idLabel.setSize(80,30);
        idLabel.setLocation(55,170);
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        loginPanel.add(idLabel); //JFrame에 JLabel를 추가

        idText.setSize(130,30);
        idText.setLocation(120,170);
        loginPanel.add(idText);//JFrame에 JTextField를 추가

        pwLabel.setSize(80,30);
        pwLabel.setLocation(65,200);
        loginPanel.add(pwLabel);

        pwText.setSize(130,30);
        pwText.setLocation(120,200);
        loginPanel.add(pwText);


        idpwSearchBtn.setForeground(Color.WHITE);
        idpwSearchBtn.setBackground(Color.BLACK);
        idpwSearchBtn.setSize(160,30);
        idpwSearchBtn.setLocation(100,400);
        //loginPanel.add(idpwSearchBtn);

        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setSize(200,40);
        loginBtn.setLocation(60,250);
        loginPanel.add(loginBtn);

        singUp.setForeground(Color.WHITE);
        singUp.setBackground(Color.BLACK);
        singUp.setSize(200,40);
        singUp.setLocation(60,400);
        loginPanel.add(singUp);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("로그인 시도");
                String id = idText.getText().trim();
                String pw = pwText.getText().trim();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "로그인 확인!",
                            JOptionPane.PLAIN_MESSAGE);

                } else if (pw.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "비밀 번호를 입력해주세요", "로그인 확인!",
                            JOptionPane.PLAIN_MESSAGE);

                } else if(id != null) {
                    if(pw != null){
                        if(_DAO.logincheck(id,pw)){
                            setVisible(false);
                            JFrame mainFrame = new MainFrame();
                            mainFrame.setVisible(true);
                        }else {
                            JOptionPane.showMessageDialog(null, "등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력했습니다.",
                                    "로그인 확인!", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }  else {
                    JOptionPane.showMessageDialog(null, "등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력했습니다.",
                            "로그인 확인!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        idpwSearchBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "아이디 비번", "아이디/비밀번호 찾기",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        singUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame singUpFrame = new SingUpFrame();
                singUpFrame.setVisible(true);
            }
        });
    }
}
