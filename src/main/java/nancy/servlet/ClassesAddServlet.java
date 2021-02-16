package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.ClassesDao;
import nancy.exception.BusinessException;
import nancy.pojo.Book;
import nancy.pojo.Classes;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ClassesAddServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 12:24
 * @Version 1.0
 **/
@WebServlet("/classes/add")
public class ClassesAddServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Classes classes = JsonUtil.read(req.getInputStream(), Classes.class);
        int num = ClassesDao.insert(classes);
        if (num != 1) {
            throw new BusinessException("0001","插入图书信息异常");
        }
        return null;
    }
}
