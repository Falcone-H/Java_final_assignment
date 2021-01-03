package front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_page extends JFrame implements ActionListener {
    private JLabel account_label;
    private JLabel password_label;
    private JLabel title_label;
    private JTextField account_input;
    private JPasswordField password_input;
    private JButton login_button;
    private JButton register_button;
    private Container container;
    private Font font_normal;
    private Font font_large;

    public login_page() {
        setTitle("用户管理系统");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 居中显示
        setResizable(false);    // 不可调节窗口大小

        // 创建对象
        init();

        // 设置控件
        addComponent();

        setVisible(true);
    }

    public void init() {
        account_label = new JLabel("账号");
        password_label = new JLabel("密码");
        title_label = new JLabel("用户管理系统");
        account_input = new JTextField(20);
        password_input = new JPasswordField(20);
        login_button = new JButton("登录");
        register_button = new JButton("注册");
        font_normal = new Font("宋体", Font.PLAIN, 18);
        font_large = new Font("宋体", Font.BOLD, 35);

        container = getContentPane(); // 获取当前窗口
        container.setLayout(null);  // 设置布局方式为绝对布局

        login_button.addActionListener(this);
        register_button.addActionListener(this);
    }

    public void addComponent() {
        account_label.setLocation(70, 130);
        account_label.setSize(50, 30);
        password_label.setLocation(70, 180);
        password_label.setSize(50, 30);
        title_label.setLocation(95, 10);
        title_label.setSize(300, 150);
        account_input.setLocation(120, 130);
        password_input.setLocation(120, 180);
        account_input.setSize(180, 30);
        password_input.setSize(180, 30);
        login_button.setLocation(120, 240);
        login_button.setSize(80, 40);
        register_button.setLocation(220, 240);
        register_button.setSize(80, 40);

        account_label.setFont(font_normal);
        password_label.setFont(font_normal);
        account_input.setFont(font_normal);
        account_input.setFont(font_normal);
        title_label.setFont(font_large);
        login_button.setFont(font_normal);
        register_button.setFont(font_normal);

        container.add(title_label);
        container.add(account_label);
        container.add(account_input);
        container.add(password_label);
        container.add(password_input);
        container.add(login_button);
        container.add(register_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login_button) {
            loginAction();
        } else if(e.getSource() == register_button) {
            registerAction();
        }
    }

    public void loginAction() {
        this.dispose();
        new main_page();
    }

    public void registerAction() {
        new register_page();
    }
}
