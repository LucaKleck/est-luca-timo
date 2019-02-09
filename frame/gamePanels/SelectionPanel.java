package frame.gamePanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import abilities.Ability;
import abilities.FireBall;
import abilities.Melee;
import core.Boot;
import core.Core;
import core.Event;
import core.GameInfo;
import effects.AbilityEffect;
import entity.Entity;
import entity.unit.Unit;
import entity.unit.Warrior;
import net.miginfocom.swing.MigLayout;

public class SelectionPanel extends JScrollPane {
	private static BufferedImage background;
	private static BufferedImage elementBackground;
	private static final long serialVersionUID = 126L;
	private ArrayList<Entity> selectedEntityList = new ArrayList<>();
	private ArrayList<SelectionPaneElement> selectedEntityElementList = new ArrayList<>();
	private MouseAdapter ma = new MouseAdapter() {

		private Point origin;

		@Override
		public void mousePressed(MouseEvent e) {
			origin = new Point(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (origin != null) {
				JViewport viewPort = InteractionPanel.getCurrentPanel().getViewport();
				if (viewPort != null) {
					int deltaY = origin.y - e.getY();
					Point p = new Point();
					p.setLocation(viewPort.getViewPosition().getX(), viewPort.getViewPosition().getY() + deltaY);
					if (p.getY() < 0) {
						p.setLocation(p.getX(), 0);
					}
					viewPort.setViewPosition(p);
					origin = new Point(e.getPoint());
				}
			}
		}

	};

	/**
	 * @param x
	 * @param y
	 */
	public SelectionPanel(int x, int y) {
		if (background == null) {
			try {
				background = ImageIO.read(Boot.class.getResource("/resources/selectionPanelBackground.png"));
			} catch (IOException e) {
			}
		}
		setBackground(new Color(0, 0, 0, 0));
		setDoubleBuffered(true);
		setBorder(null);
		setAutoscrolls(true);
		addMouseListener(ma);
		addMouseMotionListener(ma);

		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getVerticalScrollBar().setUnitIncrement(16);
		this.getVerticalScrollBar().setUI(new SelectionPanelScrollBarUI());
		this.getVerticalScrollBar().setBackground(new Color(0, 0, 0, 0));

		this.getHorizontalScrollBar().setUnitIncrement(16);
		this.getHorizontalScrollBar().setUI(new SelectionPanelScrollBarUI());

		JPanel headerPanel = new ViewportPanel();
		headerPanel.setForeground(Color.LIGHT_GRAY);
		setColumnHeaderView(headerPanel);

		JLabel lblSelectMenu = new JLabel("Select Menu");
		if (GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
				|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
			lblSelectMenu.setText("Select Target Menu");
			// TODO remove currently selected entity from list

		}
		lblSelectMenu.setForeground(Color.LIGHT_GRAY);
		lblSelectMenu.setFont(new Font("MS PGothic", Font.BOLD, 13));

		JPanel viewportPanel = new ViewportPanel();
		viewportPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setViewportView(viewportPanel);
		viewportPanel.setLayout(new MigLayout("", "[fill]", "[fill]"));

		for (int i = 0; i < GameInfo.getObjectMap().getEntityMap().size(); i++) {
			if (GameInfo.getObjectMap().getEntityMap().get(i).getXPos() == x
					&& GameInfo.getObjectMap().getEntityMap().get(i).getYPos() == y) {
				selectedEntityList.add(GameInfo.getObjectMap().getEntityMap().get(i));
			}
		}

		new SelectionPanelFilter().sortEntityList(selectedEntityList);

		for (int i = 0; i < selectedEntityList.size(); i++) {
			selectedEntityElementList.add(createEntityPane(selectedEntityList.get(i)));
		}

		String columns = "";

		for (int i = 0; i < selectedEntityList.size(); i++) {
			columns += "[fill]";
		}

		viewportPanel.setLayout(new MigLayout("", "[fill]", columns));

		headerPanel.add(lblSelectMenu);

		for (int i = 0; i < selectedEntityElementList.size(); i++) {
			viewportPanel.add(selectedEntityElementList.get(i), ("cell 0 " + i + ", grow"));
		}

	}

	private class ViewportPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public ViewportPanel() {
			this.setBackground(new Color(0, 0, 0, 0));
		}

		@Override
		public void paint(Graphics g) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			super.paint(g);
		}

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
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

