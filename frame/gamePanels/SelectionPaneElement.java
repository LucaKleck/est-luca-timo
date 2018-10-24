package frame.gamePanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Entity;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class SelectionPaneElement extends JPanel implements ActionListener {
	private static final long serialVersionUID = 128L;
	private JButton jBtn;
	private Entity entity;
	
	public SelectionPaneElement(Entity entity) {
		this.entity = entity;
		this.jBtn = new JButton(entity.getName());
		this.jBtn.addActionListener(this);
		setLayout(new MigLayout("", "[20%,fill]", "[20%,fill]"));
		this.add(jBtn, "cell 0 0,alignx left,aligny top");
		
		this.setBackground(getColorFromName());
	}
	
	private Color getColorFromName() {
		Color c = Color.darkGray;
		if(entity.getName() == "2") {
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
	}
	
}
