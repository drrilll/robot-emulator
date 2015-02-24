package dang.program.expressions;

import dang.program.AnonVariable;
import dang.program.Memory;
import dang.program.MethodInstance;
import dang.program.Variable;

/**
 * A class for the SPIN cnt function or variable (I believe it is both)
 * @author darrylhill
 *
 */
public class Cnt extends Variable {

	public Cnt(Memory memory) {
		super("cnt", VariableType.LONG, memory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		setValue((int)context.getRobot().getCnt(), context);
		return this;
	}

	@Override
	public int evaluateToValue(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		setValue((int)context.getRobot().getCnt(), context);
		return getValue();
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}

}
