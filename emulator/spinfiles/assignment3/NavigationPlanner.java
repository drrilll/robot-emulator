/**
 * @(#)DarrylsPlanner.java
 *
 *
 * @author
 * @version 1.00 2012/2/3
 */
import dang.tracker.*;

public class NavigationPlanner extends Planner{

	boolean firstPose = true;
	Pose[] path;
	int currentDestination = 0;
	int finalDestination;
	public static final byte NOTURN = 0;
	public static final byte LEFT = 1;
	public static final byte RIGHT = 2;
	public static final byte STOP = 3;
	public static final byte STOPTWO = 4;
	public static final byte PIVOTLEFT = 5;
	public static final byte PIVOTRIGHT = 6;
	double tolerance = 15;
	double angleTolerance = 15.0;
	byte[] instruction;
	int count = 0;

    public NavigationPlanner() {
    	//if you want custom headings:
    	//setTraceFileUserHeaderData("DIRRS, Sonar");
		System.out.println("1");
    	Pose[] p = getUserDefinedPath();
		System.out.println("2");
    	//for convenience later I extend path and add the first
    	//location as the final location so it makes a complete
    	//loop

    	path = new Pose[p.length +1];
    	System.out.println("3");
    	for (int i = 0; i < p.length; i++){
    		path[i] = p[i];
    	}
    	System.out.println("4");
    	path[p.length] = p[0];
    	System.out.println("5");

    	//path = getUserDefinedPath();
		finalDestination = path.length;
		System.out.println("6");
    	instruction = new byte[1];
        
        //default instruction. We could get bad readings right away
        //in which case this is the instruction we send
        instruction[0] = STOP;
    }
// Called when the planner receives a pose from the tracker
	public void receivedPoseFromTracker(Pose p){
		int x,y;
		
        //if we get a bad reading just continue what we were doing
		if (p.x == -1){
			sendDataToRobot(instruction);
			return;
		}

		x = path[currentDestination].x - p.x;
		y = path[currentDestination].y - p.y;
		count ++;
		if (count > 30){
			System.out.println(x + " , " + y + " , " + currentDestination);
			count = 0;
		}
		if ((Math.abs(x)< tolerance) && (Math.abs(y)<tolerance)){
			System.out.println("within tolerance");
			if (currentDestination == finalDestination){
				finished();
			}else{
                
                //if we increment our currentDestination we 
                //send a STOP instruction and exit the method
                //otherwise we will do a bunch of calculations
                //and send an instruction based on our old destination
                
				System.out.println("found 1");
				currentDestination ++;
                instruction[0]=STOP;
                sendDataToRobot(instruction);
                return;
			}
		}
		double angle = Math.toDegrees(Math.atan2(y,x));
		System.out.println("destination angle: " + angle);
		double pangle = p.angle;
		if (pangle > 180){
			pangle = pangle - 360;
		}
		angle = pangle - angle;
		//get rid of negative angles
		System.out.println("angle difference: " + angle);
		if (angle > 180){
			angle = angle -360;
		}
		if (angle < -180){
			angle = angle + 360;
		}
		if (Math.abs(angle) < angleTolerance){
			instruction[0] = NOTURN;
		}else if (angle < 0){
			instruction[0] = PIVOTLEFT;
		}else if (angle > 0){
			instruction[0] = PIVOTRIGHT;
		}
		sendDataToRobot(instruction);
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

	public void finished(){
		instruction[0] = STOPTWO;
		sendDataToRobot(instruction);
	}

}
