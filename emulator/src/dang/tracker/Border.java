package dang.tracker;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;

import dang.robot.Robot;

public class Border {

	private static int MARKER_RADIUS = 7;
	private static int DIRECTION_RADIUS = 4;
	private static int DIRECTION_ANGLE = 2;

	Vector<Point> points;		// Contains all points on the border
	Vector<Border> borders;		// Contains any subborders
	Vector<Point> pixels;		// Contains all pixels within the object

	// Markers for identifying the robot
	int[] markers = {0, 0, 0};

	private int minX, maxX, minY, maxY, direction;
	private Robot robot;

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Border(Robot robot){
		// Initialize instance variables
		this.robot = robot;
		points = new Vector<Point>();
		pixels = new Vector<Point>();
		borders = new Vector<Border>();

		minX = TrackingPanel.IMAGE_WIDTH;
		minY = TrackingPanel.IMAGE_HEIGHT;
		maxX = 0;
		maxY = 0;
	}

	public int getID(){
		return getRobot().getId();
		/*
		int rtn = markers[2];
		rtn += markers[1] * 2;
		rtn += markers[0] * 4;

		return rtn;*/
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getWidth(){
		return maxX - minX;
	}

	public int getHeight(){
		return maxY - minY;
	}

	public Point getCenter(){
		//Point center = new Point(minX + getWidth() / 2, minY + getHeight() / 2);
		Point center = new Point((int)robot.getX(), (int)robot.getY());
		return center;
	}

	public double getRatio(){
		return (double)getWidth()/getHeight();
	}

	public boolean add(Point p){
		if (p.x > maxX){
			maxX = p.x;
		}
		else if (p.x < minX){
			minX = p.x;
		}
		if (p.y > maxY){
			maxY = p.y;
		}
		else if (p.y < minY){
			minY = p.y;
		}
		return points.add(p);
	}

	public boolean addSubborder(Border b){
		if (!borders.contains(b)){
			return borders.add(b);
		}
		return false;
	}

	public void addPixel(int x,int y){
		pixels.add(new Point(x,y));
	}

	public boolean remove(Point p){
		return points.remove(p);
	}

	public void clear(){
	//points.clear();
	//	borders.clear();
	}

	public boolean contains(Point p){
		return points.contains(p);
	}

	public boolean isEmpty(){
		return points.isEmpty();
	}

	// Check if a border is contained within or equal to this border.
	public boolean isSubborder(Border b){
		if (b.getMaxX() <= this.getMaxX()
				&& b.getMinX() >= this.getMinX()
				&& b.getMaxY() <= this.getMaxY()
				&& b.getMinY() >= this.getMinY()){
			return true;
		}
		return false;
	}

	// Number of pixels on the border
	public int size(){
		return points.size();
	}

	// Object is completed, perform calculations (direction, markers, etc)
	public boolean finalize(BufferedImage img){

		// Checks within the bounding box and add any black pixels to the pixel list
		for (int x = minX; x <=maxX; x++){
			for (int y = minY; y <= maxY; y++){
				if (img.getRGB(x,y) == TrackingPanel.BLACK_PIXEL){
					addPixel(x,y);
				}
			}
		}

		double angle, x, y;
		int newX, newY;

		Vector<Integer> angles = new Vector<Integer>();

		// Check in a circle around the center with radius DIRECTION_RADIUS for any pixels to identify the direction marker
		for (int i = 0; i < 360/DIRECTION_ANGLE; i++){
			angle = DIRECTION_ANGLE * i;
			x = DIRECTION_RADIUS * Math.cos(Math.toRadians(angle));
			y = DIRECTION_RADIUS * Math.sin(Math.toRadians(angle));
			newX = getCenter().x + (int)Math.round(x);
			newY = getCenter().y - (int)Math.round(y);
			// If this pixel is considered part of the object, remember this angle
			if (pixels.contains(new Point(newX, newY))){
				angles.add(new Integer((int)angle));
			}
		}

		if (angles.size() > 0){
			int total = 0;

			// Check for boundary case (spanning the 360-0 degree border), and adjust.
			if (angles.get(angles.size() - 1) - angles.get(0) > 180){

				// Calculate average angle. If angle > 180, convert to negative angle.
				for (Integer a : angles){
					if (a > 180)
						total += (a - 360);
					else
						total += a;
				}

				direction = total/angles.size();

				if (direction < 0)
					direction += 360;
			}
			else {
				// Calculate average angle.
				for (Integer a : angles){
					total += a;
				}
				direction = total/angles.size();
			}
		}
		else {
			// Direction marker not found
			return false;
		}

		// Check in 90 degree increments (from direction marker) for ID markers.
		for (int i = 1; i < 4; i++){
			angle = direction + 90 * i;

			x = MARKER_RADIUS * Math.cos(Math.toRadians(angle));
			y = MARKER_RADIUS * Math.sin(Math.toRadians(angle));
			newX = getCenter().x + (int)Math.round(x);
			newY = getCenter().y - (int)Math.round(y);

			if (pixels.contains(new Point(newX, newY))){
				markers[i-1] = 1;
			}
			else {
				markers[i-1] = 0;
			}
		}
		return true;
	}

	public String toString(){

		String rtn = "Center: (" + getCenter().x + "," + getCenter().y + ") Radius: " + ((getHeight())/2);
		rtn += " Direction: " + direction;
		rtn += " Markers: ";

		for (int i = 0; i < 3; i++){
			rtn += markers[i];
		}
		rtn += "\n";
		rtn += "Size: " + size() + " Width: " + getWidth() + " Height: " + getHeight() + " Ratio: " + getRatio() + "\n";
		return rtn;
	}

	// Draws border, direction and ID markers onto the BufferedImage.
	public void draw(Graphics g, Color tagColor, boolean dark, int xOffset, int yOffset){
		int		x1, y1, x2, y2;
		int		drawRadius = 10;
		int s = drawRadius + 6;

		x1 = xOffset+getCenter().x + (int)Math.round(s/2.5 * Math.cos(Math.toRadians(getDirection()-0)));
		y1 = yOffset+getCenter().y - (int)Math.round(s/2.5 * Math.sin(Math.toRadians(getDirection())));
		x2 = xOffset+getCenter().x + (int)Math.round(s * Math.cos(Math.toRadians(getDirection())));
		y2 = yOffset+getCenter().y - (int)Math.round(s * Math.sin(Math.toRadians(getDirection())));

		// Draw boundary circle
		int thickness = 2;
		g.setColor(Color.WHITE);
		g.fillOval(xOffset+(int)getCenter().x-drawRadius-thickness*2, yOffset+(int)getCenter().y-drawRadius-thickness*2,
					drawRadius*2+thickness*4, drawRadius*2+thickness*4);
		g.setColor(Color.BLACK);
		g.fillOval(xOffset+(int)(getCenter().x-drawRadius-thickness*1), yOffset+(int)(getCenter().y-drawRadius-thickness*1),
					(int)(drawRadius*2+thickness*2), (int)(drawRadius*2+thickness*2));
		g.setColor(tagColor);
		g.fillOval(xOffset+(int)getCenter().x-drawRadius+1, yOffset+(int)getCenter().y-drawRadius+1,
					drawRadius*2-2, drawRadius*2-2);
		if (dark)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLACK);
		g.drawString("" + getID(), xOffset+getCenter().x-drawRadius/2+2, yOffset+getCenter().y+drawRadius-4);

		// Draw direction line
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}

	// Draws border, direction and ID markers onto the BufferedImage.
	public void draw(Graphics g, Color tagColor, boolean dark){
		draw(g, tagColor, dark, 0, 0);
	}


	public int getDirection() {
		//return direction, phase shifted to account for the flipped y axis
		//this is simulator specific code
		return 360-(int)Math.toDegrees(robot.getAngle());
	}

	// Returns true if both borders are on the same position or have the same ID.
	public boolean equals(Object b){
		if (b.getClass() == getClass()){
			if (((Border)b).getCenter() == getCenter()){
				System.out.println(((Border)b).getCenter() + " + " + getCenter());
				return true;
			}
			return false;
		}
		return false;
	}
}
