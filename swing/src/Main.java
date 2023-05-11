import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();


        /*Panel panel = new Panel();
        panel.add(new JTextField("测试文本"));
        panel.add(new JButton("测试按钮"));
        frame.add(panel);*/


        /*ScrollPane jScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        jScrollPane.add(new JTextField("测试为目标"));
        jScrollPane.add(new JButton("测试按钮"));
        frame.add(jScrollPane);*/

        /*frame.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        for (int i = 0; i < 100; i++) {
            frame.add(new JButton("按钮"+i));
        }*/

        /*frame.setLayout(new BorderLayout(30,10));
        frame.add(new JButton("北侧按钮"),BorderLayout.NORTH);
        frame.add(new JButton("东侧按钮"),BorderLayout.EAST);
        frame.add(new JButton("西侧按钮"),BorderLayout.WEST);
        frame.add(new JButton("南侧按钮"),BorderLayout.SOUTH);
        frame.add(new JButton("中侧按钮"),BorderLayout.CENTER);*/

        Panel panel = new Panel();
        panel.add(new TextField(30));
        frame.add(panel,BorderLayout.NORTH);

        Panel panel1 = new Panel();
        panel1.setLayout(new GridLayout(3,5,4,4));
        panel1.add(new JButton("0"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("2"));
        panel1.add(new JButton("3"));
        panel1.add(new JButton("4"));
        panel1.add(new JButton("5"));
        panel1.add(new JButton("6"));
        panel1.add(new JButton("7"));
        panel1.add(new JButton("8"));
        panel1.add(new JButton("9"));
        panel1.add(new JButton("+"));
        panel1.add(new JButton("-"));
        panel1.add(new JButton("*"));
        panel1.add(new JButton("/"));
        panel1.add(new JButton("."));
        frame.add(panel1);



        //frame.setBounds(100,100,500,300);
        frame.pack();
        frame.setVisible(true);
    }
}