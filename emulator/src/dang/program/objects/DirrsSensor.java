package dang.program.objects;

import java.io.IOException;

import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class DirrsSensor extends SpinObject {

	public DirrsSensor(Program main) throws Exception{
		super("dirrssensor", main);
		addMethod(new DistanceCM(this));
	}



	/*
	 * The official Spin specification:
	 * 
	PUB DistanceCM | aByte
  	{{ Return the distance (in centimeters) to the object in front of the DIRRS+.
     -1 is returned if no object is detected (usually more than 80cm away).
      0 is returned if the object is between 7 to 10cm away.
      Invalid data is returned if the object is less than 7cm away. }}

	 */

	private class DistanceCM extends HardwareMethod{
		public DistanceCM(Program program) throws SyntaxError{
			super("distancecm", MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					//the robot handles all the nitty gritty
					return new AnonVariable((int)getRobot().dirrsDistanceCM());
				}
			};
		}
	}


}