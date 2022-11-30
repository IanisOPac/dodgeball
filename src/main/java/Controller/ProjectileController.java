package Controller;

import Model.Projectile;
import View.ProjectileView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ProjectileController {
    Projectile model;
    ProjectileView view;

    // Constructeur du projectile controleur
    public ProjectileController(GraphicsContext _gc, double _x, double _y, double _angle) {
        model = new Projectile(_x, _y, _angle);
        view = new ProjectileView(_gc);
    }

    // Affiche le projectile
    public void display() {
        view.display(model.posX(), model.posY());
    }

    // Modifie la position du projectile
    public void setPosition(Point2D pos) {
        model.setX(pos.getX());
        model.setY(pos.getY());
    }

    // Renvoi la position du projectile
    public Point2D getPosition() {
        return model.getPosition();
    }

    // Renvoi la position et la taille du projectile
    public BoundingBox getBoundingBox() {
        return model.getBoundingBox();
    }

    // Déplace le projectile en fonction d'un angle
    public void move(double angle) {
        model.move(angle);
    }

    //
    public boolean idling() {
        return model.idling();
    }

    // Renvoi le côté sur lequel se trouve le projectile : 1 s'il est en haut, et -1 s'il est en bas
    public int getSide() {
        return model.getSide();
    }

    // Modifie le côté sur lequel se trouve le projectile
    public void setSide(int side) {
        model.setSide(side);
    }
}
