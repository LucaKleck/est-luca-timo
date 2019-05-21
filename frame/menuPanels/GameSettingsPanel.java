package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import core.GameInfo;
import core.XMLSaveAndLoad;
import core.actions.ControlInput;
import frame.MainJFrame;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.customPresets.CustomLable;
import frame.gamePanels.LogPanel;
import frame.graphics.ResourceManager;
import net.miginfocom.swing.MigLayout;

/**
 * Here the user can change the settings of the game and enter the name of the
 * save
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class GameSettingsPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 113L;
	private JTextField txtNewGame;

	public GameSettingsPanel() {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 30 30 30 30", "[100%]", "[100%]"));
		
		add(container, "cell 1 0,grow, ay top");

		JLabel lblGameName = new CustomLable(MainJFrame.makeCssStyle("font-size: 1.3em;")+"Game Name", true);
		container.add(lblGameName, "cell 0 0, ay top, newline, flowy");

		txtNewGame = new JTextField();
		txtNewGame.setBackground(new Color(182, 137, 100));
		txtNewGame.setForeground(Color.YELLOW);
		txtNewGame.setBorder(BorderFactory.createLineBorder(getBackground().darker().darker(), 4, true));
		txtNewGame.setToolTipText("Enter save name");
		Random r = new Random();
		txtNewGame.setText("New Game "+r.nextInt(25565));
		
		container.add(txtNewGame, "cell 0 0,growx, ay top");
		
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
		container.add(btnStartGame, "cell 0 0, ay top");
		
		JButton btnBack = new JButton_01("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		container.add(btnBack, "cell 0 2, ax right, ay bottom");

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