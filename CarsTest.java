import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class CarsTest {
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();

    // Volvo tests
    @Test
    public void testVolvoInstantiation() {
        Assert.assertEquals(4, volvo.getNrDoors());
        Assert.assertEquals(100.0, volvo.getEnginePower(), 0);
        Assert.assertEquals(Color.black, volvo.getColor());
        Assert.assertEquals("Volvo240", volvo.getModelName());
        Assert.assertEquals(0.0, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testVolvoMovement() {
        volvo.startEngine();
        Assert.assertEquals(0.1, volvo.getCurrentSpeed(), 0.0001);

        volvo.incrementSpeed(50);
        Assert.assertEquals(62.6, volvo.getCurrentSpeed(), 0.0001);

        volvo.move();
        Assert.assertEquals(62.6, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);
        volvo.move();
        Assert.assertEquals(125.2, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);


        Assert.assertEquals(1, volvo.getDirection()[0], 0.0001);
        Assert.assertEquals(0, volvo.getDirection()[1], 0.0001);
        volvo.turnLeft();
        Assert.assertEquals(0, volvo.getDirection()[0], 0.0001);
        Assert.assertEquals(1, volvo.getDirection()[1], 0.0001);
        volvo.move();
        Assert.assertEquals(125.2, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, volvo.getPosition()[1], 0.0001);
        
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.move();
        Assert.assertEquals(187.8, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, volvo.getPosition()[1], 0.0001);

        volvo.turnRight();
        volvo.turnRight();
        volvo.move();
        volvo.move();
        volvo.move();
        volvo.move();
        Assert.assertEquals(-62.6, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, volvo.getPosition()[1], 0.0001);


    }

    // Saab tests
    @Test
    public void testSaabInstantiation() {
        Assert.assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void testSaabMovement() {
        saab.startEngine();
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0);
    }
}