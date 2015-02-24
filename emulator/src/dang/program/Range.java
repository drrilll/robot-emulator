package dang.program;

import java.util.ArrayList;

import dang.exceptions.CompilerError;
import dang.program.expressions.Expression;

/**
 * Range, as in 1..10, or what have you, and the functions needed
 * 
 * @author darrylhill
 *
 */
public class Range implements SpinListItem{
	private Expression startValue, stopValue;

	public Range(Expression startValue, Expression stopValue) {
		super();
		this.startValue = startValue;
		this.stopValue = stopValue;
	}
	
	public ArrayList<Expression> getList(MethodInstance method) throws Exception{
		
		ArrayList<Expression> list = new ArrayList<Expression>();
		int startval = startValue.evaluateToValue(method);
		int stopval = stopValue.evaluateToValue(method);
		
		if (stopval < startval){
			for (int i = startval; i >= stopval; i --){
				list.add(new AnonVariable(i));
			}
		}else if (startval < stopval){
			for (int i = startval; i <= stopval; i ++){
				list.add(new AnonVariable(i));
			}
		//else they are equal
		}else{
			list.add(new AnonVariable(startval));
		}
		
		return list;
		
	}
	
	public boolean inRange(Expression valueExp, MethodInstance method) throws Exception{
		int startval = startValue.evaluateToValue(method);
		int stopval = stopValue.evaluateToValue(method);
		int value = valueExp.evaluateToValue(method);
		Debug.debug("value: "+value+ "Start: "+ startval + "Stop: "+ stopval);
		return (((value <= stopval)&&(value >= startval))||((value >= stopval)&&(value <= startval)));
	}
	
	@Override
	public boolean equals(Expression expValue, MethodInstance method) throws Exception{
		return inRange(expValue, method);
	}
	
	@Override
	public String toString(){
		return "Range Item";
	}

	@Override
	public Expression getExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
