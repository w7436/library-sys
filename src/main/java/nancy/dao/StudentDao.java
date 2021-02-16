package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Classes;

import nancy.pojo.Student;
import nancy.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:38
 * @Version 1.0
 **/
public class StudentDao {
    public static List<Student> queryAsDict(int key) {
        List<Student> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select s.id,s.student_name,s.id_card,s.student_no" +
                    " from student s join classes c on s.classes_id = c.id" +
                    " where s.classes_id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setDictionaryTagKey(rs.getString("id"));
                student.setDictionaryTagValue(rs.getString("student_name"));
                student.setIdCard(rs.getString("id_card"));
                student.setStudentNo(rs.getString("student_no"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }

    public static List<Student> query() {
        List<Student> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select s.id, s.student_name, " +
                    "       s.student_no, " +
                    "       s.id_card, " +
                    "       c.classes_name, " +
                    "       c.classes_graduate_year, " +
                    "       c.classes_major, " +
                    "       s.student_email, " +
                    "       s.create_time " +
                    "from student s " +
                    "         join classes c on s.classes_id = c.id";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentNo(rs.getString("student_no"));
                student.setIdCard(rs.getString("id_card"));
                student.setStudentEmail(rs.getString("student_email"));
                student.setCreateTime(rs.getTimestamp("create_time"));

                Classes classes = new Classes();
                classes.setClassesName(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesMajor(rs.getString("classes_major"));
                student.setClasses(classes);
                list.add(student);

            }
            return list;
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }


    }

    public static int insert(Student student) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "insert into student(student_id, student_name, student_no, id_card, student_email) values (?,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setInt(1,student.getId());
            ps.setString(2,student.getStudentName());
            ps.setString(3,student.getStudentNo());
            ps.setString(4,student.getIdCard());
            ps.setString(5,student.getStudentEmail());
            int num = ps.executeUpdate();
            return num;
        } catch (SQLException e) {
            throw new SysException("00001",e,"插入学生信息出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            StringBuilder sql = new StringBuilder("delete from student where id in (");
            for(int i = 0;i < ids.length;i++){
                if(i != 0) {
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            p = c.prepareStatement(sql.toString());
            for(int i = 0;i < ids.length;i++){
                p.setInt(i+1,Integer.parseInt(ids[i]));
            }
            return p.executeUpdate();
        } catch (SQLException e) {
            throw new SysException("0000","删除学生信息出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static Student queryById(int id) {
        Student student = new Student();
        Classes classes = new Classes();
        Connection c = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            c = DButil.getConnetion();
            String sql =" select s.id, s.student_name, " +
            "       s.student_no, " +
                    "       s.id_card, " +
                    "       c.classes_name, " +
                    "       c.classes_graduate_year, " +
                    "       c.classes_major, " +
                    "       s.student_email, " +
                    "       s.create_time,s.classes_id " +
                    "from student s " +
                    "         join classes c on s.classes_id = c.id where s.id = ?";

            p = c.prepareStatement(sql);
            p.setInt(1,id);
            r = p.executeQuery();
            while (r.next()) {
                student.setId(r.getInt("id"));
                student.setStudentName(r.getString("student_name"));
                student.setIdCard(r.getString("id_card"));
                student.setStudentNo(r.getString("student_no"));
                student.setStudentEmail(r.getString("student_email"));
                student.setCreateTime(r.getTimestamp("create_time"));

                classes.setId(r.getInt("classes_id"));
                classes.setClassesName(r.getString("classes_name"));
                classes.setClassesMajor(r.getString("classes_major"));
                classes.setClassesGraduateYear(r.getString("classes_graduate_year"));
                student.setClasses(classes);

            }
            return student;
        } catch (SQLException e) {
            throw new SysException("0001","查询学生出错");
        } finally {
            DButil.close(c,p);
        }
        
    }

    public static int update(Student student) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            String sql = "update student set student_name = ?, student_no = ?,id_card = ?," +
                    "student_email = ?, classes_id = ? where id = ?";

            p = c.prepareStatement(sql);
            p.setString(1,student.getStudentName());
            p.setString(2,student.getStudentNo());
            p.setString(3,student.getIdCard());
            p.setString(4,student.getStudentEmail());
            p.setInt(5,student.getClassesId());
            p.setInt(6,student.getId());

            return p.executeUpdate();
        } catch (SQLException e) {
            throw new SysException("0001","更改学生信息出错");
        } finally {
            DButil.close(c,p);
        }
    }
}
