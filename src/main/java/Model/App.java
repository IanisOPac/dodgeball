package Model;
import Controller.GameController;
import Controller.PlayerController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale de l'application 
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application 
{
	
	/**
	 * En javafx start() lance l'application
	 *
	 * On cree le SceneGraph de l'application ici
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception 
	{
		// Nom de la fenetre
        stage.setTitle("Dodgeball");

        Group root = new Group();
        Scene scene = new Scene(root);

        // On cree le terrain de jeu et on l'ajoute a la racine de la scene
        GameController gameC = new GameController();
        root.getChildren().add(gameC);
		for (PlayerController p : gameC.getActivePlayers()) {
			root.getChildren().add(p.getSprite());
		}

        // On ajoute la scene a la fenetre et on affiche
        stage.setScene(scene);
        stage.show();
	}
	
    public static void main(String[] args) 
    {
        //System.out.println( "Hello World!" );
    	Application.launch(args);
    }
}
