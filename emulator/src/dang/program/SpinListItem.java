package dang.program;

import dang.program.expressions.Expression;

public interface SpinListItem {

	public boolean equals(Expression expValue, MethodInstance method) throws Exception;
	public Expression getExpression();
}
