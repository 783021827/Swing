import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main1 {
    public static void main(String[] args) {
        JFrame jf = new JFrame();

        Panel panel = new Panel();
        String[] str=new String[]{"第一张","第二张","第三张","第四张","第五张"};
        CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        for (int i = 0; i < str.length; i++) {
            panel.add(str[i],new JButton(""+str[i]));
        }

        Panel panel1 = new Panel();
        JButton b1 = new JButton("第一页");
        JButton b2 = new JButton("下一页");
        JButton b3 = new JButton("第三页");
        JButton b4 = new JButton("上一页");
        JButton b5 = new JButton("最后页");

        ActionListener ac=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                switch (actionCommand){
                    case "第一页":
                        cardLayout.first(panel);break;
                    case "下一页":
                        cardLayout.next(panel);break;
                    case "第三页":
                        cardLayout.show(panel,str[2]);break;
                    case "上一页":
                        cardLayout.previous(panel);break;
                    case "最后页":
                        cardLayout.last(panel);break;

                }
            }
        };
        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(b4);
        panel1.add(b5);

        /*b1.addActionListener(e->{
            cardLayout.first(panel);
        });
        b2.addActionListener(e->{
            cardLayout.next(panel);
        });
        b3.addActionListener(e->{
            cardLayout.show(panel,str[2]);
        });
        b4.addActionListener(e->{
            cardLayout.previous(panel);
        });
        b5.addActionListener(e->{
            cardLayout.last(panel);
        });*/
        b1.addActionListener(ac);
        b2.addActionListener(ac);
        b3.addActionListener(ac);
        b4.addActionListener(ac);
        b5.addActionListener(ac);



        jf.add(panel,BorderLayout.NORTH);
        jf.add(panel1);
        jf.pack();
        jf.setVisible(true);

    }
}
