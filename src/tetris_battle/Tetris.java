package tetris_battle;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import tetris_battle.Tetris;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tetris extends Application{

	//The public variables
	public static int[] spawn = new int[] {0,0,0,0,0,0,0}; // This array saves data on spawned pieces to avoid repeating
	public static boolean cycle = false; // This is the flag for each cycle when spawning pieces
	public static int [] nextBlocks = {0, 0};
	public static boolean FLAG = false;
	public static int score = 0;
	public static int level = 0;
	public static int linesNo = 0;
	public static final int MOVE = 30;
	public static final int SIZE = 30;
	public static int XMAX = SIZE * 12;
	public static int YMAX = SIZE * 24;
	public static int[][] GRID = new int[XMAX / SIZE][YMAX / SIZE];
	public static Text scoreText;
	public static Text levelText;
	//calculate score
	public static int combo = 0;
	public static Boolean tetris = false;
	public static Boolean T_spin = false;
	public static Boolean T_spin_mini = false;
	public static Boolean B2B = false; // Back to Back
	public static boolean game = true;
	public static int dropSpeed = 700;
	public static Image sakuraPetals = new Image("Petals.gif");
	public static ImageView Petals = new ImageView(sakuraPetals);
	public static Media BGM = new Media(Paths.get("BGM1.mp3").toUri().toString());
    public static MediaPlayer player = new MediaPlayer(BGM);
    public static MediaView mediaView = new MediaView(player);
    public static Media BGM2 = new Media(Paths.get("BGM2.mp3").toUri().toString());
    public static MediaPlayer player2 = new MediaPlayer(BGM2);
    public static MediaView mediaView2 = new MediaView(player2);
	//The private variables
	
	private static Timer timer = new Timer();
	private static Boolean upgrade = false;
	private static Random rand = new Random();
	private static Group group;
	private static Group menuGroup;
	private static Scene scene;
	private static ImageView IV1;
	private static ImageView IV2;
	private static Boolean holded = false;
	private static Blocks currentBlock;
	private static Blocks next1;
	private static Blocks next2;
	private static Blocks hold;
	private static Blocks shadow;
	private static int holdtype = -1;
	private static Media BlockFall = new Media(Paths.get("BlockFall.mp3").toUri().toString());
	//Getter Functions
	public static Scene getScene() {
		return scene;
	}
	
	public static Group getGroup() {
		return group;
	}
	
	public static Blocks getCurrentBlock() {
		return currentBlock;
	}
	
	public static Timer getCurrentTimer() {
		return timer;
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		//clears the GRID and initialize all slots to zero
		

		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		menuGroup = new Group(root.getChildrenUnmodifiable());
		Scene menu = new Scene(menuGroup, XMAX + 200, YMAX);
		menu.getStylesheets().add(getClass().getResource("Menu.css").toExternalForm());
		stage.setScene(menu);
		stage.show();
	
		for (int[] a : GRID) {
			Arrays.fill(a, 0);
		}
		Parent root2 = FXMLLoader.load(getClass().getResource("/tetris_battle/Scene.fxml"));
		group = new Group(root2.getChildrenUnmodifiable());
		scene = new Scene(group, XMAX + 200, YMAX);
		
		group.getChildren().addAll(Petals);
		Petals.setOpacity(0);
		
		//adds BGM to game
		
        player.setCycleCount(MediaPlayer.INDEFINITE);
        group.getChildren().addAll(mediaView);
        player.play();
        
        player2.setCycleCount(MediaPlayer.INDEFINITE);
        group.getChildren().addAll(mediaView2);
        

		//The text for scores
		scoreText = new Text("SCORE: ");
		scoreText.setStyle("-fx-font: 20 arial;");
		scoreText.setY(55);
		scoreText.setX(377);
		
		//The text for lines eliminated
		levelText = new Text("LEVEL: ");
		levelText.setStyle("-fx-font: 20 arial;");
		levelText.setY(88);
		levelText.setX(377);
	    
		group.getChildren().addAll(scoreText, levelText);
		
		//import game over images
		Image gameOver = new Image("GameOver.png");
		IV1 = new ImageView(gameOver);
		IV1.setX(75);
		IV1.setY(125);
		Image exitHint = new Image("ExitHint.png");
		IV2 = new ImageView(exitHint);
		IV2.setX(175);
		IV2.setY(450);
		
		//randomly choose a type of the seven pieces
		int type = rand.nextInt(7);
		currentBlock = Blocks.createBlock(type);
		shadow = Blocks.createBlock(type);
		
		scene.getStylesheets().add(getClass().getResource("/tetris_battle/Scene.css").toExternalForm());
		stage.setResizable(false);
		stage.setTitle("TETRIS");

		MoveShadowDown();
		spawn[type] = 1;
		group.getChildren().addAll(currentBlock.a, currentBlock.b, currentBlock.c, currentBlock.d);
		group.getChildren().addAll(shadow.a, shadow.b, shadow.c, shadow.d);
		
		
		//set icon
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
		
		
		for (int i = 0; i < 2; i++) {
			type = rand.nextInt(7);
			while(spawn[type] == 1) {
				type = rand.nextInt(7);
			}
			spawn[type] = 1;
			nextBlocks[i] = type;
		}
		
		next1 = Blocks.setPreviewBlock(nextBlocks[0], 1);
		group.getChildren().addAll(next1.a, next1.b, next1.c, next1.d);
		next2 = Blocks.setPreviewBlock(nextBlocks[1], 2);
		group.getChildren().addAll(next2.a, next2.b, next2.c, next2.d);
		
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	//Do not Move this into another Class!
	public static void MoveDown(Blocks form) {
			
		if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
				|| form.d.getY() == YMAX - SIZE || Functions.moveA(form) || Functions.moveB(form) 
				|| Functions.moveC(form) || Functions.moveD(form)) {
			Tetris.GRID[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
			Tetris.GRID[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
			Tetris.GRID[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
			Tetris.GRID[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
			MediaPlayer BF_player = new MediaPlayer(BlockFall);
			BF_player.setVolume(0.8);
			BF_player.play();
			Functions.RemoveRows(Tetris.getGroup());
			
			int prev_level = level;
			if (linesNo < 5) {
				level = 0;
			} else if (linesNo < 10) {
				level = 1;
			} else if (linesNo < 15) {
				level = 2;
			} else if (linesNo < 20) {
				level = 3;
			} else if (linesNo < 25) {
				level = 4;
			} else if (linesNo < 30) {
				level = 5;
			} else if (linesNo < 40) {
				level = 6;
			} else if (linesNo < 50) {
				level = 7;
			} else if (linesNo < 60) {
				level = 8;
			} else if (linesNo < 70) {
				level = 9;
			} else if (linesNo < 80) {
				level = 10;
			} else if (linesNo < 95) {
				level = 11;
			} else if (linesNo < 110) {
				level = 12;
			} else if (linesNo < 115) {
				level = 13;
			} else if (linesNo < 130) {
				level = 14;
			} else if (linesNo < 145) {
				level = 15;
			} 
			if (level > prev_level) {
				upgrade = true;
			}
			levelText.setText("LEVEL: " + Integer.toString(level));
			holded = false;
			Tetris.FLAG = true;
			currentBlock = Blocks.createBlock(nextBlocks[0]);
			Tetris.MoveShadowDown();
			nextBlocks[0] = nextBlocks[1];
			int type;
			do {
				type = rand.nextInt(7);
			} while(spawn[type] == 1);
			spawn[type] = 1;
			nextBlocks[1] = type;
			
			//checks for the flag of a cycle
			for (int i = 0; i < 7; i++) {
				if (spawn[i] == 0)
					break;
				if (i == 6) {
					for (int j = 0; j < 7; j++)
						spawn[j] = 0;
				}
			}
			
			if(GRID[5][0] == 1 || GRID[6][0] == 1 ||  GRID[7][0] == 1 || GRID[8][0] == 1) {
					game = false;
					group.getChildren().addAll(IV1, IV2);
					Functions.keyLock(currentBlock);
					timer.cancel();
					return;
			}
			
			group.getChildren().addAll(currentBlock.a, currentBlock.b, currentBlock.c, currentBlock.d);
			group.getChildren().remove(next1.a);
			group.getChildren().remove(next1.b);
			group.getChildren().remove(next1.c);
			group.getChildren().remove(next1.d);
			group.getChildren().remove(next2.a);
			group.getChildren().remove(next2.b);
			group.getChildren().remove(next2.c);
			group.getChildren().remove(next2.d);
			next1 = Blocks.setPreviewBlock(nextBlocks[0], 1);
			group.getChildren().addAll(next1.a, next1.b, next1.c, next1.d);
			next2 = Blocks.setPreviewBlock(nextBlocks[1], 2);
			group.getChildren().addAll(next2.a, next2.b, next2.c, next2.d); 
			
			if (upgrade) {
				timer.cancel();
				timer = new Timer();
				TimerTask task = new TimerTask() {
					public void run() {
						Platform.runLater(new Runnable() {
							public void run() {
							
								if (game){
									MoveDown(currentBlock);
										if(Tetris.level > 4) {
											Tetris.Petals.setOpacity(1);
											player.stop();
											player2.setVolume(0.3);
											player2.play();
											
									}
								} else {
									Functions.moveOnKeyPress(currentBlock);
								}
							}
						});
					}
				};
				timer.schedule(task, 0, dropSpeed / level);
				upgrade = false;
				
			}
			Functions.moveOnKeyPress(currentBlock);
		}

		if (form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
				&& form.d.getY() + MOVE < YMAX) {
			int movea = Tetris.GRID[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
			int moveb = Tetris.GRID[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
			int movec = Tetris.GRID[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
			int moved = Tetris.GRID[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setY(form.a.getY() + MOVE);
				form.b.setY(form.b.getY() + MOVE);
				form.c.setY(form.c.getY() + MOVE);
				form.d.setY(form.d.getY() + MOVE);
			}
		}
	}
	
	public static void hold() {
		if (!holded) {
			if (holdtype == -1) {
				switch (currentBlock.getName()) {
					case "j":
						holdtype = 0;
						break;
					case "l":
						holdtype = 1;
						break;
					case "o":
						holdtype = 2;
						break;
					case "s":
						holdtype = 3;
						break;	
					case "t":   
						holdtype = 4;
						break;
					case "z":
						holdtype = 5;
						break;
					case "i":
						holdtype = 6;
						break;
				}
				group.getChildren().remove(currentBlock.a);
				group.getChildren().remove(currentBlock.b);
				group.getChildren().remove(currentBlock.c);
				group.getChildren().remove(currentBlock.d);
				currentBlock = Blocks.createBlock(nextBlocks[0]);
				nextBlocks[0] = nextBlocks[1];
				int type;
				do {
					type = rand.nextInt(7);
				} while(spawn[type] == 1);
				spawn[type] = 1;
				nextBlocks[1] = type;
				
				//checks for the flag of a cycle
				for (int i = 0; i < 7; i++) {
					if (spawn[i] == 0)
						break;
					if (i == 6) {
						for (int j = 0; j < 7; j++)
							spawn[j] = 0;
					}
				}
				group.getChildren().addAll(currentBlock.a, currentBlock.b, currentBlock.c, currentBlock.d);
				group.getChildren().remove(next1.a);
				group.getChildren().remove(next1.b);
				group.getChildren().remove(next1.c);
				group.getChildren().remove(next1.d);
				group.getChildren().remove(next2.a);
				group.getChildren().remove(next2.b);
				group.getChildren().remove(next2.c);
				group.getChildren().remove(next2.d);
				next1 = Blocks.setPreviewBlock(nextBlocks[0], 1);
				group.getChildren().addAll(next1.a, next1.b, next1.c, next1.d);
				next2 = Blocks.setPreviewBlock(nextBlocks[1], 2);
				group.getChildren().addAll(next2.a, next2.b, next2.c, next2.d); 
				
			} else {
				int temp = holdtype;
				switch (currentBlock.getName()) {
					case "j":
						holdtype = 0;
						break;
					case "l":
						holdtype = 1;
						break;
					case "o":
						holdtype = 2;
						break;
					case "s":
						holdtype = 3;
						break;	
					case "t":   
						holdtype = 4;
						break;
					case "z":
						holdtype = 5;
						break;
					case "i":
						holdtype = 6;
						break;
				}
				group.getChildren().remove(currentBlock.a);
				group.getChildren().remove(currentBlock.b);
				group.getChildren().remove(currentBlock.c);
				group.getChildren().remove(currentBlock.d);
				group.getChildren().remove(hold.a);
				group.getChildren().remove(hold.b);
				group.getChildren().remove(hold.c);
				group.getChildren().remove(hold.d);
				currentBlock = Blocks.createBlock(temp);
				group.getChildren().addAll(currentBlock.a, currentBlock.b, currentBlock.c, currentBlock.d);
			}
			hold = Blocks.setPreviewBlock(holdtype, 3);
			group.getChildren().addAll(hold.a, hold.b, hold.c, hold.d);
			holded = true;
			Functions.moveOnKeyPress(currentBlock);
		}
	}
	
	public static void MoveShadowDown() {
		shadow.a.setX(currentBlock.a.getX());
		shadow.a.setY(currentBlock.a.getY());
		shadow.b.setX(currentBlock.b.getX());
		shadow.b.setY(currentBlock.b.getY());
		shadow.c.setX(currentBlock.c.getX());
		shadow.c.setY(currentBlock.c.getY());
		shadow.d.setX(currentBlock.d.getX());
		shadow.d.setY(currentBlock.d.getY());
		shadow.a.setFill(currentBlock.a.getFill());
		shadow.b.setFill(currentBlock.a.getFill());
		shadow.c.setFill(currentBlock.a.getFill());
		shadow.d.setFill(currentBlock.a.getFill());
		shadow.a.setOpacity(0.5);
		shadow.b.setOpacity(0.5);
		shadow.c.setOpacity(0.5);
		shadow.d.setOpacity(0.5);
		
		while(true) {
			if (shadow.a.getY() == YMAX - SIZE || shadow.b.getY() == YMAX - SIZE || shadow.c.getY() == YMAX - SIZE
					|| shadow.d.getY() == YMAX - SIZE || Functions.moveA(shadow) || Functions.moveB(shadow) 
					|| Functions.moveC(shadow) || Functions.moveD(shadow))
				break;
				
			if (shadow.a.getY() + MOVE < YMAX && shadow.b.getY() + MOVE < YMAX && shadow.c.getY() + MOVE < YMAX
					&& shadow.d.getY() + MOVE < YMAX) {
				int movea = Tetris.GRID[(int) shadow.a.getX() / SIZE][((int) shadow.a.getY() / SIZE) + 1];
				int moveb = Tetris.GRID[(int) shadow.b.getX() / SIZE][((int) shadow.b.getY() / SIZE) + 1];
				int movec = Tetris.GRID[(int) shadow.c.getX() / SIZE][((int) shadow.c.getY() / SIZE) + 1];
				int moved = Tetris.GRID[(int) shadow.d.getX() / SIZE][((int) shadow.d.getY() / SIZE) + 1];
				if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
					shadow.a.setY(shadow.a.getY() + MOVE);
					shadow.b.setY(shadow.b.getY() + MOVE);
					shadow.c.setY(shadow.c.getY() + MOVE);
					shadow.d.setY(shadow.d.getY() + MOVE);
				}
				
			}
		}
		
		
	}

}
