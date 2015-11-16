package main.uno.edu.components.transcript.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;


import main.uno.edu.utils.MyJFileOpenChooser;

import main.uno.edu.components.transcript.view.*;
import main.uno.edu.view.MainApplicationWindow;
import main.uno.edu.view.controls.Notification;

public class Controller  implements ActionListener{
	View v;
	MainApplicationWindow appWin;
	MyJFileOpenChooser chooser = null;
	
	public Controller(MainApplicationWindow appWin) {
		this.appWin = appWin;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void notifyLaunch(JDesktopPane draw_surface) {
		if (v == null) {
			v = new View(this, draw_surface);
			v.toFront();
			try {
				v.setSelected(true);
			} catch (Exception ex) {
			}
		} else {
			try {
				v.setMaximum(false);
			} catch (PropertyVetoException pex) {
				pex.printStackTrace();
			}
		}
	}
	
	public String FileChooser(){
		if (chooser == null) {
			chooser = new MyJFileOpenChooser(".txt");
			chooser.setMultiSelectionEnabled(false);
			int i = chooser.showOpenDialog(this.appWin);
			if (i == JFileChooser.APPROVE_OPTION) {
				File file=chooser.getSelectedFile();
				if (file!=null){
					String filename = file.getAbsolutePath().trim();
					return filename;
				}
				
			}
			
		}	
		Notification notice=new Notification();
		notice.showErrorMessage("No files selected!!");
		return null;
	}
}
