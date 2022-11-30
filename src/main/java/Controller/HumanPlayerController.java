package Controller;

import Model.HumanPlayer;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class HumanPlayerController extends PlayerController {
    HumanPlayer model;

    // Constructeur du controleur du joueur humain
    public HumanPlayerController(GraphicsContext gc, int x, int y, String side, String color) {
        super(gc, x, y, side, color, false);
        model = new HumanPlayer(x, y, side);
    }


    // Se déplace sur la gauche
    public void moveLeft() {
        model.moveLeft();
    }

    // Se déplace sur la droite
    public void moveRight() {
        model.moveRight();
    }

    // Déplacement de la flèche de trajectoire vers la gauche
    public void turnLeft() {
        model.turnLeft();
    }

    // Déplacement de la flèche de trajectoire vers la droite
    public void turnRight() {
        model.turnRight();
    }

    // Renvoi la position du personnage
    public Point2D getPosition() {
        return model.getPosition();
    }

    // Renvoi la position et la taille du personnage
    public BoundingBox getBoundingBox() {
        return model.getBoundingBox();
    }

    // Renvoi 1 si le personnage est en haut et -1 si le personnage est en bas
    public int getSide() {
        return model.getSide();
    }

    // Renvoie vrai si le personnage est en vie, et faux si il a déjà été touché par une balle
    public boolean alive() {
        return model.alive();
    }

    // Deplace le personnage, fait tirer le personnage s'il a la balle, positionne la balle au niveau du joueur et lance l'affichage
    public void display() {
        if (proj != null) proj.setPosition(getPosition());
        view.display(model.getPosition(), model.getAngle());
    }

    // Calcule l'angle de tire
    public double getShootAngle() {
        return model.getShootAngle();
    }

    // Met le personnage comme "mort", le joueur ne peut plus rien faire
    public void die() {
        model.die();
        view.die();
    }

  

}
