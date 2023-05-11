package com.hjj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;

public class calcultor {
    public calcultor(){
        JFrame frame = new JFrame();

        StringBuffer sb=new StringBuffer();

        Panel panel = new Panel();
        TextField tx = new TextField(30);
        panel.add(tx);
        frame.add(panel,BorderLayout.NORTH);

        Panel panel1 = new Panel();
        panel1.setLayout(new GridLayout(4,5,4,4));
        JButton b1 = new JButton("0");
        JButton b2 = new JButton("1");
        JButton b3 = new JButton("2");
        JButton b4 = new JButton("3");
        JButton b5 = new JButton("4");
        JButton b6 = new JButton("5");
        JButton b7 = new JButton("6");
        JButton b8 = new JButton("7");
        JButton b9 = new JButton("8");
        JButton b10 = new JButton("9");
        JButton b11 = new JButton("+");
        JButton b12 = new JButton("-");
        JButton b13 = new JButton("*");
        JButton b14 = new JButton("/");
        JButton b15 = new JButton(".");
        JButton b16 = new JButton("=");

        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(b4);
        panel1.add(b5);
        panel1.add(b6);
        panel1.add(b7);
        panel1.add(b8);
        panel1.add(b9);
        panel1.add(b10);
        panel1.add(b11);
        panel1.add(b12);
        panel1.add(b13);
        panel1.add(b14);
        panel1.add(b15);
        panel1.add(b16);

        frame.add(panel1);

        ActionListener ac = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                switch (s){
                    case "0":sb.append("0");tx.setText(sb.toString());break;
                    case "1":sb.append("1");tx.setText(sb.toString());break;
                    case "2":sb.append("2");tx.setText(sb.toString());break;
                    case "3":sb.append("3");tx.setText(sb.toString());break;
                    case "4":sb.append("4");tx.setText(sb.toString());break;
                    case "5":sb.append("5");tx.setText(sb.toString());break;
                    case "6":sb.append("6");tx.setText(sb.toString());break;
                    case "7":sb.append("7");tx.setText(sb.toString());break;
                    case "8":sb.append("8");tx.setText(sb.toString());break;
                    case "9":sb.append("9");tx.setText(sb.toString());break;
                    case "+":sb.append("+");tx.setText(sb.toString());break;
                    case "-":sb.append("-");tx.setText(sb.toString());break;
                    case "*":sb.append("*");tx.setText(sb.toString());break;
                    case "/":sb.append("/");tx.setText(sb.toString());break;
                    case ".":sb.append(".");tx.setText(sb.toString());break;

                }
            }
        };

        b1.addActionListener(ac);
        b2.addActionListener(ac);
        b3.addActionListener(ac);
        b4.addActionListener(ac);
        b5.addActionListener(ac);
        b6.addActionListener(ac);
        b7.addActionListener(ac);
        b8.addActionListener(ac);
        b9.addActionListener(ac);
        b10.addActionListener(ac);
        b11.addActionListener(ac);
        b12.addActionListener(ac);
        b13.addActionListener(ac);
        b14.addActionListener(ac);
        b15.addActionListener(ac);


        b16.addActionListener(e->{
            String str=calcultor.calculate(sb.toString())+"";
            tx.setText(str);
        });

        frame.pack();
        frame.setVisible(true);
    }

    public static int calculate(String s) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        char t='+';
        int num=0;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                num=num*10+s.charAt(i)-'0';
            }
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i)!=' ' || i==s.length()-1){
                switch (t){
                    case '+':deque.push(num);break;
                    case '-':deque.push(-num);break;
                    case '*':deque.push(deque.pop()*num);break;
                    default:deque.push(deque.pop()/num);break;
                }
                t=s.charAt(i);
                num=0;
            }
        }
        int res=0;
        while (!deque.isEmpty()){
            res+=deque.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        new calcultor();
    }
}

