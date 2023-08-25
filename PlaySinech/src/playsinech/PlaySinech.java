package playsinech;

import flappybirds.FlappySinech;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;

import pkg2dsinech.SinechGem;

public class PlaySinech extends JFrame implements ActionListener{

    public static void main(String[] args) {
        JFrame frame = new JFrame("Play Sinech");
        JButton flappySinech = new JButton ("Flappy Sinech");
        JButton SinechGem = new JButton ("Sinech Saga");
        JButton pingpong = new JButton ("Ping Pong");
        JButton exit = new JButton("Exit");
        
        JLabel labelMain = new JLabel("Hello user!");
        JPanel panel = new JPanel();
        
        Container container = frame.getContentPane();
        
        labelMain.setText("HELLO USER! CHOOSE A GAME TO PLAY: ");
        labelMain.setFont(new Font("Arial", Font.BOLD, 15));
        panel.setBounds(0, 0, 500, 20);
        panel.add(labelMain);
        
        frame.add(panel);
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 500, 350); //x, y, width, height;
        
        container.setLayout(null);
        
        flappySinech.setBounds(frame.getWidth()/2 - 200/2, 40, 200, 40);
        container.add(flappySinech);
        flappySinech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FlappySinech();
            }
        });
        
        SinechGem.setBounds(frame.getWidth()/2 - 200/2, 40*2+20*1, 200, 40);
        container.add(SinechGem);
        SinechGem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SinechGem();
            }
        });
        
        pingpong.setBounds(frame.getWidth()/2 - 200/2, 40*3+20*2, 200, 40);
        container.add(pingpong);
        pingpong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame();
                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();
                ImageIcon icon = new ImageIcon("Assets/hqdefault.jpg");
                JLabel label = new JLabel(icon);
                JLabel label2 = new JLabel("Progressing...");
                                
                label.setIcon(icon);
                
                panel.setBackground(Color.pink);
                panel.setBounds(0, 50, 500, 500);
                panel.add(label);
                
                panel2.setBounds(0, 0, 500, 40);
                panel2.add(label2);
                
                f.setTitle("This game is under developing");
                f.setSize(600, 500);
                f.setResizable(false);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                
                f.add(panel);
//                f.add(label2);
            }
        });
        
        exit.setBounds(frame.getWidth()/2 - 200/2, 40*4 + 20*3, 200, 40);
        container.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Heek");
    }
    
}
