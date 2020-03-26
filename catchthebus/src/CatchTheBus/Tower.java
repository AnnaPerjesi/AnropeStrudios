package catchthebus;
import catchthebus.Player;

public class Tower {
    
    //TODO enum?? FREEZE, DOUBLESHOOT..etc?
    private int buyingCost;
    private int refundCost;
    private int upgradeCost;
    private int level = 1;
    private final int maxlevel = 10; /*IS IT ENOUHG?*/
    private final double posX;
    private final double posY;
    /****************************************/
    private final double lastAttack; /*COMMENT*/
    private double range;
    private double power;
    private double modifierIncrease = 1.3; //range and attack increase by upgarde?
    
    public Tower(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        lastAttack = 0;
    }
    /**
     * refund money to the player, when it sells tower
     */
    public void refundTower(){
        Player.getPlayer().addMoney(refundCost);
    }  
    
    /**
     * If the player has got enough money and the tower is not full -> UPGRADE IT
     * @return 
     */
    public boolean upgrade(){
        if(level < maxlevel && Player.getPlayer().getMoney() >= upgradeCost){
            level += 1;
            Player.getPlayer().addMoney(-1*upgradeCost);
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

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getLastAttack() {
        return lastAttack;
    }

    public double getRange() {
        return range;
    }

    public double getPower() {
        return power;
    }

    public double getModifierIncrease() {
        return modifierIncrease;
    }   
    
}
