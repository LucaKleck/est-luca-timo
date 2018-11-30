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

import core.PlayerStats.PlayerResources;
import entity.Entity;
import entity.building.Building;
import entity.unit.Unit;
import entity.unit.Warrior;
import frame.gamePanels.LogPanel;
import map.MapTile;
import map.MapTileResources;
import map.ObjectMap;


public class XMLSaveAndLoad {
	
	private static final String X_POS = "xPos";
	private static final String Y_POS = "yPos";
	private static final String TYPE = "type";
	private static final String NAME = "name";
	private static final String GOLD= "gold";
	private static final String FOOD = "food";
	private static final String WOOD = "wood";
	private static final String STONE = "stone";
	private static final String METAL = "metal";
	private static final String MANA_STONE = "manaStone";
	private static final String IS_ROAD = "isRoad";
	private static final String ENTITY = "entity";
	private static final String MAX_HEALTH = "maxHealth";
	private static final String CURRENT_HEALTH = "currentHealth";
	private static final String BASE_DAMAGE = "baseDamage";
	private static final String MOVEMENT_RANGE = "movementRange";
	private static final String MAP_SIZE = "mapSize";
	private static final String CLICKS = "clicks";
	private static final String UNITS_KILLED = "unitsKilled";
	private static final String BUILDINGS_DESTROYED = "buildingsDestroyed";
	private static final String DAMAGE_DEALT = "damageDealt";
	private static final String BUILDINGS_BUILT = "buildingsBuilt";
	private static final String UNITS_CREATED = "unitsCreated";
	private static final String TIME_PLAYED_MINS = "timePlayedMins";
	private static final String LEVEL = "level";
	private static final String CONTROLABLE = "controlable";
	
	private static String xmlFilePath;
	
