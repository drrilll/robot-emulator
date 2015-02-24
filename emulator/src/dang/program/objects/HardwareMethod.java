package dang.program.objects;


import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Debug;
import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;
import dang.robot.Robot;
/**
 * All hardware classes have an instance of robot. Essentially
 * a hardware class is speaking directly to the robot. As such 
 * I think I am not having statements. Can always put them in later
 * 
 * @author darrylhill
 *
 */
public abstract class HardwareMethod extends Method {

	Robot robot;
	
	public HardwareMethod(String name, MethodType type, String[] parameters, 
			String[] localVariables, Program program) 
					throws SyntaxError{
		super(name, type, parameters, localVariables, null, program);
		this.robot = program.getRobot();
	}
	
	
	
	

}
