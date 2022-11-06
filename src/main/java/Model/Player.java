package Model;
import java.util.Random;

public class Player {
    private double x, y, speed, angle;
    private String color;
    private int side = -1;

    public Player(int _x, int _y, String _side, String _color) {
        // Tous les joueurs commencent au centre du canvas,
        x = _x - 32;
        y = _y;
        color=_color;

        angle = 0;

        // On charge la representation du joueur
        if(_side=="top") side = -side;


        // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
        Random randomGenerator = new Random();
        speed = 3 + randomGenerator.nextFloat() / 2;
    }


    public void moveLeft() {
        if (x > 10) x -= speed;
    }

    public void moveRight() {
        if (x < 580) x += speed;
    }

    public void turnLeft() {
        if (angle < 45) angle += 1;
    }

    /**
     *  Rotation du joueur vers la droite
     */
    public void turnRight() {
        if (angle > -45) angle -=1;
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

    public double getAngle() {
        return angle;
    }
    public double getShootAngle() {
        return angle + 90 * side;
    }
}
