package back;
import Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private User user = null;
    private ResultSet resultSet = null;

    public Executor() throws SQLException {
        connection = druid_util.getConnection();
        user = new User();
    }

    // 验证账号是否存在
    public boolean verifyAccount(String account) throws SQLException {
        String sql = "select * from user_info where account = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    // 验证密码是否正确
    public boolean verifyPassword(String account, String password) throws SQLException {
        String sql = "select * from user_info where account = ? and password = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setSex(resultSet.getInt("sex"));
            // user.setImg_path(resultSet.getString("img_path"));
            return true;
        } else {
            return false;
        }
    }

    // 注册
    public boolean register() {
        return false;
    }
}
