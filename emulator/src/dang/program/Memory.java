package dang.program;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import dang.program.Variable.VariableType;

/**
 * A wrapper class for a bytebuffer, which serves as our memory,
 * with a bit of extra functionality.
 * When a bytebuffer runs our of room, in order to increase the size a
 * new bytebuffer needs to be instantiated with the larger size and the
 * contents copied over. If we passed a bytebuffer reference around,
 * as soon as we increased the size the objects would have an irrelevant
 * reference to the memory. Which is the essential reason for this class,
 * since its reference always remains current. Most of the methods are
 * simply mirrored bytebuffer methods, with a method for increasing memory.
 * 
 * @author darrylhill
 *
 */
public class Memory {
	ByteBuffer memory;
	int stackPointer;
	
	public Memory(int initCapacity){
		memory = ByteBuffer.allocate(initCapacity);
		stackPointer = 0;
	}

	public ByteBuffer getMemory() {
		return memory;
	}

	public void setMemory(ByteBuffer memory) {
		this.memory = memory;
	}

	public int getStackPointer() {
		return stackPointer;
	}

	public void setStackPointer(int stackPointer) {
		this.stackPointer = stackPointer;
	}
	
	public void position(int position){
		memory.position(position);
	}
	
	public int capacity(){
		return memory.capacity();
	}
		
	//all memory increases are permanent. Eventually it will top out somewhere
	//if you use recursion unwisely, or there is memory leaks, you can crash 
	//the program. Just like real life!
	public void increaseMemoryCapacity(int mem){
		int newCapacity = memory.capacity()+mem*4;
		byte[] data = new byte[newCapacity];
		try{
			memory.position(0);
			memory.get(data, 0, memory.capacity());
		}catch(BufferUnderflowException e){
			e.printStackTrace();
		}
		memory = ByteBuffer.wrap(data);
	}

	public void incrementStackPointer(int inc){
		//in the specific instance that this is called, this should never happen
		//but in the future who knows
		if ((stackPointer+inc)>getMemory().capacity()){
			increaseMemoryCapacity(inc*2);
		}
		stackPointer += inc;
	}
	
	public void incrementStackPointer(VariableType type){
		switch(type){
		case LONG:
			if ((stackPointer+4)>getMemory().capacity()){
				increaseMemoryCapacity(8);
			}
			stackPointer += 4;
			break;
		case WORD:
			if ((stackPointer+2)>getMemory().capacity()){
				increaseMemoryCapacity(8);
			}
			stackPointer += 2;
			break;
		case BYTE:
			if ((stackPointer+1)>getMemory().capacity()){
				increaseMemoryCapacity(8);
			}
			stackPointer += 1;
			break;
		}
	}
	
	public void put(byte b){
		memory.put(b);
	}
	
	public void put(int index, byte b){
		memory.put(index, b);
	}
	
	public void putShort(short s){
		memory.putShort(s);
	}
	
	public void putShort(int i, short s){
		memory.putShort(i,s);
	}
	
	public void putInt(int i){
		memory.putInt(i);
	}
	
	public void putInt(int index,int i){
		memory.putInt(index, i);
	}
	
	public byte get(int i){
		return memory.get(i);
	}
	
	public short getShort(int i){
		return memory.getShort(i);
	}
	
	public int getInt(int i){
		return memory.getInt(i);
	}

	public float getFloat(int location) {
		// TODO Auto-generated method stub
		return memory.getFloat(location);
	}
	
	public void get(byte[] temp, int start, int len){
		memory.get(temp, start, len);
	}
	
	public void put(byte[] temp, int start, int len){
		memory.put(temp, start, len);
	}

	public void put(byte[] bytestring) {
		memory.put(bytestring);
		
	}

	public int position() {
		// TODO Auto-generated method stub
		return memory.position();
	}

	public byte get() {
		// TODO Auto-generated method stub
		return memory.get();
	}
	
	public short getShort(){
		return memory.getShort();
	}
	
	public int getInt(){
		return memory.getInt();
	}
	
	
}
