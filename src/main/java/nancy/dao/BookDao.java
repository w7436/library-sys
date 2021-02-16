package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Book;
import nancy.util.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookAsDictDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:03
 * @Version 1.0
 **/

public class BookDao {
    public static List<Book> queryAsDict() {
        List<Book> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select  id, book_name,author,price from book";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                book.setDictionaryTagValue(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                list.add(book);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询图书数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }

    public static int insert(Book book){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "insert into book(book_name,author,price) values (?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1,book.getBookName());
            ps.setString(2,book.getAuthor());
            ps.setBigDecimal(3, book.getPrice());
            int num = ps.executeUpdate();
            return num;
        } catch (SQLException e) {
            throw new SysException("00001",e,"插入图书信息出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static List<Book> query() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> list = new ArrayList<>();
        try {
            c = DButil.getConnetion();
            String sql = "select * from book";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setCreateTime(rs.getTimestamp("create_time"));
                book.setPrice(rs.getBigDecimal("price"));
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询图书信息出错");
        } finally {
            DButil.close(c,ps,rs);
        }
    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            StringBuilder sql = new StringBuilder("delete from book where id in (");
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
            throw new SysException("0000","删除图书信息出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static int update(Book book) {
        Connection c = null;
        PreparedStatement p = null;
        try {
            c = DButil.getConnetion();
            String sql = "update book set book_name = ?, author = ?, price = ?,create_time = ?  where id = ?";

            p = c.prepareStatement(sql);
            p.setString(1,book.getBookName());
            p.setString(2,book.getAuthor());
            p.setBigDecimal(3,book.getPrice());
            p.setTimestamp(4, (Timestamp) book.getCreateTime());
            p.setInt(5,book.getId());
            return p.executeUpdate();
        } catch (SQLException e) {
            throw new SysException("0001","更改图书借阅信息详情出错");
        } finally {
            DButil.close(c,p);
        }
    }

    public static Book queryById(int id) {
        Connection c = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        Book book = new Book();
        try {
            c = DButil.getConnetion();
            String sql = "select * from book where id = ?";

            p = c.prepareStatement(sql);
            p.setInt(1,id);

            rs = p.executeQuery();
            while (rs.next()) {
                book.setId(id);
                book.setBookName(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setCreateTime(rs.getTimestamp("create_time"));
            }
            return book;
        } catch (SQLException e) {
            throw new SysException("0001","更改图书借阅信息详情出错");
        } finally {
            DButil.close(c,p);
        }
    }
}
