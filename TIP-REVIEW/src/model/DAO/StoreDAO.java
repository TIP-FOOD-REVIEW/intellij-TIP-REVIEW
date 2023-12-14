package model.DAO;

import h2.DBConnection;
import model.Entitiy.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreDAO {
    final private DBConnection dbConnection;

    public StoreDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //getStore (by storeId)
    public Store getStore(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE WHERE STOREID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Store store = new Store();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                store.setStoreId(rs.getLong("storeId"));
                store.setStoreName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setDescription(rs.getString("description"));
                store.setLocation(rs.getString("location"));
                store.setRating(rs.getDouble("rating"));
                store.setReviewCount(rs.getLong("reviewCount"));
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

        ArrayList<Store> tempList = new ArrayList<>();
        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setStoreName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setRating(rs.getDouble("rating"));
                store.setReviewCount(rs.getLong("reviewCount"));
                tempList.add(store);
            }
        }

        return tempList.toArray(new Store[0]);
    }


    //ListByRating
    public Store[] listByRating() throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE ORDER BY RATING DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        ArrayList<Store> tempList = new ArrayList<>();

        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setStoreName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setRating(rs.getDouble("rating"));
                store.setReviewCount(rs.getLong("reviewCount"));
                tempList.add(store);
            }
            pstmt.executeQuery();
            return tempList.toArray(new Store[0]);
        }
    }

    //ListByReviewCount
    public Store[] listByReviewCount() throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE ORDER BY REVIEWCOUNT DESC";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        ArrayList<Store> tempList = new ArrayList<>();

        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setStoreName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setRating(rs.getDouble("rating"));
                store.setReviewCount(rs.getLong("reviewCount"));
                tempList.add(store);
            }
            pstmt.executeQuery();
            return tempList.toArray(new Store[0]);
        }
    }

    //searchStore (by name)
    public Store[] searchStore(String name) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM STORE WHERE NAME LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Store> tempList = new ArrayList<>();

        try (conn; pstmt; rs) {
            while (rs.next()) {
                Store store = new Store();
                store.setStoreId(rs.getLong("storeId"));
                store.setStoreName(rs.getString("storeName"));
                store.setAddress(rs.getString("address"));
                store.setPhone(rs.getString("phone"));
                store.setRating(rs.getDouble("rating"));
                store.setReviewCount(rs.getLong("reviewCount"));
                tempList.add(store);
            }
            pstmt.executeQuery();
            return tempList.toArray(new Store[0]);
        }
    }
}