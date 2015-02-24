package dang.tracker;




// This is the base class used for making planners.  The developper should
// extend this class Planner and override the public methods.

public class Planner {
	private static RBCPlannerHandler	handler;				// The Planner Handler that runs the planner
	private String 						userHeader = "";		// User defined header that is added with the default title of a trace file.


	// Called when planner receives a pose from the Robot Tracker (you can override this)
	public void receivedPoseFromTracker(Pose pose) {}

	// Called when  planner receives data from the robot (you can override this)
	public void receivedDataFromRobot(int[] data) {}

	// Called when planner receives data from another station (you can override this)
	public void receivedDataFromStation(int station, String data) {}

	// Sets the trace file header (you can override this)
	protected void setTraceFileUserHeaderData(String s) { userHeader = s; }
	// Sends data to the robot (you can override this)
	protected void sendDataToRobot(byte[] data) {
		handler.sendData(data);
	}

	// Sends data to a station (you can override this)
	protected void sendDataToStation(int station, String data) {
		handler.sendStationData(station, data);
	}

	// Sends data to the trace file (you can override this)
	protected void sendDataToTraceFile(String data) {
		handler.postTraceData(data);
	}

	// Sends the estimated position to the Robot Tracker (you can override this)
	protected void sendEstimatedPositionToTracker(int x, int y, int angle) {
		handler.sendEstimatedPositionToTracker(x, y, angle);
	}

	// Return an array of Poses representing the points in the
	// desired path from the Robot Tracker (you can override this)
	protected Pose[] getUserDefinedPath() {
		return handler.getDesiredPath();
	}

	// (Not Used) Called when the planner receives data from the Robot Tracker (you can override this)
	// public void receivedDataFromTracker(int[] data) {}

	// (Not Used) Sends data to the Robot Tracker (you can override this)
	// protected void sendDataToTracker(byte[] data) {
	//	handler.sendTrackerData(data);
	// }

	// Set the Planner Handler in order to access its methods
	public static void setRBCPlannerHandler(RBCPlannerHandler	h) { handler = h; }

	// Gets the trace file header (you can override this)
	protected String getTraceFileUserHeaderData() { return userHeader; }
}