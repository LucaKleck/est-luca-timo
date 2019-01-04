package frame.gamePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import abilities.Ability;
import core.Core;
import core.GameInfo;
import net.miginfocom.swing.MigLayout;

public class AbilityPanel extends JPanel {
	private static final long serialVersionUID = 129L;
	private static AbilityPanel self;
	
	public AbilityPanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[]", "[]"));
		this.setMinimumSize(new Dimension(40, 40));
		setDoubleBuffered(true);
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		self = this;
	}


	public void update() {
		if(GameInfo.getObjectMap().getSelected().getSelectedEntity() != null) {
			self.removeAll();
			System.gc();
			if(GameInfo.getObjectMap().getSelected().getSelectedEntity().hasAbility()) {
				ArrayList<Ability> abList = GameInfo.getObjectMap().getSelected().getSelectedEntity().getAbilities();
				String columns = "";

				for (int i = 0; i < abList.size(); i++) {
					columns += "[fill]";
				}

				self.setLayout(new MigLayout("", columns, "[fill]"));
				
				for (int i = 0; i < abList.size(); i++) {
					JButton jButton = new JButton(abList.get(i).getName() );
					Ability abl = abList.get(i);
					jButton.setToolTipText(abl.getDescription());
					jButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							InteractionPanel.setCurrentPanel(null);
							GameInfo.getObjectMap().getSelected().setSelectedAbility(abl);
						}
					});
					self.add(jButton, ("cell " + i + " 0, grow"));
				}
				self.repaint();
				self.getParent().repaint();
				Core.getMainJFrame().repaint();
			}
		} else {
			self.removeAll();
			self.repaint();
			self.getParent().repaint();
			System.gc();
		}
			
	}
	
}
