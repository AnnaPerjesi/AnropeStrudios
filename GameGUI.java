/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class GameGUI extends JFrame {
    
    private JFrame frame;
    private JLabel lives;
    private JLabel money;
    private JLabel pause;

    
    public GameGUI() {
        
        {//
        lives = new JLabel("lives-valtozo helye");
        lives.setHorizontalAlignment(JLabel.CENTER);
        lives.setPreferredSize(new Dimension(150, 40));
        lives.setLayout(null);
        lives.setBounds(635, 20, 150, 40);
        lives.setVisible(true);
        lives.setBorder(BorderFactory.createLineBorder(Color.black));
        lives.setOpaque(true);
        lives.setBackground(new java.awt.Color(189,189,189));
        }
        
        {//
        money = new JLabel("money-valtozo helye");
        money.setHorizontalAlignment(JLabel.CENTER);
        money.setPreferredSize(new Dimension(150, 40));
        money.setLayout(null);
        money.setBounds(815, 20, 150, 40);
        money.setVisible(true);
        money.setBorder(BorderFactory.createLineBorder(Color.black));
        money.setOpaque(true);
        money.setBackground(new java.awt.Color(189,189,189));
        }
        
        {//TODO javítani
        
       // ImageIcon icon = new ImageIcon(getClass().getResource("C:\\Users\\User\\Desktop\\4FÉLÉV\\SZOFTTECH\\CatchTheBus\\src\\data\\pause.png"));
        pause = new JLabel("");
        Image img = new ImageIcon(this.getClass().getResource("data/pause.png")).getImage();
        pause.setIcon((Icon) img);
       // pause.setHorizontalAlignment(JLabel.CENTER);
        pause.setPreferredSize(new Dimension(40, 40));
        pause.setLayout(null);
        pause.setBounds(1530, 20, 40, 40);
        pause.setVisible(true);

        
        }
        
        
        
        
        
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(lives);        
        panel.add(money);
        panel.add(pause);
//ctrl shift lenyíl
        
       
        
        frame = new JFrame("Catch The Bus ~ Anrope Studios©");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1600, 900));
        panel.setBackground(new java.awt.Color(223,197,161));
        frame.setResizable(false);
        
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        
    }
    
    
}
