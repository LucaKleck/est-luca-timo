/**  
* GameSettingsPanel.java - Here the user can change the settings of the game and enter the name of the save
* @author Luca Kleck
* @version 0.01
* @since 0.01 
* @see MainJFrame
*/
package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ControlInput;
import frame.MainJFrame;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class GameSettingsPanel extends JPanel {
	private static final long serialVersionUID = 113L;
	private JTextField txtNewGame;

	public GameSettingsPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[grow]", "[10%][3%]"));
		
		JLabel lblGameName = new JLabel("Game Name");
		add(lblGameName, "flowy,cell 0 0");
		
		txtNewGame = new JTextField();
		txtNewGame.setText("New Game");
		add(txtNewGame, "cell 0 0,growx"); // this text field will make up the save name of the game!
		txtNewGame.setColumns(10);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setActionCommand("frame.gamePanels.MainGamePanel");
		btnStartGame.addActionListener(ControlInput.menuChanger);
		btnStartGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				ObjectMap objectMap = new ObjectMap();
			}
		});
		// TODO add another action listener that creates the save/makes the map and so on
		add(btnStartGame, "cell 0 1");
		
	}

	
}