/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;


public class Player {
    private int money = 0;
    private int lives;
    private final int STARTLIVES = 100;
    private final int STARTMONEY = 0;
    
    public void reset(){
        this.lives = STARTLIVES;
        this.money = STARTMONEY;
    }
    
    public void addMoney(int amount){
        this.setMoney(this.getMoney()+amount);
       // this.money += amount;
    }
    
    public void decreaseLife(){
        this.setLives(this.getLives()-1);
       // this.lives--;
    }

    public int getMoney() {
        return money;
    }

    public int getLives() {
        return lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    
    
}
