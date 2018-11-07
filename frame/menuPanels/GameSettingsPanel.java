package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import core.ControlInput;
import core.XMLSaveAndLoad;
import frame.gamePanels.InfoPanel;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.LogPanel;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can change the settings of the game and enter the name of the
 * save
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class GameSettingsPanel extends JPanel {
	private static final long serialVersionUID = 113L;
	private JTextField txtNewGame;

	public GameSettingsPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[grow]", "[10%][3%][100%][fill]"));

		JLabel lblGameName = new JLabel("Game Name");
		add(lblGameName, "flowy,cell 0 0");

		txtNewGame = new JTextField();
		txtNewGame.setToolTipText("Enter save name");
		txtNewGame.setText("New Game");
		add(txtNewGame, "cell 0 0,growx");
		txtNewGame.setColumns(1);
		((AbstractDocument)txtNewGame.getDocument()).setDocumentFilter(new SaveNameTextDocumentFilter());
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setActionCommand("frame.gamePanels.MainGamePanel");
		btnStartGame.addActionListener(ControlInput.menuChanger);
		btnStartGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectMap.getSelected().removeSelected();
					InteractionPanel.setSelectionPane(null);
					InfoPanel.refresh();
					LogPanel.reset("This is the log, keeping track of all important events");
				} catch (NullPointerException nl) {
					
				}
				new XMLSaveAndLoad(txtNewGame.getText());
				new ObjectMap();
			}

		});
		add(btnStartGame, "cell 0 1");
		
		JButton btnBack = new JButton("Back");
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