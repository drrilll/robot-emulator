package dang.interpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.ArrayAccess;
import dang.program.Block;
import dang.program.BlockHolder;
import dang.program.Debug;
import dang.program.Memory;
import dang.program.Method;
import dang.program.Method.MethodType;
import dang.program.Program;
import dang.program.Variable;
import dang.program.Variable.VariableType;
import dang.program.expressions.Expression;
import dang.program.expressions.SpinExpression;
import dang.program.expressions.VariableExpression;
import dang.program.statements.Assignment;
import dang.program.statements.IfElse;
import dang.program.statements.MemFill;
import dang.program.statements.MemMove;
import dang.program.statements.MethodCall;
import dang.program.statements.Repeat;
import dang.program.statements.RepeatUntil;
import dang.program.statements.RepeatVariableFrom;
import dang.program.statements.RepeatWhile;
import dang.program.statements.Statement;

/**
 * This is largely deprecated with the introduction of ANTLR, but
 * it did work for reading in the method headers, so it is still being
 * used in that capacity, since more than two passes in ANTLR is possible,
 * but kind of a headache. Method headers are read in first so that any
 * references to them in other methods can be reliably resolved. 
 *
 */
public class MethodHandler {
	
	
	/*
	 * FYI first line has name, parameters in brackets, colon return variable,
	 * |local variables, which are all longs, separated by commas.
	 * The first line alone is pretty daunting.
	 */
	
	private TreeSet<Integer> expectedSet = new TreeSet<Integer>();
	private boolean firstLine = true;
	private String name;
	private Program program;
	private Method currentMethod;
	private int numberOfParameters;
	private String methodName;
	
	public static final int METHODCALL = 1;
	public static final int OBJECT = 2;
	public static final int VARIABLE = 3;
	public static final int WAIT = 4;
	private int STATE;
	Memory memory;
	

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	public void setNumberOfParameters(int numberOfParameters) {
		this.numberOfParameters = numberOfParameters;
	}

	public Method getCurrentMethod() {
		return currentMethod;
	}

	public void setCurrentMethod(Method currentMethod) {
		this.currentMethod = currentMethod;
	}

	public TreeSet<Integer> getExpectedSet() {
		return expectedSet;
	}

	public void setExpectedSet(TreeSet<Integer> expectedSet) {
		this.expectedSet = expectedSet;
	}

	public String[] getStringArray(ArrayList<String> list){
		String[] locVar = new String[list.size()];
		for (int i = 0; i < list.size(); i ++){
			locVar[i] = list.get(i);
		}
		return locVar;
	}

	public boolean isExpected(int expected) {
		return expectedSet.contains(expected);
	}

	public void addExpected(int expected) {
		expectedSet.add(expected);
	}
	
	public void addExpected(int[] expected){
		for (int i: expected){
			expectedSet.add(i);
		}
	}
	
	public void removeExpected(int expected){
		expectedSet.remove(expected);
	}
	
	public void setExpected(int...expected){
		expectedSet.clear();
		addExpected(expected);
	}
		
	public void printExpected(){
		for (int s: expectedSet){
			Debug.debug("Expected: "+ s);
		}
	}
	
	/**
	 * essentially a very complicated way of maintaining state
	 * don't stare too long, your eyes will cross
	 * @param expected I expected to be able to code this better
	 */
	public void setExpected(int expected, SpinTokenizer token){
		expectedSet.clear();
		expectedSet.add(expected);
		token.setExpected(expected);
		Debug.debug("Setting token expected: " +expected);

	}
	
	public void resetExpected(){
		expectedSet.clear();
	}

	public boolean isFirstLine() {
		return firstLine;
	}

