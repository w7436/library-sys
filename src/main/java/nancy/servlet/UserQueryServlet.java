package nancy.servlet;

import nancy.dao.UserDao;
import nancy.pojo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName UserQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 15:18
 * @Version 1.0
 **/
@WebServlet("/user/query")
public class UserQueryServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> list = UserDao.query();
        return list;
    }
}
