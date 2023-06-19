import javax.swing.*;
import java.awt.*;

public class SignUpFrame extends JFrame {
    boolean uniqueID = false;
    boolean pwLenght = false;
    boolean pwDuplicated = false;
    JPanel signUpPanel = new JPanel();
    JLabel mainLabel = new JLabel("회원가입 ");
    JButton duplicatedbut = new JButton("중복 확인");
    JLabel idLabel = new JLabel("아이디 (필수)");
    JTextField idText = new JTextField();
    JLabel passwordLabel = new JLabel("비밀번호 6자리 이상입력");
    JPasswordField passwordText = new JPasswordField();
    JLabel passwordCheckLabel = new JLabel("비밀번호 확인");
    JPasswordField passwordCheckText = new JPasswordField();
    JLabel nameLabel = new JLabel("이름");
    JTextField nameText = new JTextField();
    JLabel emailLabel = new JLabel("Email(필수)");
    JTextField emailText = new JTextField();
    JButton signUpbut = new JButton("회원가입");
    JButton backbut = new JButton("뒤로 가기");
    private UserDAO _DAO;
    SignUpFrame() {
        _DAO = new UserDAO();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth * 2 / 9, screenHeight * 3 / 5);
        setLocation(screenWidth * 2 / 5, screenHeight / 6);
        this.setTitle("회원가입");

        this.add(signUpPanel);
        signUpPanel.setVisible(true);
        signUpPanel.setLayout(null);

        mainLabel.setSize(200, 40);
        mainLabel.setLocation(10,15);
        signUpPanel.add(mainLabel);

        //아이디
        idLabel.setSize(100,20);
        idLabel.setLocation(20,55);
        signUpPanel.add(idLabel);

        idText.setSize(210,30);
        idText.setLocation(15,80);
        signUpPanel.add(idText);

        duplicatedbut.setForeground(Color.WHITE);
        duplicatedbut.setBackground(Color.BLACK);
        duplicatedbut.setSize(85,28);
        duplicatedbut.setLocation(230,80);
        signUpPanel.add(duplicatedbut);

        //비밀번호
        passwordLabel.setSize(210,20);
        passwordLabel.setLocation(20,115);
        signUpPanel.add(passwordLabel);

        passwordText.setSize(300,30);
        passwordText.setLocation(15,140);
        signUpPanel.add(passwordText);

        passwordCheckLabel.setSize(210,20);
        passwordCheckLabel.setLocation(20,175);
        signUpPanel.add(passwordCheckLabel);

        passwordCheckText.setSize(300,30);
        passwordCheckText.setLocation(15,200);
        signUpPanel.add(passwordCheckText);

        //이름
        nameLabel.setSize(210,20);
        nameLabel.setLocation(20,230);
        signUpPanel.add(nameLabel);

        nameText.setSize(300,30);
        nameText.setLocation(15,255);
        signUpPanel.add(nameText);

        //이메일
        emailLabel.setSize(300,30);
        emailLabel.setLocation(20,280);
        signUpPanel.add(emailLabel);

        emailText.setSize(300,30);
        emailText.setLocation(15,310);
        signUpPanel.add(emailText);


        signUpbut.setForeground(Color.WHITE);
        signUpbut.setBackground(Color.BLACK);
        signUpbut.setSize(100,30);
        signUpbut.setLocation(115,380);
        signUpPanel.add(signUpbut);

        backbut.setForeground(Color.WHITE);
        backbut.setBackground(Color.BLACK);
        backbut.setSize(100,30);
        backbut.setLocation(115,420);
        signUpPanel.add(backbut);

        signUpbut.addActionListener(event ->{
            String id = idText.getText().trim();
            String pw = passwordText.getText().trim();
            String pwD = passwordCheckText.getText().trim();
            String name = nameText.getText().trim();
            String Email = emailText.getText().trim();

            if(id.isEmpty() ){
               JOptionPane.showMessageDialog(null, "아이디를 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if (pw.isEmpty()){
               JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if(pwD.isEmpty()){
               JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if(name.isEmpty()){
               JOptionPane.showMessageDialog(null, "이름을 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if(Email.isEmpty()){
               JOptionPane.showMessageDialog(null, "Email를 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else{
                if (!uniqueID) {
                    JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요",
                            "아이디 중복", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (pw.length() < 5) {
                        JOptionPane.showMessageDialog(null, "비밀번호는 6글자 이상입니다",
                                "비밀번호", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        pwLenght = true;
                        if(pw.equals(pwD)){
                            pwDuplicated = true;
                        }else {
                            JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인 해주세요",
                                    "비밀번호", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
           }
           if(uniqueID && pwLenght && pwDuplicated){
               //모든 textfield가 체워지고 중복체크가 완료된 경우
               _DAO.signup(id,pw,Email,name);
               System.out.println("유저추가");
               LogInFrame logInFrame = new LogInFrame();
               logInFrame.setVisible(true);
               this.dispose();
           }
        });

        duplicatedbut.addActionListener(e -> {
            String id = idText.getText().trim();
            if(!id.isEmpty()){
                if(_DAO.idDUniqueCheck(id)) {
                    JOptionPane.showMessageDialog(null, "중복된 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    //idText.setEditable(true);
                    uniqueID = false;
                }else if(!_DAO.idDUniqueCheck(id)){
                    JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    //idText.setEditable(false);
                    uniqueID = true;
                }
                if(uniqueID) idText.setEditable(false);
            }else {
                JOptionPane.showMessageDialog(null, "아이디를 입력해주세요",
                        "아이디", JOptionPane.PLAIN_MESSAGE);
            }
        });

        backbut.addActionListener(event ->{
                dispose();
                LogInFrame newLogInFrame = new LogInFrame();
                newLogInFrame.setVisible(true);
        });
    }
}
