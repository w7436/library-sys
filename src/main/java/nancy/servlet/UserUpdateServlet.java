package nancy.servlet;

import nancy.dao.StudentDao;
import nancy.dao.UserDao;
import nancy.exception.BusinessException;
import nancy.pojo.Student;
import nancy.pojo.User;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserUpdateServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 12:48
 * @Version 1.0
 **/
@WebServlet("/user/update")
public class UserUpdateServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JsonUtil.read(req.getInputStream(), User.class);
        int num = UserDao.update(user);
        if (num != 1) {
            throw new BusinessException("0008","修改图书借阅信息异常");
        }
        return null;
    }
}
