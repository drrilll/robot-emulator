package dang.robot;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

import dang.program.Debug;
import dang.program.objects.CMUCam;
import dang.tracker.objects.Environment;

public class CameraReading {
	
	int red, blue, green;
	/*
	 * cameraPitch is -40 degrees up to 42 degrees down, with 0 being level
	 */
	Camera cam;
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	public final int xRange = 80;
	public final int yRange = 143;
	
	public CameraReading(Camera cam){
		
		this.cam = cam;
	}
	
	public CameraReading(int red, int blue, int green){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	private Block getClosestBlock(){
		double distance = 2000;
		Block closestBlock = null;
		for (Block block: blocks){
			if (block.getDistance() < distance){
				distance = block.getDistance();
				closestBlock = block;
			}
		}
		return closestBlock;
	}
	
	public boolean hasBlocks(){
		return (blocks.size()!=0);
	}

	public int getCenterX() {
		//first get the closest block
		if (blocks.size() == 0) return 0;
		Block closestBlock = getClosestBlock();
		//should give a value from 0 to 1.0
		double anglePercent = ((closestBlock.getAngle())/cam.cameraSpreadAngle);
		Debug.debug("closest block angle: " +closestBlock.getAngle());
		Debug.debug("camera spread angle: " +cam.cameraSpreadAngle);
		Debug.debug("angle percent: " +anglePercent);

		return (int) (80-(xRange * anglePercent));
		
	}
	
	public int getCenterY() {
		if (blocks.size() == 0) return 0;
		Block closestBlock = getClosestBlock();
		//so the cameraPitch angle is the center line. Camera height is 13.5 cm
		//we'll say the block is 5cm high, or 15 pixels. So center is 2.5
		double height = cam.getCameraHeight() - 2.5;
		//now how far off is that from the center line?
		//distance is the x value, height is the y.
		double angle =Math.toDegrees( Math.atan2(height*3, closestBlock.getDistance()));
		//now express that as a percent of the cameraSpreadAngle
		double percentAngle = ((angle - cam.getCameraPitch())+cam.getCameraSpreadAngle()/2)
				/cam.getCameraSpreadAngle();
		//multiply that by yRange
		int value = (int)(percentAngle * yRange);
		//return the value, or yRange as the maximum allowable value
		return (value > yRange) ? yRange : value;
	}

	public int getRed() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getGreen() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBlue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTopLeftX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTopLeftY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBottomRightX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBottomRightY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPixels() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The confidence depends on distance and angle
	 * calculate on a per block basis, then return the highest value.
	 * If the distance is greater than half the allowable distance, 
	 * then the angle gets weighted more (ie, the block needs to be closer
	 * to the center). Otherwise confidence will be pretty high regardless.
	 * This is a formula I made up to approximate the confidence.
	 * @return
	 */
	public int getConfidence() {
		
		if (blocks.size() == 0) return 0;
		int maxConfidence = 0;
		
		for (Block block: blocks){
			int confidence = 255;
			//get the distance as a percent
			double percentConfidence =(1 - block.distance/cam.getCameraDistance());
			if (percentConfidence < 0.5){
				//this may need some tweaking, but confidence will degrade
				//pretty quickly with a far distance and bad angle
				if (percentConfidence < 0.25) percentConfidence = 0.25;
				double anglePercent = (1-
						Math.abs((block.getAngle()-cam.getCameraSpreadAngle()/2)/ 
								cam.getCameraSpreadAngle()/2));
				percentConfidence *= anglePercent;
			}
			confidence *= percentConfidence;
			if (confidence > maxConfidence) maxConfidence = confidence;
		}
		return maxConfidence;
		
	}

	public void addBlock(double distance, double angle) {
		blocks.add(new Block(distance, Math.toDegrees(angle)));
	}
	
	private class Block {
		
		double distance, angle;
		
		public Block(double distance, double angle){
			this.distance = distance;
			this.angle = angle;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}
		public double getAngle() {
			return angle;
		}
		public void setAngle(double angle) {
			this.angle = angle;
		}
		
	}

}
