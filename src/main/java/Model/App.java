package Model;
import Controller.GameController;
import Controller.PlayerController;
import Util.Constant;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Classe principale de l'application 
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application 
{
	
	/**
	 * En javafx start() lance l'application
	 * On cree le SceneGraph de l'application ici
	 * 
	 */
	@Override
	public void start(Stage stage)
	{
		// Nom de la fenetre
        stage.setTitle("Dodgeball");

		BorderPane root = new BorderPane();
		root.setPrefHeight(Constant.WINDOW_HEIGHT);
		root.setPrefWidth(Constant.WINDOW_WIDTH);

		Button pauseButton = new Button("START / PAUSE");
		pauseButton.setPrefHeight(Constant.WINDOW_HEIGHT * 0.3);
		pauseButton.setPrefWidth(Constant.WINDOW_WIDTH - Constant.FIELD_WIDTH);

		Button plusButton = new Button("+");
		plusButton.setPrefHeight(Constant.WINDOW_HEIGHT * 0.1);
		plusButton.setPrefWidth(Constant.WINDOW_WIDTH - Constant.FIELD_WIDTH);
		plusButton.setFont(Font.font("arial", FontWeight.BOLD, 32));

		Button minusButton = new Button("-");
		minusButton.setPrefHeight(Constant.WINDOW_HEIGHT * 0.1);
		minusButton.setPrefWidth(Constant.WINDOW_WIDTH - Constant.FIELD_WIDTH);
		minusButton.setFont(Font.font("arial", FontWeight.BOLD, 32));

		Label nbPlayers = new Label();
		nbPlayers.setPrefHeight(Constant.WINDOW_HEIGHT * 0.2);
		nbPlayers.setPrefWidth(Constant.WINDOW_WIDTH - Constant.FIELD_WIDTH);
		nbPlayers.setAlignment(Pos.CENTER);
		nbPlayers.setFont(Font.font("arial", 48));


		VBox vbox = new VBox();
		vbox.getChildren().addAll(pauseButton, plusButton, nbPlayers, minusButton);
		vbox.setAlignment(Pos.CENTER_LEFT);

        // On cree le terrain de jeu et on l'ajoute a la racine de la scene
        GameController gameC = new GameController();

		pauseButton.setOnAction(event -> {
			gameC.pause();
			gameC.requestFocus();
		});

		plusButton.setOnAction(event -> {
			int nb = Integer.parseInt(nbPlayers.getText()) + 1;
			if (nb <= 10) addPlayers(root, vbox, gameC, nbPlayers, nb);
		});

		minusButton.setOnAction(event -> {
			int nb = Integer.parseInt(nbPlayers.getText()) - 1;
			if (nb > 0) addPlayers(root, vbox, gameC, nbPlayers, nb);
		});

		addPlayers(root, vbox, gameC, nbPlayers, 3);
        // On ajoute la scene a la fenetre et on affiche

		Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

	}

	private void addPlayers(BorderPane root, VBox vbox, GameController gameC, Label nbPlayers, int nb) {
		nbPlayers.setText(Integer.toString(nb));
		gameC.newGame(nb);
		root.getChildren().clear();
		root.setRight(vbox);
		root.getChildren().add(gameC);
		for (PlayerController p : gameC.getActivePlayers()) {
			root.getChildren().add(p.getSprite());
		}
		gameC.requestFocus();
	}
	
    public static void main(String[] args) 
    {
        //System.out.println( "Hello World!" );
    	Application.launch(args);
    }
}
