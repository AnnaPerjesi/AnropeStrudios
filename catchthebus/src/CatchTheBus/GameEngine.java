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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameEngine extends JPanel {

    private final int FPS = 60;
    private static boolean paused = false;
    private static boolean isOver = false;
    private int timer = 0;

    private Timer newFrameTimer;

    private int levelNum = 0;
    private Level level;
    private Player player;
    private ArrayList<Tower> towers;
    private ArrayList<Tower> realTowers;
    private boolean showTowers = false;
    /*NEW*/
    private static ArrayList<Enemy> enemies;

    public GameEngine() {
        super();
        realTowers = new ArrayList<>();
        player = new Player();
        player.reset();
        restart();
        enemies = startRound();

        newFrameTimer = new Timer(5000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public ArrayList<Enemy> startRound() {
        ArrayList<Enemy> enemies = new ArrayList();
        try {
            File myObj = new File("src/data/enemies.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] currencies = data.split(" ");
                int startY = 0;
                for (int i = 0; i < currencies.length; i++) {
                    switch (currencies[i]) {
                        case "p":
                            Image pregnantImage = new ImageIcon("src/data/pngs/pregnant.png").getImage();
                            Enemy pregnant = new Enemy(225, startY, 50, 50, pregnantImage, 2, true);
                            enemies.add(pregnant);
                            break;
                        case "i":
                            Image itImage = new ImageIcon("src/data/pngs/it_man.png").getImage();
                            Enemy it = new Enemy(225, startY, 50, 50, itImage, 3,true);
                            enemies.add(it);
                            break;
                        case "a":
                            Image manImage = new ImageIcon("src/data/pngs/man.png").getImage();
                            Enemy man = new Enemy(225, startY, 50, 50, manImage, 2,true);
                            enemies.add(man);
                            break;
                        case "s":
                            Image kidImage = new ImageIcon("src/data/pngs/kid.png").getImage();
                            Enemy kid = new Enemy(225, startY, 50, 50, kidImage, 1,true);
                            enemies.add(kid);
                            break;
                        case "b":
                            Image bossImage = new ImageIcon("src/data/pngs/boss.png").getImage();
                            Enemy boss = new Enemy(225, startY, 50, 50, bossImage, 5,true);
                            enemies.add(boss);
                            break;
                        default:
                            break;
                    }
                    startY = startY - 100;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
        if (showTowers) {
            towers = new ArrayList<>();
            towers = this.level.getAllTower();
            for (Tower tower : towers ) {
                tower.draw(grphcs);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(grphcs);
        }
        for (Tower tower : realTowers ) {
            tower.draw(grphcs);
        }
        this.setBackground(new java.awt.Color(223, 197, 161));
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
                if (!GameEngine.paused && !isOver) {

                    timer++;
                    if(timer % 10 == 0){
                        //TODO torony eléri-e, ha igen -> lő
                        
                        
                        
                        /*for(int i = 0; i<enemies.size(); i++){
                            enemies.get(i).takeDamage();
                        }*/
                    }
                    for (int i = 0; i < enemies.size(); i++) {
                        
                        if (enemies.get(i).collidesBus(level.getBus())) {
                            player.decreaseLife(enemies.get(i).getDmg());
                            enemies.get(i).kill();
                        } else {
                            System.out.println(enemies.get(0).getAlive());
                            enemies.get(i).move(level.getCoordinates(), level.getDirections());
                        }
                    }
                    
                    for(int i = 0; i<enemies.size(); i++){
                        if(!enemies.get(i).getAlive()){
                            enemies.remove(i);
                        }
                    }
                 //System.out.println(timer);
                }
                

                repaint();
        }

    }

    public static void setPaused(boolean paused) {
        GameEngine.paused = paused;
    }

    public int getPlayerLives() {
        return this.player.getLives();
    }

    public int getPlayerMoney() {
        return this.player.getMoney();
    }
    
    public Level getLevel() {
        return this.level;
    }
    
    public void changeShowTower() {
        this.showTowers = !this.showTowers;
    }
    
    public void addTower(Tower tower, int type) {
        // TODO - típus
        if (type == 1) {
            realTowers.add(tower.createTower(new ImageIcon("src/data/pngs/crowgrey.png").getImage()));
            this.towers.remove(tower);
        } else if ( type == 2 ) {
            realTowers.add(tower.createTower(new ImageIcon("src/data/pngs/disabgrey.png").getImage()));
            this.towers.remove(tower);
        } else if ( type == 3 ) {
            realTowers.add(tower.createTower(new ImageIcon("src/data/pngs/incoggrey.png").getImage()));
            this.towers.remove(tower);
        }
    }
    
    public boolean getShowTowers() {
        return this.showTowers;
    }
}
