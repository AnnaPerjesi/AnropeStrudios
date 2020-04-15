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
    Image image;

    public Bullet(int x, int y,int height, int width, Image image) {
        super(x, y, width, height, image);
        this.visible = false;
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
    public void show(){
        this.visible = true;
    }
    public void move(Enemy enemy){
        //TODO move to dir
        this.visible = false;
    }
    public boolean getVisibility(){
        return this.visible;
    }
}
