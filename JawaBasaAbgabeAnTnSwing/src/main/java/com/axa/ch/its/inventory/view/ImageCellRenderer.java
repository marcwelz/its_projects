package com.axa.ch.its.inventory.view;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1195126813536465395L;

	public ImageCellRenderer() {
		super();
	}

	/**
	 * Aus dem Bild ein Icon machen, auf 60*60 verkleinern
	 */
	public void setValue(Object value) {
		if (value != null && value instanceof BufferedImage) {
			BufferedImage img = (BufferedImage) value;						
			
			setIcon(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
			
			setText(null);
		} else {
			setText("");
		}

	}
}
