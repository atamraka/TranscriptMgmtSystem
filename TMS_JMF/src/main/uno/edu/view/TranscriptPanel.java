package main.uno.edu.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;

public class TranscriptPanel extends JPanel{
	
	public TranscriptPanel(JDesktopPane jdp){
		this.setLayout(new GridBagLayout());
        this.add(jdp, new GridBagConstraints(0,0,1,1,0.001,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0));
		
	}

}
