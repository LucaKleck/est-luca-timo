package entity.unit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import abilities.Ability;
import core.Boot;
import core.GameInfo;
import core.Point2DNoFxReq;

public class UnitFactory {

	private static Document namesXML = null;
	private Random random;

	public UnitFactory() {
		random = new Random();
	}

	public Unit getNewPortalUnitByType(String type) {
		return getNewUnit(GameInfo.getObjectMap().getPortalPoint(), 0, false, new ArrayList<Ability>(), type);
	}
	
	public ArrayList<Unit> getNewPortalUnitsByWave(int wave) {
		
		ArrayList<Unit> waveUnits = new ArrayList<Unit>();
		ArrayList<String> unitTypes = new ArrayList<String>();
		
		if(wave > 0) {
			unitTypes.add(Unit.UNIT_WARRIOR);
		}
		if(wave > 1) {
			unitTypes.add(Unit.UNIT_TREBUCHET);
			unitTypes.add(Unit.UNIT_MAGE);
		}
		if(wave > 2) {
			unitTypes.add(Unit.UNIT_MANGONEL);
			unitTypes.add(Unit.UNIT_BATTERING_RAM);
			unitTypes.add(Unit.UNIT_CAVALRY);
		}
		if(wave > 3) {
			unitTypes.add(Unit.UNIT_PRIEST);
			unitTypes.add(Unit.UNIT_CAVALRY_ARCHER);
		}
		if(wave > 5) {
			unitTypes.add(Unit.UNIT_KNIGHT);
		}
		if(wave % 8 == 0) {
			unitTypes.add(Unit.UNIT_BUILDER);
			unitTypes.add(Unit.UNIT_DRAGON);
			unitTypes.add(Unit.UNIT_HERO);
		}
		for(int i = 0; i < 2*wave; i++) {
			waveUnits.add(getNewPortalUnitByType(unitTypes.get(random.nextInt(unitTypes.size()))));
		}
		
		return waveUnits;
		
	}

	private Unit getNewUnit(Point2DNoFxReq point, int level, boolean controlable, ArrayList<Ability> abilities,
			String type) {

		Unit unit;
		Point2DNoFxReq pointXY = new Point2DNoFxReq(point.x + random.nextFloat(), point.y + random.nextFloat());
		
		switch (type) {
		case Unit.UNIT_ARCHER:
			unit = new Archer(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_BUILDER:
			unit = new Builder(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_MAGE:
			unit = new Mage(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_TREBUCHET:
			unit = new Trebuchet(pointXY, "Trebuchet", level, controlable, abilities);
			break;
		case Unit.UNIT_WARRIOR:
			unit = new Warrior(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_BATTERING_RAM:
			unit = new BatteringRam(pointXY, "BatteringRam", level, controlable, abilities);
			break;
		case Unit.UNIT_CAVALRY:
			unit = new Cavalry(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_CAVALRY_ARCHER:
			unit = new CavalryArcher(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_DRAGON:
			unit = new Dragon(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_HERO:
			unit = new Hero(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_KNIGHT:
			unit = new Knight(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_MANGONEL:
			unit = new Mangonel(pointXY, "Mangonel", level, controlable, abilities);
			break;
		case Unit.UNIT_PRIEST:
			unit = new Priest(pointXY, "", level, controlable, abilities);
			break;
		default:
			unit = null;
			break;
		}

		if (namesXML == null) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			try {
				InputStream names = Boot.class.getResourceAsStream("/resources/names.xml");

				namesXML = builder.parse(names);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		if(unit.getName().equals("Trebuchet") == false) {
			Element rootElement = namesXML.getDocumentElement();
			unit.setName(getString("name", rootElement));
		}
		
		return unit;

	}

	private String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		int randomInt = random.nextInt(list.getLength());
		if (list != null && list.getLength() > 0) {
			return list.item(randomInt).getTextContent();
		}

		return null;
	}

}
