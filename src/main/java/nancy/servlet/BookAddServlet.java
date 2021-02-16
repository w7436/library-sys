package nancy.servlet;

import nancy.dao.BookDao;
import nancy.exception.BusinessException;
import nancy.pojo.Book;
import nancy.util.JsonUtil;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BookAddServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/9 14:55
 * @Version 1.0
 **/
@WebServlet("/book/add")
public class BookAddServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book = JsonUtil.read(req.getInputStream(), Book.class);
        int num = BookDao.insert(book);
        if (num != 1) {
            throw new BusinessException("0001","插入图书信息异常");
        }
        return null;
    }
}
