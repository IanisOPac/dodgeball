package Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Controller.AIPlayerController;
import Controller.HumanPlayerController;
import Controller.PlayerController;
import Controller.ProjectileController;
import Util.Constant;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    PlayerController[] team1;
	PlayerController[] team2;

	/** Couleurs possibles */
	String[] colorMap = new String[] {"Blue", "Red"};
    final int width;
    final int height;

	ProjectileController projectile;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Game(GraphicsContext gc, int w, int h, int nb_player) {
		width = w;
		height = h;
		team1 = new PlayerController[nb_player];
		team2 = new PlayerController[nb_player];
		generateTeams(gc, nb_player);

		Random rand = new Random();
		int startingTeam = rand.nextInt(2) == 0 ? -1 : 1;
		double angle = rand.nextDouble(-45, 45);
		projectile = new ProjectileController(gc,
				Constant.FIELD_WIDTH / 2 - Constant.BALL_SIZE / 2,
				Constant.FIELD_HEIGHT / 2 - Constant.BALL_SIZE / 2,
				angle + 90 * startingTeam);
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

	private void generateTeams(GraphicsContext gc, int nb_player) {
		team1[0] = new HumanPlayerController(gc, width/(nb_player +1), Constant.FIELD_HEIGHT - Constant.PLAYER_HEIGHT, "bottom", colorMap[0]);
		team2[0] = new HumanPlayerController(gc, width/(nb_player +1), 0, "top", colorMap[1]);

		for(int i = 1; i < nb_player; i++) {
			team1[i] = new AIPlayerController(gc, (i+1) * width/(nb_player +1), Constant.FIELD_HEIGHT - Constant.PLAYER_HEIGHT, "bottom", colorMap[0]);
			team2[i] = new AIPlayerController(gc, (i+1)  * width/(nb_player +1), 0, "top", colorMap[1]);
		}
	}

	public ProjectileController getProjectile() {
		return projectile;
	}
}
