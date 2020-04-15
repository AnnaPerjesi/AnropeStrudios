package catchthebus;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tower extends Sprite {

    private int buyingCost;
    private int refundCost;
    private int upgradeCost;
    private int level = 1;
    private final int maxlevel = 10;
    /*IS IT ENOUHG?*/

    /**
     * *************************************
     */
    private final double lastAttack;
    /*COMMENT*/

    private double range;
    private double power;
    private double modifierIncrease = 1.3; //range and attack increase by upgarde!
    private static int timer = 0;
    private  Enemy firstEnemy;

    private Bullet bullet;

    public Tower(int x, int y, int width, int height, double dmg, double range, Image image) {
        super(x, y, width, height, image);
        this.buyingCost = 15;
        this.power = dmg;
        this.range = range;
        lastAttack = 0;
        this.bullet = new Bullet(this.x + 26, this.y + 26, 25, 25, new ImageIcon("src/data/pngs/circle.png").getImage());
    }

    /**
     * Method to create new Tower object
     *
     * @param image
     * @return
     */
    public Tower createTower(double dmg, double range, Image image) {
        return new Tower(x - 15, y - 15, 80, 80, dmg, range, image);
    }

    /**
     * refund money to the player, when it sells tower
     */
    public void refundTower() {
        Player.getPlayer().addMoney(refundCost);
    }

    /**
     * If the player has got enough money and the tower is not full -> UPGRADE
     * IT
     *
     * @return
     */
    public boolean upgrade(Tower tower) {
        if (level < maxlevel && Player.getPlayer().getMoney() >= upgradeCost) {
            level += 1;
            Player.getPlayer().addMoney(-1 * upgradeCost);
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

    public void shoot(ArrayList<Enemy> enemies) {
        if (timer < 10) {
            timer++;
        } else {
            showBullet();
            boolean found = false;
            int i = 0;
            while (!found && i < enemies.size()) {
                Enemy enemy = enemies.get(i);
                if (inRange(enemy)) {
                    found = true;
                    firstEnemy = enemy;
                }
                i++;
            }
            if(found){
                firstEnemy.takeDamage(this.power);
                moveBullet(firstEnemy);
                timer = 0;
            }

        }
    }

    /**
     *
     * @param target
     * @return
     */
    public boolean inRange(Enemy target) {
        double x = Math.abs(target.getX() - this.x);
        double y = Math.abs(target.getY() - this.y);

        double z = Math.sqrt(x * x + y * y);

        return (z < this.getRange());
    }

    public void showBullet() {
        this.bullet.show();
    }

    public void moveBullet(Enemy enemy) {
        this.bullet.move(enemy);
    }

    public Bullet getBullet() {
        return this.bullet;
    }
}
