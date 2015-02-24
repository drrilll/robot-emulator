package dang.program.statements;

import java.nio.ByteBuffer;

import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;

/**
 * Spin statements, bytefill, wordfill, longfill
 * @author darrylhill
 *
 */
public class MemFill extends Statement {
	
	Expression address, value, fillLength;
	VariableType type;
	Memory memory;

	public MemFill(Expression address, Expression value, Expression fillLength, VariableType type,
			Method method) {
		super(method);
		memory = method.getMemory();
		this.address = address;
		this.value = value;
		this.fillLength = fillLength;
		this.type = type;
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		int val = value.evaluateToValue(method);
		int add = address.evaluateToValue(method);
		int fill = fillLength.evaluateToValue(method);
		int step = 0;
		switch (type){
		case BYTE: step = 1; break;
		case WORD: step = 2; break;
		case LONG: step = 4; break;
		}
		//I could explain it, but its more fun if you figure it out.
		//The move is done much simpler.
		for (int i = add; i < (add+(fill*step)); i+=step){
			memory.position(i);
			switch (type){
			case BYTE: memory.put((byte)val);  		break;
			case WORD: memory.putShort((short)val); break;
			case LONG: memory.putInt(val); 			break;
			}
		}
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
		case BYTE: buf.append("Bytefill("); break;
		case WORD: buf.append("Wordfill("); break;
		case LONG: buf.append("Longfill("); break;
		}
		buf.append(address+","+value+","+fillLength+")");
		return buf.toString();
	}
	

	

}
