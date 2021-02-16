package nancy.servlet;

import nancy.dao.BookDao;
import nancy.pojo.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName BookQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/9 17:35
 * @Version 1.0
 **/

@WebServlet("/book/query")
public class BookQueryServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> list = BookDao.query();
        return list;
    }
}
