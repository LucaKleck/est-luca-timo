package map;

import java.awt.image.BufferedImage;

public class MapImageEffect extends BufferedImage {

	public MapImageEffect(String command) {
		super(MapImage.getImageWidth(), MapImage.getImageHeight(), TYPE_INT_ARGB);
		drawCommand(command);
	}

	// example command: "unitMove,attack=true,x1=10,x2=20,y1=10,y2=20" // May change
	// to drawCommand(String command, boolean attack, int x0, int x1, int y0, int
	// y2)
	private void drawCommand(String command) {
		if (command.contains("unitMove")) {
			drawArrow(extractAttack(command), extractXOne(command), extractXTwo(command), extractYOne(command),
					extractYTwo(command));
		}
	}

	private void drawArrow(boolean attack, int extractXOne, int extractXTwo, int extractYOne, int extractYTwo) {

	}

	private int extractYTwo(String command) {

		return 0;
	}

	private int extractYOne(String command) {
		return 0;
	}

	private int extractXTwo(String command) {
		return 0;
	}

	private int extractXOne(String command) {
//		StringBuilder xOne = new StringBuilder(command.toCharArray());

		return 0;
	}

	private boolean extractAttack(String command) {
		return false;
	}

}
