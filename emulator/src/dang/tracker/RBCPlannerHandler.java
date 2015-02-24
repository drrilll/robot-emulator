package dang.tracker;




import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class RBCPlannerHandler extends Thread {
	private RobotTracker	rb;						// The main application

	private Pose 			prevPose;				// Current Position of the robot
	private Class<?> 		plannerClass;			// The class of the planner that is loaded
	private Object 			planner;				// The instance of the planner that is loaded
	private boolean 		shutdown = false;		// Used to tell the class whether the planner is shutdown or not
	private URL 			location;				// Location of the planner class
	private String 			name;					// Name of the planner class
	private volatile File	currentTraceFile;		// The file that represents the current trace file that the planner is writing to
	private boolean 		writeToFile = false;	// Flag on whether to write data to file or not
	private static URLClassLoader 	loader;			// The class loader



	// Data queues
	volatile LinkedList<byte[]> dataReceivedQ;			// Data queue for data received from the robot. It feeds it to the class one at a time
	volatile LinkedList<byte[]> stationDataReceivedQ;	// Data queue for data received from another station. It feeds it to the class one at a time

	// Methods
	private Method poseReceived;			// The method in the planner, poseReceivedFromTracker
	private Method dataReceived;			// The method in the planner, dataReceivedFromRobot
	private Method stationDataReceived;		// The method in the planner, dataReceivedFromStation
	private Method trackerDataReceived;		// The method in the planner, dataReceivedFromTracker


	public RBCPlannerHandler(RobotTracker mainApp, URL plannerLocation, String plannerName) throws ClassNotFoundException {
		rb = mainApp;
		location = plannerLocation;
		name = plannerName;

		dataReceivedQ = new LinkedList<byte[]>();
		stationDataReceivedQ = new LinkedList<byte[]>();

		File file = new File(location.getPath() + "\\" + name + ".class");
		prevPose = new Pose(-1,-1,-1);


		// Attempt to load planner
		try {
			URL[] url = {plannerLocation};
			loader = new URLClassLoader(url);
			plannerClass = loader.loadClass(plannerName);
		} catch (Exception e) {
			System.out.println("[ERROR] Unable to load planner.");
			shutdown = true;
			throw new ClassNotFoundException();
		}
		grabMethods(true);
		if (shutdown)
			rb.unloadPlanner();
	}

	// Initialize the methods from the loaded planner class
	private boolean grabMethods(boolean reportError) {
		try {
			poseReceived = plannerClass.getMethod("receivedPoseFromTracker", Pose.class);
		} catch (NoSuchMethodException e) {
			if (reportError) {
				System.out.println("[ERROR]: Planner must have a receivedPoseFromTracker method.");
				shutdown = true;
			} else
				return false;
		}
		try {
			dataReceived = plannerClass.getMethod("receivedDataFromRobot", int[].class);
		} catch (NoSuchMethodException e) {
			if (reportError) {
				System.out.println("[ERROR]: Planner must have a receivedDataFromRobot method.");
				shutdown = true;
			} else
				return false;
		}
		try {
			stationDataReceived = plannerClass.getMethod("receivedDataFromStation", int.class, String.class);
		} catch (NoSuchMethodException e) {
			if (reportError) {
				System.out.println("[ERROR]: Planner must have a receivedDataFromStation method.");
				shutdown = true;
			} else
				return false;
		}

		/*try {
			trackerDataReceived = plannerClass.getMethod("receivedDataFromTracker", int[].class);
		} catch (NoSuchMethodException e) {
			if (reportError) {
				System.out.println("[ERROR]: Planner must have a receivedDataFromTacker method.");
				shutdown = true;
			} else
				return false;
		}*/
		return true;
	}

	// Start running the loaded planner class
	@SuppressWarnings("unchecked")
	public void run() {
		System.out.println("Planner Started");

		File file = new File(location.getPath() + "\\" + name + ".class");
		int count = 1;
		currentTraceFile = new File(location.getPath() + "\\trace" + count + ".trc");
		while (currentTraceFile.exists()) {
			count++;
			currentTraceFile = new File(location.getPath() + "\\trace" + count + ".trc");
		}
		Class<?> oldClass = plannerClass;
		Method oldPoseReceived = poseReceived;
		Method oldDataReceived = dataReceived;
		Method oldStationDataReceived = stationDataReceived;
		try {
			Constructor[] cons = plannerClass.getConstructors();
			Planner.setRBCPlannerHandler(this);
			planner = (cons[0].newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] Cannot start planner.");
			Thread.currentThread().interrupt();
			return;
		}
		if (shutdown)
			return;
		long timedMillis = 0;

		// Output to the trace file
		try {
			FileWriter write = new FileWriter(currentTraceFile);
			write.write("x,y,angle");
			if (((Planner)planner).getTraceFileUserHeaderData().compareTo("") != 0)
				write.write("," + ((Planner)planner).getTraceFileUserHeaderData());
			write.close();
			writeToFile = true;
		} catch (Exception e) {
			System.out.println("[ERROR] Unable to write to trace file.");
			e.printStackTrace();
		}

		prevPose = new Pose(-1,-1,-1);
		while (true) {
			try {
        		Thread.sleep(500);
        	} catch (InterruptedException e) {
        		Thread.currentThread().interrupt();
        		break;
        	}
        	if (System.currentTimeMillis() - timedMillis < (1000/rb.frameRate)) {//((Planner)planner).getFrameRate())) {
        		// receivedMethods(); NO LONGER USED
        		continue;
        	}

 			// First consider poses found from other stations
			Pose robotPose = null;
			/*for (int s=0; s<3; s++) {
				if (!rb.trackerServer.servers[rb.trackerServer.trackerID-1].equals(rb.trackerServer.servers[s]))
					if (rb.trackerServer.posesFromServers[s][rb.trackingPanel.trackID] != null)
						robotPose = rb.trackerServer.posesFromServers[s][rb.trackingPanel.trackID];
			}*/

       		// Now consider poses found on this station
			if (rb.trackingPanel.posesFound[rb.trackingPanel.trackID] != null)
				robotPose = rb.trackingPanel.posesFound[rb.trackingPanel.trackID];

			// If nobody found this robot, then no pose available
			if (robotPose == null)
				robotPose = new Pose(-1,-1,-1);

			recordTrace(robotPose, prevPose);
			poseReceived(robotPose);
			timedMillis = System.currentTimeMillis();
			// receivedMethods(); NO LONGER USED
			prevPose = robotPose;
		}
	}


	// Record the trace in the current trace file
	private void recordTrace(Pose currentPose, Pose prevPose) {
		if (writeToFile && poseDifferent(prevPose, currentPose)) {
			try {
				FileWriter write = new FileWriter(currentTraceFile, true);
				write.write("\r\n" + currentPose.x + "," + currentPose.y + "," + currentPose.angle);
				write.close();
			} catch (Exception e) {
				System.out.println("[ERROR] Unable to write to trace file.");
			}
		}
	}

	// Send data to the trace file at the current position of the robot
	public void postTraceData(String data) {
		if (writeToFile) {
			try {
				FileWriter write = new FileWriter(currentTraceFile, true);
				write.write(","+data);
				write.close();
			} catch (Exception e) {
				System.out.println("[ERROR] Unable to write to trace file.");
			}
		}
	}

	// Determine whether the two poses are different
	private boolean poseDifferent(Pose p1, Pose p2) {
		return !((p1.x == p2.x) && (p1.y == p2.y) && (p1.angle == p2.angle));
	}

	// Attempt to call poseReceivedFromTracker in the loaded planner
	public void poseReceived(Pose robotPose) {
		try {
			poseReceived.invoke(planner, robotPose);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] Unable to call poseReceivedFromTracker() in planner.");
		}
	}

	// Attempt to call dataReceivedFromRobot in the loaded planner
	public void dataReceived(ArrayList<Integer> inData) {
		int[] data = new int[inData.size()];
		for (int i=0;i<inData.size();i++) {
			data[i] = inData.get(i);
		}
		try {
			dataReceived.invoke(planner, data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] Unable to call dataReceivedFromRobot() in planner.");
		}
	}

	// Attempt to call receivedDataFromStation() in the loaded planner
	public void stationDataReceived(int id, String data) {
		try {
			stationDataReceived.invoke(planner, id, data);
		} catch (Exception e) {
		}
	}

	// NOT USED Attempt to call dataReceivedFromTracker in the loaded planner
	/*public void trackerDataReceived(byte[] data2) {
		int[] data = new int[data2.length];
		for (int i=0;i<data2.length;i++) {
			data[i] = data2[i]&0xFF;
		}
		try {
			trackerDataReceived.invoke(planner, data);
		} catch (Exception e) {
			System.out.println("[ERROR] Unable to call dataReceivedFromTracker() in planner.");
		}
	}*/


	// Send data to the robot
	public void sendData(byte[] data) {
		rb.sendData(data);
	}

	// Send data to a specific station
	public void sendStationData(int station, String data) {
		//if ((station >= 1) && (station <= 3))
			//rb.trackerServer.stationDataToSend[station-1] = data;
	}


	// Send the estimated position to the Robot Tracker
	public void sendEstimatedPositionToTracker(int x, int y, int angle) {
		rb.trackingPanel.addEstimatedPath(x, y);
	}

	// Receive desired path from Robot Tracker
	public Pose[] getDesiredPath() {
		int length = rb.trackingPanel.desiredPath.size();
		Pose[] poses = new Pose[length];
		for (int i=0; i<length; i++)
			poses[i] = new Pose(rb.trackingPanel.desiredPath.get(i).x,rb.trackingPanel.desiredPath.get(i).y,-1);
		return poses;
	}
}