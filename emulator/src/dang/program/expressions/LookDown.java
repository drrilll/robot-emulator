package dang.program.expressions;

import java.util.ArrayList;

import dang.program.AnonVariable;
import dang.program.Debug;
import dang.program.SList;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;

/**
 * Lookdown and lookdownz statements in SPIN
 * @author darrylhill
 *
 */
public class LookDown implements Expression {
	
	private Expression val;
	private SList list;
	private boolean z;

	public LookDown (Expression val, SList list, boolean z) {
		this.val = val;
		this.list = list;
		this.z = z;
	}

	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		return new AnonVariable(evaluateToValue(method));
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		int offset = 0;
		if(!z){
			Debug.debug("adding 1");
			offset = 1;
		}
		return list.getIndexForValue(val,method)+offset;
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer("Lookdown");
		if (z){str.append("z");}
		str.append("("+val+" : "+ list);
		return str.toString();
	}
	
	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}
}
