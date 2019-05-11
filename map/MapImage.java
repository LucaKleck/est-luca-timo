package map;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import abilities.Ability;
import core.Boot;
import core.Core;
import core.GameInfo;
import core.ResourceManager;
import entity.Entity;
import entity.building.Building;
import entity.building.DefenseBuilding;
import entity.building.ProductionBuilding;
import entity.building.ResourceBuilding;
import entity.unit.Archer;
import entity.unit.BatteringRam;
import entity.unit.Builder;
import entity.unit.Cavalry;
import entity.unit.CavalryArcher;
import entity.unit.Dragon;
import entity.unit.Hero;
import entity.unit.Knight;
import entity.unit.Mage;
import entity.unit.Mangonel;
import entity.unit.Priest;
import entity.unit.Trebuchet;
import entity.unit.Unit;
import entity.unit.Warrior;
import frame.gamePanels.MainGamePanel;

/**
 * @author Luca Kleck
 * @version 2
 *
 */
public class MapImage {

	public static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	private static final int[] TOP_LEFT = { -1, -1 };
	private static final int[] TOP = { 0, -1 };
	private static final int[] TOP_RIGHT = { 1, -1 };
	private static final int[] LEFT = { -1, 0 };
	private static final int[] RIGHT = { 1, 0 };
	private static final int[] BOTTOM_LEFT = { -1, 1 };
	private static final int[] BOTTOM = { 0, 1 };
	private static final int[] BOTTOM_RIGHT = { 1, 1 };

	private BufferedImage selectionLayer; // selection is drawn here
	private BufferedImage effectLayer; // draw unit movement arrows and so on
	private BufferedImage unitBuildingLayer; // draw Units and buildings
	private BufferedImage decalLayer; // draw map tile overlays
	private BufferedImage mapTileLayer; // draw map tiles
	private BufferedImage abilityLayer;

	private static final Lock mapImageLock = new ReentrantLock();
	private static int mapTileSize;
	private static int imageWidth;
	private static int imageHeight;

	// external resources
	// Map Tiles
	private static BufferedImage plainImage;

	// Forest
	private static BufferedImage forestImage;
	private static BufferedImage forestImage192;

	private static BufferedImage forestImageLeftRight;
	private static BufferedImage forestImageTopBottom;

	private static BufferedImage forestImageEndBottom;
	private static BufferedImage forestImageEndLeft;
	private static BufferedImage forestImageEndTop;
	private static BufferedImage forestImageEndRight;

	// Mountain
	private static BufferedImage mountainImage;
	private static BufferedImage mountainImage192;
	private static BufferedImage mountainImageBottomLeftCurve;
	private static BufferedImage mountainImageBottomRightCurve;
	private static BufferedImage mountainImageTopLeftCurve;
	private static BufferedImage mountainImageTopRightCurve;
	private static BufferedImage mountainImageV;
	private static BufferedImage mountainImageH;
	private static BufferedImage mountainImageEndLeft;
	private static BufferedImage mountainImageEndRight;
	private static BufferedImage mountainImageEndTop;
	private static BufferedImage mountainImageEndBottom;
	private static BufferedImage mountainImageBottomLeftNotch;
	private static BufferedImage mountainImageBottomRightNotch;
	private static BufferedImage mountainImageTopLeftNotch;
	private static BufferedImage mountainImageTopRightNotch;

	// River
	private static BufferedImage riverImage;
	private static BufferedImage riverImage192;

	private static BufferedImage riverH;
	private static BufferedImage riverV;

	// Road
	private static BufferedImage roadH;
	private static BufferedImage roadV;

	private static BufferedImage roadTopLeft;
	private static BufferedImage roadTopRight;
	private static BufferedImage roadBottomLeft;
	private static BufferedImage roadBottomRight;

	

	private static BufferedImage portalImage;

	private BufferedImage combinedImage;

	private static final ExecutorService redrawMapImageService = Executors.newFixedThreadPool(1);

	private boolean updateFlag = true;

