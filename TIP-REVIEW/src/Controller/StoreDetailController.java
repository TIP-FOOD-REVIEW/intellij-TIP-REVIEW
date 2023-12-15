package Controller;

import h2.DBConnection;
import model.DAO.*;
import model.Entitiy.Food;
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
import java.util.*;

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
            if(action.equals("getReviewList")) {
                getReviewList(request, response);
            }else if(action.equals("getReviewListByRatingDESC")) {
                getReviewListByRatingDESC(request, response);
            }else if(action.equals("getReviewListByRatingASC")) {
                getReviewListByRatingASC(request, response);
            }else if(action.equals("search")) {
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

        Food[] foodList = foodDAO.getFoodList(storeId);
        request.setAttribute("foodList", foodList);

        Review[] reviewList = reviewDAO.listReviews(storeId);
        request.setAttribute("reviewList", reviewList);
        //reviewList 출력
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            ArrayList<String> foodnames = new ArrayList<>();
            for(int j = 0; j < foodIdList.length; j++) {
                foodnames.add(foodDAO.getFood(foodIdList[j]).getFoodName());
            }
            request.setAttribute("food_" + reviewList[i].getReviewId(), foodnames);
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }

    //ReviewListByRatingDESC (by storeId)
    public void getReviewListByRatingDESC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Food[] foodList = foodDAO.getFoodList(storeId);
        request.setAttribute("foodList", foodList);
        Review[] reviewList = reviewDAO.listByRating(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            ArrayList<String> foodnames = new ArrayList<>();
            for(int j = 0; j < foodIdList.length; j++) {
                foodnames.add(foodDAO.getFood(foodIdList[j]).getFoodName());
            }
            request.setAttribute("food_" + reviewList[i].getReviewId(), foodnames);
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }


    //ReviewListByRatingASC (by storeId)
    public void getReviewListByRatingASC(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Food[] foodList = foodDAO.getFoodList(storeId);
        request.setAttribute("foodList", foodList);
        Review[] reviewList = reviewDAO.listByRatingAsc(storeId);
        request.setAttribute("reviewList", reviewList);
        for(int i = 0; i < reviewList.length; i++) {
            request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
            Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
            ArrayList<String> foodnames = new ArrayList<>();
            for(int j = 0; j < foodIdList.length; j++) {
                foodnames.add(foodDAO.getFood(foodIdList[j]).getFoodName());
            }
            request.setAttribute("food_" + reviewList[i].getReviewId(), foodnames);
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }

    //ReviewListByStoreIdAndFoodId (by storeId, foodId)
    public void getReviewListByStoreIdAndFoodId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.parseLong(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);
        request.setAttribute("store", store);

        Food[] foodList = foodDAO.getFoodList(storeId);
        request.setAttribute("foodList", foodList);
        String foodname = request.getParameter("name");
        if(foodname == null) {
            Review[] reviewList = reviewDAO.listReviews(storeId);
            request.setAttribute("reviewList", reviewList);
            //reviewList 출력
            for(int i = 0; i < reviewList.length; i++) {
                request.setAttribute("user_" + reviewList[i].getReviewId(), userDAO.getUser(reviewList[i].getUserId()));
                Long[] foodIdList = selectFoodDAO.listByReviewId(reviewList[i].getReviewId());
                ArrayList<String> foodnames = new ArrayList<>();
                for(int j = 0; j < foodIdList.length; j++) {
                    foodnames.add(foodDAO.getFood(foodIdList[j]).getFoodName());
                }
                request.setAttribute("food_" + reviewList[i].getReviewId(), foodnames);
            }
        }else{
            Long[] foodIdList = Arrays.stream(foodDAO.searchFood(foodname, storeId)).map(Food::getFoodId).toArray(Long[]::new);
            Long[] reviewIdList = selectFoodDAO.listByStoreIdAndFoodIds(storeId, foodIdList);
            Review[] reviewList = reviewDAO.listByListReviewId(reviewIdList);
            List<Review> reviewArrayList = Arrays.asList(reviewList);
            Set<Review> uniqueReviews = new HashSet<>(reviewArrayList);
            Review[] uniqueReviewArray = uniqueReviews.toArray(new Review[0]);
            request.setAttribute("reviewList", uniqueReviewArray);
            for (int i = 0; i < uniqueReviewArray.length; i++) {
                request.setAttribute("user_" + uniqueReviewArray[i].getReviewId(), userDAO.getUser(uniqueReviewArray[i].getUserId()));
                Long[] foodIdList1 = selectFoodDAO.listByReviewId(uniqueReviewArray[i].getReviewId());
                ArrayList<String> foodnames = new ArrayList<>();
                for (int j = 0; j < foodIdList1.length; j++) {
                    foodnames.add(foodDAO.getFood(foodIdList1[j]).getFoodName());
                }
                request.setAttribute("food_" + uniqueReviewArray[i].getReviewId(), foodnames);
            }
        }
        request.getRequestDispatcher(storeDetailPage).forward(request, response);
    }
}
