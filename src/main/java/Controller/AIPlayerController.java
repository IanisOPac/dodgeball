package Controller;

import Model.AIPlayer;
import Util.Constant;
import com.sun.javafx.scene.paint.GradientUtils;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class AIPlayerController extends PlayerController {
    AIPlayer model;
    public AIPlayerController(GraphicsContext gc, int x, int y, String side, String color) {
        super(gc, x, y, side, color, true);
        model = new AIPlayer(x, y, side);
    }

    public void grab(ProjectileController p) {
        super.grab(p);
        model.stay(true);
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

    public void display() {
        model.moveInDirection();
        if (model.moveAngle()) shoot();
        if (proj != null) proj.setPosition(getPosition());
        view.display(model.getPosition(), model.getAngle());
    }

    public double getShootAngle() {
        return model.getShootAngle();
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

    public void initiateShot(PlayerController[] enemies) {
        int i = new Random().nextInt(enemies.length);
        PlayerController target = enemies[i];
        measureShootAngle(target.getPosition());
    }

    private void measureShootAngle(Point2D pos) {
        double opp = pos.getX() - getPosition().getX();
        double adj = pos.getY() - getPosition().getY();
        double tan = opp / adj;
        double angle = Math.atan(tan);
        model.updateAngle(angle * 180 / Math.PI);
    }

    public void shoot() {
        super.shoot();
        model.stay(false);
        model.resetAngle();
    }

    public void setDestination(double x) {
        model.setxDirection(x);
    }
}
