package dang.program.objects;

import java.io.IOException;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;
import dang.robot.CameraReading;

public class CMUCam extends SpinObject {

	//all the methods with no arguments
	public static final int START = -1;
	public static final int SETFULLWINDOW = -2;
	public static final int READCOLOR = -3;
	public static final int GETRED = -4;
	public static final int GETGREEN = -5;
	public static final int GETBLUE = -6;
	public static final int TRACKCOLOR = -7;
	public static final int GETCENTERX = -8;
	public static final int GETCENTERY = -9;
	public static final int GETTOPLEFTX = -10;
	public static final int GETTOPLEFTY = -11;
	public static final int GETBOTTOMRIGHTX = -12;
	public static final int GETBOTTOMRIGHTY = -13;
	public static final int GETPIXELS = -14;
	public static final int GETCONFIDENCE = -15;
	//the methods with arguments
	public static final int SETCONSTRAINEDWINDOW = -16;
	public static final int SETTRACKCOLOR = -17;

	boolean started = false;
	int topleftx, toplefty, bottomrightx, bottomrighty;
	final int MAX_X = 80;
	final int MAX_Y = 143;
	//colors read after call the READCOLOR
	int r,g,b;
	//the tracked colors after call to TRACKCOLOR
	int tred,tgreen,tblue,sensitivity, confidence;
	//the object passed back from the robot with the relevant 
	//camera data
	CameraReading reading = null;



	public CMUCam(Program program) throws Exception{
		super("CMUCam", program);
		addMethod(new CMUMethod("start",this,START));
		addMethod(new CMUMethod("trackcolor", this, TRACKCOLOR));
		addMethod(new CMUMethod("setfullwindow", this, SETFULLWINDOW));
		addMethod(new CMUMethod("readcolor", this, READCOLOR));
		addMethod(new CMUMethod("getred", this, GETRED));
		addMethod(new CMUMethod("getgreen", this, GETGREEN));
		addMethod(new CMUMethod("getblue", this, GETBLUE));
		addMethod(new CMUMethod("getcenterx", this, GETCENTERX));
		addMethod(new CMUMethod("getcentery", this, GETCENTERY));
		addMethod(new CMUMethod("gettopleftx", this, GETTOPLEFTX));
		addMethod(new CMUMethod("gettoplefty", this, GETTOPLEFTY));
		addMethod(new CMUMethod("getbottomrightx", this, GETBOTTOMRIGHTX));
		addMethod(new CMUMethod("getbottomrighty", this, GETBOTTOMRIGHTY));
		addMethod(new CMUMethod("getpixels", this, GETPIXELS));	
		addMethod(new CMUMethod("getconfidence", this, GETCONFIDENCE));
		addMethod(new CMUMethod("setconstrainedwindow", this, SETCONSTRAINEDWINDOW));
		addMethod(new CMUMethod("settrackcolor", this, SETTRACKCOLOR));

	}

	private class CMUMethod extends HardwareMethod{
		int function;

