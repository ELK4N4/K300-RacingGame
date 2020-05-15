package Client.Backend;

import BackandForth.CarColor;
import java.awt.image.BufferedImage;

public class Car {

    private CarColor carColor;
    private double carXPosition;
    private double carYPosition;
    private double carAngle;

    public Car(CarColor carColor) {
        this.carColor = carColor;
    }

    public CarColor getCarColor(){
        return carColor;
    }

    public double getCarXPosition() {
        return carXPosition;
    }

    public double getCarYPosition() {
        return carYPosition;
    }

    public double getCarAngle() {
        return carAngle;
    }

    public void setCarXPosition(double carXPosition) {
        this.carXPosition = carXPosition;
    }

    public void setCarYPosition(double carYPosition) {
        this.carYPosition = carYPosition;
    }

    public void setCarAngle(double carAngle) {
        this.carAngle = carAngle;
    }

    @Override
    public String toString() {
        return "Car{\n" +
                ", carColor=" + carColor +
                ", carXPosition=" + carXPosition +
                ", carYPosition=" + carYPosition +
                ", carAngle=" + carAngle +
                "\n}";
    }
}
