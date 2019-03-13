package frame.gamePanels;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static InteractionPanel selfInteractionPanel;
	private static Component currentPanel;

	public InteractionPanel() {
		setBorder(null);
		selfInteractionPanel = this;
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[100%,fill]", "[100%,fill]"));
	}

	public static void setCurrentPanel(Component currentPanel) {
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

	public static Component getCurrentPanel() {
		return currentPanel;
	}
}