	class SelectionPanelScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
			LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground");
			thumbHighlightColor = Color.DARK_GRAY;
			thumbLightShadowColor = Color.DARK_GRAY;
			thumbDarkShadowColor = Color.DARK_GRAY;
			thumbColor = Color.DARK_GRAY;
			trackColor = new Color(255, 255, 255, 120);
			trackHighlightColor = new Color(255, 255, 255, 120);
		}

		@Override
		protected JButton createDecreaseButton(int orientation) {
			JButton btn = new BasicArrowButton(orientation, Color.LIGHT_GRAY, new Color(0, 0, 0, 255),
					new Color(0, 0, 0, 255), new Color(0, 0, 0, 0));
			btn.setOpaque(false);
			return btn;
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			JButton btn = new BasicArrowButton(orientation, Color.LIGHT_GRAY, new Color(0, 0, 0, 255),
					new Color(0, 0, 0, 255), new Color(0, 0, 0, 0));
			btn.setOpaque(false);
			return btn;
		}

	}

	/**
	 * @author Luca Kleck
	 */
	public class SelectionPaneElement extends JPanel implements MouseListener, ActionListener {
		private static final long serialVersionUID = 128L;

		Timer creationAnimationTimer = new Timer(20, this);

		private JButton jBtn;
		private Entity entity;

		public SelectionPaneElement(Entity entity) {
			if (elementBackground == null) {
				try {
					elementBackground = ImageIO
							.read(Boot.class.getResource("/resources/selectionPanelElementBackground.png"));
				} catch (IOException e) {
				}
			}
			setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			this.entity = entity;
			this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			this.setBackground(new Color(0, 0, 0, 0));
			this.setOpaque(false);

			MigLayout miglay = new MigLayout("insets 4 5 2 5, gap 4px 0px",
					"[135px][" + (InteractionPanel.getInteractionPanel().getWidth() - 192) + ",fill]",
					"[fill][fill][fill][fill]");

			miglay.preferredLayoutSize(InteractionPanel.getInteractionPanel());

			setLayout(miglay);
			this.jBtn = new JButton("Select");
			jBtn.setOpaque(false);
			jBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jBtn.setBackground(new Color(0, 0, 0, 120));
			jBtn.setForeground(Color.WHITE);
			jBtn.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jBtn.addMouseListener(this);

			this.jBtn.setPreferredSize(new Dimension(135, 23));
			if (entity.isControlable() && GameInfo.getObjectMap().getSelected().getSelectionMode() != 3
					&& GameInfo.getObjectMap().getSelected().getSelectionMode() != 5) {
				this.add(jBtn, "flowx,cell 0 3,grow");
			} else if (!entity.isControlable() && GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
					|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
				this.add(jBtn, "flowx,cell 0 3,grow");
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

			JProgressBar healthStatus = new JProgressBar();
			healthStatus.setMaximum(entity.getMaxHealth());
			healthStatus.setMinimum(0);
			healthStatus.setValue(entity.getCurrentHealth());
			healthStatus.setStringPainted(true);
			healthStatus.setString(entity.getCurrentHealth() + "/" + entity.getMaxHealth());
			add(healthStatus, "cell 0 1,growy");

		}

		public void paint(Graphics g) {
			g.drawImage(elementBackground, 0, 0, getWidth(), getHeight(), null);
//			g.setColor(Color.LIGHT_GRAY);
//			g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
			g.setColor(getControllableColor());
			g.fillRoundRect(0, 0, 5, getHeight(), 10, 10);
			g.setColor(getColorFromClass());
			g.fillRoundRect(getWidth() - 5, 0, 5, getHeight(), 10, 10);
			g.setColor(new Color(0, 0, 0, 120));
			g.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
			super.paint(g);
		}

		private Color getControllableColor() {
			Color c;
			if (entity.isControlable() == true) {
				c = new Color(0, 255, 0);
			} else {
				c = new Color(255, 0, 0);
			}

			return c;
		}

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
			if (jBtn.contains(e.getPoint())) {
				// if it's with an ability create event with target (this entity and source
				// current selected entity)
				if (GameInfo.getObjectMap().getSelected().getSelectionMode() == 3
						|| GameInfo.getObjectMap().getSelected().getSelectionMode() == 5) {
					Ability abl = GameInfo.getObjectMap().getSelected().getSelectedAbility();
					if (abl instanceof Melee) {
						GameInfo.getObjectMap().getSelected().getSelectedEntity()
								.setEvent(new Event(GameInfo.getObjectMap().getSelected().getSelectedEntity(), entity,
										abl,
										new AbilityEffect(
												(Unit) GameInfo.getObjectMap().getSelected().getSelectedEntity(),
												(Unit) entity, abl)));
					}
					if (abl instanceof FireBall) {
						GameInfo.getObjectMap().getSelected().getSelectedEntity()
								.setEvent(new Event(GameInfo.getObjectMap().getSelected().getSelectedEntity(), entity,
										abl,
										new AbilityEffect(
												(Unit) GameInfo.getObjectMap().getSelected().getSelectedEntity(),
												(Unit) entity, abl)));
					}
					GameInfo.getObjectMap().getSelected().removeSelected();
					InteractionPanel.setCurrentPanel(null);
				} // else just normal selection change
				else {
					InteractionPanel.setCurrentPanel(new EntityPanel(entity));
					GameInfo.getObjectMap().getSelected().setSelectedEntity(entity);
				}
				if (Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
}
