package Controller;
import Util.Constant;
import View.Sprite;

import View.PlayerView;
import Model.Player;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class PlayerController {
    PlayerView view;
    Player model;

    public PlayerController(GraphicsContext gc, int x, String side, String color) {
        int y = side == "top" ? 0 : Constant.WINDOW_HEIGHT - Constant.PLAYER_HEIGHT;
        view = new PlayerView(gc, x, y, side);
        model = new Player(x, y, side, color);
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
        System.out.println(model.getShootAngle());
        proj.move(model.getShootAngle(), model.getSide());
        view.shoot();
    }

    public Point position() {
        return model.getPosition();
    }

    public boolean isHolding() {
        return model.isHolding();
    }

    public void setHolding(boolean h) {
        model.setHolding(h);
    }

    public void display() {
        view.display(model.getX(), model.getY(), model.getAngle());
    }

    public void display(ProjectileController proj) {
        display();
        proj.setPos(position());
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
