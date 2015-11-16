package main.uno.edu.view.controls;

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.uno.edu.model.MessageBundle;

public class Notification extends JPanel {


	JLabel msg;
	Icon error_icon;


	public Notification() {

		msg = new JLabel();
		msg.setForeground(Color.RED);
		msg.setVerticalTextPosition(JLabel.CENTER);
		msg.setHorizontalAlignment(JLabel.LEFT);
		error_icon = createImageIcon("/resources/icons/error.png", "Error");

		this.add(msg);
	}
	
	public void showErrorMessage(String msg_){
		msg.setText(msg_);
		msg.setIcon(error_icon);
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
