package dang.antlr.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.nio.ByteBuffer;

import dang.exceptions.SyntaxError;
import dang.program.Debug;

/**
 * The default streamtokenizer leaves a lot to be desired.
 * @author darrylhill
 *
 */
public class TabTokenizer {


	
	
	public static final int 	TT_EOF =	-1;
	public static final int 	TT_EOL =	10;
	public static final int 	TT_NUMBER = -2;
	public static final int 	TT_WORD =	-3;
	
	public static final int TT_FLOAT = -57;
	public static final int TT_HEADER = -4;
	public static final int TT_TABBED = -5;
	public static final int TT_VARIABLETYPE = -6;
	public static final int TT_PARAMETER = -7;
	public static final int TT_RETURNVARIABLE = -8;
	public static final int TT_LOCALVARIABLE = -9;
	public static final int TT_IF = -10;
	public static final int TT_ELSEIF = -11;
	public static final int TT_REPEAT = -12;
	public static final int TT_CASE = -13;
	public static final int TT_ELSE = -14;
	public static final int TT_WAITCNT = -15;
	public static final int TT_WHILE = -16;
	public static final int TT_MULTIPLYRETURNHIGHBITS = -17;
	public static final int TT_LEASTOF = -18;
	public static final int TT_GREATESTOF = -19;
	public static final int TT_INCREMENT = -20;
	public static final int TT_DECREMENT = -21;
	public static final int TT_MODULUS = -22;
	public static final int TT_ADDTO = -23;
	public static final int TT_SUBTRACTFROM = -24;
	public static final int TT_MULTIPLYBY = -25;
	public static final int TT_DIVIDEBY = -26;
	public static final int TT_MODOF =-27;
	public static final int TT_MULTIPLYHIGHBITSOF =-28;
	public static final int TT_ABSOLUTEVALUE =-29;
	public static final int TT_SQUAREROOT =-30;
	public static final int TT_BLANKLINE = -31;
	public static final int TT_UNTIL = -32;
	/*got to here */
	//these two are not strictly necessary
	//public final static int TT_GREATERTHAN = -31;
	//public final static int TT_LESSTHAN = -32;
	public final static int TT_EQUALTO = -33;
	public final static int TT_GREATERTHANOREQUALTO = -34;
	public final static int TT_LESSTHANOREQUALTO = -35;
	public final static int TT_NOTEQUALTO = -36;
	public final static int TT_RANDOM = -37;
	
	public static final int TT_AND = -37;
	public static final int TT_OR = -38;
	public static final int TT_XOR = -39;
	public static final int TT_NOT = -40;
	public static final int TT_ASSIGNMENT = -41;
	public static final int TT_TO = -42;
	public static final int TT_STEP = -43;
	public static final int TT_STRING = -44;
	public static final int TT_STRSIZE = -45;
	public static final int TT_STRCOMP = -46;
	public static final int TT_BYTEFILL = -47;
	public static final int TT_BYTEMOVE = -48;
	public static final int TT_WORDFILL = -49;
	public static final int TT_WORDMOVE = -50;
	public static final int TT_LONGFILL = -51;
	public static final int TT_LONGMOVE = -52;
	public static final int TT_LOOKUPZ = -53;
	public static final int TT_LOOKUP = -54;
	public static final int TT_LOOKDOWNZ = -55;
	public static final int TT_LOOKDOWN = -56;
	public static final int TT_RANGE = -58;
	public static final int TT_EXTENDBYTE = -59;
	public static final int TT_EXTENDWORD = -60;
	public static final int TT_TRUE = -61;
	public static final int TT_FALSE = -62;
	public static final int TT_IFNOT = -63;
	public static final int TT_ELSEIFNOT = -64;
	public static final int TT_CASECHOICE = -65;



	
	/*
	 * don't forget all these:
	public static final String PLUS ="+";
	public static final String MINUS ="-";
	public static final String DIVIDE ="/";
	public static final String MULTIPLY ="*";
	public static final String MULTIPLYRETURNHIGHBITS ="**";
	public static final String LEASTOF ="<#";
	public static final String GREATESTOF ="#>";
	public static final String INCREMENT ="++";
	public static final String DECREMENT ="--";
	public static final String ADDTO ="+=";
	public static final String SUBTRACTFROM ="-=";
	public static final String MULTIPLYBY ="*=";
	public static final String DIVIDEBY ="/=";
	public static final String MODOF ="//=";
	public static final String MULTIPLYHIGHBITSOF ="**=";
	public static final String MOD ="//";
	public static final String ABSOLUTEVALUE ="||";
	public static final String SQUAREROOT ="^^";
	
	public final static String GREATERTHAN = ">";
	public final static String LESSTHAN = "<";
	public final static String EQUALTO = "==";
	public final static String GREATERTHANOREQUALTO = "=>";
	public final static String LESSTHANOREQUALTO = "=<";
	public final static String NOTEQUALTO = "<>";
	
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String XOR = "XOR";
	public static final String NOT = "NOT";
	 */
	
