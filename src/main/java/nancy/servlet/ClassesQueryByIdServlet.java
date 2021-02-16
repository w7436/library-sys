package nancy.servlet;


import nancy.dao.ClassesDao;
import nancy.pojo.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ClassesQueryByIdServlet
 * @Description TODO
 * @Author nancy
 * @Date 2021/2/10 12:32
 * @Version 1.0
 **/
@WebServlet("/classes/queryById")
public class ClassesQueryByIdServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        Classes classes = ClassesDao.queryById(id);
        return classes;
    }
}
