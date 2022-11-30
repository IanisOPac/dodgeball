package Controller;

import Model.AIPlayer;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class AIPlayerController extends PlayerController {
    AIPlayer model;

    // Constructeur du Controller de l'IA
    public AIPlayerController(GraphicsContext gc, int x, int y, String side, String color) {
        super(gc, x, y, side, color, true);
        model = new AIPlayer(x, y, side);
    }

    // L'IA ramasse le projectile et s'arrête de bouger
    public void grab(ProjectileController p) {
        super.grab(p);
        model.stay(true);
    }

    // Déplacement vers la gauche
    public void moveLeft() {
        model.moveLeft();
    }

    // Déplacement vers la droite
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

    // Deplace le personnage, fait tirer le personnage s'il a la balle, positionne la balle au niveau du joueur et lance l'affichage
    public void display() {
        model.moveInDirection();
        if (model.moveAngle()) shoot();
        if (proj != null) proj.setPosition(getPosition());
        view.display(model.getPosition(), model.getAngle());
    }

    // Calcule l'angle de tire
    public double getShootAngle() {
        return model.getShootAngle();
    }

    // Renvoi 1 si le personnage est en haut et -1 si le personnage est en bas
    public int getSide() {
        return model.getSide();
    }

    // Renvoie vrai si le personnage est en vie, et faux si il a déjà été touché par une balle
    public boolean alive() {
        return model.alive();
    }

    // Met le personnage comme "mort", l'IA ne peut plus rien faire
    public void die() {
        model.die();
        view.die();
    }

    // Choisi un ennemi au hasard, et le prends comme cible en calculant l'angle pour le viser
    public void initiateShot(PlayerController[] enemies) {
        int i = new Random().nextInt(enemies.length);
        PlayerController target = enemies[i];
        measureShootAngle(target.getPosition());
    }

    // Calcule l'angle de tire en fonction de la position du joueur adverse
    private void measureShootAngle(Point2D pos) {
        double opp = pos.getX() - getPosition().getX();
        double adj = pos.getY() - getPosition().getY();
        double tan = opp / adj;
        double angle = Math.atan(tan);
        model.updateAngle(angle * 180 / Math.PI);
    }

    // Lance la balle, remet le personnage en mouvement et repositionne la fleche de tire vers au centre
    public void shoot() {
        super.shoot();
        model.stay(false);
        model.resetAngle();
    }

    // Met à jour la direction dans laquelle doit aller le joueur
    public void setDestination(double x) {
        model.setxDirection(x);
    }
}
