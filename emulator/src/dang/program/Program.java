package dang.program;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Method.MethodType;
import dang.program.Variable.VariableType;
import dang.program.expressions.Cnt;
import dang.program.expressions.Expression;
import dang.program.statements.Case;
import dang.program.statements.IfElse;
import dang.program.statements.Statement;
import dang.robot.Robot;
/**
 * This may be confusing, but I have a lot of dependencies on passing a method argument,
 * since any relevant variable or memory needed can be accessed from the method. There is
 * some initialization done here, so I pass in the main method. Not the most elegant solution,
 * but I really only need access to global constants. There is a rare case (possibly) where
 * this could throw a weird error difficult to trace (as in a programmer error). Sucks but this 
 * is the solution for now.
 * @author darrylhill
 *
 */
public class Program {
	
	public static final int INITIALCAPACITY = 64;
	//data variables are global to all objects. Global variables are
	//on global to one object. Locals are local to a method.
	//DataVariables are not implemented presently.
	private HashMap<String, Variable> DataVariables = new HashMap<String, Variable>();
	private HashMap<String, Variable> globalVariables = new HashMap<String, Variable>();
	private HashMap<String, Constant> constants = new HashMap<String, Constant>();
	private HashMap<String, Program> objects = new HashMap<String, Program>();
	private HashMap<String, Method> methods = new HashMap<String, Method>();
	//The Spin spec says that the first method in any program is the first to be
	//called. By convention it is called main, but it can be called anything. So we
	//stuff it in its own attribute, and call it when the program is run.
	private Method firstMethod;
	/**
	 * stringMemory simulates the memory used to store strings, so that 
	 * "pointers" can be used. Only explicitly assigned strings and chars are 
	 * stored here. Attempting to dereference a variable as a string that wasn't
	 * explicitly assigned here throws an error, unless you are using byte arrays.
	 */
	private Memory memory;
	private Robot robot;
	private String programName;
	private boolean running = false; 
	MethodInstance initialContext;
	
	public Program(Robot robot, String name) throws Exception{
		setProgramName(name);
		this.robot = robot;
		setMemory(new Memory(INITIALCAPACITY));
		initialContext = new InitialContext(this);
	}
	
	public void addInitialConstants() throws Exception{
		int val = Float.floatToIntBits((float)Math.PI);
		addConstant("pi", val);
		Cnt cnt = new Cnt(getMemory());
		addGlobalVariable(cnt);
		addConstant("test1",0);
		addConstant("xtal1", 0);
		addConstant("pll16x",0);
	}
		
	public void addObject(String name, Program pro){
		objects.put(name, pro);
	}
	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public void addMethod(Method method){
		if (methods.isEmpty()){
			firstMethod = method;
		}
		methods.put(method.getName(), method);
		
	}
	
	public Method getFirstMethod(){
		return firstMethod;
	}
	
	public HashMap<String, Constant> getConstants() {
		return constants;
	}


	public void addConstants(HashMap<String, Constant> constants) throws Exception {
		for (Constant c : constants.values()){
			addConstant(c);
		}
	}

	
	public void addConstant(Constant constant)throws Exception{
		if (constants.containsKey(constant.getName())){
			//throw new DuplicateVariableNameException();
			throw new SyntaxError("Duplicate variable name exception");
		}else{
			//set the location in memory
			//Debug.debug("First method: "+getFirstMethod(),"Program:addconstant");
			constant.initialize(initialContext);
			
			//now we have enough memory, set the value
			constant.setInitialValue();
			
			//store the constant in the program
			constants.put(constant.getName(), constant);
		}
		
	}
	
	public void addConstant(String name, Expression val) throws Exception{
		int value = val.evaluateToValue(initialContext);
		addConstant(name, value);
	}
	
	public void addConstant(String name, int value) 
			throws Exception{
		if (constants.containsKey(name)){
			//throw new DuplicateVariableNameException();
			throw new SyntaxError("Duplicate constant name exception");
		}else{
			//new constant
			Constant constant = new Constant(name, value, memory);
			
			//set the location in memory. Its the initialcontext
			//because constants are initialized before any methods
			//are called.
			constant.initialize(initialContext);
			
			//now we have enough memory, set the value to memory
			constant.setInitialValue();
			
			//store the constant in the program
			constants.put(name, constant);
		}
	}
	
	public HashMap<String, Variable> getGlobalVariables() {
		return globalVariables;
	}
	
	public void addGlobalVariables(HashMap<String, Variable> gv) throws Exception {
		for (Variable v: gv.values()){
			addGlobalVariable(v);
		}
	}
	
	public void addGlobalVariable(Variable variable)throws Exception{
		if (globalVariables.containsKey(variable.getName())){
			throw new SyntaxError("Duplicate variable name exception");
		}else{
			variable.initialize(initialContext);
			Debug.debug("Putting global variable: " +variable.getName());
			globalVariables.put(variable.getName(), variable);
		}
		
	}
	
	public void addGlobalVariable(String name, VariableType type) 
			throws Exception{
		if (globalVariables.containsKey(name)){
			//throw new DuplicateVariableNameException();
			throw new SyntaxError("Duplicate variable name exception");
		}else{
			Variable variable = new Variable(name, type, memory);
			variable.initialize(initialContext);
			globalVariables.put(name, variable);
		}
	}
	
