import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

/**
 * jdbc相关
 * 类似数据库连接类
 */
public class Bean {
    private static final String driverStr = "com.mysql.jdbc.Driver";
    private static final String connStr = "jdbc:mysql://49.232.53.181:44444/hotel";
    private static final String dbusername = "root";
    private static final String dbpassword = "123456";
    private static Connection conn = null;
    private Statement stmt = null;

    public Bean() {
        try {
            Class.forName(driverStr);
            conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("数据连接成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("数据连接失败");
        }

    }

    public int executeUpdate(String s) {
        int result = 0;
        System.out.println("--更新语句:" + s + "\n");
        try {
            result = stmt.executeUpdate(s);
        } catch (Exception ex) {
            System.out.println("执行更新错误！");
        }
        return result;
    }

    public ResultSet executeQuery(String s) {
        ResultSet rs = null;
        System.out.print("--查询语句:" + s + "\n");
        try {
            rs = stmt.executeQuery(s);
            System.out.println("执行查询成功！");
        } catch (Exception ex) {
            System.out.println("执行查询错误！");
        }
        return rs;
    }

    public void execQuery(String s) {
        System.out.print("--插入语句:" + s + "\n");
        try {
            stmt.executeUpdate(s);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("执行插入错误！");
        }
    }


    public static Connection getConnection() throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
            return conn;
        }
        return conn;
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }
}