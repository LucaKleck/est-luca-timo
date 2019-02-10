package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	private static BufferedImage backgroundWoodVertical;
	private static BufferedImage backgroundWoodHorizontal;
	
	private ResourceManager() {
	}
	
	public static BufferedImage getBackgroundWoodVertical() {
		if(backgroundWoodVertical == null) {
			try {
				backgroundWoodVertical = ImageIO.read(Boot.class.getResource("/resources/IMG_BackgroundWoodVertical.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return backgroundWoodVertical;
	}
	
	public static BufferedImage getBackgroundWoodHorizontal() {
		if(backgroundWoodHorizontal == null) {
			try {
				backgroundWoodHorizontal = ImageIO.read(Boot.class.getResource("/resources/IMG_BackgroundWoodHorizontal.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return backgroundWoodHorizontal;
	}
}
