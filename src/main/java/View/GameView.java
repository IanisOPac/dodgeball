package View;

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
public class GameView extends Canvas {

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public GameView(int w, int h) {
		super(w, h); 
		width = w;
		height = h;
		
        gc = this.getGraphicsContext2D();
	}

    public void cleanCanvas() {
        // On nettoie le canvas a chaque frame
        gc.setFill( Color.LIGHTGRAY);
        gc.fillRect(0, 0, width, height);
    }

    public GraphicsContext getGC() {
        return gc;
    }
}
