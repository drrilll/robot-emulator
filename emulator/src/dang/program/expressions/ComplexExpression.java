package dang.program.expressions;

import dang.program.AnonVariable;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.Variable.VariableType;

public interface ComplexExpression extends Expression{
	public String getOperator();
	public Expression getExp1();
	public Expression getExp2();
	public void setExp1(Expression e);
	public void setExp2(Expression e);
}
