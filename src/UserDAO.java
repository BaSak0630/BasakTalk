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
    boolean idDUniqueCheck(String _i) {
        boolean idflag = false;
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
                    idflag = true;
                    System.out.println("중복");
                    return idflag;
                }
            }
        } catch(Exception e) {
            System.out.println("중복확인 실패 >>> " + e.toString());
        } finally {
            dbc.dbClose();
        }
        return idflag;
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
        } finally {
            dbc.dbClose();
        }
    }
}
