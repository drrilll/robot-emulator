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

public class BlockSensor extends SpinObject {

	public BlockSensor(Program program) throws Exception{
		super ("BlockSensor", program);
		addMethod(new Detect("detect", this));
	}

	private class Detect extends HardwareMethod{
		public Detect(String name, Program program) throws SyntaxError{
			super (name, MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					// If there is a block within the block area, we return 1
					// otherwise return 0. That is the spec described in the SPIN
					// file so it is what we do here
					//return getRobot().blockDetect().size() == 0 ?  Variable.FALSE : new AnonVariable(1) ;
					return getRobot().blockDetect() ?  new AnonVariable(1) : Variable.FALSE;
				}
			};
		}
	}
}
