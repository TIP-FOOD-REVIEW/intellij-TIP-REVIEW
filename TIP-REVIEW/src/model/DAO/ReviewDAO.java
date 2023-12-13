package model.DAO;

import h2.DBConnection;
import model.Entitiy.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {
    final private DBConnection dbConnection;

    public ReviewDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //addReview
    public void addReview(Review review) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "INSERT INTO REVIEW (USERID, STOREID, CONTENT, RATING, IMAGE) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, review.getUserId());
            pstmt.setLong(2, review.getStoreId());
            pstmt.setString(3, review.getContent());
            pstmt.setLong(4, review.getRating());
            pstmt.setString(5, review.getImage());
            pstmt.executeUpdate();
        }
    }

    //deleteReview
    public void deleteReview(Long reviewId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "DELETE FROM REVIEW WHERE REVIEWID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, reviewId);
            pstmt.executeUpdate();
        }
    }

    //updateReview
    public void updateReview(Review review) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "UPDATE REVIEW SET USERID = ?, STOREID = ?, CONTENT = ?, RATING = ?, IMAGE = ? WHERE REVIEWID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setLong(1, review.getUserId());
            pstmt.setLong(2, review.getStoreId());
            pstmt.setString(3, review.getContent());
            pstmt.setLong(4, review.getRating());
            pstmt.setString(5, review.getImage());
            pstmt.setLong(6, review.getReviewId());
            pstmt.executeUpdate();
        }
    }

    // listReviews (by storeId)
    public Review[] listReviews(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STOREID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Review> reviewList = new ArrayList<>();
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
<<<<<<< HEAD
                review.setImage(rs.getString("imageurl"));
=======
                review.setImage(rs.getString("image"));
>>>>>>> 4b9032b96d08c7cd85b0f063482a136ca984d42f
                reviewList.add(review);
            }
        }
        return reviewList.toArray(new Review[0]);
    }


    //ListByRatingDesc (내림차순)
    public Review[] listByRating(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STOREID = ? ORDER BY RATING DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Review> reviewList = new ArrayList<>();
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
<<<<<<< HEAD
                review.setImage(rs.getString("imageurl"));
=======
                review.setImage(rs.getString("image"));
>>>>>>> 4b9032b96d08c7cd85b0f063482a136ca984d42f
                reviewList.add(review);
            }
        }
        return reviewList.toArray(new Review[0]);
    }

    //ListByRatingAsc (오름차순)
    public Review[] listByRatingAsc(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE STOREID = ? ORDER BY RATING ASC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Review> reviewList = new ArrayList<>();
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getLong("reviewId"));
                review.setUserId(rs.getLong("userId"));
                review.setStoreId(rs.getLong("storeId"));
                review.setContent(rs.getString("content"));
                review.setRating((int) rs.getLong("rating"));
<<<<<<< HEAD
                review.setImage(rs.getString("imageurl"));
=======
                review.setImage(rs.getString("image"));
>>>>>>> 4b9032b96d08c7cd85b0f063482a136ca984d42f
                reviewList.add(review);
            }
        }
        return reviewList.toArray(new Review[0]);
    }

    //ListByListReviewId
    public Review[] listByListReviewId(Long[] reviewIdList) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM REVIEW WHERE REVIEWID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ArrayList<Review> reviewList = new ArrayList<>();
        try (conn; pstmt) {
            for (Long reviewId : reviewIdList) {
                pstmt.setLong(1, reviewId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Review review = new Review();
                    review.setReviewId(rs.getLong("reviewId"));
                    review.setUserId(rs.getLong("userId"));
                    review.setStoreId(rs.getLong("storeId"));
                    review.setContent(rs.getString("content"));
                    review.setRating((int) rs.getLong("rating"));
<<<<<<< HEAD
                    review.setImage(rs.getString("imageurl"));
=======
                    review.setImage(rs.getString("image"));
>>>>>>> 4b9032b96d08c7cd85b0f063482a136ca984d42f
                    reviewList.add(review);
                }
            }
            pstmt.executeQuery();
            return reviewList.toArray(new Review[0]);
        }
    }
}