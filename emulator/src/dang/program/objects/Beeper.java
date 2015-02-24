package dang.program.objects;

import java.awt.Toolkit;
import java.io.IOException;

import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class Beeper extends SpinObject {

	public static final int STARTUP = -1;
	public static final int SHUTDOWN = -2;
	public static final int OK = -3;
	public static final int ERROR = -4;


	/**
	 * Wish I had a better beeper implementation. I think it beeps, 
	 * but so far I haven't heard it. 
	 * @param program
	 * @throws Exception
	 */
	public Beeper(Program program) throws Exception{
		super("Beeper", program);
		addMethod(new BeepMethod("startup", this, STARTUP));
		addMethod(new BeepMethod("shutdown", this, SHUTDOWN));
		addMethod(new BeepMethod("ok", this, OK));
		addMethod(new BeepMethod("error", this, ERROR));
		String[] params = {"duration", "frequency"};
		addMethod(new Beep("beep", params, this));

	}

	private class BeepMethod extends HardwareMethod{
		int function;
		public BeepMethod (String name, Program program, int function) throws SyntaxError{
			super(name, MethodType.PUB, null, null, program);
			this.function = function;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					Toolkit tk = Toolkit.getDefaultToolkit();
					switch(function){
					case STARTUP:
						tk.beep();
						break;
					case SHUTDOWN:
						tk.beep();
						break;
					case OK:
						tk.beep();
						break;
					case ERROR:
						tk.beep();
						break;
					}
					return Variable.TRUE;
				}

			};
		}
	}

	private class Beep extends HardwareMethod{

		public Beep(String name, String[] params, Program program) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					Toolkit.getDefaultToolkit().beep();
					return Variable.TRUE;
				}
			};
		}
	}

}
