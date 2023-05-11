import javax.swing.*;
import java.awt.*;

public class Main2 {

    private final String rect="RECT_SHAPE";
    private final String oval="OVAL_SHAPE";

    JFrame jf=new JFrame();
    JButton drawRect=new JButton("绘制矩形");
    JButton drawOval=new JButton("绘制椭圆");

    private String shape="";
    private class MyCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            if(shape.equals(rect)){
                g.setColor(Color.BLACK);
                g.drawRect(100,100,150,100);

            }
            else {
                g.setColor(Color.cyan);
                g.drawOval(100,100,150,100);
            }
        }
    }
    MyCanvas mc=new MyCanvas();

    public void init(){
        drawRect.addActionListener(e->{
            shape=rect;
            mc.repaint();
        });

        drawOval.addActionListener(e->{
            shape=oval;
            mc.repaint();
        });

        Panel panel = new Panel();
        panel.add(drawRect);
        panel.add(drawOval);

        jf.add(panel,BorderLayout.SOUTH);
        mc.setPreferredSize(new Dimension(300,300));

        jf.add(mc);

        jf.pack();
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        new Main2().init();
    }

}
