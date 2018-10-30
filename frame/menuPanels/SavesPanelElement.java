package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import core.ControlInput;
import core.XMLSaveAndLoad;
import frame.gamePanels.InfoPanel;
import frame.gamePanels.InteractionPanel;
import map.ObjectMap;
import net.miginfocom.swing.MigLayout;

public class SavesPanelElement extends JPanel {
	private static final long serialVersionUID = -5017892450502876588L;
	JButton btnDelete;
	JButton btnLoad;

	public SavesPanelElement(File save, JPanel savesPanelContainer) {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		MigLayout miglay = new MigLayout("insets 4 4 4 4, gap 4px 4px", "[135px]["+(savesPanelContainer.getWidth()-202)+",fill]", "[fill][fill][fill][fill]");
		
		this.setLayout(miglay);
		
		JLabel lblSave = new JLabel("Save: "+save.getName().substring(0, (save.getName().length()-4)) );
		add(lblSave, "cell 0 0 2 1");
		
		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LoadMenuPanel.isAskBeforeDelete()) {
					if(JOptionPane.showConfirmDialog(btnDelete, "Sure you want to delete?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)  {
						save.delete();
						btnDelete.setText("Deleted!");
						btnDelete.setEnabled(false);
						btnLoad.setText("Deleted!");
						btnLoad.setEnabled(false);
					}
				} else {
					save.delete();
					btnDelete.setText("Deleted!");
					btnDelete.setEnabled(false);
					btnLoad.setText("Deleted!");
					btnLoad.setEnabled(false);
				}
				
			}
		});
		add(btnDelete, "cell 0 1,alignx left,aligny top");
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new XMLSaveAndLoad(save.getName().substring(0, save.getName().length()-4));
				XMLSaveAndLoad.loadGame(save);
				ControlInput.menuChanger.actionPerformed(new ActionEvent(this, 0, "frame.gamePanels.MainGamePanel"));
				try {
					ObjectMap.getSelected().removeSelected();
					InfoPanel.refresh();
					InteractionPanel.setSelectionPane(null);
				} catch (NullPointerException nl) {
				}
			}
		});
		add(btnLoad, "cell 0 3,grow");
		
	}

}
