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
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('한스오믈렛', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2580', '양식파는 집이에요.', '37.556602, 126.924171', 5, 10)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('산북경', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2581', '중식파는 집이에요.', '37.556602, 126.924171', 3, 10)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('하나비', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2582', '이것저것 팔아요.', '37.556602, 126.924171', 2.5, 10)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('맘스터치', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2583', '싸이버거가 시그니쳐에요.', '37.556602, 126.924171', 2, 10)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('토스피아', '경기도 시흥시 산기대학로 237 1층', '031-8041-2584', '토스트 맛집이에요.', '37.556602, 126.924171', 3.8, 10)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('올리브그린', '경기도 시흥시 산기대학로 237 2층', '031-8041-2585', '많이 와주세요.', '37.556602, 126.924171', 3, 10)");
            //Review 데이터 삽입
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 1, '맛집이네요.', 5, '한스오믈렛1.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 1, '잘 먹었습니다.', 4.5, '한스오믈렛2.png')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 1, '다음에 또 올게요.', 4.0, '한스오믈렛3.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 1, '음식 잘하시네요.', 4.5, '한스오믈렛4.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 1, '너무 비싸요.', 2, '한스오믈렛5.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 2, '맛있어요', 5, '산북경1.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 2, '가성비 좋지 않아요.', 2.5, '산북경2.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 2, '그럭저럭이에요.', 1.5, '산북경3.jpg')");
            stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                    "VALUES (1, 3, '...', 1, '하나비.jpg')");
            //Food 데이터 삽입
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('매콤소금구이덮밥', 1, 6000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('날치알누드김밥', 1, 11000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('비빔밥 + 미니우동', 1, 5500)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('통소세지 오므라이스', 1, 6000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('부대찌개', 1, 6000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('짜장면', 2, 6000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('양장피', 2, 20000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('미니탕수육', 2, 10000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('마제소바', 3, 5000)");
            //SelectFood 데이터 삽입
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (2, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (3, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (4, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (5, 1, 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1, 1, 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (2, 1, 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (3, 1, 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1, 1, 3)");
            //User 데이터 삽입
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) VALUES ('홍길동', '123456', 'aaa@tukorea.ac.kr', '010-1234-1234')");
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