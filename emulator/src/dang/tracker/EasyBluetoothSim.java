package dang.tracker;



import java.util.ArrayList;
import java.util.LinkedList;

import dang.exceptions.SpinError;

/**
 * a bluetooth simulator that simply links the robot with 
 * the appropriate "hardware", and doesn't bother with pipes and
 * streams and all the stuff that slowed it down.
 * @author darrylhill
 *
 */
public class EasyBluetoothSim  {
	
	private RobotTracker rb;
	private volatile LinkedList<Integer> queue = new LinkedList<Integer>();				// stores outgoing messages which are fed to the server one at a time

	protected EasyBluetoothSim(RobotTracker mainApp) {
		rb = mainApp;
	}

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
 		else if (command==RBCConstants.PLANNER_DATA){
 			int flag = data.get(0);
 			data.remove(0);
			rb.receivedPlannerData(data, flag);
 		}
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
 	/*	else if (command==RBCConstants.ROBOT_DATA) {
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

	public void sendData(byte[] data) {
		sendData(data, true);
	}
	
	public void sendSpecificData(byte[] data) {
		sendData(data, false);
	}
	
	/*
	 * If there is no data, this method never exits. Because that is how SPIN does it
	 */
	public Integer deque() throws Exception{
		while (queue.size() == 0){
			Thread.sleep(10);
		}
		
		return queue.removeFirst();
	}


	public void sendDebugData(byte[] data) {
		int length = data.length;
		queue.add(RBCConstants.BEGIN_DEBUG_DATA);
		queue.add(length);
 		for (int i=0;i<length;i++) {
 			queue.add((data[i]&0xFF));
 		}
	}
	
	public void sendData(byte[] data, boolean addCommand){
		int length = data.length;
		if (addCommand) {
			System.out.println("******adding command**********");
			queue.add(RBCConstants.BEGIN_DATA);
			queue.add(length);
		} 
		for (int i=0;i<length;i++) {
 			queue.add((data[i]&0xFF));
		}
	}
}
