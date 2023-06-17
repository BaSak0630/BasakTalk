import javax.swing.*;
import java.sql.*;

public class UserDAO {
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "admin";
    String pw = "admin";
    Connection conn = null;
    Statement stmt = null;
    ResultSet logInResult = null;

    boolean logincheck(String _i, String _p) {
        boolean flag = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // connection으로 db와 연결 (객체 생성)
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn.isClosed()?"접속종료":"접속중");
            stmt = conn.createStatement();
            System.out.println("접속 완료");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        String id = _i;
        String pw = _p;
        String dbpw;

        try {
            System.out.println("로그인 시도 중");
            //String checkingStr = "INSERT INTO USERINFO values (0000000003,'userid3','userpw3','userid@gmail.com','이시현')";
            String checkingStr = "SELECT USERPW FROM USERINFO WHERE USERID='" +id+ "'";
            logInResult = stmt.executeQuery(checkingStr);
            System.out.println("DB 접속중");
            while(logInResult.next()) {
                System.out.println("result.next 테스트 ");
                dbpw = logInResult.getString("USERPW");
                if(pw.equals(dbpw)){
                    flag = true;
                    System.out.println("로그인 성공");

                }
            }
        } catch(Exception e) {
            flag = false;
            System.out.println("로그인 실패 >>> " + e.toString());
        }finally {
            dbClose();
        }
        return flag;
    }
    boolean idDUniqueCheck(String _i) {
        boolean idflag = false;
        String id = _i;
        String dbid;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // connection으로 db와 연결 (객체 생성)
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn.isClosed()?"접속종료":"접속중");
            stmt = conn.createStatement();
            System.out.println("접속 완료");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("아이디 중복 확인 중");
            String checkingStrUnique = "SELECT USERID FROM USERINFO WHERE USERID ='"+id+"'";
            logInResult = stmt.executeQuery(checkingStrUnique);
            System.out.println("DB 접속중");
            while(logInResult.next()) {
                dbid = logInResult.getString("USERID");
                if(dbid.equals(id)){
                    idflag = true;
                    System.out.println("중복");
                    return idflag;
                }
            }
        } catch(Exception e) {
            System.out.println("중복확인 실패 >>> " + e.toString());
        } finally {
            dbClose();
        }
        return idflag;
    }
    public void dbClose() {
        try {
            if (logInResult != null)
                logInResult.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }
}
