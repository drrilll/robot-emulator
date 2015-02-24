package dang.program.expressions;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.Variable.VariableType;
import dang.program.statements.Statement;

/**
 * Ordering is done by the parser, so we do not enforce it here. 
 * Aug 9/2012 - as of this rewrite this class will no longer be compatible with
 * ExpressionHandler. It is being rewritten to be ANTLR compatible. So some things
 * may seem strange.
 * Also we send in the operators as strings for convenience, then change
 * them into ints for faster checking and execution.
 * 
 * Any assignment variation, because there are so many, is checked for 
 * at construction time and uses a flag to ensure execution
 * @author darrylhill
 *
 */
public class SpinExpression extends Statement implements ComplexExpression {
	private Expression exp1 = null, exp2 = null;
	private String operator;
	private int op = 0;
	
	//SPIN does a lot of *= and +=. Just about every operator
	//has a corresponding equals operator, so we set a boolean flag rather than 
	//doubling the amount of operators we check for.
	private boolean eqls = false;
	public static final String PLUS ="+";
	public static final String MINUS ="-";
	public static final String DIVIDE ="/";
	public static final String MULTIPLY ="*";
	public static final String MULTIPLYRETURNHIGHBITS ="**";
	public static final String LEASTOF ="<#";
	public static final String GREATESTOF ="#>";
	public static final String PREINCREMENT ="pre++";
	public static final String POSTINCREMENT ="post++";
	public static final String PREDECREMENT ="pre--";
	public static final String POSTDECREMENT ="post--";
	public static final String MOD ="//";
	public static final String ABSOLUTEVALUE ="||";
	public static final String SQUAREROOT ="^^";
	
	//boolean
	public final static String GREATERTHAN = ">";
	public final static String LESSTHAN = "<";
	public final static String EQUALS = "=";
	public final static String EQUALTO = "==";
	public final static String GREATERTHANOREQUALTO = "=>";
	public final static String LESSTHANOREQUALTO = "=<";
	public final static String NOTEQUALTO = "<>";
	
	//logical
	public static final String AND = "and";
	public static final String OR = "or";
	public static final String XOR = "xor";
	public static final String NOT = "not";
	
	//bitwise
	public static final String BITAND = "&";
	public static final String BITOR = "|";
	public static final String BITNOT = "!";
	public static final String BITXOR = "^";
	public static final String SETHIGH = "sethigh";
	public static final String SETLOW = "setlow";
	public static final String ENCODE = ">|";
	public static final String DECODE = "|<";
	
	// ShiftOp: '->'| '<-'| '>>'| '<<'| '~>'| '><';



	//other
	public static final String RAND = "?";

	/*
	 * These are all appended with a lower case i because
	 * it was much easier to do a find/replace this way
	 */
	
	public static final int PLUSi =1;
	public static final int MINUSi =2;
	public static final int DIVIDEi =3;
	public static final int MULTIPLYi =4;
	public static final int MULTIPLYRETURNHIGHBITSi =5;
	public static final int LEASTOFi =6;
	public static final int GREATESTOFi =7;
	public static final int PREINCREMENTi =8;
	public static final int POSTINCREMENTi =9;
	public static final int PREDECREMENTi =10;
	public static final int POSTDECREMENTi =11;
	//public static final int ADDTOi =12;
	//public static final int SUBTRACTFROMi =13;
	//public static final int MULTIPLYBYi =14;
	//public static final int DIVIDEBYi =15;
	//public static final int MODOFi =16;
	//public static final int MULTIPLYHIGHBITSOFi =17;
	public static final int MODi =18;
	public static final int ABSOLUTEVALUEi =19;
	public static final int SQUAREROOTi =20;
	
	//boolean
	public final static int GREATERTHANi = 21;
	public final static int LESSTHANi = 22;
	public final static int EQUALTOi = 23;
	public final static int EQUALSi = 17;
	public final static int GREATERTHANOREQUALTOi = 24;
	public final static int LESSTHANOREQUALTOi = 25;
	public final static int NOTEQUALTOi = 26;
	
	//logical
	public static final int ANDi = 27;
	public static final int ORi = 28;
	public static final int XORi = 29;
	public static final int NOTi = 30;
	
	//bitwise
	public static final int BITANDi = 31;
	public static final int BITORi = 32;
	public static final int BITNOTi = 33;
	public static final int BITXORi = 34;
	public static final int SETHIGHi = 35;
	public static final int SETLOWi = 36;
	public static final int ENCODEi = 37;
	public static final int DECODEi = 38;
	
