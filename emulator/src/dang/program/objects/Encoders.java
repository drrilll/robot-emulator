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

public class Encoders extends SpinObject {

	public static final int START = -1;
	public static final int GETLEFTCOUNT = -2;
	public static final int GETRIGHTCOUNT = -3;
	public static final int RESETCOUNTERS = -4;

	private boolean started = false;


	public Encoders(Program main) throws Exception {
		super("Encoders", main);
		addMethod(new EncoderMethod("start", main, START));
		addMethod(new EncoderMethod("getleftcount", main, GETLEFTCOUNT));
		addMethod(new EncoderMethod("getrightcount", main, GETRIGHTCOUNT));
		addMethod(new EncoderMethod("resetcounters", main, RESETCOUNTERS));


	}

	/*
	 * Pub start
	 * pub getleftcount
	 * pub getrightcount
	 * pub resetcounters
	 */

	private class EncoderMethod extends HardwareMethod {
		private int type;

		public EncoderMethod(String name, Program program, int methodType) throws SyntaxError{
			super(name, MethodType.PUB, null, null, program);
			type = methodType;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					if (type == START){
						started = true;
						getRobot().startEncoders();
					}
					if (!started) return Variable.FALSE;
					switch(type){
					case GETLEFTCOUNT:
						return new AnonVariable(getRobot().getLeftEncoderCount());
					case GETRIGHTCOUNT:
						return new AnonVariable(getRobot().getRightEncoderCount());
					case RESETCOUNTERS:
						return new AnonVariable(getRobot().resetCounters());

					}
					return Variable.FALSE;
				}
			};

		}
	}
}
