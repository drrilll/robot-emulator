package dang.tracker;



import java.io.File;

public class ClassFileFilter extends javax.swing.filechooser.FileFilter {
	// Returns the description of the file filters.
	public String getDescription() {
		return "Java Class File";
	}

	// Determine whether the given file is allowed in the filter
	public boolean accept(File f) {
		if (f.isDirectory())
			return true;
		String extension = getExtension(f);
		return (extension != null && extension.equals("class"));
	}

	// Return the extension of the given file.
	private String getExtension(File f) {
		String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

    	if (i > 0 &&  i < s.length() - 1)
	    	ext = s.substring(i+1).toLowerCase();
	    return ext;
	}
}