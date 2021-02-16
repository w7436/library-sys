package nancy.servlet;

import nancy.dao.ClassesDao;
import nancy.dao.StudentDao;
import nancy.exception.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName StudentDeleteServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 12:16
 * @Version 1.0
 **/
@WebServlet("/student/delete")
public class StudentDeleteServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");

        int num = StudentDao.delete(ids);
        if (num != ids.length) {
            throw new BusinessException("0001","删除图书信息异常");
        }
        return null;
    }
}
