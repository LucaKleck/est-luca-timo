package frame.gamePanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static InteractionPanel selfInteractionPanel;
	private static JScrollPane currentPanel;

	public InteractionPanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		selfInteractionPanel = this;
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[100%,fill]", "[100%,fill]"));
	}

	public static void setCurrentPanel(JScrollPane currentPanel) {
		if(InteractionPanel.currentPanel!=null) {
			selfInteractionPanel.removeAll();
			InteractionPanel.currentPanel = null;
			System.gc();
		}
		InteractionPanel.currentPanel = currentPanel;
		if(currentPanel!=null) {
			selfInteractionPanel.add(currentPanel, "cell 0 0");
		}
		selfInteractionPanel.validate();
		selfInteractionPanel.repaint();
	}
	
	public static InteractionPanel getInteractionPanel() {
		return selfInteractionPanel;
	}
	
	public static JScrollPane getCurrentPanel() {
		return currentPanel;
	}
}
