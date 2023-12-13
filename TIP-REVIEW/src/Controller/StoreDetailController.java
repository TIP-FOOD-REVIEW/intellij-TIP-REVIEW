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
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/storeDetail")
public class StoreDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StoreDAO storeDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private SelectFoodDAO selectFoodDAO;

    private final String storeDetailPage = "/WEB-INF/view/storeDetail.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
        this.reviewDAO = new ReviewDAO(dbConnection);
        this.userDAO = new UserDAO(dbConnection);
        this.selectFoodDAO = new SelectFoodDAO(dbConnection);
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

    //getStore (by storeId)
    public String getStore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);
        return storeDetailPage;
    }

    //ReviewList (by storeId)
    public String getReviewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Review[] reviewList = reviewDAO.getReviewList(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            request.setAttribute("foodIdList_" + reviewList[i].getReviewId(), foodIdList);
        }
        return storeDetailPage;
    }

    //ReviewListByRatingDESC (by storeId)
    public String getReviewListByRatingDESC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Review[] reviewList = reviewDAO.listByRating(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            request.setAttribute("foodIdList_" + reviewList[i].getReviewId(), foodIdList);
        }
        return storeDetailPage;
    }

    //ReviewListByRatingASC (by storeId)
    public String getReviewListByRatingASC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Review[] reviewList = reviewDAO.listByRatingAsc(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            request.setAttribute("foodIdList_" + reviewList[i].getReviewId(), foodIdList);
        }
        return storeDetailPage;
    }

    //ReviewListByStoreIdAndFoodId (by storeId, foodId)
    public String getReviewListByStoreIdAndFoodId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Long foodId = Long.parseLong(request.getParameter("foodId"));
        Long[] reviewIdList = selectFoodDAO.listByStoreIdAndFoodId(storeId, foodId);
        Review[] reviewList = reviewDAO.listByListReviewId(reviewIdList);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            request.setAttribute("foodIdList_" + reviewList[i].getReviewId(), foodIdList);
        }
        return storeDetailPage;
    }

    //deleteReview (by reviewId)
    public String deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long reviewId = Long.parseLong(request.getParameter("reviewId"));
        selectFoodDAO.deleteSelectFoodByReviewId(reviewId);
        reviewDAO.deleteReview(reviewId);
        return storeDetailPage;
    }
}
