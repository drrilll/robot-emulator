package dang.program.expressions;

import dang.program.AnonVariable;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.Variable.VariableType;

/**
 * 
 * @author Darryl
 * An expression is anything that can be evaluated, including math and boolean
 * Any boolean or logical expression, 0 is false, anything else is true. -1 is
 * the constant true in SPIN, so whenever we have a true value that is ill-defined
 * (such as an OR of two true values) we return -1.
 */
public interface Expression {
	public final static AnonVariable TRUE = new AnonVariable(VariableType.LONG,-1){
		@Override
		public String toString(){
			return "Static variable TRUE";
		}
	};
	public final static AnonVariable FALSE = new AnonVariable(VariableType.LONG,0){
		@Override
		public String toString(){
			return "Static variable FALSE";
		}
	};
	public Variable evaluate(MethodInstance context) throws Exception;
	public int evaluateToValue(MethodInstance context) throws Exception;
	public String toString();
	public boolean isAtomic();
}

