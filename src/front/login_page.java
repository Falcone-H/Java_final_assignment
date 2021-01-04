package front;

import back.Executor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class login_page extends JFrame implements ActionListener {
    private JLabel account_label;
    private JLabel password_label;
    private JLabel title_label;
    private JTextField account_input;
    private JTextField password_input;
    private JButton login_button;
    private JButton register_button;
    private Container container;
    private Font font_normal;
    private Font font_large;
    private Executor executor;

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
        account_input = new JTextField(6);
        password_input = new JTextField(6);
        login_button = new JButton("登录");
        register_button = new JButton("注册");
        font_normal = new Font("宋体", Font.PLAIN, 18);
        font_large = new Font("宋体", Font.BOLD, 35);

        container = getContentPane(); // 获取当前窗口
        container.setLayout(null);  // 设置布局方式为绝对布局

        login_button.addActionListener(this);
        register_button.addActionListener(this);

        try {
            executor = new Executor();      // 获取数据库执行对象
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        password_input.setFont(font_normal);
        title_label.setFont(font_large);
        login_button.setFont(font_normal);
        register_button.setFont(font_normal);

        account_input.addFocusListener(new FocusListener() {
            String hint_text = "请输入6位的账号";
            @Override
            public void focusGained(FocusEvent e) {
                if(account_input.getText().equals(hint_text))
                    account_input.setText("");
                account_input.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(account_input.getText().equals(""))
                    account_input.setText("请输入6位的账号");
                account_input.setForeground(Color.GRAY);
            }
        });
        password_input.addFocusListener(new FocusListener() {
            String hint_text = "请输入6位的密码";
            @Override
            public void focusGained(FocusEvent e) {
                if(password_input.getText().equals(hint_text))
                    password_input.setText("");
                password_input.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(password_input.getText().equals(""))
                    password_input.setText("请输入6位的密码");
                password_input.setForeground(Color.GRAY);
            }
        });
        password_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if((char)e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        loginAction();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        container.add(title_label);
        container.add(account_label);
        container.add(account_input);
        container.add(password_label);
        container.add(password_input);
        container.add(login_button);
        container.add(register_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {    // 添加按钮动作
        if(e.getSource() == login_button) {
            try {
                loginAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if(e.getSource() == register_button) {
            registerAction();
        }
    }

    public void loginAction() throws SQLException {
        String account = account_input.getText();
        String password = password_input.getText();
        if(account.length() != 6) {     // 账号不为六位
            JOptionPane.showMessageDialog(this, "请输入长度为六位的账号",
                    "警告", JOptionPane.WARNING_MESSAGE);
            account_input.setText("");
            password_input.setText("");
            return;
        }
        if(password.length() != 6) {    // 密码不为六位
            JOptionPane.showMessageDialog(this, "请输入长度为六位的密码",
                    "警告", JOptionPane.WARNING_MESSAGE);
            password_input.setText("");
            return;
        }
        if(!verifyAccount(account)) {    // 该账号不存在
            JOptionPane.showMessageDialog(this, "该账号不存在，请先注册",
                    "警告", JOptionPane.WARNING_MESSAGE);
            account_input.setText("");
            password_input.setText("");
            return;
        }
        if(!verifyPassword(account, password)) { // 如果密码不正确
            JOptionPane.showMessageDialog(this, "密码有误，请重新输入",
                    "警告", JOptionPane.WARNING_MESSAGE);
            password_input.setText("");
            return;
        }
        this.dispose();
        new main_page(executor);    // 如果验证都通过，则打开主页面
    }

    public void registerAction() {
        new register_page();
    }

    // 验证账号是否存在
    public boolean verifyAccount(String account) throws SQLException {
        return executor.verifyAccount(account);
    }

    // 验证密码是否正确
    public boolean verifyPassword(String account, String password) throws SQLException {
        return executor.verifyPassword(account, password);
    }

}
