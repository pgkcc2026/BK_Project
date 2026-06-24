package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        if (URL == null || USER == null || PASSWORD == null) {
            throw new RuntimeException("DB環境変数が設定されてない。");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
