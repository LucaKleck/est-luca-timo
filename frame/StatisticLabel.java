package frame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StatisticLabel extends JLabel {

	private static final long serialVersionUID = 6270195112324466784L;
	
	public StatisticLabel(String name) {
		super(name, SwingConstants.CENTER);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setForeground(Color.YELLOW);
	}
	
}
