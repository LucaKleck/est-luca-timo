package frame.menuPanels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ControlInput;
import net.miginfocom.swing.MigLayout;

public class GameSettingsPanel extends JPanel {
	private static final long serialVersionUID = 114L;
	private JTextField txtNewGame;

	public GameSettingsPanel() {
		this.setBackground(Color.GREEN);
		setLayout(new MigLayout("", "[grow]", "[][][][][][][][][]"));
		
		JLabel lblGameName = new JLabel("Game Name");
		add(lblGameName, "flowy,cell 0 0");
		
		txtNewGame = new JTextField();
		txtNewGame.setText("New Game");
		add(txtNewGame, "cell 0 0,growx");
		txtNewGame.setColumns(10);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setActionCommand("frame.gamePanels.MainGamePanel");
		btnStartGame.addActionListener(ControlInput.menuChanger);
		add(btnStartGame, "cell 0 8");
		
	}

	
}