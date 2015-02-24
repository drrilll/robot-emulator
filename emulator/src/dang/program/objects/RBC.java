package dang.program.objects;



import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Constant;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;
import dang.tracker.BluetoothSimulator;
import dang.tracker.EasyBluetoothSim;
import dang.tracker.RBCConstants;

/**
 * What seemed like a good idea in the beginning, separate classes for each method
 * (to ensure that each could accomplish a specialized task) has turned into pure code
 * bloat here. Oh well. Will fix if I have time.
 * 
 * @author darrylhill
 * 
 * inner classes needed: 							** means done
 * PUB DebugLongCR(toSend) 							**
 * PUB DebugLong(toSend)  							**
 * PUB DebugStrCR(output) 							**
 * PUB DebugStr(output) 							**
 * PUB DebugCharCR(output)							**
 * PUB DebugChar(output)							**
 * PUB DebugCR   //newline							**
 * PUB DebugClear 									**
 * PUB SendDataToRobot(station, outData, count)
 * PUB SendResetImage
 * PUB SendDataToPc(outData, count, flag)
 * PUB ReceiveData(dataReceived) | aByte
 * PUB ReceiveDebugData(dataReceived) | aByte
 * PUB SendTrackedDataToPc(x1, y1, x2, y2)
 * PUB SendTrackedColorToPc(red, green, blue)
 * PUB DisableDebugInput
 * PUB EnableDebugInput
 *
 */
public class RBC extends SpinObject{

	private EasyBluetoothSim blue = null;


	public RBC(Program main) throws Exception {
		super("RBC", main);
		//setRobot(main.getRobot());
		addMethod(new Init(this));
		String[] params = {"string"};
		addMethod(new DebugClear(this));
		addMethod(new DebugStrCr(params,this));
		addMethod(new DebugStr(params, this));
		addMethod(new DebugLong(params, this));
		addMethod(new DebugLongCr(params, this));
		addMethod(new DebugCr(this));
		addMethod(new SendDataToPc(this,"outdata", "count", "flag"));
		addMethod(new SendResetImage(this));
		addMethod(new SendDataToRobot(this, "station","outdata","count"));
		addMethod(new DebugChar(params, this));
		addMethod(new DebugCharCr(params, this));
		addMethod(new ReceiveData("receivedata", this, RBCConstants.BEGIN_DATA, "datain"));
		addMethod(new ReceiveData("receivedebugdata", this, RBCConstants.BEGIN_DEBUG_DATA, "datain"));
		addMethod(new SendTrackedDataToPc(this, "x1","y1","x2","y2"));
		addMethod(new SendTrackedColorToPc(this,"red","green","blue"));
		addMethod(new DebugInput("enabledebuginput", this, RBCConstants.ENABLE_INPUT));
		addMethod(new DebugInput("disabledebuginput", this, RBCConstants.DISABLE_INPUT));


		addConstant(new Constant("rbc_begin_data", 2, getMemory()));
		addConstant(new Constant("rbc_begin_debug_data", 3, getMemory()));

		// These are the types of messages (modes) that can be sent to the PC
		addConstant(new Constant("rbc_debug_output", 1, getMemory()));
		addConstant(new Constant("rbc_debug_output_cr", 2, getMemory()));
		addConstant(new Constant("rbc_debug_file", 3, getMemory()));
		addConstant(new Constant("rbc_debug_file_cr", 4, getMemory()));

		addConstant(new Constant("rbc_planner_data", 5, getMemory()));
		addConstant(new Constant("rbc_enable_input", 7, getMemory()));
		addConstant(new Constant("rbc_disable_input", 8, getMemory()));
		addConstant(new Constant("rbc_robot_begin", 11, getMemory()));

		addConstant(new Constant("rbc_start_camera", 6, getMemory()));
		addConstant(new Constant("rbc_connection_request", 10, getMemory()));
		addConstant(new Constant("rbc_conn_ack", 8, getMemory()));
		addConstant(new Constant("rbc_debug_clear", 9, getMemory()));

		addConstant(new Constant("rbc_image_trace", 10, getMemory()));
		addConstant(new Constant("rbc_robot_data", 11, getMemory()));
		addConstant(new Constant("rbc_image_clear", 12, getMemory()));
		addConstant(new Constant("rbc_image_color", 13, getMemory()));

		// Flags for SendDataToPc

		addConstant(new Constant("output_to_log", 1, getMemory()));
		addConstant(new Constant("output_to_file", 2, getMemory()));
		addConstant(new Constant("output_to_log_and_file", 3, getMemory()));
		addConstant(new Constant("output_to_none", 4, getMemory()));

	}

