package dang.program.statements;

import java.nio.ByteBuffer;

import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;

/**
 * Spin statements bytemove, wordmove, longmove
 * @author darrylhill
 *
 */
public class MemMove extends Statement {
	
	Expression destination, source, length;
	VariableType type;
	Memory memory;

	public MemMove(Expression destination, Expression source, Expression length,
			VariableType type, Method method) {
		super(method);
		memory = method.getMemory();
		this.destination = destination;
		this.source = source;
		this.length = length;
		this.type = type;
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		int destadd = destination.evaluateToValue(method);
		int sourceadd = source.evaluateToValue(method);
		Debug.debug("Memmove, dest:" + destadd + " source: "+sourceadd);
		int len = length.evaluateToValue(method);
		byte[] temp;
		//for byte there is no change in len, so we skip it
		switch(type){
		case WORD:
			len *= 2;
			break;
		case LONG:
			len *= 4;
			break;
		}
		temp = new byte[len];
		memory.position(sourceadd);
		memory.get(temp, 0, len);
		memory.position(destadd);
		memory.put(temp, 0, len);
		return -1;
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getBlock() throws SpinError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTab(int tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTab() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override 
	public String toString(){
		StringBuffer buf = new StringBuffer();
		switch (type){
		case BYTE: buf.append("Bytemove("); break;
		case WORD: buf.append("Wordmove("); break;
		case LONG: buf.append("Longmove("); break;
		}
		buf.append(destination+","+source+","+length+")");
		return buf.toString();
	}
	
}
