package dang.program.objects;

import java.io.IOException;
import java.nio.ByteBuffer;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.program.Debug;
import dang.program.Memory;
import dang.program.Program;
import dang.program.Variable.VariableType;
import dang.robot.Robot;

public class SpinObject extends Program {
	
	private Program main;

	/**
	 * We pass in the main program so we can share the main memory.
	 * SpinObjects are java imitations of the Spin Object files 
	 * provided in comp4807, essentially a set of files that abstract
	 * out the details of hardware communication.
	 * 
	 * The specification is a little complicated. When we call a regular
	 * method we get back a methodinstance, which has a copy of the 
	 * local variables and statements, etc, and we call execute on it
	 * and it starts executing statements. So to add method functionality
	 * to a SpinObject we make a class that extends HardwareMethod and
	 * override the getMethodInstance call and return a new MethodInstance.
	 * In this MethodInstance we override the execute() method, and write
	 * whatever functionality we want to have in regular java code. So its a whole
	 * bunch of boiler plate code, but the stuff we are interested in
	 * happens in the "execute()" method. See any of the SpinObjects
	 * (RBC.java, ServoControl.java) for examples. Complicated, but works.
	 * 
	 * @param robot
	 * @param name
	 * @param main
	 * @throws IOException
	 * @throws SpinError
	 */
	public SpinObject(String name, Program main) throws Exception {
		super(main.getRobot(), name);
		this.main = main;
		setMemory(main.getMemory());
		
		//pi, the timing constants, cnt, etc
		addInitialConstants();
	}
		
	public Program getMain() {
		return main;
	}

	public void setMain(Program main) {
		this.main = main;
	}
	
	@Override 
	public void setMemory(Memory memory){
		//my logic powers are elusive. I override this just
		//to provide error reporting, so if memory is corrupted
		//I might know where
		Debug.debug("setting memory from object: "+getProgramName());
		super.setMemory(memory);
	}
	

}
