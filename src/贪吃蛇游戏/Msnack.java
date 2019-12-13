package 贪吃蛇游戏;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Msnack extends Frame implements ActionListener {
    MenuBar mainmenubar =new MenuBar();
    Menu game;
    Menu mode;
    Menu about;
    MenuItem newgame;
    MenuItem oneplayer;
    MenuItem mutiplayer;
    MenuItem instruction;
    TextArea tx;
    JFrame frame;
    Mpanel mpanel;

    int player;
    boolean gameover;

    public Msnack(String title){
        super (title);
        Msnack.CloseHandler handler=new Msnack.CloseHandler();//定义窗体事件的侦听器对象
        this.addWindowListener(handler); //为当前窗体注册侦听器对象
        setSize(300,200);
        setLocationRelativeTo(null);
        menuinit();
        tx=new TextArea();
        this.add(tx);
        tx.setEditable(false);
        setVisible(true);

        player=1;//默认一个玩家
        newFrame();
        gameover=false;
    }

    public void newFrame(){
        frame = new JFrame();
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        mpanel=new Mpanel(player,this);
        frame.add(mpanel);
    }

    public void menuinit(){
        mainmenubar = new MenuBar();

        game=new Menu ("游戏");
        mode=new Menu("模式");
        about=new Menu("关于");

        newgame=new MenuItem("新游戏");
        oneplayer=new MenuItem("单人游戏");
        mutiplayer=new MenuItem("多人游戏");
        instruction=new MenuItem("关于");

        game.add(newgame);
        mode.add(oneplayer);
        mode.add(mutiplayer);
        about.add(instruction);

        mainmenubar.add(game);
        mainmenubar.add(mode);
        mainmenubar.add(about);

        setMenuBar(mainmenubar);
        newgame.addActionListener(this);//为各菜单项注册事件侦听器
        oneplayer.addActionListener(this);//为各菜单项注册事件侦听器
        mutiplayer.addActionListener(this);//为各菜单项注册事件侦听器
        instruction.addActionListener(this);//为各菜单项注册事件侦听器
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object ob=actionEvent.getSource();
        if (ob==newgame){
            frame.setVisible(true);
            mpanel.setFocusable(true);
            tx.setText("启动游戏中。。。");
            this.setVisible(false);
        }else if (ob==oneplayer) {
            tx.setText("单人游戏模式：\n只有一条虫\n操作：\t向上：W\t\t向下：S\n\t向左：A\t\t向右：D\n\n\t暂停：SPACE");
            player=1;
            mpanel.player=1;
        }else if (ob==mutiplayer) {
            tx.setText("双人游戏模式：\n有两条虫\n玩家2：\t向上：up\t向下：down\n\t向左：left\t向右：right\n\n\t撞到、就会去世！");
            player=2;
            mpanel.player=2;
        }else if (ob==instruction) {
            tx.setText("\t项目：贪吃蛇游戏\n\tBY：李思\n\t班级：信息安全182班\n\t学号：8003118045");
        }
    }

    public void record(int score,int len){
        tx.setText("你的成绩："+score+"\t你的长度:"+len);
    }

    public void winner(int[] score,int[] len,int failure){
        int winner=1;
        if (failure==0) {
            winner=2;
        }
        tx.setText("1号玩家的成绩："+score[0]+"\t长度:\n"+len[0]+"\n2号玩家的成绩："+score[1]+"\t长度:"+len[1]+"\n\twinner:"+winner+"号玩家");

    }


    public static void main(String[] args) {
        Msnack msnack =new 贪吃蛇游戏.Msnack("贪吃蛇游戏");
    }

    public class CloseHandler extends WindowAdapter{
        public void windowClosing (WindowEvent e){
            System.exit(0);
        }
    }
}
