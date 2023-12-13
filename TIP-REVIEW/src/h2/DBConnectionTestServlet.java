package h2;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/TestDB")
public class DBConnectionTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                response.getWriter().write("데이터베이스 연결 성공!");
                conn.close(); // 연결을 성공적으로 사용한 후 닫아주는 것이 좋습니다.
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter()); // 웹 페이지에 예외 출력
            response.getWriter().write("데이터베이스 연결 실패! 오류: " + e.getMessage());
        }
    }
}