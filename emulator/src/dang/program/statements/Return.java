package dang.program.statements;

import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

/**
 * The return statement in SPIN
 * @author darrylhill
 *
 */
public class Return extends Statement {
	
	Expression returnValue = null;
	
	public Return(Expression exp, Method method){
		super (method);
		returnValue = exp;
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		if (returnValue != null){
			method.setReturnValue(returnValue.evaluateToValue(method));
		}
		return 1;
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		// TODO Auto-generated method stub

	}

	@Override
	public Block getBlock() throws SpinError {
		// TODO Auto-generated method stub
		return null;
	}

}
