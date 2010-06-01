package de.mxro.image;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.jhlabs.image.RaysFilter;
import com.jhlabs.image.ShadowFilter;

import de.mxro.utils.Utils;

public class ToolBarButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128263532362850145L;

	public ToolBarButton(Action a) {
		this(a, false);
	}
	
	public ToolBarButton(Action a, boolean addShadow) {
		super(a);
		this.setVerticalAlignment(SwingConstants.BOTTOM);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setText((String) a.getValue(Action.NAME));
		this.setPreferredSize(new Dimension(80, 80));
		this.setMinimumSize(new Dimension(80, 80));
		this.setSize(new Dimension(80, 80));
		this.setBorderPainted(false);
		
		
		BufferedImage source;
		if (this.getIcon() instanceof ImageIcon) {
			if (addShadow ) {
				final BufferedImage sourceImage = Utils.toBufferedImage(((ImageIcon)this.getIcon()).getImage().getScaledInstance(this.getIcon().getIconWidth()-5, this.getIcon().getIconWidth()-5, Image.SCALE_SMOOTH));
				//System.out.println("source image " +sourceImage.getType());
				BufferedImage destImage = new BufferedImage(this.getIcon().getIconWidth(), this.getIcon().getIconHeight(), sourceImage.getType());
				destImage = new ShadowFilter().filter(sourceImage, destImage);
				
				source = Utils.toBufferedImage(destImage);
				this.setIcon(new ImageIcon(destImage));
				
				//this.setPressedIcon(new ImageIcon(GrayFilter.createDisabledImage(source)));
				
			} else {
				source = Utils.toBufferedImage(((ImageIcon)this.getIcon()).getImage());
				
			}
			final BufferedImage dest = new BufferedImage(this.getIcon().getIconWidth(), this.getIcon().getIconHeight(), source.getType());
			
			this.setPressedIcon(new ImageIcon(new RaysFilter().filter(source, dest)));
		}
		
		
		
		
			
	}
	
}
