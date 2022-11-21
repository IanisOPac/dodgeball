package Controller;

import Model.HumanPlayer;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class HumanPlayerController extends PlayerController {
    HumanPlayer model;
    
    public HumanPlayerController(GraphicsContext gc, int x, int y, String side, String color) {
        super(gc, x, y, side, color, false);
        model = new HumanPlayer(x, y, side);
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


    public int getSide() {
        return model.getSide();
    }

    public boolean alive() {
        return model.alive();
    }

    public void display() {
        view.display(model.getPosition(), model.getAngle());
    }

    public void shoot(ProjectileController proj) {
        setHolding(false);
        proj.move(model.getShootAngle());
        view.shoot();
    }

    public void display(ProjectileController proj) {
        display();
        proj.setPosition(getPosition());
    }

    public void die() {
        model.die();
        view.die();
    }

    public void grab(ProjectileController proj) {
        setHolding(true);
        proj.setSide(model.getSide());
    }

  

}