	//for arrays
	public void addGlobalVariable(String name, VariableType type, Expression length) throws Exception{
		if (globalVariables.containsKey(name)){
			//throw new DuplicateVariableNameException();
			throw new SyntaxError("Duplicate variable name exception");
		}else{
			Variable variable = new Variable(name, type, length, memory);
			variable.initialize(initialContext);
			globalVariables.put(name, variable);
		}
	}
	
	
	
	
	public HashMap<String, Program> getObjects() {
		return objects;
	}
	
	public Program getObject(String name){
		return objects.get(name);
	}

	public void setObjects(HashMap<String, Program> objects) {
		this.objects = objects;
	}

	public HashMap<String, Method> getMethods() {
		return methods;
	}

	public void setMethods(HashMap<String, Method> methods) {
		this.methods = methods;
	}
	
	public Method getMethod(String name) throws SyntaxError{
		return methods.get(name);
	}
	
	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
		Debug.debug("Program robot: "+robot);

	}
	
	public boolean isMethod(String name){
		return getMethods().containsKey(name);
	}
	
	public boolean isVariable(String name){
		return getGlobalVariables().containsKey(name);
	}
	
	public boolean isConstant(String name){
		return getConstants().containsKey(name);
	}
	
	public boolean isObject(String name){
		return getObjects().containsKey(name);
	}
	
	
	public void loadIntoRobot(){
		robot.load(this);
	}
	
	public Variable getVariable(String v){
		return getGlobalVariables().get(v);
	}
	
	public Constant getConstant(String c){
		return getConstants().get(c);	}
	
	public void printAllStatements() throws SyntaxError{
		for (String m: methods.keySet()){
			Debug.debug("Method: "+m);
			getMethod(m).printVariables();
			Method method = methods.get(m);
			Block block = method.getBlock();
			printBlock(block, 2);
			
		}
	}
	
	public void printBlock(Block block, int tab){
		ArrayList<Expression> exp;
		Block block1;
		if (block == null){
			return;
		}
		for (Statement s: block.getStatements()){
			for (int i = 0; i< tab; i++){
				Debug.debug(' ');
			}
			Debug.debug("Statement: "+s);
			if (s instanceof IfElse){
				for (int i = 0; i< tab; i++){
					Debug.debug(" ");
				}
				exp = ((IfElse)s).getExp();
				for (int i = 0; i < exp.size(); i++){
					Debug.debug("Statement: IfElse block:");
					block1 = ((IfElse)s).getIfBlock().get(i);
					printBlock(block1, tab+2);
				}
				Debug.debug("Statement: Else block:");
				block1 = ((IfElse)s).getElseBlock();
				printBlock(block1, tab +2);
			}else if(s instanceof Case){
				for (Block b: ((Case)s).getBlocks()){
					printBlock(b, tab);
				}
			}else{
				try{
					block1 = s.getBlock();
					printBlock(block1, tab +2);
				}catch(SpinError e){
					e.printStackTrace();
				}catch(Exception e){
					//do nothing. This is a terrible way to implement it.
				}
			}

		}
	}
				

	public void load(Robot robot) throws IOException, SyntaxError{
		this.robot = robot;
		this.robot.load(this);
	}
	
	
	public void run () throws Exception{
		//basically a fake method
		setRunning(true);
		callMethod(firstMethod.getName(),null, initialContext);
	}
	
	
	
	public Variable callMethod(String name, Expression[] parameters, MethodInstance callingMethod) throws Exception {
		Debug.debug("Calling method :" +name);
		MethodInstance instance = methods.get(name).getMethodInstance(callingMethod);
		instance.initializeMemory(parameters);
		Variable v = instance.execute(parameters);
		instance.releaseMemory();
		return v;
	}

	public void printVariables(){
		Debug.debug("Global Variables and Constants:");
		for (Variable v: globalVariables.values()){
			Debug.debug(v.toString());
		}
		for (Constant c: constants.values()){
			Debug.debug(c.toString());
		}
	}
	
	/**
	 * Had to put these methods somewhere. They are necessary
	 * for a SPIN implementation
	 * @param num
	 * @return
	 */
	public static int binaryToInt(String num){
		int value = 0;
		Debug.debug("num length: "+num.length(),"binary");
		Debug.debug("num: "+num,"binary");
		num = num.substring(1);
		int length = num.length()-1;
		//this is backwards, but I never bothered to fix it
		for (int i = num.length()-1 ; i >= 0  ; i--){
			if (num.charAt(i) == '1'){
				value += Math.pow(2, length- i);
			}
		}
		return value;
	}

	public static int quaternaryToInt(String num){
		int value = 0;
		int val = 0;
		num = num.substring(2);
		int length = num.length()-1;
		for (int i = 0 ; i < num.length() ; i++){
			val = num.charAt(i) - '0';
			value += val*Math.pow(4, length-i);
		}
		return value;
	}
	
	public static int hexToInt(String num){
		int value = 0;
		int val = 0;
		num = num.substring(1);
		int length = num.length()-1;
		for (int i = 0 ; i < num.length() ; i++){
			if (Character.isDigit(num.charAt(i))){
				val = num.charAt(i) - '0';
			}else{
				switch (num.charAt(i)){
				case 'a': val = 10; break;
				case 'b': val = 11; break;
				case 'c': val = 12; break;
				case 'd': val = 13; break;
				case 'e': val = 14; break;
				case 'f': val = 15; break;
				}
			}
			value += val*Math.pow(16,length - i);
		}
		return value;
	}
}
