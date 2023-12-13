package Controller;

import h2.DBConnection;
import model.DAO.ReviewDAO;
import model.DAO.SelectFoodDAO;
import model.DAO.StoreDAO;
import model.DAO.UserDAO;
import model.Entitiy.Store;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/reviewPost")
public class ReviewPostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StoreDAO storeDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private SelectFoodDAO selectFoodDAO;

    private final String ReviewPostPage = "/reviewPost.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
        this.reviewDAO = new ReviewDAO(dbConnection);
        this.userDAO = new UserDAO(dbConnection);
        this.selectFoodDAO = new SelectFoodDAO(dbConnection);
    }

//    //service
//    protected void service(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String storeId = request.getParameter("storeId");
//        String action = request.getParameter("action");
//
//        try {
//            getStore(request, response);
//            getReviewList(request, response);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    //addSelectFoodList
//    public void postReview(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException {
//        Long storeId = Long.parseLong(request.getParameter("storeId"));
//
//        Long userId = Long.valueOf(request.getParameter("userId"));
//        request.setAttribute("userId", userId);
//
//        String[] selectFoodList = request.getParameterValues("selectFoodList");
//        for (String selectFood : selectFoodList) {
//            selectFoodDAO.addSelectFoodList(userId, storeId, selectFood);
//        }
//    }
}
