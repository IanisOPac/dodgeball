package Model;
import Util.Constant;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

public class Projectile {
    double speed = Constant.PROJECTILE_SPEED;
    double x, y, angle, x_velocity, y_velocity;
    int side = 0;

    public Projectile(double _x, double _y, double _angle) {
        x = _x;
        y = _y;
        angle = _angle;
        updateTrajectory();
    }

    /**
     * Calcul la valeur de déplacement que le projectile doit prendre en x et en y
     * pour se déplacer avec l'angle donné
     */
    private void updateTrajectory() {
        double rad_angle = angle * Math.PI / 180;
        x_velocity = Math.cos(rad_angle);
        y_velocity = Math.sin(rad_angle);
    }

    public double posX() {
        if (collidingWall()) {
            angle = 180 - angle;
            updateTrajectory();
        }
        x += x_velocity * speed;
        return x;
    }
    
    public double posY() {
        if (collidingSide()) {
            speed = 0;
            side = 0;
        }
        y += y_velocity * speed;
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Fonction appelée pour que le projectile se déplace avec l'angle donné
     * @param angle
     */
    public void move(double angle) {
        this.angle = angle;
        updateTrajectory();
        speed = Constant.PROJECTILE_SPEED;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    /**
     * Renvoie la BoundingBox de l'objet, sert à gérer les collisions
     */
    public BoundingBox getBoundingBox() {
        return new BoundingBox(x, y, Constant.BALL_SIZE, Constant.BALL_SIZE);
    }

    public boolean idling() {
        return side == 0;
    }

    private boolean collidingWall() {
        return this.x + Constant.BALL_SIZE > Constant.FIELD_WIDTH || this.x < 0;
    }

    private boolean collidingSide() {
        return this.y + Constant.BALL_SIZE > Constant.FIELD_HEIGHT || this.y < 0;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int _side) {
        side = _side;
    }
}
