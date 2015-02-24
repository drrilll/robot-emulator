package dang.tracker;



import java.net.*;
import java.io.*;
import java.util.Vector;
import java.awt.Point;
import java.util.StringTokenizer;

public class TrackerServer extends Thread {
	public  static final String	 NAME = "Tracker Server v" + RobotTracker.VERSION;
    public  static final int 	 SERVER_PORT = 6000;
    public  static final int 	 RBC_PORT = 7001;

    // This is the maximum length of an incoming command.   Most commands come in the
    // form of 2 bytes ... one byte for command, one for robot ID (if needed).   However,
    // when in multi-camera tracking mode, the other two servers repeatedly send
    // tracking data to this server indicating the poses for all tracked robots as well
    // as the locations of the red/blue blocks.   In this case, the size needed for all
    // robot data (i.e., 6 IDs) is up to 150 bytes.   The size needed for each block is
    // up to 10 bytes each.   So if the request is for all robot data AND all block data,
    // this could be 250 bytes if 10 blocks are found ... 350 bytes if 20 blocks are found etc..
	private static final int 	 INPUT_BUFFER_LIMIT = 400; // allows 25 blocks and all robot data



    // This is the list of commands that the server accepts
    public static final byte	TRACKER_DATA_FROM_OTHER_SERVERS = 0;	// data from another server with tracking data
    public static final byte	GET_SINGLE_ROBOT_POSE 			= 1;	// gets a single (x,y,angle) pose
    public static final byte	GET_ALL_ROBOT_POSES 			= 2;	// gets all (x,y,angle) poses
    public static final byte	STATION_DATA 					= 4;	// incoming data for a station
    public static final byte	ROBOT_DATA 						= 5;	// incoming data for a robot
    public static final byte	TRACKER_DATA 					= 6;	// incoming data for tracker
    public static final byte	RBC				 				= 10;	// incoming RBC data

    // The main socket for communications
    public  boolean			online = false;
    public  boolean			multiTrack = false;
    private DatagramSocket	socket = null;
    private	RobotTracker	rb;
    public	int				trackerID;				// ID of this tracker (1, 2 or 3)
	public InetAddress[]	servers;				// Internet addresses for all the trackers


    // These are to be read from the RobotTracker and updates in the Tracking Panel and Results Dialog
    public	Pose[][]		posesFromServers;		// incoming poses from the 3 tracker servers
	public	String[]		stationDataToSend;		// outgoing data to other servers

	public TrackerServer(RobotTracker rt, int id, String[] addresses) {
		// The source of the tracking data is the TrackingPanel passed in
		rb = rt;

		// Keep track of the pose array so that we can add to it when a tracked pose is found
		posesFromServers = new Pose[3][8];

		// Keep track of data to send to other servers
		stationDataToSend = new String[3];
		for (int i=0; i<3; i++)
			stationDataToSend[i] = " ";

		trackerID = id;

        // Create a socket for communication
        try {
            socket = new DatagramSocket(SERVER_PORT);
         	debugLog("On-line");
         	online = true;
        } catch (SocketException e) {
        	errorLog("Cannot connect to network");
        }

        // Make sure that the addresses for both other RobotTracker servers
		// are valid addresses and that the machines are connected to the network.
		// This is only needed if we are in multi-tracker mode
		servers = new InetAddress[3];
		try {
			servers[0] = InetAddress.getByName(addresses[0]);
        } catch (Exception e) {
        	errorLog("Cannot connect to RobotTracker at station 1 with address: " + addresses[0]);
        }
		try {
			servers[1] = InetAddress.getByName(addresses[1]);
        } catch (Exception e) {
        	errorLog("Cannot connect to RobotTracker at station 2 with address: " + addresses[1]);
        }
		try {
			servers[2] = InetAddress.getByName(addresses[2]);
        } catch (Exception e) {
        	errorLog("Cannot connect to RobotTracker at station 3 with address: " + addresses[2]);
        }
	}

	// Display the error string as a log on the System console
	private void errorLog(String s) {
		System.out.println(NAME + " ERROR: " + s);
	}

	// Display the debug string as a log on the System console
	private void debugLog(String s) {
		//System.out.println(NAME + ": " + s);
	}

