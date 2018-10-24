package frame.gamePanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static SelectionPanel selectionPane;
	private static Refresh refresh;
	private static InteractionPanel self;
	
	public InteractionPanel() {
		self = this;
		if(refresh == null) {
			refresh = new Refresh();
			refresh.run();
		}
	}
	public static SelectionPanel getSelectionPane() {
		return selectionPane;
	}

	public static void setSelectionPane(SelectionPanel selectionPane) {
		InteractionPanel.selectionPane = selectionPane;
	}
	
	private class Refresh implements Runnable {
		@Override
		public void run() {
			Timer timer = new Timer(50, new RefreshTask());
			timer.setRepeats(true);
			timer.start();
		}
		
		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
//					System.out.println(selectionPane.getSelectedEntityList().toString());
//				} catch(NullPointerException ex) {
//				}
				if(selectionPane != null) {
					removeAll();
					add(selectionPane);
					validate();
					repaint();
				}
				if(!isValid()) {
					validate();
					repaint();
				}
			}

		}
	}

	public static void staticValidate() {
		if(self != null) {
			self.validate();
			self.repaint();
		}
	}
}
