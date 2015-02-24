grammar Spin2Grammar;

options {
  language = Java;
  output = AST;
  ASTLabelType = CommonTree;
} 
  
tokens{ 
 NEGATION;
 RANGE; 
 PRE;
 POST;
 BINARYOP;
 UNARYOP;
ASSIGNMENT;
POSTINCREMENT;
SETHIGH;
SETLOW;
BITNOT;
BITNOTEXP;
RAND;
NOT_OP;
}
 
@header{
package dang.antlr.parser;
import dang.robot.Robot;
import dang.program.Program;
import dang.program.Variable; 
import dang.program.ArrayAccess;
import dang.program.Variable.VariableType;
import dang.program.Method;
import dang.exceptions.CompilerError;
import dang.program.statements.Statement;
import dang.program.Block;
import dang.program.Range;
import dang.program.CaseItem;
import dang.program.expressions.Expression;
import dang.program.expressions.VariableExpression;
import dang.exceptions.SyntaxError;
import dang.program.statements.IfElse;
import dang.program.AnonVariable;
import dang.program.expressions.SpinExpression;
import dang.program.statements.Assignment;
import dang.program.statements.MethodCall;
import dang.program.statements.Case;
import dang.program.statements.Repeat;
import dang.program.statements.RepeatBlockWhile;
import dang.program.statements.RepeatConstantTimes;
import dang.program.statements.RepeatWhile;
import dang.program.statements.RepeatVariableFrom;
import dang.program.statements.RepeatUntil;
import dang.program.statements.RepeatBlockUntil;
import dang.program.statements.Wait;
import dang.program.statements.Return;
import dang.program.Debug;
import dang.program.objects.Beeper;
import dang.program.objects.BlockSensor;
import dang.program.objects.CMUCam;
import dang.program.objects.Debugger;
import dang.program.objects.DirrsSensor;
import dang.program.objects.Float32Full;
import dang.program.objects.FloatString;
import dang.program.objects.Ir8SensorArray;
import dang.program.objects.RBC;
import dang.program.objects.ServoControl;
import dang.program.objects.Sonar;
import dang.program.objects.Encoders;
import dang.program.ListItem;
import dang.program.SignExtend;
import dang.program.SpinString;
import dang.program.expressions.SpinStringWrapper;
import dang.program.expressions.Negation;
import dang.program.expressions.Cnt;
import dang.program.Address;
import java.util.LinkedList;
import dang.interpreter.ErrorDialog;
import dang.interpreter.IDE;

}

@lexer::header{
package dang.antlr.parser;
import dang.program.Debug;
import dang.interpreter.IDE;
}

@lexer::members {
   IDE ide;
    private ArrayList<Exception> errors = new ArrayList<Exception>();
    public void displayRecognitionError(String[] tokenNames,
                                        RecognitionException e) {
        errors.add(e);
    }
    public ArrayList<Exception> getErrors() {
        return errors;
    }
/*    protected Object recoverFromMismatchedToken(IntStream input,
                                            int ttype,
                                            BitSet follow)
    throws RecognitionException
{   
     RecognitionException e = new MismatchedTokenException(ttype, input);
     errors.add(e);
     Debug.debug("adding error","error");
     //new ErrorDialog(ide.getParent(), "parser error", e);
     return super.recoverFromMismatchedToken(input, ttype, follow);
    //throw e;
     } */
}


@members{
    IDE ide;
    private ArrayList<Exception> errors = new ArrayList<Exception>();
    public void displayRecognitionError(String[] tokenNames,
                                        RecognitionException e) {
        Debug.debug("adding error","error");
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        errors.add(new Exception(hdr + " " + msg));
    }
    public ArrayList<Exception> getErrors() {
        return errors;
    }
 /*   protected Object recoverFromMismatchedToken(IntStream input,
                                            int ttype,
                                            BitSet follow)
    throws RecognitionException
{   
     RecognitionException e = new MismatchedTokenException(ttype, input);
     String hdr = getErrorHeader(e);
     String msg = getErrorMessage(e, tokenNames);
     errors.add(new Exception(hdr+" "+msg));
     Debug.debug("adding error","error");
     //new ErrorDialog(ide.getParent(), "parser error", e);
     //throw e;
     return super.recoverFromMismatchedToken(input, ttype, follow);
     } */
}


prog [IDE ide]
  @init{ this.ide = ide;}
    : (conblock|objblock|varblock|method|NEWLINE!)*
    ;
  
/*---------------------------------------------------*/
 
conblock 
    :  'con'^ NEWLINE+ 
       //'_clkmode'! '='! 'xtal1'! '+'! 'pll16x'! NEWLINE!+
       (constatement (','! constatement)* NEWLINE!*)+ Dedent!
    ;

constatement
    :  ID '=' expression
    ;

/*---------------------------------------------------*/

varblock 
    :  'var'^ NEWLINE!+
       (vartype ID array? (','! ID array?)* NEWLINE!* )* Dedent!
      
    ;

