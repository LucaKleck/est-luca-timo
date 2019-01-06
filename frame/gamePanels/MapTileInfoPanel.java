package frame.gamePanels;

import javax.swing.JPanel;

import core.Boot;
import frame.MainJFrame;
import map.MapTile;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapTileInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MapTile m;
	private BufferedImage elementBackground;
	private String cssSmall = MainJFrame.makeCssStyle("color: #F0F900; font-size: 8px");
	
	public MapTileInfoPanel(MapTile m) {
		if(elementBackground == null) {
			try {
				elementBackground = ImageIO.read( Boot.class.getResource("/resources/selectionPanelElementBackground.png") );
			} catch (IOException e) {
			}
		}
		setDoubleBuffered(true);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setPreferredSize(new Dimension(170, 170));
		this.m = m;
		setLayout(new MigLayout("gap 1px 1px", "[100px:n]", "[12px:n][12px:n][12px:n][12px:n][12px:n][12px:n][12px:n]"));
		
		JLabel lblTileName = new JLabel(MainJFrame.makeCssStyle("color: #FFFF00; font-size: 12px")+"Tile Name: "+m.getName());
		add(lblTileName, "cell 0 0");
		
		JLabel lblGoldPercent = new ChangedJLable(cssSmall+"Gold Percent: "+m.getMapTileResources().getGoldPercent());
		add(lblGoldPercent, "cell 0 1");
		
		JLabel lblFoodPercent = new ChangedJLable(cssSmall+"Food Percent: "+m.getMapTileResources().getFoodPercent());
		add(lblFoodPercent, "cell 0 2");
		
		JLabel lblWoodPercent = new ChangedJLable(cssSmall+"Wood Percent: "+m.getMapTileResources().getWoodPercent());
		add(lblWoodPercent, "cell 0 3");
		
		JLabel lblStonePercent = new ChangedJLable(cssSmall+"Stone Percent: "+m.getMapTileResources().getManaStonePercent());
		add(lblStonePercent, "cell 0 4");
		
		JLabel lblMetalPercent = new ChangedJLable(cssSmall+"Metal Percent: "+m.getMapTileResources().getMetalPercent());
		add(lblMetalPercent, "cell 0 5");
		
		JLabel lblManaStonePercent = new ChangedJLable(cssSmall+"Mana Stone Percent: "+m.getMapTileResources().getManaStonePercent());
		add(lblManaStonePercent, "cell 0 6");
		
		setVisible(true);
		validate();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(elementBackground, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
	
	private class ChangedJLable extends JLabel {
		private static final long serialVersionUID = 1L;
		public ChangedJLable(String s) {
			super(s);
			this.setForeground(Color.WHITE);
		}
	}
}
