package Model;
import Util.Constant;

public class Projectile {
    double speed = Constant.PROJECTILE_SPEED;
    double x, y, rad_angle, x_velocity, y_velocity;

    public Projectile(double _x, double _y, double _angle) {
        x = _x;
        y = _y;
        rad_angle = _angle * Math.PI / 180;
        x_velocity = Math.cos(rad_angle);
        y_velocity = Math.sin(rad_angle);
    }

    public double posX() {
        x += x_velocity * speed;
        return x;
    }
    
    public double posY() {
        y += y_velocity * speed;
        return y;
    }

}
