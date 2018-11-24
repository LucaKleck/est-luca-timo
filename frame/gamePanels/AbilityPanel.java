package frame.gamePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import abilities.Ability;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class AbilityPanel extends JPanel {
	private static final long serialVersionUID = 129L;
	private static AbilityPanel self;
	
	public AbilityPanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[]", "[]"));
		self = this;
	}


	public static void checkAbilities() {
		if(ObjectMap.getSelected().getSelectedAbility() != null) {
			// remove abilites from view
		} else try {
			if(ObjectMap.getSelected().getSelectedEntity().hasAbility()) {
				ArrayList<Ability> abList = ObjectMap.getSelected().getSelectedEntity().getAbilities();
				String columns = "";

				for (int i = 0; i < abList.size(); i++) {
					columns += "[fill]";
				}

				self.setLayout(new MigLayout("", columns, "[fill]"));
				
				for (int i = 0; i < abList.size(); i++) {
					JButton jButton = new JButton(abList.get(i).getName() );
					Ability abl = abList.get(i);
					jButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							ObjectMap.getSelected().setSelectedAbility(abl);
						}
					});
					self.add(jButton, ("cell 0 " + i + ", grow"));
				}
				
				
			}
		} catch (NullPointerException nl) {
		}
			
	}
	
}
