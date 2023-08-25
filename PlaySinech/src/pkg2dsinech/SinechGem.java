package pkg2dsinech;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class SinechGem extends JFrame{

    public SinechGem() throws HeadlessException {
        JFrame frame = new JFrame("Snake Game");
        frame.setBounds(10, 10, 905, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        frame.setVisible(true);
    }
    
}
