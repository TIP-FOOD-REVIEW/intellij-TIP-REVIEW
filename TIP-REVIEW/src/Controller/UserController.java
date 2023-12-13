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
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(urlPatterns = "/userController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.userDAO = new UserDAO(dbConnection);
    }
    //doGet
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to add user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        //request.getRequestDispatcher("/result.jsp").forward(request, response);
        request.getRequestDispatcher("/hello.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        try {
            userDAO.deleteUser(userId);
            String resultMessage = "User.java deleted successfully.";
            request.setAttribute("resultMessage", resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to delete user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        try {
            userDAO.updateUser(user);
            String resultMessage = "User.java updated successfully.";
            request.setAttribute("resultMessage", resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to update user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + username + ", password: " + password);

        try {
            Long userId = userDAO.login(username, password);
            if (userId != null) {

                String resultMessage = "Login successful. User.java ID: " + userId;
                request.setAttribute("resultMessage", resultMessage);
                request.setAttribute("username", username); //화면에 보여줄 유저 아이디

                //유저 기본키
                request.setAttribute("userId", userId); //이 값으로 마이페이지 정보에 접근

                //성공 시 메인페이지로 이동
                request.getRequestDispatcher("/hello.jsp").forward(request, response);
            } else {
                String resultMessage = "Login failed. Invalid credentials.";
                request.setAttribute("resultMessage", resultMessage);
                System.out.println("로그인 실패!!");
                request.getRequestDispatcher("/User.java/loginForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Login failed. An error occurred.";
            request.setAttribute("resultMessage", resultMessage);
        }

        //request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

}
