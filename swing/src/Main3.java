import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main3 {

    JFrame jf=new JFrame();

    JMenuBar menuBar=new JMenuBar();
    JMenu file=new JMenu("文件");
    JMenu edit=new JMenu("编辑");
    JMenuItem auto=new JMenu("自动换行");
    JMenuItem copy=new JMenuItem("复制");
    JMenuItem paste=new JMenuItem("粘贴");
    JMenu format=new JMenu("格式");
    JMenuItem comment =new JMenuItem("注释");
    JMenuItem cancelComment=new JMenuItem("取消注释");

    JTextArea ta=new JTextArea(8,20);

    String[] color=new String[]{"红色","绿色","蓝色"};

    JList<String> colorList=new JList<>(color);
    JComboBox<String> colorSelect=new JComboBox<>();
    ButtonGroup bg=new ButtonGroup();
    JRadioButton male=new JRadioButton("男",false);
    JRadioButton feMale=new JRadioButton("女",true);

    JCheckBox isMarried=new JCheckBox("是否已婚",true);

    JTextField tf=new JTextField(40);
    JButton ok=new JButton("确定",new ImageIcon());

    JPopupMenu jPopupMenu=new JPopupMenu();

    ButtonGroup pop=new ButtonGroup();
    JRadioButtonMenuItem metal=new JRadioButtonMenuItem("Metal 风格");
    JRadioButtonMenuItem nimbus=new JRadioButtonMenuItem("Nimbus 风格");
    JRadioButtonMenuItem windows=new JRadioButtonMenuItem("Windows 风格",true);
    JRadioButtonMenuItem windows1=new JRadioButtonMenuItem("Windows 经典风格");
    JRadioButtonMenuItem motif=new JRadioButtonMenuItem("Motif 风格");




    public void init(){

        JPanel jPanel=new JPanel();
        jPanel.add(tf);jPanel.add(ok);
        jf.add(jPanel, BorderLayout.SOUTH);

        colorSelect.addItem("红色");
        colorSelect.addItem("绿色");
        colorSelect.addItem("蓝色");

        JPanel jPanel1=new JPanel();
        jPanel1.add(colorSelect);
        bg.add(male);
        bg.add(feMale);
        jPanel1.add(male);
        jPanel1.add(feMale);
        jPanel1.add(isMarried);

        Box topLeft = Box.createVerticalBox();
        topLeft.add(ta);
        topLeft.add(jPanel1);


        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);

        jf.add(top);

        menuBar.add(file);
        menuBar.add(edit);

        edit.add(auto);
        edit.addSeparator();
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();

        format.add(comment);
        format.add(cancelComment);

        edit.add(format);

        jf.setJMenuBar(menuBar);

        pop.add(metal);
        pop.add(nimbus);
        pop.add(windows);
        pop.add(windows1);
        pop.add(motif);

        ActionListener ac=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                try {
                    changeFlavor(actionCommand);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        };

        metal.addActionListener(ac);
        nimbus.addActionListener(ac);
        windows.addActionListener(ac);
        windows1.addActionListener(ac);
        motif.addActionListener(ac);

        jPopupMenu.add(metal);
        jPopupMenu.add(nimbus);
        jPopupMenu.add(windows);
        jPopupMenu.add(windows1);
        jPopupMenu.add(motif);

        //不需要监听鼠标是讲
        ta.setComponentPopupMenu(jPopupMenu);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    private void changeFlavor(String command) throws Exception {

        switch (command){
            case "Metal 风格":UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");break;
            case "Nimbus 风格":UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");break;
            case "Windows 风格":UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");break;
            case "Windows 经典风格":UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");break;
            case "Motif 风格":UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");break;
        }

        SwingUtilities.updateComponentTreeUI(jf.getContentPane());
        SwingUtilities.updateComponentTreeUI(menuBar);
        SwingUtilities.updateComponentTreeUI(jPopupMenu);
    }

    public static void main(String[] args) {
        new Main3().init();
    }

}
