package tetris_battle;




import javafx.scene.shape.Rectangle;


public class Blocks {
	
	public static int[] spawn = Tetris.spawn; // This array saves data on spawned pieces to avoid repeating
	
	public static final int MOVE = Tetris.MOVE;
	public static final int SIZE = Tetris.SIZE;
	public static int XMAX = Tetris.XMAX;
	public static int YMAX = Tetris.YMAX;
	public static int[][] Board = Tetris.GRID;
	
	
	//Each block is consisted of four small squares -> a, b, c, d
	Rectangle a;
	Rectangle b;
	Rectangle c;
	Rectangle d;
	
	private String name;//name is the type of block
	public int form = 1;//form is for rotating, each form represents a certain form its currently displayed
	
	public Blocks(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Blocks(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;

		switch (name) {
		case "j":	
			Colors.Paint(this.a, this.b, this.c, this.d, 1);
			break;
			
		case "l":
			Colors.Paint(this.a, this.b, this.c, this.d, 2);
			break;
			
		case "o":
			Colors.Paint(this.a, this.b, this.c, this.d, 3);
			break;
			
		case "s":
			Colors.Paint(this.a, this.b, this.c, this.d, 4);
			break;	
			
		case "t":
			Colors.Paint(this.a, this.b, this.c, this.d, 5);          
			break;
			
		case "z":
			Colors.Paint(this.a, this.b, this.c, this.d, 6);
			break;
			
		case "i":
			Colors.Paint(this.a, this.b, this.c, this.d, 7);
			break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void changeForm() {
		form++;
		if(form == 5) {
			form = 1;
		}
	}
	
	//As its name implies, this creates new blocks for the game
	public static Blocks createBlock(int type) {

		String name = "";
		Rectangle a = new Rectangle(SIZE - 1, SIZE - 1), b = new Rectangle(SIZE - 1, SIZE - 1), c = new Rectangle(SIZE - 1, SIZE - 1),
				d = new Rectangle(SIZE - 1, SIZE - 1);

		switch(type) {
		case 0:
			Positions.spawnPosition(a, b, c, d, type);
			name = "j";
			break;
		case 1:
			Positions.spawnPosition(a, b, c, d, type);
			name = "l";
			break;
		case 2:
			Positions.spawnPosition(a, b, c, d, type);
			name = "o";
			break;
		case 3: 
			Positions.spawnPosition(a, b, c, d, type);
			name = "s";
			break;
		case 4: 
			Positions.spawnPosition(a, b, c, d, type);
			name = "t";
			break;
		case 5:
			Positions.spawnPosition(a, b, c, d, type);
			name = "z";
			break;
		case 6:
			Positions.spawnPosition(a, b, c, d, type);
			name = "i";
			break;
		}
		
		return new Blocks(a, b, c, d, name);
	}
	
	public static Blocks setPreviewBlock(int type, int num) {
		String name = "";
		Rectangle a = new Rectangle(SIZE - 1, SIZE - 1), 
				  b = new Rectangle(SIZE - 1, SIZE - 1), 
				  c = new Rectangle(SIZE - 1, SIZE - 1),
				  d = new Rectangle(SIZE - 1, SIZE - 1);

		switch(type) {
			case 0:
				name = "j";
				break;
			case 1:
				name = "l";
				break;
			case 2:
				name = "o";
				break;
			case 3: 
				name = "s";
				break;
			case 4: 
				name = "t";
				break;
			case 5:
				name = "z";
				break;
			case 6:
				name = "i";
				break;
		}
		switch(num) {
			case 1:
				Positions.previewNext1(a, b, c, d, name);
				break;
			case 2:
				Positions.previewNext2(a, b, c, d, name);
				break;
			case 3:
				Positions.previewHold(a, b, c, d, name);
				break;
		}
		
		return new Blocks(a, b, c, d, name);
	}
}


