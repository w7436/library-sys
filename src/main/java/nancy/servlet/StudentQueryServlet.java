package nancy.servlet;

import nancy.dao.ClassesDao;
import nancy.dao.StudentDao;
import nancy.pojo.Classes;
import nancy.pojo.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName StudentQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 12:55
 * @Version 1.0
 **/
@WebServlet("/student/query")
public class StudentQueryServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Student> list = StudentDao.query();
        return list;
    }
}
