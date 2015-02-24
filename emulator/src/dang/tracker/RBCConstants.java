package dang.tracker;



public interface RBCConstants {
	// Bluetooth Server modes
	public static final int WAITING_FOR_BEGIN = 0;			// Server mode: Waiting for the begin command
	public static final int WAITING_FOR_COMMAND = 1;		// Server mode: Waiting for a command from the robot
	public static final int WAITING_FOR_SIZE = 2;			// Server mode: Waiting for the size of the data from the robot
	public static final int WAITING_FOR_DATA = 3;			// Server mode: Waiting for the data from the robot
	public static final int WAITING_FOR_EXTRA = 4;			// Server mode: Waiting for extra data
	public static final int WAITING_FOR_CAMERA_DATA = 5;	// Server mode: Waiting for camera data from a frame dump
	public static final int WAITING_FOR_FLAG = 6;			// Server mode: Waiting for the flag from the Send Data command

	// First byte sent with each packet
	public static final int BEGIN_DATA = 2;					// Byte sent at the beginning of each regular data packet
	public static final int BEGIN_DEBUG_DATA = 3;			// Byte sent at the beginning of each debug data packet

	// Type of packets sent to server
	public static final int DEBUG_OUTPUT = 1;				// Data command: Received debug output
	public static final int DEBUG_OUTPUT_LINE = 2;			// Data command: Received debug output with a carriage return
	public static final int PLANNER_DATA = 5;				// Data command: Received planner data
	public static final int CAMERA_OUTPUT = 6;				// Data command: Received data from a camera frame dump
	public static final int ENABLE_INPUT = 7;				// Data command: Enable the debug input
	public static final int DISABLE_INPUT = 8;				// Data command: Disable the debug input
	public static final int CLEAR_DEBUG = 9;				// Data command: Clear the debug output
	public static final int IMAGE_BLOB = 10;				// Data command: Tracking information from the camera
	public static final int ROBOT_DATA = 11;				// Data command: Data that is to be sent to another robot
	public static final int IMAGE_RESET = 12;				// Data command: Reset the image in the image panel
	public static final int IMAGE_COLOR = 13;				// Data command: Set the image colour that is being tracked

	// Flags for sendData
	public static final int OUTPUT_TO_LOG = 1;				// Send Data flag: Output the data to the log output
	public static final int OUTPUT_TO_FILE = 2;				// Send Data flag: Output the data to the log file
	public static final int OUTPUT_TO_LOG_AND_FILE = 3;		// Send Data flag: Output the data to the log output and file
	public static final int OUTPUT_TO_NONE = 4;				// Send Data flag: Don't output the data

	// RBC Server Constants
	public static final int SERVER_PLANNER_DATA = 1;		// Server commands: Planner data sent from another station
	public static final int SERVER_ROBOT_DATA = 2;			// Server commands: Robot data sent from another robot
	public static final int TRACKER_DATA = 10;				// Server commands: Data sent from the Robot Tracker

	// EasyBluetooth connection constants
	public static final int REQUEST_CONNECTION = 10;		// EasyBluetooth command: Attempt to connect to the robot
	public static final int ROBOT_CONN_ACK = 8;				// EasyBluetooth command: Acknowledge the connection to the robot

	// Packets sent to the robot
	public static final int ROBOT_BEGIN = 11;				// Sent to start the robot
}