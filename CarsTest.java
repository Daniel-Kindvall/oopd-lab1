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

        for (int i = 0; i < 10; i++) {
            volvo.gas(1);
        }

        volvo.move();
        Assert.assertEquals(12.6, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);
        volvo.move();
        Assert.assertEquals(25.2, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);
    }

    public void volvoTestTurning() {
        Assert.assertEquals(1, volvo.getDirection()[0], 0.0001);
        Assert.assertEquals(0, volvo.getDirection()[1], 0.0001);
        volvo.turnLeft();
        Assert.assertEquals(0, volvo.getDirection()[0], 0.0001);
        Assert.assertEquals(1, volvo.getDirection()[1], 0.0001);
        volvo.move();
        Assert.assertEquals(25.2, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(12.6, volvo.getPosition()[1], 0.0001);
        
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.move();
        Assert.assertEquals(37.8, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(12.6, volvo.getPosition()[1], 0.0001);

        volvo.turnRight();
        volvo.turnRight();
        volvo.move();
        volvo.move();
        volvo.move();
        volvo.move();
        Assert.assertEquals(-12.6, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(12.6, volvo.getPosition()[1], 0.0001);
    }

    public void volvoTestGas() {
        double previousSpeed = volvo.getCurrentSpeed();
        volvo.gas(-4);
        Assert.assertEquals(previousSpeed, volvo.getCurrentSpeed(), 0);
        volvo.gas(1000);
        Assert.assertEquals(previousSpeed + 0.01*1.25*100, volvo.getCurrentSpeed(), 0);
        volvo.gas(0.3);
        Assert.assertEquals(14.225, volvo.getCurrentSpeed(), 0);

        // Make sure that no matter how much we gas, the speed will never go above the engine power.
        for (int i = 0; i < 100000; i++) {
            volvo.gas(1);
        }
        Assert.assertTrue(volvo.getCurrentSpeed() > previousSpeed);
        Assert.assertEquals(volvo.getCurrentSpeed(), volvo.getEnginePower(), 0);

    }
    public void volvoTestBrake() {
        double previousSpeed = volvo.getCurrentSpeed();
        volvo.brake(-4);
        Assert.assertEquals(previousSpeed, volvo.getCurrentSpeed(), 0);
        volvo.brake(1000);
        Assert.assertEquals(previousSpeed - 0.01*1.25*100, volvo.getCurrentSpeed(), 0);
        volvo.brake(0.3);
        Assert.assertEquals(98.375, volvo.getCurrentSpeed(), 0);

        volvo.setNrDoors(2);
        Assert.assertEquals(2, volvo.getNrDoors());
        volvo.setColor(Color.green);
        Assert.assertEquals(Color.green, volvo.getColor());
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
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0.0001);

        for (int i = 0; i < 10; i++) {
            saab.gas(1);
        }

        saab.move();
        Assert.assertEquals(12.6, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(0, saab.getPosition()[1], 0.0001);
        saab.move();
        Assert.assertEquals(25.2, saab.getPosition()[0], 0.0001);
        Assert.assertEquals(0, saab.getPosition()[1], 0.0001);

    }

    public void saabTestTurbo() {
        saab.stopEngine();
        Assert.assertEquals(0.0, saab.getCurrentSpeed(), 0);

        saab.startEngine();
        Assert.assertEquals(0.1, saab.getCurrentSpeed(), 0);

        saab.setTurboOn();
        Assert.assertTrue(saab.getTurboStatus());

        for (int i = 0; i < 10; i++) {
            saab.gas(1);
        }

        Assert.assertEquals(16.35, saab.getCurrentSpeed(), 0.0001);

        saab.setTurboOff();
        Assert.assertFalse(saab.getTurboStatus());
    }

}