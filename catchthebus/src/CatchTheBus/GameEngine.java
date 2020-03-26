/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import catchthebus.Level.Pair;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameEngine extends JPanel {

    private final int FPS = 60;
    private static boolean paused = false;

    
    private Timer newFrameTimer;

    private int levelNum = 0;
    private Level level;
    /*NEW*/private static ArrayList<Enemy> enemies;

    public GameEngine() {
        super();

        restart();
        enemies = startRound();

        newFrameTimer = new Timer(5000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        System.out.println(newFrameTimer);

    }

    public ArrayList<Enemy> startRound() {
        ArrayList<Enemy> enemies = new ArrayList();
        Image enemyImage = new ImageIcon("src/data/pngs/pregnant.png").getImage();
        Enemy first = new Enemy(225, 0, 50, 50, enemyImage);
        enemies.add(first);
        return enemies;
    }

    public void restart() {
        try {
            level = new Level("src/data/level1.txt", "src/data/coordinates1.txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image road = new ImageIcon("src/data/pngs/road.png").getImage();

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        level.draw(grphcs);
        enemies.get(0).draw(grphcs);

        this.setBackground(new java.awt.Color(223, 197, 161));
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!GameEngine.paused) {
                enemies.get(0).move(level.getCoordinates(), level.getDirections());
            }
            repaint();
        }

    }
    
    //NEW
    public static void setPaused(boolean paused) {
        GameEngine.paused = paused;
    }
    
    //NEW
    public static void enemyArrived(){
        System.out.println(enemies.size());
        enemies.remove(0);
        System.out.println(enemies.size());
        
    }

}
