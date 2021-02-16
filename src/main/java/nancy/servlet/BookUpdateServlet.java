package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.BorrowRecordDao;
import nancy.exception.BusinessException;
import nancy.pojo.Book;
import nancy.pojo.BorrowRecord;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BookUpdateServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 21:38
 * @Version 1.0
 **/
@WebServlet("/book/update")
public class BookUpdateServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = JsonUtil.read(req.getInputStream(), Book.class);
        int num = BookDao.update(book);
        if (num != 1) {
            throw new BusinessException("0008","修改图书信息异常");
        }
        return null;
    }
}
