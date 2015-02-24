package dang.program;

import dang.program.expressions.Expression;

public class ListItem implements SpinListItem{

	Expression expression;

	public ListItem(){}
	
	public ListItem(Expression expression) {
		super();
		this.expression = expression;
	}
	
	public Expression getExpression(){
		return expression;
	}
	
	@Override
	public boolean equals(Expression expValue, MethodInstance method) throws Exception{
		int value = expValue.evaluateToValue(method);
		return value == expression.evaluateToValue(method);
	}
	
	public String toString(){
		return expression.toString();
	}
}
