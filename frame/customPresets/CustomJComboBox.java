package frame.customPresets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import core.ResourceManager;

public class CustomJComboBox<E> extends JComboBox<E> {
	private static final long serialVersionUID = 7437563077534672953L;
	private static final Color bg = new Color(182, 137, 100);

	public CustomJComboBox(E[] items) {
		super(items);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setForeground(Color.YELLOW);
		setRenderer(new CustomListCellRenderer());
		setUI(new CustomUI());
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(ResourceManager.getText_bg_02(), 0, 0, getWidth(), getHeight(), null);
		super.paint(g);
	}
	
	private static class CustomListCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = -313762779398269769L;
		
		public CustomListCellRenderer() {
			setBackground(bg);
	        setForeground(Color.YELLOW);			
	        setOpaque(false);
		}
		
		@Override
		public void paint(Graphics g) {
			g.drawImage(ResourceManager.getText_bg_02(), 0, 0, getWidth(), getHeight(), null);
	        super.paint(g);
	    }
	}
	
	private static class CustomUI extends BasicComboBoxUI {
		public CustomUI() {
		}
		
		protected JButton createArrowButton() {
	        return new JButton_02(JButton_02.DOWN);
	    }
		
		@Override
        protected ComboPopup createPopup() {
            return new BasicComboPopup(comboBox) {
				private static final long serialVersionUID = 8644545914106913248L;

				@Override
                protected JScrollPane createScroller() {
                    JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    list.setBackground(bg);
                    scroller.getVerticalScrollBar().setUI(new CustomScrollBarUI());
                    return scroller;
                }
            };
        }
	}
}
