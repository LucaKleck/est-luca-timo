package core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInput {
	public static ActionListener menuChanger;
	
	public ControlInput() {
		menuChanger = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
					try {
						CoreController.mainJFrame.getContentPane().removeAll();
						CoreController.mainJFrame.getContentPane().add( (Component) ClassLoader.getSystemClassLoader().loadClass(evt.getActionCommand()).newInstance() );
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					CoreController.mainJFrame.validate();
			}
		
		};
	}

}