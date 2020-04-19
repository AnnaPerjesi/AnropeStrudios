/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Player {
    private int money = 0;
    private int lives;
    private final int STARTLIVES = 100;
    private final int STARTMONEY = 50;
    private Player player;

    public Player(int lives, int money) {
        this.lives = 100;
        this.money = 50;
    }
   
    public void reset(){
        this.lives = STARTLIVES;
        this.money = STARTMONEY;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void addMoney(int amount){
        this.money += amount;
        //GameGUI.refreshMoney(this.money);
    }
    
    public void decreaseLife(int x){
        this.lives = this.lives - x;
       // GameGUI.refreshLives(this.lives);
    }
    
    public void addLife(int x){
        this.lives = this.lives + x;
        //GameGUI.refreshLives(this.lives);
    }

    public int getMoney() {
        return this.money;
    }

    public int getLives() {
        return this.lives;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
