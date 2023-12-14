package h2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/CreateDBTable")
public class DatabaseInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // 데이터베이스 테이블 생성 (User, Store, Review, SelectFood, Food) (외래키는 없음)
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS Users");
            stmt.execute("DROP TABLE IF EXISTS Store");
            stmt.execute("DROP TABLE IF EXISTS Review");
            stmt.execute("DROP TABLE IF EXISTS SelectFood");
            stmt.execute("DROP TABLE IF EXISTS Food");
            stmt.execute("CREATE TABLE IF NOT EXISTS Users (" +
                    "userId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(255) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    "phone VARCHAR(255) NOT NULL " +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS Store (" +
                    "storeId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "storeName VARCHAR(255) NOT NULL, " +
                    "address VARCHAR(255) NOT NULL, " +
                    "phone VARCHAR(255) NOT NULL, " +
                    "description VARCHAR(255) NOT NULL, " +
                    "location VARCHAR(255) NOT NULL, " +
                    "rating DOUBLE NOT NULL, " +
                    "ReviewCount BIGINT NOT NULL " +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS Review (" +
                    "reviewId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "userId BIGINT NOT NULL, " +
                    "storeId BIGINT NOT NULL, " +
                    "content VARCHAR(255) NOT NULL, " +
                    "rating DOUBLE NOT NULL, " +
                    "imageurl VARCHAR(255) NOT NULL " +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS SelectFood (" +
                    "selectFoodId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "foodId BIGINT NOT NULL, " +
                    "reviewId BIGINT NOT NULL, " +
                    "storeId BIGINT NOT NULL " +
                    ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS Food (" +
                    "foodId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "foodName VARCHAR(255) NOT NULL, " +
                    "storeId BIGINT NOT NULL, " +
                    "price DOUBLE NOT NULL " +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //doGet 성공! 띄우기
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("테이블 생성 성공!");
    }
}
