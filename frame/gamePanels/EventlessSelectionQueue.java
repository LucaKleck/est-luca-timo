package frame.gamePanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import core.Core;
import core.GameInfo;
import entity.Entity;
import entity.building.Building;
import frame.customPresets.JPanelCustomBg;
import frame.customPresets.MouseHoverListener;
import frame.graphics.ResourceManager;

public class EventlessSelectionQueue extends JPanelCustomBg {
	private static final long serialVersionUID = 3364851222909630455L;

	public EventlessSelectionQueue() {
		super(ResourceManager.getBackground_05(), false, true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setMinimumSize(new Dimension(282, 83));
		setPreferredSize(new Dimension(282, 83));
		setMaximumSize(new Dimension(282, 83));
		updateList();
	}
	
	public class EventlessEntity extends JPanel {
		private static final long serialVersionUID = -8414788789036001915L;
		private Entity entity;
		private BufferedImage entityImage = null;;
		
		public EventlessEntity(Entity e) {
			setDoubleBuffered(true);
			addMouseListener(new MouseHoverListener(this));
			entity = e;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			setOpaque(false);
			setPreferredSize(new Dimension(64, 64));
			setMinimumSize(new Dimension(64, 64));
			setBackground(new Color(0, 0, 0, 0));
			switch (e.getClass().getSimpleName()) {
			case "Warrior":
				entityImage = ResourceManager.getWarriorImage();
				break;
			case "Mage":
				entityImage = ResourceManager.getMageImage();
				break;
			case "Builder":
				entityImage = ResourceManager.getBuilderImage();
				break;
			case "Archer":
				entityImage = ResourceManager.getArcherImage();
				break;
			case "Trebuchet":
				entityImage = ResourceManager.getTrebuchetImage();
				break;
			case "BatteringRam":
				entityImage = ResourceManager.getBatteringRamImage();
				break;
			case "Cavalry":
				entityImage = ResourceManager.getCavalryImage();
				break;
			case "CavalryArcher":
				entityImage = ResourceManager.getCavalryArcherImage();
				break;
			case "Knight":
				entityImage = ResourceManager.getKnightImage();
				break;
			case "Hero":
				entityImage = ResourceManager.getHeroImage();
				break;
			case "Dragon":
				entityImage = ResourceManager.getDragonImage();
				break;
			case "Priest":
				entityImage = ResourceManager.getPriestImage();
				break;
			case "Mangonel":
				entityImage = ResourceManager.getMangonelImage();
				break;
			default:
				entityImage = ResourceManager.getBackground_01();
				break;
			}
			
			
			addMouseListener(new MouseListener() {
				

				@Override
				public void mouseReleased(MouseEvent e) {
					if (contains(e.getPoint())) {
						select();
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			
		}
		
		private void select() {
			if (((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getBtnNextRound().isEnabled()) {
				GameInfo.getObjectMap().getSelected().removeSelected();
				GameInfo.getObjectMap().getSelected().setSelectedMapTile(entity.getXPos(), entity.getYPos());
				GameInfo.getObjectMap().getSelected().setSelectedEntity(entity);
				InteractionPanel.setCurrentPanel(new EntityPanel(entity));
			}
			if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				MainGamePanel mp = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
				mp.getMapPanel().setPosition(entity.getXPos(), entity.getYPos());
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ResourceManager.getSlot(), 0, 0, 64, 64, null);
			g.drawImage(entityImage, 16, 16, 32, 32, null);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getBackground_01(), 0, 0, getPreferredSize().width, getPreferredSize().height, null);
		super.paint(g);
	}
	
	public void updateList() {
		removeAll();
		int i = 0;
		for(Entity e : GameInfo.getObjectMap().getEntityMap()) {
			if(i >= 4) break;
			if(e.getEvent() == null && e.isControllable() && !(e instanceof Building)) {
				i++;
				add(new EventlessEntity(e));
			}
		}
	}
	
	public void selectFirstInRow()  {
		if(Core.loadSetting(Core.SETTING_AUTO_SELECT_NEXT).matches("true")) {
			if(getComponentCount() > 0) {
				((EventlessEntity) getComponent(0)).select();
			}
		}
	}
	
}
