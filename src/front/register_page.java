package front;

import back.Executor;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class register_page extends JFrame {
    private JLabel title_label;
    private JLabel username_label;
    private JLabel account_label;
    private JLabel password_label;
    private JLabel sex_label;
    private JTextField username_input;
    private JTextField account_input;
    private JTextField password_input;
    private JRadioButton male_radioButton;
    private JRadioButton female_radioButton;
    private ButtonGroup buttonGroup;
    private Container container;
    private Font font_normal;
    private Font font_large;
    private Executor executor;

    register_page() {
        setTitle("用户管理系统");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);    // 居中显示
        setResizable(false);    // 不可调节窗口大小

        // 创建对象
        init();

        // 设置控件
        addComponent();

        setVisible(true);
    }

    public void init() {
        title_label = new JLabel("注册");
        username_label = new JLabel("用户名");
        account_label = new JLabel("账号");
        password_label = new JLabel("密码");
        sex_label = new JLabel("性别");
        username_input = new JTextField();
        account_input = new JTextField();
        password_input = new JTextField();
        male_radioButton = new JRadioButton("男");
        female_radioButton = new JRadioButton("女");
        buttonGroup = new ButtonGroup();
        font_normal = new Font("宋体", Font.PLAIN, 18);
        font_large = new Font("宋体", Font.BOLD, 35);

        container = this.getContentPane();
        container.setLayout(null);

        try {
            executor = new Executor();      // 获取数据库执行对象
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addComponent() {
        buttonGroup.add(male_radioButton);
        buttonGroup.add(female_radioButton);
        container.add(title_label);
        container.add(username_label);
        container.add(account_label);
        container.add(password_label);
        container.add(sex_label);
        container.add(username_input);
        container.add(account_input);
        container.add(password_input);
        container.add(male_radioButton);
        container.add(female_radioButton);
    }
}
