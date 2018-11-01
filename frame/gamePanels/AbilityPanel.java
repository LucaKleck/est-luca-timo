package frame.gamePanels;

import javax.swing.JPanel;

import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class AbilityPanel extends JPanel {
	private static final long serialVersionUID = 129L;

	public AbilityPanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[]", "[]"));
	}


	public static void checkAbilities() {
		if(ObjectMap.getSelected().getSelectedAbility() != null) {
			// remove abilites from view
		} else try {
			if(ObjectMap.getSelected().getSelectedEntity().hasAbility()) {
				// create buttons and stuff
			}
		} catch (NullPointerException nl) {
		}
			
	}
	
}
