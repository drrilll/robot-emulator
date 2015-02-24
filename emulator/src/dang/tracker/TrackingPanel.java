package dang.tracker;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TrackingPanel extends PlotArea implements ProgressMonitor {

	// Parameters for valid objects
	static int MIN_COUNT = 55;
	static int MAX_COUNT = 70;
	static int MIN_WIDTH = 20;
	static int MAX_WIDTH = 25;
	static int MIN_HEIGHT = 20;
	static int MAX_HEIGHT = 28;
	static double MIN_RATIO = 0.9;
	static double MAX_RATIO = 1.1;
	static int MAP_BORDER_SIZE = 115;

	// Default colours
	static int WHITE_PIXEL = new Color(255, 255, 255).getRGB();
	static int BLACK_PIXEL = new Color(0, 0, 0).getRGB();
	static int RED_PIXEL = new Color(255, 0, 0).getRGB();
	static int BLUE_PIXEL = new Color(0, 0, 255).getRGB();
	static int GREEN_PIXEL = new Color(0, 255, 0).getRGB();
	static int CYAN_PIXEL = new Color(0, 255, 255).getRGB();
	static int MAGENTA_PIXEL = new Color(255, 0, 255).getRGB();

	// Image properties
	static int IMAGE_WIDTH = RobotTracker.CAMERA_WIDTH;
	static int IMAGE_HEIGHT = RobotTracker.CAMERA_HEIGHT;
	static int WIDTH = MAP_BORDER_SIZE*2 + IMAGE_WIDTH+1;
	static int HEIGHT = MAP_BORDER_SIZE*2 + IMAGE_HEIGHT+1;

	/*private BufferedImage colorImage;		// Original image capture
	private BufferedImage colorImageMod;	// Image with markers added
	private BufferedImage binImage;			// Generated image marking black pixels
	private BufferedImage binImageMod;		// Binary image with markers added
	private BufferedImage edgeImage;	*/	// Generated image marking which border pixels have been checked already
	public  BufferedImage currentImage;		// Contains the image to be displayed
	public  BufferedImage mapImage;			// Contains the map to be displayed

	private Border borders;			// All objects which are considered valid
	//private Vector<Border> rejectedBorders;	// Border objects which are not considered valid

	public Vector<Point> desiredPath;		// Contains the path created by the user
	public Vector<Point> estimatedPath;		// Path received from RCMS
	public Vector<Point> actualPath;		// Path of the currently tracked robot

	public int sum = 525;					// Threshold for sum of RGB values
	public int diff = 43;					// Threshold for maximum difference of RGB values
	public int trackID = 0;					// ID of robot being tracked (for path)

	public boolean 	hideImage = false;						// If user wants to hide the image
	public boolean 	calibrating = false;					// If currently in calibration mode
	public boolean 	plotting = false;						// If plotting of desired path is enabled
	public boolean 	showTags = false;						// If tags should be displayed
	public boolean 	showDesiredPath = true;					// If desired path should be displayed
	public boolean 	showActualPath = false;					// If actual (tracked) path should be displayed
	public boolean 	showEstimatedPath = false;				// If estimated (from RCMS) path should be displayed
	//public boolean 	showIRSensors = true;					// If IRSensors should be shown
	public Color	actualPathColor = Color.BLACK;			// Color for actual path (user adjustable)
	public Color	desiredPathColor = new Color(0,150,0);	// Color for user-defined path (user adjustable)
	public Color	estimatePathColor = new Color(150,0,0);	// Color for estimated path (user adjustable)
	public Color	tagColor = new Color(255,0,0);			// Color for robot tags (user adjustable)
	public boolean  tagColorIsDark = false;					// indicates whether or not the tag color is dark or bright

	public Pose[] posesFound; 		// Last pose found of the robot

	protected int xOffset=0, yOffset=0;		// Offsets to be used when this tracker is part of a combined network of trackers

	private RobotTracker rt;				// Parent RobotTracker


	// These are used for mapping (to monitor the progress and display the map)
	private int 			progress = 100;
	private int 			progressPercentage = 100;
	private int 			maxProgress = 100;
	private String 			progressMessage = "";
	public  Map				grid;

	public TrackingPanel (RobotTracker owner) {
		super(WIDTH, HEIGHT);
		rt = owner;
		grid = new Map(WIDTH, HEIGHT);

		//The simulator doesn't process any images, it can grab location straight
		//from the simulator, so it doesn't need all these
		// Initialize images
	/*	binImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
		binImageMod = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		colorImageMod = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		edgeImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);*/
		mapImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		currentImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
//TODO
		borders = new Border(rt.getRobot());
		//rejectedBorders = new Vector<Border>();

		desiredPath = new Vector<Point>();
		estimatedPath = new Vector<Point>();
		actualPath = new Vector<Point>();
		
		posesFound = new Pose[8];
		for (int i = 0;i <8; i++){
			posesFound[i] = null;
		}
		

	/*	try {
			currentImage = null;//ImageIO.read(new File(RobotTracker.INSTALL_DIRECTORY + "TrackerImage.png"));
		}
		catch (Exception e){
			currentImage = null;
		}*/
		

		// Set up a timer for the progress during map display
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				repaint();
			}
		}, 0, 50);

		addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent arg0) { }
			public void mouseMoved(MouseEvent e) {
				setMouseLoc(e.getPoint());
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				rt.mousePosition(new Point(e.getPoint().x, 480 + yOffset - e.getPoint().y));
				if (plotting){
					if (rt.mapping)
						desiredPath.add(new Point(e.getPoint().x-MAP_BORDER_SIZE, 480 + yOffset - e.getPoint().y+MAP_BORDER_SIZE));
					else
						desiredPath.add(new Point(e.getPoint().x, 480 + yOffset - e.getPoint().y));
					update();
				}
			}
		});

		setSize(MAP_BORDER_SIZE + IMAGE_WIDTH+1, MAP_BORDER_SIZE + IMAGE_HEIGHT+1);
		setPreferredSize(new java.awt.Dimension(MAP_BORDER_SIZE + IMAGE_WIDTH+1, MAP_BORDER_SIZE + IMAGE_HEIGHT+1));
		setMinimumSize(new java.awt.Dimension(MAP_BORDER_SIZE + IMAGE_WIDTH+1, MAP_BORDER_SIZE + IMAGE_HEIGHT+1));
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}

	// Tell the tracker to display the mouse location in the bottom right
	protected void setMouseLoc(Point p) {
		rt.setMouseLoc(p);
	}

	public Image getImage() {
		return currentImage;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//TODO
		//rt.getRobot().draw(g);
		if (hideImage) {
			if (showTags)
				if (rt.mapping)
					borders.draw(g, tagColor, tagColorIsDark, MAP_BORDER_SIZE,MAP_BORDER_SIZE);
				else
					borders.draw(g, tagColor, tagColorIsDark);
		}
		else {
			if (calibrating)
				setCurrentImage("bin");
			else
				setCurrentImage("color");
			if (rt.mapping)
				g.drawImage(currentImage,MAP_BORDER_SIZE,MAP_BORDER_SIZE,this);
			else
				g.drawImage(currentImage,0,0,this);
		}

		// If mapping, draw the map
		if (rt.mapping) {
			g.drawImage(mapImage, 0, 0, this);

			if (progressPercentage < 100) {
				Graphics2D g2d = (Graphics2D)g;

        		// Enable antialiasing for shapes and text
        		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

				// Show a shaded background while creating map
				g2d.setColor(new Color(0,0,0,0.50f));
				g2d.fillRect(0, 0, WIDTH, HEIGHT);

				// Show progress text
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("Arial",Font.BOLD,18));
				g2d.drawString((progress*100)/maxProgress + "% Completed", WIDTH/2-60,  HEIGHT/2-3);
				g2d.setFont(new Font("Arial",Font.PLAIN,12));
				g2d.drawString(progressMessage, WIDTH/2-60, HEIGHT/2+15);
			}
		}

		// Draw the necessary paths
		if (showDesiredPath)
			drawUserDefinedPath(g);
		if (showEstimatedPath)
			drawEstimatePath(g);
		if (showActualPath)
			drawActualPath(g);

		// Draw the outline of the image
		if (rt.mapping) {
			g.setColor(Color.BLACK);
        	g.drawRect(MAP_BORDER_SIZE,MAP_BORDER_SIZE,640,480);
        	g.setColor(Color.WHITE);
        	g.drawRect(MAP_BORDER_SIZE-1,MAP_BORDER_SIZE-1,642,482);
		}
	}


	// Draw the user-defined path with the given pen
	public void drawUserDefinedPath(Graphics g) {
		Point prev, next;
		int border = 0;
		if (rt.mapping) border = MAP_BORDER_SIZE;
		g.setColor(desiredPathColor);
		if (desiredPath.size() >= 1){
			prev = desiredPath.get(0);
			g.fillOval(border+prev.x-4, border+480 + yOffset - prev.y-4, 8, 8);
			for (int i=1; i<desiredPath.size(); i++) {
				next = desiredPath.get(i);
				// Flip the y around so that the bottom left is (0,0)
				g.fillOval(border+next.x-4, 480 + border+yOffset - next.y-4, 8, 8);
				// Flip the y around so that the bottom left is (0,0)
				g.drawLine(border+prev.x, border+480 + yOffset - prev.y, border+next.x, border+480 + yOffset - next.y);
				prev = next;
			}
		}
	}

	// Draw the actualPath path with the given pen
	public void drawActualPath(Graphics g) {
		Point prev, next;
		int border = 0;
		if (rt.mapping) border = MAP_BORDER_SIZE;
		g.setColor(actualPathColor);
		//System.out.format("******************************Actual Path %d*************\n", actualPath.size());
		//System.out.println("******************************Actual Path*************");
		if (actualPath.size() >= 1){
			prev = actualPath.get(0);
			g.fillOval(border+prev.x-4, border+prev.y-4, 8, 8);
			for (int i=1; i<actualPath.size(); i++) {
				next = actualPath.get(i);
				g.drawLine(border+prev.x, border+prev.y, border+next.x, border+next.y);
				prev = next;
			}
		}
	}

	// Draw the estimatePath path with the given pen
	public void drawEstimatePath(Graphics g) {
		Point prev, next;
		int border = 0;
		if (rt.mapping) border = MAP_BORDER_SIZE;
		g.setColor(estimatePathColor);
		if (estimatedPath.size() >= 1){
			prev = estimatedPath.get(0);
			g.fillOval(border+prev.x-4, border+480 + yOffset - prev.y-4, 8, 8);
			for (int i=1; i<estimatedPath.size(); i++) {
				next = estimatedPath.get(i);
				g.drawLine(border+prev.x, border+480 + yOffset - prev.y, border+next.x, border+480 + yOffset - next.y);
				prev = next;
			}
		}
	}
