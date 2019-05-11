package core;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import frame.MainJFrame;

/**
 * This holds all the main components that make up the game
 * 
 * @author Luca Kleck
 */
public class Core {
	public static final Logger GAME_LOGGER = Logger.getLogger("gameLogger");
	
	public static final String GAME_PATH = System.getProperty("user.home") + File.separator + "Games" + File.separator + "TBTD";
	public static final String GAME_PATH_SAVES = GAME_PATH + File.separator + "saves";
	public static final String GAME_PATH_SETTINGS = GAME_PATH + File.separator + "settings.xml";
	public static final String GAME_PATH_NAMES = GAME_PATH + File.separator + "names.xml";
	
	// Setting strings
	public static final String SETTING_FULLSCREEN = "fullscreen";
	public static final String SETTING_DEFAULT_WIDTH = "defaultWidth";
	public static final String SETTING_DEFAULT_HEIGHT = "defaultHeight";
	public static final String SETTING_FPS_LIMIT = "fpsLimit";
	public static final String SETTING_ASK_SAVE_DELETE = "askSaveDelete";
	public static final String SETTING_SHOW_LOG = "showLog";
	public static final String SETTING_ENABLE_LOG = "enableLog";
	public static final String SETTING_RTX = "RTX";
	public static final String SETTING_DEV = "dev";
	
	private static MainJFrame mainJFrame;
	private static ControlInput controlInput;
	