		/**
		 * As much fun as it would be to do a more robust implementation,
		 * I am on a strict time budget. For now, the camera will only track blocks,
		 * and probably not even check the RGB values. It wouldn't be exceptionally
		 * difficult to do a full color tracking implementation, but it would take
		 * time to get all the proper RGB values in the lab, and a full day or two
		 * of implementation, which is time I may or may not
		 * have.
		 * 
		 * @param name the name the program will use to find the method
		 * @param program the calling program (the 'SpinObject'
		 * @param function the int code of the method
		 * @param params the optional list of parameters
		 * @throws SyntaxError because you are a sloppy typer
		 */
		public CMUMethod(String name, Program program, int function, String ... params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
			this.function = function;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					Debug.debug("executing CMUMethod function: "+ function);
					if (function == START){
						Debug.debug("Starting cam");
						started = true;
					}
					Debug.debug("Started: " + started);
					if (!started) return Variable.FALSE;
					switch(function){
					case START:
					case SETFULLWINDOW:
						//not sure what purpose, if any, this will have. Likely affect the width
						//of the camera read. Not to be confused with the coordinates of the blob
						//that the camera is reading
						topleftx = 1;
						toplefty = 1;
						bottomrightx = MAX_X;
						bottomrighty = MAX_Y;

						return Variable.TRUE;

					case READCOLOR:
						/*
						 * this will be tricky. What color are we reading? I doubt I will
						 * even implement this. Just make up some values. It might not be
						 * that hard actually. Temp for now
						 */
						reading = getRobot().getCameraReading(READCOLOR);

						return Variable.TRUE;

					case GETRED:
						return new AnonVariable(reading.getRed());

					case GETGREEN:
						return new AnonVariable(reading.getGreen());

					case GETBLUE:
						return new AnonVariable(reading.getBlue());

					case TRACKCOLOR:
						Debug.debug("executing TrackColor");

						/*
						 * This is where the most important stuff happens. We look to see
						 * if a block is in the line of sight. I think all my obstacles need
						 * color values. Then I can do this dynamically. Is that a good idea?
						 * If I have time.
						 */
						reading = getRobot().getCameraReading(TRACKCOLOR);
						break;
					case GETCENTERX :
						return new AnonVariable(reading.getCenterX());
					case GETCENTERY:
						return new AnonVariable(reading.getCenterY());
					case GETTOPLEFTX:
						return new AnonVariable(reading.getTopLeftX());
					case GETTOPLEFTY :
						return new AnonVariable(reading.getTopLeftY());
					case GETBOTTOMRIGHTX:
						return new AnonVariable(reading.getBottomRightX());
					case GETBOTTOMRIGHTY:
						return new AnonVariable(reading.getBottomRightY());
					case GETPIXELS:
						return new AnonVariable(reading.getPixels());
					case GETCONFIDENCE:
						return new AnonVariable(reading.getConfidence());
						//the methods with arguments
					case SETCONSTRAINEDWINDOW:
						//this stores the values, but I don't think I will be
						//using them at all. Only full windows in the sim
						topleftx = parameters[0].evaluateToValue(getCallingInstance());
						toplefty = parameters[1].evaluateToValue(getCallingInstance());
						bottomrightx = parameters[2].evaluateToValue(getCallingInstance());
						bottomrighty = parameters[3].evaluateToValue(getCallingInstance());

						return Variable.TRUE;

					case SETTRACKCOLOR:
						tred = parameters[0].evaluateToValue(getCallingInstance());
						tgreen = parameters[1].evaluateToValue(getCallingInstance());
						tblue = parameters[2].evaluateToValue(getCallingInstance());
						sensitivity = parameters[3].evaluateToValue(getCallingInstance());
						getRobot().setTrackColor(tred,tgreen,tblue,sensitivity);
						return Variable.TRUE;
					default:
						throw new CompilerError("Shouldn't be here, no function found in CMUMethod: "+function);
					}
					return Variable.FALSE;
				}
			};
		}
	}
	/*
	 * PUB Start
	 * 
	 * PUB SetFullWindow
  {{ Set the camera's image window to full size (i.e., 80x143). }}

  PUB SetConstrainedWindow(X_UpperLeft, Y_UpperLeft, X_BottomRight, Y_BottomRight)
  {{ Set the portion of the camera's image that you want to process.
     Maximum values are 1, 1, 80, 143. }}

 PUB ReadColor
  {{ Read the mean color value in terms of red, green and blue components.
     Use GetRed, GetGreen and GetBlue to extract the values. }}

PUB GetRed
  {{ Get the red value from the last call to ReadColor. }}
  return red


PUB GetGreen
  {{ Get the green value from the last call to ReadColor. }}
  return green


PUB GetBlue
  {{ Get the blue value from the last call to ReadColor. }}
  return blue

PUB SetTrackColor(r, g, b, sensitivity)
  {{ Set the color to be tracked currently. The sensitivity is the allowable +- range
     for each color component during tracking.  Call this before calling TrackColor.}}

PUB TrackColor
  {{ Track the color previously specified by the call to SetTrackColor. }}

PUB GetCenterX
  {{ Return the x component of the blob's center of mass.  Call only after TrackColor. }}
  return midX

PUB GetCenterY
  {{ Return the y component of the blob's center of mass.  Call only after TrackColor. }}
  return midY

PUB GetTopLeftX
  {{ Return the x component of the blob bounding box's top left corner.   Call only after TrackColor. }}
  return xTL

PUB GetTopLeftY
  {{ Return the y component of the blob bounding box's top left corner.   Call only after TrackColor. }}
  return yTL

PUB GetBottomRightX
  {{ Return the x component of the blob bounding box's bottom right corner.   Call only after TrackColor. }}
  return xBR

PUB GetBottomRightY
  {{ Return the x component of the blob bounding box's bottom right corner.   Call only after TrackColor. }}
  return yBR

PUB GetPixels
  {{ Return the number of pixels in the tracked blob.  The actual value should be (pixels+4)/8.  Call only after TrackColor. }}
  return pixels

PUB GetConfidence
  {{ Return the confidence level of the track (i.e., 0 to 255 where 8 is poor & 50 is very good).  Call only after TrackColor. }}
  return confidence


	 */
}
