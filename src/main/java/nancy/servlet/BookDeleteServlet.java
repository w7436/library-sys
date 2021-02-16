package nancy.servlet;

import nancy.dao.BookDao;
import nancy.dao.BorrowRecordDao;
import nancy.exception.BusinessException;
import nancy.pojo.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BookDeleteServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/9 17:48
 * @Version 1.0
 **/

//有问题
@WebServlet("/book/delete")
public class BookDeleteServlet extends AbstractServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");

        int num = BookDao.delete(ids);
        if (num != ids.length) {
            throw new BusinessException("0001","删除图书信息异常");
        }
        return null;
    }
}
