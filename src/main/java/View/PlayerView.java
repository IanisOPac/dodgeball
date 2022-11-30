package View;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PlayerView {
    Image directionArrow;
    Sprite sprite;
    ImageView PlayerDirectionArrow;
    GraphicsContext graphicsContext;
    /**
     * Constructeur de l'affichage du joueur
     *
     * @param gc ContextGraphic dans lequel on va afficher le joueur
     * @param y position joueur
     * @param x position joueur
     */
    public PlayerView(GraphicsContext gc, int x, int y, String _side, String color, boolean ai) {

        // Tous les joueurs commencent au centre du canvas,
        graphicsContext = gc;
        // On charge la representation du joueur
        directionArrow = new Image("assets/PlayerArrow.png");

        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);

        String imagePath = "assets/Player"+ color ;
        if (ai) imagePath += "_gray";
        Image tilesheetImage = new Image(imagePath + ".png");
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), _side);
        sprite.setX(x);
        sprite.setY(y);
        //directionArrow = sprite.getClip().;

    }

    /**
     *  Affichage de la flèche du joueur
     */
    public void display(Point2D pos, double angle)
    {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotateArrow(graphicsContext, angle, pos.getX() + directionArrow.getWidth() / 2, pos.getY() + directionArrow.getHeight() / 2);
        graphicsContext.drawImage(directionArrow, pos.getX(), pos.getY());
        graphicsContext.restore(); // back to original state (before rotation)
        sprite.setX(pos.getX());
        sprite.setY(pos.getY());
    }

    private void rotateArrow(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }



    void spriteAnimate(double x, double y){
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(x);
        sprite.setY(y);
    }


    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Joue l'animation de tir du Sprite
     */
    public void shoot() {
        sprite.playShoot();
    }

    /**
     * joue l'animation de mort du Sprite
     */
    public void die() {
        sprite.playDie();
    }
}