vartype 
    :  'byte' 
    |  'word' 
    |  'long'
    ;

/*---------------------------------------------------*/

objblock 
    : 'obj'^ NEWLINE!+
      (i=ID ':'! STRING NEWLINE!+)* Dedent! 
    ;
/*---------------------------------------------------*/

paramlist 
    :   '(' list ')' 
    ;
            
list
    :   expression (','! expression)*
    ;

method 
    //the method parameters, localvariables and return variables have already been
    //processed by a preprocessing method, so we can discard their values here
    :  Methodheader ID paramlist!? (':'! ID!)? ('|'! ID! (','! ID!)*)? 
       block? 
    ;

/*---------------------------------------------------*/


/* **********Statement ****************************/
statement 
    : IncOp id
    | assignorpostinc
    | rand
    | unary_statement
    | not_statement
    | 'waitcnt' paramlist
    | 'return' expression? 
    |  methodCall 
    //The methodcall with no arguments
    | ID
    | objMethodCall
    | neg_statement
    | memmove
    | memfill
    ;

block_statement
    :  if_statement 
    | case_statement 
    | repeat 
    | repeatconstanttimes
    | repeatwhile 
    | repeatuntil 
    | repeatvariablefrom 
    ;

    
assignorpostinc
    : id (Assign expression -> ^(ASSIGNMENT Assign id expression)
    | IncOp -> ^(POSTINCREMENT IncOp id)
    | '~' -> ^(SETLOW id)
    | '~~' -> ^(SETHIGH id)
    | '?' -> ^(RAND id))
    ;

id
    : ID array?
    ;
    

neg_statement
    : '-' id
    ;


memmove
    : ('bytemove'|'wordmove'|'longmove')'('expression','expression','expression')'
    ;
    
    
memfill
    : ('bytefill'|'wordfill'|'longfill')'('expression','expression','expression')'
    ;
    
 /* 
if_statement 
    :  'if' expression block?  
        (NEWLINE 'elseif' expression block?)*
        (NEWLINE 'else' block?)?
    ; */
   
if_statement 
    :  'if' expression block?  
        ( 'elseif' expression block?)*
        ( 'else' block?)?
    ; 
case_statement 
    :   'case' expression Indent
        (casechoice | NEWLINE)*
        Dedent 
    ;

casechoice 
    : (expression ('..' expression)? )
      (','(expression ('..' expression)? ) )*':' block 
    ;
 
/*
  Repeat, RepeatBlockWhile, RepeatConstantTImes, RepeatUntil, RepeatVariableFrom
  RepeatWHile, should be a repeatBlockUntil
*/
repeatvariablefrom 
    : 'repeat' ID 'from' expression 'to' expression ('step' expression)? block?
    ;


repeatconstanttimes 
    : 'repeat' e=expression block? 
    ;

repeatuntil 
    : 'repeat' 'until' expression block?
    ;

repeatwhile 
    : 'repeat' 'while' expression block?
    ;

repeat   
    : 'repeat' (block|NEWLINE) (NEWLINE('until' expression|'while' expression))?
    ;
    
/* ******** Block  *******************************/

block 
    :  Indent
       (NEWLINE | statement NEWLINE | block_statement)*    
       Dedent
    ;
      
/* Expressions  *********************************/


array 
    : '['expression']'
    ;

methodCall
    : ID paramlist
    ;
    
objMethodCall
    : ID '.' ID paramlist?
    ;
    
objectConstant
    :ID '#' ID
    ;
    
string
    : 'string' '('(STRING|CHARACTER) ')'
    ;
    
setbits
    : id ('~'|'~~'|'?')?
    ;
    
identifier
    : (UnaryOp|'~'|'~~')? setbits
    ;
    
rand 
    : '?' id -> ^(RAND id)
    ;
 
unary_statement
    : (Unary2Op)+ unary2 ->^(UNARYOP unary2 Unary2Op+)
    ;
    
// - , ^^ , |< , >| , not
  
strcomp
    : 'strcomp''('expression ',' expression')'
    ;
    
strsize
    : 'strsize''('expression')'
    ;
    
lookup
    : ('lookup'|'lookupz') '('expression':' rangelist')'
    ;
    
lookdown
    : ('lookdown'|'lookdownz') '('expression':' rangelist')'
    ;
   
rangelist
    : rangeitem (','rangeitem)*
    ;
    
rangeitem
    : expression ('..'expression)?
    ;
    
assignment
    : id Assign expression
    ;
    
 /*
 * This covers object method calls, object constants, variables, constants, and methodcalls
 * in the expression format. Methodcall and object methodcall could also be statements
 */
