package tetris_battle;

import tetris_battle.Blocks;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Colors {
	
	//The following is the gradient data for the colors painted on the pieces
			public static Stop[] stops1 = new Stop[] { new Stop(0, Color.DEEPSKYBLUE), new Stop(1, Color.BLANCHEDALMOND)};
			public static Stop[] stops2 = new Stop[] { new Stop(0, Color.ORANGE), new Stop(1, Color.AZURE)};
			public static Stop[] stops3 = new Stop[] { new Stop(0, Color.YELLOW), new Stop(1, Color.FLORALWHITE)};
			public static Stop[] stops4 = new Stop[] { new Stop(0, Color.SPRINGGREEN), new Stop(1, Color.MISTYROSE)};
			public static Stop[] stops5 = new Stop[] { new Stop(0, Color.FUCHSIA), new Stop(1, Color.LIGHTCYAN)};
			public static Stop[] stops6 = new Stop[] { new Stop(0, Color.CRIMSON), new Stop(1, Color.PEACHPUFF)};
			public static Stop[] stops7 = new Stop[] { new Stop(0, Color.DODGERBLUE), new Stop(1, Color.LAVENDERBLUSH)};
			
			public static LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
			public static LinearGradient lg2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops2);
			public static LinearGradient lg3 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops3);
			public static LinearGradient lg4 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops4);
			public static LinearGradient lg5 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops5);
			public static LinearGradient lg6 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops6);
			public static LinearGradient lg7 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops7);
			
			
			public static void Paint(Blocks Object, int colorType) {
				switch(colorType) {
				case 1:
					Paint(Object.a, Object.b, Object.c, Object.d, 1);
					break;
				case 2:
					Paint(Object.a, Object.b, Object.c, Object.d, 2);
					break;
				case 3:
					Paint(Object.a, Object.b, Object.c, Object.d, 3);
					break;
				case 4:
					Paint(Object.a, Object.b, Object.c, Object.d, 4);
					break;
				case 5:
					Paint(Object.a, Object.b, Object.c, Object.d, 5);
					break;
				case 6:
					Paint(Object.a, Object.b, Object.c, Object.d, 6);
					break;
				case 7:
					Paint(Object.a, Object.b, Object.c, Object.d, 7);
					break;
				}
			}
			
			public static void Paint(Rectangle a, Rectangle b, Rectangle c, Rectangle d, int colorType) {
				switch(colorType) {
				case 1:
					a.setFill(lg1);
					b.setFill(lg1);
					c.setFill(lg1);
					d.setFill(lg1);
					break;
				case 2:
					a.setFill(lg2);
					b.setFill(lg2);
					c.setFill(lg2);
					d.setFill(lg2);
					break;
				case 3:
					a.setFill(lg3);
					b.setFill(lg3);
					c.setFill(lg3);
					d.setFill(lg3);
					break;
				case 4:
					a.setFill(lg4);
					b.setFill(lg4);
					c.setFill(lg4);
					d.setFill(lg4);
					break;
				case 5:
					a.setFill(lg5);
					b.setFill(lg5);
					c.setFill(lg5);
					d.setFill(lg5);
					break;
				case 6:
					a.setFill(lg6);
					b.setFill(lg6);
					c.setFill(lg6);
					d.setFill(lg6);
					break;
				case 7:
					a.setFill(lg7);
					b.setFill(lg7);
					c.setFill(lg7);
					d.setFill(lg7);
					break;
				}
			}
	
}
