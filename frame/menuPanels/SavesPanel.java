package frame.menuPanels;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import core.start;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class SavesPanel extends JScrollPane {
	private static final long serialVersionUID = 6973604110107187761L;
	private ArrayList<JPanel> saveList= new ArrayList<>();
	
	public SavesPanel(JPanel container) {
		setBackground(Color.DARK_GRAY);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getVerticalScrollBar().setUnitIncrement(16);
		setDoubleBuffered(true);
		
		JPanel viewport = new JPanel();
		viewport.setBackground(Color.DARK_GRAY);
		viewport.setLayout(new MigLayout("", "[fill]", "[fill]"));
		setViewportView(viewport);

		File[] saves = saveGameSearcher(start.GAME_PATH_SAVES);
		String columns = "";
		for(int i = 0; i < saves.length; i++) {
			saveList.add(new SavesPanelElement(saves[i], container));
			columns += "[fill]";
		}
		viewport.setLayout(new MigLayout("", "[fill]", columns));

		for (int i = 0; i < saveList.size(); i++) {
			viewport.add(saveList.get(i), ("cell 0 " + i + ", grow"));
		}

		
	}
	
	private File[] saveGameSearcher(String savesFolderPath){
        File savesFolder = new File(savesFolderPath);

        return savesFolder.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".xml"); }
        } );
	}
}
