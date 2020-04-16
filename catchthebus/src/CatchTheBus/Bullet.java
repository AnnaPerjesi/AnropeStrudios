/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Bullet extends Sprite {

    private int x;
    private int y;
    private int width = 25;
    private int height = 25;
    private int velx;
    private int vely;
    public boolean visible;
    Point location = new Point(0, 0);
    Image image;

    public Bullet(int x, int y, int height, int width, Image image) {
        super(x, y, width, height, image);
        this.x = x;
        this.y = y;
        //location.setLocation(x,y);
        this.visible = false;
        velx = 25;
        vely = 25;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelx() {
        return velx;
    }

    public int getVely() {
        return vely;
    }

    public void show() {
        this.visible = true;
    }

    public void move(Enemy enemy) {
        //TODO move to dir
        int eX = enemy.getX();
        int eY = enemy.getY();
        //System.out.println(this.x + ", " + this.y);
        
        if(this.x < eX){
            this.x += 50;
        } else if(this.x > eX){
            this.x -= 50;
        }
        
        if(this.y < eX){
            this.y += 50;
        } else if(this.y > eX){
            this.y -= 50;
        }
        
        
        
        
        //System.out.println(this.x + ", " + this.y);
        if(eX == this.x && eY == this.y){
            this.visible = false;
        }
    }

    public boolean getVisibility() {
        return this.visible;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
