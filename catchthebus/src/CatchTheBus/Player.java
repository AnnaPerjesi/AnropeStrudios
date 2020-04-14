/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;


public class Player {
    private static int money = 0;
    private static int lives;
    private final int STARTLIVES = 100;
    private final int STARTMONEY = 0;
    private static Player player;
    
    public void reset(){
        this.lives = STARTLIVES;
        this.money = STARTMONEY;
    }
    
    public static Player getPlayer(){
        return player;
    }
    
    public void addMoney(int amount){
        this.money += amount;
        GameGUI.refreshMoney(this.money);
    }
    
    public void decreaseLife(int x){
        this.lives = this.lives - x;
        GameGUI.refreshLives(this.lives);
    }
    public void addLife(int x){
        this.lives = this.lives + x;
        GameGUI.refreshLives(this.lives);
    }

    public int getMoney() {
        return money;
    }

    public static int getLives() {
        return lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }      
}
