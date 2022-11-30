package Model;

import java.util.Random;
import Util.Constant;

public class AIPlayer extends Player {
    double xDirection;
    boolean stay = false;
    double nAngle;

    /**
     * Constructeur du model de l'IA
     */
    public AIPlayer(int _x, int _y, String _side) {
        super(_x, _y, _side);
        updateDestination();
        nAngle = base_angle;
    }

    /**
     * Déplace le personnage dans la direction xDirection
     */
    public void moveInDirection(){
        if (stay) return;
        if(xDirection > x - 5 && xDirection < x + 5) updateDestination();
        if(xDirection < x) moveLeft();
        else moveRight();
    }

    /**
     * Met à jour le xDirection avec une nouvelle valeur aléatoire
     */
    private void updateDestination() {
        xDirection = new Random().nextDouble() * (Constant.FIELD_WIDTH - Constant.PLAYER_WIDTH);
    }

    /**
     * Permet de dire à l'IA de bouger ou non
     * @param s
     */
    public void stay(boolean s) {
        stay = s;
    }

    /**
     * Met à jour l'angle de tir
     */
    public void updateAngle(double angle) {
        nAngle = base_angle - angle;
    }

    /**
     * Remet l'angle de tir au milieu
     */
    public void resetAngle() {
        nAngle = base_angle;
    }

    /**
     * Modifie l'angle pour arriver vers l'angle souhaité nAngle
     */
    public boolean moveAngle() {
        if (Math.round(angle) == Math.round(nAngle)) return true;
        if (angle < nAngle) turnLeft();
        else turnRight();
        return false;
    }

    /**
     * Met à jour la xDirection à x passé en parametre
     */
    public void setxDirection(double x) {
        xDirection = x;
    }
}
