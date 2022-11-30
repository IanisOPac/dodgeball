package Model;
import Util.Constant;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import java.util.Random;

public abstract class Player {
    protected double x, y, speed, base_angle, angle;
    protected int side = -1;
    private boolean alive = true;

    public Player(int _x, int _y, String _side) {
        x = _x;
        y = _y;
        base_angle = 0;

        if(_side.equals("top")) {
            side = -side;
            base_angle = 180;
        }
        angle = base_angle;

        // Tous les joueurs ont une vitesse aleatoire entre 3.0 et 3.49
        Random randomGenerator = new Random();
        speed = Constant.PLAYER_SPEED + randomGenerator.nextDouble() / 2;
    }

    public void moveLeft() {
        if (x > 0) x -= speed;
    }

    public void moveRight() {
        if (x + Constant.PLAYER_WIDTH < Constant.FIELD_WIDTH) x += speed;
    }

    public void turnLeft() {
        if (angle < base_angle + 60) angle += 1;
    }

    /**
     *  Rotation du joueur vers la droite
     */
    public void turnRight() {
        if (angle > base_angle - 60) angle -=1;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    /**
     * Renvoie la BoundingBox de l'objet, sert à gérer les collisions
     */
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x, y, Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT);
    }

    /**
     * Renvoie l'angle de rotation du joueur
     * sert à afficher la flèche avec la bonne rotation
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Renvoie l'angle de tir, en fonction de l'angle de rotation du joueur
     * Permet de tirer le projectile dans le bon sens
     */
    public double getShootAngle() {
        return angle - 90;
    }

    public int getSide() {
        return side;
    }

    public boolean alive() {
        return alive;
    }

    /**
     * Met le joueur dans l'état "mort"
     */
    public void die() {
        speed = 0;
        alive = false;
    }
}
