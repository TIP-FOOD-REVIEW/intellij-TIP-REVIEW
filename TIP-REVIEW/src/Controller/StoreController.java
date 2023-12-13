package Controller;

import Bean.StoreBean;
import model.DAO.StoreDAO;
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
    private StoreDAO storedao;
    private ServletContext ctx;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        ctx = getServletContext();

    }

    private StoreBean storeBean = StoreBean.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listStore(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listStore".equals(action)) {
            listStore(request,response);
        } else if ("listByRating".equals(action)) {
            listByRating(request,response);
        } /*else if ("listByReviewCount".equals(action)) {
            listByReviewCount(request, response);
        } */
    }

    private void listStore(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        // StoreBean 객체를 통해 StoreDAO 인스턴스를 얻음
        StoreBean storeBean = StoreBean.getInstance();
        StoreDAO storeDAO = storeBean.getStoreDAO();

            // StoreDAO를 이용하여 가게 목록을 가져옴
            List<Store> storeArray = storeDAO.listStore();
            // 가게 목록을 request에 설정
            request.setAttribute("storeList", storeArray);

        request.getRequestDispatcher("/storeList.jsp").forward(request,response);
    }

    public String getStore(HttpServletRequest req) {
        long id = Integer.parseInt(req.getParameter("storeId"));

        try {
            // StoreDAO 객체를 초기화하고
            StoreDAO storeDAO = new StoreDAO();

            // 올바른 변수명(longId)를 사용하여 가게 정보를 가져옴
            Store store = storeDAO.getStore(id);

            // 가져온 가게 정보를 request에 설정
            req.setAttribute("store", store);
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            ctx.log("가게를 가져오는 과정에서 문제 발생!!");
            req.setAttribute("error", "가게를 정상적으로 가져오지 못했습니다!!");
        }
        return "storeList.jsp";
    }

    private void listByRating(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoreBean storeBean = StoreBean.getInstance();
        StoreDAO storeDAO = storeBean.getStoreDAO();
        try {
            // StoreDAO를 이용하여 가게 목록을 가져옴
            List<Store> storeArray = storeDAO.listByRating();
            // Store[] 배열을 List<Store>로 변환

            // 가게 목록을 request에 설정
            request.setAttribute("storeList", storeArray);

        } catch (Exception e) {
            e.printStackTrace();
            String resultMessage = "Failed to get Storelist.";
            request.setAttribute("resultMessage", resultMessage);
        }
        request.getRequestDispatcher("/storeList.jsp").forward(request, response);
    }
}
