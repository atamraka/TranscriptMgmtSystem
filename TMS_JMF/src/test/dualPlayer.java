package test;

import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;

import com.sun.media.ui.MessageBox;

public class dualPlayer extends Frame {

	JMFrame jmframe = null;
	JDesktopPane desktop;
	FileDialog fd = null;
	CheckboxMenuItem cbAutoLoop = null;
	Player player = null;
	Player newPlayer = null;
	String filename;

	public dualPlayer() {
		super("Java Media Player");

		// Add the desktop pane
		setLayout(new BorderLayout());
		desktop = new JDesktopPane();
//		desktop.setDoubleBuffered(true);
		add("Center", desktop);
		setMenuBar(createMenuBar());
		setSize(640, 480);
		setVisible(true);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			System.err.println("Could not initialize java.awt Metal lnf");
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
	}

	private MenuBar createMenuBar() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String command = ae.getActionCommand();
				if (command.equals("Open")) {
					if (fd == null) {
						fd = new FileDialog(dualPlayer.this, "Open File",
								FileDialog.LOAD);
						fd.setDirectory("/movies");
					}
					fd.show();
					if (fd.getFile() != null) {
						String filename = fd.getDirectory() + fd.getFile();
						openFile("file:" + filename);
					}
				} else if (command.equals("Exit")) {
					dispose();
					System.exit(0);
				}
			}
		};

		MenuItem item;
		MenuBar mb = new MenuBar();
		// File Menu
		Menu mnFile = new Menu("File");
		mnFile.add(item = new MenuItem("Open"));
		item.addActionListener(al);
		mnFile.add(item = new MenuItem("Exit"));
		item.addActionListener(al);

		// Options Menu
		Menu mnOptions = new Menu("Options");
		cbAutoLoop = new CheckboxMenuItem("Auto replay");
		cbAutoLoop.setState(true);
		mnOptions.add(cbAutoLoop);

		mb.add(mnFile);
		mb.add(mnOptions);
		return mb;
	}

	/**
	 * Open a media file.
	 */
	public void openFile(String filename) {
		String mediaFile = filename;
		Player player = null;
		// URL for our media file
		URL url = null;
		try {
			// Create an url from the file name and the url to the
			// document containing this applet.
			if ((url = new URL(mediaFile)) == null) {
				Fatal("Can't build URL for " + mediaFile);
				return;
			}

			// Create an instance of a player for this media
			try {
				player = Manager.createPlayer(url);
				
			} catch (NoPlayerException e) {
				Fatal("Error: " + e);
			}
		} catch (MalformedURLException e) {
			Fatal("Error:" + e);
		} catch (IOException e) {
			Fatal("Error:" + e);
		}
		if (player != null) {
			this.filename = filename;
			
//			System.out.println("player duration==>  "+player.getDuration().getNanoseconds());
			System.out.println(player.getDuration().getClass());
			JMFrame jmframe = new JMFrame(player, filename);
			
			desktop.add(jmframe);
		}
	}

	static void Fatal(String s) {
		MessageBox mb = new MessageBox("JMF Error", s);
	}

	public static void main(String args[]) {
		dualPlayer mdi = new dualPlayer();
	}

}
