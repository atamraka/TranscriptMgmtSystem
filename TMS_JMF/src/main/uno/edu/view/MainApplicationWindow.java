package main.uno.edu.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.Manager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.uno.edu.components.transcript.controller.Controller;
import main.uno.edu.controller.MainApplicationController;
import main.uno.edu.controller.MainMenuController;

public class MainApplicationWindow extends JFrame {
	
	private JDesktopPane jdp;
	MainApplicationMenu mbar;
	MainMenuController ctrl;
	PlayerPanel ppnl;
	TranscriptPanel tpnl;

	public void setWindowTitle(String title) {
		this.setTitle(title);
	}

	public MainApplicationWindow(MainApplicationController mCtrl) {
		
		ctrl=new MainMenuController();
		ctrl.setMainController(mCtrl);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		jdp = new JDesktopPane();
		mbar = new MainApplicationMenu(this.ctrl);
		setJMenuBar(mbar);
		this.getContentPane().setLayout(new GridBagLayout());
		
		ppnl=new PlayerPanel(jdp);
		tpnl=new TranscriptPanel(jdp);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,ppnl, tpnl );
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
//        this.getContentPane().add(jdp, new GridBagConstraints(0,0,1,1,1.0,0.01,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        this.getContentPane().add(splitPane ,new GridBagConstraints(0,1,0,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,5,5,5),0,0));
		setMaxWindowSize();
		
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
		SwingUtilities.updateComponentTreeUI(this);
		setVisible(true);
	}
	
	public void setMaxWindowSize(){
		this.setExtendedState(MAXIMIZED_BOTH);
	}
	
	private main.uno.edu.components.player.controller.Controller p_ctrl;
	public void showMediaPlayer(){
		
		if (p_ctrl==null){
		
			p_ctrl=new main.uno.edu.components.player.controller.Controller(this);
		}
		p_ctrl.notifyLaunch(jdp);
		p_ctrl=null;
	}
	
	private main.uno.edu.components.transcript.controller.Controller t_ctrl;
	public void showTranscript(){
		if (t_ctrl==null){
			t_ctrl= new Controller(this);
		}
		t_ctrl.notifyLaunch(jdp);
		t_ctrl=null;
	}
	


}
