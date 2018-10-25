package frame.gamePanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Entity;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class SelectionPaneElement extends JPanel implements ActionListener {
	private static final long serialVersionUID = 128L;
	private JButton jBtn;
	private Entity entity;

	public SelectionPaneElement(Entity entity, int filler) {
		this.entity = entity;
		setLayout(new MigLayout("", "[100px:20%,fill][" + filler + "px:n,fill]", "[5%,fill][5%,fill][5%,fill][5%]"));
		this.jBtn = new JButton("Select");
		this.jBtn.addActionListener(this);

		this.add(jBtn, "flowx,cell 0 0,grow");

		this.setBackground(getColorFromName());

		JLabel lblName = new JLabel(entity.getName());
		add(lblName, "cell 0 1 2 1,alignx left,aligny center");

		JLabel lblDamage = new JLabel("Range: " + entity.getMaxRange());
		add(lblDamage, "cell 0 2,alignx left,aligny center");

		JLabel lblHealth = new JLabel("Health: " + entity.getHealth());
		add(lblHealth, "cell 0 3,alignx left,aligny center");
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
		System.out.println(entity.getName());
		ObjectMap.getSelected().setSelectedEntity(entity);
		InteractionPanel.setSelectionPane(null);
		InteractionPanel.staticValidate();
	}

}
