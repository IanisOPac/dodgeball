package Model;
import Util.Constant;

public class Projectile {
    double speed = Constant.PROJECTILE_SPEED;
    double x, y, angle, x_velocity, y_velocity;

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
        if (isOutOfBound()) {
            if(angle > 90){
                angle -= 90;
            }
            else {
                angle +=90;
            }

            updateAngle();
        }
        x += x_velocity * speed;
        return x;
    }
    
    public double posY() {
        y += y_velocity * speed;
        return y;
    }

    private boolean isOutOfBound() {
        if(this.x > Constant.WINDOW_WIDTH || this.x < Constant.WINDOW_HEIGHT){
            return true;
        }
        return false;
    }

}
