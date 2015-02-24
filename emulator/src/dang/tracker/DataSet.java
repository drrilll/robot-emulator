package dang.tracker;



import java.io.*;
import java.util.*;

public class DataSet {

	private int width, height;

	public static int X_OFFSET = TrackingPanel.MAP_BORDER_SIZE;	// offsets for the data to allow readings outside 640x480 area so that
	public static int Y_OFFSET = TrackingPanel.MAP_BORDER_SIZE;

	public static int MAX_Y = 480;		// maximum y value in data

	public static double[] SIX_SIGMA_PROBS_1x = {0.0214f, 0.1359f, 0.3413f, 0.3413f, 0.1359f, 0.0214f};
	public static double[] SIX_SIGMA_PROBS_2x = {0.0049f, 0.0166f, 0.044f, 0.0919f, 0.1498f, 0.1915f,
												0.1915f, 0.1498f, 0.0919f, 0.044f, 0.0166f, 0.0049f};
	public static double[] SIX_SIGMA_PROBS_3x = {0.0022f, 0.0047f, 0.0097f, 0.018f, 0.0309f, 0.0483f, 0.069f, 0.0902f, 0.1078f, 0.1179f,
												0.1179f, 0.1078f, 0.0902f, 0.069f, 0.0483f, 0.0309f, 0.018f, 0.0097f, 0.0047f, 0.0022f};
	public static double[] SIX_SIGMA_PROBS_5x = {0.0013f, 0.0021f, 0.0035f, 0.0057f, 0.0089f,
												0.0131f, 0.0189f, 0.026f, 0.0343f, 0.0436f,
												0.0532f, 0.0624f, 0.0703f, 0.0761f, 0.0793f,
												0.0793f, 0.0761f, 0.0703f, 0.0624f, 0.0532f,
												0.0436f, 0.0343f, 0.026f, 0.0189f, 0.0131f,
												0.0089f, 0.0057f, 0.0035f, 0.0021f, 0.0013f};
	public static double[] SIX_SIGMA_PROBS_10x= {0.0006f, 0.0007f, 0.0009f, 0.0012f, 0.0015f, 0.0020f, 0.0025f, 0.0032f, 0.0040f, 0.0049f,
											 	0.0059f, 0.0072f, 0.0087f, 0.0102f, 0.0120f, 0.0140f, 0.0160f, 0.0183f, 0.0206f, 0.0230f,
											 	0.0254f, 0.0278f, 0.0301f, 0.0323f, 0.0342f, 0.0361f, 0.0375f, 0.0386f, 0.0395f, 0.0398f,
											 	0.0398f, 0.0395f, 0.0386f, 0.0375f, 0.0361f, 0.0342f, 0.0323f, 0.0301f, 0.0278f, 0.0254f,
											 	0.0230f, 0.0206f, 0.0183f, 0.0160f, 0.0140f, 0.0120f, 0.0102f, 0.0087f, 0.0072f, 0.0059f,
											 	0.0049f, 0.0040f, 0.0032f, 0.0025f, 0.0020f, 0.0015f, 0.0012f, 0.0009f, 0.0007f, 0.0006f};

	public static final int NUM_ANGLES = (int)(Math.PI*10000.0);
	public static final double SIN_LOOKUP[] = new double[NUM_ANGLES];
	public static final double COS_LOOKUP[] = new double[NUM_ANGLES];

	static {
		for (int i=0; i<NUM_ANGLES; i++) {
			double angle = i/1000.0;
			SIN_LOOKUP[i] = Math.sin(angle);
			COS_LOOKUP[i] = Math.sin(angle);
		}
	}


	String 				description;
	List<MapPose>		poses;
	List<Integer>		data;

	double				angleOffset;
	double				xOffset;
	double				yOffset;
	double 				distanceError;
	double				angularResolution;
	double 				weight;
	boolean				enabled;
	static boolean		incorporateShape;

	private File 		file;
	private Map 		latestMap;


	public DataSet(String desc, int w, int h) {
		description = desc;
		poses = new ArrayList<MapPose>();
		data = new ArrayList<Integer>();

		width = w;
		height = h;
		latestMap = new Map(width, height);
		weight = 50;
		enabled = true;
		incorporateShape = false;
	}

	public String getDescription() {
		return description;
	}

	public Map getLatestMap() {
		return latestMap;
	}


	public void setLatestMap(Map latestMap) {
		this.latestMap = latestMap;
	}

	public boolean addData(MapPose p, int d) {
		poses.add(p);
		data.add(d);
		return true;
	}

	public List<MapPose> getPoses() {
		return poses;
	}

	public List<Integer> getData() {
		return data;
	}

