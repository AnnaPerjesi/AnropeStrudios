package catchthebus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStart extends JFrame {
    
    private final JFrame frame;
    private JPanel start;
    private JLabel menuLabel;
    private final JButton play;
    private final JButton exit;
    private final int ROAD_WIDTH = 100;
    private final int ROAD_HEIGHT = 100;
    
    public GameStart() throws FileNotFoundException, IOException {
        start = new JPanel();
        start.setBackground(new java.awt.Color(223, 197, 161));
            
        menuLabel = new JLabel();
        menuLabel.setBackground(new java.awt.Color(223, 197, 161));
        menuLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        menuLabel.setBounds(600, 200, 400, 250);
        
        {
        play = new JButton("Play");
        play.setBounds(750, 300, 150, 70);
        play.setBackground(null);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        }
        
        {
        exit = new JButton("Exit");
        exit.setBounds(750, 400, 150, 70);
        exit.setBackground(null);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        }

        start.setLayout(null);
        start.add(menuLabel);
        drawGame("src/data/level1.txt");
        start.add(play);
        start.add(exit);
        frame = new JFrame("Catch The Bus ~ Anrope Studios©");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1600, 900));
        frame.setResizable(false);
        
        frame.getContentPane().add(start);
        frame.pack();
        frame.setVisible(true);
    
    }
    
    public void startGame() {
        frame.setVisible(false);
        GameGUI game = new GameGUI();
    }
    
    public void drawGame(String filename) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int y = 0;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char type : line.toCharArray()) {
                if (type == 'r' || type == 'b') {
                    JLabel road = new JLabel();
                    road.setIcon(new ImageIcon("src/data/pngs/road.png"));
                    road.setBounds(x * ROAD_WIDTH, y * ROAD_HEIGHT, ROAD_WIDTH, ROAD_HEIGHT);
                    road.setBorder(null);
                    road.setBackground(null);
                    start.add(road);
                } else if ( type == '0') {
                    
                }
                x++;
            }
            y++;
        }
        
    }
}
