package core;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
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

	public static final String GAME_PATH = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EST-SCHULPROJEKT";
	public static final String GAME_PATH_SAVES = GAME_PATH + File.separator + "saves";
	public static final String GAME_PATH_SETTINGS = GAME_PATH + File.separator + "settings.xml";
	private static MainJFrame mainJFrame;
	private static ControlInput controlInput;
	
	public Core() {
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
				Element fullscreen = settingsDoc.createElement("fullscreen");
				fullscreen.setTextContent("false");
				settingsRoot.appendChild(fullscreen);
				
				Element defaultWidth = settingsDoc.createElement("defaultWidth");
				defaultWidth.setTextContent("800");
				settingsRoot.appendChild(defaultWidth);
				
				Element defaultHeight = settingsDoc.createElement("defaultHeight");
				defaultHeight.setTextContent("600");
				settingsRoot.appendChild(defaultHeight);

				Element askSaveDelete = settingsDoc.createElement("askSaveDelete");
				askSaveDelete.setTextContent("true");
				settingsRoot.appendChild(askSaveDelete);
				
				// Log
				Element showLog = settingsDoc.createElement("showLog");
				showLog.setTextContent("true");
				settingsRoot.appendChild(showLog);
				
				Element enableLog = settingsDoc.createElement("enableLog");
				enableLog.setTextContent("false");
				settingsRoot.appendChild(enableLog);

				// Other
				Element RTX = settingsDoc.createElement("RTX");
				RTX.setTextContent("false");
				settingsRoot.appendChild(RTX);
				
				Element dev = settingsDoc.createElement("dev");
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
		Core.controlInput = new ControlInput();
		Core.mainJFrame = new MainJFrame();
    	
		
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//		System.out.println(threadSet.toString());
	}

	public static MainJFrame getMainJFrame() {
		return mainJFrame;
	}

	public static ControlInput getControlInput() {
		return controlInput;
	}

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
		System.out.println("Load["+settingName+"="+settingString+"]");
		return settingString;
	}
	
	public static void saveSetting(String settingName, String state) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document settingsDoc = dBuilder.parse(GAME_PATH_SETTINGS);
			settingsDoc.getDocumentElement().normalize();
			
			Node node = settingsDoc.getElementsByTagName(settingName).item(0);
			node.setTextContent(state);
			System.out.println("save["+settingName+"="+state+"]");
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
}