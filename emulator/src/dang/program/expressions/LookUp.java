package dang.program.expressions;

import java.util.ArrayList;

import dang.program.AnonVariable;
import dang.program.SList;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
/**
 * Lookup and lookupz from SPIN
 * @author darrylhill
 *
 */
public class LookUp implements Expression {
	private Expression index;
	private SList list;
	private boolean z;
	
	public LookUp(Expression index, SList list, boolean z){
		this.index = index;
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
			offset = -1;
		}
		int val = index.evaluateToValue(method)+offset;
		if (val < 0){val = 0;}
		AnonVariable ind = new AnonVariable(val);
		return list.getValueValue(ind, method);
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer("Lookup");
		if (z){str.append("z");}
		str.append("("+index+" : "+ list);
		return str.toString();
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
