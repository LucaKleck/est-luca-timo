package frame.gamePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import core.Core;
import core.GameInfo;
import core.SelectionPanelFilter;
import entity.Entity;
import entity.unit.Warrior;
import events.Event;
import events.abilities.Ability;
import events.abilities.AddStatusEffect;
import frame.customPresets.CustomProgressBar;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.customPresets.JScrollPaneBg;
import frame.graphics.MapImage;
import frame.graphics.ResourceManager;
import frame.graphics.effects.AbilityEffect;
import net.miginfocom.swing.MigLayout;

public class SelectionPanel extends JScrollPaneBg {
	private static final long serialVersionUID = 126L;
	private ArrayList<Entity> selectedEntityList = new ArrayList<>();
	private ArrayList<SelectionPaneElement> selectedEntityElementList = new ArrayList<>();
	

	/**
	 * @param x
	 * @param y
	 */
	public SelectionPanel(int x, int y) {
		super(ResourceManager.getBackground_03(), true);
		setDoubleBuffered(true);

		JPanel headerPanel = new JPanelCustomBg(ResourceManager.getText_bg_02());
		setColumnHeaderView(headerPanel);

		JLabel lblSelectMenu = new JLabel("Select Menu");
		if (GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
				|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
			lblSelectMenu.setText("Select Target Menu");
		}
		lblSelectMenu.setForeground(Color.YELLOW);
		lblSelectMenu.setFont(new Font("MS PGothic", Font.BOLD, 13));

		JPanel viewportPanel = new JPanelCustomBg(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
		setViewportView(viewportPanel);
		viewportPanel.setLayout(new MigLayout("insets 4 4 2 2", "[fill]", "[fill]"));

		selectedEntityList = GameInfo.getObjectMap().getEntitiesOnTile(x,y);

		new SelectionPanelFilter().sortEntityList(selectedEntityList);

		for (int i = 0; i < selectedEntityList.size(); i++) {
			if(selectedEntityList.get(i).equals(GameInfo.getObjectMap().getSelected().getSelectedEntity())) continue;
			selectedEntityElementList.add(createEntityPane(selectedEntityList.get(i)));
		}
		
		String columns = "";

		for (int i = 0; i < selectedEntityList.size(); i++) {
			columns += "[fill]";
		}

		viewportPanel.setLayout(new MigLayout("insets 12 12 9 9", "[fill]", columns));

		headerPanel.add(lblSelectMenu);

		for (int i = 0; i < selectedEntityElementList.size(); i++) {
			viewportPanel.add(selectedEntityElementList.get(i), ("cell 0 " + i + ", grow"));
		}
		
	}
	
	private SelectionPaneElement createEntityPane(Entity entity) {
		SelectionPaneElement e = new SelectionPaneElement(entity);
		return e;
	}

	public ArrayList<Entity> getSelectedEntityList() {
		return selectedEntityList;
	}

	@Override
	public String toString() {
		return selectedEntityList.toString();
	}


	/**
	 * @author Luca Kleck
	 */
	public class SelectionPaneElement extends JPanelCustomBg implements MouseListener, ActionListener {
		private static final long serialVersionUID = 128L;

		Timer creationAnimationTimer = new Timer(20, this);

		private JButton btnAbility;
		private Entity entity;

		public SelectionPaneElement(Entity entity) {
			super(ResourceManager.getBackground_05());
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
			this.entity = entity;

			MigLayout miglay = new MigLayout("insets 4 5 2 5, gap 4px 0px",
					"[135px][" + (InteractionPanel.getInteractionPanel().getWidth() - 198) + ",fill]",
					"[fill][fill][fill][fill]");
			miglay.preferredLayoutSize(InteractionPanel.getInteractionPanel());
			setLayout(miglay);
			
			
			this.btnAbility = new JButton_01("Select");
			btnAbility.addMouseListener(this);

			this.btnAbility.setPreferredSize(new Dimension(135, 23));
			
			Ability abl = GameInfo.getObjectMap().getSelected().getSelectedAbility();
			
			if(abl == null) {
				// if controllable
				if (entity.isControllable() && GameInfo.getObjectMap().getSelected().getSelectionMode() != 3
						&& GameInfo.getObjectMap().getSelected().getSelectionMode() != 5) {
					this.add(btnAbility, "flowx,cell 0 3,grow");
				}
				// if not controllable
				else if (!entity.isControllable() && GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
						|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
					this.add(btnAbility, "flowx,cell 0 3,grow");
				}
			} else {
				if(entity.isControllable()) {
					if(abl instanceof AddStatusEffect && ((AddStatusEffect) abl).getStatusEffect().isNegative() ||
							abl.getType().matches(Ability.ABILITY_TYPE_DAMAGE)) {
					} else {
						this.add(btnAbility, "flowx,cell 0 3,grow");
					}
				} else {
					if(abl instanceof AddStatusEffect && !((AddStatusEffect) abl).getStatusEffect().isNegative()) {
					} else {
						this.add(btnAbility, "flowx,cell 0 3,grow");
					}
				}
			}

			JLabel lblName = new JLabel(entity.getName());
			lblName.setForeground(Color.YELLOW);
			add(lblName, "cell 0 0 2 1,alignx left,aligny center");

			JLabel lblDamage = new JLabel("Level: " + entity.getLevel());
			lblDamage.setForeground(Color.YELLOW);
			add(lblDamage, "cell 0 2 2 1,alignx left,aligny center");

			JLabel lblHealth = new JLabel("");
			lblHealth.setIcon(new ImageIcon(SelectionPaneElement.class.getResource("/resources/healthIcon.png")));
			add(lblHealth, "flowx,cell 0 1,alignx left,aligny center");

			
			JProgressBar healthStatus = CustomProgressBar.createFromEntity(entity);
			add(healthStatus, "cell 0 1,growy");

		}

		private void select() {
			// if it's with an ability create event with target (this entity and source
			// current selected entity)
			if (GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
					|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
				Ability abl = GameInfo.getObjectMap().getSelected().getSelectedAbility();
				if (abl.getType().equals(Ability.ABILITY_TYPE_DAMAGE) || abl.getType().equals(Ability.ABILITY_TYPE_STATUS_EFFECT)) {
					GameInfo.getObjectMap().getSelected().getSelectedEntity()
							.setEvent(new Event(GameInfo.getObjectMap().getSelected().getSelectedEntity(), entity,
									abl,
									new AbilityEffect(GameInfo.getObjectMap().getSelected().getSelectedEntity(),
											entity, abl)));
				}
				GameInfo.getObjectMap().getSelected().removeSelected();
				InteractionPanel.setCurrentPanel(null);
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getEventlessSelectionQueue().selectFirstInRow();
			} // else just normal selection change
			else {
				InteractionPanel.setCurrentPanel(new EntityPanel(entity));
				GameInfo.getObjectMap().getSelected().setSelectedEntity(entity);
			}
			MapImage.highlightEntity = null;
			if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			}
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(getControllableColor());
			g.fillRoundRect(0, 0, 5, getHeight(), 10, 10);
//			g.setColor(getColorFromClass());
//			g.fillRoundRect(getWidth() - 5, 0, 5, getHeight(), 10, 10);
			g.setColor(new Color(0, 0, 0, 120));
			g.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		}

		private Color getControllableColor() {
			Color c;
			if (entity.isControllable() == true) {
				c = new Color(0, 255, 0);
			} else {
				c = new Color(255, 0, 0);
			}

			return c;
		}

		@SuppressWarnings("unused")
		private Color getColorFromClass() {
			Color c = Color.lightGray;
			if (entity instanceof Warrior) {
				c = new Color(255, 0, 0);
			}
			return c;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (btnAbility.contains(e.getPoint())) {
				select();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			MapImage.highlightEntity = entity;
			if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			MapImage.highlightEntity = null;
			if(Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
				((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
}
