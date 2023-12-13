package Controller;

import h2.DBConnection;
import model.DAO.ReviewDAO;
import model.DAO.SelectFoodDAO;
import model.DAO.StoreDAO;
import model.DAO.UserDAO;
import model.Entitiy.Store;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/StoreController")
public class StoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StoreDAO storeDAO;
    private ServletContext ctx;

    private final String storeDetailPage = "/storeList.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
        ctx = getServletContext();
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

    private void listStore(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Store> storeArray = List.of(storeDAO.listStore());
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeDetailPage).forward(request,response);
    }

    private void listByRating(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Store> storeArray = List.of(storeDAO.listByRating());
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeDetailPage).forward(request,response);
    }

    //ListByReviewCount
    private void listByReviewCount(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Store> storeArray = List.of(storeDAO.listByReviewCount());
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeDetailPage).forward(request,response);
    }

    //searchStore (by storeName)
    private void searchStore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String storeName = request.getParameter("storeName");
        List<Store> storeArray = List.of(storeDAO.searchStore(storeName));
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeDetailPage).forward(request,response);
    }
}
