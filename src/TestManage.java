
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//演示JDBC的变更类操作（含建表、改表结构、删表、增加记录、修改记录、删除记录）
public class TestManage {
    private static String driver_class = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8";
    private static String dbUserName = "admin";
    private static String dbPassword = "wangqi111222";

    static {
        try {
            Class.forName(driver_class); // 加载数据库的驱动（包含初始化动作）
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
             Statement stmt = conn.createStatement()) {
//			dropTable(stmt,"mybook"); // 删除表格ok
//            createTable(stmt,); // 创建表格ok
//			alterTable(stmt); // 修改表结构 ok
//			insertRecord(stmt); // 插入记录ok
//			updateRecord(stmt); // 更新记录ok
//			deleteRecord(stmt); // 删除记录ok
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 创建表格
    private static void createTable(Statement stmt, String sqlOrder) throws SQLException {
        //执行的sql语句
        int count = stmt.executeUpdate(sqlOrder);// 执行处理语句
        System.out.println("建表语句的返回结果为" + count);
        if (count == 0) System.out.println("建表成功");
    }

    // 修改表结构
    private static void alterTable(Statement stmt, ArrayList<String> sqlList) throws SQLException {

        for (String sql : sqlList) {
            int count = stmt.executeUpdate(sql); // 执行处理语句
            System.out.println("表结构修改语句的返回结果为" + count);
        }
    }

    // 删除表格
    private static void dropTable(Statement stmt, String tableName) throws SQLException {
        String sql = "drop table " + tableName; // 删表语句
        int count = stmt.executeUpdate(sql); // 执行处理语句
        System.out.println("删表语句的返回结果为" + count);
    }

    // 插入记录
    private static void insertRecord(Statement stmt, ArrayList<String> sqlList1) throws SQLException {

        for (String sql : sqlList1) {
            int count = stmt.executeUpdate(sql); // 执行处理语句
            System.out.println("添加记录语句的返回结果为" + count);
        }
    }

    // 更新记录
    public static void updateRecord(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
             Statement stmt = conn.createStatement()) {
            int count = stmt.executeUpdate(sql); // 执行处理语句。返回被更新的记录数量}
            System.out.println("更新记录语句的返回结果为" + count);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除记录
    private static void deleteRecord(Statement stmt, String sql) throws SQLException {
        int count = stmt.executeUpdate(sql); // 执行处理语句。返回被删除的记录数量
        System.out.println("删除记录语句的返回结果为" + count);
    }

}