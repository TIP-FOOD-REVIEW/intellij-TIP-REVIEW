package model.DAO;

import h2.DBConnection;
import model.Entitiy.SelectFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectFoodDAO {
    final private DBConnection dbConnection;

    public SelectFoodDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //addSelectFoodList
    public void addSelectFoodList(SelectFood[] selectFoodList) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "INSERT INTO SELECT_FOOD (REVIEW_ID, STORE_ID, FOOD_ID) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            for(int i = 0; i < selectFoodList.length; i++) {
                pstmt.setLong(1, selectFoodList[i].getReviewId());
                pstmt.setLong(2, selectFoodList[i].getStoreId());
                pstmt.setLong(3, selectFoodList[i].getFoodId());
                pstmt.executeUpdate();
            }
        }
    }

    //deleteSelectFoodByReviewId
    public void deleteSelectFoodByReviewId(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "DELETE FROM SELECT_FOOD WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, reviewId);
            pstmt.executeUpdate();
        }
    }

    //ListByStoreIdAndFoodId (return reviewId)
    public Long[] listByStoreIdAndFoodId(Long storeId, Long foodId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM SELECT_FOOD WHERE STORE_ID = ? AND FOOD_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        pstmt.setLong(2, foodId);
        ResultSet rs = pstmt.executeQuery();

        Long[] reviewIdList = new Long[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                reviewIdList[i] = rs.getLong("reviewId");
                i++;
            }
            pstmt.executeQuery();
            return reviewIdList;
        }
    }

    //ListByReviewId (return foodId)
    public Long[] listByReviewId(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM SELECT_FOOD WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, reviewId);
        ResultSet rs = pstmt.executeQuery();

        Long[] foodIdList = new Long[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                foodIdList[i] = rs.getLong("foodId");
                i++;
            }
            pstmt.executeQuery();
            return foodIdList;
        }
    }
}