package dang.program.expressions;

import dang.program.Debug;
import dang.program.MethodInstance;
import dang.program.SpinString;
import dang.program.Variable;

/**
 * SpinStrings aren't available until the specific methodinstance
 * is called, then they are put into memory. In the meantime we have
 * this, which has an id number that is used to pull the right string out
 * of memory when the time is right.
 * 
 * @author darrylhill
 *
 */
public class SpinStringWrapper implements Expression {
	
	String id;
	
	public SpinStringWrapper(String id){
		this.id = id;
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		return context.getSpinString(id).evaluate(context);
	}

	@Override
	public int evaluateToValue(MethodInstance context) throws Exception {
		Debug.debug("Spinstring id: "+ id);
		if (context == null) Debug.debug("context is null in spinstringwrapper");
		SpinString ss = context.getSpinString(id);
		Debug.debug("string: "+ ss.getStr());
		return context.getSpinString(id).evaluateToValue(context);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString(){
		return "SpinStringWrapper";
	}
}
