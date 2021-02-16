package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.BorrowRecord;
import nancy.pojo.Classes;
import nancy.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassesDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 11:04
 * @Version 1.0
 **/
public class ClassesDao {
    public static List<Classes> queryAsDict() {
        List<Classes> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select id, classes_name, classes_graduate_year, classes_major from classes";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classes classes = new Classes();
                classes.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                classes.setDictionaryTagValue(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesMajor(rs.getString("classes_major"));

                list.add(classes);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }

    public static List<Classes> query() {
        List<Classes> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select id,classes_name, classes_graduate_year, classes_major, classes_major, classes_desc, create_time from classes";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classes classes = new Classes();
                classes.setId(rs.getInt("id"));
                classes.setClassesName(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesDesc(rs.getString("classes_desc"));
                classes.setCreateTime(rs.getTimestamp("create_time"));
                classes.setClassesMajor(rs.getString("classes_major"));
                list.add(classes);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;

    }

    public static int insert(Classes classes) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1,classes.getClassesName());
            ps.setString(2,classes.getClassesGraduateYear());
            ps.setString(3, classes.getClassesMajor());
            ps.setString(4,classes.getClassesDesc());
            int num = ps.executeUpdate();
            return num;
        } catch (SQLException e) {
            throw new SysException("00001",e,"插入班级信息出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static int update(Classes classes) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            String sql = "update classes set classes_name = ?, classes_graduate_year = ?, classes_major= ?, classes_desc = ?,create_time = ? where id = ?";

            p = c.prepareStatement(sql);
            p.setString(1,classes.getClassesName());
            p.setString(2,classes.getClassesGraduateYear());
            p.setString(3,classes.getClassesMajor());
            p.setString(4,classes.getClassesDesc());
            p.setTimestamp(5, (Timestamp) classes.getCreateTime());
            p.setInt(6,classes.getId());

            return p.executeUpdate();
        } catch (SQLException e) {
            throw new SysException("0001","更改图书借阅信息详情出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static Classes queryById(int id) {
        Classes classes = new Classes();
        Connection c = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            c = DButil.getConnetion();
            String sql = "select * from classes where id = ?";

            p = c.prepareStatement(sql);
            p.setInt(1,id);
            r = p.executeQuery();
            while (r.next()) {
                classes.setId(r.getInt("id"));
                classes.setClassesName(r.getString("classes_name"));
                classes.setClassesGraduateYear(r.getString("classes_graduate_year"));
                classes.setClassesMajor(r.getString("classes_major"));
                classes.setClassesDesc(r.getString("classes_desc"));
                classes.setCreateTime(r.getTimestamp("create_time"));

            }
            return classes;
        } catch (SQLException e) {
            throw new SysException("0001","更改图书借阅信息详情出错");
        } finally {
            DButil.close(c,p);
        }


    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            StringBuilder sql = new StringBuilder("delete from classes where id in (");
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
            throw new SysException("0000","删除班级信息出错");
        } finally {
            DButil.close(c,p);
        }
    }
}
