package Controller;

import h2.DBConnection;
import model.DAO.StoreDAO;
import model.Entitiy.Store;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/home")
public class StoreController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StoreDAO storeDAO;

    private final String storeListPage = "/storeList.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
    }

    //service
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if(action == null) {
                listStore(request, response);
            }else if(action.equals("listByRating")) {
                listByRating(request, response);
            }else if(action.equals("listByReviewCount")) {
                listByReviewCount(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //ListStore
    private void listStore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Store[] storeArray = storeDAO.listStore();
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeListPage).forward(request,response);
    }

    //listByRating
    private void listByRating(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Store[] storeArray = storeDAO.listByRating();
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeListPage).forward(request,response);
    }

    //ListByReviewCount
    private void listByReviewCount(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Store[] storeArray = storeDAO.listByReviewCount();
        request.setAttribute("storeList", storeArray);
        request.getRequestDispatcher(storeListPage).forward(request,response);
    }

}
