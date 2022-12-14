package View;

import Util.Constant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ProjectileView {
    GraphicsContext gc;
    // l'image est chargée avec une taille définie dans la classe Util.Constant
    Image image = new Image("assets/ball.png", Constant.BALL_SIZE, Constant.BALL_SIZE, false, false);

    public ProjectileView(GraphicsContext _gc) {
        gc = _gc;
    }

    public void display(double x, double y) {
        gc.save(); // saves the current state on stack, including the current transform
        gc.drawImage(image, x, y);
        gc.restore(); // back to original state (before rotation)
    }
}
