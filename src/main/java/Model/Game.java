package Model;
import java.util.ArrayList;
import java.util.Arrays;

import Controller.AIPlayerController;
import Controller.HumanPlayerController;
import Controller.PlayerController;
import Util.Constant;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    PlayerController[] team1 = new PlayerController[Constant.TEAM_SIZE];
	PlayerController[] team2 = new PlayerController[Constant.TEAM_SIZE];

	/** Couleurs possibles */
	String[] colorMap = new String[] {"Blue", "Red"};
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
		generateTeams(gc);
    }

	public PlayerController[] getActivePlayers() {
		ArrayList<PlayerController> result = new ArrayList<>();
		for (PlayerController p : getPlayers()) {
			if (p.alive()) result.add(p);
		}
		return result.toArray(new PlayerController[0]);
	}

	public PlayerController[] getActivePlayers(int side) {
		ArrayList<PlayerController> result = new ArrayList<>();
		for (PlayerController p : getActivePlayers()) {
			if (p.getSide() == side) result.add(p);
		}
		return result.toArray(new PlayerController[0]);
	}

	private PlayerController[] getPlayers() {
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

	private void generateTeams(GraphicsContext gc) {
		team1[0] = new HumanPlayerController(gc, width/(Constant.TEAM_SIZE +1), Constant.WINDOW_HEIGHT - Constant.PLAYER_HEIGHT, "bottom", colorMap[0]);
		team2[0] = new HumanPlayerController(gc, width/(Constant.TEAM_SIZE +1), 0, "top", colorMap[1]);

		for(int i = 1; i < Constant.TEAM_SIZE; i++) {
			team1[i] = new AIPlayerController(gc, (i+1) * width/(Constant.TEAM_SIZE +1), Constant.WINDOW_HEIGHT - Constant.PLAYER_HEIGHT, "bottom", colorMap[0]);
			team2[i] = new AIPlayerController(gc, (i+1)  * width/(Constant.TEAM_SIZE +1), 0, "top", colorMap[1]);
		}
	}
}
