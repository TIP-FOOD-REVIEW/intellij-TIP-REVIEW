package model.DAO;

import h2.DBConnection;
import model.Entitiy.User;

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
    public void addUser(User user) throws SQLException {
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

    //login (by username, password) -> return userId
    public Long login(String username, String password) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT USER_ID FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeQuery();
            return pstmt.getResultSet().getLong("userId");
        }
    }

    //getUser (by userId)
    public User getUser(Long userId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM USERS WHERE USERID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);


        pstmt.setLong(1, userId);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        try(conn; pstmt; rs) {
            user.setUserId(rs.getLong("USERID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPhone(rs.getString("PHONE"));
            pstmt.executeQuery();
            return user;
        }
    }
}