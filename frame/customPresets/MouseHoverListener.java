package frame.customPresets;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHoverListener implements MouseListener {

	private Component attachedComponent;
	public MouseHoverListener(Component component) {
		attachedComponent = component;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		attachedComponent.setBackground(new Color(255, 255, 255, 120));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		attachedComponent.setBackground(new Color(0, 0, 0, 0));
	}

}
