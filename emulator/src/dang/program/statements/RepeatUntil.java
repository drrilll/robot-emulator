package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

public class RepeatUntil extends Repeat {

	public RepeatUntil(Expression exp, Method method) {
		super(exp, method);
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		while (getExp().evaluate(method)==Expression.FALSE){
			getBlock().execute(method);
		}
		return 1;
	}
	
	@Override
	public String toString(){
		return "repeat until "+ getExp();
	}
}
