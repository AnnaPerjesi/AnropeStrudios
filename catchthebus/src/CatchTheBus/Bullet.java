/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catchthebus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Bullet extends Sprite {

    //Is it drawn
    public boolean visible;

    //Bullet directions
    private int dirX;
    private int dirY;
    private boolean hasDir;

    //Default position
    private int defX;
    private int defY;

    Image image;

    public Bullet(int x, int y, int height, int width, Image image) {
        super(x, y, width, height, image);
        defX = x;
        defY = y;
        visible = false;
        hasDir = false;
    }

    public void show() {
        visible = true;
    }

    public void move(Enemy enemy) {
        dirX = enemy.getX();
        dirY = enemy.getY();
        if (getHasDir()) {
            if (x < dirX) {
                x += 25;
            } else if (x > dirX) {
                x -= 25;
            }
            if (y < dirY) {
                y += 25;
            } else if (y > dirY) {
                y -= 25;
            }
        }
        if ((Math.abs(x - dirX) < 25 && Math.abs(y - dirY) < 25) || collideEnemy(enemy)){
            hit();
        }
    }

    public void hit() {
        visible = false;
        x = defX;
        y = defY;
        hasDir = false;
    }

    public boolean collideEnemy(Enemy enemy) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        return rect.intersects(otherRect);
    }

    public boolean getVisibility() {
        return visible;
    }

    public void setHasDir(int x, int y) {
        hasDir = true;
    }
    public boolean getHasDir(){
        return hasDir;
    }
}
