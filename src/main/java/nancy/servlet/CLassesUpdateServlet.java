package nancy.servlet;

import nancy.dao.BorrowRecordDao;
import nancy.dao.ClassesDao;
import nancy.exception.BusinessException;
import nancy.pojo.BorrowRecord;
import nancy.pojo.Classes;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CLassesUpdateServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 12:45
 * @Version 1.0
 **/
@WebServlet("/classes/update")
public class CLassesUpdateServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Classes classes = JsonUtil.read(req.getInputStream(), Classes.class);
        int num = ClassesDao.update(classes);
        if (num != 1) {
            throw new BusinessException("0008","修改图书借阅信息异常");
        }
        return null;
    }
}