	// Load up the data sets from the given trace file and return an array of such DataSets
	// w and h are the width and height of the map
	public static DataSet[] loadSetsFromFile(File file, int w, int h) {
		// The trace file is expected to look like this:
		//
		// x,y,angle,DIRRS,SONAR, SET3, etc...
		// -1,-1,-1,0,0,5,11,4,13
		// 251,330,51,5,15,4,16
		// 257,323,46,4,13,4,12
		// -1,-1,-1,4,12,3,12,5,11
		// 274,300,49,5,10
		// ...
		// where -1,-1,-1 indicates that the pose was not available
		// and multiple groups of sensor data may appear on each line.
		// index is the index of the data required for this data set.
		// 0 is the DIRRS data and 1 is the SONAR data.  Data is in
		// CM, not in pixels.

		try {
			BufferedReader 		input = new BufferedReader(new FileReader(file));
			String 				nextLine;
			StringTokenizer 	wholeLine, config;

			DataSet[] dataSets = null;

			// Read the header and determine the number of data sets (assumes header is correct)
			nextLine = input.readLine();
			wholeLine = new StringTokenizer(nextLine,",");
			int numberOfDataSets = wholeLine.countTokens() - 3; 					// first three are (x,y,angle)
			wholeLine.nextToken(); wholeLine.nextToken(); wholeLine.nextToken();	// ignore (x,y,angle)

			// Create the necessary number of data sets
			dataSets = new DataSet[numberOfDataSets];
			for (int i=0; i<numberOfDataSets; i++) {
				// Now extract the description, angleOffset, distanceOffset, distanceError and angularResolution
				config = new StringTokenizer(wholeLine.nextToken(),"|");
				dataSets[i] = new DataSet(config.nextToken(), w, h);
				dataSets[i].angleOffset = Integer.parseInt(config.nextToken());			// degrees (i.e., facing left)
				dataSets[i].xOffset = Float.parseFloat(config.nextToken())*3;			// convert from cm to pixels
				dataSets[i].yOffset = Float.parseFloat(config.nextToken())*3;			// convert from cm to pixels
				dataSets[i].distanceError = Float.parseFloat(config.nextToken());		// +- e%
				dataSets[i].angularResolution = Float.parseFloat(config.nextToken());   // +- a degrees
			}

			// Now read in the data, line by line
			MapPose p;
			while ((nextLine = input.readLine()) != null) {
				wholeLine = new StringTokenizer(nextLine,",");
				p = new MapPose();
				p.setX(Integer.parseInt(wholeLine.nextToken()) + X_OFFSET);
				p.setY(Integer.parseInt(wholeLine.nextToken()) + Y_OFFSET);
				p.setDegrees(Float.parseFloat(wholeLine.nextToken()));

				if (wholeLine.hasMoreTokens() && (p.getX() >= X_OFFSET)) {
					do {
						for (int i=0; i<numberOfDataSets; i++) {
							int value = Integer.parseInt(wholeLine.nextToken());
							if (value > 0)
								dataSets[i].addData(p, value);
						}
					} while(wholeLine.hasMoreTokens());
				}
			}
			return dataSets;
		}
		catch (Exception e) {
			System.out.println("Error reading trace file");
			return null;
		}
	}

	public int getDataSize() {
		return data.size();
	}

	private int getAngleIndex(double radians) {
		int index = (int)(radians * 1000.0);
		while (index < 0) index += NUM_ANGLES;
		while (index >= NUM_ANGLES) index -= NUM_ANGLES;
		return index;
	}

