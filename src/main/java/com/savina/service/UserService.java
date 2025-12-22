package com.savina.service;
import com.savina.model.User;
import java.sql.*;


public class UserService {

    private static String DbUsername = "soft";
    private static String DbPassword = "1";
    private static String DbUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    public void register(String firstName, String lastName,
                         String email, String phoneNumber, String password) {

        Connection con = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null; // mban rezultatin e një SELECT

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            con.setAutoCommit(false);

            // 1️⃣ Kontrollo a ekziston user-i
            String checkQuery =
                    "SELECT COUNT(*) FROM USERI WHERE EMAIL = ?";
            checkStmt = con.prepareStatement(checkQuery); //Përgatit këtë SELECT query që ka ? brenda
            checkStmt.setString(1, email); //Vendos email-in te placeholder-i i parë (?),logjikisht lart po java e ka ndryshe
            rs = checkStmt.executeQuery(); // ekzekutohet

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Useri me kete email egziston ");
                return;
            }

            // 2️⃣ Insert user i ri
            String insert =
                    "INSERT INTO USERI (USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, PASSWORD) VALUES (USER_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

            insertStmt = con.prepareStatement(insert);
            insertStmt.setString(1, firstName);
            insertStmt.setString(2, lastName);
            insertStmt.setString(3, email);
            insertStmt.setString(4, phoneNumber);
            insertStmt.setString(5, password);

            int rows = insertStmt.executeUpdate();
            con.commit();

            System.out.println("User u regjistrua. Rreshta: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean logIn(String email, String password) {
        // kontroll kredencialesh
        return true;
    }
}
