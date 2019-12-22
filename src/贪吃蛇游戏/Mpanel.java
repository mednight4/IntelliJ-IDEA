package 贪吃蛇游戏;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Mpanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title ;
    ImageIcon body;
    ImageIcon up ;
    ImageIcon down;
    ImageIcon left ;
    ImageIcon right ;
    ImageIcon food ;

    int player;
    int failure;
    int[] len= {3,3};
    int[] score ={0,0};
    int[][] snakex = new int[2][750];
    int[][] snakey = new int[2][750];
    String[] fx={"R","R"}; //方向:R, L, U, D
    boolean isStarted = false;
    boolean isFailed = false;
    Timer timer = new Timer(100, this);
    int foodx;
    int foody;
    Random rand = new Random();
    Msnack msnack;
    Clip BGM;
    Clip eat;
    boolean playEat;

    public Mpanel(Msnack msnack) {
        loadSound();
        loadImages();
        player=1;
        this.msnack=msnack;
        failure=0;//默认一号玩家，因为直接将order（从零开始）赋给failure
        initSnake(player);
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        playEat=false;
    }
    private void loadImages() {
        // TODO 自动生成的方法存根
        InputStream is;
        try {
            is=getClass().getClassLoader().getResourceAsStream("pic/title.jpg");
            title = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/body.png");
            body = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/up.png");
            up  = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/down.png");
            down = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/left.png");
            left = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/right.png");
            right = new ImageIcon(ImageIO.read(is));
            is=getClass().getClassLoader().getResourceAsStream("pic/food.png");
            food = new ImageIcon(ImageIO.read(is));
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }


    private void loadSound() {
        // TODO 自动生成的方法存根
        try {
            BGM=AudioSystem.getClip();
            eat=AudioSystem.getClip();
            InputStream getbgm=this.getClass().getClassLoader().getResourceAsStream("sound/bgm.wav");
            AudioInputStream ais=AudioSystem.getAudioInputStream(getbgm);
            BGM.open(ais);
            InputStream geteat=this.getClass().getClassLoader().getResourceAsStream("sound/eat.wav");
            ais=AudioSystem.getAudioInputStream(geteat);
            eat.open(ais);
        } catch (LineUnavailableException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    private void stopBGM() {
        // TODO 自动生成的方法存根
        BGM.stop();

    }
    private void playBGM() {
        // TODO 自动生成的方法存根
        BGM.loop(Clip.LOOP_CONTINUOUSLY);
    }
    private void playeat() {
        // TODO 自动生成的方法存根
        if(playEat) {
            eat.loop(Clip.LOOP_CONTINUOUSLY);
            playEat=!playEat;
        }else if(!playEat) {
            eat.stop();
        }
    }


    public void initSnake(int player) {
        for (int order=0;order<player;order++)
        {
            len[order] = 3;
            snakex[order][0] = 100;
            snakey[order][0] = 200+(order)*100;
            snakex[order][1] = 75;
            snakey[order][1] = 200+(order)*100;
            snakex[order][2] = 50;
            snakey[order][2] = 200+(order)*100;
            fx[order]= "R";
            score[order] = 0;
        }
        foodx = 25 + 25 * rand.nextInt(34);
        foody = 75 + 25 * rand.nextInt(24);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);
        g.setColor(Color.BLACK);

        for (int order=0;order<player;order++)
        {
            g.drawString("Len " + len[order], 550+(order)*100, 35);
            g.drawString("Score " + score[order], 550+(order)*100, 50);

            if(fx[order] == "R") {
                right.paintIcon(this, g, snakex[order][0], snakey[order][0]);
            }else if(fx[order] == "L") {
                left.paintIcon(this, g, snakex[order][0], snakey[order][0]);
            }else if(fx[order] == "D") {
                down.paintIcon(this, g, snakex[order][0], snakey[order][0]);
            }else if(fx[order] == "U") {
                up.paintIcon(this, g, snakex[order][0], snakey[order][0]);
            }
            for(int i=1; i< len[order]; i++) {
                body.paintIcon(this, g, snakex[order][i], snakey[order][i]);
            }
        }

        food.paintIcon(this, g, foodx, foody);

        if(isStarted == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 40));
            g.drawString("Press Space to Start", 300, 300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE) {
            if(isFailed) {
                isFailed = false;
                initSnake(player);
            } else {
                isStarted = !isStarted;
            }
            repaint();
            if(isStarted) {
                playBGM();
            }if(!isStarted){
                stopBGM();
            }
        }
        if(( fx[0]!="D")&&(keyCode == KeyEvent.VK_W )) {
            fx[0] = "U";
        }if(( fx[0]!="U")&&(keyCode == KeyEvent.VK_S )) {
            fx[0] = "D";
        }if((fx[0]!="R") &&(keyCode == KeyEvent.VK_A) ) {
            fx[0] = "L";
        }if((fx[0]!="L")&& (keyCode == KeyEvent.VK_D) ) {
            fx[0] = "R";
        }if(  (fx[1]!="R")&&(keyCode == KeyEvent.VK_LEFT)) {
            fx[1] = "L";
        }if((fx[1]!="L") && (keyCode == KeyEvent.VK_RIGHT)) {
            fx[1] = "R";
        }if((fx[1]!="D")&&(keyCode == KeyEvent.VK_UP)   ){
            fx[1] = "U";
        }if( (fx[1]!="U")&&(keyCode == KeyEvent.VK_DOWN) ) {
            fx[1] = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isFailed==true){
            msnack.frame.setVisible(false);
            msnack.setVisible(true);
            stopBGM();
            if (player==1){
                msnack.record(score[0],len[0]);
            }else if(player==2){
                msnack.winner(score,len,failure);
            }
            initSnake(1);
            initSnake(2);
            isFailed=!isFailed;
            isStarted=!isStarted;
        }
        if(isStarted && !isFailed) {
            for(int order=0;order<player;order++) {
                for (int i = len[order] - 1; i > 0; i--) {
                    snakex[order][i] = snakex[order][i - 1];
                    snakey[order][i] = snakey[order][i - 1];
                }

                if (fx[order] == "R") {
                    snakex[order][0] = snakex[order][0] + 25;
                    if (snakex[order][0] > 850)
                    {
                        snakex[order][0] = 25;
                        isFailed = true;
                        failure=order;
                    }
                } else if (fx[order] == "L") {
                    snakex[order][0] = snakex[order][0] - 25;
                    if (snakex[order][0] < 25)
                    {
                        snakex[order][0] = 850;
                        isFailed = true;
                        failure=order;
                    }
                } else if (fx[order] == "U") {
                    snakey[order][0] = snakey[order][0] - 25;
                    if (snakey[order][0] < 75)
                    {
                        snakey[order][0] = 650;
                        isFailed = true;
                        failure=order;
                    }
                } else if (fx[order] == "D") {
                    snakey[order][0] = snakey[order][0] + 25;
                    if (snakey[order][0] > 670)
                    {
                        snakey[order][0] = 75;
                        isFailed = true;
                        failure=order;
                    }
                }
                if (snakex[order][0] == foodx && snakey[order][0] == foody) {
                    playEat=true;
                    len[order]++;
                    score[order] = score[order] + 10;
                    foodx = 25 + 25 * rand.nextInt(34);
                    foody = 75 + 25 * rand.nextInt(24);
                }

                for (int i = 1; i < len[order]; i++) {
                    if (snakex[order][i] == snakex[order][0] && snakey[order][i] == snakey[order][0]) {
                        isFailed = true;
                        failure=order;
                    }
                }
            }

            if(player==2) {
                int p1=len[0];
                int p2=len[1];
                for(int i=1;i<len[0];i++) {
                    if (snakex[0][i] == snakex[1][0] && snakey[0][i]==snakey[1][0]) {
                        p1=i;
                        score[1]=score[1]+score[0]-(p1-3)*10;
                    }
                }
                for(int i=1;i<len[1];i++) {
                    if (snakex[1][i] == snakex[0][0] && snakey[1][i]==snakey[0][0]) {
                        p2=i;
                        score[0]=score[0]+score[1]-(p2-3)*10;
                    }
                }
                len[0]=p1;
                len[1]=p2;
            }

            repaint();
        }
        playeat();
        timer.start();
    }

}
