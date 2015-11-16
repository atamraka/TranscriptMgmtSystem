package main.uno.edu.controller;

import main.uno.edu.view.MainApplicationWindow;

public class MainApplicationController implements
		MainApplicationMenuEventListener {

	private MainApplicationWindow mView;

	public void showWindow() {
		mView.setVisible(true);
	}

	@Override
	public void dispatchMenuEvent(String command) {
		mView.setTitle("Transcript Management System");
		
		if(command.equals("Exit"))
        {
           mView.dispose();
        }else if (command.equals("Open Media")){
        	mView.showMediaPlayer();
        }else if (command.equals("Open Transcript")){
        	mView.showTranscript();
        }
		
		
		
	}
	
	public void setMainView(MainApplicationWindow mView)
    {
        this.mView = mView;
    }
}
