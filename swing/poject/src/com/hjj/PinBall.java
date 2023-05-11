package com.hjj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinBall {

    private JFrame jf=new JFrame("弹球游戏");
    //桌面的高度和宽度
    private final int TABLE_WIDTH=400;
    private final int TABLE_HEIGHT=400;
    //球拍的高度和宽度
    private final int RACKET_WIDTH=60;
    private final int RACKET_HEIGHT=20;

    //球的大小
    private final int BALL_SIZE=16;

    //记录小球坐标
    private int ballX=120;
    private int ballY=20;
    //记录x与y方向移动的速度
    private int speedX=10;
    private int speedY=10;

    //记录球拍的坐标
    private int racketX=120;
    private final int racketY=340;

    //标识游戏是否结束
    private boolean isOver=false;

    //定时器
    private Timer timer;

    private class MyCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            if(isOver){
                g.setColor(Color.BLUE);
                g.setFont(new Font("times",Font.BOLD,30));
                g.drawString("游戏结束",50,200);


            }
            else {

                g.setColor(Color.RED);
                g.fillOval(ballX,ballY,BALL_SIZE,BALL_SIZE);

                g.setColor(Color.pink);
                g.fillRect(racketX,racketY,RACKET_WIDTH,RACKET_HEIGHT);

            }
        }
    }
    //绘画区域
    MyCanvas drawArea=new MyCanvas();


    public void init(){

        KeyListener listener=new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode==KeyEvent.VK_LEFT){
                    if(racketX>0){
                        racketX-=10;
                    }
                }
                if(keyCode==KeyEvent.VK_RIGHT){
                    if(racketX<TABLE_WIDTH-RACKET_WIDTH){
                        racketX+=10;
                    }
                }
            }
        };

        jf.addKeyListener(listener);
        drawArea.addKeyListener(listener);

        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ballX<=0 || ballX>=TABLE_HEIGHT-BALL_SIZE){
                    speedX=-speedX;
                }
                if(ballY<=0 || (ballY>racketY-BALL_SIZE && ballX>racketX && ballX<racketX+RACKET_WIDTH)){
                    speedY=-speedY;
                }
                if(ballY>racketY-BALL_SIZE && (ballX<racketX || ballX>racketX+RACKET_WIDTH)){
                    timer.stop();
                    isOver=true;
                    drawArea.repaint();
                }

                ballX+=speedX;
                ballY+=speedY;

                drawArea.repaint();
            }
        };

        timer=new Timer(100,task);
        timer.start();

        drawArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));


        jf.add(drawArea);

        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new PinBall().init();
    }
}
