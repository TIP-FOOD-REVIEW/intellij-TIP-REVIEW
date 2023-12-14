package model.DAO;

import h2.DBConnection;
import model.Entitiy.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodDAO {
    final private DBConnection dbConnection;

    public FoodDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //getFood (by foodId)
    public Food getFood(Long foodId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE FOODID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, foodId);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        Food food = new Food();
        try(conn; pstmt; rs) {
            food.setFoodName(rs.getString("foodName"));
            pstmt.executeQuery();
            return food;
        }
    }

    //getFoodList (by storeId)
    public Food[] getFoodList(Long storeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE STOREID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, storeId);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Food> foodList = new ArrayList<>();
        try(conn; pstmt; rs) {
            while(rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getLong("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setPrice(rs.getLong("price"));
                foodList.add(food);
            }
            pstmt.executeQuery();
            return foodList.toArray(new Food[0]);
        }
    }

    //searchFood (by name)
    public Food searchFood(String name) throws SQLException {
        Connection conn = dbConnection.getConnection();
        String sql = "SELECT * FROM FOOD WHERE FOODNAME LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        Food food = new Food();
        try(conn; pstmt; rs) {
            food.setFoodId(rs.getLong("foodId"));
            food.setFoodName(rs.getString("foodName"));
            food.setPrice(rs.getLong("price"));
            pstmt.executeQuery();
            return food;
        }
    }
}