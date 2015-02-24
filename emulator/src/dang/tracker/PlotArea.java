package dang.tracker;


import java.awt.Dimension;
import javax.swing.JPanel;

public class PlotArea extends JPanel {
	private Dimension 	minSize;

	public PlotArea(int xSize, int ySize){
		super();
		setBackground(java.awt.Color.LIGHT_GRAY);
		minSize = new Dimension(xSize, ySize);
	}

	public Dimension getMinimumSize() {
		return minSize;
	}

	public Dimension getPreferredSize() {
		return minSize;
	}

}
