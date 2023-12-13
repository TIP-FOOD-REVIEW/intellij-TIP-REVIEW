package Bean;

import h2.DBConnection;
import model.DAO.UserDAO;

public class UserBean {
    private static UserDAO userDAO;
    private static UserBean instance;

    private UserBean() {
        DBConnection dbConnection = new DBConnection(); // DBConnection에 기본 생성자가 있다고 가정합니다.
        userDAO = new UserDAO(dbConnection);
    }

    public static synchronized UserBean getInstance() {
        if (instance == null) {
            instance = new UserBean();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    // 필요에 따라 UserBean과 관련된 다른 메서드를 추가할 수 있습니다.
}