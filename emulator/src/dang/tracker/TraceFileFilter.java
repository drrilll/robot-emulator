package dang.tracker;


import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TraceFileFilter extends FileFilter {
    //Accept all directories and all .trc files.
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;

        if (f.getName().endsWith(".trc"))
            return true;

        return false;
    }

    public String getDescription() {
        return "Robot Tracker Trace Files";
    }
}
