package frame.gamePanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.Core;
import core.GameInfo;
import core.ResourceManager;
import entity.Entity;
import entity.building.Building;
import frame.MouseHoverListener;

public class EventlessSelectionQueue extends JPanel {
	private static final long serialVersionUID = 3364851222909630455L;

	public EventlessSelectionQueue() {
		setOpaque(false);
		setDoubleBuffered(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		setMinimumSize(new Dimension(280, 64));
		setPreferredSize(new Dimension(280, 64));
		setMaximumSize(new Dimension(280, 64));
		updateList();
	}
	
	private class EventlessEntity extends JPanel {
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
			default:
				entityImage = ResourceManager.getBackground_01();
				break;
			}
			addMouseListener(new MouseListener() {
				

				@Override
				public void mouseReleased(MouseEvent e) {
					if (contains(e.getPoint()) && ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getBtnNextRound().isEnabled()) {
						GameInfo.getObjectMap().getSelected().removeSelected();
						GameInfo.getObjectMap().getSelected().setSelectedMapTile(entity.getXPos(), entity.getYPos());
						GameInfo.getObjectMap().getSelected().setSelectedEntity(entity);
						InteractionPanel.setCurrentPanel(new EntityPanel(entity));
						if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
							MainGamePanel mp = (MainGamePanel) Core.getMainJFrame().getCurrentComponent();
							mp.getMapPanel().setPosition(entity.getXPos(), entity.getYPos());
						}
						if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
							((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
						}
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
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ResourceManager.getSlot(), 6, 6, 48, 48, null);
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
			if(i > 5) break;
			if(e.getEvent() == null && e.isControlable() && !(e instanceof Building)) {
				i++;
				add(new EventlessEntity(e));
			}
		}
	}
	
}
