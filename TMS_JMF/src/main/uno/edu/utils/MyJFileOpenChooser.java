package main.uno.edu.utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class MyJFileOpenChooser extends JFileChooser {
	public MyJFileOpenChooser(String description) {
		super.setFileFilter(new myFileFilter(description));
	}
}

class myFileFilter extends FileFilter {
	String description = "";

	public myFileFilter(String desc) {
		this.description = desc;
	}

	@Override
	public boolean accept(File arg0) {
		return (arg0.isDirectory() || arg0.getName().endsWith(this.description));
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