//TODO
	public void setCurrentImage(String img){
		/*if (img == "color") {
			if (showTags)
				currentImage = colorImageMod;
			else
				currentImage = colorImage;
		}
		else if (img == "bin") {
			if (showTags)
				currentImage = binImageMod;
			else
				currentImage = binImage;
		}*/
	}

	
	public void processImage(){
		//borders.clear();
		//borders.draw(colorImageMod.getGraphics(), tagColor, tagColorIsDark);
		//borders.draw(binImageMod.getGraphics(), tagColor, tagColorIsDark);
		TrackingPanel.fill(currentImage, Color.white.getRGB());
		Graphics g = currentImage.getGraphics();
		rt.getRobot().updatePosition();
		rt.getRobot().draw(g);
		rt.getRobot().getEnvironment().draw(g);
		if (showTags)
			borders.draw(g, tagColor, tagColorIsDark);
		g.dispose();
		int id = borders.getID();
		Point p = borders.getCenter();
		posesFound[id] = new Pose(p.x, 480 + yOffset - p.y, borders.getDirection());
		if (id == trackID){
			if (!actualPath.contains(p)){
				actualPath.add(p);
			}
		}
		repaint();
	}

	public int calcNewPosX(int x, int dir){
		int newX = x;
		boolean wall = false;
		if (dir == 3 || dir == 4 || dir == 5){
			if (x > 0) newX = x - 1;
			else wall = true;
		}
		else if (dir == 0 || dir == 1 || dir == 7){
			if (x < IMAGE_WIDTH) newX = x + 1;
			else wall = true;
		}

		if (wall) return -1;
		else return newX;
	}

	public int calcNewPosY(int y, int dir){
		int newY = y;
		boolean wall = false;
		if (dir == 1 || dir == 2 || dir == 3){
			if (y > 0) newY = y - 1;
			else wall = true;
		}
		else if (dir == 5 || dir == 6 || dir == 7){
			if (y < IMAGE_HEIGHT) newY = y + 1;
			else wall = true;
		}

		if (wall) return -1;
		else return newY;
	}

	private boolean withinLimits(Border aBorder){
		if (aBorder.size() >= MIN_COUNT && aBorder.size() <= MAX_COUNT
				&& aBorder.getWidth() <= MAX_WIDTH && aBorder.getWidth() >= MIN_WIDTH
				&& aBorder.getHeight()<= MAX_HEIGHT && aBorder.getHeight() >= MIN_HEIGHT
				&& aBorder.getRatio() <= MAX_RATIO && aBorder.getRatio() >= MIN_RATIO){
			return true;
		}
		return false;
	}

	// Fill an image with one colour
	public static BufferedImage fill(BufferedImage img, int color){
		for (int y=0; y<img.getHeight(); y++){
			for (int x=0; x<img.getWidth(); x++){
				img.setRGB(x, y, color);
			}
		}
		return img;
	}

	public Pose[] getPosesFound() {
		return posesFound;
	}

	public void update() {
		repaint();
	}

	public BufferedImage getModifiedImage() {
		//return colorImageMod;
		return currentImage;
	}

	public void addEstimatedPath(int x, int y){
		if (x >= 0) {
			if (!estimatedPath.contains(new Point (x,y))){
				estimatedPath.add(new Point (x,y));
				update();
			}
		}
	}

	public void removeLastDesiredPlot(){
		if (desiredPath.size() > 0){
			desiredPath.remove(desiredPath.size()-1);
		}
		update();
	}

	public void clearDesiredPath() {
		desiredPath.clear();
		update();
	}

	public void clearActualPath() {
		actualPath.clear();
		update();
	}

	public void clearEstimatedPath() {
		estimatedPath.clear();
		update();
	}

	public void changeTrackID(int id) {
		actualPath.clear();
		trackID = id;
		update();
	}

	public void setFrameRate(int fr){
		rt.setFrameRate(fr);
	}

	public int getFrameRate(){
		return rt.frameRate;
	}





	// These remaining methods are used by the progress monitor
	public synchronized void advance() {
		advance(1);
	}

	public synchronized void advance(int n) {
		int oldPercentage = progressPercentage;
		progress += n;
		progressPercentage = (progress * 100) / maxProgress;
		if (progressPercentage > oldPercentage) {
			repaint();
		}
	}
	public synchronized void setProgress(int progress) {
		this.progress = progress;
		repaint();
	}

	public synchronized void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
		repaint();
	}

	public synchronized void setProgressMessage(String progressMessage) {
		this.progressMessage = progressMessage;
		repaint();
	}

}
