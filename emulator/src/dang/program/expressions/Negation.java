package dang.program.expressions;

import dang.program.AnonVariable;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;

public class Negation implements Expression {
	
	Expression exp;
	Method callingMethod;
	
	public Negation(Expression exp, Method callingMethod){
		this.exp = exp;
		this.callingMethod = callingMethod;
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		// TODO Auto-generated method stub
		return new AnonVariable(evaluateToValue(context));
	}

	@Override
	public int evaluateToValue(MethodInstance context) throws Exception {
		/*
		 * Freaking edge cases. Everything is bits in SPIN, EXCEPT when you negate a
		 * float literal, in which case it assigns the negative float value, instead
		 * of the negation of the float bits. 
		 * 
		 * As an example, if you do this: 
		 * a := 3.14
		 * a = -a;
		 * The output is -0.45 or something, because it simply flips the sign bit,
		 * which won't work on a float.
		 * Whereas this:
		 * a:= -3.14
		 * will output -3.14 appropriately, which is the case we handle here. I can't handle
		 * it at the token level, because then you couldn't do this:
		 * a:= 2 - 3.14
		 * because it would read "-3.14" as a negative number instead of a math expression.
		 * You wouldn't want to do that in any case, because it will not give you a meaningful
		 * answer, but its perfectly legal syntax in SPIN.
		 */
		if ((exp instanceof AnonVariable)&&(((AnonVariable)exp).isfloat)){
			int val = exp.evaluateToValue(context);
			float fval = Float.intBitsToFloat(val);
			fval = -fval;
			val = Float.floatToIntBits(fval);
			return val;
		}
		int value = -exp.evaluateToValue(context);
		return value;
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return false;
	}

}
