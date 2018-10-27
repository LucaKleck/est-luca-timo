package frame.gamePanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entity.Entity;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;

public class SelectionPaneElement extends JPanel implements ActionListener {
	private static final long serialVersionUID = 128L;
	private JButton jBtn;
	private Entity entity;

	public SelectionPaneElement(Entity entity) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.entity = entity;
		MigLayout miglay = new MigLayout("insets 4 5 2 5, gap 4px 0px", "[135px]["+(InteractionPanel.getInteractionPanel().getWidth()-202)+",fill]", "[fill][fill][fill][fill]");
		miglay.preferredLayoutSize(InteractionPanel.getInteractionPanel());
		setLayout(miglay);
		this.jBtn = new JButton("Select");
		jBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jBtn.setBackground(Color.GRAY);
		jBtn.setForeground(Color.WHITE);
		jBtn.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.jBtn.addActionListener(this);
		this.jBtn.setPreferredSize(new Dimension(135, 23));
		this.add(jBtn, "flowx,cell 0 3,grow");

		this.setBackground(getColorFromName());

		JLabel lblName = new JLabel(entity.getName());
		add(lblName, "cell 0 0 2 1,alignx left,aligny center");

		JLabel lblDamage = new JLabel("Range: " + entity.getMaxRange());
		add(lblDamage, "cell 0 2 2 1,alignx left,aligny center");

		JLabel lblHealth = new JLabel("Health: " + entity.getHealth());
		lblHealth.setIcon(new ImageIcon(SelectionPaneElement.class.getResource("/resources/healthIcon.png")));
		add(lblHealth, "cell 0 1 2 1,alignx left,aligny center");
		
	}

	private Color getColorFromName() {
		Color c = Color.lightGray;
		if (entity.getName() == "2") {
			c = new Color(200, 200, 100);
		}
		return c;
	}

	public Entity getEntity() {
		return entity;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ObjectMap.getSelected().setSelectedEntity(entity);
		InteractionPanel.setSelectionPane(null);
		InfoPanel.refresh();
	}
}
