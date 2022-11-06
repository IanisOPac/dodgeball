package Model;
import java.util.Arrays;

import Controller.PlayerController;
import Util.Constant;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    PlayerController[] team1 = new PlayerController[Constant.NB_PLAYERS];
	PlayerController[] team2 = new PlayerController[Constant.NB_PLAYERS];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Game(GraphicsContext gc, int w, int h) {
		width = w;
		height = h;

        /** On initialise le terrain de jeu */
		team1[1] = new PlayerController(gc, w/4, h-50, "bottom", colorMap[0]);
		team1[2] = new PlayerController(gc, 3*w/4, h-50, "bottom", colorMap[0]);
		team1[0] = new PlayerController(gc, w/2, h-50, "bottom", colorMap[0]);

		team2[1] = new PlayerController(gc, w/4, 20, "top", colorMap[1]);
		team2[2] = new PlayerController(gc, 3*w/4, 20, "top", colorMap[1]);
		team2[0] = new PlayerController(gc, w/2, 20, "top", colorMap[1]);
    }

	public PlayerController[] getPlayers() {
		PlayerController[] result = Arrays.copyOf(team1, team1.length + team2.length);
		System.arraycopy(team2, 0, result, team1.length, team2.length);
		return result;
	}

    public PlayerController[] getTeam1() {
		return team1;
	}

	public PlayerController[] getTeam2() {
		return team2;
	}
}
