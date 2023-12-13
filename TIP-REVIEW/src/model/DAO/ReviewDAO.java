package model.DAO;

import h2.DBConnection;
import model.Entitiy.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAO {
    final private DBConnection dbConnection;

    public ReviewDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //addReview
    public void addReview(Review review) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "INSERT INTO REVIEW (USER_ID, STORE_ID, CONTENT, RATING, IMAGEURL) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, review.getUserId());
            pstmt.setLong(2, review.getStoreId());
            pstmt.setString(3, review.getContent());
            pstmt.setLong(4, review.getRating());
            pstmt.setBytes(5, review.getImageUrl());
            pstmt.executeUpdate();
        }
    }

    //deleteReview
    public void deleteReview(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "DELETE FROM REVIEW WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, reviewId);
            pstmt.executeUpdate();
        }
    }

    //updateReview
    public void updateReview(Review review) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "UPDATE REVIEW SET USER_ID = ?, STORE_ID = ?, CONTENT = ?, RATING = ?, IMAGEURL = ? WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, review.getUserId());
            pstmt.setLong(2, review.getStoreId());
            pstmt.setString(3, review.getContent());
            pstmt.setLong(4, review.getRating());
            pstmt.setBytes(5, review.getImageUrl());
            pstmt.setLong(6, review.getReviewId());
            pstmt.executeUpdate();
        }
    }

    //getReviewList (by storeId)
    public Review[] getReviewList(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STORE_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Review[] reviewList = new Review[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
                review.setImageUrl(rs.getBytes("imageUrl"));
                reviewList[i] = review;
                i++;
            }
            pstmt.executeQuery();
            return reviewList;
        }
    }

    //ListByRatingDesc (내림차순)
    public Review[] listByRating(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STORE_ID = ? ORDER BY RATING DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Review[] reviewList = new Review[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
                review.setImageUrl(rs.getBytes("imageUrl"));
                reviewList[i] = review;
                i++;
            }
            pstmt.executeQuery();
            return reviewList;
        }
    }

    //ListByRatingAsc (오름차순)
    public Review[] listByRatingAsc(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STORE_ID = ? ORDER BY RATING ASC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Review[] reviewList = new Review[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
                review.setImageUrl(rs.getBytes("imageUrl"));
                reviewList[i] = review;
                i++;
            }
            pstmt.executeQuery();
            return reviewList;
        }
    }

    //ListByListReviewId
    public Review[] listByListReviewId(Long[] reviewIdList) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE REVIEW_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        Review[] reviewList = new Review[100];
        int i = 0;
        try(conn; pstmt) {
            for(Long reviewId : reviewIdList) {
                pstmt.setLong(1, reviewId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()) {
                    Review review = new Review();
                    review.setReviewId(rs.getLong("reviewId"));
                    review.setUserId(rs.getLong("userId"));
                    review.setStoreId(rs.getLong("storeId"));
                    review.setContent(rs.getString("content"));
                    review.setRating((int) rs.getLong("rating"));
                    review.setImageUrl(rs.getBytes("imageUrl"));
                    reviewList[i] = review;
                    i++;
                }
            }
            pstmt.executeQuery();
            return reviewList;
        }
    }
}
