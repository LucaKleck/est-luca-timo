package core.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.Core;

public class ToggleBooleanSettingAction implements ActionListener {
	private String setting = "";
	public ToggleBooleanSettingAction(String setting) {
		this.setting = setting;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Boolean b = new Boolean(Core.loadSetting(setting));
			b = !b;
			Core.saveSetting(setting, b.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
