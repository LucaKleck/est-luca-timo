package frame;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainMenuFrame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 112L;

	public MainMenuFrame() {
		
		setBackground(SystemColor.desktop);
		setLayout(new MigLayout("", "[30%]", "[:20%:20%][:10%:10%][:10%:10%][:10%:10%][:50%:50%]"));
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setActionCommand("goToNewGameFrame");
		add(btnNewGame, "cell 0 1,grow");
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setActionCommand("goToLoadFrame");
		add(btnLoadGame, "cell 0 2,grow");
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setActionCommand("goToOptionFrame");
		add(btnOptions, "cell 0 3,grow");
	
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand() == "goToNewGameFrame") {
			
		}
	}	
}
