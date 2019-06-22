package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import core.GameInfo;
import core.actions.ControlInput;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.graphics.ResourceManager;
import frame.MainJFrame;
import frame.customPresets.CustomLable;
import net.miginfocom.swing.MigLayout;

public class EndScreenPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 111L;
	private String endScreenTitle;
	
	public EndScreenPanel(boolean gameIsWon) {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 0 0 0 0", "[10%][80%][10%]", "[10%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][6%][10%][10%]"));
		
		add(container, "cell 1 0,grow");
		
		if(gameIsWon) {
			endScreenTitle = MainJFrame.makeCssStyle("font-size: 1.15em; font-weight: bold")+"Congratulations! You Won!";
		} else {
			endScreenTitle = MainJFrame.makeCssStyle("font-size: 1.15em; font-weight: bold")+"You lost! Better luck next time!";
		}
		
		JLabel endScreenTitleLbl = new CustomLable(endScreenTitle, false, SwingConstants.CENTER);
		container.add(endScreenTitleLbl, "cell 1 1,grow");
		
		JLabel totalWoodLbl = new CustomLable("Total Wood: " + GameInfo.getPlayerStats().getTotalWoodCollected(), false, SwingConstants.CENTER);
		container.add(totalWoodLbl, "cell 1 2,grow");
		
		JLabel totalFoodLbl = new CustomLable("Total Food: " + GameInfo.getPlayerStats().getTotalFoodCollected(), false, SwingConstants.CENTER);
		container.add(totalFoodLbl, "cell 1 3,grow");
		
		JLabel totalStoneLbl = new CustomLable("Total Stone: " + GameInfo.getPlayerStats().getTotalStoneCollected(), false, SwingConstants.CENTER);
		container.add(totalStoneLbl, "cell 1 4,grow");
		
		JLabel totalMetalLbl = new CustomLable("Total Metal: " + GameInfo.getPlayerStats().getTotalMetalCollected(), false, SwingConstants.CENTER);
		container.add(totalMetalLbl, "cell 1 5,grow");
		
		JLabel totalGoldLbl = new CustomLable("Total Gold: " + GameInfo.getPlayerStats().getTotalGoldCollected(), false, SwingConstants.CENTER);
		container.add(totalGoldLbl, "cell 1 6,grow");
		
		JLabel totalManaStoneLbl = new CustomLable("Total ManaStone: " + GameInfo.getPlayerStats().getTotalManaStoneCollected(), false, SwingConstants.CENTER);
		container.add(totalManaStoneLbl, "cell 1 7,grow");
		
		JLabel unitsKilled = new CustomLable("Units Killed: " + GameInfo.getPlayerStats().getUnitsKilled(), false, SwingConstants.CENTER);
		container.add(unitsKilled, "cell 1 8,grow");
		
		JLabel unitsCreated = new CustomLable("Units Created: " + GameInfo.getPlayerStats().getUnitsCreated(), false, SwingConstants.CENTER);
		container.add(unitsCreated, "cell 1 9,grow");
		
		JLabel buildingsDestroyed = new CustomLable("Buildings Destroyed: " + GameInfo.getPlayerStats().getBuildingsDestroyed(), false, SwingConstants.CENTER);
		container.add(buildingsDestroyed, "cell 1 10,grow");
		
		JLabel buildingsBuilt = new CustomLable("Buildings Built: " + GameInfo.getPlayerStats().getBuildingsBuilt(), false, SwingConstants.CENTER);
		container.add(buildingsBuilt, "cell 1 11,grow");
		
		JButton btnMainMenu = new JButton_01("Exit to Main Menu");
		btnMainMenu.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnMainMenu.addActionListener(ControlInput.menuChanger);
		container.add(btnMainMenu, "cell 1 13,grow");
		
		JButton btnExit = new JButton_01("Exit Game");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		container.add(btnExit, "cell 1 14,grow");

	}

}