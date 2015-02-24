package dang.tracker;



import dang.program.Debug;
// This class is used to handle which bluetooth communication class we use.

public class RBCBlue extends Thread {
	// The bluetooth communication object which can be either BluetoothServer, or BluetoothServerEasyBlue.
	private BluetoothComm 		server;
	private RobotTracker 		rb;					// Reference to the main program

	public RBCBlue(RobotTracker mainApp) {
		rb = mainApp;
		if (RobotTracker.USE_SIM_BLUE){
			server = new BluetoothSimulator(rb);
		}
	}


	// Send data to the robot
	public void sendData(byte[] data) {
		server.sendData(data, true);
	}

	// Send debug data to the robot
	public void sendDebugData(byte[] data) {
		server.sendDebugData(data);
	}

	// Send data to the robot but without any preparation (no command is automatically added to the beginning).
	public void sendSpecificData(byte[] data) {
		server.sendData(data, false);
	}


	// Start running RBCBlue in a thread and initialize the server
	public void run() {
		try {
    		Debug.debug("Checking for robot connection");
			if (server.connectToRobot()) {
				rb.showRobotConnected();
	    		Debug.debug("Checking for tracker running");
	    		//taking out this line. Everything happens at once, there is no real
	    		//reason to delay
				//while (!rb.tracking);
	    		Debug.debug("Starting server . . .");
				server.startServer();
			}
		}
		catch (Exception e) {
			System.err.println(e.toString());
            System.err.println(e.getStackTrace());
            e.printStackTrace();
		}
	}
}