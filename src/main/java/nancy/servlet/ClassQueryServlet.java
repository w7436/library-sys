package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.ClassesDao;
import nancy.pojo.Book;
import nancy.pojo.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName ClassQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/9 18:48
 * @Version 1.0
 **/
@WebServlet("/classes/query")
public class ClassQueryServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Classes> list = ClassesDao.query();
        return list;
    }
}
