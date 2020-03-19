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
    private final JFrame frame;
    private final JLabel lives;
    private final JLabel money;
    private final JButton pause;
    private final JButton tower;
    private final JButton tower2;
    private final JButton tower3;
    private final JLabel towerCost;
    private final JLabel towerCost2;
    private final JLabel towerCost3;
;

    
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
            pause.setIcon(new ImageIcon("src/data/pngs/pause.png"));
            pause.setBounds(1540,20,40,40);
            pause.setBorder(null);
            pause.setBackground(null);
        }
        
        {//Tower1 Button + Cost Label
            /*AFROMAGYAR CSOPORT*/
            tower = new JButton();
            tower.setIcon(new ImageIcon("src/data/pngs/crowgrey.png"));
            tower.setBounds(1532,780,50,50);
            tower.setBorder(null);
            tower.setBackground(null);
            
            towerCost = new JLabel("cost");
            towerCost.setHorizontalAlignment(JLabel.CENTER);
            towerCost.setBounds(1532,835,50,20);
            towerCost.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost.setOpaque(true);
            towerCost.setBackground(new java.awt.Color(189,189,189));
            
            
            /*KEREKESSZÉKES*/
            tower2 = new JButton();
            tower2.setIcon(new ImageIcon("src/data/pngs/disabgrey.png"));
            tower2.setBounds(1470,780,50,50);
            tower2.setBorder(null);
            tower2.setBackground(null);
            
            towerCost2 = new JLabel("cost");
            towerCost2.setHorizontalAlignment(JLabel.CENTER);
            towerCost2.setBounds(1470,835,50,20);
            towerCost2.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost2.setOpaque(true);
            towerCost2.setBackground(new java.awt.Color(189,189,189));
            
            
            /*ELLENŐR*/
            tower3 = new JButton();
            tower3.setIcon(new ImageIcon("src/data/pngs/incoggrey.png"));
            tower3.setBounds(1408,780,50,50);
            tower3.setBorder(null);
            tower3.setBackground(null);
            
            towerCost3 = new JLabel("cost");
            towerCost3.setHorizontalAlignment(JLabel.CENTER);
            towerCost3.setBounds(1408,835,50,20);
            towerCost3.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost3.setOpaque(true);
            towerCost3.setBackground(new java.awt.Color(189,189,189));
            
            
        }
        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(lives);        
        panel.add(money);
        panel.add(pause);
        panel.add(tower);
        panel.add(towerCost);
        panel.add(tower2);
        panel.add(towerCost2);
        panel.add(tower3);
        panel.add(towerCost3); 
        

        
       
        
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
