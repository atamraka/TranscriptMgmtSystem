package main.uno.edu.components.player.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import main.uno.edu.view.controls.Notification;
import javax.swing.JDesktopPane;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.bean.playerbean.MediaPlayer;

import com.sun.media.ui.MessageBox;

import main.uno.edu.components.player.view.View;
import main.uno.edu.view.MainApplicationWindow;

public class Controller implements ActionListener {

	View v;
	MainApplicationWindow appWin;
	FileDialog fd = null;

	public Controller(MainApplicationWindow appWin) {
		this.appWin = appWin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
		if (fd == null) {
			fd = new FileDialog(this.appWin, "Open File",
					FileDialog.LOAD);
			fd.setDirectory("/movies");
		}
		fd.show();
		if (fd.getFile() != null) {
			String filename = fd.getDirectory() + fd.getFile();
			return filename;
		}
		Notification notice=new Notification();
		notice.showErrorMessage("Selected File cannot be opened!!");
		return null;
	}
	


}
