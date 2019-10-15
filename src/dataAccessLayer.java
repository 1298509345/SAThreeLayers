package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݷ��ʲ㣬��ҵ���߼������
 * */
public class dataAccessLayer {
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//������ģ���һ��
    private static String user = "sa";//�û���
    private static String pwd = "sygvbn1908";//����
    private static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=pubs";//���ݿ����ӡ���ʽ�����ݿ�����+IP+�˿�+����

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
