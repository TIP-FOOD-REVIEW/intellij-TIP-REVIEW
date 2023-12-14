package model.DAO;

import h2.DBConnection;
import model.Entitiy.SelectFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectFoodDAO {
    final private DBConnection dbConnection;

    public SelectFoodDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //addSelectFoodList
    public void addSelectFoodList(Long reviewId, Long storeId, Long foodId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "INSERT INTO SELECTFOOD (REVIEW_ID, STORE_ID, FOOD_ID) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, reviewId);
            pstmt.setLong(2, storeId);
            pstmt.setLong(3, foodId);
            pstmt.executeUpdate();
        }
    }

    //deleteSelectFoodByReviewId
    public void deleteSelectFoodByReviewId(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "DELETE FROM SELECTFOOD WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, reviewId);
            pstmt.executeUpdate();
        }
    }

    //ListByStoreIdAndFoodId (return reviewId)
    public Long[] listByStoreIdAndFoodId(Long storeId, Long foodId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM SELECTFOOD WHERE STOREID = ? AND FOODID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        pstmt.setLong(2, foodId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Long> foodIdList = new ArrayList<>();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                foodIdList.add(rs.getLong("reviewId"));
            }
            pstmt.executeQuery();
            return foodIdList.toArray(new Long[0]);
        }
    }

    //ListByReviewId (return foodId)
    public Long[] listByReviewId(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM SELECTFOOD WHERE REVIEWID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, reviewId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Long> foodIdList = new ArrayList<>();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                foodIdList.add(rs.getLong("foodId"));
            }
            pstmt.executeQuery();
            return foodIdList.toArray(new Long[0]);
        }
    }
}