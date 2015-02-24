package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

/**
 * @author darrylhill
 *
 */
public class MethodCall extends Statement implements Expression {
	Method method;
	Expression[] parameters;
	Program program;
	
	/**
	 * Essentially, if an instance of this method exists, then it should
	 * exist within the program, so a check is unnecessary. In the event that
	 * it does not exist, it is a bug
	 * I am returning an int, so any method call will need to explicity
	 * put its value in a new Variable (compiler note)
	 * Incidentally this is used for object method calls as well
	 * @param methodName
	 * @param parameters
	 * @throws SyntaxError 
	 */
	public MethodCall(String methodName, Expression[] parameters, Program program, Method callingMethod) throws SyntaxError{
		
		super(callingMethod);
		method = program.getMethod(methodName);
		this.parameters = parameters;
		this.program = program;
		//a hook to see if this method is returning any dynamic memory.
		//if it is we reserve space for it. edit: I don't
		//believe this feature is being utilized.
		if(callingMethod == null)Debug.debug("calling method null");
		if (method == null)Debug.debug("method is null");
		method.setCallingMethod(callingMethod);
		Debug.debug("MethodCall robot: "+program.getRobot());
	}
	
	public String getName() {
		return method.getName();
	}


	public Expression[] getParameters() {
		return parameters;
	}

	public void setParameters(Expression[] parameters) {
		this.parameters = parameters;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	

	@Override
	public int execute(MethodInstance context) throws Exception {
		if (getProgram() == null){
			Debug.debug("program in methodcall: "+getName()+" is null");
		}
		if(context == null){
			Debug.debug("context in methodcall: "+getName()+" is null");
		}
		Debug.debug("running instance of method: "+method.getName());
		MethodInstance instance = method.getMethodInstance(context);
		instance.initializeMemory(parameters);
		Variable v = instance.execute(parameters);
		instance.releaseMemory();
		if(v == null){
			Debug.debug("returnvariable in methodcall: "+getName()+" is null");
		}
		return v.evaluateToValue(context);
	}

	@Override
	public Variable evaluate(MethodInstance context) throws Exception {
		MethodInstance instance = method.getMethodInstance(context);
		instance.initializeMemory(parameters);
		Variable v= instance.execute(parameters);
		if(v == null){
			Debug.debug("returnvariable in methodcall: "+getName()+" is null");
			instance.releaseMemory();
			return null;
		}
		AnonVariable ret =new AnonVariable( v.evaluateToValue(instance));
		instance.releaseMemory();
		return ret;
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		throw new CompilerError("setBlock: blocks are stored in methods not methodcalls");	
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
	public Block getBlock() throws SpinError {
		return null;

		//throw new CompilerError("getBlock: blocks are stored in methods not methodcalls");	
	}
	
	@Override
	public String toString(){
		StringBuffer ret = new StringBuffer("MethodCall: "+getName());
		if (parameters != null){
			ret.append("(");
			for (Expression e: parameters){
				ret.append(e+",");
			}
			ret.setCharAt(ret.length()-1, ')');
			
		}
		return ret.toString();
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		// TODO Auto-generated method stub
		return execute(method);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return true;
	}


}
