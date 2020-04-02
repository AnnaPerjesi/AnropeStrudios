/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class Bullet extends Sprite {

    private int x;
    private int y;
    private int width;
    private int height;
    private int velx;
    private int vely;
    Image image;

    public Bullet(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
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

}
