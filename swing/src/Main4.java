import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main4 {

    JFrame jf=new JFrame();

    JMenuBar jMenuBar=new JMenuBar();
    JMenu jMenu=new JMenu("文件");

    JMenuItem jMenuItem=new JMenuItem(new AbstractAction("打开") {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser(".");
            fileChooser.showOpenDialog(jf);

            File selectedFile = fileChooser.getSelectedFile();

            try {
                bufferedImage= ImageIO.read(selectedFile);
                drawArea.repaint();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

    JMenuItem jMenuItem1=new JMenuItem(new AbstractAction("另存为") {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser=new JFileChooser(".");
            fileChooser.showSaveDialog(jf);

            File selectedFile = fileChooser.getSelectedFile();

            try {
                ImageIO.write(bufferedImage,"jpeg",selectedFile);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    });


    BufferedImage bufferedImage;

    private class Canvas extends JPanel{
        @Override
        public void paint(Graphics g) {
            g.drawImage(bufferedImage,0,0,null);
        }
    }
    Canvas drawArea=new Canvas();
    public void init(){
        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem1);
        jMenuBar.add(jMenu);

        jf.setJMenuBar(jMenuBar);

        drawArea.setPreferredSize(new Dimension(740,500));


        jf.add(drawArea);

        jf.pack();
        jf.setVisible(true);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public static void main(String[] args) {
        new Main4().init();
    }
}