	public void setFirstLine(boolean firstLine) {
		this.firstLine = firstLine;
		if (!firstLine){
			//transitioning from the header line and into the code,
			//so we reset the expected variable
			//hopefully this doesn't bite me in the ass
			setExpected(SpinTokenizer.TT_WORD,SpinTokenizer.TT_EOL);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
	public boolean isVariable(String string){
		return isGlobalVariable(string)||isMethodVariable(string)||
				isConstant(string);
	}
	public boolean isGlobalVariable(String string){
		return getProgram().getGlobalVariables().containsKey(string);
	}
	
	public boolean isMethodVariable(String string){
		return getCurrentMethod().isVariable(string);
	}
	
	public boolean isConstant(String string){
		return getProgram().isConstant(string);
	}
	
	public boolean isObject(String string){
		return getProgram().getObjects().containsKey(string);
	}

	private void printAllStatements(){
		
	}

	public MethodHandler(Program program) {
		this.program = program;
		this.memory = program.getMemory();
	}
	
	public Method readMethods(SpinTokenizer token, Program program) throws Exception{
		String name = null, returnVariable = null;
		ArrayList<String> parameters = new ArrayList<String>();
		ArrayList<String> localVariables = new ArrayList<String>();
		MethodType type;
		setExpected(SpinTokenizer.TT_WORD);
		//consume the header
		token.nextToken();
		if (token.sval.equals("pub")){
			type = MethodType.PUB;
		}else if (token.sval.equals("pri")){
			type = MethodType.PRI;
		}else{
			throw new CompilerError("Line: "+token.lineno()+" Expected pub or pri, got: "+token.sval);
		}
		while (true){
			token.nextToken();
			//a little sloppy, but I can't call token twice in
			//the while loop, so I use break exclusively to exit the loop
			Debug.debug("Methodheader: indented: "+ token.ttab);
			Debug.debug("Methodheader: Expected: "+ getExpectedSet());
			Debug.debug("Methodheader: Type: "+ token.ttype);
			Debug.debug("Methodheader: sval: "+token.sval);

			printExpected();
			//Debug.debug("Expected: "+token.ttype);
			if (!isExpected(token.ttype)){
				throw new SyntaxError("Line: "+token.lineno()+" Malformed method exception: "+(char)token.ttype+" ascii: "+
			token.ttype);
			}
			switch (token.ttype){
			case SpinTokenizer.TT_WORD:
				name = token.sval;
				setExpected('(','|',':',SpinTokenizer.TT_EOL, SpinTokenizer.TT_EOF);
				break;
			case '(':
				setExpected(SpinTokenizer.TT_PARAMETER, token);
				break;
			case ':':
				setExpected(SpinTokenizer.TT_RETURNVARIABLE, token);
				break;
			case '|':
				setExpected(SpinTokenizer.TT_LOCALVARIABLE, token);
				break;
			case ')':
				setExpected('|',':',SpinTokenizer.TT_EOL, SpinTokenizer.TT_EOF);
				break;
			case ',':
				/* wow. I really don't want to explain this
				 * state is stored in the tokenizer. It seemed like
				 * a good idea at the time. Since comma can
				 * occur at different places, this remembers what
				 * kind of variable we are expecting. I may regret this
				 * design yet
				 */
				setExpected(token.getExpected());
				break;
			case SpinTokenizer.TT_PARAMETER:
				parameters.add(token.sval);
				setExpected(')',',');
				break;
			case SpinTokenizer.TT_RETURNVARIABLE:
				Debug.debug("***************************return variable************");
				returnVariable = token.sval;
				setExpected('|',SpinTokenizer.TT_EOL, SpinTokenizer.TT_EOF);
				//only 1 return variable, so we reset our "memory" so that
				//it throws an error if someone tries to assign more than 1
				token.setExpected(0);
				break;
			case SpinTokenizer.TT_LOCALVARIABLE:
				localVariables.add(token.sval);
				setExpected(',',SpinTokenizer.TT_EOL,SpinTokenizer.TT_EOF);
				break;
			case SpinTokenizer.TT_EOL:
			case SpinTokenizer.TT_EOF:
				//TODO who knows what happens if either the parameters or localVaribles are
				//empty. I don't
				return new Method(name, type, getStringArray(parameters), 
						getStringArray(localVariables),returnVariable, program);
			}
		}

	}
}

/*
 * I leave it here for posterity
 */
	/*
	
	public void interpret(SpinTokenizer token) throws Exception {
		addExpected(SpinTokenizer.TT_WORD);
		Debug.debug("token sval: "+token.sval);
		token.nextToken();
		Debug.debug("nexttoken sval: "+token.sval);
		String methodName = token.sval;
		setCurrentMethod(getProgram().getMethod(methodName));
		if (getCurrentMethod() == null){
			throw new CompilerError("Line: "+token.lineno()+" There is no method: "+methodName);
		}
		//not sure if I should push a 0 tab by default. Probably for a method
		//we already have all the header information, so we skip to the next line
		while(token.ttype != SpinTokenizer.TT_EOL){
			token.nextToken();
		}
		//make a block and send in the method it belongs to, along
		//with the tab
		makeBlock(token,getCurrentMethod(), 0,0);
	}


	/*
	 * How do I know when a block is ended? The indentation returns to an earlier
	 * value, the method ends, or the file ends. So we implement this method recursively,
	 * each block tracks its own tab value. Should work.
	 */
	/**
	 * 
	 * @param token
	 * @param blockStatement
	 * This is the statement with which this block is associated
	 * @param tab
	 * @return
	 * @throws SyntaxError
	 * @throws IOException
	 */
	/*
	public void makeBlock(SpinTokenizer token, BlockHolder blockStatement, int tab, int level) 
			throws Exception{
		//you need to check that this is indented more than the last
		//one, otherwise return right away
		//TODO there still might be lurking logic errors
		//sometimes we need to temporarily store the tab
		int memTab;
		Debug.debug("Block tab is "+tab);
		Debug.debug("Making the block level "+ level);
		Block block = new Block(getCurrentMethod());
		main:
		do{
			token.nextToken();
			Debug.debug("block tab is: "+tab);
			Debug.debug("ttab is: "+token.ttab);
			Debug.debug("In main loop of making the block level: "+level);
			Debug.debug("ttype: "+token.ttype);
			Debug.debug("sval: "+token.sval);
			if (token.ttab != 0){
				if (token.ttab <= tab){
				//TODO wtf is this? fix this shit to maintain blocks properly
				//with tabs
				//Gar for now I am not checking proper alignment initially, 
				Debug.debug("breaking out of loop");
				token.pushBack();
				//token.reverseToken(1);
				break main;
				}
			}
			Statement statement;
			switch (token.ttype){
			/*can be a variable or another method or an object. Will only
			* deal with the first two for now
			* TODO deal with objects
			* there are one or two other cases, but for now just WORD
			* There are actually a few more cases, bytemove, bytefill,
			* wordfill, wordmove, longfill, longmove 
			*
			/*
			case SpinTokenizer.TT_WORD:
				if (getProgram().isMethod(token.sval)){
					statement = makeMethodCall(getProgram(), token);
					block.addStatement(statement);
				}else if (getProgram().isVariable(token.sval)){
					//its a variable, so parse the rest and see what's what
					//for now I will assume assignment
					//makeAssignment includes make increment and decrement
					statement = makeAssignment(token);
					block.addStatement(statement);

				}else if (getProgram().isObject(token.sval)){
					Debug.debug("Object sval: "+token.sval);
					//its an object, so you know
					Program program = getProgram().getObjects().get(token.sval);
					if (token.nextToken() == '.'){
						token.nextToken();
						/*
						 * I need to be able to tell the current method if I need
						 * more memory here. Not just for the objects, but if
						 * people implement their own objects down the road. Essentially
						 * I have a method that returns a string pointer. So it is the
						 * responsibility of the calling method to manage that memory. If
						 * I have a method call, there is always a calling method. I 
						 * thought telling it at runtime would be adequate, but I guess 
						 * not. Really each statement should have a reference to its
						 * calling method, and this should be done at compile time.
						 * Holy christ fixing this will be a nightmare.
						 *
						statement = makeMethodCall(program, token);
						block.addStatement(statement);
					}else{
						throw new SyntaxError("Line: "+token.lineno()+" object call: unknown token: "+(char)token.ttype);
					}
				}else if (getCurrentMethod().isVariable(token.sval)){
					statement = makeAssignment(token);
					block.addStatement(statement);
				}else{
					//what else could it be? There might be more
					throw new SyntaxError("Line: "+token.lineno()+" unknown string: "+token.sval);
				}
			break;
			case '(':

			break;
			case SpinTokenizer.TT_IF:
				if (token.nextToken()!='('){
					throw new SyntaxError("Line: "+token.lineno()+" Malformed if expression");
				}
				//the tab gets reset once you do all this mumbo jumbo
				memTab = token.ttab;
				Expression expression = expressionHandler.parseExpression(token);
				Debug.debug("Expression: "+expression);
				statement = new IfElse(expression, getCurrentMethod());
				makeBlock(token,statement, memTab, level +1);
				block.addStatement(statement);
			break;
			case SpinTokenizer.TT_ELSE:
				Debug.debug("Else last statement: "+block.getLastStatement());
				if (block.getLastStatement() instanceof IfElse){
					IfElse ifstatement = (IfElse)block.getLastStatement();
					//reset the tab to where the else statement is,
					//since we exited the if statement block 
					//and lost the tab in the process, 
					memTab = token.ttab;
					//nextToken, otherwise we will keep coming back here
					token.nextToken();
					Debug.debug("making the else block");
					makeBlock(token,ifstatement, memTab, level +1);
					//we don't need to add it since it is already there
				}else{
					throw new SyntaxError("Line: "+token.lineno()+" misplaced else statement");
				}
			break;
			/*
			 * This is logically identical to nested else if's (ie else, followed by an
			 * if on the following line), with the exception that the blocks are not indented.
			 * So we do the same thing, but add another if statement at the same block. 
			 * That might not work. Or it might. Probably need testing.
			 * That will work but I need somehow to add another if statement to the block.
			 * Or I can build something specific. The else statement starts a new block.
			 * this is an else statement block, but will start with an if statement that is
			 * at the same tab level. In fact the entire block is simply that if statement. 
			 * Because it is supposed to be the same block, so falling out of the if statement
			 * means falling out of the else block also.
			 * 
			 * So if I set the expression, the ifelse statement will expect an
			 * ifelse block, otherwise it sets the else block, at which point further
			 * manipulation throws an error.
			 *
			case SpinTokenizer.TT_ELSEIF:
				Debug.debug("ElseIf last statement: "+block.getLastStatement());
				if (block.getLastStatement() instanceof IfElse){
					IfElse ifstatement = (IfElse)block.getLastStatement();
					//reset the tab to where the else statement is,
					//since we exited the if statement block 
					//and lost the tab in the process, 
					memTab = token.ttab;
					//nextToken, otherwise we will keep coming back here
					token.nextToken();
					Debug.debug("making the elseif block");
					/*
					 * important to note that we are constructing an if statement here
					 *
					//TODO I have no idea if this will actually work
					//Block newblock = new Block();
					Expression exp = getExpressionHandler().parseExpression(token);
					Debug.debug("Expression for elseif: "+exp);
					ifstatement.setExp(exp);
					makeBlock(token,ifstatement, memTab, level +1);
				}else{
					throw new SyntaxError("Line: "+token.lineno()+" misplaced elseif statement");
				}
			break;
			case SpinTokenizer.TT_REPEAT:
				/*
				 * repeat
				 * repeat 10
				 * repeat i from 0 to 10
				 * repeat i from 0 to 10 step 2
				 *  //have to check for a variable where i is, not just
				 *  //an expression. Otherwise could get messy. Variable is
				 *  //followed by FROM, expression is followed by EOL
				 * repeat while (expression)
				 * repeat until (expression)
				 * repeat
				 *   block
				 * while (expression)
				 * 
				 * piece of cake haha
				 * if its a variable, the next token must be a FROM or else
				 * it is an expression (as in repeat (expression))
				 * so after repeat, there can be EOL, a variable followed by FROM, 
				 * an expression (number,variable,method or bracket), while, or until
				 *
				memTab = token.ttab;
				statement = makeRepeatStatement(token);
				//this should end on an EOL, so
				//token.nextToken();
				Debug.debug("making the repeat block");
				Debug.debug("Current token ttype: "+token.ttype+" sval: "+token.sval);
				makeBlock(token, statement, memTab, level +1);
				token.nextToken();
				
				/*
				 * This rather bulky block checks for and parses while or until
				 * statements that follow the repeat block. Its clunky.
				 *
				
				if (token.ttype == SpinTokenizer.TT_WHILE){
					if ((statement instanceof Repeat)&&(((Repeat)statement).getExp() == null)){
						token.nextToken();
						Debug.debug("parsing expression for repeat-while"); 
						Expression exp = expressionHandler.parseExpression(token);
						((Repeat)statement).setExp(exp);
						((Repeat)statement).setWhileStatement(true);
					}
				}else if (token.ttype == SpinTokenizer.TT_UNTIL){
					if ((statement instanceof Repeat)&&(((Repeat)statement).getExp() == null)){
						token.nextToken();
						Debug.debug("parsing expression for repeat-until"); 
						Expression exp = expressionHandler.parseExpression(token);
						((Repeat)statement).setExp(exp);
						((Repeat)statement).setUntilStatement(true);
					}	
				}
				block.addStatement(statement);

				//TODO here we have to check for a trailing while or until statement
				
			break;
			case SpinTokenizer.TT_BYTEFILL:
				statement = makeFill(token, VariableType.BYTE);
				block.addStatement(statement);
				break;
			case SpinTokenizer.TT_BYTEMOVE:
				statement = makeMove(token, VariableType.BYTE);
				block.addStatement(statement);
				break;
			case SpinTokenizer.TT_WORDFILL:
				statement = makeFill(token, VariableType.WORD);
				block.addStatement(statement);
				break;
			case SpinTokenizer.TT_WORDMOVE:
				statement = makeMove(token, VariableType.WORD);
				block.addStatement(statement);
				break;
			case SpinTokenizer.TT_LONGFILL:
				statement = makeFill(token, VariableType.LONG);
				block.addStatement(statement);
				break;
			case SpinTokenizer.TT_LONGMOVE:
				statement = makeMove(token, VariableType.LONG);
				block.addStatement(statement);
				break;	
			case SpinTokenizer.TT_EOL:
				//do nothing I guess. Any error will show up next pass,
				//or else at runtime
				break;
			case SpinTokenizer.TT_EOF:
				break main;
			}
		}while (token.ttype!=SpinTokenizer.TT_HEADER);
		Debug.debug("returning the block at bottom level "+ level);
		printAllStatements();
		blockStatement.setBlock(block);
	}
	
	public Statement makeFill(SpinTokenizer token, VariableType type) throws Exception{
		if (token.nextToken()!='('){
			throw new SyntaxError("Line: "+token.lineno()+" Fill statement, expected (, recieved: "+(char)token.ttype);
		}
		//all token advances are handled by the calling class
		token.nextToken();
		Expression address = expressionHandler.parseExpression(token);
		token.nextToken();
		Expression value = expressionHandler.parseExpression(token);
		token.nextToken();
		Expression length = expressionHandler.parseExpression(token);
		if (token.nextToken()!=SpinTokenizer.TT_EOL){
			throw new SyntaxError("Line: "+token.lineno()+" Fill statement, expected EOL, recieved: "+(char)token.ttype);
		}
		return new MemFill(address, value, length, type, getCurrentMethod());
	}
	
	public Statement makeMove(SpinTokenizer token, VariableType type) throws Exception{
		if (token.nextToken()!='('){
			throw new SyntaxError("Line: "+token.lineno()+" Move statement, expected (, recieved: "+(char)token.ttype);
		}
		//all token advances are handled by the calling class
		token.nextToken();
		Expression address1 = expressionHandler.parseExpression(token);
		token.nextToken();
		Expression address2 = expressionHandler.parseExpression(token);
		token.nextToken();
		Expression length = expressionHandler.parseExpression(token);
		if (token.nextToken()!=SpinTokenizer.TT_EOL){
			throw new SyntaxError("Line: "+token.lineno()+" Move statement, expected EOL, recieved: "+(char)token.ttype);
		}
		return new MemMove(address1, address2, length, type,getCurrentMethod());
	}

	public Statement makeRepeatStatement(SpinTokenizer token) throws Exception{
		//at this early point we currently have "repeat" as our token
		Expression expression;
		token.nextToken();
		switch (token.ttype){
		case SpinTokenizer.TT_EOL:
			//TODO this may need to be changed to a repeatwhile or repeatuntil
			return new Repeat(getCurrentMethod());
		case SpinTokenizer.TT_NUMBER:
			expression = expressionHandler.parseExpression(token);
			return new Repeat(expression,getCurrentMethod());
		case SpinTokenizer.TT_WORD:
			//TODO may have to do a next token here
			//It can be either a method or a variable, or in the case
			//of above, a number. If a variable we check if the next token
			//is FROM, or else we parse for an expression.
			if (isVariable(token.sval)){
				String variable = token.sval;
				token.peekNext();
				if(token.ttype == SpinTokenizer.TT_WORD){
					if (token.sval.equals("from")){
						return makeRepeatVariableFrom(variable,token);
					}
				}
				token.putBack();
			}
			expression = expressionHandler.parseExpression(token);
			return new Repeat(expression,getCurrentMethod());
		case SpinTokenizer.TT_WHILE:
			token.nextToken();
			expression = expressionHandler.parseExpression(token);
			return new RepeatWhile(expression,getCurrentMethod());
		case SpinTokenizer.TT_UNTIL:
			token.nextToken();
			expression = expressionHandler.parseExpression(token);
			return new RepeatUntil(expression,getCurrentMethod());
		default:
			throw new SyntaxError("Line: "+token.lineno()+" unknown token type: "+token.ttype +" sval: "+token.sval);
		}
	}
	
	public Statement makeRepeatVariableFrom(String variable, SpinTokenizer token) 
			throws Exception{
		Expression exp1, exp2, step;
		/*
		Variable v;
		if (isMethodVariable(variable)){
			v = getCurrentMethod().getVariable(variable);
		}else if (isConstant(variable)){
			v = getProgram().getConstant(variable);
		}else{
			v = getProgram().getVariable(variable);
		}*
		token.nextToken();
		exp1 = expressionHandler.parseExpression(token);
		//not sure what token might be returned
		if (token.ttype == SpinTokenizer.TT_TO){
			token.nextToken();
			exp2 = expressionHandler.parseExpression(token);
		}else{
			throw new SyntaxError("Line: "+token.lineno()+" expecting 'to'");
		}
		if (token.ttype == SpinTokenizer.TT_STEP){
			Debug.debug("found step");
			token.nextToken();
			step = expressionHandler.parseExpression(token);
			return new RepeatVariableFrom(exp1,exp2, variable, step,getCurrentMethod());
		}else{
			Debug.debug("didn't find step");
			return new RepeatVariableFrom(exp1,exp2,variable,getCurrentMethod());
					
		}
	}
	
	
	public MethodCall makeMethodCall(Program program, SpinTokenizer token) throws Exception{
		Debug.debug("MakingMethodCall: "+ token.sval);
		String name = token.sval;
		//TODO not sure what will happen here if there
		//are multiple nested objects. but it should work for now
		program.setRobot(getProgram().getRobot());
		Method method = program.getMethod(token.sval);
		int parameters = method.getNumberOfParameters();
		int paramCount = 0;
		if (parameters>0){
			Debug.debug("Num of params: "+parameters);
			setExpected('(');
		}else{
			return new MethodCall(name, null, program, getCurrentMethod());
		}
		Expression[] expressions = new Expression[parameters];
		token.nextToken();
		printExpected();
		main:
		while (true){
			//I am starting to hate the way I handled this. Experience has
			//taught me that this expected stuff is unnecessary
			//so "token.ttype ==']'" is a bit of a hack
			if ((isExpected(token.ttype))||(token.ttype ==']')){
				//handle what we expect
				switch (token.ttype){
				case '(':
				case ',':
					token.nextToken();
					expressions[paramCount] = expressionHandler.parseExpression(token);
					Debug.debug("MethodHandler Expression: "+expressions[paramCount]);
					paramCount ++;
					if (paramCount<parameters){
						setExpected(',');
					}else{
						setExpected(')');
					}
					break;
				case ')':
					if (paramCount != parameters){
						throw new SyntaxError("Line: "+token.lineno()+" malformed method exception");
					}else{
						//This may be set in an expression, so this line is
						//no longer valid. Exit as soon as the method is done,
						//and handle what comes next in whatever method called
						//this one
						//setExpected(SpinTokenizer.TT_EOL);
						break main;
					}
				case SpinTokenizer.TT_EOL:
					throw new SyntaxError("Line: "+token.lineno()+" malformed method exception");
				case ']':
					token.nextToken();
					break;
				}
			}else{
				//something is wrong
				throw new SyntaxError("Line: "+token.lineno()+" malformed method exception token: "+token.ttype);
			}
		}
		return new MethodCall(name,expressions, program, getCurrentMethod());
	}
	
	public Statement makeAssignment(SpinTokenizer token) throws Exception{
		/*
		Variable variable;
		if (getProgram().isVariable(token.sval)){
			variable = getProgram().getGlobalVariables().get(token.sval);
		}else if(getCurrentMethod().isVariable(token.sval)){
			variable = getCurrentMethod().getVariable(token.sval);
		}else{
			throw new CompilerError("Line: "+token.lineno()+" makeAssignment: no variable found");
		}*
		//setExpected(SpinTokenizer.TT_ASSIGNMENT, SpinTokenizer.TT_DECREMENT, SpinTokenizer.TT_INCREMENT);
		VariableExpression variable = new VariableExpression(token.sval);
		token.nextToken();
		Expression expression = null;
		while(true){
			switch(token.ttype){
			case SpinTokenizer.TT_ASSIGNMENT:
				Debug.debug("Methodhandler.makeassignment: current method: "+
						getCurrentMethod().getName());
				token.nextToken();
				expression = expressionHandler.parseExpression(token);
				Debug.debug("Returning assignment statement");
				return new Assignment(variable, expression,getCurrentMethod());
			case SpinTokenizer.TT_DECREMENT:
			case SpinTokenizer.TT_INCREMENT:
				return new SpinExpression(variable, token.sval, getCurrentMethod());
			case '[':
				//an array access
				token.nextToken();
				Expression arrayOffset = expressionHandler.parseExpression(token);
				/*if (token.nextToken() != ']'){
					throw new SyntaxError("malformed array access, expected ], found "+token.ttype);
				}*
				token.nextToken();
				variable.setOffset(arrayOffset);
				break;
			default:
				throw new SyntaxError("Line: "+token.lineno()+" malformed assignment exception, token: "+token.ttype);
			}
		}

	}	
	
	public void cleanup() throws SyntaxError {
		// TODO Auto-generated method stub
		
	}
	
	

}*/
