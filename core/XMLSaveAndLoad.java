package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;
import entity.unit.Warrior;
import frame.gamePanels.LogPanel;
import map.MapTile;
import map.MapTileResources;
import map.ObjectMap;


public class XMLSaveAndLoad {
	@SuppressWarnings("unused")
	private static String saveName;
	private static String xmlFilePath;
	
	public XMLSaveAndLoad(String saveName) {
		XMLSaveAndLoad.saveName = saveName;
//		System.out.println(saveName);
		xmlFilePath = Core.GAME_PATH_SAVES;
		File saves = new File(xmlFilePath);

    	if (saves.exists()) {

    	} else if (saves.mkdirs()) {
    		
    	} else {

    	}
    	xmlFilePath += File.separator + saveName+".xml";
	}
	public static void loadGame(File save) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document saveDoc = dBuilder.parse(save);
			saveDoc.getDocumentElement().normalize();

			new ObjectMap(loadMap(saveDoc),loadEntityMap(saveDoc));
			
			try {
				LogPanel.reset(saveDoc.getElementsByTagName("gameLog").item(0).getTextContent());
			} catch (NullPointerException nl) {
			}	
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private static MapTile[][] loadMap(Document saveDoc) {
		NodeList nList = saveDoc.getElementsByTagName("mapTile");
        
        int mapSize = Integer.parseInt(saveDoc.getElementsByTagName("map").item(0).getAttributes().getNamedItem("mapSize").getNodeValue());
        
        MapTile[][] map = new MapTile[mapSize][mapSize];
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               int xPos = Integer.parseInt(eElement.getElementsByTagName("xPos").item(0).getTextContent());
               int yPos = Integer.parseInt(eElement.getElementsByTagName("yPos").item(0).getTextContent());
               int type = Integer.parseInt(eElement.getElementsByTagName("type").item(0).getTextContent());
               String name = eElement.getElementsByTagName("name").item(0).getTextContent();
               int gold = Integer.parseInt(eElement.getElementsByTagName("gold").item(0).getTextContent());
               int food = Integer.parseInt(eElement.getElementsByTagName("food").item(0).getTextContent());
               int wood = Integer.parseInt(eElement.getElementsByTagName("wood").item(0).getTextContent());
               int stone = Integer.parseInt(eElement.getElementsByTagName("stone").item(0).getTextContent());
               int metal = Integer.parseInt(eElement.getElementsByTagName("metal").item(0).getTextContent());
               int manaStone = Integer.parseInt(eElement.getElementsByTagName("manaStone").item(0).getTextContent());
               boolean isRoad = new Boolean(eElement.getElementsByTagName("isRoad").item(0).getTextContent());
               map[xPos][yPos] = new MapTile(xPos, yPos, type, name, new MapTileResources(gold, food, wood, stone, metal, manaStone), isRoad);
            }
        }
		return map;
	}
	
	private static ArrayList<Entity> loadEntityMap(Document doc) {
		NodeList nList = doc.getElementsByTagName("entity");
        
        int xyEntityMapSize = Integer.parseInt(doc.getElementsByTagName("entityMap").item(0).getAttributes().getNamedItem("xyEntityMapSize").getNodeValue());
        int entityMapDepth = Integer.parseInt(doc.getElementsByTagName("entityMap").item(0).getAttributes().getNamedItem("entityMapDepth").getNodeValue());
        ArrayList<Entity> entityMap = new ArrayList<>();
		
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               Entity e;
               String type = eElement.getAttribute("type");
               
               int xPos = Integer.parseInt(eElement.getElementsByTagName("xPos").item(0).getTextContent());
               int yPos = Integer.parseInt(eElement.getElementsByTagName("yPos").item(0).getTextContent());
               int zPos = Integer.parseInt(eElement.getElementsByTagName("zPos").item(0).getTextContent());
               String name = eElement.getElementsByTagName("name").item(0).getTextContent();
               int health = Integer.parseInt(eElement.getElementsByTagName("health").item(0).getTextContent());
               e = new Entity(xPos, yPos, zPos, name, health);
               if(type.matches("Unit") || type.matches("Warrior")) {
            	   int damage = Integer.parseInt(eElement.getElementsByTagName("damage").item(0).getTextContent());
            	   int movementRange = Integer.parseInt(eElement.getElementsByTagName("movementRange").item(0).getTextContent());
            	   e = new Unit(xPos, yPos, zPos, name, health, damage, movementRange);
            	   if(type.matches("Warrior")) {
            		
            		   e = new Warrior(xPos, yPos, zPos, name, health, damage, movementRange);
            	   }
               }
               if(type.matches("Building")) {
            	   e = new Building(xPos, yPos, zPos, name, health);
               }
               for(int z = 0; z < entityMapDepth; z++) {
            	   if(entityMap.get(z).getXPos() == xPos && entityMap.get(z).getYPos() == yPos) {
            		   if(entityMap.get(z) == null) {
            			   entityMap.set(z, e);
            			   break;
            		   }
            	   }
               }
            }
            
        }
		return entityMap;
	}

	public static String saveGame() {
		String returnMessage = "Something isn't right, save system broken!";
		try {
    		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
    		Document saveDoc = documentBuilder.newDocument();
    		// create root for save
    		Element saveRoot = saveDoc.createElement("save");
    		
    		saveDoc.appendChild(saveRoot);
    		// append object map
    		saveRoot.appendChild(saveObjectMap(saveDoc));
		    
    		// append game log (do this last, there is a Game Saved that will be added to the Log, if something crashes after log, it'll say "Game Saved!" even though it crashed
    		saveRoot.appendChild(saveGameLog(saveDoc));
    		
		    // XML create
		    saveDoc.getDocumentElement().normalize();
		    saveDoc.normalizeDocument();
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
		    DOMSource domSource = new DOMSource(saveDoc);
		    
		    StreamResult streamResult = new StreamResult(new File(xmlFilePath));
		    
		    transformer.transform(domSource, streamResult);
		    returnMessage = "Game Saved!";
    	} catch (ParserConfigurationException pce) {
    		returnMessage = "Failed Saving: ParserConfigurationException";
    	} catch (TransformerException tfe) {
    		returnMessage = "Failed Saving: TransformerException";
    	} catch (NullPointerException nl) {
    		returnMessage = "Failed Saving: NullPointerException";
    	} catch (org.w3c.dom.DOMException domx) {
    		returnMessage = "Failed Saving: DOMException";
    	}
		return returnMessage;
	}
	
	private static Element saveObjectMap(Document save) {
		Element objectMapElement = save.createElement("ObjectMap");
	    
	    // Maps
		objectMapElement.appendChild(saveMapTileMap(save));
		objectMapElement.appendChild(saveEntityMap(save));
	    
		return objectMapElement;
	}

	private static Element saveMapTileMap(Document save) {
		Element map = save.createElement("map");
	    Attr mapSize = save.createAttribute("mapSize");
        mapSize.setValue(""+ObjectMap.getMap().length);
        map.setAttributeNode(mapSize);
        
	    for(int x = 0; x < ObjectMap.getMap().length; x++) {
	    	for(int y = 0; y < ObjectMap.getMap()[x].length; y++) {
			    
			    Element mapTile = save.createElement("mapTile");
			    map.appendChild(mapTile);
			    
			    Element xPos = save.createElement("xPos");
			    xPos.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getXPos()) );
			    mapTile.appendChild(xPos);
			    
			    Element yPos = save.createElement("yPos");
			    yPos.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getYPos()) );
			    mapTile.appendChild(yPos);
			    
			    Element type = save.createElement("type");
			    type.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getType()) );
			    mapTile.appendChild(type);
			    
			    Element name = save.createElement("name");
			    name.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getName()) );
			    mapTile.appendChild(name);
			    
			    Element mapTileResources = save.createElement("mapTileResources");

			    Element gold = save.createElement("gold");
			    gold.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getGoldPercent()));
			    mapTileResources.appendChild(gold);
			    
			    Element food = save.createElement("food");
			    food.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getFoodPercent()));
			    mapTileResources.appendChild(food);
			    
			    Element wood = save.createElement("wood");
			    wood.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getWoodPercent()));
			    mapTileResources.appendChild(wood);
			    
			    Element stone = save.createElement("stone");
			    stone.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getStonePercent()));
			    mapTileResources.appendChild(stone);
			    
			    Element metal = save.createElement("metal");
			    metal.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getMetalPercent()));
			    mapTileResources.appendChild(metal);
			    
			    Element manaStone = save.createElement("manaStone");
			    manaStone.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getManaStonePercent()));
			    mapTileResources.appendChild(manaStone);
			    
			    mapTile.appendChild(mapTileResources);
			    
			    Element isRoad = save.createElement("isRoad");
			    isRoad.appendChild(save.createTextNode(new Boolean(ObjectMap.getMap()[x][y].isRoad()).toString() ) );
			    mapTile.appendChild(isRoad);
			    
	    	}
	    }
		return map;
	}
	
	private static Element saveEntityMap(Document save) {
		Element entityMap = save.createElement("entityMap");
	    Attr xyEntityMapSize = save.createAttribute("xyEntityMapSize");
	    xyEntityMapSize.setValue(""+ObjectMap.getEntityMap().size());
        entityMap.setAttributeNode(xyEntityMapSize);
        
	    for(int i = 0; i < ObjectMap.getEntityMap().size(); i++) {
	    			Element entity = save.createElement("entity");
	    			Attr className = save.createAttribute("type");
	    			if(ObjectMap.getEntityMap().get(i) != null) {
	    				className.setValue(ObjectMap.getEntityMap().get(i).getClass().getSimpleName());
	    				
	    				// things every entity has
	    				Element xPos = save.createElement("xPos");
					    xPos.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getXPos()) );
					    entity.appendChild(xPos);
					    
					    Element yPos = save.createElement("yPos");
					    yPos.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getYPos()) );
					    entity.appendChild(yPos);
					    
					    Element name = save.createElement("name");
					    name.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getName()) );
					    entity.appendChild(name);
					    
					    Element health = save.createElement("health");
					    health.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getCurrentHealth()) );
					    entity.appendChild(health);
					    
					    // things every unit has
		    			if(ObjectMap.getEntityMap().get(i) instanceof Unit) {
		    				Element damage = save.createElement("damage");
		    				damage.appendChild(save.createTextNode(""+((Unit) ObjectMap.getEntityMap().get(i)).getDamage() ) );
						    entity.appendChild(damage);
						    
						    Element movementRange = save.createElement("movementRange");
						    movementRange.appendChild(save.createTextNode(""+((Unit) ObjectMap.getEntityMap().get(i)).getMovementRange() ) );
						    entity.appendChild(movementRange);
						    
						    // things every warrior has
						    if(ObjectMap.getEntityMap().get(i) instanceof Warrior) {
						    	
						    }
						    
		    			}
		    			// things every building has
		    			if(ObjectMap.getEntityMap().get(i) instanceof Building) {
		    				
		    			}
		    			
	    			} else {
	    				className.setValue("null");
	    			}
	    			entity.setAttributeNode(className);
	    			if(className.getValue() != "null") {
	    				entityMap.appendChild(entity);
	    	}
	    }
		return entityMap;
	}
	
	private static Element saveGameLog(Document save) {
		Element gameLog = save.createElement("gameLog");
		gameLog.appendChild(save.createTextNode(LogPanel.getLog().getText()+System.lineSeparator()+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+" - Game Saved!"));
		return gameLog;
	}
	
	public static void SaveOptions() {
		
	}
}
