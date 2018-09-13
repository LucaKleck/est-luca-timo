package frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 111L;

	public MainJFrame() {
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		this.add(new MainMenuFrame());
		this.setVisible(true);
	}

}
