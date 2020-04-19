package views;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.Player;

public class Tower extends Sprite {

    private final int buyingCost;
    private int refundCost;
    private int upgradeCost;
    private int level = 1;
    private final int maxlevel = 10;
    private final double lastAttack;
    private double range;
    private double power;
    private final double modifierIncrease = 1.3; //range and attack increase by upgarde!
    private int timer = 0;
    private Enemy firstEnemy;
    private Bullet bullet;

    public Tower(int x, int y, int width, int height, double dmg, double range, Image image) {
        super(x, y, width, height, image);

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
    public Tower createTower(double dmg, double range, Image image) {
        return new Tower(x - 15, y - 15, 80, 80, dmg, range, image);
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
    public boolean upgrade(Tower tower, Player player) {
        if (level < maxlevel && player.getMoney() >= upgradeCost) {
            level += 1;
            player.addMoney(-1 * upgradeCost);
            if (level < 5) {
                /*TODO*/
                tower.setRange(getRange() * 1.3);
                tower.setPower(getPower() * 1.3);
            } else {
                /*TODO IDK which i have clicked on*/
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
                    firstEnemy.takeDamage(this.power);
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

    public boolean bulletCollide() {
        Rectangle rect = new Rectangle(firstEnemy.getX(), firstEnemy.getY(), firstEnemy.getWidth(), firstEnemy.getHeight());
        Rectangle otherRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        return rect.intersects(otherRect);
    }
    
    // GETTER - SETTER
    
    public int getBuyingCost() {
        return buyingCost;
    }

    public int getRefundCost() {
        return refundCost;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxlevel() {
        return maxlevel;
    }

    public double getLastAttack() {
        return lastAttack;
    }

    public double getRange() {
        return range;
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

    public double getModifierIncrease() {
        return modifierIncrease;
    }

    public Bullet getBullet() {
        return this.bullet;
    }

    public Enemy getFirstEnemy() {
        return firstEnemy;
    }
}
