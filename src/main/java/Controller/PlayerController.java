package Controller;
import Model.AIPlayer;
import Model.HumanPlayer;
import Model.Player;
import Util.Constant;
import View.Sprite;

import View.PlayerView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class PlayerController {
    PlayerView view;

    public PlayerController(GraphicsContext gc, int x, int y, String side, String color, boolean ai) {
        view = new PlayerView(gc, x, y, side, color, ai);
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void turnLeft();
    public abstract void turnRight();
    public abstract Point2D getPosition();
    public abstract BoundingBox getBoundingBox();
    public abstract boolean isHolding();
    public abstract void setHolding(boolean h);
    public abstract int getSide();
    public abstract boolean alive();
    public abstract void die();
    public abstract void shoot(ProjectileController proj);
    public abstract void grab(ProjectileController proj);
    public abstract void display();

    public Sprite getSprite() {
        return view.getSprite();
    }

    public void display(ProjectileController proj) {
        display();
        proj.setPosition(getPosition());
    }
}
