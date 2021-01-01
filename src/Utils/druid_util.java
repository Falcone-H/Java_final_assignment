package Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class druid_util {
    private static DataSource dataSource;

    // 加载配置文件
    static {
        Properties properties = new Properties();
        InputStream inputStream = druid_util.class.getClassLoader().getResourceAsStream("Utils/druid.properties");  // 路径从src开始
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void close(Statement statement, Connection connection, ResultSet resultSet) throws SQLException {
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
        if(resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Statement statement, Connection connection) throws SQLException {
        close(statement, connection, null);
    }
}
