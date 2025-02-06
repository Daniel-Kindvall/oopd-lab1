import java.awt.*;
import java.util.Stack;

public class CarTransport {
    Stack<Car> carStack = new Stack<>();
    int carStackSize = 4;
    int maxCarSize = 2;

    private Truck parent;
    public CarTransport(){
        // Create an anonymous class
        this.parent = new Truck(2, 400, Color.blue, "Lada", 3, 0.5){};
    }

    /* Car methods */
    public void startEngine() {
        parent.startEngine();
    }

    public void gas(double amount) {
        parent.gas(amount);
    }

    public double[] getPosition() {
        return parent.getPosition();
    }

    public double[] getDirection() {
        return parent.getDirection();
    }

    public double getCurrentSpeed() {
        return parent.getCurrentSpeed();
    }

    public void setPosition(double[] newPos) {
        parent.setPosition(newPos);
    }

    /* Car transport specific methods */
    public int getMaxCarSize() {
        return maxCarSize;
    }

    public void openRamp(){
        parent.raiseCargoBed(1);
    }

    public void closeRamp(){
        parent.lowerCargoBed(70);
    }

    public boolean isRampOpen() {
        if (parent.getCargoBedAngle() > 0) {
            return true;
        }
        return false;
    }


    private double calculateDistance(Car car) {
        double[] difference = new double[2];
        difference[0] = car.getPosition()[0] - this.getPosition()[0];
        difference[1] = car.getPosition()[1] - this.getPosition()[1];
        // Return the result of the pythagorean theorem.
        return Math.abs(Math.sqrt(Math.pow(difference[0], 2.0) + Math.pow(difference[1], 2.0)));
    }

    public Boolean loadCar(Car car) {
        if (
            this.isRampOpen() &&
            calculateDistance(car) < 12.5 &&
            car.getSize() <= maxCarSize &&
            carStack.size() < carStackSize
        ) {
            car.addMovementHindrance("isLoadedOnTransport");
            carStack.push(car);
            car.stopEngine();
            car.setPosition(this.getPosition());
            return true;
        }
        return false;
    }

    public Car unloadCar() {
        if (this.isRampOpen() && this.carStack.size() > 0) {
            Car car = carStack.pop();
            double[] carPos = new double[2];
            carPos[0] = this.getPosition()[0] - this.getDirection()[0] * 10;
            carPos[1] = this.getPosition()[1] - this.getDirection()[1] * 10;
            car.setPosition(carPos);
            car.removeMovementHindrance("isLoadedOnTransport");
            return car;
        }
        if (!this.isRampOpen()) {
            throw new Error("The cargobed is closed.");
        } else {
            throw new Error("There are no cars on the car transport.");
        }
    }

    public void move() {
        double[] newPos = new double[2];
        newPos[0] = getDirection()[0] * getCurrentSpeed() + getPosition()[0];
        newPos[1] = getDirection()[1] * getCurrentSpeed() + getPosition()[1];
        this.setPosition(newPos);
        
        for (int index = 0; index < carStack.size(); index++) {
            carStack.elementAt(index).setPosition(newPos);
        }
    }
}