    // Start handling the incoming requests
    public void run() {
    	Pose	p = null; 	//variable to hold onto a single pose
    	int		robotID = 0;

    	// Set up the space to receive the incoming request
    	byte[]	receiveBuffer = new byte[INPUT_BUFFER_LIMIT];
    	byte[]  sendBuffer = null;

    	// data fromfor other stations
    	int 	stationId, len;
		byte[] 	d;

		// The infinite loop
        while(true) {
        	try {
        		Thread.sleep(0);
        	} catch (InterruptedException e) {
        		Thread.currentThread().interrupt();
        		break;
        	}

        	if (online == false) {
        		socket.close();
        		return;
        	}
            try {
				// Wait for an incoming client request
            	receiveBuffer = new byte[INPUT_BUFFER_LIMIT];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.setSoTimeout(100); //Wait at most 1/20th of a second
                socket.receive(receivePacket);

				// Extract the packet data that contains the request
                InetAddress address = receivePacket.getAddress();
				int clientPort = receivePacket.getPort();
				// Clear the pose data from the previous rounds
				for (int s=0; s<3; s++) {
					if (address.equals(servers[s])) {
						for (int i=0; i<8; i++)
							posesFromServers[s][i] = null;
					}
				}


                debugLog("Command received: (" + receivePacket.getData()[0] + ") from " + address + ":" + clientPort);

				// Decide what should be sent back to the client.  Build up an appropriate
				// result string to contain the requested data.
				String result = "";
				int command = receivePacket.getData()[0];

				switch(command) {
					case TRACKER_DATA_FROM_OTHER_SERVERS:
						if (!multiTrack) break;	// Ignore the incoming data if we are not multitracking
						String st = new String(receivePacket.getData(), 1, receivePacket.getLength()-1);
						StringTokenizer inData = new StringTokenizer(st,"|");
						debugLog("Received data from other stations: " + st);

						// Determine how many poses were sent
						try {
							int count = Integer.parseInt(inData.nextToken());
							for (int i=0; i<count; i++) {
								int index = Integer.parseInt(inData.nextToken());
								p = new Pose(Integer.parseInt(inData.nextToken()), Integer.parseInt(inData.nextToken()), Integer.parseInt(inData.nextToken()));
								for (int s=0; s<3; s++) {
									if (address.equals(servers[s]))
										posesFromServers[s][index] = p;
								}
							}

							// Now read the extra data (if any) that was sent by other servers and call the planner method
							for (int i=0; i<3; i++) {
								int sID = Integer.parseInt(inData.nextToken());
								String sData = inData.nextToken();
								if ((!sData.equals(" ")) && (i+1 == rb.stationID)) {
									if (rb.plannerHandler != null && rb.plannerHandler.isAlive()) {
										rb.plannerHandler.stationDataReceived(sID, sData);
									}
								}
							}
						} catch(NumberFormatException e) {
							// Occasionally, some pose data gets garbled...weird...likely a conflicting resource issue
						}

						break;

					case GET_SINGLE_ROBOT_POSE:
						robotID = receivePacket.getData()[1];
						p = rb.trackingPanel.posesFound[robotID];
						if (p == null)
							p = posesFromServers[0][robotID];
						if (p == null)
							p = posesFromServers[1][robotID];
						if (p == null)
							p = posesFromServers[2][robotID];
						if (p == null)
							p = new Pose(-1, -1, -1);
						result = p.x + "," + p.y + "," + p.angle;
						sendResponse(address, clientPort, result.getBytes());
						break;

					case GET_ALL_ROBOT_POSES:
						for (int i=0; i<8; i++) {
							p = rb.trackingPanel.posesFound[i];
							if (p == null)
								p = posesFromServers[0][i];
							if (p == null)
								p = posesFromServers[1][i];
							if (p == null)
								p = posesFromServers[2][i];
							if (p == null)
								p = new Pose(-1, -1, -1);
							result += p.x + "," + p.y + "," + p.angle + ",";
						}
						sendResponse(address, clientPort, result.getBytes());
						break;

					case ROBOT_DATA:
						stationId = (int)receiveBuffer[1]&0xFF;
						len = (int)receiveBuffer[2]&0xFF;
						d = new byte[len];
						for (int i=0;i<len;i++)
							d[i] = receiveBuffer[i+3];
						rb.bluetoothCoordinator.sendData(d);
						break;

					/*case TRACKER_DATA:
						stationId = (int)receiveBuffer[1]&0xFF;
						len = (int)receiveBuffer[2]&0xFF;
						d = new byte[len];
						for (int i=0;i<len;i++)
							d[i] = receiveBuffer[i+3];
						if (rb.plannerHandler != null && rb.plannerHandler.isAlive())
							rb.plannerHandler.trackerDataReceived(d);
						break;*/

					default:
						//errorLog("unrecognized command (" + receivePacket.getData()[0] + ")");
						continue;
						//online = false; socket.close(); return;
				}
			} catch(java.net.SocketTimeoutException  e) {
				// Timed out ... do nothing
            } catch(IOException e) {
				errorLog(": Error receiving client requests");
				online = false; socket.close(); return;
            }

			// Now send the latest tracking data to the other servers if in multi-tracker mode
			if (multiTrack) {
				// Build up the tracking data string
				String result = Character.valueOf((char)TRACKER_DATA_FROM_OTHER_SERVERS).toString() + "";	// Add the command to the beginning

				// Determine how many poses were found and copy them over to a temp array
				// because another process may be altering the poses at the same time
				Pose[] tempPoses = new Pose[8];
				for (int i=0; i<8; i++)
					tempPoses[i] = rb.trackingPanel.posesFound[i];

				int count = 0;
				for (int i=0; i<8; i++)
					if (rb.trackingPanel.posesFound[i] != null) count++;
				result += count + "|";

				// Add all the pose data, as long as a pose was found
				for (int i=0; i<8; i++) {
					p = tempPoses[i];
					if (p != null)
						result += i + "|" + p.x + "|" + p.y + "|" + p.angle + "|";
				}

				// Now append the station data for the other servers (each is a string without vertical bars)
				// Preceed the dadat by the station ID so that others will know who sent it
				for (int i=0; i<3; i++) {
					result += rb.stationID + "|" + stationDataToSend[i] + "|";
					stationDataToSend[i] = " ";
				}

				// Now send all this data to the other servers
				String resultCopy = new String(result);
				for (int s=0; s<3; s++) {
					if (s != trackerID-1) {
						try {
							sendResponse(servers[s], SERVER_PORT, result.getBytes());
			    		} catch (Exception e) {
			        		errorLog("ERROR: Cannot send poses to Tracker at station " + (s+1));
			    		}
					}
				}
			}
        }
    }

    // This method sends a given response back to the client
    private void sendResponse(InetAddress address, int clientPort, byte[] response) {
        try {
            // Create a packet to contain the response and send it
            DatagramPacket sendPacket = new DatagramPacket(response, response.length, address, clientPort);
            socket.send(sendPacket);
        } catch (IOException e) {
        	errorLog(": Error sending response to client" + address + ":" + clientPort);
        	online = false;
        	socket.close();
        	return;
        }
    }
}