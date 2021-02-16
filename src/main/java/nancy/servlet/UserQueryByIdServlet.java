package nancy.servlet;

import nancy.dao.StudentDao;
import nancy.dao.UserDao;
import nancy.pojo.Student;
import nancy.pojo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserQueryByIdServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 12:47
 * @Version 1.0
 **/
@WebServlet("/user/queryById")
public class UserQueryByIdServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int i = Integer.parseInt(req.getParameter("id"));
        User user = UserDao.queryById(i);
        return user;
    }
}
