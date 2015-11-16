package main.uno.edu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainMenuController implements ActionListener{
	MainApplicationController mCtrl;
	
	 public void setMainController(MainApplicationController mCtrl)
	    {
	        this.mCtrl = mCtrl;
	    }

	  
	@Override
	public void actionPerformed(ActionEvent ae) {
		mCtrl.dispatchMenuEvent(ae.getActionCommand());
		
	}

}
