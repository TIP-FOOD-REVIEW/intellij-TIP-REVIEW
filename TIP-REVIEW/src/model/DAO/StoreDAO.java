package model.DAO;

import h2.DBConnection;
import model.Entitiy.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreDAO {
    final private DBConnection dbConnection;

    public StoreDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //getStore (by storeId)
    public Store getStore(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE WHERE STORE_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Store store = new Store();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                store.setStoreId(rs.getLong("storeId"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setImage(rs.getBytes("image"));
                store.setDescription(rs.getString("description"));
                store.setLocation(rs.getString("location"));
                store.setRating(rs.getLong("rating"));
            }
            pstmt.executeQuery();
            return store;
        }
    }

    //ListStore
    public Store[] listStore() throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        Store[] storeList = new Store[100];
        int i = 0;
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setImage(rs.getBytes("image"));
                store.setRating(rs.getLong("rating"));
                storeList[i] = store;
                i++;
            }
            pstmt.executeQuery();
            return storeList;
        }
    }

    //ListByRating
    public Store[] listByRating() throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE ORDER BY RATING DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        Store[] storeList = new Store[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setImage(rs.getBytes("image"));
                store.setRating(rs.getLong("rating"));
                storeList[i] = store;
                i++;
            }
            pstmt.executeQuery();
            return storeList;
        }
    }

    //ListByReviewCount
    public Store[] listByReviewCount() throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE ORDER BY REVIEW_COUNT DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        Store[] storeList = new Store[100];
        int i = 0;
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setImage(rs.getBytes("image"));
                store.setRating(rs.getLong("rating"));
                storeList[i] = store;
                i++;
            }
            pstmt.executeQuery();
            return storeList;
        }
    }

    //searchStore (by name)
    public Store[] searchStore(String name) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE WHERE NAME LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();

        Store[] storeList = new Store[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setImage(rs.getBytes("image"));
                store.setRating(rs.getLong("rating"));
                storeList[i] = store;
                i++;
            }
            pstmt.executeQuery();
            return storeList;
        }
    }
}