package user;

/*import db.dbConnecter;*/

import db.dbConnecter;

import javax.swing.*;
import java.net.InetAddress;
import java.sql.*;

public class UserDAO {
    dbConnecter dbc = new dbConnecter();
    PreparedStatement stmt = null;
    ResultSet result = null;
    public boolean logincheck(String _i, String _p) {
        boolean flag = false;

        String id = _i;
        String pw = _p;
        String dbpw;

        try {
            System.out.println("로그인 시도 중");
            String query = "SELECT USERPW FROM BASAKTALKUSER WHERE USERID='" +id+ "'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");

            while(result.next()) {
                System.out.println("result.next 테스트 ");
                dbpw = result.getString("USERPW");
                if(pw.equals(dbpw)){
                    flag = true;
                    System.out.println("로그인 성공");
                }
            }
        } catch(Exception e) {
            flag = false;
            System.out.println("로그인 실패 >>> " + e.toString());
        }finally {
            dbc.dbClose();
        }
        return flag;
    }
    public boolean findingCheck(String _e){
        boolean findFlag = false;
        String inputEmail = _e;
        String dbEmail;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL FROM BASAKTALKUSER WHERE USEREMAIL ='"+inputEmail+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
            while(result.next()) {
                dbEmail = result.getString("USEREMAIL");
                if(dbEmail.equals(inputEmail)){
                    findFlag = true;
                    System.out.println("계정 존재");
                    return findFlag;
                }
            }
        } catch(Exception e) {
            System.out.println("계정 여부 확인 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
        return findFlag;
    }
    public boolean idDUniqueCheck(String _i) {
        boolean idFlag = false;
        String id = _i;
        String dbid;

        try {
            System.out.println("아이디 중복 확인 중");
            String query = "SELECT USERID FROM BASAKTALKUSER WHERE USERID ='"+id+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
            while(result.next()) {
                dbid = result.getString("USERID");
                if(dbid.equals(id)){
                    idFlag = true;
                    System.out.println("중복");
                    return idFlag;
                }
            }
        } catch(Exception e) {
            System.out.println("중복확인 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
        return idFlag;
    }
    public void signup(String ID, String PW, String email, String name){
        String userID = ID;
        String userPW = PW;
        String userEmail = email;
        String userName = name;

        try {
            System.out.println("회원가입 여부 확인 중");
            String query = "INSERT INTO BASAKTALKUSER(USERNUM,USERID,USERPW,USEREMAIL,USERNAME) values ((select nextval('test_sequence') from dual),?,?,?,?)";
            //INSERT INTO `BasakTalk`.`BASAKTALKUSER` (`USERNUM`, `userid`, `userpw`, `userEmail`, `username`) VALUES ('1', 'userid1', 'userpw1', 'userid1@gmail.com', '김동혁');
            stmt = dbc.dbConnecting(query,userID,userPW,userEmail,userName);
            stmt.execute();
            //String query ="INSERT INTO USERINFO values (create_sequence,'"+userID+"','"+userPW+"','"+userEmail+"','"+userName+"')";
        } catch(Exception e) {
            System.out.println("유저 삽입 실패 >>> " + e.toString());
            JOptionPane.showMessageDialog(null, "회원가입의 실패하였습니다.\n 다시 한번 확인해주세요",
                    "회원가입", JOptionPane.PLAIN_MESSAGE);
        } finally {
            dbc.dbClose();
        }
    }
    public String returnID(String _e){
        String returnID = "";
        String inputEmail = _e;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL,USERID,USERPW FROM BASAKTALKUSER WHERE USEREMAIL ='"+inputEmail+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
            while(result.next()) {
                returnID = result.getString("USERID");
            }
        } catch(Exception e) {
            System.out.println("id 리턴 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
        return returnID;
    }
    public String returnPW(String _e){
        String returnPW = "";
        String inputEmail = _e;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL,USERID,USERPW FROM BASAKTALKUSER WHERE USEREMAIL ='"+inputEmail+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
            while(result.next()) {
                returnPW = result.getString("USERID");
            }
        }catch (Exception e){
            System.out.println("pw 리턴 실패 >>> " + e.toString());
        }finally {
            dbc.dbClose();
        }
        return returnPW;
    }
    public User findUser(String id){
        User user = new User();
        try {
            System.out.println("로그인 성공 후 User정보 가져오기");
            String query = "SELECT * FROM BASAKTALKUSER WHERE USERID ='"+id+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
            while(result.next()) {
                user.set_userNum(result.getInt("NO"));
                user.set_userID(result.getString("USERID"));
                user.set_userPW(result.getString("USERPW"));
                user.set_userEmail(result.getString("USEREMAIL"));
                user.set_ip(result.getString("IP"));
                if(result.getInt("SERVER")==0){
                    user.set_server(true);
                }else {
                    user.set_server(false);
                }

            }
        } catch(Exception e) {
            System.out.println("중복확인 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
        return user;
    }

    public void ipSet(String id) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String myIP = address.getHostAddress();
            String query = "UPDATE BASAKTALKUSER SET IP ='"+myIP+"'WHERE USERID ='"+id+"'";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);
            System.out.println("DB 접속중");
        } catch(Exception e) {
            System.out.println("중복확인 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
    }
}

