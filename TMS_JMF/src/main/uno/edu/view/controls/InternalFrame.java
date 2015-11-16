package main.uno.edu.view.controls;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame {

	protected JDesktopPane draw_surface;

	public InternalFrame() {
		
		this.setClosable(true);
		this.setMaximizable(true);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(600, 500));
		this.setSize(600, 500);

		this.setVisible(true);
//		this.setLayout(new GridBagLayout());
	}
}
