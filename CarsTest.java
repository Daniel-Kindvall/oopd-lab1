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


        double previousSpeed = volvo.getCurrentSpeed();
        volvo.gas(-4);
        Assert.assertEquals(previousSpeed, volvo.getCurrentSpeed(), 0);
        volvo.gas(1000);
        Assert.assertEquals(previousSpeed + 0.01*1.25*100, volvo.getCurrentSpeed(), 0);
        volvo.incrementSpeed(1000);
        Assert.assertTrue(volvo.getCurrentSpeed() > previousSpeed);
        Assert.assertEquals(volvo.getCurrentSpeed(), volvo.getEnginePower(), 0);
    }

    // Saab tests
    @Test
    public void testSaabInstantiation() {
        Assert.assertEquals(2, saab.getNrDoors());
        Assert.assertEquals(125.0, saab.getEnginePower(), 0);
        Assert.assertEquals(Color.red, saab.getColor());
        Assert.assertEquals("Saab95", saab.getModelName());
        Assert.assertEquals(0.0, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabMovement() {
        saab.startEngine();
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0);

        saab.startEngine();
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0.0001);

        saab.incrementSpeed(50);
        Assert.assertEquals(62.6, saab.getCurrentSpeed(), 0);



        saab.move();
        Assert.assertEquals(62.6, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(0, saab.getPosition()[1], 0.0001);
        saab.move();
        Assert.assertEquals(125.2, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(0, saab.getPosition()[1], 0.0001);


        Assert.assertEquals(1, saab.getDirection()[0], 0.0001);
        Assert.assertEquals(0, saab.getDirection()[1], 0.0001);
        saab.turnLeft();
        Assert.assertEquals(0, saab.getDirection()[0], 0.0001);
        Assert.assertEquals(1, saab.getDirection()[1], 0.0001);
        saab.move();
        Assert.assertEquals(125.2, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, saab.getPosition()[1], 0.0001);

        saab.turnLeft();
        saab.turnLeft();
        saab.turnLeft();
        saab.move();
        Assert.assertEquals(187.8, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, saab.getPosition()[1], 0.0001);

        saab.turnRight();
        saab.turnRight();
        saab.move();
        saab.move();
        saab.move();
        saab.move();
        Assert.assertEquals(-62.6, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(62.6, saab.getPosition()[1], 0.0001);


        saab.stopEngine();
        Assert.assertEquals(0.0, saab.getCurrentSpeed(), 0);

        saab.startEngine();
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0);

        saab.setTurboOn();
        Assert.assertTrue(saab.getTurboStatus());

        saab.incrementSpeed(50);
        Assert.assertEquals(0.1 + (125*1.3*0.01*50), saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasBreak(){





    }
}