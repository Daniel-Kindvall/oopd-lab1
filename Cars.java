import java.awt.*;

abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    private double[] position = {0, 0};
    private double[] direction = {1, 0};

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public String getModelName() {
        return modelName;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    protected void setNrDoors(int amount){
        nrDoors = amount;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public Color getColor(){
        return color;
    }

    protected void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void stopEngine(){
	    currentSpeed = 0;
    }

    abstract double speedFactor();

    abstract void incrementSpeed(double amount);
    abstract void decrementSpeed(double amount);
    
    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        position[0] = direction[0] * getCurrentSpeed() + position[0];
        position[1] = direction[1] * getCurrentSpeed() + position[1];
    }

    @Override
    public void turnLeft() {
        double[] previousDirection = new double[5];
        System.arraycopy(direction, 0, previousDirection, 0, direction.length);
        direction[0] = -previousDirection[1];
        direction[1] = previousDirection[0];
    }

    @Override
    public void turnRight() {
        double[] previousDirection = new double[5];
        System.arraycopy(direction, 0, previousDirection, 0, direction.length);
        direction[1] = -previousDirection[0];
        direction[0] = previousDirection[1];
    }

    public double[] getPosition() {
        return position;
    }

    public double[] getDirection() {
        return direction;
    }
}

abstract class TurboCar extends Car {

    private boolean turboOn = false;

    public TurboCar(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
    }

    public boolean getTurboStatus() {
        return turboOn;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount){
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    @Override
    protected void decrementSpeed(double amount){
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }
}

abstract class TrimCar extends Car {

    private final double trimFactor;

    public TrimCar(int nrDoors, double enginePower, Color color, String modelName, double trimFactor){
        super(nrDoors, enginePower, color, modelName);
        this.trimFactor = trimFactor;
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount){
	    setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    @Override
    protected void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }
}