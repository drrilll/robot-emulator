package dang.program;

import java.nio.ByteBuffer;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;

/**
 * Just a big fancy variable. There are memory issues and I do not
 * check for them. If you have it at an initial size and then change
 * the string, you have to make sure it is at most the size of the old
 * string. Otherwise you won't have enough memory allocated and you will
 * either throw an exception or corrupt other memory.
 * @author darrylhill
 *
 */
public class SpinString extends Variable {
	static int currentId =0;
	private String str;
	private int location;
	private Memory memory;
	private String id;

	/*
	 * Constructor
	 */
	public SpinString(String id, String str, Memory memory){
		super(id, VariableType.BYTE, memory);
		this.id = id;
		this.str = str;
		this.memory = memory;
	}
	
	public static String getCurrentId(){
		currentId ++;
		return Integer.toString(currentId);
	}
	
	@Override
	public int getSize(){
		return str.length()+1;
	}
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public int length(){
		//the number of bytes stored in memory, +1 as there is a delimiter
		return str.length()+1;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	

	@Override
	//important note: we are returning the address that this string
	//resides at. 
	public Variable evaluate(MethodInstance method) throws Exception {
		
		//take the address from evaluatetovalue and stick it in an
		//anonymous variable
		return new AnonVariable(VariableType.LONG, location);
	}

	@Override
	//important note: we are returning the address that this string
	//resides at. Also putting the string into memory. The memory needed
	//for this should have been communicated to the method during
	//compilation and allocated when the Method began execution. So
	//it should be there. Regardless it is 
	public int evaluateToValue(MethodInstance method) {
		//return the address the string is stored at. 
		return location;
	}
	
	public void putInMemory(){
		byte[] bytestring = str.getBytes();

		//get the stackpointer
		int stackPointer = memory.getStackPointer();
		//set the stackPointer to its new location
		//In the event that there is not enough memory 
		//(there should be as it is preallocated), more
		//memory is allocated
		memory.incrementStackPointer(bytestring.length+1);

		//set the location in memory where this string is stored
		setLocation(stackPointer);

		//store the string in memory, and end it
		//with the string delimiter 0
		memory.position(stackPointer);
		memory.put(bytestring);
		memory.put((byte) 0);

	}

}
