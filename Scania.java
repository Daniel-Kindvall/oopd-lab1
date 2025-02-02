import java.awt.*;

public class Scania extends TrimCar{
    double cargoBedAngle;

    public Scania(){
        super(2, 400, Color.blue, "Scania", 0.5);
        this.cargoBedAngle = 0;
    }

    public double getCargoBedAngle() {
        return cargoBedAngle;
    }

    public void raiseCargoBed(double amount){
        if(getCurrentSpeed() > 0){
            return;
        }
        cargoBedAngle = cargoBedAngle + amount;
        if(cargoBedAngle > 70){
            cargoBedAngle = 70;
        }
    }

    public void lowerCargoBed(double amount){
        cargoBedAngle = cargoBedAngle + amount;
        if (cargoBedAngle < 0) {
            cargoBedAngle = 0;
        }
    }

    @Override
    public void move(){
        if(cargoBedAngle == 0){
            super.move();
        }
    }
}
