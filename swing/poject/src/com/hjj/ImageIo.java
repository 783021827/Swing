package com.hjj;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageIo {

    private JFrame jf=new JFrame();
    MenuBar menuBar=new MenuBar();

    Menu menu=new Menu("文件");
    MenuItem menuItem1=new MenuItem("打开");
    MenuItem menuItem2=new MenuItem("另存为");

    BufferedImage image;

    private class MyCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            g.drawImage(image,0,0,null);
        }
    }
    MyCanvas drawArea=new MyCanvas();



    public void init(){

        menuItem1.addActionListener(e->{
            FileDialog fileDialog = new FileDialog(jf, "打开图片", FileDialog.LOAD);
            fileDialog.setVisible(true);

            String directory = fileDialog.getDirectory();
            String file = fileDialog.getFile();

            try {
                image = ImageIO.read(new File(directory, file));
                drawArea.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        menuItem2.addActionListener(e->{
            FileDialog fileDialog = new FileDialog(jf, "保存图片", FileDialog.SAVE);
            fileDialog.setVisible(true);

            String directory = fileDialog.getDirectory();
            String file = fileDialog.getFile();

            try {
                ImageIO.write(image,"PNG",new File(directory,file));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.add(menuItem1);
        menu.add(menuItem2);

        menuBar.add(menu);

        jf.setMenuBar(menuBar);

        jf.add(drawArea);

        //drawArea.setPreferredSize(new Dimension());

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        jf.setBounds(100,100,800,500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new ImageIo().init();
    }
}
