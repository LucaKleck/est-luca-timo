/**  
* MapPanel.java
* @author Luca Kleck
* @version 0.01 
* @since 0.01
* @see MainGamePanel
*/
package frame.gamePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import map.MapImage;

public class MapPanel extends JPanel implements ImageObserver {
	private static final long serialVersionUID = 121L;
	private static MapImage mapImage = new MapImage(300, 300);
	private int displacementMultiplier = 1;
	private int displacementX;
	private int displacementY;
	private boolean startDisplacement = false;
	
	public MapPanel() {
		setBackground(new Color(0,0,0,0));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// displacement
		if(startDisplacement == false) {
			displacementX = -(this.getWidth()/2);
			displacementY = -(this.getHeight()/2);
			startDisplacement = true;
		}
		
		// draw map image with displacement & multiplier
		g.drawImage(mapImage, (int) ((this.getWidth()/2)+displacementX), (int) ((this.getHeight()/2)+displacementY), this.getWidth()*displacementMultiplier, this.getWidth()*displacementMultiplier, this);
	}
	public void addDisplacementX(int displacementX) {
		this.displacementX += displacementX;
	}
	public void addDisplacementY(int displacementY) {
		this.displacementY += displacementY;
	}

}
