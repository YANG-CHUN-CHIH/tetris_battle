package tetris_battle;

import tetris_battle.Tetris;
import javafx.scene.shape.Rectangle;

public class Positions {
	
	public static final int SIZE = Tetris.SIZE;
	public static int XMAX = Tetris.XMAX;
	public static int YMAX = Tetris.YMAX;
	
	public static void spawnPosition(Rectangle a, Rectangle b, Rectangle c, Rectangle d,int type) {
		switch(type) {
		case 0:
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			break;
		case 1:
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			break;
		case 2:
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
			break;
		case 3:
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
			break;
		case 4:
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - 2 * SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
			break;
		case 5:
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			break;
		case 6:
			a.setX(XMAX / 2 - SIZE - SIZE);
			a.setY(SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			break;
		}
	}
	
	public static void previewNext1(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String type) {
		switch(type) {
		case "j":
			a.setX(XMAX + 85 - SIZE);
			a.setY(210);
			b.setX(XMAX + 85 - SIZE);
			b.setY(210 + SIZE);
			c.setX(XMAX + 85);
			c.setY(210 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(210 + SIZE);
			break;
		case "l":
			a.setX(XMAX + 85 + SIZE);
			a.setY(210);
			b.setX(XMAX + 85 - SIZE);
			b.setY(210 + SIZE);
			c.setX(XMAX + 85);
			c.setY(210 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(210 + SIZE);
			break;
		case "o":
			a.setX(XMAX + 100 - SIZE);
			a.setY(210);
			b.setX(XMAX + 100);
			b.setY(210);
			c.setX(XMAX + 100 - SIZE);
			c.setY(210 + SIZE);
			d.setX(XMAX + 100);
			d.setY(210 + SIZE);
			break;
		case "s":
			a.setX(XMAX + 85 + SIZE);
			a.setY(210);
			b.setX(XMAX + 85);
			b.setY(210);
			c.setX(XMAX + 85);
			c.setY(210 + SIZE);
			d.setX(XMAX + 85 - SIZE);
			d.setY(210 + SIZE);
			break;
		case "t":
			a.setX(XMAX + 85 - SIZE);
			a.setY(210 + SIZE);
			b.setX(XMAX + 85);
			b.setY(210);
			c.setX(XMAX + 85);
			c.setY(210 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(210 + SIZE);
			break;
		case "z":
			a.setX(XMAX + 55 + SIZE);
			a.setY(210);
			b.setX(XMAX + 55);
			b.setY(210);
			c.setX(XMAX + 55 + SIZE);
			c.setY(210 + SIZE);
			d.setX(XMAX + 55 + SIZE + SIZE);
			d.setY(210 + SIZE);
			break;
		case "i":
			a.setX(XMAX + 100 - SIZE - SIZE);
			a.setY(220);
			b.setX(XMAX + 100 - SIZE);
			b.setY(220);
			c.setX(XMAX + 100);
			c.setY(220);
			d.setX(XMAX + 100 + SIZE);
			d.setY(220);
			break;
		}
	}
	
	public static void previewNext2(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String type) {
		switch(type) {
		case "j":
			a.setX(XMAX + 85 - SIZE);
			a.setY(366);
			b.setX(XMAX + 85 - SIZE);
			b.setY(366 + SIZE);
			c.setX(XMAX + 85);
			c.setY(366 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(366 + SIZE);
			break;
		case "l":
			a.setX(XMAX + 85 + SIZE);
			a.setY(366);
			b.setX(XMAX + 85 - SIZE);
			b.setY(366 + SIZE);
			c.setX(XMAX + 85);
			c.setY(366 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(366 + SIZE);
			break;
		case "o":
			a.setX(XMAX + 100 - SIZE);
			a.setY(366);
			b.setX(XMAX + 100);
			b.setY(366);
			c.setX(XMAX + 100 - SIZE);
			c.setY(366 + SIZE);
			d.setX(XMAX + 100);
			d.setY(366 + SIZE);
			break;
		case "s":
			a.setX(XMAX + 85 + SIZE);
			a.setY(366);
			b.setX(XMAX + 85);
			b.setY(366);
			c.setX(XMAX + 85);
			c.setY(366 + SIZE);
			d.setX(XMAX + 85 - SIZE);
			d.setY(366 + SIZE);
			break;
		case "t":
			a.setX(XMAX + 85 - SIZE);
			a.setY(366 + SIZE);
			b.setX(XMAX + 85);
			b.setY(366);
			c.setX(XMAX + 85);
			c.setY(366 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(366 + SIZE);
			break;
		case "z":
			a.setX(XMAX + 55 + SIZE);
			a.setY(366);
			b.setX(XMAX + 55);
			b.setY(366);
			c.setX(XMAX + 55 + SIZE);
			c.setY(366 + SIZE);
			d.setX(XMAX + 55 + SIZE + SIZE);
			d.setY(366 + SIZE);
			break;
		case "i":
			a.setX(XMAX + 100 - SIZE - SIZE);
			a.setY(366);
			b.setX(XMAX + 100 - SIZE);
			b.setY(366);
			c.setX(XMAX + 100);
			c.setY(366);
			d.setX(XMAX + 100 + SIZE);
			d.setY(366);
			break;
		}
	}
	
	public static void previewHold(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String type) {
		switch(type) {
		case "j":
			a.setX(XMAX + 85 - SIZE);
			a.setY(551);
			b.setX(XMAX + 85 - SIZE);
			b.setY(551 + SIZE);
			c.setX(XMAX + 85);
			c.setY(551 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(551 + SIZE);
			break;
		case "l":
			a.setX(XMAX + 85 + SIZE);
			a.setY(551);
			b.setX(XMAX + 85 - SIZE);
			b.setY(551 + SIZE);
			c.setX(XMAX + 85);
			c.setY(551 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(551 + SIZE);
			break;
		case "o":
			a.setX(XMAX + 100 - SIZE);
			a.setY(551);
			b.setX(XMAX + 100);
			b.setY(551);
			c.setX(XMAX + 100 - SIZE);
			c.setY(551 + SIZE);
			d.setX(XMAX + 100);
			d.setY(551 + SIZE);
			break;
		case "s":
			a.setX(XMAX + 85 + SIZE);
			a.setY(551);
			b.setX(XMAX + 85);
			b.setY(551);
			c.setX(XMAX + 85);
			c.setY(551 + SIZE);
			d.setX(XMAX + 85 - SIZE);
			d.setY(551 + SIZE);
			break;
		case "t":
			a.setX(XMAX + 85 - SIZE);
			a.setY(551 + SIZE);
			b.setX(XMAX + 85);
			b.setY(551);
			c.setX(XMAX + 85);
			c.setY(551 + SIZE);
			d.setX(XMAX + 85 + SIZE);
			d.setY(551 + SIZE);
			break;
		case "z":
			a.setX(XMAX + 55 + SIZE);
			a.setY(551);
			b.setX(XMAX + 55);
			b.setY(551);
			c.setX(XMAX + 55 + SIZE);
			c.setY(551 + SIZE);
			d.setX(XMAX + 55 + SIZE + SIZE);
			d.setY(551 + SIZE);
			break;
		case "i":
			a.setX(XMAX + 100 - SIZE - SIZE);
			a.setY(551);
			b.setX(XMAX + 100 - SIZE);
			b.setY(551);
			c.setX(XMAX + 100);
			c.setY(551);
			d.setX(XMAX + 100 + SIZE);
			d.setY(551);
			break;
		}
	}
}
