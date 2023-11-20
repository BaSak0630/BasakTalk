import javax.swing.*;
import java.sql.*;

public class UserDAO {
    dbConnecter dbc = new dbConnecter();
    PreparedStatement stmt = null;
    ResultSet result = null;
    boolean logincheck(String _i, String _p) {
        boolean flag = false;

        String id = _i;
        String pw = _p;
        String dbpw;

        try {
            System.out.println("로그인 시도 중");
            String query = "SELECT USERPW FROM USERINFO WHERE USERID='" +id+ "'";
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
    boolean findingCheck(String _e){
        boolean findFlag = false;
        String inputEmail = _e;
        String dbEmail;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL FROM USERINFO WHERE USEREMAIL ='"+inputEmail+"'";
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
    boolean idDUniqueCheck(String _i) {
        boolean idFlag = false;
        String id = _i;
        String dbid;

        try {
            System.out.println("아이디 중복 확인 중");
            String query = "SELECT USERID FROM USERINFO WHERE USERID ='"+id+"'";
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
    void signup(String ID,String PW,String email, String name){
        String userID = ID;
        String userPW = PW;
        String userEmail = email;
        String userName = name;

        try {
            System.out.println("아이디 중복 확인 중");
            String query = "INSERT INTO USERINFO (USERNO,USERID,USERPW,USEREMAIL,USERNAME) values (USER_SEQ.NEXTVAL,?,?,?,?)";
            stmt = dbc.dbConnecting(query,userID,userPW,userEmail,userName);
            result = stmt.executeQuery();
            /*String query ="INSERT INTO USERINFO values (USER_SEQ.NEXTVAL,'"+userID+"','"+userPW+"','"+userEmail+"','"+userName+"')";
            stmt = dbc.dbConnecting(query);
            result = stmt.executeQuery(query);*/
        } catch(Exception e) {
            System.out.println("유저 삽입 실패 >>> " + e.toString());
            JOptionPane.showMessageDialog(null, "회원가입의 실패하였습니다.\n 다시 한번 확인해주세요",
                    "회원가입", JOptionPane.PLAIN_MESSAGE);
        } finally {
            dbc.dbClose();
        }
    }
    String returnID(String _e){
        String returnID = "";
        String inputEmail = _e;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL,USERID,USERPW FROM USERINFO WHERE USEREMAIL ='"+inputEmail+"'";
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
    String returnPW(String _e){
        String returnPW = "";
        String inputEmail = _e;
        try {
            System.out.println("계정 존재 여부 확인 중");
            String query = "SELECT USEREMAIL,USERID,USERPW FROM USERINFO WHERE USEREMAIL ='"+inputEmail+"'";
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
}