	public void applyToGrid(boolean useAngularGaussian, boolean useDistanceGaussian, int resolution, boolean rawOnly, ProgressMonitor progress) {

		OccupancyGrid   tempGrid = new OccupancyGrid(width, height);
		double angProb=1, distProb=1;

		latestMap.clear();

		Iterator<MapPose> i;
		Iterator<Integer> j;
		for (i=poses.iterator(), j=data.iterator(); i.hasNext() && j.hasNext();) {

			MapPose p = i.next();
			tempGrid.clear();

			// Blank out the area around the robot
			if (incorporateShape)
				for (int x=-18; x<=18; x++) {
					for (int y=-15; y<=15; y++) {
						tempGrid.setCell((int)p.getX() + (int)(x*Math.cos(p.getRadians()) - y*Math.sin(p.getRadians())),
										 (int)p.getY() + (int)(x*Math.sin(p.getRadians()) + y*Math.cos(p.getRadians())), 0);
					}
				}

			// Now plot the data for this poase
			double reading = j.next();
			double 	range = reading * 3;	// 3 indicates 3 pixels per cm from our GPS data

			double dError = 0.01f;
			double aRes = 0.5f;
			if (!rawOnly) {
				dError = distanceError;
				aRes = angularResolution;
			}
			if (!useAngularGaussian && !useDistanceGaussian) {
				for (double r=-1*range*dError; r<=range*dError; r+=0.5) {
					int sX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset - aRes)) * (range+r));
					int sY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset - aRes)) * (range+r));
					int dX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset + aRes)) * (range+r));
					int dY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset + aRes)) * (range+r));
					double angularInterval = aRes / (double)(Math.floor(Math.sqrt((sX-dX)*(sX-dX) + (sY-dY)*(sY-dY))));

					for (double a=-1*aRes; a<=aRes; a+=angularInterval) {
						int objX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset + a)) * (range+r));
						int objY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset + a)) * (range+r));
						objX += (int)(xOffset*Math.cos(p.getRadians()) - yOffset*Math.sin(p.getRadians()));
						objY += (int)(xOffset*Math.sin(p.getRadians()) + yOffset*Math.cos(p.getRadians()));
						if ((objX >= 0) && (objY >= 0)) {
							tempGrid.setCell(objX,objY, 0);//angProb*distProb/2.0-0.5);
						}
					}
				}
			}
			else {
				for (double r=-1*range; r<=range*distanceError; r+=0.5) {
					int sX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset - angularResolution)) * (range+r));
					int sY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset - angularResolution)) * (range+r));
					int dX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset + angularResolution)) * (range+r));
					int dY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset + angularResolution)) * (range+r));
					double angularInterval = angularResolution / (double)(Math.floor(Math.sqrt((sX-dX)*(sX-dX) + (sY-dY)*(sY-dY))));

					if (useDistanceGaussian) {
						if (r<(-2*range*distanceError)) {
							distProb = -1;
						}
						else {
							if (r<(-1*range*distanceError)) {
								distProb = (r+range*distanceError)/(range*distanceError);
								//distProb = Math.pow(distProb, 3);
								//distProb = -1*2*SIX_SIGMA_PROBS_10x[(int)(((-1*r)/range)* 100/2 / 1.667f)]/0.0398;
							}
							else {
								switch(resolution) {
									case 0: distProb = SIX_SIGMA_PROBS_1x[(int)((r + range*distanceError)/(double)(range*distanceError) / 2 * 100 / 16.67f)];break;
									case 1: distProb = SIX_SIGMA_PROBS_2x[(int)((r + range*distanceError)/(double)(range*distanceError) / 2 * 100 / 8.34f)];break;
									case 2: distProb = SIX_SIGMA_PROBS_3x[(int)((r + range*distanceError)/(double)(range*distanceError) / 2 * 100 / 5.56f)];break;
									case 3: distProb = SIX_SIGMA_PROBS_5x[(int)((r + range*distanceError)/(double)(range*distanceError) / 2 * 100 / 3.34f)];break;
									case 4: distProb = SIX_SIGMA_PROBS_10x[(int)((r + range*distanceError)/(double)(range*distanceError) / 2 * 100 / 1.67f)];break;
								}
							}
						}
					}
					else {
						if (r<(-2*range*distanceError)) {
							distProb = -1;
						}
					}

					for (double a=-1*angularResolution; a<=angularResolution; a+=angularInterval) {
						int objX = (int)(p.getX() + Math.cos(p.getRadians() + Math.toRadians(angleOffset + a)) * (range+r));
						int objY = (int)(p.getY() + Math.sin(p.getRadians() + Math.toRadians(angleOffset + a)) * (range+r));

						objX += (int)(xOffset*Math.cos(p.getRadians()) - yOffset*Math.sin(p.getRadians()));
						objY += (int)(xOffset*Math.sin(p.getRadians()) + yOffset*Math.cos(p.getRadians()));


						if ((objX >= 0) && (objY >= 0)) {
							if (useAngularGaussian) {
								switch(resolution) {
									case 0: angProb = SIX_SIGMA_PROBS_1x[(int)((a + angularResolution)/(double)angularResolution / 2 * 100 / 16.67f)];break;
									case 1: angProb = SIX_SIGMA_PROBS_2x[(int)((a + angularResolution)/(double)angularResolution / 2 * 100 / 8.34f)];break;
									case 2: angProb = SIX_SIGMA_PROBS_3x[(int)((a + angularResolution)/(double)angularResolution / 2 * 100 / 5.56f)];break;
									case 3: angProb = SIX_SIGMA_PROBS_5x[(int)((a + angularResolution)/(double)angularResolution / 2 * 100 / 3.34f)];break;
									case 4: angProb = SIX_SIGMA_PROBS_10x[(int)((a + angularResolution)/(double)angularResolution / 2 * 100 / 1.67f)];break;
								}
							}


							tempGrid.setCell(objX, objY, angProb*distProb/2.0+0.5);

						}
					}
				}
			}

			latestMap.fuseWith(tempGrid);
			progress.advance();
		}
	}

}