	// ShiftOp: '->'| '<-'| '>>'| '<<'| '~>'| '><';
	public static final int ROTATERIGHTi = 39;
	public static final int ROTATELEFTi = 40;
	public static final int SHIFTRIGHTi = 41;
	public static final int SHIFTLEFTi = 42;
	public static final int SHIFTMATHRIGHTi = 43;
	public static final int REVERSEi = 44;

	//other
	public static final int RANDi = 45;

	
	public SpinExpression(Expression exp1, Expression exp2, String operator, Method callingMethod) throws SyntaxError {
		super(callingMethod);
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operator = operator;
		setOperator();
	}
	
	public SpinExpression(Expression exp1, Expression exp2, String operator, Method callingMethod,boolean eqls) throws SyntaxError {
		this(exp1,exp2,operator,callingMethod);
		this.eqls = eqls;
	}
		
	
	//TODO throw an exception on false input
	public SpinExpression(Expression exp1, String operator, Method callingMethod) throws SyntaxError{
		super(callingMethod);
		this.exp1=exp1;
		this.operator=operator;
		setOperator();
	}
	
	public SpinExpression(Expression exp1, String operator, Method callingMethod, boolean equls) throws SyntaxError{
		super(callingMethod);
		this.exp1=exp1;
		this.operator=operator;
		this.eqls= equls;
		setOperator();
	}
	
	private void setOperator() throws SyntaxError{
		if (operator.charAt(operator.length()-1)=='='){
			eqls = true;
			operator = operator.substring(0,operator.length()-1);
		}
		if(operator.equals(PLUS)){
			op = PLUSi;
		}else if(operator.equals(MINUS)){
			op = MINUSi;
		}else if(operator.equals(DIVIDE)){
			op = DIVIDEi;
		}else if(operator.equals(MULTIPLY)){
			op = MULTIPLYi;
		}else if(operator.equals(MULTIPLYRETURNHIGHBITS)){
			op = MULTIPLYRETURNHIGHBITSi;
		}else if(operator.equals(LEASTOF)){
			op = LEASTOFi;
		}else if(operator.equals(GREATESTOF)){
			op = GREATESTOFi;
		}else if(operator.equals(PREINCREMENT)){
			op = PREINCREMENTi;
		}else if(operator.equals(POSTINCREMENT)){
			op = POSTINCREMENTi;
		}else if(operator.equals(PREDECREMENT)){
			op = PREDECREMENTi;
		}else if(operator.equals(POSTDECREMENT)){
			op = POSTDECREMENTi;	
		}else if(operator.equals(MOD)){
			op = MODi;
		}else if(operator.equals(ABSOLUTEVALUE)){
			op = ABSOLUTEVALUEi;
		}else if(operator.equals(SQUAREROOT)){
			op = SQUAREROOTi;
		}else if (operator.equals(EQUALTO)){
			op = EQUALTOi;
		}else if (operator.equals(EQUALS)){
			op = EQUALSi;
		}else if (operator.equals(GREATERTHAN)){
			op = GREATERTHANi;
		}else if (operator.equals(GREATERTHANOREQUALTO)){
			op = GREATERTHANOREQUALTOi;
		}else if (operator.equals(LESSTHAN)){
			op = LESSTHANi;
		}else if (operator.equals(LESSTHANOREQUALTO)){
			op = LESSTHANOREQUALTOi;
		}else if (operator.equals(NOTEQUALTO)){
			op = NOTEQUALTOi;
		}else if (operator.equals(BITAND)){
			op = BITANDi;
		}else if (operator.equals(BITNOT)){
			op = BITNOTi;
		}else if (operator.equals(DECODE)){
			op = DECODEi;
		}else if (operator.equals(ENCODE)){
			op = ENCODEi;
		}else if (operator.equals(BITOR)){
			op = BITORi;
		}else if (operator.equals(BITXOR)){
			op = BITXORi;
		}else if (operator.equals(AND)){
			op = ANDi;
		}else if (operator.equals(OR)){
			op = ORi;	
		}else if (operator.equals(NOT)){
			op = NOTi;
		}else if (operator.equals(XOR)){
			op = XORi;
		}else if (operator.equals(RAND)){
			op = RANDi;
		}else if (operator.equals(SETHIGH)){
			op = SETHIGHi;
		}else if (operator.equals(SETLOW)){
			op = SETLOWi;
		// ShiftOp: '->'| '<-'| '>>'| '<<'| '~>'| '><';
		}else if (operator.equals("->")){
			op = ROTATERIGHTi;
		}else if (operator.equals("<-")){
			op = ROTATELEFTi;
		}else if (operator.equals(">>")){
			op = SHIFTRIGHTi;
		}else if (operator.equals("<<")){
			op = SHIFTLEFTi;
		}else if (operator.equals("~>")){
			op = SHIFTMATHRIGHTi;
		}else if (operator.equals("><")){
			op = REVERSEi;
		}else{
			//throw new IllegalOperatorException();
			throw new SyntaxError("Illegal operator exception: "+operator);
		}
	}

	

