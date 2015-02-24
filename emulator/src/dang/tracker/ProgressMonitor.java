package dang.tracker;


public interface ProgressMonitor {
	public abstract void advance();
	public abstract void advance(int n);
}
