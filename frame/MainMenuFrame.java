package frame;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.CoreController;
import net.miginfocom.swing.MigLayout;

public class MainMenuFrame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 112L;
	
	public MainMenuFrame() {
		setBackground(SystemColor.desktop);
		setLayout(new MigLayout("", "[30%]", "[:20%:20%][:10%:10%][:10%:10%][:10%:10%][:50%:50%]"));
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setActionCommand("frame.GameSettingsFrame");
		btnNewGame.addActionListener(this);
		add(btnNewGame, "cell 0 1,grow");
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setActionCommand("frame.insertFrameName");
		btnLoadGame.addActionListener(this);
		add(btnLoadGame, "cell 0 2,grow");
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setActionCommand("frame.OptionsMenuFrame");
		btnOptions.addActionListener(this);
		add(btnOptions, "cell 0 3,grow");
	
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent evt) {
		// change to mainJFrame.add( ClassLoader(name/command) )
			CoreController.mainJFrame.remove(this);
			try {
				CoreController.mainJFrame.add( (Component) ClassLoader.getSystemClassLoader().loadClass(evt.getActionCommand()).newInstance() );
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			CoreController.mainJFrame.resize(CoreController.mainJFrame.getWidth(), CoreController.mainJFrame.getHeight()+1);
			CoreController.mainJFrame.resize(CoreController.mainJFrame.getWidth(), CoreController.mainJFrame.getHeight()-1);
	}	
}
