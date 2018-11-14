package frame.gamePanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static SelectionPanel selectionPane;
	private static InteractionPanel selfInteractionPanel;

	public InteractionPanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		selfInteractionPanel = this;
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[100%,fill]", "[100%,fill]"));
		
	}

	public static void setSelectionPane(SelectionPanel selectionPane) {
		selectionPaneCheck(selectionPane);
		InteractionPanel.selectionPane = selectionPane;
		selfInteractionPanel.validate();
		selfInteractionPanel.repaint();
	}

	public static void selectionPaneCheck(SelectionPanel se) {
		if(se != null) {
			selfInteractionPanel.removeAll();
			System.gc();
			selfInteractionPanel.add(se, "cell 0 0");
		} else if( se == null && selectionPane != null) {
			selfInteractionPanel.remove(selectionPane);
		}
	}

	public static InteractionPanel getInteractionPanel() {
		return selfInteractionPanel;
	}
	
	public static SelectionPanel getSelectionPane() {
		return selectionPane;
	}
}
