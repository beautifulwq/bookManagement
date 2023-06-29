package sqlManage;

import role.myService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//演示JDBC的查询类操作
public class TestQuery {
    private static String driver_class = "com.mysql.cj.jdbc.Driver"; // 数据库的驱动类
    private static String dbUrl = "jdbc:mysql://localhost:3306/study?serverTimezone=GMT%2B8"; // 数据库的连接地址
    private static String dbUserName = "admin"; // 数据库的用户名
    private static String dbPassword = "wangqi111222"; // 数据库的密码

    static {
        try {
            Class.forName(driver_class); // 加载数据库的驱动（包含初始化动作）
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //自己写一个
    public static ArrayList<myService> showMyQuery(String sql) {
        ArrayList<myService> serviceArrayList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { // 循环遍历结果集里面的所有记录
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int useHour = rs.getInt("usehour");

                int type = rs.getInt("typeid");
                int haveCnt = rs.getInt("havecnt");
                int buyCnt = rs.getInt("buycnt");
                int comment = rs.getInt("comment");
                int price = rs.getInt("price");

                String path = rs.getString("imagepath");
                myService book = new myService(name, id, useHour, type, price, buyCnt, comment, haveCnt, path);
                serviceArrayList.add(book);
                String desc = String.format("序号为%d，品名为%s，现有数量为%d，已购买次数为为%d。", id, name, haveCnt, buyCnt);
            //    System.out.println("当前信息为：" + desc);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceArrayList;
    }

    //check one
    public static void showOneServiceQuery(String sql) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // 循环遍历结果集里面的所有记录
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int useHour = rs.getInt("usehour");

                String typeName = rs.getString("typename");
                int haveCnt = rs.getInt("havecnt");
                int buyCnt = rs.getInt("buycnt");
                int comment = rs.getInt("comment");
                int price = rs.getInt("price");

                String path = rs.getString("imagepath");

                String desc = String.format("序号为%d，品名为%s，类别为%s,现有数量为%d，已购买次数为为%d,价格为%d,图片路径为%s。", id, name, typeName, haveCnt, buyCnt, price, path);
                System.out.println("当前信息为：" + desc);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


}