package Controller;

import h2.DBConnection;
import model.DAO.ReviewDAO;
import model.DAO.SelectFoodDAO;
import model.DAO.StoreDAO;
import model.DAO.UserDAO;
import model.Entitiy.Review;
import model.Entitiy.Store;

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

@WebServlet(urlPatterns = "/reviewController")
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

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String storeId = request.getParameter("storeId");
        String action = request.getParameter("action");

        if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            doPost(request, response);
        }
    }

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

    //addSelectFoodList
    public void postReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        // 이거도 교체 필요
        Long storeId = 1L;

        //session에서 userId 받아오기
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        String content = request.getParameter("content");
        String ratingParam = request.getParameter("rating");
        Integer rating = (ratingParam != null) ? Integer.valueOf(ratingParam) : 0; // 또는 다른 디폴트 값
        Review review = new Review();
        review.setContent(content);
        review.setRating(rating);
        review.setStoreId(storeId);
        review.setUserId(userId);
        //이미지 주소 받아오는 부분으로 교체 필요
        review.setImage("12345");
        reviewDAO.addReview(review);

        String[] selectFoodList = request.getParameterValues("selectFoodList");
//        for (String selectFood : selectFoodList) {
//            //selectFoodDAO.addSelectFoodList(userId, storeId, selectFood);
//        }
    }
}
