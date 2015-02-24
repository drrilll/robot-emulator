/**
 * @(#)DarrylsPlanner.java
 *
 *
 * @author
 * @version 1.00 2012/2/3
 */

import dang.tracker.*;

public class InverseKinematicsPlanner extends Planner{

	boolean firstPose = true;

    public InverseKinematicsPlanner() {
    	//if you want custom headings:
    	//setTraceFileUserHeaderData("DIRRS, Sonar");
    	//if you want user-defined path loaded in:
    	//Pose[] path = getDesiredPathFromTracker();
    }
// Called when the planner receives a pose from the tracker
	public void receivedPoseFromTracker(Pose p){

		System.out.println("Received Pose: (" + p.x + "," + p.y + "," + p.angle + ")");
		byte[] d = new byte[22];
		d[0] = (byte)(p.x/256);
		d[1] = (byte)(p.x%256);
		d[2] = (byte)(p.y/256);
		d[3] = (byte)(p.y%256);
		d[4] = (byte)(p.angle/256);
		d[5] = (byte)(p.angle%256);

        Pose[] path = getUserDefinedPath();

        d[6] = (byte)(path[0].x/256);
        d[7] = (byte)(path[0].x%256);
        d[8] = (byte)(path[0].y/256);
        d[9] = (byte)(path[0].y%256);
        d[10] = (byte)(path[1].x/256);
        d[11] = (byte)(path[1].x%256);
        d[12] = (byte)(path[1].y/256);
        d[13] = (byte)(path[1].y%256);
        d[14] = (byte)(path[2].x/256);
        d[15] = (byte)(path[2].x%256);
        d[16] = (byte)(path[2].y/256);
        d[17] = (byte)(path[2].y%256);
        d[18] = (byte)(path[3].x/256);
        d[19] = (byte)(path[3].x%256);
        d[20] = (byte)(path[3].y/256);
        d[21] = (byte)(path[3].y%256);
		sendDataToRobot(d);
	}
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
