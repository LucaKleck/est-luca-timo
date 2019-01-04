package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import core.Boot;
import core.GameInfo;
import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JScrollPane {
	private static final long serialVersionUID = 125L;
	private JLabel lblHealth = new JLabel(style+"Health: ");
	private JLabel lblName = new JLabel(style+"Name: ");
	private JProgressBar healthStatus;
	private static BufferedImage background;
	private static final String style = MainJFrame.makeCssStyle("color: black;");

	public InfoPanel() {
		if(background == null) {
			try {
				background = ImageIO.read( Boot.class.getResource("/resources/selectionPanelBackground.png") );
			} catch (IOException e) {
			}
		}
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		JPanel viewportPanel = new JPanel();
		setViewportView(viewportPanel);
		viewportPanel.setBackground(new Color(0,0,0,0));
		viewportPanel.setLayout(new MigLayout("", "[]", "[][][]"));

		viewportPanel.add(lblName, "cell 0 0");

		viewportPanel.add(lblHealth, "cell 0 1");

		JLabel lblCurrentlySelected = new JLabel(MainJFrame.htmlStyleDefault+"Currently Selected");
		setColumnHeaderView(lblCurrentlySelected);
		
		healthStatus = new JProgressBar();
		healthStatus.setMaximum(0);
		healthStatus.setMinimum(0);
		healthStatus.setValue(0);
		healthStatus.setStringPainted(true);
		healthStatus.setString("0/0");
		viewportPanel.add(healthStatus, "cell 0 2");
	}

	public void update() {
		if(GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
			lblName.setText(style+"Name: " + GameInfo.getObjectMap().getSelected().getSelectedEntity().getName());
			lblHealth.setText(style+"Health:");
			healthStatus.setMaximum(GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth());
			healthStatus.setMinimum(0);
			healthStatus.setValue(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth());
			healthStatus.setStringPainted(true);
			healthStatus.setString(GameInfo.getObjectMap().getSelected().getSelectedEntity().getCurrentHealth()+ "/" + GameInfo.getObjectMap().getSelected().getSelectedEntity().getMaxHealth() );
		} else {
			lblName.setText(style+"Name: ");
			lblHealth.setText(style+"Health:");
			healthStatus.setMaximum(0);
			healthStatus.setMinimum(0);
			healthStatus.setValue(0);
			healthStatus.setStringPainted(true);
			healthStatus.setString("0/0");
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
}
