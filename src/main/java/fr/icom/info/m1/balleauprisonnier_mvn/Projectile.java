package fr.icom.info.m1.balleauprisonnier_mvn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile {
    double speed, x, y, rad_angle, x_velocity, y_velocity;
    GraphicsContext gc;
    Image image = new Image("assets/ball.png");

    public Projectile(GraphicsContext _gc, double _x, double _y, double _angle) {
        speed = 5;
        x = _x;
        y = _y;
        rad_angle = _angle * Math.PI / 180;
        x_velocity = Math.cos(rad_angle);
        y_velocity = Math.sin(rad_angle);
        gc = _gc;

        display();
    }

    void display()
    {
        gc.save(); // saves the current state on stack, including the current transform
        x += x_velocity * speed;
        y += y_velocity * speed;
        gc.drawImage(image, x, y);
        gc.restore(); // back to original state (before rotation)
    }
}
