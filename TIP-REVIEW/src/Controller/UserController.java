package Controller;

import h2.DBConnection;
import model.DAO.UserDAO;
import model.Entitiy.User;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/userController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
    }

    //service
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if(action.equals("addUser")) {
            addUser(request, response);
        }else if(action.equals("login")) {
            login(request, response);
        }else if(action.equals("logout")) {
            logout(request, response);
        }

    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        try {
            userDAO.addUser(user);
            String resultMessage = "User.java added successfully.";
            request.setAttribute("resultMessage", resultMessage);
            // 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("username", username); //메인에 보여줌
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to add user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/home").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + username + ", password: " + password);

        try {
            Long userId = userDAO.login(username, password);
            if (userId != null) {

                String resultMessage = "Login successful. User.java ID: " + userId;
                System.out.println("resultMessage = " + resultMessage);
                request.setAttribute("resultMessage", resultMessage);

                // 세션 생성
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username); //메인에 보여줌

            } else {
                String resultMessage = "Login failed. Invalid credentials.";
                request.setAttribute("resultMessage", resultMessage);
                System.out.println("로그인 실패!!");
                request.getRequestDispatcher("/User/loginForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Login failed. An error occurred.";
            request.setAttribute("resultMessage", resultMessage);
        }
        response.sendRedirect("/home");
    }

    // 세션 삭제 메서드
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // 세션이 존재하고 username 속성이 있다면 해당 속성을 삭제합니다.
        if (session != null) {
            session.removeAttribute("username");
        }

        // 원하는 페이지로 리다이렉트할 수 있습니다.
        response.sendRedirect("/home");
    }

}
