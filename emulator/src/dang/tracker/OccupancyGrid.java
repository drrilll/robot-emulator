package dang.tracker;


import java.util.Arrays;

public class OccupancyGrid {
	//public static int WIDTH = 792;	// 800 pixels wide - 8 pixels for window frame
	//public static int HEIGHT = 550;	// 600 pixels high - 50 pixels for menu bar and frame
	public static int SCALE = 1;

	protected int width;
	protected int height;
	protected double	prob[][];		//probability of the grid cells being occupied

	// Bounding box
	private int minX = Integer.MAX_VALUE;
	private int minY = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private int maxY = Integer.MIN_VALUE;

	public OccupancyGrid(int width, int height) {
		this.width = width;
		this.height = height;
		prob = new double[width][height];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getCell(int x, int y) { return prob[x][y]; }

	public void incrementCell(int x, int y) {
		prob[x][y] += 1;
	}
	public void setCell(int x, int y, double newValue) {
		if ((x < width) && (x >= 0) && (y < height) && (y >= 0)) {
			prob[x][y] = newValue;

			if (x < minX) minX = x;
			if (y < minY) minY = y;
			if (x > maxX) maxX = x;
			if (y > maxY) maxY = y;
		}
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public void clear() {
		for (int x=0; x<width; x++)
			Arrays.fill(prob[x],0.5);
	}

	public double maxValue() {
		double maxValue = 0;

		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (prob[x][y] > maxValue)
					maxValue = prob[x][y];
		return maxValue;
	}
	public double minValue() {
		double minValue = 9999;

		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (prob[x][y] < minValue)
					minValue = prob[x][y];
		return minValue;
	}

	public void mergeGridIncrementally(OccupancyGrid g) {
		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (g.getCell(x,y) > 0) {
					prob[x][y] += g.getCell(x,y);
				}
	}
}