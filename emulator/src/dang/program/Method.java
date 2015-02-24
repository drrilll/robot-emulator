package dang.program;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;
import dang.program.statements.Statement;
import dang.robot.Robot;

/**
 * Method implements Statement, but for storage purposes
 * only. Actually executing a method from within another method
 * should be relegated to a MethodCall. For now. Make the method, stuff
 * it into a MethodCall, and put it where it needs to go. MethodCall is
 * "officially" a statement, and handles all the stuff it needs to.
 * @author darrylhill
 *
 */
public class Method implements BlockHolder{


	public enum MethodType {PUB, PRI};
	private String name;
	private MethodType type = MethodType.PUB;
	private HashMap<String, String> localStrings = new HashMap<String, String>();
	private Block block;
	private Program program;
	private String[] parameters;
	private String[] localVariables;
	private String returnVariable;
	private int tab;
	private int extraMemory = 0;
	private Memory memory;
	/*
	 * A methodcall may set the calling method of the called method. Purely
	 * optional, unless you plan on returning some dynamic memory, then you
	 * tell the calling method not to free it. Not currently implemented
	 */
	private Method callingMethod;

	/**
	 * 
	 * @param name
	 * @param type
	 * @param parameters
	 * an array of strings that is the names of the parameters. We turn them into
	 * variables and store them in the localVariables collection, but not in memory
	 * until the method is called
	 * @param localVariables
	 * same thing as above
	 * @param program is where we find global variables and other methods we
	 * may call
	 * @throws SyntaxError 
	 */
	public Method(String name, MethodType type, String[] parameters, 
			String[] localVariables, String returnVariable, Program program) throws SyntaxError{
		this.program = program;
		memory = program.getMemory();
		this.name = name;
		this.type = type;
		block = new Block(this);
		if(parameters != null){
			for (String param :parameters){
				if (program.getGlobalVariables().containsKey(param)){
					//throw new DuplicateVariableNameException();
					throw new SyntaxError("Duplicate variable name exception");
				}
			}
		}
		this.parameters = parameters;
		if (localVariables != null){
			for (String var :localVariables){
				if (program.getGlobalVariables().containsKey(var)){
					//throw new DuplicateVariableNameException();
					throw new SyntaxError("Duplicate variable name exception");
				}
			}
		}
		this.localVariables = localVariables;

		//if there is a return variable, we store it
		if (returnVariable != null){
			this.returnVariable = returnVariable;

			//if there is no return variable, we put in the generic "result" variable
		}else{
			this.returnVariable = "result";

		}
		Debug.debug("Method: "+getName()+" returnVariable: "+returnVariable);


	}
	
	public Method(Program program){
		this.program = program;
	}

	public MethodInstance getMethodInstance(MethodInstance callingInstance){
		return new MethodInstance(this, callingInstance);
	}




	public void addExtraMemory(int mem){
		extraMemory += mem;
	}
	public int getExtraMemory() {
		return extraMemory;
	}

	public void setExtraMemory(int extraMemory) {
		this.extraMemory = extraMemory;
	}

	//number of params plus number of localvariables plus 1 for return variable
	//all longs, so 4 bytes each. ExtraMemory is for string literals that we need 
	//to store. It starts at 0 and the compiler will add to it when it encounters
	//string("some string"). 
	public int memoryNeeded(){
		int p = 0,l = 0;
		if (parameters != null) p =parameters.length;
		if (localVariables != null) l = localVariables.length;
		return ((p+l+1)*4)+getExtraMemory();
	}

	public Method getCallingMethod() {
		return callingMethod;
	}
	public void setCallingMethod(Method callingMethod) {
		this.callingMethod = callingMethod;
	}


	public void addLocalString(String id, String str){
		localStrings.put(id, str);
	}

	public HashMap<String, String> getLocalStrings(){
		return localStrings;
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


	public void setTab(int tab) {
		this.tab = tab;
	}


	public int getTab() {
		return tab;
	}

	public boolean isVariable(String name){
		for (String n : parameters){
			if (n.equals(name)) return true;
		}
		for (String n : localVariables){
			if (n.equals(name)) return true;
		}
		if (returnVariable.equals(name)) return true;

		//no matches
		return false;
	}


	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	public Block getBlock() {
		return block;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public void addStatement(Statement s){
		block.addStatement(s);
	}

	public int getNumberOfParameters(){
		if (parameters == null){
			return 0;
		}
		return parameters.length;
	}

	public Robot getRobot(){
		return getProgram().getRobot();
	}

	public Memory getMemory(){
		return memory;
	}

	//TODO make sure these are all necessary
	public void printVariables(){
		Debug.debug("Local:");
		for (String v: localVariables){
			Debug.debug(v+", ");
		}
		Debug.debug("Parameters:");
		for (String v: parameters){
			Debug.debug(v+", ");
		}
		Debug.debug("Return Variable: "+ returnVariable);
		Debug.debug("");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MethodType getType() {
		return type;
	}
	public void setType(MethodType type) {
		this.type = type;
	}

	public boolean equals(Method method){
		if (name.equals(method.getName())){
			if (parameters.length == method.getNumberOfParameters()){
				return true;
			}
		}
		return false;
	}




}
