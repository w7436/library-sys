package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Classes;
import nancy.pojo.Student;
import nancy.pojo.User;
import nancy.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName UserDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 19:07
 * @Version 1.0
 **/
public class UserDao {

    public static User query(User user) {
        User u = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select id,username, password,nickname from user where username = ? and password = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                u = user;
                u.setId(rs.getInt("id"));
                u.setNickname(rs.getString("nickname"));
            }
        } catch (SQLException e) {
            throw new SysException("00002",e,"查询用户失败");
        } finally {
            DButil.close(c,ps,rs);
        }
        return u;
    }
    public static List<User>  query() {
        List<User> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select * from user";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               User user = new User();
               user.setId(rs.getInt("id"));
               user.setUsername(rs.getString("username"));
               user.setNickname(rs.getString("nickname"));
               user.setPassword(rs.getString("password"));
               user.setEmail(rs.getString("email"));
               user.setCreateTime(rs.getTimestamp("create_time"));
               list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static int insert(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "insert into user(username, password, nickname, email) values(?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getNickname());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            StringBuilder sql = new StringBuilder("delete from user where id in (");
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
            throw new SysException("0000","删除用户信息出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static int update(User user) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            String sql = "update user set username = ?, password = ?, nickname = ?, " +
                    " email = ?, create_time = ? where id = ?";

            p = c.prepareStatement(sql);
            p.setString(1,user.getUsername());
            p.setString(2,user.getPassword());
            p.setString(3,user.getNickname());
            p.setString(4,user.getEmail());
            p.setTimestamp(5,new Timestamp(user.getCreateTime().getTime()));
            p.setInt(6, user.getId());

            System.out.println(sql);
            int num = p.executeUpdate();
            System.out.println(num);
            return num;

        } catch (SQLException e) {
            throw new SysException("0001","更改图书借阅信息详情出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static User queryById(int id) {
        User user = new User();
        Connection c = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            c = DButil.getConnetion();
            String sql = "select * from user where id = ?";

            p = c.prepareStatement(sql);
            p.setInt(1,id);
            r = p.executeQuery();
            while (r.next()) {
                user.setId(r.getInt("id"));
                user.setUsername(r.getString("username"));
                user.setPassword(r.getString("password"));
                user.setNickname(r.getString("nickname"));
                user.setEmail(r.getString("email"));
                user.setCreateTime(r.getTimestamp("create_time"));

            }
            return user;
        } catch (SQLException e) {
            throw new SysException("0001","查询学生出错");
        } finally {
            DButil.close(c,p);
        }
    }
}
