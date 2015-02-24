package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.expressions.Expression;
import dang.program.statements.Statement;
/**
 * variable.getValue() and Variable.execute() return the same values.
 * Generally speaking you should use execute(), but there are some
 * getValues roaming around.
 * Also I did a late move to ByteBuffer as memory for variables. Since
 * it is occasionally copied out to more memory, so it needs to be explicitly
 * passed in for every access. 
 * Update: getValue() is deprecated
 * 
 * It would appear, the way Spin does things, and I will need to confirm in
 * the lab, is that with variable access x is equivalent to x[0]. Needs confirmation
 * @author darrylhill
 *
 */
public class Variable implements Expression{
	
	
	//TODO implement byte, word and long behaviour here
	//very important, but have to test in the lab first
	//eg, does it scroll to negative automatically?
	//I know how the notes say negative is handled, but I have
	//had bytes that went -- scratch that, I think all negs are handled
	
	//TODO extend this with constants and literals, for readability
	public enum VariableType {LONG, WORD, BYTE};
	private String name;
	private VariableType type;
	protected int location;
	private int size;
	//whether the variable is initialized as an array
	private int numberOfElements = 1;
	public final boolean isArray;
	private Expression length;
	//private Method method;
	
	/*
	 * This is an attempt to keep an up to date reference to memory for printing purposes.
	 * Only an attempt, it may fail and give an unknown value
	 */
	private Memory memory;
	
	//constructor for arrays
	public Variable(String name, VariableType type, Expression length, Memory memory){
		this.setMemory(memory);
		this.name = name;
		this.type = type;
		isArray = true;
		this.length = length;
	}

	public Variable(String name, VariableType type, Memory memory){
		if (memory == null) Debug.debug("variable "+name+" in initializer");
		this.setMemory(memory);
		this.name = name;
		this.type = type;
		isArray = false;
	}

	public void setMemory(Memory memory){
		this.memory = memory;
	}
	
	public void initialize(MethodInstance method) throws Exception{
		setSize(method);
		if (getMemory() == null){
			Debug.debug("memory is null, variable: "+name);
		}
		setLocation(getMemory().getStackPointer());
		getMemory().incrementStackPointer(getSize());
	}
	
	/*
	 * Strictly speaking, this method is only necessary upon initialization
	 */
	private void setSize(MethodInstance method) throws Exception{
		if (length == null){
			//Debug.debug("In setsize, length is null");
		}
		if (type == null){
			throw new CompilerError("Variable: Attempting to access size with null type");
		}
		switch (type){
		case BYTE: size = 1; break;
		case WORD: size = 2; break;
		case LONG: size = 4; break;
		}
		if (isArray){
			int l = length.evaluateToValue(method);
			numberOfElements = l;
			size *= l;
		}
	}
	
	public int getSize() throws CompilerError{
		if (size == 0){
			throw new CompilerError("Variable: size = 0 (possibly uninitialized with setSize())");
		}
		return size;
	}
	public int getLocation(MethodInstance method) throws Exception {
		return location;
	}

	private void setLocation(int location) {
		this.location = location;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name= name;
	}

	public VariableType getType() {
		return type;
	}

	//this exception is for the subclass
	public void setValue(int value,MethodInstance method) throws Exception {
		//the bytes of the java types matches the bytes of the SPIN types
		switch (type){
		case BYTE:
			getMemory().put(location,(byte)value);
			break;
		case WORD:
			getMemory().putShort(location,(short)value);
			break;
		case LONG:
			if (getMemory() == null)Debug.debug("memory is null");
			getMemory().putInt(location,(int)value);		
		}
	}
	
	/**
	 * A little SPIN trivia for ya. Are the values signed or unsigned?
	 * Trick question! It's actually a weirdo combination of both! Bytes
	 * and Words are unsigned, unless you explicitly add a ~ before every
	 * comparison or output to RBC (which takes a long). And to extend a
	 * byte, with sign, to a long is 1 ~, and to extend a word to a long
	 * is 2 ~'s. Does this make any sense? Absolutely none!!
	 * 
	 * What this means is that Java does everything backwards from SPIN!
	 * Bytes and Shorts are signed and there is nothing you can do about it,
	 * except put them in a larger variable that can handle those values!!
	 * 
	 * Bitwise and (&) ensures that the values returned are unsigned.
	 * 
	 * @param memory
	 * @return
	 * @throws CompilerError
	 */
	public int getValue() throws CompilerError{
		Debug.debug("Variable:" +getName()+" memlocation: "+location);
		switch (type){
		case WORD:
			Debug.debug("Value: "+getMemory().getShort(location));
			return getMemory().getShort(location)&0xFFFF;
		case LONG:
			Debug.debug("Value: "+getMemory().getInt(location));
			return getMemory().getInt(location);
		default:
			Debug.debug("Value: "+getMemory().get(location));
			return getMemory().get(location)&0xFF;
		}	
	}
	
	public float getFloatValue(){
		Debug.debug("VariableasFloat:" +getName()+" memlocation: "+location);
		return getMemory().getFloat(location);	
	}
	
	
	public static long longGarbage(){
		//the point here is uninstantiated SPIN variables will return garbage
		//but for now we are going with 0
		// TODO put an actual random number
		long garbage = 0;
		return garbage;
	}


	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		return this;
	}
	
	@Override
	public int evaluateToValue(MethodInstance method) throws Exception{
		return getValue();
	}
	
	
	
	public Variable increment(MethodInstance method) throws Exception{
		Debug.debug("incrementing "+getName());
		setValue(evaluateToValue(method) +1, method);
		return this;
	}
	
	public Variable decrement(MethodInstance method) throws Exception{
		Debug.debug("decrementing "+getName());
		setValue(evaluateToValue(method) -1,method);
		return this;
	}
	
	
	public boolean equals(Variable v, MethodInstance method) throws SpinError{
		if (getValue() == v.getValue()){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean equals(Variable v) throws CompilerError{
		if (getValue() == v.getValue()){
			return true;
		}else{
			return false;
		}
	}
	
	
	public String toString(){
		String value = null;
		if (getMemory() == null){
			value = "unknown";
		}else if (location > getMemory().capacity()){
			value = "unknown";
		}else{
			try {
				value = Integer.toString(getValue());
			} catch (CompilerError e) {
				e.printStackTrace();
			}
		}
		return new String("Variable "+name+" value: "+value);
	}
	
	
	public String memoryMap(){
		StringBuffer str = new StringBuffer();
		if (getMemory() == null){
			str.append("Variable:" +getName()+" memlocation: "+location+"\n");
			str.append("Value: unknown");
		}else{
			for (int i = 0; i < numberOfElements; i++){
				switch (type){
				case WORD:
					str.append("Variable:" +getName()+" memlocation: "+(location+(i*2))+"\n");
					str.append("Value: "+getMemory().getShort(location+(i*2)));
					break;
				case LONG:
					str.append("Variable:" +getName()+" memlocation: "+(location+(i*4))+"\n");
					str.append("Value: "+getMemory().getInt(location+(i*4)));
					break;
				default:
					str.append("Variable:" +getName()+" memlocation: "+(location+i)+"\n");
					str.append("Value: "+getMemory().get(location+i));
					break;
				}
				str.append("\n");
			}
		}
		return str.toString();
	}

	public Memory getMemory() {
		return memory;
	}
	
	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}

}
