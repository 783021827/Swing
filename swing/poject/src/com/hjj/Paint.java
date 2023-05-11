package com.hjj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Paint {

    JFrame jf=new JFrame();
    private final int AREA_WIDTH=500;
    private final int AREA_HEIGHT=400;

    private PopupMenu popupMenu=new PopupMenu();
    private MenuItem redItem=new MenuItem("红色");
    private MenuItem greenItem=new MenuItem("绿色");
    private MenuItem blueItem=new MenuItem("蓝色");
    private Color forceColor=Color.BLACK;

    private int preX=-1;
    private int preY=-1;


    BufferedImage image=new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_RGB);

    Graphics g=image.getGraphics();

    private class MyCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            g.drawImage(image,0,0,null);
        }
    }
    MyCanvas mc=new MyCanvas();

    public void init(){

        ActionListener ac=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                switch (actionCommand){
                    case "红色":forceColor=Color.RED;break;
                    case "绿色":forceColor=Color.GREEN;break;
                    case "蓝色":forceColor=Color.BLUE;break;
                }
            }
        };

        redItem.addActionListener(ac);
        greenItem.addActionListener(ac);
        blueItem.addActionListener(ac);

        popupMenu.add(redItem);
        popupMenu.add(greenItem);
        popupMenu.add(blueItem);

        mc.add(popupMenu);
        mc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {//鼠标抬起
                boolean popupTrigger = e.isPopupTrigger();
                if(popupTrigger){
                    popupMenu.show(mc,e.getX(),e.getY());
                }
                preX=-1;
                preY=-1;
            }
        });

        //设置背景
        g.setColor(Color.WHITE);
        g.fillRect(0,0,AREA_WIDTH,AREA_HEIGHT);

        mc.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(preX>0 && preY>0){
                    g.setColor(forceColor);
                    g.drawLine(preX,preY,e.getX(),e.getY());
                }
                preX=e.getX();
                preY=e.getY();

                mc.repaint();

            }
        });

        mc.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));

        jf.add(mc);

        jf.pack();
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new Paint().init();
    }

}
