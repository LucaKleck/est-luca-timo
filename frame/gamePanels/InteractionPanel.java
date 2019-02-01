package frame.gamePanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static InteractionPanel selfInteractionPanel;
	private static JScrollPane currentPanel;

	public InteractionPanel() {
		setBorder(null);
		selfInteractionPanel = this;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[100%,fill]", "[100%,fill]"));
	}

	public static void setCurrentPanel(JScrollPane currentPanel) {
		if (InteractionPanel.currentPanel != null) {
			selfInteractionPanel.removeAll();
			InteractionPanel.currentPanel = null;
			System.gc();
		}
		InteractionPanel.currentPanel = currentPanel;
		if (currentPanel != null) {
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
