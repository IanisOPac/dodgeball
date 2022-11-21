package Controller;

import Model.Projectile;
import View.ProjectileView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

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

    public void setPosition(Point2D pos) {
        model.setX(pos.getX());
        model.setY(pos.getY());
    }

    public Point2D getPosition() {
        return model.getPosition();
    }

    public BoundingBox getBoundingBox() {
        return model.getBoundingBox();
    }

    public void move(double angle) {
        model.move(angle);
    }

    public boolean idling() {
        return model.idling();
    }

    public int getSide() {
        return model.getSide();
    }

    public void setSide(int side) {
        model.setSide(side);
    }
}
