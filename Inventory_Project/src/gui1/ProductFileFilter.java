package gui1;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ProductFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		
	if (f.isDirectory()) {
		return true;
	}	
	String name = f.getName();
	String extension= Utils.getFileExtension(name);
	if (extension==null) {
		return false;
	}if (extension.equals("per")) {
		return true;
	}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Person database files (*.per)";
	}

}
