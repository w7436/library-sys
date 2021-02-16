package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.ClassesDao;
import nancy.exception.BusinessException;
import nancy.pojo.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ClassesDeleteServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 2:34
 * @Version 1.0
 **/
@WebServlet("/classes/delete")
public class ClassesDeleteServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");

        int num = ClassesDao.delete(ids);
        if (num != ids.length) {
            throw new BusinessException("0001","删除图书信息异常");
        }
        return null;
    }
}
