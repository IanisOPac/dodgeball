package Controller;
import Util.Constant;
import View.Sprite;

import View.PlayerView;
import Model.Player;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class PlayerController {
    PlayerView view;
    Player model;

    public PlayerController(GraphicsContext gc, int x, String side, String color) {
        int y = side.equals("top") ? 0 : Constant.WINDOW_HEIGHT - Constant.PLAYER_HEIGHT;
        x -= Constant.PLAYER_WIDTH / 2; 
        view = new PlayerView(gc, x, y, side, color);
        model = new Player(x, y, side);
    }

    public Sprite getSprite() {
        return view.getSprite();
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

    public void shoot(ProjectileController proj) {
        setHolding(false);
        proj.move(model.getShootAngle());
        view.shoot();
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
        view.display(model.getPosition(), model.getAngle());
    }

    public void display(ProjectileController proj) {
        display();
        proj.setPosition(getPosition());
    }

    public int getSide() {
        return model.getSide();
    }

    public void die() {
        model.die();
        view.die();
    }

    public boolean alive() {
        return model.alive();
    }
}
