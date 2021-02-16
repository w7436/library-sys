package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.BorrowRecordDao;
import nancy.pojo.Book;
import nancy.pojo.BorrowRecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BookQueryByIdServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 2:17
 * @Version 1.0
 **/
@WebServlet("/book/queryById")
public class BookQueryByIdServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = BookDao.queryById(id);
        return book;
    }
}
