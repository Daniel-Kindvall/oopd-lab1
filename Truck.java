import java.awt.*;

public abstract class Truck extends TrimCar {
    private double cargoBedAngle = 0;
    public Truck(int nrDoors, double enginePower, Color color, String modelName, int carSize, double trimFactor){
        super(nrDoors, enginePower, color, modelName, carSize, trimFactor);
    }

    public double getCargoBedAngle() {
        return cargoBedAngle;
    }

    public void raiseCargoBed(double amount){
        if(getCurrentSpeed() > 0){
            return;
        }
        cargoBedAngle = cargoBedAngle + Math.abs(amount);
        if(cargoBedAngle > 70){
            cargoBedAngle = 70;
        }
        addMovementHindrance("cargoBedIsRaised");
    }

    public void lowerCargoBed(double amount){
        cargoBedAngle = cargoBedAngle - Math.abs(amount);
        if (cargoBedAngle <= 0) {
            cargoBedAngle = 0;
            removeMovementHindrance("cargoBedIsRaised");
        }
    }
}