package frame.gamePanels;

import java.util.ArrayList;

import javax.swing.JScrollPane;

import entity.Entity;
import map.ObjectMap;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollBar;

public class SelectionPanel extends JScrollPane {
	private static final long serialVersionUID = 126L;
	private ArrayList<Entity> selectedEntityList = new ArrayList<>();
	private ArrayList<SelectionPaneElement> selectedEntityElementList = new ArrayList<>();
	
	public SelectionPanel(int x, int y) {
		
		JPanel viewportPanel = new JPanel();
		setViewportView(viewportPanel);
		viewportPanel.setLayout(new MigLayout("", "[]", "[]"));
		for(int i = 0; i < ObjectMap.getEntityMap()[x][y].length; i++) {
			if(ObjectMap.getEntityMap()[x][y][i] != null) {
				selectedEntityList.add(ObjectMap.getEntityMap()[x][y][i]);
			}
		}
		for(int i = 0; i < selectedEntityList.size(); i++) {
			selectedEntityElementList.add(createEntityPane(selectedEntityList.get(i)));
		}
		String layoutDepth = "";
		for(int i = 0; i < selectedEntityElementList.size(); i++) {
			layoutDepth+="[]";
		}
		viewportPanel.setLayout(new MigLayout("", "[]", layoutDepth));
		
		JScrollBar scrollBar = new JScrollBar();
		setRowHeaderView(scrollBar);
		for(int i = 0; i < selectedEntityElementList.size(); i++) {
			viewportPanel.add(selectedEntityElementList.get(i));
		}
	}
	
	private SelectionPaneElement createEntityPane(Entity entity) {
		SelectionPaneElement e = new SelectionPaneElement(entity);
		return e;
	}

	public ArrayList<Entity> getSelectedEntityList() {
		return selectedEntityList;
	}
	
	public String toString() {
		return selectedEntityList.toString();
	}
	
	
}
