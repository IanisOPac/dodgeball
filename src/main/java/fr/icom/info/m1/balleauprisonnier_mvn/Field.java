package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	Player[] team1 = new Player[3];
	Player[] team2 = new Player[3];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) {
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */
		team1[1] = new AI(gc, colorMap[0], w/4, h-50, "bottom");
		team1[2] = new AI(gc, colorMap[0], 3*w/4, h-50, "bottom");
		team1[0] = new Player(gc, colorMap[0], w/2, h-50, "bottom");

		team2[1] = new AI(gc, colorMap[1], w/4, 20, "top");
		team2[2] = new AI(gc, colorMap[1], 3*w/4, 20, "top");
		team2[0] = new Player(gc, colorMap[1], w/2, 20, "top");


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
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);
	        	
	            if (input.contains("LEFT")) {
					team1[0].moveLeft();
				}
				if (input.contains("RIGHT")) {
					team1[0].moveRight();
				}
				if (input.contains("UP")) {
					team1[0].turnLeft();
				}
				if (input.contains("DOWN")) {
					team1[0].turnRight();
				}
				if (input.contains("Q")) {
					team2[0].moveLeft();
				}
				if (input.contains("D")) {
					team2[0].moveRight();
				}
				if (input.contains("Z")) {
					team2[0].turnLeft();
				}
				if (input.contains("S")) {
					team2[0].turnRight();
				}
				if (input.contains("SPACE")) {
					team2[0].shoot();
				}
				if (input.contains("ENTER")) {
					team1[0].shoot();
				}
				for (Player p : getPlayers()) {
					p.display();
				}
			}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}

	public Player[] getPlayers() {
		Player[] result = Arrays.copyOf(team1, team1.length + team2.length);
		System.arraycopy(team2, 0, result, team1.length, team2.length);
		return result;
	}
}
