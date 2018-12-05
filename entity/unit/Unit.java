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

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Move;
import core.Boot;
import entity.Entity;

public class Unit extends Entity {

	private static Document namesXML = null;
	
	private int baseDamage;
	private int movementRange;
	private Ability move = new Move();

	public Unit(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {

		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		
		if(namesXML == null) {
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
		
		this.setName(getString("name", rootElement));
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