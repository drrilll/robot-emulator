package dang.program;

import dang.exceptions.SyntaxError;
import dang.program.Method.MethodType;
import dang.program.expressions.Expression;

public class InitialContext extends MethodInstance {

	/**
	 * Methods and statements are called with a copy of
	 * their context, or method, which holds relevant info
	 * such as local variables. Of course, there is the
	 * problem of the first methodcall you make in the program,
	 * which why we have this. There are no local variables, just
	 * a blank method that makes our first methodcall legal. 
	 * 
	 * The method supplied here i
	 * @param method
	 * @param callingInstance
	 * @throws SyntaxError 
	 */
	public InitialContext(Program program) throws SyntaxError {
		super(new Method("", MethodType.PRI, null, null, null,program), null);
		// TODO Auto-generated constructor stub
	}

	public void initializeMemory(Expression[] params) {
		//do nothing
	}
}
