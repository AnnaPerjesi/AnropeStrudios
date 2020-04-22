package views;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import model.GameGUI;
import model.Player;

public class Tower extends Sprite {

    private final int buyingCost;
    private int refundCost;
    private int upgradeCost = 20;
    private int upgradeCost1 = 50;
    private int upgradeCost2 = 70;
    private int upgradeCost3 = 90;
    private int level = 1;
    private final int maxlevel = 10;
    private final double lastAttack;
    private double range;
    private double power;
    private int timer = 0;
    private Enemy firstEnemy;
    private Bullet bullet;

    public Tower(int x, int y, int width, int height, double dmg, double range,int worth, Image image) {
        super(x, y, width, height, image);
        this.refundCost = worth;
        this.buyingCost = 15;
        this.power = dmg;
        this.range = range;
        this.lastAttack = 0;
    }

    /**
     * Method to create new Tower object
     *
     * @param dmg
     * @param range
     * @param image
     * @return
     */
    public Tower createTower(double dmg, double range,int worth, Image image) { //Creates real towers instead of X-es
        return new Tower(x - 15, y - 15, 80, 80, dmg, range,worth, image);
    }

    /**
     * refund money to the player, when it sells tower
     *
     * @param player
     */
    public void refundTower(Player player) {
        player.addMoney(refundCost);
    }

    /**
     * If the player has got enough money and the tower is not full -> UPGRADE
     * IT
     *
     * @param tower
     * @param player
     * @return
     */
    public boolean upgrade(Tower tower, Player player, int type) {
        if (level < maxlevel && player.getMoney() >= upgradeCost) {
            level += 1;
            player.addMoney(-1 * upgradeCost);
            if (level != 5) {
                tower.setRange(getRange() * 1.05);
                tower.setPower(getPower() * 1.1);
            } else {
                switch (type) {
                    case 2:
                        //disab
                        player.setMoney(player.getMoney() - getUpgradeCost2());
                        
                        break;
                    case 3:
                        //incog
                        player.setMoney(player.getMoney() - getUpgradeCost3());
                        
                        break;
                    default:
                        //cigok
                        player.setMoney(player.getMoney() - getUpgradeCost1());
                        
                        break;
                }
                GameGUI.refreshMoney(player.getMoney());
            }
            return true;
        }
        return false;
    }

    public void shoot(ArrayList<Enemy> enemies, Bullet bullet) {
        boolean found = false;
        if (timer < 100) {
            timer++;
        } else {
            bullet.show();
            int i = 0;
            while (!found && i < enemies.size()) {
                Enemy enemy = enemies.get(i);
                if (inRange(enemy)) {
                    found = true;
                    firstEnemy = enemy;
                    bullet.setHasDir(firstEnemy.getX(), firstEnemy.getY());
                    if(bullet.getVisibility()){
                        hit();
                    }
                    timer = 0;
                }
                i++;
            }
        }
    }

    /**
     *
     * @param target
     * @return
     */
    public boolean inRange(Enemy target) {
        double a = Math.abs(target.getX() - this.x);
        double b = Math.abs(target.getY() - this.y);

        double z = Math.sqrt(a * a + b * b);

        return (z < this.getRange());
    }
    
    public void hit(){ //if the bullet collides with the enemy, it takes damage
        firstEnemy.takeDamage(this.power);
    }
    // GETTER - SETTER
    
    public int getBuyingCost() {
        return buyingCost;
    }

    public int getRefundCost() {
        return refundCost;
    }

    public int getUpgradeCost1() {
        return upgradeCost1;
    }

    public int getUpgradeCost2() {
        return upgradeCost2;
    }

    public int getUpgradeCost3() {
        return upgradeCost3;
    }

    public void setUpgradeCost1(int upgradeCost1) {
        this.upgradeCost1 = upgradeCost1;
    }

    public void setUpgradeCost2(int upgradeCost2) {
        this.upgradeCost2 = upgradeCost2;
    }

    public void setUpgradeCost3(int upgradeCost3) {
        this.upgradeCost3 = upgradeCost3;
    }

    

    public int getLevel() {
        return this.level;
    }

    public int getMaxlevel() {
        return maxlevel;
    }

    public double getLastAttack() {
        return lastAttack;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public Bullet getBullet() {
        return this.bullet;
    }

    public Enemy getFirstEnemy() {
        return firstEnemy;
    }
    public Image getImage(){
        return image;
    }
}
