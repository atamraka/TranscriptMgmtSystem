package main.uno.edu.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.uno.edu.controller.MainMenuController;

public class MainApplicationMenu extends JMenuBar {
	JMenu file_menu;

	public MainApplicationMenu(MainMenuController mCtrl) {
		file_menu = new JMenu("File");
		this.add(file_menu);

		JMenuItem open_msubmenu = new JMenuItem("Open Media");
		open_msubmenu.addActionListener(mCtrl);
		file_menu.add(open_msubmenu);
		
		JMenuItem open_fsubmenu = new JMenuItem("Open Transcript");
		open_fsubmenu.addActionListener(mCtrl);
		file_menu.add(open_fsubmenu);

		JMenuItem exit_submenu = new JMenuItem("Exit");
		exit_submenu.addActionListener(mCtrl);
		file_menu.add(exit_submenu);
	}

}
