package front;

import back.Executor;
import back.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main_page extends JFrame {
    private JLabel tip;
    private JLabel username_title;
    private JLabel account_title;
    private JLabel password_title;
    private JLabel username_text;
    private JLabel password_text;
    private JLabel account_text;
    private JLabel sex_title;
    private JLabel sex_text;
    private JTable user_table;
    private Container container;
    private Executor executor;
    private User user;
    private Font font_normal;
    private Font font_large;
    private Object[] column_name;
    private Object[][] row_data = new Object[100][10];
    private ResultSet resultSet;

    private String username;
    private String account;
    private String password;
    private String sex;
    private int ID;

    private int count = 0;

    main_page(Executor executor) {
        setTitle("用户管理系统");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 居中显示
        setResizable(false);    // 不可调节窗口大小

        this.executor = executor;
        user = executor.getUserInfo();  // 获取信息
        try {
            resultSet = executor.getAllUserInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        column_name = new Object[]{"ID", "用户名", "账号", "密码", "性别"};

        while(true) {
            try {
                if (!resultSet.next()) break;
                ID = resultSet.getInt("id");
                username = resultSet.getString("name");
                account = resultSet.getString("account");
                password = resultSet.getString("password");
                if(resultSet.getInt("sex") == 0)
                    sex = "男";
                else
                    sex = "女";
                row_data[count] = new Object[]{ID, username, account, password, sex};
                count++;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        // 创建对象
        init();

        // 设置控件
        addComponent();

        setVisible(true);
    }

    public void init() {
        tip = new JLabel("当前用户");
        username_title = new JLabel("用户名");
        account_title = new JLabel("账号");
        password_title = new JLabel("密码");
        sex_title = new JLabel("性别");
        username_text = new JLabel(user.getName());
        account_text = new JLabel(user.getAccount());
        password_text = new JLabel(user.getPassword());
        if(user.getSex() == 0)
            sex_text = new JLabel("男");
        else
            sex_text = new JLabel("女");

        user_table = new JTable(row_data, column_name);
        font_normal = new Font("宋体", Font.PLAIN, 18);
        font_large = new Font("宋体", Font.BOLD, 35);
        container = this.getContentPane();
        container.setLayout(null);
    }

    public void addComponent() {
        tip.setLocation(20, 270);
        tip.setSize(80, 40);
        username_title.setLocation(20, 300);
        username_title.setSize(80, 40);
        username_text.setLocation(100, 300);
        username_text.setSize(80, 40);
        account_title.setLocation(20, 330);
        account_title.setSize(80, 40);
        account_text.setLocation(100, 330);
        account_text.setSize(80, 40);
        password_title.setLocation(20, 360);
        password_title.setSize(80, 40);
        password_text.setLocation(100, 360);
        password_text.setSize(80, 40);
        sex_title.setLocation(20, 390);
        sex_title.setSize(80, 40);
        sex_text.setLocation(100, 390);
        sex_text.setSize(80, 40);

        user_table.getTableHeader().setLocation(200, 50);
        user_table.getTableHeader().setSize(700, 30);
        user_table.setLocation(200, 80);
        user_table.setSize(700, 600);

        username_title.setFont(font_normal);
        account_title.setFont(font_normal);
        password_title.setFont(font_normal);
        sex_title.setFont(font_normal);
        username_text.setFont(font_normal);
        account_text.setFont(font_normal);
        password_text.setFont(font_normal);
        sex_text.setFont(font_normal);
        user_table.setFont(font_normal);
        user_table.getTableHeader().setFont(font_normal);
        user_table.setRowHeight(30);
        tip.setFont(font_normal);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        user_table.setDefaultRenderer(Object.class, renderer);  // 设置表格内容居中

        container.add(tip);
        container.add(username_title);
        container.add(username_text);
        container.add(account_title);
        container.add(account_text);
        container.add(password_title);
        container.add(password_text);
        container.add(sex_title);
        container.add(sex_text);
        container.add(user_table.getTableHeader());
        container.add(user_table);
    }
}