	public Core() {
		setCustomUI();
		File game_directory = new File(GAME_PATH);

    	if (game_directory.exists()) {
    	} else if (game_directory.mkdirs()) {
    	} else {
    		JOptionPane.showMessageDialog(null, "Game could not create folders (something blocked it!)", "BOOT FAILED", JOptionPane.ERROR_MESSAGE);
    		System.exit(0);
    	}
		File settingsXML = new File(GAME_PATH_SETTINGS);
		
		if(!settingsXML.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				Document settingsDoc = dbFactory.newDocumentBuilder().newDocument();
				
				Element settingsRoot = settingsDoc.createElement("settings");
				settingsDoc.appendChild(settingsRoot);
				
				// General
				Element fullscreen = settingsDoc.createElement(SETTING_FULLSCREEN);
				fullscreen.setTextContent("false");
				settingsRoot.appendChild(fullscreen);
				
				Element defaultWidth = settingsDoc.createElement(SETTING_DEFAULT_WIDTH);
				defaultWidth.setTextContent(""+((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2));
				settingsRoot.appendChild(defaultWidth);
				
				Element defaultHeight = settingsDoc.createElement(SETTING_DEFAULT_HEIGHT);
				defaultHeight.setTextContent(""+((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2));
				settingsRoot.appendChild(defaultHeight);

				Element fpsLimit = settingsDoc.createElement(SETTING_FPS_LIMIT);
				fpsLimit.setTextContent("30");
				settingsRoot.appendChild(fpsLimit);

				Element askSaveDelete = settingsDoc.createElement(SETTING_ASK_SAVE_DELETE);
				askSaveDelete.setTextContent("true");
				settingsRoot.appendChild(askSaveDelete);
				
				// Log
				Element showLog = settingsDoc.createElement(SETTING_SHOW_LOG);
				showLog.setTextContent("true");
				settingsRoot.appendChild(showLog);
				
				Element enableLog = settingsDoc.createElement(SETTING_ENABLE_LOG);
				enableLog.setTextContent("false");
				settingsRoot.appendChild(enableLog);

				// Other
				Element RTX = settingsDoc.createElement(SETTING_RTX);
				RTX.setTextContent("false");
				settingsRoot.appendChild(RTX);
				
				Element dev = settingsDoc.createElement(SETTING_DEV);
				dev.setTextContent("false");
				settingsRoot.appendChild(dev);
				
				settingsDoc.normalizeDocument();
				
			    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    
			    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
			    
			    DOMSource domSource = new DOMSource(settingsDoc);
			    
			    StreamResult streamResult = new StreamResult(new File(GAME_PATH_SETTINGS));
				
			    transformer.transform(domSource, streamResult);
			} catch (ParserConfigurationException | TransformerException e) {
				e.printStackTrace();
			}
		}
		try {
			FileHandler logFileHandler = new FileHandler(GAME_PATH+File.separator+"gameLog.log");
			GAME_LOGGER.addHandler(logFileHandler);
			GAME_LOGGER.setLevel(Level.ALL);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Core.controlInput = new ControlInput();
		Core.mainJFrame = new MainJFrame();
	}

	public static MainJFrame getMainJFrame() {
		return mainJFrame;
	}

	public static ControlInput getControlInput() {
		return controlInput;
	}

	/**
	 * @author Luca Kleck
	 * @param settingName - setting name of the setting you want to get information of
	 * @return current state of setting
	 */
	public static String getSetting(String settingName) {
		String settingString = null;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document settingsDoc = dBuilder.parse(GAME_PATH_SETTINGS);
			settingsDoc.getDocumentElement().normalize();
			Node node = settingsDoc.getElementsByTagName(settingName).item(0);
			try {
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					settingString = e.getTextContent();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
		GAME_LOGGER.log(Level.CONFIG, "Load["+settingName+"="+settingString+"]");
		return settingString;
	}
	
	/**
	 * @author Luca Kleck
	 * @param settingName - setting you want to reach
	 * @param state - state you want to put it into
	 */
	public static void saveSetting(String settingName, String state) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document settingsDoc = dBuilder.parse(GAME_PATH_SETTINGS);
			settingsDoc.getDocumentElement().normalize();
			
			Node node = settingsDoc.getElementsByTagName(settingName).item(0);
			node.setTextContent(state);
			GAME_LOGGER.log(Level.CONFIG, "save["+settingName+"="+state+"]");
			settingsDoc.normalizeDocument();
			
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
		    
		    DOMSource domSource = new DOMSource(settingsDoc);
		    
		    StreamResult streamResult = new StreamResult(new File(GAME_PATH_SETTINGS));
			
		    transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
			e1.printStackTrace();
		} catch (NullPointerException nl) {
			
		}
	}
	/**
	 * @author Luca Kleck
	 */
	private void setCustomUI() {
		Color menuBgColor = new Color(80,80,80);
		Color transparent = new Color(0,0,0,0);
		// Button
		UIManager.getDefaults().put("Button.border", BorderFactory.createEmptyBorder(3, 3, 3, 3));
		UIManager.getDefaults().put("Button.background", transparent);
		UIManager.getDefaults().put("Button.foreground", transparent);
		
		UIManager.getDefaults().put("Button.select", transparent);
		UIManager.getDefaults().put("Button.focus", transparent);
		UIManager.getDefaults().put("Button.select", transparent);
		
		UIManager.getDefaults().put("Button.light", transparent);
		UIManager.getDefaults().put("Button.highlight", transparent);
		
		UIManager.getDefaults().put("Button.shadow", transparent);
		UIManager.getDefaults().put("Button.darkShadow", transparent);
		
		// Menu Item
		UIManager.getDefaults().put("MenuItem.background", menuBgColor);
		UIManager.getDefaults().put("MenuItem.foreground", Color.WHITE);
		UIManager.getDefaults().put("MenuItem.selectionBackground", Color.LIGHT_GRAY);
		UIManager.getDefaults().put("MenuItem.selectionForeground", new Color(60,40,40));
		UIManager.getDefaults().put("MenuItem.acceleratorForeground", Color.LIGHT_GRAY);
		UIManager.getDefaults().put("MenuItem.border", BorderFactory.createLineBorder(Color.DARK_GRAY, 2, false));
		UIManager.getDefaults().put("MenuItem.margin", new Insets(5, 5, 5, 5));
		
		// Menu Check Box
		UIManager.getDefaults().put("CheckBoxMenuItem.background", menuBgColor);
		UIManager.getDefaults().put("CheckBoxMenuItem.foreground", Color.WHITE);
		UIManager.getDefaults().put("CheckBoxMenuItem.selectionBackground", Color.LIGHT_GRAY);
		UIManager.getDefaults().put("CheckBoxMenuItem.selectionForeground", new Color(60,40,40));
		UIManager.getDefaults().put("CheckBoxMenuItem.acceleratorForeground", Color.LIGHT_GRAY);
		UIManager.getDefaults().put("CheckBoxMenuItem.border", BorderFactory.createLineBorder(Color.DARK_GRAY, 2, false));
		UIManager.getDefaults().put("CheckBoxMenuItem.margin", new Insets(5, 5, 5, 5));
		
	}
}