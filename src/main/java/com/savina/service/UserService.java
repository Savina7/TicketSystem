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
        PreparedStatement checkStudentIdStmt = null;
        PreparedStatement checkUserStmt = null;
        PreparedStatement insertUserStmt = null;
        PreparedStatement insertStudentStmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            con.setAutoCommit(false);

            // 1️⃣ Kontrollo te dhënat ne STUDENT_ID (emri, mbiemri, email)
            String checkStudentIdQuery = "SELECT STUDENT_ID_NUMBER, STUDY_PROGRAM, YEAR_OF_ENROLLMENT, YEAR_OF_GRADUATION " +
                    "FROM STUDENT_ID WHERE STUDENT_NAME = ? AND STUDENT_SURNAME = ? AND EMAIL = ?";
            checkStudentIdStmt = con.prepareStatement(checkStudentIdQuery);
            checkStudentIdStmt.setString(1, firstName);
            checkStudentIdStmt.setString(2, lastName);
            checkStudentIdStmt.setString(3, email);
            rs = checkStudentIdStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Te dhenat tuaja nuk jane te sakta.");
                return;
            }

            // Ruaj të dhënat për insert në STUDENT
            String studentIdNumber = rs.getString("STUDENT_ID_NUMBER");
            String studyProgram = rs.getString("STUDY_PROGRAM");
            int yearEnrollment = rs.getInt("YEAR_OF_ENROLLMENT");
            int yearGraduation = rs.getInt("YEAR_OF_GRADUATION");

            // 2️⃣ Kontrollo në USERI nëse email ekziston
            String checkUserQuery = "SELECT COUNT(*) FROM USERI WHERE EMAIL = ?";
            checkUserStmt = con.prepareStatement(checkUserQuery);
            checkUserStmt.setString(1, email);
            rs = checkUserStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Studenti me kete email ekziston.");
                return;
            }

            // 3️⃣ Insert në USERI
            String insertUserSql = "INSERT INTO USERI (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, PASSWORD, ROLE, STATUS) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertUserStmt = con.prepareStatement(insertUserSql, new String[]{"USER_ID"});
            insertUserStmt.setString(1, firstName);
            insertUserStmt.setString(2, lastName);
            insertUserStmt.setString(3, email);
            insertUserStmt.setString(4, phoneNumber);
            insertUserStmt.setString(5, password);
            insertUserStmt.setString(6, "student");
            insertUserStmt.setString(7, "1");
            insertUserStmt.executeUpdate();

            // Merr USER_ID i sapo insertuar
            ResultSet generatedKeys = insertUserStmt.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            // 4️⃣ Insert në STUDENT
            String insertStudentSql = "INSERT INTO STUDENT (USER_ID, NR_MATRIKULIMI, STUDY_PROGRAM, YEAR_OF_ENROLLMENT, YEAR_OF_GRADUATION) " +
                    "VALUES (?, ?, ?, ?, ?)";
            insertStudentStmt = con.prepareStatement(insertStudentSql);
            insertStudentStmt.setInt(1, userId);
            insertStudentStmt.setString(2, studentIdNumber);
            insertStudentStmt.setString(3, studyProgram);
            insertStudentStmt.setInt(4, yearEnrollment);
            insertStudentStmt.setInt(5, yearGraduation);

            int rowsInserted = insertStudentStmt.executeUpdate();

            con.commit();
            System.out.println("Studenti u regjistrua me sukses. Rreshta te inseruar ne STUDENT: " + rowsInserted);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (checkStudentIdStmt != null) checkStudentIdStmt.close(); } catch (Exception ignored) {}
            try { if (checkUserStmt != null) checkUserStmt.close(); } catch (Exception ignored) {}
            try { if (insertUserStmt != null) insertUserStmt.close(); } catch (Exception ignored) {}
            try { if (insertStudentStmt != null) insertStudentStmt.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }



    public void registerAdmin(String firstName, String lastName,
                              String email, String phoneNumber, String password) {

        Connection con = null;
        PreparedStatement checkAdminIdStmt = null;
        PreparedStatement checkUserStmt = null;
        PreparedStatement insertUserStmt = null;
        PreparedStatement insertAdminStmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            con.setAutoCommit(false);

            // 1️⃣ Kontrollo të dhënat në ADMIN_ID (emri, mbiemri, email)
            String checkAdminIdQuery = "SELECT ADMIN_ID_NUMBER, COMPANY_ID " +
                    "FROM ADMIN_ID WHERE ADMIN_NAME = ? AND ADMIN_SURNAME = ? AND EMAIL = ?";
            checkAdminIdStmt = con.prepareStatement(checkAdminIdQuery);
            checkAdminIdStmt.setString(1, firstName);
            checkAdminIdStmt.setString(2, lastName);
            checkAdminIdStmt.setString(3, email);
            rs = checkAdminIdStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Te dhenat tuaja nuk jane te sakta.");
                return;
            }

            // Ruaj të dhënat për insert në ADMIN
            String adminIdNumber = rs.getString("ADMIN_ID_NUMBER");
            String companyID = rs.getString("COMPANY_ID");

            // 2️⃣ Kontrollo në USERI nëse email ekziston
            String checkUserQuery = "SELECT COUNT(*) FROM USERI WHERE EMAIL = ?";
            checkUserStmt = con.prepareStatement(checkUserQuery);
            checkUserStmt.setString(1, email);
            rs = checkUserStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Admini me kete email ekziston.");
                return;
            }

            // 3️⃣ Insert në USERI
            String insertUserSql = "INSERT INTO USERI (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, PASSWORD, ROLE, STATUS) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertUserStmt = con.prepareStatement(insertUserSql, new String[]{"USER_ID"});
            insertUserStmt.setString(1, firstName);
            insertUserStmt.setString(2, lastName);
            insertUserStmt.setString(3, email);
            insertUserStmt.setString(4, phoneNumber);
            insertUserStmt.setString(5, password);
            insertUserStmt.setString(6, "admin");
            insertUserStmt.setString(7, "1");
            insertUserStmt.executeUpdate();

            // Merr USER_ID i sapo insertuar
            ResultSet generatedKeys = insertUserStmt.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }

            // 4️⃣ Insert në ADMIN
            String insertAdminSql = "INSERT INTO ADMIN (USER_ID, COMPANY_ID, STATUS) " +
                    "VALUES (?, ?, ?)";
            insertAdminStmt = con.prepareStatement(insertAdminSql);
            insertAdminStmt.setInt(1, userId);
            insertAdminStmt.setString(2, companyID);
            insertAdminStmt.setString(3, "1");

            int rowsInserted = insertAdminStmt.executeUpdate();

            con.commit();
            System.out.println("Admini u regjistrua me sukses. Rreshta të inseruar në ADMIN: " + rowsInserted);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (checkAdminIdStmt != null) checkAdminIdStmt.close(); } catch (Exception ignored) {}
            try { if (checkUserStmt != null) checkUserStmt.close(); } catch (Exception ignored) {}
            try { if (insertUserStmt != null) insertUserStmt.close(); } catch (Exception ignored) {}
            try { if (insertAdminStmt != null) insertAdminStmt.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }




    public boolean logIn(String email, String password) {

return true;
    }
}
