package Controller;

import h2.DBConnection;
import model.DAO.*;
import model.Entitiy.Food;
import model.Entitiy.Review;
import model.Entitiy.SelectFood;
import model.Entitiy.Store;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/reviewController")
@MultipartConfig(maxFileSize = 1024*1024*10, location="C:/Users/marty/TIP-REVIEW/TIP-REVIEW/web/images")
public class ReviewPostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StoreDAO storeDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private FoodDAO foodDAO;
    private SelectFoodDAO selectFoodDAO;

    private final String ReviewPostPage = "/reviewPost.jsp";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DBConnection dbConnection = new DBConnection();
        this.storeDAO = new StoreDAO(dbConnection);
        this.reviewDAO = new ReviewDAO(dbConnection);
        this.foodDAO = new FoodDAO(dbConnection);
        this.userDAO = new UserDAO(dbConnection);
        this.selectFoodDAO = new SelectFoodDAO(dbConnection);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if(action.equals("setReview")) {
                setReview(request, response);
            }else if(action.equals("postReview")) {
                postReview(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //setReview
    public void setReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long storeId = Long.valueOf(request.getParameter("storeId"));
        Store store = storeDAO.getStore(storeId);

        Food[] foodList = foodDAO.getFoodList(storeId);
        request.setAttribute("foodList", foodList);

        request.setAttribute("store", store);
        request.getRequestDispatcher(ReviewPostPage).forward(request, response);
    }

    //addSelectFoodList
    public void postReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        System.out.println( "userId = " + userId);

        Long storeId = null;
        String storeIdParam = request.getParameter("storeId");
        if (storeIdParam != null && !storeIdParam.isEmpty()) {
            try {
                storeId = Long.parseLong(storeIdParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // 또는 로깅 등의 적절한 처리
            }
        }
        System.out.println("storeId = " + storeId);

        String reviewContent = request.getParameter("reviewContent");
        System.out.println("reviewContent = " + reviewContent);

        Integer rating = null;
        String ratingParam = request.getParameter("rating");
        if (ratingParam != null && !ratingParam.isEmpty()) {
            try {
                rating = Integer.parseInt(ratingParam);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // 또는 로깅 등의 적절한 처리
            }
        }
        System.out.println("rating = " + rating);
        if(rating == null) {
            rating = 3;
        }


        Part imageFilePart = request.getPart("imageFile");
        String fileName = getFilename(imageFilePart);
        if(fileName != null && !fileName.isEmpty()) {
            imageFilePart.write(fileName);
        }

        String[] selectedFoods = request.getParameterValues("selectedFoods");


        System.out.println("userId = " + userId);
        System.out.println("reviewContent = " + reviewContent);
        System.out.println("rating = " + rating);
        System.out.println("imageFilePart = " + imageFilePart);

        Review review = new Review();
        review.setUserId(userId);
        review.setStoreId(storeId);
        review.setRating(rating);
        review.setContent(reviewContent);
        review.setImage(fileName);

        Long reviewId = reviewDAO.addReview(review);

        for(String food : selectedFoods) {
            System.out.println("food = " + food);
            selectFoodDAO.addSelectFoodList(reviewId, storeId, Long.parseLong(food));
        }
        Store store = storeDAO.getStore(storeId);
        Double chageRating = (store.getRating()*store.getReviewCount() + rating)/(store.getReviewCount()+1);
        storeDAO.updateStoreRatingAndReviewCount(storeId, chageRating);

        //redirect
        response.sendRedirect("/storeDetail?action=getReviewList&storeId=" + storeId);
    }
    private String getFilename(Part part) {
        String fileName = null;

        // Check if the "content-disposition" header is present
        String header = part.getHeader("content-disposition");
        if (header != null) {
            System.out.println("Header => " + header);
            int start = header.indexOf("filename=");
            if (start != -1) {
                fileName = header.substring(start + 10, header.length() - 1);

                // Remove underscores from the file name
                fileName = fileName.replace("_", "");
            }
        }

        return fileName;
    }


    //랜덤 정수 반환
    public int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
