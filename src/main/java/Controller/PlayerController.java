package Controller;
import View.Sprite;

import View.PlayerView;
import Model.Player;
import javafx.scene.canvas.GraphicsContext;

public class PlayerController {
    PlayerView view;
    Player model;
    ProjectileController projectile;

    public PlayerController(GraphicsContext gc, int x, int y, String side, String color) {
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

    public void shoot(GraphicsContext gc) {
        System.out.println(model.getAngle());
        projectile = new ProjectileController(gc, model.getX(), model.getY(), model.getShootAngle());
    }

    public void display() {
        view.display(model.getX(), model.getY(), model.getAngle());
//        if (projectile != null) projectile.display();
    }
}
