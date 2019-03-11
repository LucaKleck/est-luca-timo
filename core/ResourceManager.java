package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	private static BufferedImage backgroundWoodVertical;
	private static BufferedImage backgroundWoodHorizontal;
	
	// Units
	private static BufferedImage warriorImage;
	private static BufferedImage mageImage;
	private static BufferedImage builderImage;
	private static BufferedImage archerImage;
	private static BufferedImage trebuchetImage;
	
	private ResourceManager() {
	}
	
	public static BufferedImage getWarriorImage() {
		if(warriorImage == null) {
			try {
				warriorImage = ImageIO.read(Boot.class.getResource("/resources/warrior.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return warriorImage;
	}
	
	public static BufferedImage getMageImage() {
		if(mageImage == null) {
			try {
				mageImage = ImageIO.read(Boot.class.getResource("/resources/mage.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return mageImage;
	}
	
	public static BufferedImage getBuilderImage() {
		if(builderImage == null) {
			try {
				builderImage = ImageIO.read(Boot.class.getResource("/resources/builder.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return builderImage;
	}
	
	public static BufferedImage getArcherImage() {
		if(archerImage == null) {
			try {
				archerImage = ImageIO.read(Boot.class.getResource("/resources/archer.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return archerImage;
	}
	
	public static BufferedImage getTrebuchetImage() {
		if(trebuchetImage == null) {
			try {
				trebuchetImage = ImageIO.read(Boot.class.getResource("/resources/trebuchet.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return trebuchetImage;
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
