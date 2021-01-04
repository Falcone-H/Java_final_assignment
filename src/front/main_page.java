package front;

import back.Executor;

import javax.swing.*;
import java.awt.*;

public class main_page extends JFrame {
    private JLabel label;
    private Container container;
    private Executor executor;

    main_page(Executor executor) {
        setTitle("用户管理系统");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);    // 居中显示
        setResizable(false);    // 不可调节窗口大小

        this.executor = executor;

        // 创建对象
        init();

        // 设置控件
        addComponent();

        setVisible(true);
    }

    public void init() {
        label = new JLabel("This is main page");
        container = this.getContentPane();
        container.setLayout(null);
    }

    public void addComponent() {
        label.setLocation(80, 80);
        label.setSize(100, 100);

        container.add(label);
    }
}
