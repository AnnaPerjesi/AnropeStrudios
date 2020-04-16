package catchthebus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameGUI extends JFrame {

    private final JFrame frame;
    private static JLabel lives;
    private final JLabel coin;
    private final JLabel heart;
    private static JLabel money;
    private final JButton pause;
    private static JLabel level;
    private static JButton tower;
    private static JButton tower2;
    private static JButton tower3;
    private static JLabel towerCost;
    private static JLabel towerCost2;
    private static JLabel towerCost3;
    private final JButton startRound;
    private static JLabel roundCounter;

    //NEW
    private final JLabel pBg;
    private final JLabel pWindow;
    private final JButton pResume;
    private final JButton pExit;
    private static GameEngine gameArea;
    private ArrayList<Tower> towers;
    private int type;
    Tower temp;

    public GameGUI() {
        gameArea = new GameEngine();
        towers = new ArrayList<>();
        towers = gameArea.getLevel().getAllTower();
        gameArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameArea.getPaused()) {
                    if (gameArea.getShowTowers()) {
                        boolean found = false;
                        int x = e.getX();
                        int y = e.getY();
                        for (Tower tower : towers) {
                            if (tower.getX() + 50 > x && tower.getX() < x && tower.getY() < y && tower.getY() + 50 > y) {
                                temp = tower;
                                found = true;
                            }
                        }
                        if (found) {
                            gameArea.addTower(temp, type);
                            gameArea.changeShowTower();
                        }
                    } else {
                        /*TODO upgrade*/

                    }
                }
            }
        });
        {//Lives Label
            heart = new JLabel();
            heart.setIcon(new ImageIcon("src/data/pngs/heart.png"));
            heart.setBounds(640, 25, 35, 30);
            heart.setBorder(null);
            heart.setBackground(null);

            lives = new JLabel(Integer.toString(gameArea.getPlayerLives()));
            lives.setHorizontalAlignment(JLabel.CENTER);
            lives.setPreferredSize(new Dimension(150, 40));
            lives.setLayout(null);
            lives.setBounds(635, 20, 150, 40);
            lives.setVisible(true);
            lives.setBorder(BorderFactory.createLineBorder(Color.black));
            lives.setOpaque(true);
            lives.setBackground(new java.awt.Color(189, 189, 189));
        }

        {//Money Label
            coin = new JLabel();
            coin.setIcon(new ImageIcon("src/data/pngs/coin.png"));
            coin.setBounds(820, 25, 30, 30);
            coin.setBorder(null);
            coin.setBackground(null);

            money = new JLabel(Integer.toString(gameArea.getPlayerMoney()));
            money.setHorizontalAlignment(JLabel.CENTER);
            money.setPreferredSize(new Dimension(150, 40));
            money.setLayout(null);
            money.setBounds(815, 20, 150, 40);
            money.setVisible(true);
            money.setBorder(BorderFactory.createLineBorder(Color.black));
            money.setOpaque(true);
            money.setBackground(new java.awt.Color(189, 189, 189));
        }

        {//Pause Button
            pause = new JButton();
            //pause.setBorder(BorderFactory.createLineBorder(Color.black));
            pause.setIcon(new ImageIcon("src/data/pngs/pause.png"));
            pause.setBounds(1540, 20, 40, 40);
            pause.setBorder(null);
            pause.setBackground(null);

            //NEW
            pause.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pauseGame();
                }
            });
        }

        {//Level Label
            level = new JLabel("Level " + Integer.toString(gameArea.getLevelNum()));
            level.setHorizontalAlignment(JLabel.CENTER);
            level.setBounds(10, 20, 100, 40);
            level.setOpaque(true);
            level.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            level.setBackground(new java.awt.Color(189, 189, 189));
        }

        {//Tower1 Button + Cost Label
            /*AFROMAGYAR CSOPORT*/
            tower = new JButton();
            tower.setIcon(affordable(10, 1));
            tower.setBounds(1432, 780, 50, 50);
            tower.setBorder(null);
            tower.setBackground(null);
            tower.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (gameArea.getPlayerMoney() >= 10) {
                        gameArea.changeShowTower();
                        type = 1;
                    }
                }
            });

            towerCost = new JLabel("10");
            towerCost.setHorizontalAlignment(JLabel.CENTER);
            towerCost.setBounds(1432, 835, 50, 20);
            towerCost.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost.setOpaque(true);
            towerCost.setBackground(new java.awt.Color(189, 189, 189));

            /*KEREKESSZÉKES*/
            tower2 = new JButton();
            tower2.setIcon(affordable(15, 2));
            tower2.setBounds(1370, 780, 50, 50);
            tower2.setBorder(null);
            tower2.setBackground(null);
            tower2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (gameArea.getPlayerMoney() >= 15) {
                        gameArea.changeShowTower();
                        type = 2;
                    }
                }
            });

            towerCost2 = new JLabel("15");
            towerCost2.setHorizontalAlignment(JLabel.CENTER);
            towerCost2.setBounds(1370, 835, 50, 20);
            towerCost2.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost2.setOpaque(true);
            towerCost2.setBackground(new java.awt.Color(189, 189, 189));

            /*ELLENŐR*/
            tower3 = new JButton();
            tower3.setIcon(affordable(20, 3));
            tower3.setBounds(1308, 780, 50, 50);
            tower3.setBorder(null);
            tower3.setBackground(null);
            tower3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (gameArea.getPlayerMoney() >= 20) {
                        gameArea.changeShowTower();
                        type = 3;
                    }
                }
            });
            towerCost3 = new JLabel("20");
            towerCost3.setHorizontalAlignment(JLabel.CENTER);
            towerCost3.setBounds(1308, 835, 50, 20);
            towerCost3.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost3.setOpaque(true);
            towerCost3.setBackground(new java.awt.Color(189, 189, 189));

        }
        { //start round 
            startRound = new JButton();
            startRound.setIcon(new ImageIcon("src/data/pngs/go.png"));
            startRound.setBounds(1483, 730, 100, 100);
            startRound.setBorder(null);
            startRound.setBackground(null);
            startRound.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameArea.startTimer();
                }
            });

            roundCounter = new JLabel(Integer.toString(gameArea.getWave()) + "/10 rounds");
            roundCounter.setHorizontalAlignment(JLabel.CENTER);
            roundCounter.setBounds(1488, 835, 90, 20);
            roundCounter.setBorder(BorderFactory.createLineBorder(Color.black));
            roundCounter.setOpaque(true);
            roundCounter.setBackground(new java.awt.Color(189, 189, 189));

        }

        //NEW
        {/*PAUSE MENU*/

            //Pbg   TODO: Áttetsző réteg minden fölé

            pBg = new JLabel();
            pBg.setBounds(0, 0, 1600, 900);
            pBg.setBackground(new java.awt.Color(10, 10, 10, 200));

            pBg.setOpaque(true);

            //window
            pWindow = new JLabel();
            pWindow.setBounds(500, 275, 600, 350);
            pWindow.setBackground(new java.awt.Color(223, 197, 161, 150));
            pWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            pWindow.setOpaque(true);
           // pWindow.setOpaque(true);

            //resume btn
            pResume = new JButton("Resume");
            pResume.setBounds(600, 375, 400, 50);
            pResume.setBackground(new java.awt.Color(189, 189, 189));
            pResume.setHorizontalAlignment(JLabel.CENTER);//NEW
            pResume.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resumeGame();
                }
            });

            //exit btn
            pExit = new JButton("Exit");
            pExit.setBounds(600, 475, 400, 50);
            pExit.setBackground(new java.awt.Color(189, 189, 189));
            pExit.setHorizontalAlignment(JLabel.CENTER);
            pExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

        }
        gameArea.setLayout(null);
        gameArea.add(coin);
        gameArea.add(level);
        gameArea.add(heart);
        gameArea.add(lives);
        gameArea.add(money);
        gameArea.add(pause);
        gameArea.add(tower);
        gameArea.add(towerCost);
        gameArea.add(tower2);
        gameArea.add(towerCost2);
        gameArea.add(tower3);
        gameArea.add(towerCost3);
        gameArea.add(startRound);
        gameArea.add(roundCounter);

        frame = new JFrame("Catch The Bus ~ Anrope Studios©");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1600, 900));
        frame.setResizable(false);

        frame.getContentPane().add(gameArea);

        frame.pack();
        frame.setVisible(true);
    }

    //NEW
    public void pauseGame() {
        gameArea.setPaused(true);  //Megállítja a játékot
        gameArea.remove(pause);
        gameArea.add(pResume);
        gameArea.add(pExit);
        gameArea.add(pWindow);
        
    }

    //NEW
    public void resumeGame() {
        gameArea.setPaused(false);  //Elindítja a játékot
        gameArea.remove(pWindow);
        gameArea.remove(pResume);
        gameArea.remove(pExit);
        gameArea.add(pause);
    }

    public static void refreshLives(int l) {
        lives.setText(Integer.toString(l));
    }

    public static void refreshWaves(int w) {
        roundCounter.setText(Integer.toString(w) + "/10 rounds");
    }

    public static void refreshLevel(int a) {
        level.setText("Level " + Integer.toString(a));
    }

    public static void refreshMoney(int a) {
        money.setText(Integer.toString(a));
    }

    public static void refreshCost1(int c) {
        towerCost.setText(Integer.toString(c));
    }

    public static void refreshCost2(int c) {
        towerCost2.setText(Integer.toString(c));
    }

    public static void refreshCost3(int c) {
        towerCost3.setText(Integer.toString(c));
    }

    public static ImageIcon affordable(int x, int type) {
        if (gameArea.getPlayerMoney() < x) {
            switch (type) {
                case 1:
                    return new ImageIcon("src/data/pngs/crowred.png");
                case 2:
                    return new ImageIcon("src/data/pngs/disabred.png");
                case 3:
                    return new ImageIcon("src/data/pngs/incogred.png");
                default:
                    return new ImageIcon("src/data/pngs/x.png");
            }
        } else {
            switch (type) {
                case 1:
                    return new ImageIcon("src/data/pngs/crowgrey.png");
                case 2:
                    return new ImageIcon("src/data/pngs/disabgrey.png");
                case 3:
                    return new ImageIcon("src/data/pngs/incoggrey.png");
                default:
                    return new ImageIcon("src/data/pngs/x.png");
            }
        }
    }
    
    
    public static void refreshImage(){
        tower.setIcon(affordable(10,1));
        tower2.setIcon(affordable(15,2));
        tower3.setIcon(affordable(20,3));
    }
}
