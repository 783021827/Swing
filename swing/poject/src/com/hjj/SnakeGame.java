package com.hjj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame {
    JFrame jf=new JFrame("贪吃蛇");
    int slong=2;
    int[] snakeX=new int[100];
    int[] snakeY=new int[100];
    int dir=1;
    int foodX;
    int foodY;

    int started=0;

    Random ra=new Random();

    Timer timer=new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //判断游戏是否开始
            if(started==1){
                //通过循环控制蛇移动
                for (int i = slong-1; i > 0; i--) {
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                //判断蛇移动的方向
                if(dir==0){
                    snakeX[0]=snakeX[0]-20;
                } else if (dir==1) {
                    snakeX[0]=snakeX[0]+20;
                } else if (dir==2) {
                    snakeY[0]=snakeY[0]-20;
                } else if (dir==3) {
                    snakeY[0]=snakeY[0]+20;
                }
                //判断蛇是否撞到墙外
                if(snakeX[0]<0 || snakeX[0]>700 || snakeY[0]<80 || snakeY[0]>580){
                    started=2;
                }
                //判断蛇是否吃到了食物
                if(snakeX[0]==foodX && snakeY[0]==foodY){
                    slong++;
                    foodX=ra.nextInt(39);
                    foodY=ra.nextInt(22);
                    foodX=foodX*20;
                    foodY=foodY*20+80;

                }
                //判断是否吃到了自己

                for (int i = 1; i < slong; i++) {
                    if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                        started=2;
                    }
                }
                //判断食物是否随机在了蛇身上
                for (int i = 0; i < slong; i++) {
                    if(foodX==snakeX[i] && foodY==snakeY[i]){
                        foodX=ra.nextInt(39);
                        foodY=ra.nextInt(22);
                        foodX=foodX*20;
                        foodY=foodY*20+80;
                    }
                }
                drawArea.repaint();
            }
            timer.start();
        }
    });

    private class Canvas extends JPanel{
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0,0,800,600);

            g.setColor(Color.BLACK);
            g.fillRect(0,80,800,520);

            for (int i = 0; i < slong; i++) {
                g.setColor(Color.CYAN);
                g.fillRect(snakeX[i],snakeY[i],20,20);
            }

            g.setColor(Color.YELLOW);
            g.fillOval(foodX,foodY,20,20);

            if(started==0){
                g.setColor(Color.BLACK);
                g.setFont(new Font("微软雅黑",10,20));
                g.drawString("按下空格开始",300,65);
            }
            else if(started==1){
                g.setColor(Color.BLACK);
                g.setFont(new Font("微软雅黑",10,20));
                g.drawString("当前分数",300,65);
                g.drawString(String.valueOf((slong-2)),420,65);
            }
            else if(started==2){
                g.setColor(Color.BLACK);
                g.setFont(new Font("微软雅黑",10,20));
                g.drawString("最终分数",300,65);
                g.drawString(String.valueOf((slong-2)),420,65);
            }
        }
    }
    Canvas drawArea=new Canvas();
    public void init(){
        jf.setSize(800,600);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int wid=Toolkit.getDefaultToolkit().getScreenSize().width;
        int hei=Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setLocation((wid-800)/2,(hei-600)/2);


        jf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_SPACE){
                    if(started==0){
                        started=1;
                    }
                    else if(started==1){
                        started=0;
                    }
                    else if(started==2){
                        started=0;
                        slong=2;
                        snakeX[0] = 60;
                        snakeY[0] = 100;
                        snakeX[1] = 40;
                        snakeY[1] = 100;

                        foodX=ra.nextInt(39);
                        foodY=ra.nextInt(22);
                        foodX=foodX*20;
                        foodY=foodY*20+80;

                        dir=1;
                    }
                    drawArea.repaint();
                    timer.start();
                }
                else if(keyCode==KeyEvent.VK_RIGHT){
                    if(dir!=0)dir=1;
                }
                else if(keyCode==KeyEvent.VK_LEFT){
                    if(dir!=1)dir=0;
                } else if (keyCode==KeyEvent.VK_UP) {
                    if(dir!=3)dir=2;
                } else if (keyCode==KeyEvent.VK_DOWN) {
                    if(dir!=2)dir=3;
                }
            }
        });

        drawArea.setPreferredSize(new Dimension(800,600));
        jf.add(drawArea);

        snakeX[0] = 60;
        snakeY[0] = 100;
        snakeX[1] = 40;
        snakeY[1] = 100;

        foodX=ra.nextInt(39);
        foodY=ra.nextInt(22);
        foodX=foodX*20;
        foodY=foodY*20+80;


    }

    public static void main(String[] args) {
        new SnakeGame().init();
    }
}
