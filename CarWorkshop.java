import java.util.ArrayList;

public class CarWorkshop<T extends Car> {
    private ArrayList<T> cars = new ArrayList<T>();
    private int capacity;

    public CarWorkshop(int carCapacity) {
        this.capacity = carCapacity;
    }

    public void addCar(T car) {
        if (cars.size() < capacity) {
            cars.add(car);
            car.addMovementHindrance("isInWorkshop");
        } else {
            throw new Error("Verkstaden är full!");
        }
    }

    public T removeCar(T car) {
        if (cars.contains(car)) {
            cars.remove(car);
            car.removeMovementHindrance("isInWorkshop");
        } else {
            throw new Error("Vi hittade inte din bil!");
        }

        return car;
    }
}