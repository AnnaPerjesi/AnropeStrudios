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
    private final JLabel money;
    private final JButton pause;
    private final JButton tower;
    private final JButton tower2;
    private final JButton tower3;
    private final JLabel towerCost;
    private final JLabel towerCost2;
    private final JLabel towerCost3;

    //NEW
    private final JLabel pBg;
    private final JLabel pWindow;
    private final JButton pResume;
    private final JButton pExit;
    private GameEngine gameArea;
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
                if (gameArea.getShowTowers()) {
                    boolean found = false;
                    int x = e.getX();
                    int y = e.getY();
                    for (Tower tower : towers) {
                        if (tower.getX() + 100 > x && tower.getX() < x && tower.getY() < y && tower.getY() + 100 > y) {
                            temp = tower;
                            found = true;
                        }
                    }
                    if (found) {
                        gameArea.addTower(temp, type);
                        gameArea.changeShowTower();
                    }
                }
            }
        });
        {//Lives Label
            heart = new JLabel();
            heart.setIcon(new ImageIcon("src/data/pngs/heart.png"));
            heart.setBounds(640, 25, 35, 30); //zavart, hogy levágja a szélét
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

        {//Tower1 Button + Cost Label
            /*AFROMAGYAR CSOPORT*/
            tower = new JButton();
            tower.setIcon(new ImageIcon("src/data/pngs/crowgrey.png"));
            tower.setBounds(1532, 780, 50, 50);
            tower.setBorder(null);
            tower.setBackground(null);
            tower.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameArea.changeShowTower();
                    type = 1;
                }
            });

            towerCost = new JLabel("cost");
            towerCost.setHorizontalAlignment(JLabel.CENTER);
            towerCost.setBounds(1532, 835, 50, 20);
            towerCost.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost.setOpaque(true);
            towerCost.setBackground(new java.awt.Color(189, 189, 189));

            /*KEREKESSZÉKES*/
            tower2 = new JButton();
            tower2.setIcon(new ImageIcon("src/data/pngs/disabgrey.png"));
            tower2.setBounds(1470, 780, 50, 50);
            tower2.setBorder(null);
            tower2.setBackground(null);
            tower2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameArea.changeShowTower();
                    type = 2;
                }
            });

            towerCost2 = new JLabel("cost");
            towerCost2.setHorizontalAlignment(JLabel.CENTER);
            towerCost2.setBounds(1470, 835, 50, 20);
            towerCost2.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost2.setOpaque(true);
            towerCost2.setBackground(new java.awt.Color(189, 189, 189));

            /*ELLENŐR*/
            tower3 = new JButton();
            tower3.setIcon(new ImageIcon("src/data/pngs/incoggrey.png"));
            tower3.setBounds(1408, 780, 50, 50);
            tower3.setBorder(null);
            tower3.setBackground(null);
            tower3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameArea.changeShowTower();
                    type = 3;
                }
            });

            towerCost3 = new JLabel("cost");
            towerCost3.setHorizontalAlignment(JLabel.CENTER);
            towerCost3.setBounds(1408, 835, 50, 20);
            towerCost3.setBorder(BorderFactory.createLineBorder(Color.black));
            towerCost3.setOpaque(true);
            towerCost3.setBackground(new java.awt.Color(189, 189, 189));

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
        gameArea.add(pWindow);
        gameArea.add(pResume);
        gameArea.add(pExit);
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
}
