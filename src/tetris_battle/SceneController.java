package tetris_battle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class SceneController {
	
	int XMAX = Tetris.XMAX;
	int YMAX = Tetris.YMAX;
	
	@FXML
	private Stage stage;
	private Scene scene;
	
	public void switchToGame(ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = Tetris.getScene();
		stage.setScene(scene);
		stage.show();
		Functions.moveOnKeyPress(Tetris.getCurrentBlock());
		//The main task of running the game
				Timer timer = Tetris.getCurrentTimer(); 
				TimerTask task = new TimerTask() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
							
								if (Tetris.game){
									Tetris.MoveDown(Tetris.getCurrentBlock());
									
								} else {
									Functions.moveOnKeyPress(Tetris.getCurrentBlock());
								}
							}
						});
					}
				};
				timer.schedule(task, 0, Tetris.dropSpeed);

       }
	
	public void switchToControls(ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("ControlWindow.fxml"));
		Group controlSceneGroup = new Group(root.getChildrenUnmodifiable());
		Scene controlScene = new Scene(controlSceneGroup, XMAX + 200, YMAX);
		stage.setScene(controlScene);
		stage.show();
	}
	
	public void switchToMenu (ActionEvent event) throws IOException {
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Group menuGroup = new Group(root.getChildrenUnmodifiable());
		Scene menu = new Scene(menuGroup, XMAX + 200, YMAX);
		menu.getStylesheets().add(getClass().getResource("Menu.css").toExternalForm());
		stage.setScene(menu);
		stage.show();
	}
	
	public void exitGame (ActionEvent event) throws IOException {
		System.exit(0);
	}
}
