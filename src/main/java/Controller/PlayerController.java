package Controller;

import View.PlayerView;
import Model.Player;
import javafx.scene.canvas.GraphicsContext;
//A FINIR ON EN ETAIT LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
public class PlayerController {
    PlayerView view;
    Player model;

    public PlayerController(GraphicsContext gc, int xInit, int yInit, String _side, String color) {
        view = new PlayerView( );
        model = new Player();
    }
}
