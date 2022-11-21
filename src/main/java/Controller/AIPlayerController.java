package Controller;

import Model.AIPlayer;
import Model.Player;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class AIPlayerController extends PlayerController {
    AIPlayer model;
    ProjectileController projectile;
    public AIPlayerController(GraphicsContext gc, int x, int y, String side, String color) {
        super(gc, x, y, side, color, true);
        model = new AIPlayer(x, y, side);
    }

    public void grab(ProjectileController p) {
        setHolding(true);
        p.setSide(model.getSide());
        projectile = p;
    }

    public void moveLeft() {
        model.moveLeft();
    }

    public void moveRight() {
        model.moveRight();
    }

    public void turnLeft() {
        model.turnLeft();
    }

    public void turnRight() {
        model.turnRight();
    }

    public Point2D getPosition() {
        return model.getPosition();
    }

    public BoundingBox getBoundingBox() {
        return model.getBoundingBox();
    }

    public boolean isHolding() {
        return model.isHolding();
    }

    public void setHolding(boolean h) {
        model.setHolding(h);
    }

    public void display() {
        model.moveInDirection();
        view.display(model.getPosition(), model.getAngle());
    }
    public int getSide() {
        return model.getSide();
    }

    public boolean alive() {
        return model.alive();
    }

    public void die() {
        model.die();
        view.die();
    }

    public void shoot(ProjectileController proj) {

    }
}
