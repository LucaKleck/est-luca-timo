package entity.unit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Move;
import core.Core;
import entity.Entity;

public class Unit extends Entity {

	private int baseDamage;
	private int movementRange;
	private Ability move = new Move();

	public Unit(Point2D pointXY, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {

		super(pointXY, maxHealth, currentHealth, level, controlable, abilities);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(new File(Core.GAME_PATH_NAMES));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element rootElement = document.getDocumentElement();
		String name = getString("name", rootElement);
		
		this.setName(name);
		abilities.add(0, move);
		this.baseDamage = baseDamage;
		this.movementRange = movementRange;
	}
	
	private String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        int random = new Random().nextInt(list.getLength());
        if (list != null && list.getLength() > 0) {
            return list.item(random).getTextContent();
        }

        return null;
    }
	
	public int getMovementRange() {
		return movementRange;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}

	@Override
	public String toString() {
		return "Unit [ id="+super.getId()+", name=" + getName() + ", damage=" + baseDamage + ", health=" + getMaxHealth() + ", movementRange=" + movementRange + "]";
	}

	public Move getMove() {
		return (Move) move;
	}

}