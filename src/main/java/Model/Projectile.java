package Model;
import Util.Constant;

import java.awt.*;

public class Projectile {
    double speed = Constant.PROJECTILE_SPEED;
    double x, y, angle, x_velocity, y_velocity;
    int side = 0;

    public Projectile(double _x, double _y, double _angle) {
        x = _x;
        y = _y;
        angle = _angle;
        updateAngle();
    }

    private void updateAngle() {
        double rad_angle = angle * Math.PI / 180;
        x_velocity = Math.cos(rad_angle);
        y_velocity = Math.sin(rad_angle);
    }

    public double posX() {
        if (collidingWall()) {
            angle = 180 - angle;
            updateAngle();
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(double angle, int side) {
        this.angle = angle;
        updateAngle();
        speed = 5;
        this.side = side;
    }

    public Point position() {
        return new Point((int) x,(int) y);
    }

    public boolean idling() {
        return side == 0;
    }

    private boolean collidingWall() {
        return this.x + Constant.BALL_SIZE > Constant.WINDOW_WIDTH || this.x < 0;
    }

    private boolean collidingSide() {
        return this.y + Constant.BALL_SIZE > Constant.WINDOW_HEIGHT || this.y < 0;
    }

    public int getSide() {
        return side;
    }

}
