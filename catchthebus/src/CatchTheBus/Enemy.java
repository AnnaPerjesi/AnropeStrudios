/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import catchthebus.Level.Pair;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Enemy extends Sprite {

    /*LINE DELETED*/
    private int speed = 25;
    /*NEW*/
    private int dmg;
    private int velx = 0;
    private int vely = speed;
    private boolean isAlive;
    private double health;

    private int counterDir = 0;

    public Enemy(int x, int y, int width, int height, Image image, int dmg, boolean isAlive) {
        super(x, y, width, height, image);
        this.health = 15;
        this.dmg = dmg;
        this.isAlive = isAlive;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void move(ArrayList<Pair> cords, String dir) {
        this.x += velx;
        this.y += vely;

        if (turn(cords)) {
            switch (dir.charAt(counterDir)) {
                case 'r':
                    velx = speed;
                    vely = 0;
                    break;
                case 'u':
                    velx = 0;
                    vely = -(speed);
                    break;
                case 'l':
                    velx = -(speed);
                    vely = 0;
                    break;
                case 'd':
                    velx = 0;
                    vely = speed;
                    break;
            }
            counterDir++;
        }

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public boolean turn(ArrayList<Pair> cords) {
        return (cords.get(counterDir).getX() == this.x && cords.get(counterDir).getY() == this.y);
    }

    public boolean getAlive() {
        return this.isAlive;
    }
    public double getHealth(){
        return this.health;
    }
    
    /**
     * Method tells us if enemies cathed the bus or not
     * @param bus
     * @return 
     */

    public boolean collidesBus(Bus bus) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(bus.x, bus.y, bus.width, bus.height);
        return rect.intersects(otherRect);
    }
    public void kill(){
        this.isAlive = false;
    }

    public boolean collideBullet(Bullet bullet) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        return rect.intersects(otherRect);
    }

    public int getDmg() {
        return this.dmg;
    }
    
    
    /**
     * Enemy get damage from tower (bullet) and this decrese it's health
     * @param damage 
     */
    public void takeDamage(double damage) {
        this.health -= damage;
        this.isAlive = (this.health > 0);
    }
}
