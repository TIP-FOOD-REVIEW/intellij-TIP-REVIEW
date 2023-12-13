package h2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/SetDB")
public class DataSetServlet extends HttpServlet {
    @Override
    public void init() {
        //데이터 삽입
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            //Store 데이터 삽입 (description은 짧게 생성)
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, image, rating, ReviewCount) " +
                    "VALUES ('마포갈매기', '서울특별시 마포구 동교로 27', '02-3143-8383', '마포구의 대표적인 갈매기', '37.556602, 126.924171', '마포갈매기.jpg', 4.5, 100)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, image, rating, ReviewCount) " +
                    "VALUES ('마포갈매기2', '서울특별시 마포구 동교로 27', '02-3143-8383', '마포구의 대표적인 갈매기', '37.556602, 126.924171', '마포갈매기.jpg', 4.5, 100)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, image, rating, ReviewCount) " +
                    "VALUES ('마포갈매기3', '서울특별시 마포구 동교로 27', '02-3143-8383', '마포구의 대표적인 갈매기', '37.556602, 126.924171', '마포갈매기.jpg', 4.5, 100)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, image, rating, ReviewCount) " +
                    "VALUES ('마포갈매기4', '서울특별시 마포구 동교로 27', '02-3143-8383', '마포구의 대표적인 갈매기', '37.556602, 126.924171', '마포갈매기.jpg', 4.5, 100)");
            //Review 데이터 삽입
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, image) " +
                    "VALUES (1, 1, '맛있어요', 4.5, '마포갈매기.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, image) " +
                    "VALUES (1, 1, '맛있어요', 4.5, '마포갈매기.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, image) " +
                    "VALUES (1, 1, '맛있어요', 4.5, '마포갈매기.jpg')");
            //Food 데이터 삽입
            stmt.execute("INSERT INTO Food (foodName, storeId, price, image) " +
                    "VALUES ('마포갈매기', 1, 10000, '마포갈매기.jpg')");
            stmt.execute("INSERT INTO Food (foodName, storeId, price, image) " +
                    "VALUES ('매운마포갈매기', 1, 11000, '마포갈매기.jpg')");
            //SelectFood 데이터 삽입
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (2, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1, 2, 1)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //doGet 성공! 띄우기
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("데이터 삽입 생성 성공!");
    }
}