package model;

import views.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameEngine extends JPanel {
    private final int maxWave = 10;
    private final int FPS = 60;
    private static boolean paused = false;
    private static boolean isOver = false;
    private int wave = 1;
    private int levelNum = 1;
    private Timer newFrameTimer;
    public boolean started = false;
    public int speed = 100;

    private Level level;
    private Player player;
    private ArrayList<Tower> towers;
    private ArrayList<Tower> realTowers;
    private ArrayList<Bullet> bullets;
    private boolean showTowers = false;
    /*NEW*/
    private static ArrayList<Enemy> enemies;

    public GameEngine() {
        super();
        realTowers = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Player(100, 50);
        player.reset();
        restart();
        enemies = startRound(wave);

        newFrameTimer = new Timer(speed / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public ArrayList<Enemy> startRound(int line) {
        ArrayList<Enemy> enemies = new ArrayList();
        try {
            File myObj = new File("src/data/waves.txt");
            Scanner myReader = new Scanner(myObj);
            int counter = 0;
            do {
                String data = myReader.nextLine();
                counter++;
            } while (counter != line);
            String data = myReader.nextLine();
            String[] currencies = data.split(" ");
            int startY = -100;
            for (int i = 0; i < currencies.length; i++) {
                switch (currencies[i]) {
                    case "p":
                        Image pregnantImage = new ImageIcon("src/data/pngs/pregnant.png").getImage();
                        Enemy pregnant = new Enemy(225, startY, 50, 50, pregnantImage, 2, true, 5, 1);
                        enemies.add(pregnant);
                        break;
                    case "i":
                        Image itImage = new ImageIcon("src/data/pngs/it_man.png").getImage();
                        Enemy it = new Enemy(225, startY, 50, 50, itImage, 3, true, 10, 2);
                        enemies.add(it);
                        break;
                    case "a":
                        Image manImage = new ImageIcon("src/data/pngs/man.png").getImage();
                        Enemy man = new Enemy(225, startY, 50, 50, manImage, 2, true, 15, 3);
                        enemies.add(man);
                        break;
                    case "s":
                        Image kidImage = new ImageIcon("src/data/pngs/kid.png").getImage();
                        Enemy kid = new Enemy(225, startY, 50, 50, kidImage, 1, true, 5, 4);
                        enemies.add(kid);
                        break;
                    case "b":
                        Image bossImage = new ImageIcon("src/data/pngs/boss.png").getImage();
                        Enemy boss = new Enemy(225, startY, 50, 50, bossImage, 5, true, 25, 5);
                        enemies.add(boss);
                        break;
                    default:
                        break;
                }
                startY = startY - 100;
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
            level = new Level("src/data/level" + getLevelNum() + ".txt", "src/data/coordinates" + getLevelNum() + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        level.draw(grphcs);
        if (showTowers) {
            towers = new ArrayList<>();
            towers = this.level.getAllTower();
            for (Tower tower : towers) {
                tower.draw(grphcs);
            }
        }
        for (Enemy enemy : enemies) {
            enemy.draw(grphcs);
        }
        for (Tower tower : realTowers) {
            tower.draw(grphcs);
        }
        for (Bullet bullet : bullets) {
            if (bullet.getVisibility()) {
                bullet.draw(grphcs);
            }
        }

        this.setBackground(new java.awt.Color(223, 197, 161));
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (!GameEngine.paused && !isOver && started) {
                for (int i = 0; i < realTowers.size(); i++) {
                    Tower tower = realTowers.get(i);
                    Bullet bullet = bullets.get(i);
                    tower.shoot(enemies, bullet);
                    if (bullet.getHasDir()) {
                        bullet.move(tower.getFirstEnemy());
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).collidesBus(level.getBus())) {
                        player.decreaseLife(enemies.get(i).getDmg());
                        GameGUI.refreshLives(player.getLives());
                        enemies.get(i).kill();
                        player.addMoney(-5);
                        GameGUI.refreshMoney(player.getMoney());
                    } else {
                        enemies.get(i).move(level.getCoordinates(), level.getDirections());
                    }
                }

                for (int i = 0; i < enemies.size(); i++) {
                    if (!enemies.get(i).getAlive()) {
                        switch (enemies.get(i).getType()) {
                            case 1:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                            case 2:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                            case 3:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                            case 4:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                            case 5:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                            default:
                                player.addMoney(enemies.get(i).getWorth());
                                GameGUI.refreshMoney(player.getMoney());
                                break;
                        }
                        GameGUI.refreshImage();
                        enemies.remove(i);
                    }
                }
            }
            /*NEW MAYBE WRONG SOLUTION*/
            if (isOver() && wave < maxWave) {
                nextWave();
            } else if (isOver() && wave >= maxWave && levelNum != 5) {
                started = false;
                wave = 1;
                levelNum++;
                level.reset();
                /*VALAMI bibi, nem tudom még mi*/
                towers.clear();
                realTowers.clear();
                bullets.clear();
                player.setMoney(50);
                GameGUI.refreshMoney(50);
                /**
                 * *******************************
                 */
                GameGUI.refreshWaves(wave);
                GameGUI.refreshLevel(levelNum);
                restart();
                enemies = startRound(wave);
            }
            /*TODO fix this*/
            if (player.getLives() <= 0) {
                enemies.clear();
                if ((JOptionPane.showConfirmDialog(null, "Do you want to start a New Game?", "GAME OVER", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
                    started = false;
                    setLevelNum(1);
                    wave = 1;
                    enemies = startRound(wave);
                    player.setLives(100);
                    GameGUI.refreshLives(100);
                    player.setMoney(50);
                    GameGUI.refreshMoney(50);
                    restart();
                    repaint();

                } else {
                    System.exit(-1);
                }
            }

            if ((isOver() && wave == maxWave && levelNum == 5)) {
                if ((JOptionPane.showConfirmDialog(null, "Do you want to start a New Game?", "YOU WON", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
                    started = false;
                    setLevelNum(1);
                    wave = 1;
                    enemies = startRound(wave);
                    realTowers.clear();
                    bullets.clear();
                    player.setLives(100);
                    GameGUI.refreshLives(100);
                    player.setMoney(50);
                    GameGUI.refreshMoney(50);
                    restart();
                    repaint();
                } else {
                    System.exit(-1);
                }
            }

            repaint();
        }

    }

    public void nextWave() {
        started = false;
        wave++;
        GameGUI.refreshWaves(wave);
        //restart();
        enemies = startRound(wave);
    }

    public boolean getPaused() {
        return this.paused;
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
        if (!paused) {
            this.showTowers = !this.showTowers;
        }
    }

    public void addTower(Tower tower, int type) {

        { //tower
            Image img = new ImageIcon().getImage();
            if (type == 1) {
                img = new ImageIcon("src/data/pngs/crowgrey.png").getImage();
                player.setMoney(player.getMoney() - 10);
            } else if (type == 2) {
                img = new ImageIcon("src/data/pngs/disabgrey.png").getImage();
                player.setMoney(player.getMoney() - 15);
            } else {
                img = new ImageIcon("src/data/pngs/incoggrey.png").getImage();
                player.setMoney(player.getMoney() - 20);
            }
            realTowers.add(tower.createTower(10, 150, img));
            GameGUI.refreshMoney(player.getMoney());
            GameGUI.refreshImage();
            this.towers.remove(tower);
        }
        { // Bullet for the tower
            int bulletX = tower.getX() + 15;
            int bulletY = tower.getY() - 15;
            Bullet bullet = new Bullet(bulletX, bulletY, 20, 20, new ImageIcon("src/data/pngs/circle.png").getImage());
            bullets.add(bullet);
        }
    }

    public boolean getShowTowers() {
        return this.showTowers;
    }

    /**
     * Tell that if there are enemies on the screen or not
     *
     * @return
     */
    public boolean isOver() {
        return enemies.isEmpty();
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public void startTimer() {

        started = true;
    }

    public int getWave() {
        return this.wave;
    }

    public ArrayList<Tower> getTowers() {
        return this.towers;
    }
}
