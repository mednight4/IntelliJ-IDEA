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
    MenuItem pause;
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

        player=1;
        gameover=false;

        frame = new JFrame();
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
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
        instruction.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object ob=actionEvent.getSource();
        if (ob==newgame){
            this.mpanel=new Mpanel(player,this);
            frame.add(mpanel);
            tx.setText("启动游戏中。。。");
            frame.setVisible(true);
            this.setVisible(false);
        }else if (ob==oneplayer) {
            tx.setText("\t单人游戏模式：\n\t只有一条虫\n操作：\t向上：W\t\t向下：S\n\t向左：A\t\t向右：D\n\n\t暂停：SPACE");
            player=1;
        }else if (ob==mutiplayer) {
            tx.setText("\t双人游戏模式：\n\t有两条虫\nplayer2：向上：up\t\t向下：down\n\t向左：left\t\t向右：right\n\n\t撞到、就会去世！");
            player=2;
        }else if (ob==instruction) {
            tx.setText("\t项目：贪吃蛇游戏\n\tBY：李思\n\t班级：信息安全182班\n\t学号：8003118045");
        }
        else if(gameover==true){
            if (player==1){
                this.record(mpanel.score[0],mpanel.len[0]);
            }else if(player==2){
                this.winner(mpanel.score,mpanel.len,mpanel.failure);
            }
        }
    }

    public void record(int score,int len){
        tx.setText("\t你的成绩：\t"+score+"\n你的长度:\t"+len);
    }

    public void winner(int[] score,int[] len,int failure){
        int winner=1;
        if (failure==0) {
            winner=2;
        }
            tx.setText("\t1号玩家的成绩：\t"+score[0]+"\n\t1号玩家的长度:\t"+len[0]+"\n\t2号玩家的成绩：\t"+score[1]+"\n\t2号玩家的长度:\t"+len[1]+"\n\twinner:\t"+winner+"号玩家");

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
