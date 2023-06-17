import java.sql.*;

public class dbConnecter {
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String user = "admin";
    String pw = "admin";
    Connection conn = null;
    Statement stmt = null;
    dbConnecter(){

    }
    public Statement dbConnecting(){
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
        return stmt;
    }
    public void dbClose() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }
}