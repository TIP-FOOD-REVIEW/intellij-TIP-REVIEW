package model.DAO;

import h2.DBConnection;
import model.Entitiy.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodDAO {
    final private DBConnection dbConnection;

    public FoodDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //getFood (by foodId)
    public Food getFood(Long foodId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE FOOD_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, foodId);
        ResultSet rs = pstmt.executeQuery();

        Food food = new Food();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                food.setFoodId(rs.getLong("foodId"));
                food.setName(rs.getString("name"));
                food.setImageUrl(rs.getBytes("storeId"));
                food.setStoreId(rs.getLong("imageUrl"));
                food.setPrice(rs.getLong("price"));
            }
            pstmt.executeQuery();
            return food;
        }
    }

    //getFoodList (by storeId)
    public Food[] getFoodList(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE STORE_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        Food[] foodList = new Food[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getLong("foodId"));
                food.setName(rs.getString("name"));
                food.setImageUrl(rs.getBytes("storeId"));
                food.setStoreId(rs.getLong("imageUrl"));
                food.setPrice(rs.getLong("price"));
                foodList[i] = food;
                i++;
            }
            pstmt.executeQuery();
            return foodList;
        }
    }

    //searchFood (by name)
    public Food[] searchFood(String name) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE NAME LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();

        Food[] foodList = new Food[100];
        int i = 0;
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getLong("foodId"));
                food.setName(rs.getString("name"));
                food.setImageUrl(rs.getBytes("storeId"));
                food.setStoreId(rs.getLong("imageUrl"));
                food.setPrice(rs.getLong("price"));
                foodList[i] = food;
                i++;
            }
            pstmt.executeQuery();
            return foodList;
        }
    }
}