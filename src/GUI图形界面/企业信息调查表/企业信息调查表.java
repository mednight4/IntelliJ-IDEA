package GUI图形界面.企业信息调查表;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
public class 企业信息调查表 extends Frame implements ActionListener {
    TextField name = new TextField(30); // 企业名称，宽度为30
    TextField funds = new TextField(30);// 注册资金
    // 员工数量，初始值为50，文本域宽度为4
    TextField employeeCount = new TextField("50", 4);
    Choice industry = new Choice(); // 从事行业
    TextField turnover = new TextField(30);// 年营业额
    TextField margin = new TextField(30);// 利润率
    Button btn1 = new Button("确认"); // 确认、取消和退出按钮
    Button btn2 = new Button("取消");
    Button btn3 = new Button("退出");
    Label l = new Label("企业信息调查表");
    Label l1 = new Label("企业名称"), l2 = new Label("注册资金");
    Label l3 = new Label("员工数量"), l4 = new Label("从事行业");
    Label l5 = new Label("年营业额"), l6 = new Label("利润率");
    // 构造方法，用于设置窗体标题、大小，并取消窗体的布局管理器
    public 企业信息调查表(String title) {
        super(title); // 调用父类构造方法
        GUI图形界面.企业信息调查表.CloseHandler handler=new GUI图形界面.企业信息调查表.CloseHandler();//定义窗体事件的侦听器对象
        this.addWindowListener(handler); //为当前窗体注册侦听器对象
        this.setSize(400, 400); // 设置窗体的尺寸
        this.setLayout(null); // 取消窗体的布局管理器
        l.setBounds(150, 50, 100, 20);// 企业信息调查表
        l1.setBounds(50, 100, 60, 20);// 企业名称标签
        name.setBounds(110, 100, 150, 20);// 企业名称文本域
        l2.setBounds(230, 200, 60, 20);// 注册资金标签
        funds.setBounds(290, 200, 80, 20);// 注册资金
        l3.setBounds(50, 150, 60, 20);// 员工数量标签
        employeeCount.setBounds(110, 150, 80, 20);// 员工数量文本域
        l4.setBounds(230, 150, 60, 20);// 从事行业标签
        industry.add("机构组织");// 设置选项框内容
        industry.add("信息产业");
        industry.add("医药卫生");
        industry.add("机械机电");
        industry.setBounds(290, 150, 80, 20);// 从事行业
        l5.setBounds(50, 200, 60, 20);// 年营业额标签
        turnover.setBounds(110, 200, 80, 20);// 年营业额
        l6.setBounds(50, 250, 60, 20);// 利润率标签
        margin.setBounds(110, 250, 80, 20);// 利润率
        btn1.setBounds(110, 300, 50, 20);// 确认
        btn2.setBounds(180, 300, 50, 20);// 取消
        btn3.setBounds(250, 300, 50, 20);// 退出
        this.add(l);// 将各组件添加到窗体中
        this.add(l1);		this.add(name);		            this.add(l2);		this.add(funds);
        this.add(l3);		this.add(employeeCount);		this.add(l4);	    this.add(industry);
        this.add(l5);		this.add(turnover);		        this.add(l6);		this.add(margin);
        this.add(btn1);		this.add(btn2);		            this.add(btn3);
        setLocationRelativeTo(null); // 使窗体在屏幕上居中放置
        btn1.addActionListener(this); // 为三个按钮注册事件侦听器
        btn2.addActionListener(this);		btn3.addActionListener(this);
        employeeCount.addActionListener(this); // 为员工数量文本域注册焦点事件侦听器
        funds.addActionListener(this);// 为注册基金文本域注册焦点事件侦听器
        turnover.addActionListener(this);// 为年企业额文本域注册事件侦听器
        margin.addActionListener(this);// 为利润率文本域注册焦点事件侦听器
    }
    // 窗体的ActionEvent事件处理方法
    public void actionPerformed(ActionEvent e) {
        try {
            Integer.parseInt(employeeCount.getText());
            Integer.parseInt(funds.getText());
            Float.parseFloat(turnover.getText());
            Float.parseFloat(margin.getText());
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "员工数量和注册基金为整数,年营业额和利润率为浮点数","错误提示", JOptionPane.ERROR_MESSAGE);
            employeeCount.setText("50");
            funds.setText("");
            turnover.setText("");
            margin.setText("");
        }
        Object ob = e.getSource(); // 获取事件对象
        if (ob == btn3) { // 单击退出按钮
            System.exit(0); // 退出系统
        } else if (ob == btn1) {// 单击确认按钮
            System.out.println("企业名称：" + name.getText());
            System.out.println("员工数量：" + employeeCount.getText());
            System.out.println("从事行业：" + industry.getSelectedItem());
            System.out.println("年营业额：" + turnover.getText());
            System.out.println("注册资金：" + funds.getText());
            System.out.println("利润率：" + margin.getText());
        } else if (ob == btn2) { // 单击取消按钮
            name.setText(""); // 清空姓名文本域
            employeeCount.setText("50");
            turnover.setText("");
            funds.setText("");
            margin.setText("");
        }
    }
    public static void main(String[] args) {
        企业信息调查表 app = new 企业信息调查表("企业信息");
        app.setVisible(true);
    }
}
class CloseHandler extends WindowAdapter{
    //继承适配器类
    public void windowClosing (WindowEvent e){
        //处理关闭窗口事件的方法
        System.exit(0);//终止当前线程
    }
}
