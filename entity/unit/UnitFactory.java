package entity.unit;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
			unit = new Trebuchet(pointXY, "", level, controlable, abilities);
			break;
		case Unit.UNIT_WARRIOR:
			unit = new Warrior(pointXY, "", level, controlable, abilities);
			break;
		default:
			unit = new Warrior(pointXY, "", level, controlable, abilities);
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
				URL names = Boot.class.getResource("/resources/names.xml");

				try {
					namesXML = builder.parse(new File(names.toURI()));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		Element rootElement = namesXML.getDocumentElement();

		unit.setName(getString("name", rootElement));

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
