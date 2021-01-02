package front;

import javax.swing.*;
import java.awt.*;

public class login_page extends JFrame {
    private JLabel account_label;
    private JLabel password_label;
    private JTextField account_input;
    private JPasswordField password_input;
    private JButton login_button;
    private Container container;
    private Font font;

    public login_page() {
        setTitle("用户管理系统");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 居中显示
        setResizable(false);    // 不可调节窗口大小

        // 创建对象
        init();

        // 设置属性
        addComponent();

        setVisible(true);
    }

    public void init() {
        account_label = new JLabel("账号");
        password_label = new JLabel("密码");
        account_input = new JTextField(20);
        password_input = new JPasswordField(20);
        login_button = new JButton("登录");
        font = new Font("宋体", Font.PLAIN, 18);

        container = getContentPane(); // 获取当前窗口
        container.setLayout(null);  // 设置布局方式为绝对布局
    }

    public void addComponent() {
        account_label.setLocation(70, 130);
        account_label.setSize(50, 30);
        password_label.setLocation(70, 180);
        password_label.setSize(50, 30);
        account_input.setLocation(120, 130);
        password_input.setLocation(120, 180);
        account_input.setSize(180, 30);
        password_input.setSize(180, 30);
        login_button.setLocation(160, 230);
        login_button.setSize(80, 40);

        account_label.setFont(font);
        password_label.setFont(font);
        login_button.setFont(font);

        container.add(account_label);
        container.add(account_input);
        container.add(password_label);
        container.add(password_input);
        container.add(login_button);
    }
}
