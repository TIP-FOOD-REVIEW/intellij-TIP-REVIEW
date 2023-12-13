package Controller;

import Bean.UserBean;
import model.Entitiy.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userController")
public class UserController extends HttpServlet {

    private UserBean userBean = UserBean.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addUser".equals(action)) {
            addUser(request, response);
        } else if ("deleteUser".equals(action)) {
            deleteUser(request, response);
        } else if ("updateUser".equals(action)) {
            updateUser(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        } else if ("getUser".equals(action)) {
            getUser(request, response);
        }
        // 다른 액션들에 대한 처리도 추가 가능
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
            userBean.getUserDAO().addUser(user);
            String resultMessage = "User added successfully.";
            request.setAttribute("resultMessage", resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to add user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        try {
            userBean.getUserDAO().deleteUser(userId);
            String resultMessage = "User deleted successfully.";
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
            userBean.getUserDAO().updateUser(user);
            String resultMessage = "User updated successfully.";
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

        try {
            Long userId = userBean.getUserDAO().login(username, password);
            if (userId != null) {
                String resultMessage = "Login successful. User ID: " + userId;
                request.setAttribute("resultMessage", resultMessage);
            } else {
                String resultMessage = "Login failed. Invalid credentials.";
                request.setAttribute("resultMessage", resultMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Login failed. An error occurred.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        try {
            User user = userBean.getUserDAO().getUser(userId);
            String resultMessage = "User retrieved successfully. User: " + user.getUsername();
            request.setAttribute("resultMessage", resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to retrieve user.";
            request.setAttribute("resultMessage", resultMessage);
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