	public static final int NONE = 0;
	public static final int MATHOPERATOR = -1;
	public static final int BOOLEANOPERATOR = -2;
	public static final int LOGICALOPERATOR = -3;


	public int ttab,oldtab;
	public int oval;
	public boolean lineBegin = true;
	private int expected;
	boolean pushBack = false;
	public boolean blankline = true;
	boolean commentMode = false;
	boolean doubleCommentMode = false;
	boolean firstPos = true;
	int prev_type, peek_type;
	double prev_nval, peek_nval;
	String prev_sval, peek_sval;
	int prev_oval, peek_oval;
	String stream;
	int position;
	public double nval;
	public String sval;
	public int ttype;
	int size;
	int lineno;
	private boolean tabreset = false;
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int lineno(){
		return lineno;
	}
	public int getExpected() {
		return expected;
	}

	public void setExpected(int expected) {
		this.expected = expected;
		//Debug.debug("token expected being set to "+expected);
	}
	
	public boolean isExpected(int expected){
		return this.expected==expected;
	}
	
	public void resetExpected(){
		expected = 0;
	}

	/**
	 * probably more efficient to implement all this in Program 
	 * while parsing, but I want to hide all this ugliness and have
	 * a clean tokenizer interface
	 * @param r
	 */
	public TabTokenizer(String stream) {
		size = stream.length();
		this.stream = stream;
		position = -1;
		ttab = 0;
		lineno = 1;
	}
	
	
	public int nextToken() throws IOException, Exception{
		if (tabreset){
			ttab = 0;
			tabreset = false;
		}
		
		if (ttype == 13){
			superNextToken();
			lineno ++;
			firstPos = true;
		}else{
			firstPos = false;
		}
		
		if (pushBack){
			pushBack = false;
			return ttype;
		}
		
		oval = NONE;
		int val;
		
		//Debug.debug("calling supernexttoken");
		val = superNextToken();
		
		/*
		 * StreamTokenizer was inadequate and hard to customize.
		 * Also, it did not distinguish between floats and integers,
		 * which meant it wouldn't work for SPIN. So I made my
		 * own tokenizer. The first thing we do is check 
		 * for numbers or letters, and if we find them, 
		 * parse the word or number or float
		 */
		if ((Character.isDigit((char)val))||(val == '-')){
			parseNumber();
		}else if ((Character.isLetter((char)val))||(val=='_')){
			val = Character.toLowerCase((char)val);
			ttype = val;
			parseWord();
		}
	
		//if we get any sort of input at all, then set blankline
		//to false so we record the tab
		if ((ttype != ' ')&&(ttype != TT_EOL)&&(ttype != 13)&&(ttype != '\t')){
			blankline = false;
			lineBegin = false;
		}
		//ensuring that tab is only incremented at the beginning of a line
		//first non white space character we stop counting
		switch (ttype){		
		case TT_WORD:
			if (sval.equals("con")||sval.equals("var")||sval.equals("obj")||sval.equals("pub")
					||sval.equals("pri")){
				ttype = TT_HEADER;
				val = TT_HEADER;
			}else if (sval.equals("byte")||sval.equals("word")||sval.equals("long")){
				ttype = TT_VARIABLETYPE;
				val = TT_VARIABLETYPE;
			}else if (sval.equals("true")){
				ttype = TT_TRUE;
				val = TT_TRUE;
			}else if (sval.equals("false")){
				ttype = TT_FALSE;
				val = TT_FALSE;
			}else if (sval.equals("string")){
				ttype = TT_STRING;
				val = TT_STRING;
			}else if (sval.equals("strsize")){
				ttype = TT_STRSIZE;
				val = TT_STRSIZE;
			}else if (sval.equals("strcomp")){
				ttype = TT_STRCOMP;
				val = TT_STRCOMP;
			}else if (sval.equals("bytefill")){
				ttype = TT_BYTEFILL;
				val = TT_BYTEFILL;
			}else if (sval.equals("bytemove")){
				ttype = TT_BYTEMOVE;
				val = TT_BYTEMOVE;
			}else if (sval.equals("wordfill")){
				ttype = TT_WORDFILL;
				val = TT_WORDFILL;
			}else if (sval.equals("wordmove")){
				ttype = TT_WORDMOVE;
				val = TT_WORDMOVE;
			}else if (sval.equals("longfill")){
				ttype = TT_LONGFILL;
				val = TT_LONGFILL;
			}else if (sval.equals("longmove")){
				ttype = TT_LONGMOVE;
				val = TT_LONGMOVE;
			}else if (sval.equals("lookupz")){
				ttype = TT_LOOKUPZ;
				val = TT_LOOKUPZ;
			}else if (sval.equals("lookdownz")){
				ttype = TT_LOOKDOWNZ;
				val = TT_LOOKDOWNZ;
			}else if (sval.equals("lookup")){
				ttype = TT_LOOKUP;
				val = TT_LOOKUP;
			}else if (sval.equals("lookdown")){
				ttype = TT_LOOKDOWN;
				val = TT_LOOKDOWN;
			}else if(sval.equals("if")){
				ttype = TT_IF;
				val = TT_IF;
			}else if(sval.equals("else")){
				ttype = TT_ELSE;
				val = TT_ELSE;
			}else if(sval.equals("elseif")){
				ttype = TT_ELSEIF;
				val = TT_ELSEIF;
			}else if(sval.equals("elseifnot")){
				ttype = TT_ELSEIFNOT;
				val = TT_ELSEIFNOT;
			}else if(sval.equals("ifnot")){
				ttype = TT_IFNOT;
				val = TT_IFNOT;
			}else if(sval.equals("case")){
				ttype = TT_CASE;
				val = TT_CASE;
			}else if(sval.equals("repeat")){
				ttype = TT_REPEAT;
				val = TT_REPEAT;
			}else if(sval.equals("waitcnt")){
				ttype = TT_WAITCNT;
				val = TT_WAITCNT;
			}else if(sval.equals("while")){
				ttype = TT_WHILE;
				val = TT_WHILE;
			}else if(sval.equals("not")){
				oval = LOGICALOPERATOR;
				ttype = TT_NOT;
				val = TT_NOT;
			}else if(sval.equals("and")){
				oval = LOGICALOPERATOR;
				ttype = TT_AND;
				val = TT_AND;
			}else if(sval.equals("or")){
				oval = LOGICALOPERATOR;
				ttype = TT_OR;
				val = TT_OR;
			}else if(sval.equals("xor")){
				oval = LOGICALOPERATOR;
				ttype = TT_XOR;
				val = TT_XOR;
			}else if (sval.equals("until")){
				ttype = TT_UNTIL;
				val = TT_UNTIL;
			}else if (sval.equals("to")){
				ttype = TT_TO;
				val = TT_TO;
			}else if (sval.equals("step")){
				ttype = TT_STEP;
				val = TT_STEP;
			}
			switch (expected){
			case TT_PARAMETER:
			case TT_RETURNVARIABLE:
			case TT_LOCALVARIABLE:
				/*
				 * its complicated. if we are expecting a word that is a variable name
				 * we set it to this type and then reset the expected variable. 
				 */
				//Debug.debug("token: setting type to "+expected);
				ttype = expected;
				val = expected;
			}
			break;
		case '"':
			sval = parseStringLiteral();
			break;
		case ' ':
			//Debug.debug("space");
			if (lineBegin){
				ttab ++;
				//Debug.debug("ttab is "+ttab);
			}
			//counting the whitespace on a new line
			return nextToken();
		//TODO this should probably be deprecated
	/*	case '\t':
			if (lineBegin){
				if(ttab == TT_BLANKLINE){
					ttab = 4;
				}else{
					ttab+=4;
				}
			}
			return nextToken();*/
		case '~':
			peekNext();
			if (ttype == '~'){
				ttype = TT_EXTENDWORD;
				val = TT_EXTENDWORD;
				sval = "~~";
			}else{
				putBack();
				ttype = TT_EXTENDBYTE;
				val = TT_EXTENDBYTE;
				sval = "~";
			}
			break;	
		case '?':
			ttype = TT_RANDOM;
			val = TT_RANDOM;
			sval = "?";
			oval = '?';
			break;
		//comment indicators
		case '{':
			superNextToken();
			if (ttype == '{'){
				//we have '{{' comments
				while (true){
					while (superNextToken() != '}'){
						if (position > size -1){
							throw new SyntaxError("Line: "+lineno()+" unmatched {{");
						}
					}
					if (superNextToken() == '}'){
						blankline = true;
						break;
					}
				}
				
			}else if (ttype =='}'){
				//braces close right away
				return nextToken();
			}else{
				while (superNextToken()!= '}'){
					if (position > size -1){
						throw new SyntaxError("Line: "+lineno()+" unmatched {");
					}
				}
				blankline = true;
			}
			break;
		case TT_EOL:
			sval = "\n";
			if (firstPos){
				blankline = true;
			}else{
				blankline = false;
			}
		case TT_EOF:
			//blankline = true;
			lineBegin = true;
			tabreset = true;
			break;
		case ':':
			peekNext();
			if (ttype == '='){
				ttype = TT_ASSIGNMENT;
				sval = ":=";
				val = TT_ASSIGNMENT;
			}else{
				putBack();
			}
		break;
		case '.':
			//Debug.debug("Looking for a range with token: ");
			peekNext();
			//Debug.debug("this type: "+ttype);
			if (ttype == '.'){
				
				ttype = TT_RANGE;
				sval = "..";
				val = TT_RANGE;
			}else{
				//Debug.debug("putting it back");
				putBack();
			}
		break;
		case '\'':
			//single line comment. Advance to the end of the line
			//change of plans. return the token, handle the logic
			//in the calling class
			/*
			while(superNextToken()!=TT_EOL){}
			reverseToken(1);*/
			sval ="'";
			val = '\'';
			break;
		/**************************************************/
		/* Math operators */
		/*************************************************/
		case '/':
			sval ="/";
			oval = MATHOPERATOR;
			peekNext();
			if (ttype == '/'){
				oval = MATHOPERATOR;
				sval = "//";
				ttype = TT_MODULUS;
				val = TT_MODULUS;
			}else if (ttype == '='){
				oval = MATHOPERATOR;
				sval = "/=";
				ttype = TT_DIVIDEBY;
				val = TT_DIVIDEBY;
			}else if (ttype == TT_DIVIDEBY){
				oval = MATHOPERATOR;
				sval = "//=";
				ttype = TT_MODOF;
				val = TT_MODOF;
			}else{
				putBack();
			}
			break;
		
		case '*':
			sval = "*";
			oval = MATHOPERATOR;
			peekNext();
			if (ttype == '*'){
				oval = MATHOPERATOR;
				sval = "**";
				ttype = TT_MULTIPLYRETURNHIGHBITS;
				val = TT_MULTIPLYRETURNHIGHBITS;
			}else if (ttype == '='){
				oval = MATHOPERATOR;
				sval = "*=";
				ttype = TT_MULTIPLYBY;
				val = TT_MULTIPLYBY;
			}else if (ttype == TT_MULTIPLYBY){
				oval = MATHOPERATOR;
				sval = "**=";
				ttype = TT_MULTIPLYHIGHBITSOF;
				val = TT_MULTIPLYHIGHBITSOF;
			}else{
				putBack();
			}
			break;
		//this one doubles as a boolean operator
		case '<':
			sval = "<";
			oval = BOOLEANOPERATOR;
			peekNext();
			if (ttype == '#'){
				oval = MATHOPERATOR;
				sval = "<#";
				ttype = TT_LEASTOF;
				val = TT_LEASTOF;
			}else if (ttype == '>'){
				oval = BOOLEANOPERATOR;
				sval = "<>";
				ttype = TT_NOTEQUALTO;
				val = TT_NOTEQUALTO;
			}else{
				putBack();
			}
			break;
		case '>':
			sval = ">";
			oval = BOOLEANOPERATOR;
		break;
		case '#':
			oval = MATHOPERATOR;
			peekNext();
			if (ttype == '>'){
				oval = MATHOPERATOR;
				sval = "#>";
				ttype = TT_GREATESTOF;
				val = TT_GREATESTOF;
			}else{
				putBack();
			}
			break;	
		case '+':
			oval = MATHOPERATOR;
			sval = "+";
			peekNext();
			if (ttype == '+'){
				oval = MATHOPERATOR;
				sval = "++";
				ttype = TT_INCREMENT;
				val = TT_INCREMENT;
			}else if (ttype == '='){
				oval = MATHOPERATOR;
				sval = "+=";
				ttype = TT_ADDTO;
				val = TT_ADDTO;
			}else{
				putBack();
			}
			break;	
		case '-':
			oval = MATHOPERATOR;
			sval = "-";
			peekNext();
			if (ttype == '-'){
				oval = MATHOPERATOR;
				sval = "--";
				ttype = TT_DECREMENT;
				val = TT_DECREMENT;
			}else if (ttype == '='){
				oval = MATHOPERATOR;
				sval = "-=";
				ttype = TT_SUBTRACTFROM;
				val = TT_SUBTRACTFROM;
			}else{
				putBack();
			}
			break;	
		case '^':
			peekNext();
			if (ttype == '^'){
				oval = MATHOPERATOR;
				sval = "^^";
				ttype = TT_SQUAREROOT;
				val = TT_SQUAREROOT;
			}else{
				putBack();
			}
		case '|':
			peekNext();
			if (ttype == '|'){
				oval = MATHOPERATOR;
				sval = "||";
				ttype = TT_ABSOLUTEVALUE;
				val = TT_ABSOLUTEVALUE;
			}else{
				putBack();
			}
			break;
			
			/*******************************************/
			/*Boolean operators*************************/
			/*******************************************/
			
		case '=':
			oval = BOOLEANOPERATOR;
			sval = "=";
			peekNext();
			if (ttype == '='){
				oval = BOOLEANOPERATOR;
				sval = "==";
				ttype = TT_EQUALTO;
				val = TT_EQUALTO;
			}else if (ttype == '<'){
				oval = BOOLEANOPERATOR;
				sval = "=<";
				ttype = TT_LESSTHANOREQUALTO;
				val = TT_LESSTHANOREQUALTO;
			}else if (ttype == '>'){
				oval = BOOLEANOPERATOR;
				sval = "=>";
				ttype = TT_GREATERTHANOREQUALTO;
				val = TT_GREATERTHANOREQUALTO;
			}else{
				putBack();
			}
			break;
		}
		
		return val;

	}
	
