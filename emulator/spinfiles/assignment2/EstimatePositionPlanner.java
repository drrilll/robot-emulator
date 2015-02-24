/**
 * @(#)DarrylsPlanner.java
 *
 *
 * @author
 * @version 1.00 2012/2/3
 */
import dang.tracker.*;

public class EstimatePositionPlanner extends Planner{

	boolean firstPose = true;

    public EstimatePositionPlanner() {
    	//if you want custom headings:
    	//setTraceFileUserHeaderData("DIRRS, Sonar");
    	//if you want user-defined path loaded in:
    	//Pose[] path = getDesiredPathFromTracker();
    }
// Called when the planner receives a pose from the tracker
	public void receivedPoseFromTracker(Pose p){
		if (firstPose){
		System.out.println("Received Pose: (" + p.x + "," + p.y + "," + p.angle + ")");
		byte[] d = new byte[6];
		d[0] = (byte)(p.x/256);
		d[1] = (byte)(p.x%256);
		d[2] = (byte)(p.y/256);
		d[3] = (byte)(p.y%256);
		d[4] = (byte)(p.angle/256);
		d[5] = (byte)(p.angle%256);
		System.out.println("data sent:");
		for (int i = 0; i < 6; i ++){
			System.out.println((int)d[i]&0xFF);
		sendDataToRobot(d);
		firstPose = false;
		}
	}}
	// Called when the planner receives data from the robot
	public void receivedDataFromRobot(int[] data) {
		System.out.println("Received Robot Data: " + data.length + " bytes.");

		int x = (data[0] * 256) + data[1];
		int y = (data[2] * 256) + data[3];
		int angle = (data[4] * 256) + data[5];
		System.out.println("x: "+x+" y: "+y+" angle: "+angle);
		sendDataToTraceFile(x+","+y+","+angle);
		sendEstimatedPositionToTracker(x,y,angle);

	}

	// Called when the planner receives data from another station
	public void receivedDataFromStation(int stationId, int[] data) {
		System.out.println("Received Station " + stationId + " Data: ");
	}

}
