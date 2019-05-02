package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	// UI
	private static BufferedImage background_01;
	private static BufferedImage background_02;
	private static BufferedImage background_03;
	private static BufferedImage background_04;
	
	private static BufferedImage frame;
	private static BufferedImage frame_01_05;
	
	private static BufferedImage frame_10_01;
	private static BufferedImage frame_10_02;
	
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
	private static BufferedImage batteringRamImage;
	private static BufferedImage cavalryArcherImage;
	private static BufferedImage cavalryImage;
	private static BufferedImage knightImage;
	private static BufferedImage heroImage;
	private static BufferedImage dragonImage;
	private static BufferedImage priestImage;
	private static BufferedImage mangonelImage;
	
	// Buildings
	private static BufferedImage buildingImage;
	private static BufferedImage woodGetterImage;
	private static BufferedImage goldGetterImage;
	private static BufferedImage metalForgeImage;
	private static BufferedImage foodGetterImage;
	private static BufferedImage stoneGetterImage;
	private static BufferedImage manaGetterImage;
	private static BufferedImage archerTowerImage;
	private static BufferedImage mageTowerImage;
	private static BufferedImage townCenterImage;
	private static BufferedImage stableImage;
	private static BufferedImage churchImage;
	private static BufferedImage siegeWorkshopImage;
	private static BufferedImage barracksImage;
	
	// SpellBook !!! WARNING: Some of the pngs are .PNG -> Runnable JAR can't find them if you write .png !!!
	private static BufferedImage spellBook01_67; //Build (unused)
	private static BufferedImage spellBook01_93; //Ability Placeholder
	private static BufferedImage spellBook01_22; //Move
	private static BufferedImage spellBook01_46; //FireBall
	
	private static BufferedImage spellBook05_06; //MetalForge
	private static BufferedImage spellBook05_20; //Barracks
	private static BufferedImage spellBook05_22; //ArcherTower
	private static BufferedImage spellBook05_28; //Farm
	private static BufferedImage spellBook05_56; //RangedAttack
	private static BufferedImage spellBook05_72; //Wall (unused)
	private static BufferedImage spellBook05_75; //WoodGetter
	private static BufferedImage spellBook05_76; //SiegeWorkshop
	private static BufferedImage spellBook05_77; //Quarry
	private static BufferedImage spellBook05_95; //GoldMine
	
	private static BufferedImage sgi_09; //Archer
	private static BufferedImage sgi_11; //MeleeAttack
	private static BufferedImage sgi_27; //SiegeAttack
	private static BufferedImage sgi_46; //Mage
	private static BufferedImage sgi_63; //Warrior
	private static BufferedImage sgi_81; //MageTower
	private static BufferedImage sgi_90; //StoneGet
	private static BufferedImage sgi_128; //WoodGet
	private static BufferedImage sgi_138; //Trebuchet
	private static BufferedImage sgi_150; //ManaGetter
	private static BufferedImage sgi_157; //Builder
	private static BufferedImage sgi_162; //FoodGet
	private static BufferedImage sgi_164; //GoldGet
	
	private static BufferedImage sgi_addons_170; //ManaGet
	private static BufferedImage sgi_addons_172; //MetalGet
	
	private static BufferedImage green_20; //Heal
	private static BufferedImage yellow_36; //RangedAttack
	
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
	
	public static BufferedImage getBatteringRamImage() {
		if(batteringRamImage == null) {
			try {
				batteringRamImage = ImageIO.read(Boot.class.getResource("/resources/battering_ram.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return batteringRamImage;
	}
	
	public static BufferedImage getCavalryImage() {
		if(cavalryImage == null) {
			try {
				cavalryImage = ImageIO.read(Boot.class.getResource("/resources/cavalry.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return cavalryImage;
	}
	
	public static BufferedImage getCavalryArcherImage() {
		if(cavalryArcherImage == null) {
			try {
				cavalryArcherImage = ImageIO.read(Boot.class.getResource("/resources/cavalry_archer.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return cavalryArcherImage;
	}
	
	public static BufferedImage getKnightImage() {
		if(knightImage == null) {
			try {
				knightImage = ImageIO.read(Boot.class.getResource("/resources/knight.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return knightImage;
	}
	
	public static BufferedImage getHeroImage() {
		if(heroImage == null) {
			try {
				heroImage = ImageIO.read(Boot.class.getResource("/resources/hero.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return heroImage;
	}
	
	public static BufferedImage getDragonImage() {
		if(dragonImage == null) {
			try {
				dragonImage = ImageIO.read(Boot.class.getResource("/resources/dragon.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return dragonImage;
	}
	
	public static BufferedImage getPriestImage() {
		if(priestImage == null) {
			try {
				priestImage = ImageIO.read(Boot.class.getResource("/resources/priest.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return priestImage;
	}
	
	public static BufferedImage getMangonelImage() {
		if(mangonelImage == null) {
			try {
				mangonelImage = ImageIO.read(Boot.class.getResource("/resources/mangonel.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return mangonelImage;
	}
	
	public static BufferedImage getBuildingImage() {
		if(buildingImage == null) {
			try {
				buildingImage = ImageIO.read(Boot.class.getResource("/resources/building.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return buildingImage;
	}
	
	public static BufferedImage getWoodGetterImage() {
		if(woodGetterImage == null) {
			try {
				woodGetterImage = ImageIO.read(Boot.class.getResource("/resources/wood_getter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return woodGetterImage;
	}
	
	public static BufferedImage getGoldGetterImage() {
		if(goldGetterImage == null) {
			try {
				goldGetterImage = ImageIO.read(Boot.class.getResource("/resources/gold_getter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return goldGetterImage;
	}
	
	public static BufferedImage getFoodGetterImage() {
		if(foodGetterImage == null) {
			try {
				foodGetterImage = ImageIO.read(Boot.class.getResource("/resources/food_getter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return foodGetterImage;
	}
	
	public static BufferedImage getStoneGetterImage() {
		if(stoneGetterImage == null) {
			try {
				stoneGetterImage = ImageIO.read(Boot.class.getResource("/resources/stone_getter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return stoneGetterImage;
	}
	
	public static BufferedImage getManaGetterImage() {
		if(manaGetterImage == null) {
			try {
				manaGetterImage = ImageIO.read(Boot.class.getResource("/resources/mana_getter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return manaGetterImage;
	}
	
	public static BufferedImage getMetalForgeImage() {
		if(metalForgeImage == null) {
			try {
				metalForgeImage = ImageIO.read(Boot.class.getResource("/resources/metal_forge.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return metalForgeImage;
	}
	
	public static BufferedImage getArcherTowerImage() {
		if(archerTowerImage == null) {
			try {
				archerTowerImage = ImageIO.read(Boot.class.getResource("/resources/archer_tower.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return archerTowerImage;
	}
	
	public static BufferedImage getMageTowerImage() {
		if(mageTowerImage == null) {
			try {
				mageTowerImage = ImageIO.read(Boot.class.getResource("/resources/mage_tower.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return mageTowerImage;
	}
	
	public static BufferedImage getTownCenterImage() {
		if(townCenterImage == null) {
			try {
				townCenterImage = ImageIO.read(Boot.class.getResource("/resources/towncenter.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return townCenterImage;
	}
	
	public static BufferedImage getBarracksImage() {
		if(barracksImage == null) {
			try {
				barracksImage = ImageIO.read(Boot.class.getResource("/resources/barracks.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return barracksImage;
	}
	
	public static BufferedImage getChurchImage() {
		if(churchImage == null) {
			try {
				churchImage = ImageIO.read(Boot.class.getResource("/resources/church.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return churchImage;
	}
	
	public static BufferedImage getStableImage() {
		if(stableImage == null) {
			try {
				stableImage = ImageIO.read(Boot.class.getResource("/resources/stable.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return stableImage;
	}
	
	public static BufferedImage getSiegeWorkshopImage() {
		if(siegeWorkshopImage == null) {
			try {
				siegeWorkshopImage = ImageIO.read(Boot.class.getResource("/resources/siege_workshop.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return siegeWorkshopImage;
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
	
	public static BufferedImage getBackground_04() {
		if(background_04 == null) {
			try {
				background_04 = ImageIO.read(Boot.class.getResource("/resources/UI/background_04.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return background_04;
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
	
	public static BufferedImage getFrame_10_01() {
		if(frame_10_01 == null) {
			try {
				frame_10_01 = ImageIO.read(Boot.class.getResource("/resources/UI/frame_10_01.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return frame_10_01;
	}
	
	public static BufferedImage getFrame_10_02() {
		if(frame_10_02 == null) {
			try {
				frame_10_02 = ImageIO.read(Boot.class.getResource("/resources/UI/frame_10_02.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return frame_10_02;
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

	public static BufferedImage getSpellBook01_67() {
		if(spellBook01_67 == null) {
			try {
				spellBook01_67 = ImageIO.read(Boot.class.getResource("/resources/spellbook/SpellBook01_67.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook01_67;
	}

	public static BufferedImage getSpellBook01_93() {
		if(spellBook01_93 == null) {
			try {
				spellBook01_93 = ImageIO.read(Boot.class.getResource("/resources/spellbook/SpellBook01_93.PNG"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook01_93;
	}
	
	public static BufferedImage getSpellBook01_22() {
		if(spellBook01_22 == null) {
			try {
				spellBook01_22 = ImageIO.read(Boot.class.getResource("/resources/spellbook/SpellBook01_22.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook01_22;
	}
	
	public static BufferedImage getSpellBook01_46() {
		if(spellBook01_46 == null) {
			try {
				spellBook01_46 = ImageIO.read(Boot.class.getResource("/resources/spellbook/SpellBook01_46.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook01_46;
	}
	
	public static BufferedImage getSpellBook05_06() {
		if(spellBook05_06 == null) {
			try {
				spellBook05_06 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_06.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_06;
	}
	
	public static BufferedImage getSpellBook05_20() {
		if(spellBook05_20 == null) {
			try {
				spellBook05_20 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_20.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_20;
	}
	
	public static BufferedImage getSpellBook05_56() {
		if(spellBook05_56 == null) {
			try {
				spellBook05_56 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_56.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_56;
	}
	
	public static BufferedImage getSpellBook05_72() {
		if(spellBook05_72 == null) {
			try {
				spellBook05_72 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_72.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_72;
	}
	
	public static BufferedImage getSpellBook05_75() {
		if(spellBook05_75 == null) {
			try {
				spellBook05_75 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_75.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_75;
	}
	
	public static BufferedImage getSpellBook05_76() {
		if(spellBook05_76 == null) {
			try {
				spellBook05_76 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_76.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_76;
	}
	
	public static BufferedImage getSpellBook05_77() {
		if(spellBook05_77 == null) {
			try {
				spellBook05_77 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_77.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_77;
	}
	
	public static BufferedImage getSpellBook05_95() {
		if(spellBook05_95 == null) {
			try {
				spellBook05_95 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_95.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_95;
	}
	
	public static BufferedImage getSpellBook05_28() {
		if(spellBook05_28 == null) {
			try {
				spellBook05_28 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_28.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_28;
	}
	
	public static BufferedImage getSpellBook05_22() {
		if(spellBook05_22 == null) {
			try {
				spellBook05_22 = ImageIO.read(Boot.class.getResource("/resources/SpellBookPage05/SpellBook05_22.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return spellBook05_22;
	}
	
	public static BufferedImage getSGI_09() {
		if(sgi_09 == null) {
			try {
				sgi_09 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_09.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_09;
	}
	
	public static BufferedImage getSGI_11() {
		if(sgi_11 == null) {
			try {
				sgi_11 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_11.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_11;
	}
	
	public static BufferedImage getSGI_27() {
		if(sgi_27 == null) {
			try {
				sgi_27 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_27.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_27;
	}
	
	public static BufferedImage getSGI_46() {
		if(sgi_46 == null) {
			try {
				sgi_46 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_46.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_46;
	}
	
	public static BufferedImage getSGI_63() {
		if(sgi_63 == null) {
			try {
				sgi_63 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_63.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_63;
	}
	
	public static BufferedImage getSGI_81() {
		if(sgi_81 == null) {
			try {
				sgi_81 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_81.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_81;
	}
	
	public static BufferedImage getSGI_90() {
		if(sgi_90 == null) {
			try {
				sgi_90 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_90.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_90;
	}
	
	public static BufferedImage getSGI_128() {
		if(sgi_128 == null) {
			try {
				sgi_128 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_128.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_128;
	}
	
	public static BufferedImage getSGI_138() {
		if(sgi_138 == null) {
			try {
				sgi_138 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_138.PNG"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_138;
	}
	
	public static BufferedImage getSGI_150() {
		if(sgi_150 == null) {
			try {
				sgi_150 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_150.PNG"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_150;
	}
	
	public static BufferedImage getSGI_157() {
		if(sgi_157 == null) {
			try {
				sgi_157 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_157.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_157;
	}
	
	public static BufferedImage getSGI_162() {
		if(sgi_162 == null) {
			try {
				sgi_162 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_162.PNG"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_162;
	}
	
	public static BufferedImage getSGI_164() {
		if(sgi_164 == null) {
			try {
				sgi_164 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_164.PNG"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_164;
	}
	
	public static BufferedImage getSGI_addons_170() {
		if(sgi_addons_170 == null) {
			try {
				sgi_addons_170 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_addons_170.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_addons_170;
	}
	
	public static BufferedImage getSGI_addons_172() {
		if(sgi_addons_172 == null) {
			try {
				sgi_addons_172 = ImageIO.read(Boot.class.getResource("/resources/StrategyGameIcons/SGI_addons_172.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return sgi_addons_172;
	}
	
	public static BufferedImage getGreen_20() {
		if(green_20 == null) {
			try {
				green_20 = ImageIO.read(Boot.class.getResource("/resources/green_20.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return green_20;
	}
	
	public static BufferedImage getYellow_36() {
		if(yellow_36 == null) {
			try {
				yellow_36 = ImageIO.read(Boot.class.getResource("/resources/yellow_36.png"));
			} catch (IOException e) {
				return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
			}
		}
		return yellow_36;
	}
	
}
