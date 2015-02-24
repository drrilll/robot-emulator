package dang.program;

import java.util.HashMap;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;
import dang.robot.Robot;

public class MethodInstance {
	
	public static int cid = 0;
	
	private HashMap<String, Variable> variables = new HashMap<String, Variable>();
	private HashMap<String, SpinString> localStrings = new HashMap<String, SpinString>();
	String[] parameters; 
	String[] localVariables;
	String returnVariable;
	Block block;
	Memory memory;
	int memoryNeeded;
	Method method;
	Program program;
	MethodInstance callingInstance;
	int popStack;
	final int sid;


	/**
	 * essentially a running copy of a method. No difference, except
	 * that each of these has its own copy of variables and memory
	 * requirements.
	 * 
	 * @param method
	 * @param callingInstance
	 */
	public MethodInstance(Method method, MethodInstance callingInstance) {
		super();
		parameters = method.getParameters();
		localVariables = method.getLocalVariables();
		this.returnVariable = method.getReturnVariable();
		this.block = method.getBlock();
		this.memory = method.getMemory();
		this.memoryNeeded = method.memoryNeeded();
		this.method = method;
		this.callingInstance = callingInstance;
		program = method.getProgram();
		sid = cid++;
	}
	
	public HashMap<String, SpinString> getLocalStrings(){
		return localStrings;
	}
	

	public MethodInstance getCallingInstance() {
		return callingInstance;
	}


	public void setCallingInstance(MethodInstance callingInstance) {
		this.callingInstance = callingInstance;
	}


	/**
	 * 
	 * @param parameters
	 * @param method
	 * this is the method that the parameters are evaluated against, so the calling method
	 * @return
	 * @throws Exception 
	 */
	public Variable execute(Expression[] parameters) throws Exception{
		//Debug.debug("executing method: "+ method.getName());

		block.execute(this);
		Debug.debug("Method: "+ method.getName()+ " returning: "+ returnVariable);
		Variable ret = getVariable(returnVariable);
		//put the return variable into an anonymous variable
		AnonVariable av = new AnonVariable(ret.getType(), ret.evaluateToValue(callingInstance));
		//return the anonymous variable. Not the real variable, because
		//that memory will be released and possibly overwritten.
		return av;
	}

	public void releaseMemory() throws Exception{
		
		//release all the local memory
		memory.setStackPointer(popStack);
	}

	/**
	 * The strings go into memory first by default, so that if we wish
	 * to send a pointer back to the calling method, we can make sure the
	 * space remains on top of the stack. That probably won't work. It will
	 * work good enough
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void initializeMemory(Expression[] params) throws Exception{

		//the value we set the stack pointer to when this method returns
		popStack = memory.getStackPointer();
		//check if we have enough memory to run this method, 
		//if not increase it
		if ((getMemoryNeeded()+memory.getStackPointer())>memory.capacity()){
			memory.increaseMemoryCapacity(getMemoryNeeded());
		}
		
		//put the string literals into memory
		HashMap<String, String> locstr = method.getLocalStrings();
		SpinString ss;
		for (String id: locstr.keySet()){
			ss = new SpinString(id, locstr.get(id), memory);
			Debug.debug("loading string: "+id +"methodInstance id:" +sid);
			localStrings.put(id, ss);
			ss.putInMemory();
		}

		Variable result = new Variable(returnVariable, VariableType.LONG, memory);
		variables.put(returnVariable, result);
		//give the return variable a place in memory
		result.initialize(callingInstance);
		Variable v;
		Debug.debug("Methodinstance of method: "+method.getName());
		if (parameters != null){
			Debug.debug("Parameters:");
			for (int i = 0; i< parameters.length; i ++){
				Debug.debug(parameters[i]);
				v = new Variable(parameters[i], VariableType.LONG, memory);
				variables.put(parameters[i], v);
				//sets the location of the variable in memory, and also the
				//size, which in the case of an array may have to evaluate an
				//expression
				v.initialize(callingInstance);
				v.setValue(params[i].evaluateToValue(callingInstance), callingInstance);

				Debug.debug("Added parameter value: "+v);
			}
		}
		
		if (localVariables != null){
			for (int i = 0; i< localVariables.length; i ++){
				v = new Variable(localVariables[i], VariableType.LONG, memory);

				variables.put(localVariables[i], v);
				v.initialize(callingInstance);
				v.setValue(0, callingInstance);

				Debug.debug("Added localVariable value: "+v);
			}
		}
			
	}
	
	public SpinString getSpinString(String id){
		return localStrings.get(id);
	}
	
	public Variable getVariable(String name) throws SpinError{
		if (isVariable(name)){
				return variables.get(name);
		}else if(program.isConstant(name)){
			return program.getConstant(name);
		}else if (program.isVariable(name)){
			return program.getVariable(name);
		}
		throw new SpinError("No variable found of name: "+name);
	}
	
	public boolean isVariable(String name){
		return variables.containsKey(name);
	}
	
	public HashMap<String, Variable> getVariables() {
		return variables;
	}
	
	public void setVariables(HashMap<String, Variable> variables) {
		this.variables = variables;
	}


	public void setMemoryNeeded(int memoryNeeded) {
		this.memoryNeeded = memoryNeeded;
	}
	
	public int getMemoryNeeded(){
		return memoryNeeded;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String[] getLocalVariables() {
		return localVariables;
	}

	public void setLocalVariables(String[] localVariables) {
		this.localVariables = localVariables;
	}

	public String getReturnVariable() {
		return returnVariable;
	}

	public void setReturnVariable(String returnVariable) {
		this.returnVariable = returnVariable;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Robot getRobot() {
		return method.getRobot();
	}
	
	public int getSid(){
		return sid;
	}
	
	public void setReturnValue (int value) throws Exception{
		Variable v = getVariable(returnVariable);
		v.setValue(value, this);
	}

}
