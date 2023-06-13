import java.sql.*;

public class UserDAO {
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "admin";
    String pw = "admin";
    Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;
    public UserDAO(){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // connection으로 db와 연결 (객체 생성)
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn.isClosed()?"접속종료":"접속중");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    boolean logincheck(String _i, String _p) {
        boolean flag = false;

        String id = _i;
        String pw = _p;
        String dbpw;

        try {
            System.out.println("로그인 시도 중");
            //String checkingStr = "INSERT INTO USERINFO values (0000000003,'userid3','userpw3','userid@gmail.com','이시현')";
            String checkingStr = "SELECT USERPW FROM USERINFO WHERE USERID='" +id+ "'";
            result = stmt.executeQuery(checkingStr);
            System.out.println("DB 접속중");
            int count = 0;
            while(result.next()) {
                System.out.println("result.next 테스트 ");

                dbpw = result.getString("USERPW");
                if(pw.equals(dbpw)){
                    flag = true;
                    System.out.println("로그인 성공");
                }
                else {
                    flag = false;
                    System.out.println("로그인 실패");
                }
                count++;
                System.out.println(count);
            }
        } catch(Exception e) {
            flag = false;
            System.out.println("로그인 실패 >>> " + e.toString());
        }finally {
            //dbClose();
        }
        return flag;
    }
    public void dbClose() {
        try {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }
}