	public XMLSaveAndLoad(String saveName) {
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
			new XMLSaveAndLoad(save.getName().substring(0, save.getName().length()-4));
			dBuilder = dbFactory.newDocumentBuilder();
			Document saveDoc = dBuilder.parse(save);
			saveDoc.getDocumentElement().normalize();
			
			new GameInfo(new ObjectMap(loadMap(saveDoc),loadEntityMap(saveDoc)), loadPlayerStats(saveDoc));
			
			
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
        
        int mapSize = Integer.parseInt(saveDoc.getElementsByTagName("map").item(0).getAttributes().getNamedItem(MAP_SIZE).getNodeValue());
        
        MapTile[][] map = new MapTile[mapSize][mapSize];
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               int xPos = Integer.parseInt(eElement.getElementsByTagName(X_POS).item(0).getTextContent());
               int yPos = Integer.parseInt(eElement.getElementsByTagName(Y_POS).item(0).getTextContent());
               int type = Integer.parseInt(eElement.getElementsByTagName(TYPE).item(0).getTextContent());
               String name = eElement.getElementsByTagName(NAME).item(0).getTextContent();
               int gold = Integer.parseInt(eElement.getElementsByTagName(GOLD).item(0).getTextContent());
               int food = Integer.parseInt(eElement.getElementsByTagName(FOOD).item(0).getTextContent());
               int wood = Integer.parseInt(eElement.getElementsByTagName(WOOD).item(0).getTextContent());
               int stone = Integer.parseInt(eElement.getElementsByTagName(STONE).item(0).getTextContent());
               int metal = Integer.parseInt(eElement.getElementsByTagName(METAL).item(0).getTextContent());
               int manaStone = Integer.parseInt(eElement.getElementsByTagName(MANA_STONE).item(0).getTextContent());
               boolean isRoad = new Boolean(eElement.getElementsByTagName(IS_ROAD).item(0).getTextContent());
               map[xPos][yPos] = new MapTile(xPos, yPos, type, name, new MapTileResources(gold, food, wood, stone, metal, manaStone), isRoad);
            }
        }
		return map;
	}
	
	private static ArrayList<Entity> loadEntityMap(Document doc) {
		NodeList nList = doc.getElementsByTagName(ENTITY);
        
        ArrayList<Entity> entityMap = new ArrayList<>();
		
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               Entity e;
               String type = eElement.getAttribute(TYPE);
               
               int xPos = Integer.parseInt(eElement.getElementsByTagName(X_POS).item(0).getTextContent());
               int yPos = Integer.parseInt(eElement.getElementsByTagName(Y_POS).item(0).getTextContent());
               String name = eElement.getElementsByTagName(NAME).item(0).getTextContent();
               int currentHealth = Integer.parseInt(eElement.getElementsByTagName(CURRENT_HEALTH).item(0).getTextContent());
               int maxHealth = Integer.parseInt(eElement.getElementsByTagName(MAX_HEALTH).item(0).getTextContent());
               int level = Integer.parseInt(eElement.getElementsByTagName(LEVEL).item(0).getTextContent());
               boolean controlable = new Boolean(eElement.getElementsByTagName(CONTROLABLE).item(0).getTextContent());
               
               e = new Entity(xPos, yPos, name, maxHealth, currentHealth, level, controlable, null);
            		   
               if(type.matches("Unit") || type.matches("Warrior")) {
            	   int baseDamage = Integer.parseInt(eElement.getElementsByTagName(BASE_DAMAGE).item(0).getTextContent());
            	   int movementRange = Integer.parseInt(eElement.getElementsByTagName(MOVEMENT_RANGE).item(0).getTextContent());
            	   
            	   e = new Unit(xPos, yPos, name, maxHealth, currentHealth, level, controlable, baseDamage, movementRange, null);
            	   
            	   if(type.matches("Warrior")) {
            		   e = new Warrior(xPos, yPos, name, currentHealth, level, controlable);
            	   }
               }
               if(type.matches("Building")) {
            	   e = new Building(xPos, yPos, name, maxHealth, currentHealth, level, controlable, null);
               }
               entityMap.add(e);
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
		    
    		// append player stats
    		saveRoot.appendChild(savePlayerStats(saveDoc));
    		
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
	
	private static PlayerStats loadPlayerStats(Document saveDoc) {
		PlayerStats ps = new PlayerStats();
	
		Element playerStatsElement = (Element) saveDoc.getElementsByTagName("PlayerStats").item(0);
		
		int clicks = Integer.parseInt(playerStatsElement.getElementsByTagName(CLICKS).item(0).getTextContent());
		int unitsKilled = Integer.parseInt(playerStatsElement.getElementsByTagName(UNITS_KILLED).item(0).getTextContent());
		int buildingsDestroyed = Integer.parseInt(playerStatsElement.getElementsByTagName(BUILDINGS_DESTROYED).item(0).getTextContent());
		int damageDealt = Integer.parseInt(playerStatsElement.getElementsByTagName(DAMAGE_DEALT).item(0).getTextContent());
		int unitsCreated = Integer.parseInt(playerStatsElement.getElementsByTagName(UNITS_CREATED).item(0).getTextContent());
		int buildingsBuilt = Integer.parseInt(playerStatsElement.getElementsByTagName(BUILDINGS_BUILT).item(0).getTextContent());
		int timePlayedMins = Integer.parseInt(playerStatsElement.getElementsByTagName(TIME_PLAYED_MINS).item(0).getTextContent());;
		
		Element playerResourcesElement = (Element) saveDoc.getElementsByTagName("playerResources").item(0);
		int gold = Integer.parseInt(playerResourcesElement.getElementsByTagName(GOLD).item(0).getTextContent());
		int food = Integer.parseInt(playerResourcesElement.getElementsByTagName(FOOD).item(0).getTextContent());
		int wood = Integer.parseInt(playerResourcesElement.getElementsByTagName(WOOD).item(0).getTextContent());
		int stone = Integer.parseInt(playerResourcesElement.getElementsByTagName(STONE).item(0).getTextContent());
		int metal = Integer.parseInt(playerResourcesElement.getElementsByTagName(METAL).item(0).getTextContent());
		int manaStone = Integer.parseInt(playerResourcesElement.getElementsByTagName(MANA_STONE).item(0).getTextContent());
				
		PlayerResources playerResources = ps.new PlayerResources(gold, food, wood, stone, metal, manaStone);
						
		ps = new PlayerStats(clicks, unitsKilled, buildingsDestroyed, damageDealt, unitsCreated, buildingsBuilt, timePlayedMins, playerResources);
		return ps;
	}
	
	private static Node savePlayerStats(Document saveDoc) {
		Element playerStatsElement = saveDoc.createElement("PlayerStats");
		
		Element clicks = saveDoc.createElement(CLICKS);
		clicks.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getClicks()));
		playerStatsElement.appendChild(clicks);
		
		Element unitsKilled = saveDoc.createElement(UNITS_KILLED);
		unitsKilled.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getUnitsKilled()));
		playerStatsElement.appendChild(unitsKilled);
		
		Element buildingsDestroyed = saveDoc.createElement(BUILDINGS_DESTROYED);
		buildingsDestroyed.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getBuildingsDestroyed()));
		playerStatsElement.appendChild(buildingsDestroyed);
		
		Element damageDealt = saveDoc.createElement(DAMAGE_DEALT);
		damageDealt.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getDamageDealt()));
		playerStatsElement.appendChild(damageDealt);
		
		Element unitsCreated = saveDoc.createElement(UNITS_CREATED);
		unitsCreated.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getUnitsCreated()));
		playerStatsElement.appendChild(unitsCreated);
		
		Element buildingsBuilt = saveDoc.createElement(BUILDINGS_BUILT);
		buildingsBuilt.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getBuildingsBuilt()));
		playerStatsElement.appendChild(buildingsBuilt);
		
		Element timePlayedMins = saveDoc.createElement(TIME_PLAYED_MINS);
		timePlayedMins.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getTimePlayedMins()));
		playerStatsElement.appendChild(timePlayedMins);
		
	
		playerStatsElement.appendChild(playerResources(saveDoc));
		
		
		
		return playerStatsElement;
	}
	
	private static Node playerResources(Document saveDoc) {
		Element playerResources = saveDoc.createElement("playerResources");
		
		Element gold = saveDoc.createElement(GOLD);
	    gold.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getGold()));
	    playerResources.appendChild(gold);
	    
	    Element food = saveDoc.createElement(FOOD);
	    food.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getFood() ));
	    playerResources.appendChild(food);
	    
	    Element wood = saveDoc.createElement(WOOD);
	    wood.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getWood()));
	    playerResources.appendChild(wood);
	    
	    Element stone = saveDoc.createElement(STONE);
	    stone.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getStone()));
	    playerResources.appendChild(stone);
	    
	    Element metal = saveDoc.createElement(METAL);
	    metal.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getMetal()));
	    playerResources.appendChild(metal);
	    
	    Element manaStone = saveDoc.createElement(MANA_STONE);
	    manaStone.appendChild(saveDoc.createTextNode(""+GameInfo.getPlayerStats().getPlayerResources().getManaStone()));
	    playerResources.appendChild(manaStone);
		
		return playerResources;
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
	    Attr mapSize = save.createAttribute(MAP_SIZE);
        mapSize.setValue(""+ObjectMap.getMap().length);
        map.setAttributeNode(mapSize);
        
	    for(int x = 0; x < ObjectMap.getMap().length; x++) {
	    	for(int y = 0; y < ObjectMap.getMap()[x].length; y++) {
			    
			    Element mapTile = save.createElement("mapTile");
			    map.appendChild(mapTile);
			    
			    Element xPos = save.createElement(X_POS);
			    xPos.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getXPos()) );
			    mapTile.appendChild(xPos);
			    
			    Element yPos = save.createElement(Y_POS);
			    yPos.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getYPos()) );
			    mapTile.appendChild(yPos);
			    
			    Element type = save.createElement(TYPE);
			    type.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getType()) );
			    mapTile.appendChild(type);
			    
			    Element name = save.createElement(NAME);
			    name.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getName()) );
			    mapTile.appendChild(name);
			    
			    Element mapTileResources = save.createElement("mapTileResources");

			    Element gold = save.createElement(GOLD);
			    gold.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getGoldPercent()));
			    mapTileResources.appendChild(gold);
			    
			    Element food = save.createElement(FOOD);
			    food.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getFoodPercent()));
			    mapTileResources.appendChild(food);
			    
			    Element wood = save.createElement(WOOD);
			    wood.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getWoodPercent()));
			    mapTileResources.appendChild(wood);
			    
			    Element stone = save.createElement(STONE);
			    stone.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getStonePercent()));
			    mapTileResources.appendChild(stone);
			    
			    Element metal = save.createElement(METAL);
			    metal.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getMetalPercent()));
			    mapTileResources.appendChild(metal);
			    
			    Element manaStone = save.createElement(MANA_STONE);
			    manaStone.appendChild(save.createTextNode(""+ObjectMap.getMap()[x][y].getMapTileResources().getManaStonePercent()));
			    mapTileResources.appendChild(manaStone);
			    
			    mapTile.appendChild(mapTileResources);
			    
			    Element isRoad = save.createElement(IS_ROAD);
			    isRoad.appendChild(save.createTextNode(new Boolean(ObjectMap.getMap()[x][y].isRoad()).toString() ) );
			    mapTile.appendChild(isRoad);
			    
	    	}
	    }
		return map;
	}
	
	private static Element saveEntityMap(Document save) {
		Element entityMap = save.createElement("entityMap");
        
	    for(int i = 0; i < ObjectMap.getEntityMap().size(); i++) {
	    			Element entity = save.createElement("entity");
	    			Attr className = save.createAttribute("type");
	    			if(ObjectMap.getEntityMap().get(i) != null) {
	    				className.setValue(ObjectMap.getEntityMap().get(i).getClass().getSimpleName());
	    				
	    				// things every entity has
	    				Element xPos = save.createElement(X_POS);
					    xPos.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getXPos()) );
					    entity.appendChild(xPos);
					    
					    Element yPos = save.createElement(Y_POS);
					    yPos.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getYPos()) );
					    entity.appendChild(yPos);
					    
					    Element name = save.createElement(NAME);
					    name.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getName()) );
					    entity.appendChild(name);
					    
					    Element maxHealth = save.createElement(MAX_HEALTH);
					    maxHealth.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getMaxHealth()) );
					    entity.appendChild(maxHealth);
					    
					    Element currentHealth = save.createElement(CURRENT_HEALTH);
					    currentHealth.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getCurrentHealth()) );
					    entity.appendChild(currentHealth);

					    Element level = save.createElement(LEVEL);
					    level.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getLevel()) );
					    entity.appendChild(level);

					    Element controlable = save.createElement(CONTROLABLE);
					    controlable.appendChild(save.createTextNode(""+ObjectMap.getEntityMap().get(i).getLevel()) );
					    entity.appendChild(controlable);
					    
					    // things every unit has
		    			if(ObjectMap.getEntityMap().get(i) instanceof Unit) {
		    				Element damage = save.createElement(BASE_DAMAGE);
		    				damage.appendChild(save.createTextNode(""+((Unit) ObjectMap.getEntityMap().get(i)).getBaseDamage() ) );
						    entity.appendChild(damage);
						    
						    Element movementRange = save.createElement(MOVEMENT_RANGE);
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
