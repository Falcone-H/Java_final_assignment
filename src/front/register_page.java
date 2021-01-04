package front;

import back.Executor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class register_page extends JFrame implements ActionListener {
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
    private JButton submit_button;
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
        male_radioButton = new JRadioButton("男", true);
        female_radioButton = new JRadioButton("女");
        buttonGroup = new ButtonGroup();
        submit_button = new JButton("提交");
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
        title_label.setLocation(200, 30);
        title_label.setSize(200, 100);
        username_label.setLocation(90, 130);
        username_label.setSize(80, 30);
        username_input.setLocation(160, 130);
        username_input.setSize(200, 30);
        account_label.setLocation(100, 190);
        account_label.setSize(80, 30);
        account_input.setLocation(160, 190);
        account_input.setSize(200, 30);
        password_label.setLocation(100, 250);
        password_label.setSize(80, 30);
        password_input.setLocation(160, 250);
        password_input.setSize(200, 30);
        sex_label.setLocation(100, 300);
        sex_label.setSize(80, 30);
        male_radioButton.setLocation(190, 300);
        male_radioButton.setSize(80, 30);
        female_radioButton.setLocation(270, 300);
        female_radioButton.setSize(80, 30);
        submit_button.setLocation(210, 360);
        submit_button.setSize(80, 40);

        title_label.setFont(font_large);
        username_label.setFont(font_normal);
        account_label.setFont(font_normal);
        password_label.setFont(font_normal);
        sex_label.setFont(font_normal);
        username_input.setFont(font_normal);
        account_input.setFont(font_normal);
        password_input.setFont(font_normal);
        male_radioButton.setFont(font_normal);
        female_radioButton.setFont(font_normal);
        submit_button.setFont(font_normal);

        username_input.addFocusListener(new FocusListener() {    // 用户名框获取焦点和失去焦点时的动作
            String hint_text = "请输入您的用户名";

            @Override
            public void focusGained(FocusEvent e) {
                if (username_input.getText().equals(hint_text))
                    username_input.setText("");
                username_input.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username_input.getText().equals(""))
                    username_input.setText("请输入您的用户名");
                username_input.setForeground(Color.GRAY);
            }
        });
        account_input.addFocusListener(new FocusListener() {    // 账号框获取焦点和失去焦点时的动作
            String hint_text = "请输入6位的账号";

            @Override
            public void focusGained(FocusEvent e) {
                if (account_input.getText().equals(hint_text))
                    account_input.setText("");
                account_input.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (account_input.getText().equals(""))
                    account_input.setText("请输入6位的账号");
                account_input.setForeground(Color.GRAY);
            }
        });
        password_input.addFocusListener(new FocusListener() {    // 密码框获取焦点和失去焦点时的动作
            String hint_text = "请输入6位的密码";

            @Override
            public void focusGained(FocusEvent e) {
                if (password_input.getText().equals(hint_text))
                    password_input.setText("");
                password_input.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (password_input.getText().equals(""))
                    password_input.setText("请输入6位的密码");
                password_input.setForeground(Color.GRAY);
            }
        });

        submit_button.addActionListener(this);

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
        container.add(submit_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit_button) {
            try {
                submitAction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void submitAction() throws SQLException {
        String username = username_input.getText();
        String account = account_input.getText();
        String password = password_input.getText();
        int sex = 0;
        if (male_radioButton.isSelected())   // 如果选择了男性，则sex为0；如果选择了女性，则sex为1
            sex = 0;
        else
            sex = 1;
        if (username.equals("")) {
            JOptionPane.showMessageDialog(this, "用户名不能为空",
                    "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (account.length() != 6) {
            JOptionPane.showMessageDialog(this, "请输入长度为六位的账号",
                    "警告", JOptionPane.WARNING_MESSAGE);
            account_input.setText("");
            return;
        }
        if (password.length() != 6) {
            JOptionPane.showMessageDialog(this, "请输入长度为六位的密码",
                    "警告", JOptionPane.WARNING_MESSAGE);
            password_input.setText("");
            return;
        }
        if (executor.verifyAccount(account)) {
            JOptionPane.showMessageDialog(this, "该账号已存在，请直接登录",
                    "警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(executor.register(username, account, password, sex)) {
            JOptionPane.showMessageDialog(null, "注册成功",
                    "提示", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "注册失败，请联系开发者",
                    "警告", JOptionPane.WARNING_MESSAGE);
        }
        return;
    }
}