	public MapImage(int width, int height) {
		if (plainImage == null) {
			try {
				plainImage = ImageIO.read(Boot.class.getResource("/resources/plain.png"));

				// Forest
				forestImage = ImageIO.read(Boot.class.getResource("/resources/forest.png"));
				forestImage192 = ImageIO.read(Boot.class.getResource("/resources/forest192.png"));

				forestImageLeftRight = ImageIO.read(Boot.class.getResource("/resources/forestLeftRight.png"));
				forestImageTopBottom = ImageIO.read(Boot.class.getResource("/resources/forestTopBottom.png"));

				forestImageEndBottom = ImageIO.read(Boot.class.getResource("/resources/forestEndBottom.png"));
				forestImageEndLeft = ImageIO.read(Boot.class.getResource("/resources/forestEndLeft.png"));
				forestImageEndTop = ImageIO.read(Boot.class.getResource("/resources/forestEndTop.png"));
				forestImageEndRight = ImageIO.read(Boot.class.getResource("/resources/forestEndRight.png"));

				// Mountain
				mountainImageEndLeft = ImageIO.read(Boot.class.getResource("/resources/mountainEndLeft.png"));
				mountainImageEndRight = ImageIO.read(Boot.class.getResource("/resources/mountainEndRight.png"));
				mountainImageEndTop = ImageIO.read(Boot.class.getResource("/resources/mountainEndTop.png"));
				mountainImageEndBottom = ImageIO.read(Boot.class.getResource("/resources/mountainEndBottom.png"));
				mountainImage = ImageIO.read(Boot.class.getResource("/resources/mountain.png"));

				mountainImageH = ImageIO.read(Boot.class.getResource("/resources/mountainH.png"));
				mountainImageV = ImageIO.read(Boot.class.getResource("/resources/mountainV.png"));

				mountainImageTopRightCurve = ImageIO
						.read(Boot.class.getResource("/resources/mountainTopRightCurve.png"));
				mountainImageTopLeftCurve = ImageIO.read(Boot.class.getResource("/resources/mountainTopLeftCurve.png"));
				mountainImageBottomRightCurve = ImageIO
						.read(Boot.class.getResource("/resources/mountainBottomRightCurve.png"));
				mountainImageBottomLeftCurve = ImageIO
						.read(Boot.class.getResource("/resources/mountainBottomLeftCurve.png"));
				mountainImage192 = ImageIO.read(Boot.class.getResource("/resources/mountain192.png"));

				mountainImageTopLeftNotch = ImageIO.read(Boot.class.getResource("/resources/mountainTopLeftNotch.png"));
				mountainImageBottomLeftNotch = ImageIO
						.read(Boot.class.getResource("/resources/mountainBottomLeftNotch.png"));
				mountainImageTopRightNotch = ImageIO
						.read(Boot.class.getResource("/resources/mountainTopRightNotch.png"));
				mountainImageBottomRightNotch = ImageIO
						.read(Boot.class.getResource("/resources/mountainBottomRightNotch.png"));

				// River
				riverImage = ImageIO.read(Boot.class.getResource("/resources/pond.png"));
				riverImage192 = ImageIO.read(Boot.class.getResource("/resources/pond192.png"));

				riverH = ImageIO.read(Boot.class.getResource("/resources/riverH.png"));
				riverV = ImageIO.read(Boot.class.getResource("/resources/riverV.png"));

				// Road

				roadH = ImageIO.read(Boot.class.getResource("/resources/roadH.png"));
				roadV = ImageIO.read(Boot.class.getResource("/resources/roadV.png"));

				roadTopLeft = ImageIO.read(Boot.class.getResource("/resources/roadTopLeft.png"));
				roadTopRight = ImageIO.read(Boot.class.getResource("/resources/roadTopRight.png"));
				roadBottomLeft = ImageIO.read(Boot.class.getResource("/resources/roadBottomLeft.png"));
				roadBottomRight = ImageIO.read(Boot.class.getResource("/resources/roadBottomRight.png"));

				portalImage = ImageIO.read(Boot.class.getResource("/resources/portal.png"));

			} catch (IOException e) {
			}
		}

		imageWidth = width;
		imageHeight = height;
		mapTileSize = (int) (width / GameInfo.getObjectMap().getMap().length);

		mapTileLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		decalLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		drawMapTileLayer();
		drawDecalLayer();

		unitBuildingLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		effectLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		selectionLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);
		abilityLayer = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);

		combinedImage = new BufferedImage(imageWidth, imageHeight, IMAGE_TYPE);

		redrawMapImageService.execute(new GraphicsTask(this));

	}

	private void drawDecalLayer() {
		Graphics2D g = getDecalLayer().createGraphics();
		for (int x = 0; x < GameInfo.getObjectMap().getMap().length; x++) {
			for (int y = 0; y < GameInfo.getObjectMap().getMap()[0].length; y++) {
				if (GameInfo.getObjectMap().getMap()[x][y].isRoad()) {
					g.drawImage(getRoadImageForTile(x, y), x * mapTileSize, y * mapTileSize, mapTileSize, mapTileSize,
							null);
				}
			}
		}
	}

	private void drawMapTileLayer() {
		Graphics2D g = getMapTileLayer().createGraphics();
		for (int xRow = 0; xRow < GameInfo.getObjectMap().getMap().length; xRow++) {
			for (int yColumn = 0; yColumn < GameInfo.getObjectMap().getMap()[0].length; yColumn++) {
				g.drawImage(getImageForTile(xRow, yColumn), xRow * mapTileSize, yColumn * mapTileSize, mapTileSize,
						mapTileSize, null);
			}
		}
	}

	private void drawUnitBuildingLayer() {
		Graphics2D g = getUnitBuildingLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		ArrayList<Entity> e = GameInfo.getObjectMap().getEntityMap();
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Iterator<Entity> iterator = e.iterator(); iterator.hasNext();) {
			Entity s = iterator.next();
			
			if (s instanceof Unit) {
				units.add((Unit) s);
			} else if (s instanceof Building) {
				if (s instanceof ResourceBuilding) {
					if (s.getName().matches(Building.WOOD_GETTER)) {
						g.drawImage(ResourceManager.getWoodGetterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.GOLD_GETTER)) {
						g.drawImage(ResourceManager.getGoldGetterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.FOOD_GETTER)) {
						g.drawImage(ResourceManager.getFoodGetterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.METAL_GETTER)) {
						g.drawImage(ResourceManager.getMetalForgeImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.STONE_GETTER)) {
						g.drawImage(ResourceManager.getStoneGetterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.MANA_GETTER)) {
						g.drawImage(ResourceManager.getManaGetterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else {
						g.drawImage(ResourceManager.getBuildingImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					}
				}
				if (s instanceof ProductionBuilding) {
					if (s.getName().matches(Building.TOWN_CENTER)) {
						g.drawImage(ResourceManager.getTownCenterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.PORTAL)) {
						g.drawImage(portalImage, s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.SIEGE_WORKSHOP)) {
						g.drawImage(ResourceManager.getSiegeWorkshopImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.CHURCH)) {
						g.drawImage(ResourceManager.getChurchImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.BARRACKS)) {
						g.drawImage(ResourceManager.getBarracksImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else if (s.getName().matches(Building.STABLE)) {
						g.drawImage(ResourceManager.getStableImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else {
						g.drawImage(ResourceManager.getTownCenterImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					}
				}
				if (s instanceof DefenseBuilding) {
					if (s.getName().matches(Building.ARCHER_TOWER)) {
						g.drawImage(ResourceManager.getArcherTowerImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					} else {
						g.drawImage(ResourceManager.getMageTowerImage(), s.getXPos() * mapTileSize, s.getYPos() * mapTileSize, null);
					}
				}
			}
		}
		for(Unit u : units) {
			if (u instanceof Unit) {
				if(u.isControlable()) {
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.RED);
				}
				if (GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
					if (GameInfo.getObjectMap().getSelected().getSelectedEntity().equals(u)) {
						g.setColor(Color.WHITE);
					}
				}
				g.fillRoundRect((int) (u.getPoint().x * mapTileSize) - 5, (int) (u.getPoint().y * mapTileSize) - 5, 10,
						10, 10, 10);
				if (u instanceof Warrior) {
					g.drawImage(ResourceManager.getWarriorImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Mage) {
					g.drawImage(ResourceManager.getMageImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Builder) {
					g.drawImage(ResourceManager.getBuilderImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Archer) {
					g.drawImage(ResourceManager.getArcherImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Trebuchet) {
					g.drawImage(ResourceManager.getTrebuchetImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof BatteringRam) {
					g.drawImage(ResourceManager.getBatteringRamImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Cavalry) {
					g.drawImage(ResourceManager.getCavalryImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof CavalryArcher) {
					g.drawImage(ResourceManager.getCavalryArcherImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Knight) {
					g.drawImage(ResourceManager.getKnightImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Hero) {
					g.drawImage(ResourceManager.getHeroImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Dragon) {
					g.drawImage(ResourceManager.getDragonImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Priest) {
					g.drawImage(ResourceManager.getPriestImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Mangonel) {
					g.drawImage(ResourceManager.getMangonelImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				} else if (u instanceof Mangonel) {
					g.drawImage(ResourceManager.getMangonelImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				}else {
					g.drawImage(ResourceManager.getBuilderImage(), (int) (u.getPoint().x * mapTileSize) - 8,
							(int) (u.getPoint().y * mapTileSize) - 8, null);
				}
			}
		}
	}

	private void drawEffectLayer() {
		Graphics2D g = getEffectLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		for (int i = 0; i < GameInfo.getRoundInfo().getEventList().size(); i++) {
			// draw each effect
			if (GameInfo.getRoundInfo().getEventList().get(i).getEffect() != null) {
				g.drawImage(GameInfo.getRoundInfo().getEventList().get(i).getEffect(), 0, 0, imageWidth, imageHeight,
						null);
			}
		}
	}

	public void drawAbilityLayer(Ability ability, int x, int y) {
		int range = ability.maxRange;
		int totalRange = (range + range + 1);

		Graphics2D g = getAbilityLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		g.setColor(new Color(255, 255, 0, 120));

		for (int row = 0; row < totalRange; row++) {
			for (int col = 0; col < totalRange; col++) {
				if (!((x - range + col) == x && (y - range + row) == y)) {
						g.fillRect((x - range + col) * mapTileSize, (y - range + row) * mapTileSize, mapTileSize,
								mapTileSize);
				}
			}
		}
	}

	public void clearAbilityLayer() {

		Graphics2D g = getAbilityLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		g.setColor(new Color(255, 255, 0, 120));

	}

	private void drawSelectionLayer() {
		Graphics2D g = getSelectionLayer().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		g.setColor(new Color(255, 0, 0, 120));
		if (GameInfo.getObjectMap().getSelected().getSelectedMapTile() != null) {
			g.fillRect(GameInfo.getObjectMap().getSelected().getSelectedMapTile().getXPos() * mapTileSize,
					GameInfo.getObjectMap().getSelected().getSelectedMapTile().getYPos() * mapTileSize, mapTileSize,
					mapTileSize);
		}
		if (GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
			g.setColor(new Color(0, 0, 255, 120));
			g.fillRect(GameInfo.getObjectMap().getSelected().getSelectedEntity().getXPos() * mapTileSize,
					GameInfo.getObjectMap().getSelected().getSelectedEntity().getYPos() * mapTileSize, mapTileSize,
					mapTileSize);
		}
	}

	private BufferedImage getRoadImageForTile(int x, int y) {
		boolean top = checkRoad(x, y, TOP);
		boolean right = checkRoad(x, y, RIGHT);
		boolean bottom = checkRoad(x, y, BOTTOM);
		boolean left = checkRoad(x, y, LEFT);

		if (left && top) {
			return roadBottomRight;
		} else if (left && bottom) {
			return roadTopRight;
		} else if (top && right) {
			return roadBottomLeft;
		} else if (bottom && right) {
			return roadTopLeft;
		} else if (top || bottom) {
			return roadV;
		} else if (left || right) {
			return roadH;
		} else {
			return roadH;
		}

	}

	private BufferedImage getImageForTile(int x, int y) {
		MapTile mapTile = GameInfo.getObjectMap().getMap()[x][y];
		boolean top = checkTile(x, y, mapTile.getType(), TOP);
		boolean right = checkTile(x, y, mapTile.getType(), RIGHT);
		boolean bottom = checkTile(x, y, mapTile.getType(), BOTTOM);
		boolean left = checkTile(x, y, mapTile.getType(), LEFT);
		boolean topLeft = checkTile(x, y, mapTile.getType(), TOP_LEFT);
		boolean topRight = checkTile(x, y, mapTile.getType(), TOP_RIGHT);
		boolean bottomLeft = checkTile(x, y, mapTile.getType(), BOTTOM_LEFT);
		boolean bottomRight = checkTile(x, y, mapTile.getType(), BOTTOM_RIGHT);

		if (mapTile.getName().matches(MapTile.NAME_PLAIN)) {
			return plainImage;
		} else if (mapTile.getName().matches(MapTile.NAME_FOREST)) {
			// MIDDLE
			if (top && right && bottom && left) {
				return forestImage192.getSubimage(64, 64, 64, 64);
			}

			// CORNERS
			if (!top && right && bottom && !left) {
				return forestImage192.getSubimage(0, 0, 64, 64);
			}
			if (!top && !right && bottom && left) {
				return forestImage192.getSubimage(128, 0, 64, 64);
			}
			if (top && !right && !bottom && left) {
				return forestImage192.getSubimage(128, 128, 64, 64);
			}
			if (top && right && !bottom && !left) {
				return forestImage192.getSubimage(0, 128, 64, 64);
			}

			// RIGHT, LEFT, TOP, BOTTOM
			if (top && right && bottom && !left) {
				return forestImage192.getSubimage(0, 64, 64, 64);
			}
			if (!top && right && bottom && left) {
				return forestImage192.getSubimage(64, 0, 64, 64);
			}
			if (top && !right && bottom && left) {
				return forestImage192.getSubimage(128, 64, 64, 64);
			}
			if (top && right && !bottom && left) {
				return forestImage192.getSubimage(64, 128, 64, 64);
			}

			// LEFT TO RIGHT
			if (!top && right && !bottom && !left) {
				return forestImageEndLeft;
			}
			if (!top && !right && !bottom && left) {
				return forestImageEndRight;
			}
			if (!top && right && !bottom && left) {
				return forestImageLeftRight;
			}

			// TOP TO BOTTOM
			if (!top && !right && bottom && !left) {
				return forestImageEndTop;
			}
			if (top && !right && !bottom && !left) {
				return forestImageEndBottom;
			}
			if (top && !right && bottom && !left) {
				return forestImageTopBottom;
			}

			// DEFAULT
			if (!top && !right && !bottom && !left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return forestImage;
			}
			return forestImage;
		} else if (mapTile.getName().matches(MapTile.NAME_MOUNTAIN)) {
			// Notches
			if (top && right && bottom && left && topRight && !topLeft && bottomRight && bottomLeft) {
				return mountainImageTopLeftNotch;
			}
			if (top && right && bottom && left && !topRight && topLeft && bottomRight && bottomLeft) {
				return mountainImageTopRightNotch;
			}
			if (top && right && bottom && left && topRight && topLeft && !bottomRight && bottomLeft) {
				return mountainImageBottomRightNotch;
			}
			if (top && right && bottom && left && topRight && topLeft && bottomRight && !bottomLeft) {
				return mountainImageBottomLeftNotch;
			}
			// MIDDLE
			if (top && right && bottom && left) {
				return mountainImage192.getSubimage(64, 64, 64, 64);
			}

			// RIGHT, LEFT, TOP, BOTTOM
			if (top && right && bottom && !left) {
				return mountainImage192.getSubimage(0, 64, 64, 64);
			}
			if (!top && right && bottom && left) {
				return mountainImage192.getSubimage(64, 0, 64, 64);
			}
			if (top && !right && bottom && left) {
				return mountainImage192.getSubimage(128, 64, 64, 64);
			}
			if (top && right && !bottom && left) {
				return mountainImage192.getSubimage(64, 128, 64, 64);
			}

			// Curves
			if (top && right && !bottom && !left && !topRight) {
				return mountainImageBottomLeftCurve;
			}
			if (top && !right && !bottom && left && !topLeft) {
				return mountainImageBottomRightCurve;
			}
			if (!top && right && bottom && !left && !bottomRight) {
				return mountainImageTopLeftCurve;
			}
			if (!top && !right && bottom && left && !bottomLeft) {
				return mountainImageTopRightCurve;
			}

			// CORNERS
			if (!top && right && bottom && !left) {
				return mountainImage192.getSubimage(0, 0, 64, 64);
			}
			if (!top && !right && bottom && left) {
				return mountainImage192.getSubimage(128, 0, 64, 64);
			}
			if (top && !right && !bottom && left) {
				return mountainImage192.getSubimage(128, 128, 64, 64);
			}
			if (top && right && !bottom && !left) {
				return mountainImage192.getSubimage(0, 128, 64, 64);
			}

			// Horizontal and Vertical
			if (!top && right && !bottom && left) {
				return mountainImageH;
			}
			if (top && !right && bottom && !left) {
				return mountainImageV;
			}

			// Ends
			if (!top && !right && !bottom && left) {
				return mountainImageEndRight;
			}
			if (!top && !right && bottom && !left) {
				return mountainImageEndTop;
			}
			if (!top && right && !bottom && !left) {
				return mountainImageEndLeft;
			}
			if (top && !right && !bottom && !left) {
				return mountainImageEndBottom;
			}

			// LEFT TO RIGHT
			/*
			 * if(!top && right && !bottom && !left) { return forestImageEndLeft; } if(!top
			 * && !right && !bottom && left) { return forestImageEndRight; }
			 */
			// if(!top && right && !bottom && left) {
			// return riverH;
			// }

			// TOP TO BOTTOM
			/*
			 * if(!top && !right && bottom && !left) { return forestImageEndTop; } if(top &&
			 * !right && !bottom && !left) { return forestImageEndBottom; }
			 */
			// if(top && !right && bottom && !left) {
			// return riverV;
			// }

			// DEFAULT
			if (!top && !right && !bottom && !left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return mountainImage;
			}
			return mountainImage;
		} else if (mapTile.getName().matches(MapTile.NAME_RIVER)) {
			// MIDDLE
			if (top && right && bottom && left) {
				return riverImage192.getSubimage(64, 64, 64, 64);
			}

			// CORNERS
			if (!top && right && bottom && !left) {
				return riverImage192.getSubimage(0, 0, 64, 64);
			}
			if (!top && !right && bottom && left) {
				return riverImage192.getSubimage(128, 0, 64, 64);
			}
			if (top && !right && !bottom && left) {
				return riverImage192.getSubimage(128, 128, 64, 64);
			}
			if (top && right && !bottom && !left) {
				return riverImage192.getSubimage(0, 128, 64, 64);
			}

			// RIGHT, LEFT, TOP, BOTTOM
			if (top && right && bottom && !left) {
				return riverImage192.getSubimage(0, 64, 64, 64);
			}
			if (!top && right && bottom && left) {
				return riverImage192.getSubimage(64, 0, 64, 64);
			}
			if (top && !right && bottom && left) {
				return riverImage192.getSubimage(128, 64, 64, 64);
			}
			if (top && right && !bottom && left) {
				return riverImage192.getSubimage(64, 128, 64, 64);
			}

			// LEFT TO RIGHT
			/*
			 * if(!top && right && !bottom && !left) { return forestImageEndLeft; } if(!top
			 * && !right && !bottom && left) { return forestImageEndRight; }
			 */
			if (!top && right && !bottom && left) {
				return riverH;
			}

			// TOP TO BOTTOM
			/*
			 * if(!top && !right && bottom && !left) { return forestImageEndTop; } if(top &&
			 * !right && !bottom && !left) { return forestImageEndBottom; }
			 */
			if (top && !right && bottom && !left) {
				return riverV;
			}

			// DEFAULT
			if (!top && !right && !bottom && !left && !topLeft && !topRight && !bottomLeft && !bottomRight) {
				return riverImage;
			}
			return riverImage;
		} else {
			return plainImage;
		}
	}

	private boolean checkTile(int x, int y, int type, int[] pos) {
		try {
			if (GameInfo.getObjectMap().getMap()[(x + pos[0])][y + pos[1]].getType() == type) {
				return true;
			}
		} catch (IndexOutOfBoundsException iex) {
			return true;
		}
		return false;
	}

	private boolean checkRoad(int x, int y, int[] pos) {
		try {
			if (GameInfo.getObjectMap().getMap()[(x + pos[0])][y + pos[1]].isRoad()) {
				return true;
			}
		} catch (IndexOutOfBoundsException iex) {
		}
		return false;
	}

	public void redrawArea(int xStart, int xEnd, int yStart, int yEnd) {
		int xDiff = 0;
		int yDiff = 0;
		//
		if (xStart > xEnd) {
			xDiff = xStart - xEnd;
		} else if (xStart < xEnd) {
			xDiff = xEnd - xStart;
		} else {
			xDiff = 1;
		}
		//
		if (yStart > yEnd) {
			yDiff = yStart - yEnd;
		} else if (yStart < yEnd) {
			yDiff = yEnd - yStart;
		} else {
			yDiff = 1;
		}
		//
		for (int xCount = 0; xCount < xDiff; xCount++) {
			for (int yCount = 0; yCount < yDiff; yCount++) {
				// Make versions of drawXY that are with x/y coords
			}
		}
	}

	public void drawCombinedImage() {
		Graphics2D g = getCombinedImage().createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.setComposite(AlphaComposite.SrcOver);
		g.drawImage(getDecalLayer(), 0, 0, null);
		g.drawImage(getAbilityLayer(), 0, 0, null);
		g.drawImage(getSelectionLayer(), 0, 0, null);
		g.drawImage(getUnitBuildingLayer(), 0, 0, null);
		g.drawImage(getEffectLayer(), 0, 0, null);
	}

	public synchronized BufferedImage getSelectionLayer() {
		return selectionLayer;
	}

	public synchronized BufferedImage getAbilityLayer() {
		return abilityLayer;
	}

	public synchronized BufferedImage getEffectLayer() {
		return effectLayer;
	}

	public synchronized BufferedImage getUnitBuildingLayer() {
		return unitBuildingLayer;
	}

	public synchronized BufferedImage getDecalLayer() {
		return decalLayer;
	}

	public synchronized BufferedImage getMapTileLayer() {
		return mapTileLayer;
	}

	public synchronized BufferedImage getCombinedImage() {
		return combinedImage;
	}

	public static int getImageHeight() {
		return imageHeight;
	}

	public static int getImageWidth() {
		return imageWidth;
	}

	public double getMapTileSize() {
		return mapTileSize;
	}

	public synchronized void update() {
		this.updateFlag = true;
	}

	public ReentrantLock getMapImageLock() {
		return (ReentrantLock) mapImageLock;
	}

	private class GraphicsTask implements Runnable {

		private boolean run = true;
		private MapImage mapImage;

		public GraphicsTask(MapImage mapImage) {
			this.mapImage = mapImage;
		}

		@Override
		public void run() {
			while (run) {
				if (updateFlag) {
					ExecutorService drawWorkers = Executors.newCachedThreadPool();
					Future<String> decalDrawing = drawWorkers.submit(new Callable<String>() {

						@Override
						public String call() throws Exception {
							drawDecalLayer();
							return null;
						}
					});

					Future<String> selectionDrawing = drawWorkers.submit(new Callable<String>() {

						@Override
						public String call() throws Exception {
							drawSelectionLayer();
							return null;
						}
					});

					Future<String> unitBuildingDrawing = drawWorkers.submit(new Callable<String>() {

						@Override
						public String call() throws Exception {
							drawUnitBuildingLayer();
							return null;
						}
					});

					Future<String> effectDrawing = drawWorkers.submit(new Callable<String>() {

						@Override
						public String call() throws Exception {
							drawEffectLayer();
							return null;
						}
					});

					while (!decalDrawing.isDone())
						;
					while (!selectionDrawing.isDone())
						;
					while (!unitBuildingDrawing.isDone())
						;
					while (!effectDrawing.isDone())
						;

					drawWorkers.shutdown();

					while (((ReentrantLock) mapImageLock).isLocked())
						;
					mapImageLock.lock();
					drawCombinedImage();
					mapImageLock.unlock();
					updateFlag = false;
				}
				if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					MainGamePanel mgf = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
					if (!mgf.getMapPanel().getMapImage().equals(mapImage)) {
						mapImage = null;
						run = false;
					}
				}
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
