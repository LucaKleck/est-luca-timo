package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import core.ControlInput;
import core.GameInfo;
import core.XMLSaveAndLoad;
import frame.JButton_01;
import frame.JPanelBg;
import frame.gamePanels.LogPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can change the settings of the game and enter the name of the
 * save
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class GameSettingsPanel extends JPanelBg {
	private static final long serialVersionUID = 113L;
	private JTextField txtNewGame;

	public GameSettingsPanel() {
		setLayout(new MigLayout("", "[grow]", "[10%][3%][100%][fill]"));

		JLabel lblGameName = new JLabel("Game Name");
		add(lblGameName, "flowy,cell 0 0");

		txtNewGame = new JTextField();
		txtNewGame.setToolTipText("Enter save name");
		txtNewGame.setText("New Game");
		add(txtNewGame, "cell 0 0,growx");
		txtNewGame.setColumns(1);
		((AbstractDocument)txtNewGame.getDocument()).setDocumentFilter(new SaveNameTextDocumentFilter());
		
		JButton btnStartGame = new JButton_01("Start Game");
		btnStartGame.setActionCommand("frame.gamePanels.MainGamePanel");
		btnStartGame.addActionListener(ControlInput.menuChanger);
		btnStartGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogPanel.setLoad("This is the log, keeping track of all important events");
				new XMLSaveAndLoad(txtNewGame.getText());
				new GameInfo();
			}

		});
		add(btnStartGame, "cell 0 1");
		
		JButton btnBack = new JButton_01("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		add(btnBack, "cell 0 3");

	}
	private class SaveNameTextDocumentFilter extends DocumentFilter {
		private final static int charLimit = 30;
		
		@Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - charLimit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs); 
            }
        }
		
	}
}