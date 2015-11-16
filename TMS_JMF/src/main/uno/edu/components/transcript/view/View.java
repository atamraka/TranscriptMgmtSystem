package main.uno.edu.components.transcript.view;

import java.awt.Container;
import java.io.File;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.swing.JDesktopPane;

import main.uno.edu.components.transcript.controller.Controller;
import main.uno.edu.utils.FileOperations;
import main.uno.edu.view.controls.InternalFrame;

public class View extends InternalFrame implements ControllerListener {
	Controller ctrl;
	myTextArea transcript = null;

	public View(Controller ctrl, JDesktopPane draw_surface) {
		super();
		this.ctrl = ctrl;
		this.draw_surface = draw_surface;
		String transcriptText;
		String filechoosed = this.ctrl.FileChooser();
		if (filechoosed != null) {
			System.out.println(filechoosed);
			if ((transcriptText = FileOperations.getFileContent(new File(
					filechoosed))) != null)
				transcript = new myTextArea(transcriptText);
			Container pane = getContentPane();

			pane.add("South", transcript);
			pane.update(getGraphics());
			this.draw_surface.add(this);
			this.setVisible(true);
		}

	}

	@Override
	public void controllerUpdate(ControllerEvent arg0) {
		// TODO Auto-generated method stub

	}

}
