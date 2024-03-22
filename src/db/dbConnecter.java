package db;

import java.sql.*;

public class dbConnecter {
    String url = "jdbc:mysql://localhost:3306/BasakTalk";
    String user = "root";
    String pw = "rootpass";
    Connection conn = null;
    PreparedStatement stmt = null;
    public dbConnecter(){

    }
    public PreparedStatement dbConnecting(String sql){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // connection으로 db와 연결 (객체 생성)
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn.isClosed()?"접속종료":"접속중");
            stmt = conn.prepareStatement(sql);
            System.out.println("접속 완료");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe);
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }
    public PreparedStatement dbConnecting(String sql,String userID,String userPW,String userEmail,String userName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // connection으로 db와 연결 (객체 생성)
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println(conn.isClosed()?"접속종료":"접속중");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,userID);
            stmt.setString(2,userPW);
            stmt.setString(3,userEmail);
            stmt.setString(4,userName);

            System.out.println("접속 완료");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe);
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle);
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