ident 
    : objMethodCall 
    | methodCall 
    | objectConstant
    | string  
    | identifier
    | rand
    | strcomp
    | strsize
    | lookup
    | lookdown
    | CHARACTER
    | True 
    | False 
    ;
    
 IncOp: '++'|'--';
 //variable operators
 UnaryOp: '@'|'?';
 //expression operators
 Unary2Op: '+'|'-'|'^^'|'||'|'|<'| '>|'| '!';
 ShiftOp: '->'| '<-'| '>>'| '<<'| '~>'| '><';
 BitAndOp: '&';
 BitOrOp: '|'|'^';
 Multiply: '**'|'//';
 MultOp: '/'|'*';
 LimitMaxOp: '<#'|'#>';
 BoolOp: '<'|'>'|'=<'|'=>'|'<>'|'==';
 BoolNotOp: 'not';
 BoolAndOp: 'and';
 BoolOrOp: 'or';
 Assign: ':='|'+='|'-='|'*='|'**='|'/='|'//='|'#>='|'<#='|'~>='|'->='|'<-= '
         |'<<='|'>>='|'><='|'&='|'|='|'^='|'and='|'or='|'<>='|'>='|'<='|'==='
         |'=>='|'=<=';
 
// #>= <#= ~>=  ->= <-= <<= >>= ><= &= |= ^= and= or= <>= >= <= === =>= =<=
 
 /*
  * AddOp, or rather, the negation, is split between number and term. 
  * It properly belongs with term, but there is a special case for
  * floats that needs it to be integrated with number as well.
  */
 
 number
    : INT 
    | FLOAT 
    | BINARY
    | QUATERNARY
    | HEXADECIMAL
    ;

term 
    : ident 
    | '('! expression ')'! 
    | number 
    ;
    
    
preinc 
    : IncOp term -> ^(PRE IncOp term) 
    | term
    ;
 
postinc
    : (p=preinc->preinc) 
    (IncOp -> ^(POST IncOp $p))*
    ;
    
unary2 
    : ('+'!|negation^)* postinc
    ;
negation
  : '-' -> NEGATION
  ;
 
/*
unary3
    : (Unary2Op^)* unary2
    ;*/
unary3
    : unary2
    | unary_statement
    //| square_root
    ;
    
shift 
    : unary3 (ShiftOp^ unary3)* 
    ;
    
bitand 
    : shift (BitAndOp^ shift)*
    ;
    
bitor 
    : bitand (BitOrOp^ bitand)*
    ;
    
multhigh 
    : bitor(Multiply^ bitor)*
    ;

mult 
    : multhigh(MultOp^ multhigh)*
    ;
add 
    : mult (('+'^|'-'^) mult)*
    ;
    
limitmax 
    : add (LimitMaxOp^ add)*
    ;
    
boolops 
    : limitmax (BoolOp^ limitmax)*
    ;
    
not_statement
    : BoolNotOp+ boolops -> ^(NOT_OP boolops BoolNotOp+)
    ;
    
boolnot 
    : not_statement
    | boolops
    ;
    
booland 
    : boolnot (BoolAndOp^ boolnot)*
    ;
 
    
boolor
    : booland (BoolOrOp^ booland)*
    ;
    
expression 
    : boolor (Assign^ expression)?
    ;
    
//there are also the bitwise assigments which I will do later
//TODO 

/* **********end Expressions ************************************/

Indent
    :  ',<'
    ;
Dedent
    :  ',>'
    ;
    

True
    : 'true'
    ;

False
    : 'false'
    ;

Methodheader
 : 'pub' | 'pri';
 
ID  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

WS  
    :   ( ' '|'\t') {$channel=HIDDEN;}
    ;
MultiLineComment :
  ('{') ( options {greedy=false;} : . )* ('}''}'?) {$channel=HIDDEN;};
  
SingleLineComment :
  '\'' (~('\n'|'\r'))*  {$channel=HIDDEN;}; //('\n'|'\r'('\n')?)?
  

fragment HEX_DIGIT :
  ('0'..'9'|'A'..'F'|'a'..'f')('0'..'9'|'A'..'F'|'a'..'f'|'_')*;
  
    

fragment
INT : ('0'..'9')('0'..'9'|'_')*
    ;
    
fragment
FLOAT
    :   INT '.' INT EXPONENT?
    |   INT EXPONENT
    ;
        
RANGE_OR_INT
    :   ( INT '..' ) => INT  { $type=INT; }
    |   (INT '.' INT EXPONENT? | INT EXPONENT ) => FLOAT { $type=FLOAT; }
    |   INT                  { $type = INT; }
    ;    
  
Range
    : '..'
    ;
 
 
BINARY
    : '%'BINARY_DIGIT (BINARY_DIGIT|'_')*
    ;
    
fragment
BINARY_DIGIT
    :('0'|'1')
    ;

QUATERNARY
    :'%%' QUAT_DIGIT (QUAT_DIGIT|'_')*
    ;

fragment
QUAT_DIGIT
    :('0'..'4')
    ;

HEXADECIMAL
    : '$' HEX_DIGIT+
    ;

NEWLINE :
  ('\r' '\n' /*| '\r' */| '\n'|'\f');// {$channel=HIDDEN;};
  
CHARACTER :
  '"' (~('"'|'\n'|'\r')) '"' ;

STRING :
  '"' (~('"'|'\n'|'\r'))* '"';

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;


fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

