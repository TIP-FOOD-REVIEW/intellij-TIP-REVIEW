package Controller;

import h2.DBConnection;
import model.DAO.*;
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
    private FoodDAO foodDAO;
    private SelectFoodDAO selectFoodDAO;

    private final String storeDetailPage = "/storeDetail.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
        this.reviewDAO = new ReviewDAO(dbConnection);
        this.foodDAO = new FoodDAO(dbConnection);
        this.userDAO = new UserDAO(dbConnection);
        this.selectFoodDAO = new SelectFoodDAO(dbConnection);
    }

    //service
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if(action == null) {
                getReviewList(request, response);
            }else if(action.equals("getReviewListByRatingDESC")) {
                getReviewListByRatingDESC(request, response);
            }else if(action.equals("getReviewListByRatingASC")) {
                getReviewListByRatingASC(request, response);
            }else if(action.equals("getReviewListByStoreIdAndFoodId")) {
                getReviewListByStoreIdAndFoodId(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //ReviewList (by storeId)
    public void getReviewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Review[] reviewList = reviewDAO.listReviews(storeId);
        request.setAttribute("reviewList", reviewList);
        //reviewList 출력
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            for(int j = 0; j < foodIdList.length && foodIdList[j]!=null; j++) {
                request.setAttribute("food_" + reviewList[i].getReviewId() + "_" + foodIdList[j], foodDAO.getFood(foodIdList[j]));
            }
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }

    //ReviewListByRatingDESC (by storeId)
    public void getReviewListByRatingDESC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Review[] reviewList = reviewDAO.listByRating(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            for(int j = 0; j < foodIdList.length && foodIdList[j]!=null; j++) {
                request.setAttribute("food_" + reviewList[i].getReviewId() + "_" + foodIdList[j], foodDAO.getFood(foodIdList[j]));
            }
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }


    //ReviewListByRatingASC (by storeId)
    public void getReviewListByRatingASC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Review[] reviewList = reviewDAO.listByRatingAsc(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            for(int j = 0; j < foodIdList.length && foodIdList[j]!=null; j++) {
                request.setAttribute("food_" + reviewList[i].getReviewId() + "_" + foodIdList[j], foodDAO.getFood(foodIdList[j]));
            }
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }

    //ReviewListByStoreIdAndFoodId (by storeId, foodId)
    public void getReviewListByStoreIdAndFoodId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);
        Long foodId = Long.parseLong(request.getParameter("foodId"));
        Long[] reviewIdList = selectFoodDAO.listByStoreIdAndFoodId(storeId, foodId);
        Review[] reviewList = reviewDAO.listByListReviewId(reviewIdList);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            for(int j = 0; j < foodIdList.length && foodIdList[j]!=null; j++) {
                request.setAttribute("food_" + reviewList[i].getReviewId() + "_" + foodIdList[j], foodDAO.getFood(foodIdList[j]));
            }
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }
}
