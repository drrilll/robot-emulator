package dang.tracker;



import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.LinkedList;

import dang.robot.Robot;

/**
 * A bluetooth simulator that communicates between the robot
 * and whatever it is talking to. I don't actually use this any more.
 * Technically it is more true to how the lab bluetooth actually works,
 * but starting another java thread slowed everything to a crawl. Left it
 * here in case someone is a diehard purist and wants to implement it.
 * @author darrylhill
 *
 */
public class BluetoothSimulator extends BluetoothComm {
	
	Robot robot;

	
	private String 				blueURL;			// Bluetooth address of the robot
	private RobotTracker 		rb;					// Reference to the main program
   // private StreamConnection 	connection;			// The connection stream from the pc to the robot
    private PipedInputStream 		in;				// The stream to read bluetooth data from the robot
   	private PipedOutputStream 		out;			// The stream to write bluetooth data to the robot
   	private PipedInputStream		inToRobot;
   	private PipedOutputStream		outFromRobot;
   	public boolean running = false;
    
    protected BluetoothSimulator(RobotTracker mainApp) {
		super(mainApp);
		rb = mainApp;
		robot = mainApp.getRobot();
		queue = new LinkedList<int[]>();		// TODO Auto-generated constructor stub
	}

    // Set the URL of the bluetooth device to connect to.
	public void setURL(String url) {
   		blueURL = url;
	}

    // Start running the bluetooth server and attempt to connect to the robot through bluetooth.
    public boolean connectToRobot() {
 		try {

		    // Open server url (i.e., establish a connection with the robot's bluetooth device)
		    rb.showRobotConnecting();
		    System.out.println("Connecting to robot ...");
		    closeEverything();
		    /*
		     * For the simulated bluetooth, we simply hook up the input of the robot
		     * to the bluetooth output and vice versa, using piped input and output
		     * streams. Then we let the existing code handle all the little details, since
		     * it is already working.
		     */
		    in = new PipedInputStream();
		    inToRobot = new PipedInputStream();
   			out = new PipedOutputStream(inToRobot);
   			outFromRobot = new PipedOutputStream(in);
   			
   			/*
   			 * This is the actual connection. Just send in input
   			 * and output streams and then monitor the other ends.
   			 */
   			rb.getRobot().connectToSimBluetooth(inToRobot, outFromRobot);
   			
			// Send request to the robot for a connection
   			// Normally this would be the way to do it, but for simulator 
   			// purposes you know there is a robot connected, so ...
   			/*
		    out.write(RBCConstants.REQUEST_CONNECTION);
	        out.flush();
	        */
	        System.out.println("Waiting for ACK ...");
	        // Wait for acknowledgement from the robot
	        // int ack = -1;
	        // simulated robot, so we preload the ack variable with
	        // an acknowledgement
	        int ack = RBCConstants.ROBOT_CONN_ACK;
	        long	startTime = new java.util.Date().getTime();
	       	while (ack != RBCConstants.ROBOT_CONN_ACK) {
	       		if (in.available()>0)
	       			ack = in.read();
	       		try {
	        		Thread.sleep(0);
	        	} catch (InterruptedException e) {
	        		Thread.currentThread().interrupt();
	        		break;
	        	}
	        	// Check if timeout
	        	if ((new java.util.Date().getTime() - startTime) > 5000) {
	        		throw new IOException();
	        	}
	       	}
	       	System.out.println("Got it ...");
	       	return true;
	    } catch (InterruptedIOException e) {
	    	closeEverything();
	    	return false;
 		} catch (IOException e) {
 			System.out.println(e.getMessage());
		    rb.updateStatusBar("Unable to Connect to Robot", Color.black);
		    System.out.println("Unable to Connect to Robot");
			rb.showRobotDisconnected();
			closeEverything();
 			return false;
 		}
    }

	// Close down everything .. all streams and the connection
	private void closeEverything() {
	   	// free up I/O streams and close the socket connection
      	try {
        	if (in != null)
          		in.close();
      	} catch (Exception ignored) {}
      	try {
        	if (out != null)
          		out.close();
      	} catch (Exception ignored) {}
      	try {
        	if (out != null)
          		out.close();
      	} catch (Exception ignored) {}
      	try {
        	if (in != null)
          		in.close();
      	} catch (Exception ignored) {}
      	try {
      		if (inToRobot != null){
      			inToRobot.close();
      		}
      	} catch (Exception ignored) {}
      	try {
      		if (outFromRobot != null){
      			outFromRobot.close();
      		}
      	} catch (Exception ignored) {}
	}

    // Send a begin command to the robot and then go into infinite loop mode
    public void startServer() throws Exception {
 		try {
   			// Instruct the robot to begin now
	    	out.write(RBCConstants.ROBOT_BEGIN);
	    	out.flush();

			// Now go into the infinite loop to do communications, return when server stopped or interrupted
		    startListening(in, out);

		    // All done, close the connection
		    closeEverything();
		    rb.showRobotDisconnected();
	    } catch (InterruptedIOException e) {
	    	closeEverything();
	    	rb.showRobotDisconnected();
 		} catch (IOException e) {
		    rb.updateStatusBar("Unable to Start Robot", Color.black);
		    System.out.println("Unable to Start Robot");
			closeEverything();
			rb.showRobotDisconnected();
 		}
    }
}
