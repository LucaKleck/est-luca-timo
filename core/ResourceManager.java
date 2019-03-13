package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	// UI
	private static BufferedImage background_01;
	private static BufferedImage background_02;
	private static BufferedImage background_03;
	
	private static BufferedImage frame;
	private static BufferedImage frame_01_05;
	
	private static BufferedImage slot;
	
	private static BufferedImage button_01;

	private static BufferedImage slider_01_01;
	private static BufferedImage slider_01_02;
	private static BufferedImage slider_02_01;
	private static BufferedImage slider_02_03;
	private static BufferedImage slider_02_04;
	
	private static BufferedImage button_04_bg;
	private static BufferedImage button_04_frame;
	
	private static BufferedImage text_bg_02;
	
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
	
	public static BufferedImage getBackground_01() {
		if(background_01 == null) {
			try {
				background_01 = ImageIO.read(Boot.class.getResource("/resources/UI/background_01.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return background_01;
	}
	
	public static BufferedImage getBackground_02() {
		if(background_02 == null) {
			try {
				background_02 = ImageIO.read(Boot.class.getResource("/resources/UI/background_02.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return background_02;
	}
	
	public static BufferedImage getBackground_03() {
		if(background_03 == null) {
			try {
				background_03 = ImageIO.read(Boot.class.getResource("/resources/UI/background_03.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return background_03;
	}

	public static BufferedImage getFrame() {
		if(frame == null) {
			try {
				frame = ImageIO.read(Boot.class.getResource("/resources/UI/frame.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return frame;
	}
	
	public static BufferedImage getFrame_01_05() {
		if(frame_01_05 == null) {
			try {
				frame_01_05 = ImageIO.read(Boot.class.getResource("/resources/UI/frame_01_05.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return frame_01_05;
	}
	
	public static BufferedImage getSlot() {
		if(slot == null) {
			try {
				slot = ImageIO.read(Boot.class.getResource("/resources/UI/slot.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slot;
	}
	
	public static BufferedImage getButton_01() {
		if(button_01 == null) {
			try {
				button_01 = ImageIO.read(Boot.class.getResource("/resources/UI/button_01.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return button_01;
	}
	
	public static BufferedImage getButton_04_bg() {
		if(button_04_bg == null) {
			try {
				button_04_bg = ImageIO.read(Boot.class.getResource("/resources/UI/button_04_bg.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return button_04_bg;
	}
	
	public static BufferedImage getButton_04_frame() {
		if(button_04_frame == null) {
			try {
				button_04_frame = ImageIO.read(Boot.class.getResource("/resources/UI/button_04_frame.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return button_04_frame;
	}
	
	public static BufferedImage getSlider_02_04() {
		if(slider_02_04 == null) {
			try {
				slider_02_04 = ImageIO.read(Boot.class.getResource("/resources/UI/slider_02_04.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slider_02_04;
	}

	public static BufferedImage getSlider_01_01() {
		if(slider_01_01 == null) {
			try {
				slider_01_01 = ImageIO.read(Boot.class.getResource("/resources/UI/slider_01_01.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slider_01_01;
	}

	public static BufferedImage getSlider_01_02() {
		if(slider_01_02 == null) {
			try {
				slider_01_02 = ImageIO.read(Boot.class.getResource("/resources/UI/slider_01_02.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slider_01_02;
	}

	public static BufferedImage getSlider_02_03() {
		if(slider_02_03 == null) {
			try {
				slider_02_03 = ImageIO.read(Boot.class.getResource("/resources/UI/slider_02_03.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slider_02_03;
	}
	
	public static BufferedImage getText_bg_02() {
		if(text_bg_02 == null) {
			try {
				text_bg_02 = ImageIO.read(Boot.class.getResource("/resources/UI/text_bg_02.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return text_bg_02;
	}

	public static BufferedImage getSlider_02_01() {
		if(slider_02_01 == null) {
			try {
				slider_02_01 = ImageIO.read(Boot.class.getResource("/resources/UI/slider_02_01.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return slider_02_01;
	}

}
