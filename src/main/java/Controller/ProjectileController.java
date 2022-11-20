package Controller;

import Model.Projectile;
import View.ProjectileView;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class ProjectileController {
    Projectile model;
    ProjectileView view;

    public ProjectileController(GraphicsContext _gc, double _x, double _y, double _angle) {
        model = new Projectile(_x, _y, _angle);
        view = new ProjectileView(_gc);
    }

    public void display() {
        view.display(model.posX(), model.posY());
    }

    public void setPos(Point pos) {
        model.setX(pos.x);
        model.setY(pos.y);
    }

    public Point position() {
        return model.position();
    }

    public void move(double angle, int side) {
        model.move(angle, side);
    }

    public boolean idling() {
        return model.idling();
    }

    public int getSide() {
        return model.getSide();
    }
}
