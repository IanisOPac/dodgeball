package Controller;

import java.util.ArrayList;
import Model.Game;
import Util.Constant;
import javafx.animation.AnimationTimer;
import javafx.geometry.BoundingBox;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Random;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class GameController extends Canvas {
    Game gameModel;
	final GraphicsContext gc;
	
    /** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<>();

	AnimationTimer t;
	boolean running = false;

    public GameController() {
		super(Constant.FIELD_WIDTH, Constant.FIELD_HEIGHT);
		gc = this.getGraphicsContext2D();
		this.setFocusTraversable(true);
		gameModel = new Game(gc, Constant.FIELD_WIDTH, Constant.FIELD_HEIGHT, 3);

		/**
		 * Event Listener du clavier
		 * quand une touche est pressee on la rajoute a la liste d'input
		 *
		 */
		this.setOnKeyPressed(
				e -> {
					String code = e.getCode().toString();
					// only add once... prevent duplicates
					if (!input.contains(code))
						input.add(code);
				});

		/**
		 * Event Listener du clavier
		 * quand une touche est relachee on l'enleve de la liste d'input
		 *
		 */
		this.setOnKeyReleased(
				e -> {
					String code = e.getCode().toString();
					input.remove(code);
				});
		/**
		 *
		 * Boucle principale du jeu
		 * handle() est appelee a chaque rafraichissement de frame
		 * soit environ 60 fois par seconde.
		 *
		 */
		t = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (input.contains("K")) {
					gameModel.getTeam1()[0].moveLeft();
				}
				if (input.contains("M")) {
					gameModel.getTeam1()[0].moveRight();
				}
				if (input.contains("L")) {
					gameModel.getTeam1()[0].turnLeft();
				}
				if (input.contains("O")) {
					gameModel.getTeam1()[0].turnRight();
				}
				if (input.contains("ENTER")) {
					gameModel.getTeam1()[0].shoot();
				}
				if (input.contains("Q")) {
					gameModel.getTeam2()[0].moveLeft();
				}
				if (input.contains("D")) {
					gameModel.getTeam2()[0].moveRight();
				}
				if (input.contains("Z")) {
					gameModel.getTeam2()[0].turnLeft();
				}
				if (input.contains("S")) {
					gameModel.getTeam2()[0].turnRight();
				}
				if (input.contains("SPACE")) {
					gameModel.getTeam2()[0].shoot();
				}
				display();
				if (gameModel.getProjectile().idling()) notifyAIs();
				checkCollision();
			}
		};

		display();
	}

	private void display() {
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, Constant.FIELD_WIDTH, Constant.FIELD_HEIGHT);
		for (PlayerController p : getActivePlayers()) {
			p.display();
		}
		gameModel.getProjectile().display();
		requestFocus();
	}

	private void checkCollision() {
		BoundingBox projBB = gameModel.getProjectile().getBoundingBox();
		for (PlayerController p : getActivePlayers()) {
			BoundingBox playerBB = p.getBoundingBox();
			if (projBB.intersects(playerBB)) {
				if (gameModel.getProjectile().idling()) {
					p.grab(gameModel.getProjectile());
					if (p instanceof AIPlayerController) ((AIPlayerController)p).initiateShot(gameModel.getActivePlayers(-p.getSide()));
				}
				else if (gameModel.getProjectile().getSide() != p.getSide()) {
					p.die();
					if (checkVictory()) printVictory(getActivePlayers()[0].getSide());
				}
			}
		}
	}

	// private boolean rectCollide(Point2D proj, int proj_size, Point2D player) {
	// 	return proj.x + Constant.BALL_SIZE >= player.x &&
	// 		proj.x <= player.x + Constant.PLAYER_WIDTH &&
	// 		proj.y + Constant.BALL_SIZE >= player.y &&
	// 		proj.y <= player.y + Constant.PLAYER_HEIGHT;
	// }

	private boolean checkVictory() {
		PlayerController p1, p2;
		PlayerController[] players = getActivePlayers();

		p1 = players[0];
		for (int i = 1; i < players.length; i++) {
			p2 = players[i];
			if (p1.getSide() != p2.getSide()) return false;
			p1 = p2;
		}
		return true;
	}

	private void notifyAIs() {
		int side = gameModel.getProjectile().getPosition().getY() < 50 ? 1 : -1;
		for (PlayerController p : gameModel.getActivePlayers(side)) {
			if (p instanceof AIPlayerController) ((AIPlayerController)p).setDestination(gameModel.getProjectile().getPosition().getX());
		}
	}

	public PlayerController[] getActivePlayers() {
        return gameModel.getActivePlayers();
	}

	public void pause() {
		if (running) t.stop();
		else t.start();
		running = !running;
	}
	public void newGame(int nb_players) {
		gameModel = new Game(gc, Constant.FIELD_WIDTH, Constant.FIELD_HEIGHT, nb_players);
		display();
		t.stop();
		running = false;
	}

	private void printVictory(int side) {
		String s = "";
		if (side == 1) s += "TOP";
		else s += "BOTTOM";
		s += " TEAM WON !!!";
		System.out.println(s);
	}
}