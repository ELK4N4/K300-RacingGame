package Client.Backend;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Main;

import java.util.Arrays;

public class PlayersDataBase {
    
    public static final int SUM_OF_ENEMIES = Message.SUM_OF_PLAYERS - 1;
    private Main main;
    private Car playersCar;
    private Car [] enemyCars;
    private double startingX;
    private double startingY;
    
    public PlayersDataBase(Main main) {
        this.main = main;
    }

    public void setStartingXY(CarColor playersColor) {
        startingX = main.getWindowWidth() / 2.0;
        startingY = main.getWindowHeight() / 2.0 + 300;
        main.setBackendXY(startingX, startingY);
        setPlayers(playersColor);
    }

    private void setPlayers(CarColor playersColor) {
        setPlayer(playersColor);
        setEnemies(playersColor);
    }

    private void setPlayer(CarColor playersColor) {
        playersCar = new Car(playersColor);
        setPlayersInfo();
    }

    private void setPlayersInfo() {
        double x;
        double y;
        x = startingX + main.getCarWidth(playersCar.getCarColor()) / 2.0;
        y = startingY + main.getCarHeight(playersCar.getCarColor()) / 2.0;
        setCarInfo(playersCar, x, y, 0);
    }

    private void setEnemies(CarColor playersColor) {
        if(playersColor == CarColor.RED) {
            enemyCars = new Car[] {new Car(CarColor.BLUE), new Car(CarColor.YELLOW)};
        } else if(playersColor == CarColor.BLUE) {
            enemyCars = new Car[] {new Car(CarColor.RED), new Car(CarColor.YELLOW)};
        } else if(playersColor == CarColor.YELLOW) {
            enemyCars = new Car[] {new Car(CarColor.BLUE), new Car(CarColor.RED)};
        }
        setEnemyInfo();
    }

    private void setEnemyInfo(){
        double x;
        double y;
        for (int i = 0; i < SUM_OF_ENEMIES; i++) {
            x = startingX + main.getCarWidth(enemyCars[i].getCarColor()) / 2.0;
            y = startingY + main.getCarHeight(enemyCars[i].getCarColor()) / 2.0;
            setCarInfo(enemyCars[i], x, y, 0);
        }
    }

    private void setCarInfo(Car currentCar, double x, double y, double angle) {
        currentCar.setCarXPosition(x);
        currentCar.setCarYPosition(y);
        currentCar.setCarAngle(angle);
    }

    public void setPlayersInfo(double x, double y, double angle) {
        setCarInfo(playersCar, x, y, angle);
    }

    public void setCarInfo(CarColor carColor, double x, double y, double angle) {
        if(carColor == playersCar.getCarColor()) {
            setCarInfo(playersCar, x, y, angle);
        } else {
            setCarInfo(getEnemyCar(carColor), x, y, angle);
        }
    }

    private Car getEnemyCar(CarColor carColor) {
        for (Car car: enemyCars) {
            if(car.getCarColor() == carColor) {
                return car;
            }
        }
        throw new Error("Car color not found in enemy");
    }

    public CarColor getPlayersCarColor() {
        return playersCar.getCarColor();
    }

    public double getPlayersXPosition() {
        return playersCar.getCarXPosition();
    }

    public double getPlayersYPosition() {
        return playersCar.getCarYPosition();
    }

    public double getPlayersAngle() {
        return playersCar.getCarAngle();
    }

    public CarColor [] getEnemiesCarColors() {
        CarColor [] enemiesCarColors = new CarColor[SUM_OF_ENEMIES];
        for (int i = 0; i < SUM_OF_ENEMIES; i++) {
            enemiesCarColors[i] = enemyCars[i].getCarColor();
        }
        return enemiesCarColors;
    }

    public double [] getEnemyXPositions() {
        double [] enemyXPositions = new double[SUM_OF_ENEMIES];
        for (int i = 0; i < SUM_OF_ENEMIES; i++) {
            enemyXPositions[i] = enemyCars[i].getCarXPosition();
        }
        return enemyXPositions;
    }

    public double [] getEnemyYPositions() {
        double [] enemyYPositions = new double[SUM_OF_ENEMIES];
        for (int i = 0; i < SUM_OF_ENEMIES; i++) {
            enemyYPositions[i] = enemyCars[i].getCarYPosition();
        }
        return enemyYPositions;
    }

    public double [] getEnemyAngels() {
        double [] enemyAngels = new double[SUM_OF_ENEMIES];
        for (int i = 0; i < SUM_OF_ENEMIES; i++) {
            enemyAngels[i] = enemyCars[i].getCarAngle();
        }
        return enemyAngels;
    }

    @Override
    public String toString() {
        return "PlayersDataBase{" +
                "playersCar=" + playersCar +
                ", enemyCars=" + Arrays.toString(enemyCars) +
                '}';
    }

}
