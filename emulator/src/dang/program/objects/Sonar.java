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

public class Sonar extends SpinObject {

	public Sonar(Program program) throws Exception{
		super("PingSensor", program);
		addMethod(new DistanceCM(this));
	}
	
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
					return new AnonVariable((int)getRobot().sonarDistanceCM());
				}
				
				
			};
		}
		
	}

}
