import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingUpFrame extends JFrame {

    boolean uniqueID = false;
    JPanel singUpPanel = new JPanel();
    JLabel mainLabel = new JLabel("회원가입 ");
    JButton duplicatedbut = new JButton("중복 확인");
    JLabel idLabel = new JLabel("아이디 (필수)");
    JTextField idText = new JTextField();
    JLabel passwordLabel = new JLabel("비밀번호 6자리 이상입력");
    JTextField passwordText = new JTextField();
    JLabel passwordCheckLabel = new JLabel("비밀번호 재입력");
    JTextField passwordCheckText = new JTextField();
    JLabel nameLabel = new JLabel("이름");
    JTextField nameText = new JTextField();
    JLabel emailLabel = new JLabel("Email(필수)");
    JTextField emailText = new JTextField();

    JButton singUpbut = new JButton("회원가입");
    JButton backbut = new JButton("뒤로 가기");
    private UserDAO _DAO;

    //중복아이디 검증시 여러번 중복체크에 오류가 있어서 기존의 다른 텍스트 정보를 가지고 새로운 프레임을 만들어 줌
    SingUpFrame(String _pw,String _pwD,String _name, String _Email) {
        _DAO = new UserDAO();

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
        mainLabel.setLocation(10,15);
        singUpPanel.add(mainLabel);

        //아이디
        idLabel.setSize(100,20);
        idLabel.setLocation(20,55);
        singUpPanel.add(idLabel);

        idText.setSize(210,30);
        idText.setLocation(15,80);
        singUpPanel.add(idText);

        duplicatedbut.setForeground(Color.WHITE);
        duplicatedbut.setBackground(Color.BLACK);
        duplicatedbut.setSize(85,28);
        duplicatedbut.setLocation(230,80);
        singUpPanel.add(duplicatedbut);

        //비밀번호
        passwordLabel.setSize(210,20);
        passwordLabel.setLocation(20,115);
        singUpPanel.add(passwordLabel);

        passwordText.setSize(300,30);
        passwordText.setLocation(15,140);
        singUpPanel.add(passwordText);

        passwordCheckLabel.setSize(210,20);
        passwordCheckLabel.setLocation(20,175);
        singUpPanel.add(passwordCheckLabel);

        passwordCheckText.setSize(300,30);
        passwordCheckText.setLocation(15,200);
        singUpPanel.add(passwordCheckText);

        //이름
        nameLabel.setSize(210,20);
        nameLabel.setLocation(20,230);
        singUpPanel.add(nameLabel);

        nameText.setSize(300,30);
        nameText.setLocation(15,255);
        singUpPanel.add(nameText);

        //이메일
        emailLabel.setSize(300,30);
        emailLabel.setLocation(20,280);
        singUpPanel.add(emailLabel);

        emailText.setSize(300,30);
        emailText.setLocation(15,310);
        singUpPanel.add(emailText);


        singUpbut.setForeground(Color.WHITE);
        singUpbut.setBackground(Color.BLACK);
        singUpbut.setSize(100,30);
        singUpbut.setLocation(115,380);
        singUpPanel.add(singUpbut);

        backbut.setForeground(Color.WHITE);
        backbut.setBackground(Color.BLACK);
        backbut.setSize(100,30);
        backbut.setLocation(115,420);
        singUpPanel.add(backbut);

        passwordText.setText(_pw);
        passwordCheckText.setText(_pwD);
        nameText.setText(_name);
        emailText.setText(_Email);

        singUpbut.addActionListener(event ->{
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
                JOptionPane.showMessageDialog(null, "비밀번호 재입력 해주세요",
                        "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
            }else if(name.isEmpty()){
                JOptionPane.showMessageDialog(null, "이름을 입력해주세요",
                        "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
            }else if(Email.isEmpty()){
                JOptionPane.showMessageDialog(null, "Email를 입력해주세요",
                        "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
            }else {
                if(!uniqueID){
                    JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요",
                            "아이디 중복", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        duplicatedbut.addActionListener(e -> {
            String id = idText.getText().trim();
            String pw = passwordText.getText().trim();
            String pwD = passwordCheckText.getText().trim();
            String name = nameText.getText().trim();
            String Email = emailText.getText().trim();
            if(!id.isEmpty()){
                if(_DAO.idDUniqueCheck(id)) {
                    JOptionPane.showMessageDialog(null, "중복된 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    SingUpFrame newSinUpFrame = new SingUpFrame(pw,pwD,name,Email);
                    newSinUpFrame.setVisible(true);
                    this.dispose();
                    /*idText.setEditable(true);
                    idText.setText("");
                    uniqueID = false;*/
                }else if(!_DAO.idDUniqueCheck(id)){
                    JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    idText.setEditable(false);
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
    /*SingUpFrame() {

        _DAO = new UserDAO();

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
        mainLabel.setLocation(10,15);
        singUpPanel.add(mainLabel);

        //아이디
        idLabel.setSize(100,20);
        idLabel.setLocation(20,55);
        singUpPanel.add(idLabel);

        idText.setSize(210,30);
        idText.setLocation(15,80);
        singUpPanel.add(idText);

        duplicatedbut.setForeground(Color.WHITE);
        duplicatedbut.setBackground(Color.BLACK);
        duplicatedbut.setSize(85,28);
        duplicatedbut.setLocation(230,80);
        singUpPanel.add(duplicatedbut);

        //비밀번호
        passwordLabel.setSize(210,20);
        passwordLabel.setLocation(20,115);
        singUpPanel.add(passwordLabel);

        passwordText.setSize(300,30);
        passwordText.setLocation(15,140);
        singUpPanel.add(passwordText);

        passwordCheckLabel.setSize(210,20);
        passwordCheckLabel.setLocation(20,175);
        singUpPanel.add(passwordCheckLabel);

        passwordCheckText.setSize(300,30);
        passwordCheckText.setLocation(15,200);
        singUpPanel.add(passwordCheckText);

        //이름
        nameLabel.setSize(210,20);
        nameLabel.setLocation(20,230);
        singUpPanel.add(nameLabel);

        nameText.setSize(300,30);
        nameText.setLocation(15,255);
        singUpPanel.add(nameText);

        //이메일
        emailLabel.setSize(300,30);
        emailLabel.setLocation(20,280);
        singUpPanel.add(emailLabel);

        emailText.setSize(300,30);
        emailText.setLocation(15,310);
        singUpPanel.add(emailText);


        singUpbut.setForeground(Color.WHITE);
        singUpbut.setBackground(Color.BLACK);
        singUpbut.setSize(100,30);
        singUpbut.setLocation(115,380);
        singUpPanel.add(singUpbut);

        backbut.setForeground(Color.WHITE);
        backbut.setBackground(Color.BLACK);
        backbut.setSize(100,30);
        backbut.setLocation(115,420);
        singUpPanel.add(backbut);

        singUpbut.addActionListener(event ->{
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
               JOptionPane.showMessageDialog(null, "비밀번호 재입력 해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if(name.isEmpty()){
               JOptionPane.showMessageDialog(null, "이름을 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else if(Email.isEmpty()){
               JOptionPane.showMessageDialog(null, "Email를 입력해주세요",
                       "모두 작성해주세요", JOptionPane.PLAIN_MESSAGE);
           }else {
               if(!uniqueID){
                   JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요",
                           "아이디 중복", JOptionPane.PLAIN_MESSAGE);
               }
           }
        });
        duplicatedbut.addActionListener(e -> {
            String id = idText.getText().trim();
            String pw = passwordText.getText().trim();
            String pwD = passwordCheckText.getText().trim();
            String name = nameText.getText().trim();
            String Email = emailText.getText().trim();
            if(!id.isEmpty()){
                if(_DAO.idDUniqueCheck(id)) {
                    JOptionPane.showMessageDialog(null, "중복된 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    SingUpFrame newSinUpFrame = new SingUpFrame(pw,pwD,name,Email);
                    newSinUpFrame.setVisible(true);
                    this.dispose();
                    *//*idText.setEditable(true);
                    idText.setText("");
                    uniqueID = false;*//*
                }else if(!_DAO.idDUniqueCheck(id)){
                    JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다",
                            "아이디", JOptionPane.PLAIN_MESSAGE);
                    idText.setEditable(false);
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
    }*/
}
