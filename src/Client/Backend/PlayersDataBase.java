package Client.Backend;

import BackandForth.CarColor;
import BackandForth.Message;
import Client.Client;

import java.util.Arrays;

public class PlayersDataBase {
    
    static final int SUM_OF_ENEMIES = Message.SUM_OF_PLAYERS - 1;
    private Client client;
    private Car playersCar;
    private Car [] enemyCars;
    private double startingX;
    private double startingY;
    
    public PlayersDataBase(Client client) {
        this.client = client;
    }

    public void setStartingXY(CarColor playersColor) {
        startingX = 900;
        startingY = 900;
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
        x = 900;
        y = 900;
        client.setBackendXY(startingX, startingY);
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
            x = startingX + client.getCarWidth(enemyCars[i].getCarColor()) / 2.0;
            y = startingY + client.getCarHeight(enemyCars[i].getCarColor()) / 2.0;
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

    public double getStartingX() {
        return startingX;
    }

    public double getStartingY() {
        return startingY;
    }

    @Override
    public String toString() {
        return "PlayersDataBase{" +
                "playersCar=" + playersCar +
                ", enemyCars=" + Arrays.toString(enemyCars) +
                '}';
    }

}
