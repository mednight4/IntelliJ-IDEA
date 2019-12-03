package 选择框打勾;
// TextFieldTest.java
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserLogin {
    Frame app = new Frame("TextField 组件");
    Label lblName = new Label("UserName:");
    static TextField txtName = new TextField();
    Label lblPass = new Label("Password:");
    static TextField txtPass = new TextField();

    public UserLogin() {
        app.setSize(300, 150); // 设置窗体尺寸
        app.setLayout(null); // 取消窗体的布局管理器
        /* 设置姓名栏对应的标签与文本域的位置与大小 */
        lblName.setBounds(60, 50, 70, 20);
        txtName.setBounds(135, 50, 100, 20);
        // 为文本框添加事件侦听器
        txtName.addKeyListener(new keyHandler());
        txtName.addFocusListener(new focusHandler());
        /* 设置密码栏对应的标签与文本域的位置与大小 */
        lblPass.setBounds(60, 90, 70, 20);
        txtPass.setBounds(135, 90, 100, 20);
        txtPass.setEchoChar('*'); // 设置密码框文本域的回显字符
        // 为密码框添加事件侦听器
        txtPass.addKeyListener(new keyHandler());
        txtPass.addFocusListener(new focusHandler());
        /* 将组件添加到窗体容器内 */
        app.add(lblName);
        app.add(txtName);
        app.add(lblPass);
        app.add(txtPass);
        /* 设置窗体的位置与可见性 */
        app.setLocation(200, 100);
        app.setVisible(true);
    }

    public static void main(String[] args) {
        // 创建对象
        UserLogin tft = new UserLogin();
    }
}

// 定义实现keyListener接口的keyEvent事件处理类
class keyHandler implements KeyListener {
    // 按下某个键时调用此方法
    public void keyPressed(KeyEvent e) {
        // 获取事件对象
        Object ob = e.getSource();
        // 如果事件对象为txtName，并且按下回车键，则在控制台中
        // 显示输入的用户名
        if ((ob == UserLogin.txtName) && (e.getKeyCode() == 10)) {
            System.out.println(UserLogin.txtName.getText());
        }
        // 如果事件对象为txtPass，并且按下回车键，则在控制台中显示输入的密码
        else if ((ob == UserLogin.txtPass) && (e.getKeyCode() == 10)) {
            System.out.println(UserLogin.txtPass.getText());
        }
    }

    // 释放某个键时调用此方法
    public void keyReleased(KeyEvent e) {
    }

    // 键入某个键时调用此方法
    public void keyTyped(KeyEvent e) {
    }
}

// 定义实现FocusListener接口的FocusEvent事件处理类
class focusHandler implements FocusListener {
    // 获取键盘焦点
    public void focusGained(FocusEvent e) {
    }

    // 失去键盘焦点
    public void focusLost(FocusEvent e) {
        // 获取事件对象
        Object ob = e.getSource();
        // 如果事件对象为txtName，则在控制台中显示输入的用户名
        if (ob == UserLogin.txtName) {
            System.out.println(UserLogin.txtName.getText());
        }
        // 如果事件对象为txtPass，则在控制台中显示输入的密码
        else if (ob == UserLogin.txtPass) {
            System.out.println(UserLogin.txtPass.getText());
        }
    }
}
