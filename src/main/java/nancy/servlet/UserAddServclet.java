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
 * @ClassName UserAddServclet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 15:24
 * @Version 1.0
 **/
@WebServlet("/user/register")
public class UserAddServclet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JsonUtil.read(req.getInputStream(), User.class);
        int num = UserDao.insert(user);
        if (num != 1) {
            throw new BusinessException("0001","插入图书信息异常");
        }
        return null;
    }
}
