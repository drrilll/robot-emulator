package dang.interpreter;

import java.io.File;
import java.net.URI;

/**
 * A class that basically tells me if the file is untitled or not.
 * @author darrylhill
 *
 */
public class SpinFile extends File {
	
	private boolean titled = true;

	public boolean isTitled() {
		return titled;
	}

	public void setTitled(boolean titled) {
		this.titled = titled;
	}

	public SpinFile(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	public SpinFile(String parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	public SpinFile(File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	public SpinFile(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
