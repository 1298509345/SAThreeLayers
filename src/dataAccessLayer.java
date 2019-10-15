package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据访问层，被业务逻辑层调用
 * */
public class dataAccessLayer {
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//这个不改，都一样
    private static String user = "sa";//用户名
    private static String pwd = "sygvbn1908";//密码
    private static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=pubs";//数据库连接。格式：数据库类型+IP+端口+名字

    public static Connection getLink() {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public static void Close(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static void Close(Connection conn, CallableStatement call) {
        try {
            conn.close();
            call.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
