package frame.menuPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ControlInput;
import core.start;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;

/**
 * Here the user can load a save by typing the save-name and searching the save then/or clicking on a
 * saved game
 * 
 * @author Luca Kleck
 * @see frame.MainJFrame
 */
public class LoadMenuPanel extends JPanel implements ComponentListener {
	private static final long serialVersionUID = 114L;
	private static boolean askBeforeDelete = true;
	
	private JTextField textField;
	private JPanel SavesPanelContainer;
	public LoadMenuPanel() {
		this.setBackground(Color.GRAY);
		setLayout(new MigLayout("", "[100%,grow]", "[100%,fill][fill][fill]"));
		setDoubleBuffered(true);
		
		JButton btnBack = new JButton("Back");
		btnBack.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnBack.addActionListener(ControlInput.menuChanger);
		
		SavesPanelContainer  = new JPanel();
		SavesPanelContainer.setBackground(Color.DARK_GRAY);
		add(SavesPanelContainer, "cell 0 0,grow");
		SavesPanelContainer.setLayout(new MigLayout("", "[100%,fill]", "[100%,fill]"));
		
		SavesPanelContainer.add(new SavesPanel(SavesPanelContainer), "cell 0 0,grow");
		
		JLabel lblSearch = new JLabel("Search");
		add(lblSearch, "flowx,cell 0 1,alignx left,aligny center");
		
		textField = new JTextField();
		add(textField, "cell 0 1,grow");
		textField.setColumns(1);
		add(btnBack, "flowx,cell 0 2,alignx left,aligny center");
		
		JButton btnRefresh = new JButton("Refresh");
		add(btnRefresh, "cell 0 2");
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SavesPanelContainer.removeAll();
				SavesPanelContainer.add(new SavesPanel(SavesPanelContainer));
			}
		});
		this.addComponentListener(this);
		
		JCheckBox chckbxAskBeforeDelete = new JCheckBox("Ask before deleting?");
		chckbxAskBeforeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askBeforeDelete = !isAskBeforeDelete();
			}
		});
		chckbxAskBeforeDelete.setOpaque(false);
		chckbxAskBeforeDelete.setSelected(true);
		add(chckbxAskBeforeDelete, "cell 0 2");
	}
	
	public static boolean isAskBeforeDelete() {
		return askBeforeDelete;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		SavesPanelContainer.removeAll();
		SavesPanelContainer.add(new SavesPanel(SavesPanelContainer));
		start.getMainJFrame().repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
	
}