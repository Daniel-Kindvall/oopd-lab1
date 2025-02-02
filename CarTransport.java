import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck{
    Stack<Car> carStack;
    int carStackSize = 4;
    int maxCarSize = 2;
    public CarTransport(){
        super(2, 400, Color.blue, "Lada", 3, 0.5);
    }

    public void raiseCargoBed(){
        super.raiseCargoBed(1);
    }

    public void lowerCargoBed(){
        super.lowerCargoBed(70);
    }

    private double calculateDistance(Car car) {
        double[] difference = new double[2];
        difference[0] = car.getPosition()[0] - this.getPosition()[0];
        difference[1] = car.getPosition()[1] - this.getPosition()[1];
        // Return the result of the pythagorean theorem.
        return (Math.sqrt(Math.pow(difference[0], 2.0) + Math.pow(difference[1], 2.0)));
    }

    public void loadCar(Car car) {
        if (
            this.getCargoBedAngle() > 0 &&
            calculateDistance(car) < 12.5 &&
            car.getSize() <= maxCarSize &&
            carStack.size() >= carStackSize
        ) {
            car.addMovementHindrance("isLoadedOnTransport");
            carStack.push(car);
            car.stopEngine();
            car.setPosition(this.getPosition());
        }
    }

    public void unloadCar() {
        if (this.getCargoBedAngle() > 0 ) {
            Car car = carStack.pop();
            double[] carPos = new double[2];
            carPos[0] = this.getPosition()[0] - this.getDirection()[0] * 10;
            carPos[1] = this.getPosition()[1] - this.getDirection()[1] * 10;
            car.setPosition(carPos);
            car.removeMovementHindrance("isLoadedOnTransport");
        }
    }

    @Override
    public void move() {
        getPosition()[0] = getDirection()[0] * getCurrentSpeed() + getPosition()[0];
        getPosition()[1] = getDirection()[1] * getCurrentSpeed() + getPosition()[1];
        
        for (int index = 0; index < carStack.size(); index++) {
            carStack.elementAt(index).getPosition()[0] = carStack.elementAt(index).getDirection()[0] * carStack.elementAt(index).getCurrentSpeed() + carStack.elementAt(index).getPosition()[0];
            carStack.elementAt(index).getPosition()[1] = carStack.elementAt(index).getDirection()[1] * carStack.elementAt(index).getCurrentSpeed() + carStack.elementAt(index).getPosition()[1];
        }
    }
}