package frame.gamePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.MapTile;
import core.ResourceManager;
import entity.building.Building;
import entity.building.BuildingRessources;
import frame.MainJFrame;
import frame.customPresets.CustomLable;
import net.miginfocom.swing.MigLayout;

public class MapTileInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private MapTile m;
	private MigLayout layout = new MigLayout("gap 1px 1px, fillx", "[100px:n]", "[12px:n][12px:n][12px:n][12px:n][12px:n][12px:n][12px:n]");
	private String cssSmall = MainJFrame.makeCssStyle("color: #F0F900; font-size: 8px");
	private String layoutConstraints =  "cell 0 0, growx";
	
	public MapTileInfoPanel(MapTile m) {
		setDoubleBuffered(true);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setPreferredSize(new Dimension(120, 140));
		this.m = m;
		setLayout(layout);
		
		JLabel lblTileName = new CustomLable(MainJFrame.makeCssStyle("color: #FFFF00; font-size: 12px")+m.getName(), true, SwingConstants.CENTER);
		add(lblTileName, layoutConstraints+",flowy, newline");
		
		
		JLabel lblFoodPercent = new CustomLable(cssSmall+m.getMapTileResources().getFoodPercent()+"%", true);
		lblFoodPercent.setIcon(new ImageIcon(ResourceManager.getFood()));
		if(m.getMapTileResources().getFoodPercent() > 0) {
			add(lblFoodPercent, layoutConstraints);
		}
		
		JLabel lblWoodPercent = new CustomLable(cssSmall+m.getMapTileResources().getWoodPercent()+"%", true);
		lblWoodPercent.setIcon(new ImageIcon(ResourceManager.getWood()));
		if(m.getMapTileResources().getWoodPercent() > 0) {
			add(lblWoodPercent, layoutConstraints);
		}
		
		JLabel lblStonePercent = new CustomLable(cssSmall+m.getMapTileResources().getStonePercent()+"%", true);
		lblStonePercent.setIcon(new ImageIcon(ResourceManager.getStone()));
		if(m.getMapTileResources().getStonePercent() > 0) {
			add(lblStonePercent, layoutConstraints);
		}
		
		JLabel lblMetalPercent = new CustomLable(cssSmall+m.getMapTileResources().getMetalPercent()+"%", true);
		lblMetalPercent.setIcon(new ImageIcon(ResourceManager.getMetal()));
		if(m.getMapTileResources().getMetalPercent() > 0) {
			add(lblMetalPercent, layoutConstraints);
		}
		
		JLabel lblGoldPercent = new CustomLable(cssSmall+m.getMapTileResources().getGoldPercent()+"%", true);
		lblGoldPercent.setIcon(new ImageIcon(ResourceManager.getGold()));
		if(m.getMapTileResources().getGoldPercent() > 0) {
			add(lblGoldPercent, layoutConstraints);
		}
		
		JLabel lblManaStonePercent = new CustomLable(cssSmall+m.getMapTileResources().getManaStonePercent()+"%", true);
		lblManaStonePercent.setIcon(new ImageIcon(ResourceManager.getManaStone()));
		if(m.getMapTileResources().getManaStonePercent() > 0) {
			add(lblManaStonePercent, layoutConstraints);
		}
		
		setVisible(true);
		validate();
	}
	
	public MapTileInfoPanel(MapTile m, String buildingName) {
		setDoubleBuffered(true);
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setPreferredSize(new Dimension(120, 75));
		this.m = m;
		setLayout(layout);
		
		JLabel lblTileName = new CustomLable(MainJFrame.makeCssStyle("color: #FFFF00; font-size: 12px")+buildingName, true, SwingConstants.CENTER);
		add(lblTileName, layoutConstraints+", flowy, newline");
		
		BuildingRessources br = new BuildingRessources(m.getXPos(), m.getYPos(), buildingName);
		
		switch (buildingName) {
		case Building.FOOD_GETTER:
			JLabel lblFoodPercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblFoodPercent.setIcon(new ImageIcon(ResourceManager.getFood()));
			add(lblFoodPercent, layoutConstraints);
			break;
		case Building.WOOD_GETTER:
			JLabel lblWoodPercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblWoodPercent.setIcon(new ImageIcon(ResourceManager.getWood()));
			add(lblWoodPercent, layoutConstraints);
			break;
		case Building.STONE_GETTER:
			JLabel lblStonePercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblStonePercent.setIcon(new ImageIcon(ResourceManager.getStone()));
			add(lblStonePercent, layoutConstraints);
			break;
		case Building.METAL_GETTER:
			JLabel lblMetalPercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblMetalPercent.setIcon(new ImageIcon(ResourceManager.getMetal()));
			add(lblMetalPercent, layoutConstraints);
			break;
		case Building.GOLD_GETTER:
			JLabel lblGoldPercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblGoldPercent.setIcon(new ImageIcon(ResourceManager.getGold()));
			add(lblGoldPercent, layoutConstraints);
			break;
		case Building.MANA_GETTER:
			JLabel lblManaStonePercent = new CustomLable(cssSmall+br.getCollectableRessources()+"%", true);
			lblManaStonePercent.setIcon(new ImageIcon(ResourceManager.getManaStone()));
			add(lblManaStonePercent, layoutConstraints);
			break;
		}
		setVisible(true);
		validate();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getBackground_02(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
	
}
