package frame.gamePanels;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class AbilityPanel extends JPanel {
	private static final long serialVersionUID = 129L;

	public AbilityPanel() {
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[]", "[]"));
	}
	
	// TODO add runnable thingy that then adds the abilities of the selected entity to this panel, then if statement in runnable that makes new layout to fit in all the buttons.
}
