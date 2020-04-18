package test;

import org.junit.Assert;
import org.junit.Test;

import catchthebus.Player;

public class playerTest {

	
    /**
     * Test of addLife method, of class Player.
     */
    @Test
    public void testAddLife5() {
        System.out.println("addLife");
        int x = 5;
        Player instance = new Player();
        instance.addLife(x);
        Assert.assertTrue("Add life 5: ", 105==instance.getLives()); 
    }
    @Test
    public void testAddLife10() {
        System.out.println("addLife");
        int x = 10;
        Player instance = new Player();
        instance.addLife(x);
        Assert.assertTrue("Add life 5: ", 110==instance.getLives()); 
    }
    @Test
    public void testAddLife0() {
        System.out.println("addLife");
        int x = 0;
        Player instance = new Player();
        instance.addLife(x);
        Assert.assertTrue("Add life 5: ", 100==instance.getLives()); 
    }
    
    /**
     * Test of addMoney method, of class Player.
     */
    @Test
    public void testAddMoney0() {
        System.out.println("addMoney");
        int amount = 0;
        Player instance = new Player();
        instance.addMoney(amount);
        Assert.assertTrue("Add money 10: ", 50==instance.getMoney());
    }
    @Test
    public void testAddMoney15() {
        System.out.println("addMoney");
        int amount = 15;
        Player instance = new Player();
        instance.addMoney(amount);
        Assert.assertTrue("Add money 10: ", 65==instance.getMoney());
    }

    /**
     * Test of decreaseLife method, of class Player.
     */
    @Test
    public void testDecreaseLife0() {
        System.out.println("decreaseLife");
        int x = 0;
        Player instance = new Player();
        instance.decreaseLife(x);
        Assert.assertTrue("Decrease life 5:", 100 == instance.getLives());
    }
    @Test
    public void testDecreaseLife5() {
        System.out.println("decreaseLife");
        int x = 5;
        Player instance = new Player();
        instance.decreaseLife(x);
        Assert.assertTrue("Decrease life 5:", 95 == instance.getLives());
    }
}
