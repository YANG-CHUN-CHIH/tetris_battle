package tetris_battle;


import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;


public class Functions {
	public static final int MOVE = Tetris.MOVE;
	public static final int SIZE = Tetris.SIZE;
	public static int XMAX = Tetris.XMAX;
	public static int YMAX = Tetris.YMAX;
	public static int[][] Board = Tetris.GRID;
	
	private static Media Rotate = new Media(Paths.get("Rotate.mp3").toUri().toString());
	private static Media RemoveRow = new Media(Paths.get("RemoveRow.mp3").toUri().toString());
	private static Media tetris = new Media(Paths.get("Tetris.mp3").toUri().toString());
	private static Media Hold = new Media(Paths.get("Hold.mp3").toUri().toString());
	private static Media Move = new Media(Paths.get("Move.mp3").toUri().toString());
	
	public static void RemoveRows(Group pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newrects = new ArrayList<Node>();
		
		for (int i = 0; i < Tetris.GRID[0].length; i++) {
			int counter = 0;
			for (int j = 0; j < Tetris.GRID.length; j++) {
				if (Tetris.GRID[j][i] == 1)
					counter++;
			}
			if (counter == Tetris.GRID.length)
			lines.add(i);
			counter = 0;
		}
		
		int line = lines.size();
		int score = 0;
		Tetris.linesNo += lines.size();
		switch(lines.size()) {
		case 1:
			score += 100;
			break;
		case 2:
			score += 300;
			break;
		case 3:
			score += 500;
			break;
		case 4:
			score += 1000;
		}
		if (lines.size() == 0)
			Tetris.combo = 0;
		
		if (Tetris.T_spin_mini == true && lines.size() == 0 && Tetris.T_spin == false) {
			score += 100;
		} 
		//System.out.println("In B2B? " + Tetris.B2B);
		if (!Tetris.B2B) {
			if (Tetris.T_spin_mini == true && Tetris.T_spin == false && lines.size() == 1)
				score += 300;
			
			if (Tetris.T_spin == true) {
				switch(lines.size()) {
				case 1:
					score += 500;
					break;
				case 2:
					score += 800;
				case 3:
					score += 1200;
				}
			}
		} else {
			if (Tetris.T_spin_mini == true && Tetris.T_spin == false && lines.size() == 1)
				score += 450;
			
			if (Tetris.T_spin == true) {
				switch(lines.size()) {
				case 1:
					score += 750;
					break;
				case 2:
					score += 1200;
				case 3:
					score += 1800;
				}
			}
				
			if (lines.size() == 4) {
				score += 200;
			}
		}
		
		score += Tetris.combo * 50;
		
		
		
		
		if (lines.size() > 0) {
			Tetris.combo++;
			MediaPlayer remove_player = new MediaPlayer(RemoveRow);
			remove_player.setVolume(0.5);
			remove_player.play();
			if (lines.size() == 4) {
				MediaPlayer tetris_player = new MediaPlayer(tetris);
				tetris_player.setVolume(0.5);
				tetris_player.play();
				Tetris.tetris = true;
			}
			
		}
		
		if (lines.size() > 0)
			do {
				for (Node node : pane.getChildren()) {
					//System.out.println("class = " + node.getClass() + ", x = " + node.getLayoutX() + ", y = " + node.getLayoutY());
					if (node instanceof Rectangle ) {
						if (((Rectangle)node).getX() < XMAX && ((Rectangle)node).getY() < YMAX && ((Rectangle)node).getOpacity() == 1)
						//System.out.println("Ractangle x = " + ((Rectangle)node).getX() + ", Rectangle y = " + ((Rectangle)node).getY());
						rects.add(node);
					}
				}

				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() == lines.get(0) * SIZE && a.getX() < XMAX) {
						Tetris.GRID[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						pane.getChildren().remove(node);
					} else
						newrects.add(node);
				}

				for (Node node : newrects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() < lines.get(0) * SIZE && a.getX() < XMAX) {
						Tetris.GRID[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						a.setY(a.getY() + SIZE);
					}
				}
				
				lines.remove(0);
				rects.clear();
				newrects.clear();
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle && ((Rectangle)node).getOpacity() == 1)
						rects.add(node);
				}
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					try {
						Tetris.GRID[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				rects.clear();
			} while (lines.size() > 0);
		if (Tetris.tetris == true || Tetris.T_spin == true) {
			Tetris.B2B = true;
		} else if (line > 0){
			Tetris.B2B = false;
		}
		
		
		
		Tetris.T_spin = false;
		Tetris.T_spin_mini = false;
		Tetris.tetris = false;
		//System.out.println(score);
		Tetris.score += score * Tetris.level;
	}

	//Moves each rectangle
	public static void MoveDown(Rectangle rect) {
		if (rect.getY() + MOVE < YMAX)
			rect.setY(rect.getY() + MOVE);

	}

	public static void MoveRight(Rectangle rect) {
		if (rect.getX() + MOVE <= XMAX - SIZE)
			rect.setX(rect.getX() + MOVE);
	}

	public static void MoveLeft(Rectangle rect) {
		if (rect.getX() - MOVE >= 0)
			rect.setX(rect.getX() - MOVE);
	}

	public static void MoveUp(Rectangle rect) {
		if (rect.getY() - MOVE >= 0)
			rect.setY(rect.getY() - MOVE);
	}
	
	//Checks the GRID 
	public static boolean moveA(Blocks form) {
		return (Tetris.GRID[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveB(Blocks form) {
		return (Tetris.GRID[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveC(Blocks form) {
		return (Tetris.GRID[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveD(Blocks form) {
		return (Tetris.GRID[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1] == 1);
	}
	
	//checks if Block can be moved to the designated position
	public static boolean checkBlockMove(Rectangle rect, int x, int y) {
		boolean xb = false;
		boolean yb = false;
		if (x >= 0)
			xb = (rect.getX() + x * MOVE) <= (XMAX - SIZE);
		if (x < 0)
			xb = (rect.getX() + x * MOVE) >= 0;
		if (y >= 0)
			yb = (rect.getY() - y * MOVE) >= 0;
		if (y < 0)
			yb = (rect.getY() - y * MOVE) < YMAX;
		
		if(xb == true && yb == true && Tetris.GRID[((int) rect.getX()/ SIZE) + x][((int) rect.getY() / SIZE) - y] == 0) 
			return true;
		else 
			return false;
	}
	
	public static void Rotate(Blocks form) {
		int f = form.form;
		Rectangle a = form.a;
		Rectangle b = form.b;
		Rectangle c = form.c;
		Rectangle d = form.d;
		switch (form.getName()) {
		case "j":
			if (f == 1 && checkBlockMove(a, 2, 0) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, -1)) {
				MoveRight(form.a);
				MoveRight(form.a);
				MoveUp(form.b);
				MoveRight(form.b);
				MoveDown(form.d);
				MoveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2) {
				if (checkBlockMove(a, 0, -2) && checkBlockMove(b, 1, -1) && checkBlockMove(d, -1, 1)) {
					MoveDown(form.a);
					MoveDown(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 1, -2) && checkBlockMove(b, 2, -1) && checkBlockMove(d, 0, 1) && checkBlockMove(c, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 0) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, -2, 0) && checkBlockMove(b, -1, -1) && checkBlockMove(d, 1, 1)) {
				MoveLeft(form.a);
				MoveLeft(form.a);
				MoveDown(form.b);
				MoveLeft(form.b);
				MoveUp(form.d);
				MoveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, 0, 2) && checkBlockMove(b, -1, 1) && checkBlockMove(d, 1, -1)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 2) && checkBlockMove(b, -2, 1) && checkBlockMove(d, 0, -1) && checkBlockMove(c, -1, 0)){
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveUp(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 4) && checkBlockMove(b, -1, 3) && checkBlockMove(d, 1, 1) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveUp(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				}
			}
			break;
		case "l":
			if (f == 1 && checkBlockMove(a, 0, -2) && checkBlockMove(d, -1, -1) && checkBlockMove(b, 1, 1)) {
				MoveDown(form.a);
				MoveDown(form.a);
				MoveDown(form.d);
				MoveLeft(form.d);
				MoveUp(form.b);
				MoveRight(form.b);
				form.changeForm();
				break;
			}
			if (f == 2) {
				if (checkBlockMove(a, -2, 0) && checkBlockMove(d, -1, 1) && checkBlockMove(b, 1, -1)) {
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.d);
					MoveUp(form.d);
					MoveRight(form.b);
					MoveDown(form.b);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 0) && checkBlockMove(d, 0, 1) && checkBlockMove(b, 2, -1) && checkBlockMove(c, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.d);
					MoveUp(form.d);
					MoveRight(form.b);
					MoveDown(form.b);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -2, 2) && checkBlockMove(d, -1, 3) && checkBlockMove(b, 1, 1) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.d);
					MoveUp(form.d);
					MoveRight(form.b);
					MoveDown(form.b);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, 0, 2) && checkBlockMove(d, 1, 1) && checkBlockMove(b, -1, -1)) {
				MoveUp(form.a);
				MoveUp(form.a);
				MoveUp(form.d);
				MoveRight(form.d);
				MoveDown(form.b);
				MoveLeft(form.b);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, 2, 0) && checkBlockMove(d, 1, -1) && checkBlockMove(b, -1, 1)) {
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveUp(form.b);
					MoveLeft(form.b);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 1, 0) && checkBlockMove(d, 0, -1) && checkBlockMove(b, -2, 1) && checkBlockMove(c, -1, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveUp(form.b);
					MoveLeft(form.b);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 2, 2) && checkBlockMove(d, 1, 1) && checkBlockMove(b, -1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveUp(form.b);
					MoveLeft(form.b);
					form.changeForm();
					break;
				}
			}
			break;
		case "o":
			break;
		case "s":
			if (f == 1 && checkBlockMove(a, 0, -2) && checkBlockMove(b, 1, -1) && checkBlockMove(d, 1, 1)) {
				MoveDown(form.a);
				MoveDown(form.a);
				MoveRight(form.b);
				MoveDown(form.b);
				MoveUp(form.d);
				MoveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 2) {
				if (checkBlockMove(a, -2, 0) && checkBlockMove(b, -1, -1) && checkBlockMove(d, 1, -1)) {
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 0) && checkBlockMove(b, 0, -1) && checkBlockMove(d, 2, -1) && checkBlockMove(c, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -2, 2) && checkBlockMove(b, -1, 1) && checkBlockMove(d, 1, 1) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, 0, 2) && checkBlockMove(b, -1, 1) && checkBlockMove(d, -1, -1)) {
				MoveUp(form.a);
				MoveUp(form.a);
				MoveLeft(form.b);
				MoveUp(form.b);
				MoveDown(form.d);
				MoveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, 2, 0) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, 1)) {
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.b);
					MoveUp(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 1, 0) && checkBlockMove(b, 0, 1) && checkBlockMove(d, -2, 1) && checkBlockMove(c, -1, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.b);
					MoveUp(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 2, 2) && checkBlockMove(b, 1, 3) && checkBlockMove(d, -1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.b);
					MoveUp(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				}
			}
			break;
		case "t":
			if (f == 1) {
				if (checkBlockMove(a, 1, -1) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, -1)) {
					MoveRight(form.a);
					MoveDown(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveLeft(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (Tetris.GRID[(int) (form.b.getX() / SIZE)][(int) (form.b.getY() / SIZE) - 1] == 1 && checkBlockMove(a, 0, -3) && checkBlockMove(b, 0, -1) && checkBlockMove(d, -2, -3) && checkBlockMove(c, -1, -2)) {
					// T-spin
					Tetris.T_spin = true;
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveDown(form.b);
					MoveDown(form.b);
					MoveLeft(form.b);
					MoveDown(form.c);
					MoveDown(form.c);
					MoveLeft(form.c);
					MoveDown(form.d);
					MoveDown(form.d);
					MoveLeft(form.d);
					MoveRight(form.a);
					MoveDown(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveLeft(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 2) {
				if (checkBlockMove(a, -1, -1) && checkBlockMove(b, 1, -1) && checkBlockMove(d, -1, 1)) {
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveUp(form.d);
					MoveLeft(form.d);
					form.changeForm();
					if (moveD(form) && moveA(form) && moveB(form) && Tetris.GRID[(int) (form.d.getX() / SIZE)][(int) (form.d.getY() / SIZE) - 1] == 1)
						Tetris.T_spin = true;
					break;
				} else if (checkBlockMove(a, 0, -1) && checkBlockMove(b, 2, -1) && checkBlockMove(d, 0, 1) && checkBlockMove(c, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveUp(form.d);
					MoveLeft(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 1) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveRight(form.b);
					MoveDown(form.b);
					MoveUp(form.d);
					MoveLeft(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, -1, 1) && checkBlockMove(b, -1, -1) && checkBlockMove(d, 1, 1)) {
				MoveLeft(form.a);
				MoveUp(form.a);
				MoveDown(form.b);
				MoveLeft(form.b);
				MoveUp(form.d);
				MoveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, 1, 1) && checkBlockMove(b, -1, 1) && checkBlockMove(d, 1, -1)) {
					MoveRight(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveDown(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 1) && checkBlockMove(b, -2, 1) && checkBlockMove(d, 0, -1) && checkBlockMove(c, -1, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveRight(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveDown(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(b, -2, 0) && checkBlockMove(c, -1, -1) && checkBlockMove(d, 0, -2)) {
					// T-spin_mini
					Tetris.T_spin_mini = true;
					MoveLeft(form.b);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveDown(form.c);
					MoveDown(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 1, 3) && checkBlockMove(b, -1, 3) && checkBlockMove(d, 1, 1) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveRight(form.a);
					MoveUp(form.a);
					MoveLeft(form.b);
					MoveUp(form.b);
					MoveDown(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				}
			}
			break;
		case "z":
			if (f == 1 && checkBlockMove(a, 2, 0) && checkBlockMove(b, 1, -1) && checkBlockMove(d, -1, -1)) {
				MoveRight(form.a);
				MoveRight(form.a);
				MoveRight(form.b);
				MoveDown(form.b);
				MoveDown(form.d);
				MoveLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2) {
				if (checkBlockMove(a, 0, -2) && checkBlockMove(b, -1, -1) && checkBlockMove(d, -1, 1)) {
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 1, -2) && checkBlockMove(b, 0, -1) && checkBlockMove(d, 0, 1) && checkBlockMove(c, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 0) && checkBlockMove(b, -1, 1) && checkBlockMove(d, -1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.b);
					MoveDown(form.b);
					MoveLeft(form.d);
					MoveUp(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, -2, 0) && checkBlockMove(b, -1, 1) && checkBlockMove(d, 1, 1)) {
				MoveLeft(form.a);
				MoveLeft(form.a);
				MoveLeft(form.b);
				MoveUp(form.b);
				MoveUp(form.d);
				MoveRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, 0, 2) && checkBlockMove(b, 1, 1) && checkBlockMove(d, 1, -1)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 2) && checkBlockMove(b, 0, 1) && checkBlockMove(d, 0, -1) && checkBlockMove(c, -1, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 4) && checkBlockMove(b, 1, 3) && checkBlockMove(d, 1, 2) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveRight(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				}
			}
			break;
		case "i":
			if (f == 1) {
				if (checkBlockMove(a, 2, 2) && checkBlockMove(b, 1, 1) && checkBlockMove(d, -1, -1)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveDown(form.d);
					MoveLeft(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 2, 1) && checkBlockMove(b, 1, 0) && checkBlockMove(d, -1, -2)) {
					MoveDown(form.a);
					MoveDown(form.b);
					MoveDown(form.c);
					MoveDown(form.d);
					MoveUp(form.a);
					MoveUp(form.a);
					MoveRight(form.a);
					MoveRight(form.a);
					MoveUp(form.b);
					MoveRight(form.b);
					MoveDown(form.d);
					MoveLeft(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 2) {
				if (checkBlockMove(a, -2, -2) && checkBlockMove(b, -1, -1) && checkBlockMove(d, 1, 1)) {
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveDown(form.b);
					MoveLeft(form.b);
					MoveUp(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -3, -2) && checkBlockMove(b, -2, -1) && checkBlockMove(d, 0, 1) && checkBlockMove(c, -1, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveDown(form.b);
					MoveLeft(form.b);
					MoveUp(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, -2) && checkBlockMove(b, 1, -1) && checkBlockMove(d, 3, 1) && checkBlockMove(c, 2, 0)) {
					MoveRight(form.a);
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveRight(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveDown(form.b);
					MoveLeft(form.b);
					MoveUp(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -2, 0) && checkBlockMove(b, -1, 1) && checkBlockMove(d, 1, 3) && checkBlockMove(c, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveDown(form.a);
					MoveDown(form.a);
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveDown(form.b);
					MoveLeft(form.b);
					MoveUp(form.d);
					MoveRight(form.d);
					form.changeForm();
					break;
				}
			}
			if (f == 3 && checkBlockMove(a, 1, -1) && checkBlockMove(c, -1, 1) && checkBlockMove(d, -2, 2)) {
				MoveRight(form.a);
				MoveDown(form.a);
				MoveLeft(form.c);
				MoveUp(form.c);
				MoveLeft(form.d);
				MoveLeft(form.d);
				MoveUp(form.d);
				MoveUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 4) {
				if (checkBlockMove(a, -1, 1) && checkBlockMove(c, 1, -1) && checkBlockMove(d, 2, -2)) {
					MoveLeft(form.a);
					MoveUp(form.a);
					MoveRight(form.c);
					MoveDown(form.c);
					MoveRight(form.d);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, 0, 1) && checkBlockMove(c, 2, -1) && checkBlockMove(d, 3, -2) && checkBlockMove(b, 1, 0)) {
					MoveRight(form.a);
					MoveRight(form.b);
					MoveRight(form.c);
					MoveRight(form.d);
					MoveLeft(form.a);
					MoveUp(form.a);
					MoveRight(form.c);
					MoveDown(form.c);
					MoveRight(form.d);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -3, 1) && checkBlockMove(c, -1, -1) && checkBlockMove(d, 0, -2) && checkBlockMove(b, -2, 0)) {
					MoveLeft(form.a);
					MoveLeft(form.a);
					MoveLeft(form.b);
					MoveLeft(form.b);
					MoveLeft(form.c);
					MoveLeft(form.c);
					MoveLeft(form.d);
					MoveLeft(form.d);
					MoveLeft(form.a);
					MoveUp(form.a);
					MoveRight(form.c);
					MoveDown(form.c);
					MoveRight(form.d);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				} else if (checkBlockMove(a, -1, 3) && checkBlockMove(c, 1, 1) && checkBlockMove(d, 2, 0) && checkBlockMove(b, 0, 2)) {
					MoveUp(form.a);
					MoveUp(form.a);
					MoveUp(form.b);
					MoveUp(form.b);
					MoveUp(form.c);
					MoveUp(form.c);
					MoveUp(form.d);
					MoveUp(form.d);
					MoveLeft(form.a);
					MoveUp(form.a);
					MoveRight(form.c);
					MoveDown(form.c);
					MoveRight(form.d);
					MoveRight(form.d);
					MoveDown(form.d);
					MoveDown(form.d);
					form.changeForm();
					break;
				}
			}
			break;
		}
	}
	public static void moveOnKeyPress(Blocks form) {
			
			Tetris.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
				@SuppressWarnings("incomplete-switch")
				@Override
				public void handle(KeyEvent event) {
					switch (event.getCode()) {
					case RIGHT:
						Controller.MoveRight(form);
						Tetris.MoveShadowDown();
						break;
					case DOWN:
						Tetris.MoveDown(form);
						MediaPlayer Move_player = new MediaPlayer(Move);
						Move_player.setVolume(0.3);
						Move_player.play();
						Tetris.score++;
						Tetris.scoreText.setText("SCORE: " + Integer.toString(Tetris.score));
						break;
					case LEFT:
						Controller.MoveLeft(form);
						Tetris.MoveShadowDown();
						break;
					case UP:
						Rotate(form);
						MediaPlayer Rotate_player = new MediaPlayer(Rotate);
						Rotate_player.setVolume(0.4);
						Rotate_player.play();
						Tetris.MoveShadowDown();
						break; 
					case SPACE:
						Drop(form);
						Tetris.scoreText.setText("SCORE: " + Integer.toString(Tetris.score));
						break;
					case X:
						Tetris.hold();
						MediaPlayer hold_player = new MediaPlayer(Hold);
						hold_player.setVolume(0.5);
						hold_player.play();
						Tetris.MoveShadowDown();
						break;
					case ESCAPE:
						System.exit(0);
					}
				}
			});
		}
	
	public static void keyLock(Blocks form) {
		
		Tetris.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@SuppressWarnings("incomplete-switch")
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ESCAPE:
					System.exit(0);
				}
			}
		});
	}
	
	public static void Drop (Blocks form) {
		while(Tetris.FLAG == false) {
			Tetris.MoveDown(form);
			Tetris.score += 2;
		}
		Tetris.FLAG = false;
	}
}
