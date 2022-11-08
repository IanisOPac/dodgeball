package Controller;

import java.util.ArrayList;

import Model.Game;
import Util.Constant;
import View.GameView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class GameController extends Canvas {
    Game gameModel;
	final GraphicsContext gc;
	ProjectileController projectile;
	

    /** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();

    public GameController() {
		super(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		gc = this.getGraphicsContext2D();
		this.setFocusTraversable(true);
		gameModel = new Game(gc, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		Random rand = new Random();
		int startingTeam = rand.nextInt(2);
		if(startingTeam == 0){
			startingTeam = -1;
		}
		double angle = rand.nextDouble(-45, 45);
		System.out.println(startingTeam);
		projectile = new ProjectileController(gc, Constant.WINDOW_WIDTH/2 - Constant.BALL_SIZE / 2, Constant.WINDOW_HEIGHT/2 - Constant.BALL_SIZE / 2, 23);

		/**
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    


	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() {

	        public void handle(long currentNanoTime) {
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
//	        	System.out.println(input);
	            if (input.contains("K")) {
					gameModel.getTeam1()[0].moveLeft();
				}
				if (input.contains("M")) {
					gameModel.getTeam1()[0].moveRight();
				}
				if (input.contains("RIGHT")) {
					gameModel.getTeam1()[0].turnLeft();
				}
				if (input.contains("LEFT")) {
					gameModel.getTeam1()[0].turnRight();
				}
				if (input.contains("L")) {
					gameModel.getTeam1()[0].shoot(gc);
				}
				if (input.contains("Q")) {
					gameModel.getTeam2()[0].moveLeft();
				}
				if (input.contains("D")) {
					gameModel.getTeam2()[0].moveRight();
				}
				if (input.contains("B")) {
					gameModel.getTeam2()[0].turnLeft();
				}
				if (input.contains("N")) {
					gameModel.getTeam2()[0].turnRight();
				}
				if (input.contains("SPACE")) {
					gameModel.getTeam2()[0].shoot(gc);
				}
				for (PlayerController p : getPlayers()) {
					p.display();
				}
				projectile.display();
			}
	     }.start(); // On lance la boucle de rafraichissement
	    

    } 

	public PlayerController[] getPlayers() {
        return gameModel.getPlayers();
	}

}