	public byte[] toByteArray(String str){
		byte[] data = new byte[str.length()+2];
		for (int i = 0; i < str.length(); i++){
			data[i+2] = (byte)str.charAt(i);
		}
		return data;
	}

	/**
	 * the init method grabs a reference to the bluetooth. So if
	 * you don't run it, any bluetooth commands you run will 
	 * throw a NullPointerException
	 * 
	 * @author darrylhill
	 *
	 */
	private class Init extends HardwareMethod {
		public Init(Program program) throws SyntaxError {
			super("init", MethodType.PUB, null, null,program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws SpinError, RobotNotRunningException {		
					blue = getRobot().getBluetooth();
					return Variable.TRUE;
				}	
			};
		}

	}
	//class for RBC.DebugClear command
	private class DebugClear extends HardwareMethod {
		public DebugClear(Program program) throws SyntaxError{
			super("debugclear", MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws SpinError, RobotNotRunningException {
					int command = RBCConstants.CLEAR_DEBUG;
					blue.handleData(command, null);
					return Variable.TRUE;			
				}
			};
		}
	}

	private class DebugStrCr extends HardwareMethod {
		public DebugStrCr(String[] params, Program program) throws SyntaxError{
			super("debugstrcr",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					if (getCallingInstance() == null){
						Debug.debug("debugstrcr calling instance is null");
					}else{
						Debug.debug("debugstrcr calling instance is NOT null");
					}
					//rather then cut and paste everything, we abstract it
					sendStringToRBC(parameters, RBCConstants.DEBUG_OUTPUT_LINE, getCallingInstance());

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	private class DebugStr extends HardwareMethod {
		public DebugStr(String[] params, Program program) throws SyntaxError{
			super("debugstr",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					
					//rather then cut and paste everything, we abstract it
					sendStringToRBC(parameters, RBCConstants.DEBUG_OUTPUT, getCallingInstance());

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	/**
	 * Similar to the above, except we evaluate the expression into a long,
	 * change that long into a string and output it. Easy peasy
	 * @author darrylhill
	 *
	 */
	private class DebugLong extends HardwareMethod {
		public DebugLong(String[] params, Program program) throws SyntaxError{
			super("debuglong",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {

					//rather then cut and paste everything, we abstract it
					sendLongToRBC(parameters, RBCConstants.DEBUG_OUTPUT, getCallingInstance());

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	private class DebugLongCr extends HardwareMethod {
		public DebugLongCr(String[] params, Program program) throws SyntaxError{
			super("debuglongcr",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {

					//rather then cut and paste everything, we abstract it
					sendLongToRBC(parameters, RBCConstants.DEBUG_OUTPUT_LINE,getCallingInstance());

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	private class DebugCr extends HardwareMethod {
		public DebugCr(Program program) throws SyntaxError{
			super("debugcr",MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws SpinError, RobotNotRunningException {

					ArrayList<Integer> data = new ArrayList<Integer>();
					int command = RBCConstants.DEBUG_OUTPUT_LINE;

					data.add(0);

					//get the robot to send it
					blue.handleData(command, data);

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	private class DebugCharCr extends HardwareMethod {
		public DebugCharCr(String[] params, Program program) throws SyntaxError{
			super("debugcharcr",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {

					ArrayList<Integer> data = new ArrayList<Integer>();
					int command = RBCConstants.DEBUG_OUTPUT_LINE;

					//the expression passed in should evaluate to a char
					data.add((int) parameters[0].evaluateToValue(getCallingInstance()));

					//get the robot to send it
					blue.handleData(command, data);

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}

	private class DebugChar extends HardwareMethod {
		public DebugChar(String[] params, Program program) throws SyntaxError{
			super("debugchar",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {

					ArrayList<Integer> data = new ArrayList<Integer>();
					int command = RBCConstants.DEBUG_OUTPUT;

					//the expression passed in should evaluate to a char
					data.add((int) parameters[0].evaluateToValue(getCallingInstance()));

					//get the robot to send it
					blue.handleData(command, data);

					//nothing exploded, so return true
					return Variable.TRUE;
				}
			};
		}
	}
	
	//three parameters
	private class SendDataToPc extends HardwareMethod {
		public SendDataToPc(Program program, String...params) throws SyntaxError{
			super("senddatatopc",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					int command = RBCConstants.PLANNER_DATA;
					
					ArrayList<Integer> data = new ArrayList<Integer>();
					
					//3rd param, number 2, is the flag
					int flag = parameters[2].evaluateToValue(getCallingInstance());
					if (flag >0) data.add(flag);
					int count = parameters[1].evaluateToValue(getCallingInstance());
					count = count%256;
					int arrayAddress = parameters[0].evaluateToValue(getCallingInstance());
					getMemory().position(arrayAddress);
					if (flag > -1){
						for (int i = 0; i < count; i ++){
							//Ok here is gets a little tricky. The first element should
							//be an address to an array of bytes. So we have to dip into 
							//memory to retrieve them.
							//the memory automatically increments in this case
							data.add((int) getMemory().get()&0xFF);
						}
					}else{
						data.add((int) getMemory().get()&0xFF);
					}
					blue.handleData(command, data);
					
					//slight chance the universe will implode. If not, send
					//back the OK...	
					return Variable.TRUE;
					
				}
			};
		}
	}
	
	//no parameters
	private class SendResetImage extends HardwareMethod {
		public SendResetImage(Program program, String...params) throws SyntaxError{
			super("sendresetimage",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					ArrayList<Integer> data = new ArrayList<Integer>();
					blue.handleData(RBCConstants.IMAGE_RESET, data);
					return Variable.TRUE;
				}
			};
		}
	}
	
	//three parameters (station, outData, count)
	private class SendDataToRobot extends HardwareMethod {
		public SendDataToRobot(Program program, String...params) throws SyntaxError{
			super("senddatatorobot",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					ArrayList<Integer> data = new ArrayList<Integer>();
					int command = RBCConstants.ROBOT_DATA;
					int count = parameters[2].evaluateToValue(getCallingInstance());
					count = (count+1)%256;
					int station = parameters[0].evaluateToValue(getCallingInstance());
					station = station%256;
					data.add(station);
					
					int arrayAddress = parameters[1].evaluateToValue(getCallingInstance());
					getMemory().position(arrayAddress);
					for (int i = 0; i< count; i++){
						data.add((int) getMemory().get());
					}
					blue.handleData(command, data);
					return Variable.TRUE;
				}
			};
		}
	}

	//one parameter
	private class ReceiveData extends HardwareMethod {
		int command;
		public ReceiveData(String name, Program program, int command, String...params) throws SyntaxError{
			super(name,MethodType.PUB, params, null, program);
			this.command = command;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					/*
					 * So a little different here. Read the data in, 
					 * and put it into memory. Where do I read it from?
					 * Also, it locks up here forever if no appropriate
					 * data is forthcoming, a la the real SPIN
					 */
					int com = -1;
					while (com !=command){
						com = blue.deque();
						Debug.debug("received data: "+ com);
					}

					int length = blue.deque();
					int arrayAddress = parameters[0].evaluateToValue(getCallingInstance());
					getMemory().position(arrayAddress);
					getMemory().put((byte)length);
					for (int i = 1; i < length+1; i++){
						getMemory().put(blue.deque().byteValue());
					}

					return Variable.TRUE;
				}
			};
		}
	}
	

	//four parameters
	private class SendTrackedDataToPc extends HardwareMethod {
		public SendTrackedDataToPc(Program program, String...params) throws SyntaxError{
			super("sendtrackeddatatopc",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					int command = RBCConstants.TRACKER_DATA;
					ArrayList<Integer> data = new ArrayList<Integer>();
					int x1 = parameters[0].evaluateToValue(getCallingInstance());
					int y1 = parameters[1].evaluateToValue(getCallingInstance());
					int x2 = parameters[2].evaluateToValue(getCallingInstance());
					int y2 = parameters[3].evaluateToValue(getCallingInstance());
					x1 = x1%256;
					y1 = y1%256;
					x2 = x2%256;
					y2 = y2%256;
					data.add(x1);
					data.add(y1);
					data.add(x2);
					data.add(y2);
					blue.handleData(command, data);
					return Variable.TRUE;
				}
			};
		}
	}

	//three parameters
	private class SendTrackedColorToPc extends HardwareMethod {
		public SendTrackedColorToPc(Program program, String...params) throws SyntaxError{
			super("sendtrackedcolortopc",MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					int command = RBCConstants.IMAGE_COLOR;
					ArrayList<Integer> data = new ArrayList<Integer>();
					int red = parameters[0].evaluateToValue(getCallingInstance());
					int green = parameters[1].evaluateToValue(getCallingInstance());
					int blu = parameters[2].evaluateToValue(getCallingInstance());
					
					red += 80;
					green += 70;
					blu += 60;
					if(red>255)red=255;
					if(green>255)green=255;
					if(blu>255)blu=255;
					data.add(red);
					data.add(green);
					data.add(blu);
					blue.handleData(command, data);
					return Variable.TRUE;
				}
			};
		}
	}
	
	//three parameters
	private class DebugInput extends HardwareMethod {

		int command;
		public DebugInput(String name,Program program,int command) throws SyntaxError{
			super(name,MethodType.PUB, null, null, program);
			this.command = command;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					ArrayList<Integer> data = new ArrayList<Integer>();
					blue.handleData(command, data);
					return Variable.TRUE;
				}
			};
		}
	}


	/*


PUB EnableDebugInput
  Bluetooth.sendByte(RBC_BEGIN_DATA)
  Bluetooth.sendByte(RBC_ENABLE_INPUT)

'Disable the input bar on the console
PUB DisableDebugInput
  Bluetooth.sendByte(RBC_BEGIN_DATA)
  Bluetooth.sendByte(RBC_DISABLE_INPUT)


PUB SendTrackedColorToPc(red, green, blue)
  Bluetooth.sendByte(RBC_BEGIN_DATA)
  Bluetooth.sendByte(RBC_IMAGE_COLOR)
  Bluetooth.sendByte(3)
  ' Add a constant offset to the colors
  red := red + 80
  green := green + 70
  blue := blue + 60
  if (red > 255)
    red := 255
  if (green > 255)
    green := 255
  if (blue > 255)
    blue := 255
  Bluetooth.sendByte(red//256)
  Bluetooth.sendByte(green//256)
  Bluetooth.sendByte(blue//256)

PUB SendTrackedDataToPc(x1, y1, x2, y2)
  Bluetooth.sendByte(RBC_BEGIN_DATA)
  Bluetooth.sendByte(RBC_IMAGE_TRACE)
  Bluetooth.sendByte(4)
  Bluetooth.sendByte(x1//256)
  Bluetooth.sendByte(y1//256)
  Bluetooth.sendByte(x2//256)
  Bluetooth.sendByte(y2//256) 

PUB ReceiveDebugData(dataReceived) | aByte
  exactly same as (except for the flag, which is RBC_BEGIN_DEBUG_DATA):

PUB ReceiveData(dataReceived) | aByte
  'Read bytes until the the Begin Data signal is received
  repeat
    aByte := Bluetooth.GetByte
  until (aByte == RBC_BEGIN_DATA)

  'Now get the actual data
  aByte := Bluetooth.GetByte
  bytefill(@dataIn, 0, aByte+1)
  dataIn[0] := aByte
  Bluetooth.getBytes(@dataIn+1, aByte)  
  bytemove(dataReceived, @dataIn, aByte+1)


 PUB SendDataToRobot(station, outData, count)
  'Send the data
  Bluetooth.SendByte(RBC_BEGIN_DATA)
  Bluetooth.SendByte(RBC_ROBOT_DATA)
  Bluetooth.SendByte((count+1)//256)
  Bluetooth.SendByte(station//256)
  Bluetooth.SendBytes(outData, count)

 PUB SendDataToPc(outData, count, flag)
  SendData(outData, count, RBC_PLANNER_DATA, flag)
 PRI SendData(output, count, command, flag)
  Bluetooth.SendByte(RBC_BEGIN_DATA)
  Bluetooth.SendByte(command)
  if (flag > 0)
    Bluetooth.SendByte(flag)
  Bluetooth.SendByte(count//256)
  if (flag > -1)
    Bluetooth.SendBytes(output, count)
  else
    Bluetooth.SendByte(output)
  	 
 PUB SendResetImage
  Bluetooth.SendByte(RBC_BEGIN_DATA)
  Bluetooth.SendByte(RBC_IMAGE_CLEAR)
  
  


	 */

	private void sendLongToRBC(Expression[] parameters, int command, MethodInstance method) 
			throws Exception{
		String number = Integer.toString(parameters[0].evaluateToValue(method));
		ArrayList<Integer> data = new ArrayList<Integer>();
		data.add(number.length());
		for (int i = 0; i < number.length(); i++){
			data.add((int)number.charAt(i));
		}
		blue.handleData(command, data);
	}


	private void sendStringToRBC(Expression[] parameters, int command, MethodInstance method) 
			throws Exception{
		int location = parameters[0].evaluateToValue(method);
		int length = 0;
		//find out how long the string is
		Debug.debug("location for string is: "+location);
		while(getMemory().get(location + length)!=0){
			length ++;
		}
		Debug.debug("Length of string: " +length);		

		//we can't use absolute position for this type of bulk
		//move, so we set the position
		Debug.debug("setting memory to location: " +location);
		getMemory().position(location);
		byte[] data = new byte[length];

		//get the data
		getMemory().get(data, 0, length);
		ArrayList<Integer> dataInt = new ArrayList<Integer>();
		for (Byte b : data){
			dataInt.add((int)b);
		}

		//send to the robot, which sends it to the bluetooth simulator
		blue.handleData(command, dataInt);
	}

}
