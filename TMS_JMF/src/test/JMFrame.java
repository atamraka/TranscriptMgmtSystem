package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.swing.JInternalFrame; 
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class JMFrame extends JInternalFrame implements ControllerListener {
	Player mplayer;
	Component visual = null;
	Component control = null;
	int videoWidth = 0;
	int videoHeight = 0;
	int controlHeight = 30;
	int insetWidth = 10;
	int insetHeight = 30;
	boolean firstTime = true;

	public JMFrame(Player player, String title) {
		super(title, true, true, true, true);
		getContentPane().setLayout(new BorderLayout()); 
		setSize(320, 10);
		setLocation(50, 50);
		setVisible(true);
		mplayer = player;
		

		mplayer.addControllerListener((ControllerListener) this);
		mplayer.realize();
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent ife) {
				mplayer.close();
			}
		});

	}

	public void controllerUpdate(ControllerEvent ce) {
		if (ce instanceof RealizeCompleteEvent) {
			mplayer.prefetch();
		} else if (ce instanceof PrefetchCompleteEvent) {
			if (visual != null)
				return;

			if ((visual = mplayer.getVisualComponent()) != null) {
				Dimension size = visual.getPreferredSize();
				videoWidth = size.width;
				videoHeight = size.height;
				getContentPane().add("Center", visual);
			} else
				videoWidth = 320;
			if ((control = mplayer.getControlPanelComponent()) != null) {
				controlHeight = control.getPreferredSize().height;
				getContentPane().add("South", control);
			}
			setSize(videoWidth + insetWidth, videoHeight + controlHeight
					+ insetHeight);
			validate();
			System.out.println("here");
			mplayer.start();
		} else if (ce instanceof EndOfMediaEvent) {
			System.out.println("player duration==>  "+mplayer.getDuration().getNanoseconds());
			System.out.println(mplayer.getTimeBase().toString());
			mplayer.setMediaTime(new Time(2000000000));//nanosecond precision
//			mplayer.start();
		}
	}
}
