package dang.tracker;


import java.io.*;
import java.util.Arrays;

public class Map extends OccupancyGrid implements Serializable {
	private int		count[][];		//number of times cells were processed over time
	private double	mean[][];		//average probability of the cells over time
	private double	ssd[][];		//squared standard deviation of the cells' probabilities over time

	// Bounding box
	private int minX = Integer.MAX_VALUE;
	private int minY = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private int maxY = Integer.MIN_VALUE;

	public static boolean	COLOR = true;

	public Map(int width, int height) {
		super(width,height);
		mean = new double[width][height];
		ssd = new double[width][height];
		count = new int[width][height];
		clear();
	}

	public Map(Map map) {
		this(map.getWidth(),map.getHeight());
		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++) {
				prob[x][y]	= map.prob[x][y];
				mean[x][y]	= map.mean[x][y];
				ssd[x][y]	= map.ssd[x][y];
				count[x][y]	= map.count[x][y];
			}
	}

	public double getCellProb(int x, int y) { return prob[x][y]; }
	public double getCellSSD(int x, int y) { return ssd[x][y]; }
	public double getCellMean(int x, int y) { return mean[x][y]; }

	public void setCell(int x, int y, double newValue) {
		prob[x][y] = newValue;
		count[x][y]++;
		mean[x][y] = newValue;
		ssd[x][y] = 0;

		if (x < minX) minX = x;
		if (y < minY) minY = y;
		if (x > maxX) maxX = x;
		if (y > maxY) maxY = y;
	}

	public void clear() {
		for (int x=0; x<width; x++) {
			Arrays.fill(prob[x],0.5);
			Arrays.fill(mean[x],0.5);
			Arrays.fill(ssd[x],0);
			Arrays.fill(count[x],0);
		}

		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
	}

	public double maxSSDValue() {
		double maxValue = 0;

		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (ssd[x][y] > maxValue)
					maxValue = ssd[x][y];
		return maxValue;
	}

	public double maxMeanValue() {
		double maxValue = 0;

		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (mean[x][y] > maxValue)
					maxValue = mean[x][y];
		return maxValue;
	}

	public double maxCountValue() {
		double maxValue = 0;

		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				if (count[x][y] > maxValue)
					maxValue = count[x][y];
		return maxValue;
	}

	public void fuseWith(OccupancyGrid g) {
		for (int x=g.getMinX(); x<=g.getMaxX(); x++)
			for (int y=g.getMinY(); y<=g.getMaxY(); y++)
				if (g.getCell(x,y) != 0.5) {
					double newProb = g.getCell(x,y);
					double tmp = newProb * prob[x][y];
					prob[x][y] = tmp / (tmp + (1-newProb)*(1-prob[x][y]));
				}
	}

	public void weightedFuse(Map g, double oldWeight, double weight) {
		for (int x=0; x<width; x++)
			for (int y=0; y<height; y++)
				prob[x][y] = oldWeight * prob[x][y] + weight * g.getCell(x,y);
	}
}