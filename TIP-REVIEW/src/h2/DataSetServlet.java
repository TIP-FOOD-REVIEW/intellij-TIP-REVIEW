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

    //doGet 성공! 띄우기
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //데이터 삽입
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            //Store 데이터 삽입 (description은 짧게 생성)
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('한스오믈렛', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2580', '양식파는 집이에요.', '37.556602, 126.924171', 3, 5)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('산북경', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2581', '중식파는 집이에요.', '37.556602, 126.924171', 3, 5)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('하나비', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2582', '이것저것 팔아요.', '37.556602, 126.924171', 2.5, 4)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('맘스터치', '경기도 시흥시 산기대학로 237 지하 1층', '031-8041-2583', '싸이버거가 시그니쳐에요.', '37.556602, 126.924171', 2, 3)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('토스피아', '경기도 시흥시 산기대학로 237 1층', '031-8041-2584', '토스트 맛집이에요.', '37.556602, 126.924171', 2.5, 2)");
            stmt.execute("INSERT INTO Store (storeName, address, phone, description, location, rating, ReviewCount) " +
                    "VALUES ('올리브그린', '경기도 시흥시 산기대학로 237 2층', '031-8041-2585', '많이 와주세요.', '37.556602, 126.924171', 3, 1)");
            //Review 데이터 삽입
            for(int i=1; i<7; i++){
                stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                        "VALUES (1, "+i+", '맛집이네요.', 3, '한스오믈렛1.jpg')");
                if(i>4)
                    continue;
                stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                        "VALUES (2, "+i+", '잘 먹었습니다.', 2, '한스오믈렛2.png')");
                if(i==4)
                    continue;
                stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                        "VALUES (3, "+i+", '다음에 또 올게요.', 1, '한스오믈렛3.jpg')");
                if(i==3)
                    continue;
                stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                        "VALUES (4, "+i+", '음식 잘하시네요.', 4, '한스오믈렛4.jpg')");
                if(i==2)
                    continue;
                stmt.execute("INSERT INTO Review (userId, storeId, content, rating, imageurl) " +
                        "VALUES (5, "+i+", '너무 비싸요.', 5, '한스오믈렛5.jpg')");
            }
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
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('치킨버거', 4, 5000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('햄치즈 토스트', 5, 5000)");
            stmt.execute("INSERT INTO Food (foodName, storeId, price) " +
                    "VALUES ('김치볶음밥', 6, 5000)");
            //SelectFood 데이터 삽입
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (1,  1 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (2,  1 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (3,  1 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (2,  2 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (3,  3 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (4,  3 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (4,  4 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (5,  5 , 1)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (6,  6 , 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (6,  7 , 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (7,  8 , 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (8,  9 , 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (6,  10 , 2)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (9,  11 , 3)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (9,  12 , 3)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (9,  13 , 3)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (9,  14 , 3)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (10,  15 , 4)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (10,  16 , 4)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (10,  17 , 4)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (11,  18 , 5)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (11,  19 , 5)");
            stmt.execute("INSERT INTO SelectFood (foodId, reviewId, storeId) " +
                    "VALUES (12,  20 , 6)");
            //User 데이터 삽입
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) " +
                    "VALUES ('홍길동', '123', 'aaa@tukorea.ac.kr', '010-1234-1234')");
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) " +
                    "VALUES ('정우진', '123', 'aaa@tukorea.ac.kr', '010-1234-1234')");
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) " +
                    "VALUES ('김람운', '123', 'aaa@tukorea.ac.kr', '010-1234-1234')");
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) " +
                    "VALUES ('김상원', '123', 'aaa@tukorea.ac.kr', '010-1234-1234')");
            stmt.execute("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, PHONE) " +
                    "VALUES ('신혁준', '123', 'aaa@tukorea.ac.kr', '010-1234-1234')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write("데이터 삽입 생성 성공!");
    }
}