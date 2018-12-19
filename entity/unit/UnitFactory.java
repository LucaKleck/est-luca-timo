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
import core.Boot;

public class UnitFactory extends Unit {

	private static Document namesXML = null;
	
	//All Requirements 
	public UnitFactory(Point2D pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, baseDamage, movementRange, abilities);
	}
	
	//No Requirement
	public UnitFactory() {
		super(new Point2D(0, 0), "RandomName", 5, 3, 1, true, 2, 3, new ArrayList<Ability>());
	}
	
	//Just Point
	public UnitFactory(Point2D pointXY) {
		super(pointXY, "RandomName", 5, 3, 1, true, 2, 3, new ArrayList<Ability>());
	}
	
	//No Name
	public UnitFactory(Point2D pointXY, int maxHealth, int currentHealth, int level, boolean controlable,  int baseDamage,  int movementRange, ArrayList<Ability> abilities) {
		
		super(pointXY, "", maxHealth, currentHealth, level, controlable, baseDamage, movementRange, abilities);
		
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
		
		super.setName(getString("name", rootElement));
		
	}
	
	private String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        int random = new Random().nextInt(list.getLength());
        if (list != null && list.getLength() > 0) {
            return list.item(random).getTextContent();
        }

        return null;
    }


}
