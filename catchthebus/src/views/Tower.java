package views;

import java.awt.Image;
import java.util.ArrayList;
import model.Player;

public class Tower extends Sprite {

    private final int buyingCost;
    private int refundCost;
    private int upgradeCost;
    private int level;
    private final int maxlevel;
    private final double lastAttack;
    private double range;
    private double power;
    private int timer;
    private Enemy firstEnemy;
    private Bullet bullet;
    private int type;
    private int countShoot;

    public Tower(int x, int y, int width, int height, double dmg, double range, int worth, int type, Image image) {
        super(x, y, width, height, image);
        this.refundCost = worth;
        this.buyingCost = 15;
        this.power = dmg;
        this.range = range;
        this.lastAttack = 0;
        this.countShoot = 0;
        this.type = type;
        this.timer = 0;
        this.level = 1;
        this.maxlevel = 10;
        this.setBasicUpgradeCost(type);
    }

    /**
     * Method to create new Tower object
     *
     * @param dmg
     * @param range
     * @param image
     * @return
     */
    public Tower createTower(double dmg, double range, int worth, int type, Image image) { //Creates real towers instead of X-es
        return new Tower(x - 15, y - 15, 80, 80, dmg, range, worth, type, image);
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
     * If the player has got enough money and the tower is not at max lvl ->
     * UPGRADE
     *
     * @param tower
     * @param player
     * @return
     */
    public boolean upgrade(Player player, int evolvePath) {
        if (level < maxlevel && player.getMoney() >= this.getUpgradeCost()) {
            level += 1;
            player.addMoney(-1 * this.getUpgradeCost());
            this.setUpgradeCost((int) (this.getUpgradeCost() * this.getLevel() * 0.5));
            this.increaseRefoundCost();
            if (level != 5) {
                this.setPower(getPower() * 1.1);
            } else {
                //level += 1;
                switch (this.type) {
                    case 2:
                        //disab
                        if (evolvePath == 1) {
                            //1. evolve
                            //FAGYASZT
                            level += 1;
                        } else if(evolvePath == 2){
                            //2. evolve
                            //LASSÍT
                            level += 1;
                        }
                        break;
                    case 3:
                        //incog
                        if (evolvePath == 1) {
                            //1. evolve
                            /*MINDEN 5. lövésnél azonnal öl*/
                            if(getCountShoot() == 5){
                                firstEnemy.kill();
                            }
                            level += 1;
                        } else if(evolvePath == 2){
                            //2. evolve
                            //SASSZEM
                            this.setRange(this.getRange()*2);
                            level += 1;
                        }
                        break;
                    default:
                        //afromagyarok
                        if (evolvePath == 1) {
                            //1. evolve
                            //MINDENKIT ÖL
                            level += 1;
                        } else if (evolvePath == 2) {
                            //2. evolve
                            //DUPLA SEBZÉS
                            this.setPower(this.getPower()*2);
                            level += 1;
                        }
                        break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Depends on timer, tower shoot bullet to the first enemy that is in
     * tower's range
     *
     * @param enemies
     * @param bullet
     */
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
                    countShoot++;
                    firstEnemy = enemy;
                    bullet.setHasDir(firstEnemy.getX(), firstEnemy.getY());
                    if (bullet.getVisibility()) {
                        hit();
                    }
                    timer = 0;
                }
                i++;
            }
        }
        if (countShoot >= 5) {
            countShoot = 0;
        }
    }

    /**
     * Enemy is in tower's range or not
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

    /**
     * if the bullet collides with the enemy, it takes damage
     */
    public void hit() {
        firstEnemy.takeDamage(this.power);
    }
    // GETTER - SETTER

    public int getBuyingCost() {
        return buyingCost;
    }

    public int getRefundCost() {
        return refundCost;
    }

    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    public void setBasicUpgradeCost(int type) {
        switch (this.type) {
            default:
                this.upgradeCost = 0;
                break;
            case 1:
                this.upgradeCost = 6;
                break;
            case 2:
                this.upgradeCost = 10;
                break;
            case 3:
                this.upgradeCost = 15;
                break;

        }
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
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

    public Image getImage() {
        return image;
    }

    public void increaseRefoundCost() {
        this.refundCost += (this.getUpgradeCost() / 2);
    }
/*NEW*/
    private int getCountShoot() {
        return countShoot;
    }

}
