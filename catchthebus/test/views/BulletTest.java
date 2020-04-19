package views;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

public class BulletTest {
    @Test
    public void TestCollideEnemy() {
        Image itImage = new ImageIcon("src/data/pngs/it_man.png").getImage();
        Enemy enemy = new Enemy(200, 200, 50, 50, itImage, 3, true, 10, 2);
        Bullet bullet = new Bullet(200, 200, 20, 20, new ImageIcon("src/data/pngs/circle.png").getImage());
        assertTrue("Collide Test Enemy-Bullet",bullet.collideEnemy(enemy));
    }
}
