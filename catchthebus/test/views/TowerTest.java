package views;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

public class TowerTest {

    @Test
    public void TestCreateTower() {
        Image img = new ImageIcon("src/data/pngs/crowgrey.png").getImage();
        Tower tower = new Tower(200, 200, 50, 50, 20, 20,0,1, img);
        assertTrue("CreateTower x value", 185 == tower.createTower(20, 20,0,1, img).getX());
        assertTrue("CreateTower y value", 185 == tower.createTower(20, 20,0,1, img).getY());
    }

    /*@Test
    public void TestRefundCost() {
        Player player = new Player(100,50);
    }*/
 /*@Test
    public voide TestUpgrade() {
    }*/
    @Test
    public void TestInRange() {
        Image img = new ImageIcon("src/data/pngs/crowgrey.png").getImage();
        Tower tower = new Tower(200, 200, 50, 50, 20, 20,0,1, img);
        Image itImage = new ImageIcon("src/data/pngs/it_man.png").getImage();
        Enemy enemy = new Enemy(200, 200, 50, 50, itImage, 3, 25, true, 3, 2);
        assertTrue("Enemy in range True", tower.inRange(enemy));
    }

    @Test
    public void TestInRangeF() {
        Image img = new ImageIcon("src/data/pngs/crowgrey.png").getImage();
        Tower tower = new Tower(200, 200, 50, 50, 20, 20,0,1, img);
        Image itImage = new ImageIcon("src/data/pngs/it_man.png").getImage();
        Enemy enemy = new Enemy(700, 700, 50, 50, itImage, 3, 25, true, 3, 2);
        assertFalse("Enemy in range False", tower.inRange(enemy));
    }

}
