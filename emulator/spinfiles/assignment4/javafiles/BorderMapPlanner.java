/**
 * @(#)DarrylsPlanner.java
 *
 *
 * @author
 * @version 1.00 2012/2/3
 */


public class BorderMapPlanner extends Planner{

	boolean firstPose = true;

    public BorderMapPlanner() {
    	//if you want custom headings:
    	//setTraceFileUserHeaderData("DIRRS, Sonar");
    	//if you want user-defined path loaded in:
    	//Pose[] path = getDesiredPathFromTracker();
    	setTraceFileUserHeaderData("Dirrs+|0|-3|0|0.05|3,Sonar|0|4|0|0.10|19,IR6|0|-5|-5.5|0.85|5.5");
    }
// Called when the planner receives a pose from the tracker
	public void receivedPoseFromTracker(Pose p){

		System.out.println("Received Pose: (" + p.x + "," + p.y + "," + p.angle + ")");

	}
	// Called when the planner receives data from the robot
	public void receivedDataFromRobot(int[] data) {
		int d = data[0]*256 + data[1];
		int s = data[2]*256 + data[3];
 		int i = data[4]*256 + data[5];
 		for (int j=0; j < data.length; j++){
 			System.out.println("data "+j+": "+data[j]);
 		}
 		System.out.println("" + d + "," + s + "," + i);
		sendDataToTraceFile("" + d + "," + s + "," + i);
	}

	// Called when the planner receives data from another station
	public void receivedDataFromStation(int stationId, int[] data) {
		System.out.println("Received Station " + stationId + " Data: ");
	}

}