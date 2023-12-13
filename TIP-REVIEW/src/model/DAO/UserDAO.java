package model.DAO;

import h2.DBConnection;
import model.Entitiy.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    final private DBConnection dbConnection;

    public UserDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    //addUser
    public void addUser(Users user) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.executeUpdate();
        }
    }

    //deleteUser
    public void deleteUser(Long userId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "DELETE FROM USER WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
        }
    }

    //updateUser
    public void updateUser(Users user) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "UPDATE USER SET USERNAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ? WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setLong(5, user.getUserId());
            pstmt.executeUpdate();
        }
    }

    //login (by username, password) -> return userId
    public Long login(String username, String password) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT USER_ID FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("USER_ID");
                } else {
                    // 사용자가 존재하지 않을 경우에 대한 처리
                    return null;
                }
            }
        }
    }

    //getUser (by userId)
    public Users getUser(Long userId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM USER WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, userId);
        ResultSet rs = pstmt.executeQuery();

        Users user = new Users();
        try(conn; pstmt; rs) {
            user.setUserId(rs.getLong("userId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            pstmt.executeQuery();
            return user;
        }
    }
}
