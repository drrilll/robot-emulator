/**
 * @(#)DarrylsPlanner.java
 *
 *
 * @author
 * @version 1.00 2012/2/3
 */
import dang.tracker.*;

public class NavigationMapPlanner extends Planner{

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
	public static final byte THREESIXTY = 7;
	public static final byte TURN = 8;
	double tolerance = 15;
	double angleTurnTolerance = 15.0;
	double angleSpinTolerance = 30.0;
	byte[] instruction;
	int count = 0;
	boolean spin360 = false;
	boolean done = false;
	int startAngle;

    public NavigationMapPlanner() {
    	//if you want custom headings:
    	//setTraceFileUserHeaderData("DIRRS, Sonar");
    	setTraceFileUserHeaderData("Dirrs+|0|-3|0|0.05|3,Sonar|0|4|0|0.10|19,IR6|-90|-5|-5.5|0.85|5.5");
		System.out.println("1");
    	path = getUserDefinedPath();
		System.out.println("2");
		finalDestination = path.length;
		System.out.println("6");
    	instruction = new byte[3];
    }
// Called when the planner receives a pose from the tracker
	public void receivedPoseFromTracker(Pose p){
		int x,y;
        System.out.println("current: " + currentDestination);
        System.out.println("final: " + finalDestination);
		if (spin360){
			instruction[0] = THREESIXTY;
			System.out.println("Spin! in first procedure");
			sendDataToRobot(instruction);
			return;
		}
		if(currentDestination >= finalDestination){
            System.out.println("final stop");
			instruction[0] = STOP;
			sendDataToRobot(instruction);
			return;
		}
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
			System.out.println("current Destination: "+ currentDestination);
			System.out.println("final Destination: "+ finalDestination);

			if (currentDestination < finalDestination){
				currentDestination ++;
				spin360 = true;
				instruction[0] = THREESIXTY;
				System.out.println("Spin! 2nd proc");
				sendDataToRobot(instruction);
				return;
			}
		}
		double angle = Math.toDegrees(Math.atan2(y,x));
		System.out.println("destination angle: " + angle);
		System.out.println("pose angle: "+ p.angle);
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


		System.out.println("Adjusted angle difference: " + angle);
		if (Math.abs(angle) > angleSpinTolerance){
			if (angle < 0){
				instruction[0] = PIVOTLEFT;
			}else if (angle > 0){
				instruction[0] = PIVOTRIGHT;
			}

		}else if (Math.abs(angle) > angleTurnTolerance){
			if (angle < 0){
				instruction[0] = LEFT;
			}else if (angle > 0){
				instruction[0] = RIGHT;
			}
		}else{
			instruction[0] = NOTURN;
			spin360 = false;
		}

		sendDataToRobot(instruction);
	}
	// Called when the planner receives data from the robot
	public void receivedDataFromRobot(int[] data) {
        //this is a signal from the robot that it received
        //this particular instruction, so we can now reset
        //the flag to false
		if (data.length == 1){
			System.out.println("turn spin off");
			spin360 = false;
			return;
		}
		int d = data[0]*256 + data[1];
		int s = data[2]*256 + data[3];
 		int i = data[4]*256 + data[5];
 		System.out.println("data from robot:" + d + "," + s + "," + i);
		sendDataToTraceFile("" + d + "," + s + "," + i);
	}



	// Called when the planner receives data from another station
	public void receivedDataFromStation(int stationId, int[] data) {
		System.out.println("Received Station " + stationId + " Data: ");
	}

}
