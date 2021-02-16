package nancy.servlet;

import nancy.dao.StudentDao;
import nancy.pojo.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName StudentQueryByIdServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/11 12:20
 * @Version 1.0
 **/
@WebServlet("/student/queryById")
public class StudentQueryByIdServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int i = Integer.parseInt(req.getParameter("id"));
        Student student = StudentDao.queryById(i);
        return student;
    }
}
