package nancy.servlet;

import nancy.dao.ClassesDao;
import nancy.dao.StudentDao;
import nancy.exception.BusinessException;
import nancy.pojo.Classes;
import nancy.pojo.Student;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName StudentAddServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 14:45
 * @Version 1.0
 **/
@WebServlet("/student/add")
public class StudentAddServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Student student = JsonUtil.read(req.getInputStream(), Student.class);
        int num = StudentDao.insert(student);
        if (num != 1) {
            throw new BusinessException("0001","插入图书信息异常");
        }
        return null;
    }
}
