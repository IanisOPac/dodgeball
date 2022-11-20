package Model;
import Util.Constant;

import java.awt.*;
import java.util.Random;

public class Player {
    private double x, y, speed, base_angle, angle;
    private String color;
    private int side = -1;
    private boolean alive = true;
    private boolean holding = false;

    public Player(int _x, int _y, String _side, String _color) {
        // Tous les joueurs commencent au centre du canvas,
        x = _x - 32;
        y = _y;
        color=_color;

        base_angle = 0;

        // On charge la representation du joueur
        if(_side=="top") {
            side = -side;
            base_angle = 180;
        }
        angle = base_angle;

        // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
        Random randomGenerator = new Random();
        speed = 3 + randomGenerator.nextFloat() / 2;
    }


    public void moveLeft() {
        if (x > 0) x -= speed;
    }

    public void moveRight() {
        if (x + Constant.PLAYER_WIDTH < Constant.WINDOW_WIDTH) x += speed;
    }

    public void turnLeft() {
        if (angle < base_angle + 45) angle += 1;
    }

    /**
     *  Rotation du joueur vers la droite
     */
    public void turnRight() {
        if (angle > base_angle - 45) angle -=1;
    }

    void shoot() {
//        p = new Projectile(graphicsContext, x, y, angle + 90 * side);
    }

    /**
     *  Deplacement en mode boost
     */
    void boost() {
        x += speed*2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point getPosition() {
        return new Point((int) x, (int) y);
    }

    public boolean isHolding() {
        return holding;
    }

    public void setHolding(boolean h) {
        holding = h;
    }

    public double getAngle() {
        return angle;
    }
    public double getShootAngle() {
        return angle - 90;
    }

    public int getSide() {
        return side;
    }

    public boolean alive() {
        return alive;
    }

    public void die() {
        speed = 0;
        alive = false;
    }
}
