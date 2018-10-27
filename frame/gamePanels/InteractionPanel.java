package frame.gamePanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class InteractionPanel extends JPanel {
	private static final long serialVersionUID = 124L;

	private static SelectionPanel selectionPane;
	private static SelectionPaneCheck selectionPaneCheck;
	private static InteractionPanel self;

	public InteractionPanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		self = this;
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new MigLayout("", "[100%,fill]", "[100%,fill]"));
		if (selectionPaneCheck == null) {
			selectionPaneCheck = new SelectionPaneCheck();
			selectionPaneCheck.run();
		}
		
	}

	public static SelectionPanel getSelectionPane() {
		return selectionPane;
	}

	public static void setSelectionPane(SelectionPanel selectionPane) {
		InteractionPanel.selectionPane = selectionPane;
		InteractionPanel.getInteractionPanel().repaint();
	}

	public static void removeSelectionPane() {
		InteractionPanel.selectionPane = null;
		selectionPaneCheck.run();
		ObjectMap.getSelected().setSelectedEntity(null);
	}

	private class SelectionPaneCheck implements Runnable {
		Timer timer = new Timer(50, new RefreshTask());

		@Override
		public void run() {
			if (!timer.isRunning()) {
				timer.setRepeats(true);
				timer.start();
			} else {
				timer.restart();
			}
		}

		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				self.removeAll();
				if(selectionPane != null) {
					self.add(selectionPane, "cell 0 0");
				}
				self.validate();
				self.repaint();
			}
			
		}
	}

	public static void staticRemoveAll() {
		if (self != null) {
			if(InteractionPanel.selectionPane == null) {
				self.removeAll();
			}
			self.validate();
			self.repaint();
			InteractionPanel.getInteractionPanel().repaint();
		}
	}

	public static InteractionPanel getInteractionPanel() {
		return self;
	}
}
