package dang.program;

import dang.exceptions.CompilerError;
import dang.program.expressions.Expression;

/**
 * A list implementation for Spin. Lists are a little complicated,
 * mostly because of ranges. 
 * @author darrylhill
 *
 */
public interface SList {

	//returns the index of the specified value
	public Variable getIndex(Expression value,MethodInstance method)throws Exception;
	
	//same thing, but a straight return value
	public int getIndexForValue(Expression value,MethodInstance method) throws Exception;
	
	//get the value for the specified index
	public Variable getValue(Expression index,MethodInstance method)throws Exception;
	
	//same thing, but a straight return value
	public int getValueValue(Expression index,MethodInstance method)throws Exception;
	
	//see if the specified value is in the list
	public boolean isInList(Expression value,MethodInstance method)throws Exception;
	
	public void addItem(SpinListItem item) throws Exception;
	public void addRange(Range range) throws Exception;
	public int size() throws Exception;
	public SpinListItem remove(int i) throws Exception;
	
}
