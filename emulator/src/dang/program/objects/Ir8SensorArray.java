package dang.program.objects;

import java.io.IOException;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;

public class Ir8SensorArray extends SpinObject {

	//one long in memory, plus one return value
	private static final int MEMORYCAPACITY = 12;
	byte sensor = 0;

	public byte getSensor() {
		return sensor;
	}

	public void setSensor(byte sensor){
		this.sensor = sensor;
	}


	public Ir8SensorArray(Program main) throws Exception {
		super("Ir8SensorArray", main);
		Method capture = new Capture(this);
		String[] params = {"sensorNumber"};
		Method detect = new Detect(params, this);
		addMethod(capture);
		addMethod(detect);
	}

	private class Detect extends HardwareMethod{

		public Detect(String[] params, Program program) throws SyntaxError{
			super("detect", MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters) 
						throws Exception {


					//we don't have any "SPIN" code to run, so we don't need to store the variable
					//we can just evaluate it and use the value.
					byte sensorNumber = (byte) parameters[0].evaluateToValue(getCallingInstance());
					//Debug.debug("sensor number from the expression: " +sensorNumber);
					Variable ret = new AnonVariable(VariableType.BYTE,getRobot().getSensor(sensorNumber));
					Debug.debug("Return from detect: " + ret);
					return ret;
				}
			};
		}

	}

	private class Capture extends HardwareMethod{
		public Capture(Program program) throws SyntaxError{
			super("capture",MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters) 
						throws SpinError, RobotNotRunningException {
					//we query the environment, and see if any sensors are triggered
					//i will have to do line intersect manually
					//otherwise we shove the values into sensor as a bit map
					//hey, this entire program is grossly innefficient, but I save
					//a few bytes here. Been doing C programming lately.

					/*
					 * I can cheat, and hardcode the sensor lines relative to the robot position
					 * and at a 0 angle. I can then rotate and translate to get a line wherever, that
					 * I can check if it crosses any walls or obstacles.
					 */
					setSensor(getRobot().getSensor());
					return new AnonVariable(VariableType.LONG,0);
				}
			};
		}
	}

}
