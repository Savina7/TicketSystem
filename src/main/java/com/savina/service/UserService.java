package com.savina.service;
import com.savina.model.User;
import java.sql.*;


public class UserService {

    private static String DbUsername = "soft";
    private static String DbPassword = "1";
    private static String DbUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    public void registerUser(String firstName, String lastName,
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
                    "INSERT INTO USERI ( FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, PASSWORD,ROLE,STATUS) VALUES ( ?, ?, ?, ?,?,?,?)";

            insertStmt = con.prepareStatement(insert);
            insertStmt.setString(1, firstName);
            insertStmt.setString(2, lastName);
            insertStmt.setString(3, email);
            insertStmt.setString(4, phoneNumber);
            insertStmt.setString(5, password);
            insertStmt.setString(6,"user");

            insertStmt.setString(7,"1");

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

    public void registerStudent(String firstName, String lastName,
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
            String checkQuery = "SELECT COUNT(*) FROM USERI WHERE EMAIL = ?";
            checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Studenti me kete email egziston ");
                return;
            }

            // 2️⃣ Insert user i ri
            String insertUserSql = "INSERT INTO USERI (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, PASSWORD, ROLE, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertStmt = con.prepareStatement(insertUserSql);
            insertStmt.setString(1, firstName);
            insertStmt.setString(2, lastName);
            insertStmt.setString(3, email);
            insertStmt.setString(4, phoneNumber);
            insertStmt.setString(5, password);
            insertStmt.setString(6, "student");
            insertStmt.setString(7, "1");
            insertStmt.executeUpdate();

            // 3️⃣ Get the USER_ID of the inserted user using SELECT by email
            String selectUserIdSql = "SELECT USER_ID FROM USERI WHERE EMAIL = ?";
            PreparedStatement selectStmt = con.prepareStatement(selectUserIdSql);
            selectStmt.setString(1, email);
            rs = selectStmt.executeQuery();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt("USER_ID");
            }

            // 4️⃣ Kontrollo te dhenat nga STUDENT_ID
            String checkQuery1 = "SELECT COUNT(*) FROM STUDENT_ID WHERE STUDENT_NAME = ? AND STUDENT_SURNAME = ? AND EMAIL = ?";
            checkStmt = con.prepareStatement(checkQuery1);
            checkStmt.setString(1, firstName);
            checkStmt.setString(2, lastName);
            checkStmt.setString(3, email);
            rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Te dhenat tuaja nuk jane te sakta");
                return;
            }

            // 5️⃣ Insert into STUDENT table using the fetched USER_ID
            String insertStudentSql =
                    "INSERT INTO STUDENT (USER_ID, NR_MATRIKULIMI, STUDY_PROGRAM, YEAR_OF_ENROLLMENT, YEAR_OF_GRADUATION) " +
                            "SELECT ?, STUDENT_ID_NUMBER, STUDY_PROGRAM, YEAR_OF_ENROLLMENT, YEAR_OF_GRADUATION " +
                            "FROM STUDENT_ID " +
                            "WHERE STUDENT_NAME = ? AND STUDENT_SURNAME = ? AND EMAIL = ?";

            PreparedStatement insertStudentStmt = con.prepareStatement(insertStudentSql);
            insertStudentStmt.setInt(1, userId);   // USER_ID from USERI
            insertStudentStmt.setString(2, firstName);
            insertStudentStmt.setString(3, lastName);
            insertStudentStmt.setString(4, email);

            int rows1 = insertStudentStmt.executeUpdate();

            con.commit();

            System.out.println("Studenti u regjistrua. Rreshta: " + rows1);

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

return true;
    }
}
