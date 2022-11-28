package Model;

import java.util.Random;
import Util.Constant;

public class AIPlayer extends Player {
    double xDirection;
    boolean stay = false;
    double nAngle;
    
    public AIPlayer(int _x, int _y, String _side) {
        super(_x, _y, _side);
        updateDestination();
        nAngle = base_angle;
    }

    public void moveInDirection(){
        if (stay) return;
        if(xDirection > x - 5 && xDirection < x + 5) updateDestination();
        if(xDirection < x) moveLeft();
        else moveRight();
    }

    private void updateDestination() {
        xDirection = new Random().nextDouble() * (Constant.FIELD_WIDTH - Constant.PLAYER_WIDTH);
    }

    public void stay(boolean s) {
        stay = s;
    }

    public void updateAngle(double angle) {
        nAngle = base_angle - angle;
    }

    public void resetAngle() {
        nAngle = base_angle;
    }

    public boolean moveAngle() {
        if (Math.round(angle) == Math.round(nAngle)) return true;
        if (angle < nAngle) turnLeft();
        else turnRight();
        return false;
    }

    public void setxDirection(double x) {
        xDirection = x;
    }
}
