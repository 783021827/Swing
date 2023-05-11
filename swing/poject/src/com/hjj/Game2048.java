package com.hjj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game2048 {
    public static JFrame gameFrame;

    public static void  main(String[] args) {
        gameFrame = new JFrame();
        gameFrame.setSize(370, 400);
        gameFrame.setTitle("2048游戏");
        gameFrame.setLocationRelativeTo(null);
        gameFrame.add(new GamePanel());
        gameFrame.setVisible(true);
    }
      static class GamePanel extends JPanel {
        public GamePanel(){
            //1. 初始化所有卡片
            initCard();
            //2.随机创建一个卡片
            createRandcard();
            //3. 创建键盘监听
            createKeyListener();
        }

        ///键盘监听
        private void createKeyListener() {
            Game2048.gameFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    //清理卡片的合并标记
                    for (int i = 0; i <4; i++) {
                        for (int j = 0; j < 4; j++) {
                            Card card =allCards[i][j];
                            card.hebing = false;
                        }
                    }

                    //获取按键的code
                    int key = e.getKeyCode();
                    switch (key){
                        case KeyEvent.VK_UP: //向上
                            moveCardTop(true);
                            break;
                        case KeyEvent.VK_DOWN: //向下
                            moveCardDown(true);
                            break;
                        case KeyEvent.VK_LEFT: //向左
                            moveCardLeft(true);
                            break;
                        case KeyEvent.VK_RIGHT: //向右
                            moveCardRight(true);
                            break;
                    }
                    createRandcard();
                    repaint();//重绘
                    isgameOver();
                }
            });
        }

        //判断游戏是否结束
        private void isgameOver() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4 ; j++) {
                    if(allCards[i][j].num == 2048){//其中有个数字是2048
                        JOptionPane.showMessageDialog(null,"赢了！","恭喜",JOptionPane.WARNING_MESSAGE);
                        return;
                    }else if(isFull()){//位置满了
                        //还得判断 上下左右四个方向是不是都不饿能移动了
                        if(moveCardDown(false) || moveCardLeft(false) || moveCardRight(false) || moveCardTop(false)){//至少有一个地方能移动
                            return;//结束方法
                        }else{//都不能
                            JOptionPane.showMessageDialog(null,"游戏结束！","很遗憾",JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                    }
                }
            }
        }


        //向上
        private boolean moveCardTop(boolean flag) {
            boolean canMove = false;
            //1.从第2行开始移动，因为第1行不需要移动
            //2.只要卡片的数字不是0，就表示要移动
            //3.根据i-1 可以获取上一个卡片，如果上一个卡片是0，则把当前卡片交换上去，并且递归。因为可能要继续往上移动。
            //4.如果当前卡片与上一个卡片是相同数字，则要合并
            Card card;
            for (int i = 1; i <4 ; i++) {
                for (int j = 0; j < 4; j++) {
                    card = allCards[i][j];
                    if(card.num != 0){//只要卡片不是空白就需要移动
                        //方法递归
                        if(card.moveTop(allCards,flag)){
                            canMove = true;
                        }
                    }
                }
            }
            return canMove;
        }


        //向下
        private boolean moveCardDown(boolean flag) {
            boolean canMove = false;
            Card card;
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    card = allCards[i][j];
                    if (card.num != 0) {//只要卡片不是空白就需要移动
                        //方法递归
                        if(card.moveDown(allCards,flag)){
                            canMove = true;
                        }

                    }
                }
            }
            return canMove;
        }
        //向左
        private boolean moveCardLeft(boolean flag) {
            boolean canMove = false;
            Card card;
            for (int i = 0; i < 4 ; i++) {
                for (int j = 1; j< 4; j++) {
                    card = allCards[i][j];
                    if(card.num != 0){//只要卡片不是空白就需要移动
                        //方法递归
                        if(card.moveLeft(allCards,flag)){
                            canMove = true;
                        }
                    }
                }
            }
            return canMove;
        }
        //向右
        private boolean moveCardRight(boolean flag) {
            boolean canMove = false;
            Card card;
            for (int i = 0; i <4 ; i++) {
                for (int j = 2; j>=0; j--) {
                    card = allCards[i][j];
                    if(card.num != 0){//只要卡片不是空白就需要移动
                        //方法递归
                        if(card.moveRight(allCards,flag)){
                            canMove = true;
                        }
                    }
                }
            }
            return canMove;
        }

        Random rand = new Random();
        //随机创建一个卡片
        public void createRandcard(){
            //1. 数字2和4出现的比例是1：4。所以采用随机出0-4的数字。
            //2. 随机获取i,j就可以得到卡片的位置。获取card的一个实例对象。如果卡片上数字为0，就可以表示。若不是就递归继续取。
            int num = 0; //卡片上的数字
            int index = rand.nextInt(5);
            if(index == 0||index == 1||index == 2){
                num = 4;
            }else{
                num = 2;
            }
            //如果格子满了，则不需要再创建了
            if (isFull()) {
                return;
            }

            //卡片的位置
            Card card = getRandomCard();
            if(card != null) {
                card.num = num;//设置卡片上的数字
            }
        }

        //判断所有的格子是不是都满了
        private boolean isFull() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(allCards[i][j].num == 0){
                        return false;
                    }
                }
            }
            return true;
        }

        //卡片的位置  返回卡片对象
        // 随机获取i,j就可以得到卡片的位置。获取card的一个实例对象。如果卡片上数字为0，就可以表示。若不是就递归继续取。
        private Card getRandomCard() {
            int i = rand.nextInt(4);
            int j = rand.nextInt(4);
            Card card = allCards[i][j];
            if(card.num != 0){
                return getRandomCard();
            }else{
                return card;
            }
        }


        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //画卡片
            drawCard(g);
        }

        //绘制卡片
        private void drawCard(Graphics g) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Card card = allCards[i][j];
                    //矩形
                    Color color = getColor(card.num);
                    Color oldColor = g.getColor();
                    g.setColor(color);//设置新颜色
                    g.fillRect(card.x,card.y,80,80); //画笔默认是黑的
                    //绘制数字
                    if(card.num != 0){
                        g.setColor(new Color(125,78,51));
                        g.setFont(new Font("宋体",Font.BOLD,35));
                        String text = card.num+"";
                        int tx = card.x;
                        if(text.length()==1){
                            tx = tx + 32;
                        }else if(text.length()==2){
                            tx = tx + 20;
                        }else if(text.length()==3){
                            tx = tx + 8;
                        }else{
                            tx = tx - 2;
                        }
                        int ty = card.y+50;
                        g.drawString(text,tx,ty);
                    }

                    g.setColor(oldColor);//还原颜色
                }
            }
        }

        //获取颜色
        private Color getColor(int num){
            Color color = null;
            switch (num){
                case 2:color = new Color(242,230,56);
                    break;
                case 4:color = new Color(55,236,200);
                    break;
                case 8:color = new Color(174,213,130);
                    break;
                case 16:color = new Color(142,201,75);
                    break;
                case 32:color = new Color(111,148,48);
                    break;
                case 64:color = new Color(76,174,124);
                    break;
                case 128:color = new Color(60,180,144);
                    break;
                case 256:color = new Color(45,130,120);
                    break;
                case 512:color = new Color(9,97,26);
                    break;
                case 1024:color = new Color(242,177,121);
                    break;
                case 2048:color = new Color(223,185,0);
                    break;
                default:color = new Color(92,151,117);
                    break;
            }
            return color;
        }


        //创建卡片
        Card[][] allCards = new Card[4][4];
        public void initCard(){//4*4表格
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    allCards[i][j] = new Card(i,j);
                }
            }
        }

    }
    //卡片类
    static class Card {
        int x = 0, y = 0;//坐标
        int i = 0;//下标i
        int j = 0;//下标j
        int num = 0;//数字
        boolean hebing = false;//是否合并过了，如果已经合并过来就不能合并

        public Card(int i, int j) {
            this.i = i;
            this.j = j;
            this.x = 5 + j * 80 + (j + 1) * 5;
            this.y = 5 + i * 80 + (i + 1) * 5;
        }

        public boolean moveTop(Card[][] allCards, boolean flag) {
            if (this.i == 0) {
                return false;
            }
            //上一个的卡片
            Card prevCard = allCards[i - 1][j];
            if (prevCard.num == 0) {//把数字移动上去
                if (flag) {
                    prevCard.num = this.num;
                    this.num = 0;
                    prevCard.moveTop(allCards, flag);
                }
                return true;
            } else if (prevCard.num == this.num && !prevCard.hebing) {//合并数字
                if (flag) {
                    prevCard.hebing = true;
                    prevCard.num *= 2;
                    this.num = 0;
                }
                return true;
            } else {
                return false;
            }
        }

        public boolean moveRight(Card[][] allCards, boolean flag) {
            if (this.j == 3) {
                return false;
            }
            //右边的卡片
            Card rightCard = allCards[i][j + 1];
            if (rightCard.num == 0) {//把数字移动上去
                if (flag) {
                    rightCard.num = this.num;
                    this.num = 0;

                    rightCard.moveRight(allCards, flag);
                }
                return true;
            } else if (rightCard.num == this.num && !rightCard.hebing) {//合并数字
                if (flag) {
                    rightCard.hebing = true;
                    rightCard.num *= 2;
                    this.num = 0;
                }
                return true;
            } else {
                return false;
            }
        }

        //向下
        public boolean moveDown(Card[][] allCards, boolean flag) {
            if (this.i == 3) {
                return false;
            }
            //下边的卡片
            Card downCard = allCards[i + 1][j];
            if (downCard.num == 0) {//把数字移动上去
                if (flag) {
                    downCard.num = this.num;
                    this.num = 0;
                    downCard.moveDown(allCards, flag);
                }
                return true;
            } else if (downCard.num == this.num && !downCard.hebing) {//合并数字
                if (flag) {
                    downCard.hebing = true;
                    downCard.num *= 2;
                    this.num = 0;
                }
                return true;
            } else {
                return false;
            }
        }

        //向左
        public boolean moveLeft(Card[][] allCards, boolean flag) {
            if (this.j == 0) {
                return false;
            }
            //左边的卡片
            Card leftCard = allCards[i][j - 1];
            if (leftCard.num == 0) {//把数字移动上去
                if (flag) {
                    leftCard.num = this.num;
                    this.num = 0;
                    leftCard.moveLeft(allCards, flag);
                }
                return true;
            } else if (leftCard.num == this.num && !leftCard.hebing) {//合并数字
                if (flag) {
                    leftCard.hebing = true;
                    leftCard.num *= 2;
                    this.num = 0;
                }
                return true;
            } else {
                return false;
            }
        }

    }
}
