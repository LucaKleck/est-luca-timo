package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import core.ControlInput;
import core.GameInfo;
import frame.JButton_01;
import frame.JPanelBg;
import frame.StatisticLabel;
import net.miginfocom.swing.MigLayout;

public class EndScreenPanel extends JPanelBg {
	private static final long serialVersionUID = 111L;
	private String endScreenTitle;
	
	public EndScreenPanel(boolean gameIsWon) {
		setLayout(new MigLayout("", "[33%][33%][33%]", "[10%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][6%][10%][10%]"));

		if(gameIsWon) {
			endScreenTitle = "Congratulations! You Won!";
		} else {
			endScreenTitle = "You Lost! Good Luck Next Time!";
		}
		
		JLabel endScreenTitleLbl = new StatisticLabel(endScreenTitle);
		add(endScreenTitleLbl, "cell 1 1,grow");
		
		JLabel totalWoodLbl = new StatisticLabel("Total Wood: " + GameInfo.getPlayerStats().getTotalWoodCollected());
		add(totalWoodLbl, "cell 1 2,grow");
		
		JLabel totalFoodLbl = new StatisticLabel("Total Food: " + GameInfo.getPlayerStats().getTotalFoodCollected());
		add(totalFoodLbl, "cell 1 3,grow");
		
		JLabel totalStoneLbl = new StatisticLabel("Total Stone: " + GameInfo.getPlayerStats().getTotalStoneCollected());
		add(totalStoneLbl, "cell 1 4,grow");
		
		JLabel totalMetalLbl = new StatisticLabel("Total Metal: " + GameInfo.getPlayerStats().getTotalMetalCollected());
		add(totalMetalLbl, "cell 1 5,grow");
		
		JLabel totalGoldLbl = new StatisticLabel("Total Gold: " + GameInfo.getPlayerStats().getTotalGoldCollected());
		add(totalGoldLbl, "cell 1 6,grow");
		
		JLabel totalManaStoneLbl = new StatisticLabel("Total ManaStone: " + GameInfo.getPlayerStats().getTotalManaStoneCollected());
		add(totalManaStoneLbl, "cell 1 7,grow");
		
		JLabel unitsKilled = new StatisticLabel("Units Killed: " + GameInfo.getPlayerStats().getUnitsKilled());
		add(unitsKilled, "cell 1 8,grow");
		
		JLabel unitsCreated = new StatisticLabel("Units Created: " + GameInfo.getPlayerStats().getUnitsCreated());
		add(unitsCreated, "cell 1 9,grow");
		
		JLabel buildingsDestroyed = new StatisticLabel("Buildings Destroyed: " + GameInfo.getPlayerStats().getBuildingsDestroyed());
		add(buildingsDestroyed, "cell 1 10,grow");
		
		JLabel buildingsBuilt = new StatisticLabel("Buildings Built: " + GameInfo.getPlayerStats().getBuildingsBuilt());
		add(buildingsBuilt, "cell 1 11,grow");
		
		JButton btnMainMenu = new JButton_01("Exit to Main Menu");
		btnMainMenu.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnMainMenu.addActionListener(ControlInput.menuChanger);
		add(btnMainMenu, "cell 1 13,grow");
		
		JButton btnExit = new JButton_01("Exit Game");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		add(btnExit, "cell 1 14,grow");

	}

}