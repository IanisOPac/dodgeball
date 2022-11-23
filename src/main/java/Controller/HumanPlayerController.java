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

    public int getSide() {
        return model.getSide();
    }

    public boolean alive() {
        return model.alive();
    }

    public void display() {
        if (proj != null) proj.setPosition(getPosition());
        view.display(model.getPosition(), model.getAngle());
    }

    public double getShootAngle() {
        return model.getShootAngle();
    }

    public void die() {
        model.die();
        view.die();
    }

  

}
