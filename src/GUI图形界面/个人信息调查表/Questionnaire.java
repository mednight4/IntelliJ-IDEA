// Questionnaire.java
package GUI图形界面.个人信息调查表;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class Questionnaire extends Frame implements ActionListener, KeyListener, FocusListener {
    TextField name = new TextField(10); // 姓名，宽度为10
    Checkbox man = new Checkbox("男"), woman = new Checkbox("女");
    CheckboxGroup sex = new CheckboxGroup(); // 性别单选钮组
    TextField age = new TextField("20", 4); // 年龄，初始值为20，文本域宽度为4
    Choice nativeplace = new Choice(); // 籍贯
    Checkbox like1 = new Checkbox("读书"); // 爱好
    Checkbox like2 = new Checkbox("上网");
    Checkbox like3 = new Checkbox("体育活动");
    List website = new List(4); // 喜欢的网站。显示4行
    Button btn1 = new Button("确认"); // 确认、取消和退出按钮
    Button btn2 = new Button("取消");
    Button btn3 = new Button("退出");
    Label l = new Label("个人信息调查表");
    Label l1 = new Label("姓名"), l2 = new Label("性别");
    Label l3 = new Label("年龄"), l4 = new Label("籍贯");
    Label l5 = new Label("爱好"), l6 = new Label("喜欢的网站");

    // 构造方法，用于设置窗体标题、大小，并取消窗体的布局管理器
    public Questionnaire(String title) {
        super(title); // 调用父类构造方法
        this.setSize(400, 400); // 设置窗体的尺寸
        this.setLayout(null); // 取消窗体的布局管理器
        l.setBounds(150, 50, 100, 20);// 个人信息调查表
        l1.setBounds(50, 100, 40, 20);// 姓名标签
        name.setBounds(90, 100, 100, 20);// 姓名文本域
        l2.setBounds(230, 100, 40, 20);// 性别标签
        man.setCheckboxGroup(sex);// 制作单选按钮组
        woman.setCheckboxGroup(sex);
        sex.setSelectedCheckbox(man);
        man.setBounds(270, 100, 60, 20);// 男单选钮
        woman.setBounds(330, 100, 60, 20);// 女单选钮
        l3.setBounds(50, 150, 40, 20);// 年龄标签
        age.setBounds(90, 150, 50, 20);// 年龄文本域
        l4.setBounds(230, 150, 40, 20);// 籍贯标签
        nativeplace.add("北京");// 设置选项框内容
        nativeplace.add("上海");
        nativeplace.add("天津");
        nativeplace.add("重庆");
        nativeplace.add("广东");
        nativeplace.add("河南");
        nativeplace.setBounds(270, 150, 60, 20);// 籍贯选项框
        l5.setBounds(50, 200, 40, 20);// 爱好标签
        like1.setBounds(90, 200, 60, 20);// 读书
        like2.setBounds(150, 200, 60, 20);// 上网
        like3.setBounds(210, 200, 100, 20);// 体育活动
        website.add("新     浪"); // 喜欢的网站
        website.add("搜     狐");
        website.add("网     易");
        website.add("淘     宝");
        website.add("赶集网");
        website.add("新华网");
        l6.setBounds(50, 250, 80, 20);// 喜欢的网站标签
        website.setBounds(130, 250, 100, 60);// 喜欢的网站
        btn1.setBounds(110, 330, 50, 20);// 确认
        btn2.setBounds(180, 330, 50, 20);// 取消
        btn3.setBounds(250, 330, 50, 20);// 退出
        this.add(l);// 将各组件添加到窗体中
        this.add(l1);
        this.add(name);
        this.add(l2);
        this.add(man);
        this.add(woman);
        this.add(l3);
        this.add(age);
        this.add(l4);
        this.add(nativeplace);
        this.add(l5);
        this.add(like1);
        this.add(like2);
        this.add(like3);
        this.add(l6);
        this.add(website);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        setLocationRelativeTo(null); // 使窗体在屏幕上居中放置
        btn1.addActionListener(this); // 为三个按钮注册事件侦听器
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        age.addKeyListener(this); // 为年龄文本域注册键盘事件侦听器
        age.addFocusListener(this); // 为年龄文本域注册焦点事件侦听器
    }

    // 窗体的ActionEvent事件处理方法
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource(); // 获取事件对象
        if (ob == btn3) { // 单击退出按钮
            System.exit(0); // 退出系统
        } else if (ob == btn1) {// 单击确认按钮
            System.out.println("姓名：" + name.getText());
            System.out.println("性别：" + sex.getSelectedCheckbox().getLabel());
            System.out.println("年龄：" + age.getText());
            System.out.println("籍贯：" + nativeplace.getSelectedItem());
            // 如果复选框被选中，则返回其标签，否则将字符串设置为空
            String s1 = like1.getState() ? like1.getLabel() + "  " : "";
            String s2 = like2.getState() ? like2.getLabel() + "  " : "";
            String s3 = like3.getState() ? like3.getLabel() + "  " : "";
            System.out.println("爱好：" + s1 + s2 + s3);
            System.out.println("喜欢的网站：" + website.getSelectedItem());
        } else if (ob == btn2) { // 单击取消按钮
            name.setText(""); // 清空姓名文本域
            sex.setSelectedCheckbox(man); // 选中"男人"单选按钮
            age.setText("20"); // 设置年龄文本域为20
            like1.setState(false); // 取消爱好各复选框
            like2.setState(false);
            like3.setState(false);
            website.deselect(website.getSelectedIndex());// 取消所选喜欢的网站
        }
    }

    // 按下某个键时调用此方法
    public void keyPressed(KeyEvent e) {

    }

    // 释放某个键时调用此方法
    public void keyReleased(KeyEvent e) {
    }

    // 键入某个键时调用此方法
    public void keyTyped(KeyEvent e) {
        // 如果键入的字符是0~9，或者按键是Del键或Backspace键，则
        // 直接返回读入的键盘字符，否则，设置键入的字符为键位未知（0）
        if (((e.getKeyChar() <= 0x39) && (e.getKeyChar() >= 0x30))
                || (e.getKeyChar() == 127) || (e.getKeyChar() == 8)) {
            e.setKeyChar(e.getKeyChar());
        } else {
            e.setKeyChar((char) 0);
        }
    }

    // 年龄文本域获得键盘焦点时调用此方法
    public void focusGained(FocusEvent e) {
    }

    // 年龄文本域失去键盘焦点时调用此方法
    public void focusLost(FocusEvent e) {
        // 将年龄字符串转换为整数
        int i = Integer.parseInt(age.getText());
        // 年龄无效，年龄文本域恢复默认值并重获键盘焦点
        if ((i == 0) || (i >= 200)) {
            // 弹出一个错误提示对话框
            JOptionPane.showMessageDialog(null, "年龄有误，其值应该为1-199！", "错误提示",
                    JOptionPane.ERROR_MESSAGE);
            age.setText("20"); // 恢复年龄默认值
            age.requestFocusInWindow(); // 年龄文本域重获焦点
        }
    }

    public static void main(String[] args) {
        Questionnaire app = new Questionnaire("个人信息");
        app.setVisible(true);
    }
}