	/**
	 * In some cases the actual variable is changed, as in absolute
	 * value and square root. This is analogous with SPIN behaviour.
	 * @throws RobotNotRunningException 
	 *
	 */
	@Override
	public Variable evaluate(MethodInstance method) throws Exception {
		Variable v;
		AnonVariable var;
		int a,b,val;
		switch(op){
		case PLUSi:
			if (eqls){
				v = exp1.evaluate(method);
				b = exp2.evaluateToValue(method);
				v.setValue(v.evaluateToValue(method)+b,method );
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(VariableType.LONG,a+b);
		case MINUSi:
			//a unary assignment statement
			if ((exp2 == null)&&(eqls)){
				v = exp1.evaluate(method);
				a = -exp1.evaluateToValue(method);
				v.setValue(a, method);
				return v;
			}
			if (eqls){
				v = exp1.evaluate(method);
				b = exp2.evaluateToValue(method);
				v.setValue(v.evaluateToValue(method)-b,method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(VariableType.LONG,a-b);
		case DIVIDEi:
			if (eqls){
				v = exp1.evaluate(method);
				b = exp2.evaluateToValue(method);
				v.setValue(v.evaluateToValue(method)/b,method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(VariableType.LONG,a/b);
		case MULTIPLYi:
			if (eqls){
				v = exp1.evaluate(method);
				b = exp2.evaluateToValue(method);
				v.setValue(v.evaluateToValue(method)*b,method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(VariableType.LONG,a*b);
		case MULTIPLYRETURNHIGHBITSi:
			if(eqls){
				return new AnonVariable(0);
			}
			//TODO this is where things get weird
			return new AnonVariable(0);
		case LEASTOFi:
			v = exp1.evaluate(method);
			a = v.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			if(a<b){
				if (eqls)return v;
				return new AnonVariable(VariableType.LONG,a);
			}else{
				if (eqls){
					v.setValue(b, method);
					return v;
				}
				return new AnonVariable(VariableType.LONG,b);
			}
		case GREATESTOFi:
			v = exp1.evaluate(method);
			a = v.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			if(a>b){
				if (eqls)return v;
				return new AnonVariable(VariableType.LONG,a);
			}else{
				if (eqls){
					v.setValue(b, method);
					return v;
				}
				return new AnonVariable(VariableType.LONG,b);
			}
		case PREINCREMENTi:
			v = exp1.evaluate(method);
			v.increment(method);
			return v;
		case POSTINCREMENTi:
			v = exp1.evaluate(method);
			var = new AnonVariable(v.evaluateToValue(method));
			v.increment(method);
			return var;
		case PREDECREMENTi:
			v = exp1.evaluate(method);
			v.decrement(method);
			return v;	
		case POSTDECREMENTi:
			v = exp1.evaluate(method);
			var = new AnonVariable(v.evaluateToValue(method));
			v.decrement(method);
			return var;	
		case MODi:
			if (eqls){
				v = exp1.evaluate(method);
				b = exp2.evaluateToValue(method);
				v.setValue(v.evaluateToValue(method)%b,method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(VariableType.LONG,a%b);
		case ABSOLUTEVALUEi:
			if (eqls){
				v = exp1.evaluate(method);
				v.setValue(Math.abs(v.evaluateToValue(method)),method);
				return v;
			}else{
				return new AnonVariable(Math.abs(exp1.evaluateToValue(method)));
			}
		case SQUAREROOTi:
			if (eqls){
				v= exp1.evaluate(method);
				v.setValue((int)Math.sqrt(v.evaluateToValue(method)),method);
				return v;
			}else{
				a = exp1.evaluateToValue(method);
				a = (int)Math.sqrt(a);
				return new AnonVariable(a);
			}
		case EQUALTOi:
			 // Since we stripped off the trailing =, this must have been "==="
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)==exp2.evaluateToValue(method)){
				v.setValue(-1, method); //int value of TRUE
			}else{
				v.setValue(0, method);  //int value of FALSE
			}
			return v;
		case EQUALSi:
			 // Since we stripped off the trailing =, this must have been "=="
			if (exp1.evaluateToValue(method)==exp2.evaluateToValue(method)){
				return TRUE;
			}else{
				return FALSE;
			}
		case GREATERTHANi:
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)>exp2.evaluateToValue(method)){
				if (eqls){v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){v.setValue(0, method); return v;}
				return FALSE;
			}
		case GREATERTHANOREQUALTOi:
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)>=exp2.evaluateToValue(method)){
				if (eqls){v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){v.setValue(0, method); return v;}
				return FALSE;
			}
		case LESSTHANi:
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)<exp2.evaluateToValue(method)){
				if (eqls){v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){v.setValue(0, method); return v;}
				return FALSE;
			}
		case LESSTHANOREQUALTOi:
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)<=exp2.evaluateToValue(method)){
				if (eqls){v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){v.setValue(0, method); return v;}
				return FALSE;
			}
		case NOTEQUALTOi:
			v = exp1.evaluate(method);
			if (v.evaluateToValue(method)!=exp2.evaluateToValue(method)){
				if (eqls){v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){v.setValue(0, method); return v;}
				return FALSE;
			}
		case BITANDi:
			if (eqls){
				Variable var1 = exp1.evaluate(method);
				val = var1.getValue()&exp2.evaluateToValue(method);
				var1.setValue(val, method);
				return var1;
			}else{
				val = exp1.evaluateToValue(method)&exp2.evaluateToValue(method);
				return new AnonVariable(val);
			}
		case BITNOTi:
			if(eqls){
				Debug.debug("bitnot equals","bitnot");
				v = exp1.evaluate(method);
				val = ~v.evaluateToValue(method);
				v.setValue(val, method);
				return v;
			}
			Debug.debug("bitnot","bitnot");
			return new AnonVariable(~exp1.evaluateToValue(method));
		case DECODEi:
			if (eqls){
				v = exp1.evaluate(method);
				val = v.evaluateToValue(method);
				val = (1<<val);
				v.setValue(val, method);
				return v;
			}
			val = exp1.evaluateToValue(method);
			val = (1<<val);
			return new AnonVariable(val);
		case ENCODEi:
			v = exp1.evaluate(method);	
			val = v.evaluateToValue(method);
			int count = 0;
			while (val != 0){
				val =val >> 1;
				count ++;
			}
			if (eqls){	
				v.setValue(count, method);
				return v;
			}
			return new AnonVariable(count);
		case BITORi:
			if (eqls){
				v = exp1.evaluate(method);
				val = v.getValue()|exp2.evaluateToValue(method);
				v.setValue(val, method);
				return v;
			}else{
				val = exp1.evaluateToValue(method)|exp2.evaluateToValue(method);
				return new AnonVariable(val);
			}
		case BITXORi:
			if (eqls){
				v = exp1.evaluate(method);
				val = v.getValue()^exp2.evaluateToValue(method);
				v.setValue(val, method);
				return v;
			}else{
				val = exp1.evaluateToValue(method)^exp2.evaluateToValue(method);
				return new AnonVariable(val);
			}
		case ANDi:
			v = exp1.evaluate(method);
			if ((v.equals(FALSE))||
					(exp2.evaluate(method).equals(FALSE))){
				//demorgan's law
				if (eqls){
					v.setValue(0, method); //value of FALSE
					return v;
				}
				return FALSE;
			}else{
				//not really sure what I should be returning here
				if (eqls){
					v.setValue(-1, method); //value of TRUE
					return v;
				}
				return TRUE;
			}
		case ORi:
			v = exp1.evaluate(method);
			if ((v.equals(FALSE))&&(exp2.evaluate(method).equals(FALSE))){
				//demorgan's law
				if (eqls){
					v.setValue(0, method);  //value of FALSE
					return v;
				}
				return FALSE;
			}else{
				//not really sure what I should be returning here
				if (eqls){
					v.setValue(-1, method);  //value of TRUE
					return v;
				}
				return TRUE;
			}
			
		case NOTi:
			v = exp1.evaluate(method);
			if (v.equals(FALSE)){
				if (eqls) {v.setValue(-1, method); return v;}
				return TRUE;
			}else{
				if (eqls){ v.setValue(0, method); return v;}
				return FALSE;
			}
		case XORi:
			if ((exp1.evaluate(method).equals(FALSE))&&(exp2.evaluate(method).equals(FALSE))){
				//demorgan's law
				return FALSE;
				/*holy crap have to be careful.
				*can't compare to true because true is -1,
				*but everything other than 0 evaluates true as well
				*/
			}else if ((exp1.evaluateToValue(method)!=0)&&(exp2.evaluateToValue(method)!=0)){
				return FALSE;
			}else{
				return TRUE;
			}
		case RANDi:

			v = exp1.evaluate(method);
			v.setValue((int)(Math.random()*Integer.MAX_VALUE), method);
			return v;

		case SETHIGHi:
			v = exp1.evaluate(method);
			var = new AnonVariable(exp1.evaluateToValue(method));
			v.setValue(-1, method);
			return var;
		case SETLOWi:
			v = exp1.evaluate(method);
			var = new AnonVariable(exp1.evaluateToValue(method));
			v.setValue(0, method);
			return var;
		// ShiftOp: '->'| '<-'| '>>'| '<<'| '~>'| '><';
			/*
					}else if (operator.equals("->")){
						op = ROTATERIGHTi;
					}else if (operator.equals("<-")){
						op = ROTATELEFTi;
					}else if (operator.equals(">>")){
						op = SHIFTRIGHTi;
					}else if (operator.equals("<<")){
						op = SHIFTLEFTi;
					}else if (operator.equals("~>")){
						op = SHIFTMATHRIGHTi;
					}else if (operator.equals("><")){
						op = REVERSEi;*/
		case ROTATERIGHTi:
			if (eqls){
				v = exp1.evaluate(method);
				a = v.evaluateToValue(method);
				b = exp2.evaluateToValue(method);
				v.setValue(Integer.rotateRight(a, b), method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(Integer.rotateRight(a, b));
		case ROTATELEFTi:
			if (eqls){
				v = exp1.evaluate(method);
				a = v.evaluateToValue(method);
				b = exp2.evaluateToValue(method);
				v.setValue(Integer.rotateLeft(a, b), method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(Integer.rotateLeft(a, b));
		case SHIFTRIGHTi:
			if (eqls){
				v = exp1.evaluate(method);
				a = v.evaluateToValue(method);
				b = exp2.evaluateToValue(method);
				v.setValue(a >>> b, method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(a >>> b);
		case SHIFTLEFTi:
			if (eqls){
				v = exp1.evaluate(method);
				a = v.evaluateToValue(method);
				b = exp2.evaluateToValue(method);
				v.setValue(a << b, method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(a << b);
		case SHIFTMATHRIGHTi:
			if (eqls){
				v = exp1.evaluate(method);
				a = v.evaluateToValue(method);
				b = exp2.evaluateToValue(method);
				v.setValue(a >> b, method);
				return v;
			}
			a = exp1.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			return new AnonVariable(a >> b);
		case REVERSEi:
			v = exp1.evaluate(method);
			a = v.evaluateToValue(method);
			b = exp2.evaluateToValue(method);
			a = Integer.reverse(a);
			val = 0;
			for (int i = 32; i >32 - b; i--){
				val += Math.pow(2, i-1);
			}
			a = a&val;
			a = a >> (32-b);
			if (eqls){
				v.setValue(a, method);
				return v;
			}
			return new AnonVariable(a);
		default:
			//throw new IllegalOperatorException();
			throw new SyntaxError("Illegal operator exception: "+operator);
		}
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		// In certain cases it can be a statement, such as x++, or x += y
		// no error checking, as it should be done in the constructor.
		//TODO all this
		return evaluateToValue(method);
	}
	
	public String toString(){
		if (exp2 == null){
			//prefix operator
			return "math: "+operator+exp1.toString();	
		}
		return "math: "+exp1.toString()+operator+exp2.toString();	
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		throw new CompilerError("no blocks in math expressions");	
		
	}

	public void setEquals(boolean eqls){
		this.eqls = eqls;
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
		//throw new CompilerError("blocks are stored in methods not methodcalls");	
		return null;
	}

	@Override
	public int evaluateToValue(MethodInstance method) throws Exception {
		return evaluate(method).evaluateToValue(method);
	}

	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOperator() {
		// TODO Auto-generated method stub
		return operator;
	}

	@Override
	public Expression getExp1() {
		// TODO Auto-generated method stub
		return exp1;
	}

	@Override
	public Expression getExp2() {
		// TODO Auto-generated method stub
		return exp2;
	}

	@Override
	public void setExp1(Expression e) {
		exp1 = e;
	}

	@Override
	public void setExp2(Expression e) {
		// TODO Auto-generated method stub
		exp2 = e;
	}

}
