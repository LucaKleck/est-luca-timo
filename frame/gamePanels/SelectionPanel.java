package frame.gamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import entity.Entity;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class SelectionPanel extends JScrollPane {
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
                JViewport viewPort = InteractionPanel.getSelectionPane().getViewport();
                if (viewPort != null) {
                    int deltaX = origin.x - e.getX();
                    int deltaY = origin.y - e.getY();

                    Rectangle view = viewPort.getViewRect();
                    view.x += deltaX;
                    view.y += deltaY;
                    InteractionPanel.getSelectionPane().getViewport().scrollRectToVisible(view);
                }
            }
        }

    };
    
	public SelectionPanel(int x, int y, int selectionMode) {
		setDoubleBuffered(true);
		setBackground(Color.DARK_GRAY);
		setBorder(null);
		setAutoscrolls(true);
		
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getVerticalScrollBar().setUnitIncrement(16);
		this.getVerticalScrollBar().setUI(new SelectionPanelScrollBarUI());

		
		this.getHorizontalScrollBar().setUnitIncrement(16);
		this.getHorizontalScrollBar().setUI(new SelectionPanelScrollBarUI());
		
		JPanel headerPanel = new JPanel();
		headerPanel.setForeground(Color.LIGHT_GRAY);
		headerPanel.setBackground(Color.DARK_GRAY);
		setColumnHeaderView(headerPanel);

		JLabel lblSelectMenu = new JLabel("Select Menu");
		lblSelectMenu.setForeground(Color.LIGHT_GRAY);
		lblSelectMenu.setFont(new Font("MS PGothic", Font.BOLD, 13));

		JPanel viewportPanel = new JPanel();
		viewportPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setViewportView(viewportPanel);
		viewportPanel.setLayout(new MigLayout("", "[fill]", "[fill]"));

		for (int i = 0; i < ObjectMap.getEntityMap()[x][y].length; i++) {
			if (ObjectMap.getEntityMap()[x][y][i] != null) {
				selectedEntityList.add(ObjectMap.getEntityMap()[x][y][i]);
			}
		}

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
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	private SelectionPaneElement createEntityPane(Entity entity) {
		SelectionPaneElement e = new SelectionPaneElement(entity);
		e.addMouseListener(ma);
        e.addMouseMotionListener(ma);
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
		protected void configureScrollBarColors()
	    {
	        LookAndFeel.installColors(scrollbar, "ScrollBar.background",
	                                  "ScrollBar.foreground");
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
}
