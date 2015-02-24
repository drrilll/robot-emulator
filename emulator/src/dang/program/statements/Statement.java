package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.program.Block;
import dang.program.BlockHolder;
import dang.program.Method;
import dang.program.MethodInstance;

public abstract class Statement implements BlockHolder{
	/**
	 * 
	 * @param method is passed in to have access to the 
	 * variables, both local and global as well as params
	 * Any statement that doesn't explicitly return a value should return 1
	 * @throws SyntaxError
	 * it sure does
	 * @throws CompilerError 
	 */
	int tab=0; 
	Method callingMethod;
	
	public Method getCallingMethod() {
		return callingMethod;
	}

	public void setCallingMethod(Method callingMethod) {
		this.callingMethod = callingMethod;
	}

	public Statement(Method callingMethod){
		this.callingMethod = callingMethod;
	}
	
	public void setTab(int tab){
		this.tab = tab;
	}
	
	public int getTab(){
		return tab;
	}
	
	public abstract int execute(MethodInstance method) throws Exception;
	
	public abstract void setBlock(Block block) throws SpinError;
	
	public abstract Block getBlock() throws SpinError;
}
