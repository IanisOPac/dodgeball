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

        // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
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

    /**
     *  Deplacement en mode boost
     */
    void boost() {
        x += speed*2;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(x, y, Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT);
    }

    public double getAngle() {
        return angle;
    }

    public double getShootAngle() {
        return angle - 90;
    }

    public int getSide() {
        return side;
    }

    public boolean alive() {
        return alive;
    }

    public void die() {
        speed = 0;
        alive = false;
    }
}