	public int superNextToken(){
		position ++;
		if (position > size-1){
			ttype = TT_EOF;
			nval = 0.0;
			sval = "";
			return ttype;
		}
		ttype = Character.toLowerCase(stream.charAt(position));
		//Debug.debug("returning char: "+(char)ttype);
		nval = 0.0;
		sval = "";
		return ttype;
	}
	
	public void reverseToken(int steps){
		//if (ttype == 10) steps = 2;
		position -= steps;
		if (position < -1) position = -1;
		//ttype = stream.charAt(position);
	}
	
	public void peekNext() throws IOException{

		prev_nval = nval;
		prev_sval = sval;
		prev_type = ttype;
		prev_oval = oval;
		superNextToken();

	}
	
	public void putBack(){
		//Debug.debug("token: being put back");
		int rev = 1;
		/*if (ttype == 10){
			rev =2;
		}*/
		nval = prev_nval;
		sval = prev_sval;
		ttype = prev_type;
		oval = prev_oval;
		reverseToken(rev);
	}
	
	public void pushBack(){
		pushBack = true;
	}
	
	
	/**
	 * So if we get here, we have parsed a digit. In spin a number can't start with .3,
	 * and you likewise cannot do this '3.' . How on earth will I do this?  If I do ordinary
	 * chars, then I have to patch it to the former string. Which means I have to check after
	 * every word for a digit. This I will do later. This is terrible. I may have to build my own
	 * strings from scratch. Which is actually totally doable and fairly easy in the bargain.
	 * I mean, it is really all the letters (upper case are converted to lower case), the 10 digits,
	 * - sign, +sign and . And if I forget something it should be fairly easy to add later
	 * @throws Exception 
	 */
	public void parseWord() throws IOException{
		//Debug.debug("parsing a word");
		StringBuffer str = new StringBuffer();
		str.append((char)ttype);
		while(true){
			char ch = (char) superNextToken();
			if((Character.isLetterOrDigit(ch))||ch == '_'){
				str.append((char)ttype);
			}else{
				break;
			}
		}
		ttype = TT_WORD;
		sval = str.toString();
		reverseToken(1);
	}
	
