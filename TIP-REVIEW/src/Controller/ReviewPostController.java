package Controller;

import h2.DBConnection;
import model.DAO.*;
import model.Entitiy.Food;
import model.Entitiy.Review;
import model.Entitiy.Store;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/reviewController")
@MultipartConfig(maxFileSize = 1024*1024*2, location="/Users/sangwon/Desktop/tip-food/intellij-TIP-REVIEW/TIP-REVIEW/web/images")
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

        Long storeId = Long.valueOf(request.getParameter("storeId"));
        System.out.println("storeId = " + storeId);

        String reviewContent = request.getParameter("reviewContent");
        System.out.println("reviewContent = " + reviewContent);

        Integer rating = Integer.parseInt(request.getParameter("rating"));
        System.out.println("rating = " + rating);



        Part imageFilePart = request.getPart("imageFile");
        String fileName = getFilename(imageFilePart);
        if(fileName != null && !fileName.isEmpty()) {
            imageFilePart.write(fileName);
        }

        String[] selectedFoods = request.getParameterValues("selectedFoods");


        System.out.println("userId = " + userId);
        //System.out.println("storeId = " + storeId);
        System.out.println("reviewContent = " + reviewContent);
        System.out.println("rating = " + rating);
        System.out.println("imageFilePart = " + imageFilePart);
        System.out.println("selectedFoods = " + selectedFoods);

        Review review = new Review();
        review.setUserId(userId);
        review.setStoreId(storeId);
        review.setRating(rating);
        review.setContent(reviewContent);
        review.setImage(fileName);

        reviewDAO.addReview(review);
        //redirect
        response.sendRedirect("/storeDetail?action=getReviewList&storeId=" + storeId);

// This will be an array of food IDs that were checked


//        Long storeId = Long.valueOf(request.getParameter("storeId"));
//        Store store = storeDAO.getStore(storeId);
//
//        //session에서 userId 받아오기
//        HttpSession session = request.getSession();
//        Long userId = (Long) session.getAttribute("userId");
//
//        String content = request.getParameter("content");
//        String ratingParam = request.getParameter("rating");
//        Integer rating = (ratingParam != null) ? Integer.valueOf(ratingParam) : 0; // 또는 다른 디폴트 값
//        Review review = new Review();
//        review.setContent(content);
//        review.setRating(rating);
//        review.setStoreId(storeId);
//        review.setUserId(userId);
//        //이미지 주소 받아오는 부분으로 교체 필요
//        review.setImage("12345");
//        reviewDAO.addReview(review);
//
//        String[] selectFoodList = request.getParameterValues("selectFoodList");
////        for (String selectFood : selectFoodList) {
////            //selectFoodDAO.addSelectFoodList(userId, storeId, selectFood);
////        }
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

            }
        }

        return fileName;
    }
}
