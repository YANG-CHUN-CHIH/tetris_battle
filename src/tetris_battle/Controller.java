package tetris_battle;

import java.io.File;
import java.nio.file.Paths;

import tetris_battle.Blocks;
import tetris_battle.Tetris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller {
	
	public static int[][] Board = Tetris.GRID;
	public static final int MOVE = Tetris.MOVE;
	public static final int SIZE = Tetris.SIZE;
	public static int XMAX = Tetris.XMAX;
	public static int YMAX = Tetris.YMAX;
	
	private static Media Move = new Media(Paths.get("Move.mp3").toUri().toString());
    
	//Moves the piece right if there is space
	public static void MoveRight(Blocks form) {
		if (form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE
				&& form.c.getX() + MOVE <= XMAX - SIZE && form.d.getX() + MOVE <= XMAX - SIZE) {
			int movea = Board[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
			int moveb = Board[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
			int movec = Board[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
			int moved = Board[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				MediaPlayer Move_player = new MediaPlayer(Move);
				Move_player.setVolume(0.4);
				Move_player.play();
				form.a.setX(form.a.getX() + MOVE);
				form.b.setX(form.b.getX() + MOVE);
				form.c.setX(form.c.getX() + MOVE);
				form.d.setX(form.d.getX() + MOVE);
			}
		}
	}
	
	//Moves the pieces left if there is space
		public static void MoveLeft(Blocks form) {
			if (form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0 && form.c.getX() - MOVE >= 0
					&& form.d.getX() - MOVE >= 0) {
				int movea = Board[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
				int moveb = Board[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
				int movec = Board[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
				int moved = Board[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
				if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
					MediaPlayer Move_player = new MediaPlayer(Move);
					Move_player.setVolume(0.4);
					Move_player.play();
					form.a.setX(form.a.getX() - MOVE);
					form.b.setX(form.b.getX() - MOVE);
					form.c.setX(form.c.getX() - MOVE);
					form.d.setX(form.d.getX() - MOVE);
				}
			}
		}
	
}
