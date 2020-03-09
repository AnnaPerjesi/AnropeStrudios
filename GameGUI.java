package catchthebus;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI extends JFrame{
    private JFrame frame;
    private JLabel lives;
    private JLabel money;
    private JButton pause;
    private JButton tower;
    private JLabel towerCost;

    
    public GameGUI() {
        
        {//Lives Label
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
        
        {//Money Label
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
        
        {//Pause Button
            pause = new JButton();
            //pause.setBorder(BorderFactory.createLineBorder(Color.black));
            pause.setIcon(new ImageIcon("src/Data/pause.png"));
            pause.setBounds(1540,20,40,40);
            pause.setBorder(null);
            pause.setBackground(null);
        }
        
        {//Tower1 Button + Cost Label
            tower = new JButton();
            //pause.setBorder(BorderFactory.createLineBorder(Color.black));
            tower.setIcon(new ImageIcon("src/Data/group.png"));
            tower.setBounds(1540,800,40,40);
            tower.setBorder(null);
            tower.setBackground(null);
            
            towerCost = new JLabel("cost");
            towerCost.setHorizontalAlignment(JLabel.CENTER);
            towerCost.setBounds(1540,845,40,20);
            towerCost.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost.setOpaque(true);
            towerCost.setBackground(new java.awt.Color(189,189,189));
            
            
            
        }
        
        
        
        
        
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(lives);        
        panel.add(money);
        panel.add(pause);
        panel.add(tower);
        panel.add(towerCost);   
        

        
       
        
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