	public void parseNumber() throws Exception{
		StringBuffer sb = new StringBuffer();
		//Debug.debug("parsing a number");
		int val;
		sval = "";
		boolean negative = false;
		if (ttype == '-'){
			negative = true;
			sb.append('-');
			char ch = (char) superNextToken();
			if ((!Character.isDigit(ch))&&(ch != '_')){
				//throw new SyntaxError("Line: "+ lineno() +" expected number token, received "+ch);
				return;
			}
		}
		sb.append((char)ttype);
		val = ttype - '0';
		
		while(true){
			char ch = (char) superNextToken();
			if(Character.isDigit(ch)){
				val *=10;
				val +=ttype - '0';
				sb.append(ch);
			}else if (ch =='_'){
				//we're cool, continue to parse the number
				sb.append(ch);
			}else{
				break;
			}
		}
		//now we are checking for a decimal or a range
		if (ttype == '.'){
			
			if (Character.isDigit((char)superNextToken()/*ttype*/)){
				/*
				 * We have a float value
				 */
				//Debug.debug("making a float");
				float fval = val;
				float decval = 10;			//start at first digit to the right of the decimal
				while(Character.isDigit((char)ttype)){
					val = ttype - '0';  	//get digit value
					//Debug.debug("trying to add value: "+(val/decval));
					fval += (val/decval);	//at it to the parsed value
					decval *= 10;			//the next digit will be the next place to the right
					superNextToken();
				}
				//convert it to its integer equivalent. Everything in spin
				//is bits anyway, the calling functions must format appropriately
				
				nval = Float.floatToIntBits(fval);
				if (negative) nval = -nval;
				ttype = TT_NUMBER;
				
				sval = Float.toString(fval);
				reverseToken(1);
				return;
			}
			//we peeked at the next token, but aren't using it,
			//so we set it up for the next read (see the top of nextToken())
			reverseToken(1);
			
		}
		ttype = TT_NUMBER;
		nval = val;
		if (negative) nval = -nval;
		reverseToken(1);
		sval = sb.toString();
		//System.out.println("parsed number: "+sval);
		return;
	}
	
	//everything goes on to a string until " is reached
	public String parseStringLiteral(){
		int pos = position +1;
		while(superNextToken()!='"'){
			
		}
		//Debug.debug(stream.substring(pos, position));
		return stream.substring(pos, position);
	}

}
