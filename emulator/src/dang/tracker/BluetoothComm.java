package dang.tracker;



import java.io.*;
import java.awt.Color;
import java.util.*;

import dang.program.Debug;

public abstract class BluetoothComm {
	protected RobotTracker 					rb;					// Reference to the main program
	protected volatile LinkedList<int[]> 	queue;				// stores outgoing messages which are fed to the server one at a time
    protected int 							currentFlag = 0;	// set when data is received from robot ... allows output to log, log file, both, or none

	protected BluetoothComm(RobotTracker mainApp) {
		rb = mainApp;
	}

	// Used by the two bluetooth classes that extend BluetoothComm to set up the server
	public abstract void startServer() throws Exception;
	public abstract boolean connectToRobot();

	// Called by the two bluetooth classes that extend BluetoothComm.
	// This is the main method that controls the bluetooth communication.
	// in reads from the bluetooth device.
	// out writes data to the bluetooth device.
	protected void startListening(InputStream in, OutputStream out) throws Exception {
        int counter = 0;
        int mode = RBCConstants.WAITING_FOR_BEGIN;
        int command = -1;
        int size = -1;
        ArrayList<Integer> dataRead = new ArrayList<Integer>(256);
        boolean proceed = false;

       	try {
    		Thread.sleep(0);
			rb.updateStatusBar("Connected to robot", Color.black);
			System.out.println("Connected to robot");
    	} catch (InterruptedException e) {
    		Thread.currentThread().interrupt();
    	}
    	while (true) {
    		try {
        		Thread.sleep(10);
        	} catch (InterruptedException e) {
        		Thread.currentThread().interrupt();
        		break;
        	}

        	// Send data to the robot
    		Debug.debug("Checking for data to send to robot");
        	if (queue.size() > 0) {
        		int[] sendToRobot = queue.poll();
        		int thisSize = sendToRobot.length;
        		for (int i=0;i<thisSize;i++) {
        			out.write(sendToRobot[i]);
           		}
        		out.flush();
        		sendToRobot = null;
        	}
    		// Read string from spp client
    		Debug.debug("Checking for available data from robot");
    		if (in.available()>0) {
        		Debug.debug("RBC: Found available data!!");
    			//presumably this converts to int
    			int input = in.read()&0xFF;
    			if (mode == RBCConstants.WAITING_FOR_BEGIN) {
					Debug.debug("RBC: mode is WAITING_FOR_BEGIN");
					Debug.debug("RBC: sig1 input is: "+input);
    				int temp = input;
    				if (temp == RBCConstants.BEGIN_DATA) {
    					Debug.debug("RBC: input is BEGIN_DATA");
    					Debug.debug("RBC: mode is changing to WAITING_FOR_COMMAND");
    					mode = RBCConstants.WAITING_FOR_COMMAND;
    				} else if (temp == RBCConstants.CAMERA_OUTPUT) {
    					mode = RBCConstants.WAITING_FOR_CAMERA_DATA;
    					//imageData = new ArrayList<Integer>();
    				}
    			} else if (mode == RBCConstants.WAITING_FOR_COMMAND) {
					Debug.debug("RBC: mode is WAITING_FOR_COMMAND");
    				command = input;
					Debug.debug("RBC: input is "+input);
    				if (command == RBCConstants.ENABLE_INPUT || command == RBCConstants.DISABLE_INPUT
    					|| command == RBCConstants.CLEAR_DEBUG || command == RBCConstants.IMAGE_RESET) {
    					mode = RBCConstants.WAITING_FOR_BEGIN;
    					counter = 0;
    					proceed = true;
    				} else if (command == RBCConstants.PLANNER_DATA) {
    					mode = RBCConstants.WAITING_FOR_FLAG;
    				}
    				else
    					mode = RBCConstants.WAITING_FOR_SIZE;
    			} else if (mode == RBCConstants.WAITING_FOR_FLAG) {
    				currentFlag = input;
    				mode = RBCConstants.WAITING_FOR_SIZE;
    			} else if (mode == RBCConstants.WAITING_FOR_SIZE) {
					Debug.debug("RBC: mode is changing to WAITING_FOR_SIZE");
    				size = input;
					Debug.debug("RBC: SIZE is "+size);
    				mode = RBCConstants.WAITING_FOR_DATA;
    			} else if (mode == RBCConstants.WAITING_FOR_DATA) {
    				dataRead.add(input);
    				counter++;
    				if (counter >= size) {
    					proceed = true;
    					counter = 0;
    					mode = RBCConstants.WAITING_FOR_EXTRA;
    					mode = RBCConstants.WAITING_FOR_BEGIN;
    				}
    			} else if (mode == RBCConstants.WAITING_FOR_CAMERA_DATA) {
    				//imageData.add(new Integer(input));
    				/*if ((int)input == 3) {
    					handleImage();
    					mode = RBCConstants.WAITING_FOR_BEGIN;
    					continue;
    				  }*/
    		    }
    		}
        	else
        		continue;

        	try {
        		Thread.sleep(0);
        	} catch (InterruptedException e) {
        		Thread.currentThread().interrupt();
        		break;
        	}
        	if (proceed) {
				Debug.debug("RBC: handling the data!!");
				Debug.debug("RBC: command is "+command);
				Debug.debug("RBC: MODE is "+mode);
        		handleData(command, dataRead);
        		dataRead = new ArrayList<Integer>(256);
        		command = -1;
        		size = -1;
        		proceed = false;
        	}
    	}
		try {
    		out.close();
    		in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

 	// Receive data and then prepare it to be sent to the robot.
 	// addCommand set whether to add the standard
 	public void sendData(byte[] data, boolean addCommand) {
 		int length = data.length;
 		int[] data2;
 		int inc = 0;
 		if (addCommand) {
 			data2 = new int[length+2];
 			data2[0] = RBCConstants.BEGIN_DATA;
 			data2[1] = length;
 			inc = 2;
 		} else {
 			data2 = new int[length];
 		}
 		for (int i=0;i<length;i++) {
 			data2[i+inc] = (int)data[i];
 		}
 		queue.add(data2);
 	}

  	// Receive and prepare debug data to be sent to the robot.
 	public void sendDebugData(byte[] data) {
 		int length = data.length;
 		int[] data2;
 		int inc = 0;
		data2 = new int[length+2];
		data2[0] = RBCConstants.BEGIN_DEBUG_DATA;
		data2[1] = length;
		inc = 2;
 		for (int i=0;i<length;i++) {
 			data2[i+inc] = (int)data[i];
 		}
 		queue.add(data2);
 	}


 	// Called by the server after receiving a full packet from the robot.
 	// command = type of data that was sent by the robot.
 	// data = data that was sent by the robot.
 	public void handleData(int command, ArrayList<Integer> data) {
 		if (command==RBCConstants.DEBUG_OUTPUT) {
 			// Convert integers to chars
			String s = new String();
			for (Integer i: data)
				s += (char)(i.intValue());
 			rb.debugDialog.appendText(s);
 		}
 		else if (command==RBCConstants.DEBUG_OUTPUT_LINE) {
			// Convert integers to chars
			String s = new String();
			for (Integer i: data)
				s += (char)(i.intValue());
 			rb.debugDialog.appendTextWithCR(s);
 		}
 		else if (command==RBCConstants.PLANNER_DATA)
			rb.receivedPlannerData(data, currentFlag);
 		else if (command==RBCConstants.CLEAR_DEBUG) {
			rb.debugDialog.clearText();
 		}
 		else if (command==RBCConstants.IMAGE_BLOB) {
 			rb.cameraDialog.showImageTrace(data);
 		}
 		else if (command==RBCConstants.IMAGE_COLOR) {
			rb.cameraDialog.setImageColor(data.get(0), data.get(1), data.get(2));
 		}
 		else if (command==RBCConstants.IMAGE_RESET) {
			rb.cameraDialog.clearImage();
 		}
 		/*else if (command==RBCConstants.ROBOT_DATA) {
 			byte[] data2 = new byte[data.size()+2];
			data2[0] = RBCConstants.SERVER_ROBOT_DATA;
			data2[1] = (byte)(data.get(0).intValue());
			data2[2] = (byte)(data.size()-1);
			for (int i=1;i<data.size();i++){
				data2[i+2] = (byte)(data.get(i).intValue());
			}
			TrackerServer.sendDataToStation(data.get(0), data2, rb.stationAddresses);
 		}*/
 	}
}