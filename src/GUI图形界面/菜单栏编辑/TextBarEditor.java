package GUI图形界面.菜单栏编辑;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileReader;
import javax.swing.JFileChooser;

public class TextBarEditor extends Frame implements ActionListener {
    MenuBar mainmenubar = new MenuBar();//申明菜单条
    Menu file; //申明主菜单项
    Menu editor;//申明编辑菜单
    MenuItem cp;
    MenuItem ct;
    MenuItem pa;
    MenuItem nw;//申明各子菜单项
    MenuItem op;
    MenuItem cl;
    MenuItem sf;
    MenuItem ex;
    TextArea tx;//申明文本区对象
    String copy = "";

    public TextBarEditor(String title) {
        super(title);//调用父类构造方法
        GUI图形界面.菜单栏编辑.CloseHandler handler = new GUI图形界面.菜单栏编辑.CloseHandler();//定义窗体事件的侦听器对象
        this.addWindowListener(handler); //为当前窗体注册侦听器对象
        setSize(800, 800);//设置窗体尺寸
        setLocationRelativeTo(null);//使窗体在屏幕上居中显示
        menuinit();//构建与处理菜单
        tx = new TextArea();//创建文本区对象
        this.add(tx);//将文本区对象放入窗体
        setVisible(true);
    }

    //菜单的构建和处理
    void menuinit() {
        mainmenubar = new MenuBar();//定义主菜单栏
        file = new Menu("文件");
        editor = new Menu("编辑");
        cp = new MenuItem("复制");
        ct = new MenuItem("剪贴");
        pa = new MenuItem("粘贴");
        nw = new MenuItem("新建");
        op = new MenuItem("打开");
        cl = new MenuItem("关闭");
        sf = new MenuItem("保存");
        ex = new MenuItem("退出");
        editor.add(cp);
        editor.add(ct);
        editor.add(pa);//将各子菜单项加入到主菜单项中
        file.add(nw);//将各子菜单项加入到主菜单项中
        file.add(op);
        file.add(cl);
        file.add(sf);
        file.add(ex);
        mainmenubar.add(file);//将主菜单项加入到主菜单栏中
        mainmenubar.add(editor);
        setMenuBar(mainmenubar);//为窗体设置主菜单
        nw.addActionListener(this);//为各菜单项注册事件侦听器
        op.addActionListener(this);//为各菜单项注册事件侦听器
        cl.addActionListener(this);//为各菜单项注册事件侦听器
        sf.addActionListener(this);//为各菜单项注册事件侦听器
        ex.addActionListener(this);//为各菜单项注册事件侦听器
        cp.addActionListener(this);//为各菜单项注册事件侦听器
        pa.addActionListener(this);//为各菜单项注册事件侦听器
        ct.addActionListener(this);//为各菜单项注册事件侦听器
    }

    //窗体的ActionEvent事件处理方法
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();//获取事件对象
        JFileChooser f = new JFileChooser();//创建文件选择器对象
        if ((ob == nw) || (ob == cl)) {
            //选择"新建文件"或者"关闭文件"子菜单项
            tx.setText("");//清空文本区
        } else if (ob == op) {
            //选择"打开文件"子菜单项
            //弹出具有自定义approve按钮的自定义文件选择器对话框
            f.showOpenDialog(this);
            try {
                //定义一个字符缓冲器对象s
                StringBuffer s = new StringBuffer();
                //构造一个FileReader对象in,其参数在为文件选择器
                //对话框中选中的文件
                FileReader in = new FileReader(f.getSelectedFile());
                //读入文件内容,将其放入字符缓冲器对象s中
                while (true) {
                    int b = in.read();
                    if (b == -1)
                        break;
                    s.append((char) b);
                }
                tx.setText(s.toString());//将文件内天显示在文本区
                in.close();//关闭文件
            } catch (Exception ee) {
            }
        } else if (ob == sf) {//选择"保存文件"子菜单项
            f.showSaveDialog(this);
            try {
                //创建FileWriter对象，其参数为前面选择的文件
                FileWriter out = new FileWriter(f.getSelectedFile());
                out.write(tx.getText());//将文本内容写入文件
                out.close();//关闭文件
            } catch (Exception ee) {
            }
        } else if (ob == ex) {
            System.exit(0);
        } else if (ob == cp) {
            copy = tx.getText();
        } else if (ob == ct) {
            copy = tx.getText();
            tx.setText("");//清空文本区
        } else if (ob == pa) {
            tx.append(copy);
        }
    }

    public static void main(String[] args) {
        new GUI图形界面.菜单栏编辑.TextBarEditor("简易文本编辑器");
    }
}

//CloseHandler类实现关闭文件窗口的功能
class CloseHandler extends WindowAdapter {
    //继承适配器类
    public void windowClosing(WindowEvent e) {
        //处理关闭窗口事件的方法
        System.exit(0);//终止当前线程
    }
}

