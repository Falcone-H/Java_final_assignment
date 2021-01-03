package front;

import javax.swing.*;
import java.awt.*;

public class register_page extends JFrame {
    private JLabel label;
    private Container container;
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
        label = new JLabel("This is register page");
        container = this.getContentPane();
        container.setLayout(null);
    }

    public void addComponent() {
        label.setLocation(80, 80);
        label.setSize(100, 100);

        container.add(label);
    }
}
