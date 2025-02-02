import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class CarsTest {
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();
    Scania scania = new Scania();
    CarTransport cartransport = new CarTransport();

    // Volvo tests
    @Test
    public void volvoInstantiation() {
        Assert.assertEquals(4, volvo.getNrDoors());
        Assert.assertEquals(100.0, volvo.getEnginePower(), 0);
        Assert.assertEquals(Color.black, volvo.getColor());
        Assert.assertEquals("Volvo240", volvo.getModelName());
        Assert.assertEquals(0.0, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void volvoMovement() {
        volvo.startEngine();
        Assert.assertEquals(0.1, volvo.getCurrentSpeed(), 0.0001);

        volvo.isMoveable();

        for (int i = 0; i < 10; i++) {
            volvo.gas(1);
        }

        volvo.move();
        Assert.assertEquals(12.6, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);
        volvo.move();
        Assert.assertEquals(25.2, volvo.getPosition()[0], 0.0001);
        Assert.assertEquals(0, volvo.getPosition()[1], 0.0001);


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

        previousSpeed = volvo.getCurrentSpeed();
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
    public void saabInstantiation() {
        Assert.assertEquals(2, saab.getNrDoors());
        Assert.assertEquals(125.0, saab.getEnginePower(), 0);
        Assert.assertEquals(Color.red, saab.getColor());
        Assert.assertEquals("Saab95", saab.getModelName());
        Assert.assertEquals(0.0, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void saabMovement() {
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

    @Test
    public void scaniaCargoBed(){
        Scania scania = new Scania();
        scania.startEngine();
        scania.gas(1);
        scania.raiseCargoBed(10);
        Assert.assertEquals(0, scania.getCargoBedAngle(), 0);
        //Add more tests (if you feel like it)
    }

    // Truck tests
    @Test
    public void truckCargoBedAngleConstraints() {
        // Check maximum limit.
        scania.raiseCargoBed(30);
        Assert.assertEquals(30, scania.getCargoBedAngle(), 0.0001);
        scania.raiseCargoBed(90);
        Assert.assertTrue(scania.getCargoBedAngle() <= 70);

        // Check minimum limit.
        scania.lowerCargoBed(30);
        Assert.assertEquals(40, scania.getCargoBedAngle(), 0.0001);
        scania.lowerCargoBed(90);
        Assert.assertTrue(scania.getCargoBedAngle() >= 0);

        // Ensure that negative values don't bypass the safety checks.
        scania.raiseCargoBed(-100);
        Assert.assertTrue(scania.getCargoBedAngle() <= 70 && scania.getCargoBedAngle() >= 0);
        scania.lowerCargoBed(-100);
        Assert.assertTrue(scania.getCargoBedAngle() <= 70 && scania.getCargoBedAngle() >= 0);
    }
    @Test
    public void truckCargoBedMovementConstraints() {
        scania.startEngine();
        scania.gas(1);
        double previousBedAngle = scania.getCargoBedAngle();
        scania.raiseCargoBed(70);
        Assert.assertEquals(previousBedAngle, scania.getCargoBedAngle(), 0.0001);
    }

    // Car transport tests
    @Test
    public void carTransportLoading() {

    }
    @Test
    public void carTransportUnloading() {

    }
    @Test
    public void carTransportStack() {

    }
    @Test
    public void carTransportLoadingSize() {

    }
    @Test
    public void carTransportLoadedCarPosition() {

    }
    @Test
    public void carTransportCapacity() {

    }


    // Car Workshop tests
    @Test
    public void workshopCapacity() {
        CarWorkshop<Saab95> workshop = new CarWorkshop<>(1);
        Saab95 car1 = new Saab95();
        workshop.addCar(car1); // First car added, filling up the capacity
        // Expect an Error when adding another car beyond capacity
        Assert.assertThrows(Error.class, () -> workshop.addCar(car1));

        workshop.removeCar(car1);
        // Expect an Error when removing a car that is not in the workshop.
        Assert.assertThrows(Error.class, () -> workshop.removeCar(car1));
    }

    // Test movement hindrance
    @Test
    public void testCarMovementHindrance() {
        CarTransport cartransport = new CarTransport();
        Saab95 car = new Saab95();
        car.setPosition(cartransport.getPosition());

        cartransport.raiseCargoBed();

        // Assure the transport is able to load the car
        Assert.assertEquals(1, car.getSize(), 0.0001);
        Assert.assertTrue(cartransport.getCargoBedAngle() > 0);
        Assert.assertEquals(cartransport.getPosition()[0], car.getPosition()[0], 0.0001);
        Assert.assertEquals(cartransport.getPosition()[1], car.getPosition()[1], 0.0001);

        // Make sure the car is loaded into the transport
        Assert.assertTrue(cartransport.loadCar(car));

        // Assure the car doesn't move when loaded
        Assert.assertEquals(false, car.isMoveable());
        car.startEngine();
        car.move();
        Assert.assertEquals(cartransport.getPosition()[0], car.getPosition()[0], 0.0001);
        Assert.assertEquals(cartransport.getPosition()[1], car.getPosition()[1], 0.0001);
        car.stopEngine();
    }
}