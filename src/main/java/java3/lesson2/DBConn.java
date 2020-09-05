package java3.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class DBConn {

    private static DBConn instance;
    private Connection conn;

    public DBConn() throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        String host = rb.getString("host");
        String port = rb.getString("port");
        String user = rb.getString("user");
        String password = rb.getString("password");
        String db = rb.getString("db");

        String url = "jdbc:mysql://127.0.0.1:3306/test";

        String jdbcURL = MessageFormat.format(
               "jdbc:mysql://{0}:{1}/{2}",
                host, port, db );

        conn = DriverManager.getConnection(jdbcURL, user, password);

    }

    public static DBConn getInstance() {
        if (instance == null) {
            try {
                instance = new DBConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConn () {
        return conn;
    }
}
