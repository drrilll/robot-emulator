package dang.program.objects;

import dang.exceptions.SyntaxError;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class End extends HardwareMethod {

	public End(Program program)
			throws SyntaxError {
		super("end", MethodType.PUB, null, null, program);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public MethodInstance getMethodInstance(MethodInstance method){
		return new MethodInstance(this, method){
			@Override
			public Variable execute(Expression[] params) throws SyntaxError {
				robot.setRunning(false);
				return getVariables().get("Result");
			}

		};
	}
}
