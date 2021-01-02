package back;
import Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Execute {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private User user = null;

    // 初始化，包括获取连接、创建对象
    public void init() throws SQLException {
        connection = druid_util.getConnection();
        user = new User();
    }

    // 验证密码
    public boolean checkPassword(String account, String password) throws SQLException {
        String sql = "select * from user_info where account = ? and password = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setImg_path(resultSet.getString("img_path"));
            user.setSex(resultSet.getInt("sex"));
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
