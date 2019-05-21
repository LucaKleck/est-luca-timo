package frame.customPresets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame.graphics.ResourceManager;

public class CustomScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void configureScrollBarColors() {
			LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground");
			thumbHighlightColor = new Color(108, 71, 53);
			thumbLightShadowColor = new Color(108, 71, 53);
			thumbDarkShadowColor = new Color(108, 71, 53);
			thumbColor = new Color(239, 208, 160);
			
			trackColor = new Color(239, 208, 160);
			trackHighlightColor = new Color(239, 208, 160);
			
			maximumThumbSize = new Dimension( ((Dimension)UIManager.get("ScrollBar.maximumThumbSize")).width, 30);
		}

		@Override
		protected JButton createDecreaseButton(int orientation) {
			JButton btn = new JButton_02(orientation);
			btn.setOpaque(false);
			return btn;
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			JButton btn = new JButton_02(orientation);
			btn.setOpaque(false);
			return btn;
		}

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
	    {
	        g.setColor(trackColor);
	        g.drawImage(ResourceManager.getFrame_01_05(), trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);
	        g.drawImage(ResourceManager.getSlider_02_01(), trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);

	        if(trackHighlight == DECREASE_HIGHLIGHT)        {
	            paintDecreaseHighlight(g);
	        }
	        else if(trackHighlight == INCREASE_HIGHLIGHT)           {
	            paintIncreaseHighlight(g);
	        }
	    }
		
		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
	    {
	        if(thumbBounds.isEmpty() || !scrollbar.isEnabled())     {
	            return;
	        }

	        int w = thumbBounds.width;
	        int h = thumbBounds.height;

	        g.translate(thumbBounds.x, thumbBounds.y);

	        g.drawImage(ResourceManager.getSlider_01_01(), 0, 0, w, h, null);

	        g.translate(-thumbBounds.x, -thumbBounds.y);
	    }
}