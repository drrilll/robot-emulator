package dang.program;

import dang.exceptions.CompilerError;
import dang.interpreter.SpinTokenizer;
import dang.program.expressions.Expression;

/**
 * In java, simply casting a value into a byte or short
 * automatically changes it to a signed value of the
 * appropriate size.
 * @author darrylhill
 *
 */
public class SignExtend implements Expression {
	
	Expression var;
	//type can be SpinTokenizer.TT_EXTENDBYTE or SpinTokenizer.TT_EXTENDWORD
	int type;
	
	public SignExtend(Expression v, int type){
		var = v;
		this.type = type;
	}

	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		// TODO Auto-generated method stub
		return new AnonVariable(evaluateToValue(method));
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		switch(type){
		case SpinTokenizer.TT_EXTENDBYTE:
			int val = (byte) var.evaluateToValue(method);
			return val;
		case SpinTokenizer.TT_EXTENDWORD:
			val = (short) var.evaluateToValue(method);
			return val;
		default:
			throw new CompilerError("Illegal sign extend token: "+ (char)type);
		}
	}
	
	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}

}
