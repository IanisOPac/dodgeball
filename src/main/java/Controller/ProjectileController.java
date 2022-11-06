package Controller;

import Model.Projectile;
import View.ProjectileView;
import javafx.scene.canvas.GraphicsContext;

public class ProjectileController {
    Projectile model;
    ProjectileView view;

    public ProjectileController(GraphicsContext _gc, double _x, double _y, double _angle) {
        model = new Projectile(_x, _y, _angle);
        view = new ProjectileView(_gc);
    }

    public void display() {
        view.display(model.posX(), model.posY());
    }
}
