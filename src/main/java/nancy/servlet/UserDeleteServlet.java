package nancy.servlet;

import nancy.dao.StudentDao;
import nancy.dao.UserDao;
import nancy.exception.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserDeleteServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 12:44
 * @Version 1.0
 **/
@WebServlet("/user/delete")
public class UserDeleteServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");

        int num = UserDao.delete(ids);
        if (num != ids.length) {
            throw new BusinessException("0001","删除图书信息异常");
        }
        return null;
    }
}
