package 贪吃蛇游戏;

̰������Ϸ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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
    int scoreMax;

    public Msnack(String title){
        super (title);
        Msnack.CloseHandler handler=new Msnack.CloseHandler();//���崰���¼�������������
        this.addWindowListener(handler); //Ϊ��ǰ����ע������������
        setSize(300,200);
        setLocationRelativeTo(null);
        menuinit();
        try {
        scoreMax=readRecord();
        }catch(Exception e) {
        	scoreMax=0;
        	System.out.println(e);
        }
        tx=new TextArea();
        this.add(tx);
        tx.setEditable(false);
        setVisible(true);
        

        player=1;//Ĭ��һ�����
        newFrame();
        tx.setText("\t��Ŀ��̰������Ϸ\n\tBY����˼\n\t�༶����Ϣ��ȫ182��\n\tѧ�ţ�8003118045");
    }

    public void newFrame(){
        frame = new JFrame();
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        mpanel=new Mpanel(this);
        frame.add(mpanel);
    }

    public void menuinit(){
        mainmenubar = new MenuBar();

        game=new Menu ("��Ϸ");
        mode=new Menu("ģʽ");
        about=new Menu("����");

        newgame=new MenuItem("����Ϸ");
        oneplayer=new MenuItem("������Ϸ");
        mutiplayer=new MenuItem("������Ϸ");
        instruction=new MenuItem("����");

        game.add(newgame);
        mode.add(oneplayer);
        mode.add(mutiplayer);
        about.add(instruction);

        mainmenubar.add(game);
        mainmenubar.add(mode);
        mainmenubar.add(about);

        setMenuBar(mainmenubar);
        newgame.addActionListener(this);//Ϊ���˵���ע���¼�������
        oneplayer.addActionListener(this);//Ϊ���˵���ע���¼�������
        mutiplayer.addActionListener(this);//Ϊ���˵���ע���¼�������
        instruction.addActionListener(this);//Ϊ���˵���ע���¼�������
        
    }
    
    private int readRecord() throws IOException {
    	int score=0;
    	File file=new File("test.txt");
    	if(!file.exists()) {
    		file.createNewFile();
    	}
    	RandomAccessFile rafile=new RandomAccessFile(file,"rw");
    	rafile.seek(0);
		score=rafile.readInt();
		return score;
    }	
    
    private void writeRecord(int score) throws IOException {
    	File file=new File("test.txt");
    	if(!file.exists()) {
    		file.createNewFile();
    	}
    	RandomAccessFile rafile=new RandomAccessFile(file,"rw");
    	//int ÿ���ַ�4���ֽ� ָ������+4�Ӽ�������������
    	rafile.seek(0);
    	rafile.writeInt(score);
    }
    
    public void actionPerformed(ActionEvent actionEvent) {
        Object ob=actionEvent.getSource();
        if (ob==newgame){
            frame.setVisible(true);
            mpanel.setFocusable(true);
            tx.setText("������Ϸ�С�����");
            this.setVisible(false);
        }else if (ob==oneplayer) {
            tx.setText("������Ϸģʽ��\nֻ��һ����\n������\t���ϣ�W\t\t���£�S\n\t����A\t\t���ң�D\n\n\t��ͣ��SPACE");
            player=1;
            mpanel.player=1;
            mpanel.initSnake(1);
        }else if (ob==mutiplayer) {
            tx.setText("˫����Ϸģʽ��\n��������\n���2��\t���ϣ�up\t���£�down\n\t����left\t���ң�right\n\n\t�Ե��Է�����ȡ���������");
            player=2;
            mpanel.player=2;
            mpanel.initSnake(2);
        }else if (ob==instruction) {
            tx.setText("\t��Ŀ��̰������Ϸ\n\tBY����˼\n\t�༶����Ϣ��ȫ182��\n\tѧ�ţ�8003118045");
        }
    }

    public void record(int score,int len){
        tx.setText("��ĳɼ���"+score+"\t��ĳ���:"+len);
        if (score>scoreMax) {
        	scoreMax=score;
        	tx.append("\n\n�¼�¼��"+scoreMax);
        	try {
        		writeRecord(score);
        	}catch(Exception e) {
            	System.out.println(e);
            }
        }
    }

    public void winner(int[] score,int[] len,int failure){
        int winner=1;
        int max=score[0]>score[1]?score[0]:score[1];
        if (failure==0) {
            winner=2;
        }
        tx.setText("1����ҵĳɼ���"+score[0]+"\t����:"+len[0]+"\n2����ҵĳɼ���"+score[1]+"\t����:"+len[1]+"\n���:"+winner+"�����");
        if(max>scoreMax) {
        	scoreMax=max;
        	tx.append("\n\n�¼�¼��"+scoreMax);
        	try {
        		writeRecord(max);
        	}catch(Exception e) {
            	System.out.println(e);
            }
        }
    }


    public static void main(String[] args) {
        Msnack msnack =new ̰������Ϸ.Msnack("̰������Ϸ");
    }

    public class CloseHandler extends WindowAdapter{
        public void windowClosing (WindowEvent e){
            System.exit(0);
        }
    }
}
