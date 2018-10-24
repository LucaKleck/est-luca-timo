package frame.gamePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setLayout(new MigLayout("", "[87px]", "[23px]"));
		this.add(jBtn, "cell 0 0,alignx left,aligny top");
	}
	
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ObjectMap.getSelected().setSelectedEntity(entity);
		InteractionPanel.setSelectionPane(null);
	}
	
}
