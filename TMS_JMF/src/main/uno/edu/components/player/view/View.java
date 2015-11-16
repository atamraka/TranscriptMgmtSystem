package main.uno.edu.components.player.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.bean.playerbean.MediaPlayer;
import javax.swing.JDesktopPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.sun.media.ui.MessageBox;

import main.uno.edu.components.player.controller.Controller;
import main.uno.edu.view.controls.InternalFrame;

public class View extends InternalFrame implements ControllerListener {
	
	Controller ctrl;
	MediaPlayer mplayer=null;
	Component visual = null;
	Component control=null;
	int width;
	int height;
	int controlHeight = 30;
	int insetWidth = 10;
	int insetHeight = 30;

	public View(Controller ctrl, JDesktopPane draw_surface) {
		super();
		this.ctrl = ctrl;
		this.draw_surface = draw_surface;
		
//		this.setLayout(new GridBagLayout());
		createPlayer();
//		GridBagConstraints gbca= new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5,5,5,5),0,0);
//		add("Center", draw_surface);
//		this.add(mplayer,gbca);
		this.draw_surface.add(this);
//		setSize(640, 480);
		this.setVisible(true);
		
	}

	public void createPlayer() {
		String mediaFile = this.ctrl.FileChooser();
		
		if (mediaFile!=null) {
			createMediaPlayer("file:" +mediaFile);
			
			addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosing(InternalFrameEvent ife) {
					mplayer.stop();
					mplayer.deallocate(); // release any exclusive resources and
											// minimize its use of non-exclusive
											// resources
					mplayer.close();

				}
			});
			
		}
	}
	
	
	public void createMediaPlayer(String mediaFile){
		try {
			mplayer= new MediaPlayer();
			mplayer.setMediaLocation(mediaFile);
			
			mplayer.addControllerListener((ControllerListener) this);
			mplayer.realize();


		}catch (Exception e) {
			MessageBox mb = new MessageBox("JMF Error", "Error: " + e);
			e.printStackTrace();
		}
	}
	@Override
	public void controllerUpdate(ControllerEvent ce) {
		if (ce instanceof RealizeCompleteEvent) {
			mplayer.prefetch();
		} else if (ce instanceof PrefetchCompleteEvent) {
			if (visual != null)
				return;
			if ((visual = mplayer.getVisualComponent()) != null) {
				Dimension size = visual.getPreferredSize();
				width = size.width;
				height = size.height;
				getContentPane().add("Center", visual);
			} else {
				width = 320;
			}
			if ((control = mplayer.getControlPanelComponent()) != null) {
				controlHeight = control.getPreferredSize().height;
				getContentPane().add("South", control);
			}
			setSize(width + insetWidth, height + controlHeight + insetHeight);
			validate();
			System.out.println("here");
			mplayer.start();
		} else if (ce instanceof EndOfMediaEvent) {
			System.out.println("player duration==>  "
					+ mplayer.getDuration().getNanoseconds());
			System.out.println(mplayer.getTimeBase().toString());
			mplayer.setMediaTime(new Time(2000000000));// nanosecond precision
			mplayer.stop();
		}
	}


}
