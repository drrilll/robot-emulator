tree grammar ProgWalker;
 
options {
  language = Java;
  tokenVocab = Spin2Grammar;
  ASTLabelType = CommonTree;
} 
  
tokens{
  GlobalVariable;
  LocalVariable; 
  Method;  
  SpinObject;  
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
import dang.program.statements.MemMove;
import dang.program.statements.MemFill;
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
import dang.program.objects.SpinObject;
import dang.program.ListItem;
import dang.program.SignExtend;
import dang.program.SpinString;
import dang.program.expressions.SpinStringWrapper;
import dang.program.expressions.SpinExpression;
import dang.program.expressions.Negation;
import dang.program.expressions.Cnt;
import dang.program.expressions.StrComp;
import dang.program.expressions.StrSize;
import dang.program.expressions.LookUp;
import dang.program.expressions.LookDown;
import dang.program.expressions.RandomExpression;
import dang.program.Address;
import dang.program.Constant;
import dang.program.SpinListItem;
import dang.program.SpinList;
import dang.program.ListItem;
import dang.program.SList;
import dang.program.Range;
import dang.interpreter.SpinTokenizer;
import dang.interpreter.Compiler;
import java.io.File;
import javax.swing.JLabel;
import dang.interpreter.IDE;
}

@members{
  Robot robot;
  Program program;
  Method currentMethod;
  JLabel message;
  String workingDirectory;
  IDE ide;
}
 
prog [Program prog, JLabel message, String workingDirectory, IDE ide] returns [Program program] throws Exception
@init {
    //the peculiarities of ANTLR necessitate a lot of sharing. Great program though
    robot = $prog.getRobot();
    this.program = $prog;
    $program = $prog;
    this.workingDirectory = $workingDirectory;
    this.message = $message;
    this.ide = ide;
    }
    
   :  (conblock|objblock|varblock|method)*
      //(conblock|varblock|objblock)*
   ;
   
conblock throws Exception
    : ^('con' NEWLINE+ constatement*){Debug.debug("Conblock");}
    ;

constatement throws Exception
    :  i=ID '=' e=expression
       {Debug.debug("Constatement: ");
       Debug.debug("constant: "+ $i.getText() + " Expression: "+e);
       program.addConstant($i.getText(), e);}
    ;
   
varblock throws Exception
/*
 * Why the intermediate Expression variable ar? Apparently the ANTLR $array.exp
 * variable isn't reset to null after each iteration, ie, if you read an array once
 * that value will persist even if the next iteration has no array. Grrr. And it's
 * a read only value, so it becomes necessary to introduce an intermediate variable
 * and do your own house cleaning, ie, reset the array to null.
 */
@init{Expression ar = null;}
    :  ^('var' {Debug.debug("Varblock");}
        (vartype {Debug.debug("Vartype: "+ $vartype.mtype);}(ID (array {ar = $array.exp;})? 
        {if(ar != null){
            program.addGlobalVariable($ID.getText(),$vartype.mtype,ar);
            ar = null;
        }else{
            program.addGlobalVariable($ID.getText(),$vartype.mtype);
        }}
        )+)*)
    ;

vartype returns [VariableType mtype]
    :  'byte' {$mtype = VariableType.BYTE;}
    |  'word' {$mtype = VariableType.WORD;}
    |  'long' {$mtype = VariableType.LONG;}
    ;
    
objblock throws Exception
@init{String n;}
    : ^('obj' (i=ID s=STRING 
      {n=($s.getText().substring(1,$s.getText().length()-1));
      if (n.equals("servocontrol")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new ServoControl(program));
        }else if (n.equals("blocksensor")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new BlockSensor(program));
        }else if (n.equals("floatstring")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new FloatString(program));
        }else if (n.equals("float32full")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Float32Full(program));
        }else if (n.equals("beeper")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Beeper(program));
        }else if (n.equals("dirrssensor")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new DirrsSensor(program));
        }else if (n.equals("pingsensor")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Sonar(program));
        }else if (n.equals("cmucam")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new CMUCam(program));
        }else if (n.equals("debugger")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Debugger(program));
        }else if (n.equals("rbc")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new RBC(program));
        }else if (n.equals("ir8sensorarray")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Ir8SensorArray(program));
        }else if (n.equals("encoders")){
          ADebug.debug("Adding object: "+$i.getText());
          program.addObject($i.getText(), new Encoders(program));
        }else{
          //check the working directory for a matching file name
          //for now throw an exception
          String separator = System.getProperty("file.separator");
          File file = new File(workingDirectory+separator+n+".spin");
          Debug.debug("looking for file "+file.getAbsolutePath(),"file");
          if (!file.exists()){throw new SyntaxError("file "+n+".spin not found");}
          SpinObject object = new SpinObject(n, program);
          Compiler compiler = new Compiler(robot, message, ide);
          compiler.compileSpinObject(n,file, object);
          program.addObject($i.getText(), object);
        }})*)
    ;
    
method throws Exception
    : Methodheader i=ID 
      {if (program.isMethod($i.getText())){
        currentMethod = program.getMethod($i.getText());
       }else{
        throw new CompilerError("There is no method " + $i.getText());
       }
       ADebug.debug("Read in method header: "+ $i.getText());}
      block? {currentMethod.setBlock($block.block);}
    ;
    
block returns [Block block] throws Exception
  @init{ block = new Block(currentMethod);}
    :   Indent
       (NEWLINE 
       | s=statement NEWLINE {$block.addStatement($s.statement);
        ADebug.debug("Block adding statement: "+ $s.statement);}
       | b = block_statement {$block.addStatement($b.statement);} )*
        Dedent
    ;
    
statement returns [Statement statement] throws Exception 
    : a=assignment        
     {if ($a.exp instanceof Assignment){
        $statement = (Assignment)$a.exp;
     }else{ 
        $statement = (SpinExpression)$a.exp;
     }}
    | p=postincrement     {$statement = (SpinExpression)$p.exp;}
    | rand                {$statement = $rand.exp;}
    | 'waitcnt' paramlist {$statement = new Wait($paramlist.exps[0], currentMethod);}
    | 'return' expression? {$statement = new Return($expression.exp, currentMethod);}
    |  o=('--'|'++') id {$statement = new SpinExpression($id.exp, $o.getText(), currentMethod);}
    |  methodCall {$statement = $methodCall.mc;} 
    //The methodcall with no arguments
    |  ID {if (program.isMethod($ID.getText())){
              statement = new MethodCall($ID.getText(),null, program,currentMethod);
          }else{
              throw new SyntaxError("No method found: "+$ID.getText());}}
    //else throw exception
    | s=set_bits_high           {$statement = (Statement)$s.exp;}
    | s2=set_bits_low           {$statement = (Statement)$s2.exp;}
    | objMethodCall             {$statement = $objMethodCall.mc;} 
    | r6=memmove                {$statement = $r6.statement;}
    | r7=memfill                {$statement = $r7.statement;}
    | uu=unary_statement        
     {for(SpinExpression exp: $uu.pair.cdr()){
        exp.setEquals(true); 
      }
      $statement = $uu.pair.car();}
    | ns=not_statement        
     {for(SpinExpression exp: $ns.pair.cdr()){
        exp.setEquals(true); 
      }
      $statement = $ns.pair.car();}
    | '-' ii=id                 {$statement = new SpinExpression($ii.exp,"-", currentMethod, true);}

    ;
    
block_statement returns [Statement statement] throws Exception
    : b=if_statement            {$statement = $b.statement;}
    | c=case_statement          {$statement = $c.statement;}
    | r1=repeat                 {$statement = $r1.statement;}
    | r2=repeatconstanttimes    {$statement = $r2.statement;}
    | r3=repeatwhile            {$statement = $r3.statement;}
    | r4=repeatuntil            {$statement = $r4.statement;}
    | r5=repeatvariablefrom     {$statement = $r5.statement;}
    ;
   
unary_statement returns [Pair<SpinExpression, ArrayList<SpinExpression>> pair] throws Exception
    @init{SpinExpression exp; 
          ArrayList<SpinExpression> list = new ArrayList<SpinExpression>();}
    : ^(UNARYOP e=expression u=Unary2Op
      {exp = new SpinExpression(e,$u.getText(),currentMethod); list.add(exp);}
      (u2=Unary2Op {exp = new SpinExpression(exp,$u2.getText(),currentMethod);
      list.add(exp);})*) 
      {pair = new Pair<SpinExpression, ArrayList<SpinExpression>>(exp,list);}
    ;
    
not_statement returns [Pair<SpinExpression, ArrayList<SpinExpression>> pair] throws Exception
    @init{SpinExpression exp; 
          ArrayList<SpinExpression> list = new ArrayList<SpinExpression>();}
    : ^(NOT_OP e=expression u=BoolNotOp
      {exp = new SpinExpression(e,$u.getText(),currentMethod); list.add(exp);}
      (u2=BoolNotOp {exp = new SpinExpression(exp,$u2.getText(),currentMethod);
      list.add(exp);})*) 
      {pair = new Pair<SpinExpression, ArrayList<SpinExpression>>(exp,list);}
    ;
    
set_bits_high returns [Expression exp] throws Exception
    : ^(SETHIGH id) 
    {exp = new SpinExpression($id.exp, "sethigh", currentMethod);}
    ;
    
set_bits_low returns [Expression exp] throws Exception
    : ^(SETLOW id) 
    {exp = new SpinExpression($id.exp, "setlow", currentMethod);}
    ;
    
memmove returns [Statement statement] throws Exception
    @init{VariableType type = VariableType.LONG;}
    : ('bytemove'{type = VariableType.BYTE;}
      |'wordmove'{type = VariableType.WORD;}
      |'longmove'{type = VariableType.LONG;})
      '('e1=expression','e2=expression','e3=expression')'
      {statement = new MemMove(e1,e2,e3,type,currentMethod);}
    ;
    
    
memfill returns [Statement statement] throws Exception
    @init{VariableType type = VariableType.LONG;}
    : ('bytefill'{type = VariableType.BYTE;}
      |'wordfill'{type = VariableType.WORD;}
      |'longfill'{type = VariableType.LONG;})
      '('e1=expression','e2=expression','e3=expression')'
     {statement = new MemFill(e1,e2,e3,type,currentMethod);}      
    ;
     

if_statement returns [IfElse statement] throws Exception
    :  'if'  r=expression 
          i=block?  {statement = new IfElse($r.exp, $i.block, currentMethod);} 
        ( 'elseif'  s=expression 
         ei=block? {statement.setExp($s.exp); statement.setBlock($ei.block);})*
        ( 'else' 
          e=block?)?{statement.setElseBlock($e.block);}
    ; 
    
case_statement returns [Case statement] throws Exception
    :   'case' expression {statement = new Case($expression.exp, currentMethod);} Indent
        (c=casechoice {statement.addCase($c.pair.car(), $c.pair.cdr());}    |   NEWLINE)*
        Dedent //NEWLINE*
    ;

casechoice returns [Pair<CaseItem, Block> pair]throws Exception
  @init{CaseItem item;}
    : e=expression (Range e2=expression)?  
      {if ($e2.exp == null){
          item = new CaseItem($e.exp);
      }else{
          item = new CaseItem(new Range($e.exp, $e2.exp));
      }}
      (','e= expression (Range e2=expression)?
      {if ($e2.exp == null){
          item.addItem($e.exp);
      }else{
          item.addItem(new Range($e.exp, $e2.exp));
      }})*':' block {pair = new Pair(item, $block.block);}
    ;
    
/*
  Repeat, RepeatBlockWhile, RepeatConstantTImes, RepeatUntil, RepeatVariableFrom
  RepeatWHile, should be a repeatBlockUntil
*/
repeatvariablefrom returns [RepeatVariableFrom statement] throws Exception
    : 'repeat' ID 'from' e1=expression 'to' e2 = expression ('step' e3=expression)? block?
       {if ($e3.exp == null){
          statement = new RepeatVariableFrom($e1.exp, $e2.exp, $ID.getText(), $block.block, currentMethod);
       }else{
          statement = new RepeatVariableFrom($e1.exp, $e2.exp, $ID.getText(), $e3.exp, $block.block, currentMethod);
       }}
       
    ;


repeatconstanttimes returns [RepeatConstantTimes statement] throws Exception
    : 'repeat' e=expression block? 
      {statement = new RepeatConstantTimes($e.exp, currentMethod);
      statement.setBlock($block.block);}
    ;
    
repeatuntil returns [RepeatUntil statement] throws Exception
    : 'repeat' 'until' e=expression block?
       {statement = new RepeatUntil($e.exp, currentMethod);
       statement.setBlock($block.block);}
    ;

repeatwhile returns [RepeatWhile statement] throws Exception
    : 'repeat' 'while' e=expression block?
       {statement = new RepeatWhile($e.exp, currentMethod);
       statement.setBlock($block.block);}
    ;

repeat returns [Repeat statement] throws Exception
   
    : 'repeat' (block|NEWLINE) (NEWLINE('until' e1=expression|'while' e2=expression))?
      {if (($e1.exp == null)&&($e2.exp == null)){
          statement = new Repeat(currentMethod);
      }else if($e1.exp == null){
          statement = new RepeatBlockWhile($e2.exp, currentMethod);
      }else if($e2.exp ==null){
          statement = new RepeatBlockUntil($e1.exp, currentMethod);
      }else{ throw new SyntaxError("Using while and until concurrently");}
      statement.setBlock($block.block);}
    ;
  
    
array returns [Expression exp] throws Exception
    : '['expression']' {exp = $expression.exp;}
    ;
    
/*
 IncOp: '++'|'--';
 AddOp: '+';
 AddOp2: '-';
 UnaryOp: '?'|'@';
 Unary2Op: AddOp|AddOp2 | '^^'| '||'| '|<'| '>|'| '!';
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
 */ 
   
expression returns [Expression exp] throws Exception
    : ^(POST IncOp e=expression) {exp = new SpinExpression(e, "post"+$IncOp.getText(), currentMethod);}
    | ^(PRE IncOp e=expression) {exp = new SpinExpression(e, "pre"+$IncOp.getText(), currentMethod);}
    | ^(NEGATION e=expression) {exp = new Negation(e, currentMethod);}
    //all unary operators
    | u=unary_statement           {exp = $u.pair.car();}
    | v=not_statement             {exp = $v.pair.car();}
    | ^(o=(Unary2Op) e=expression) {exp = new SpinExpression(e, o.getText(), currentMethod);}    
    //all binary operators
    | ^(o=('+'|'-'|ShiftOp|BitAndOp|Multiply|MultOp|LimitMaxOp|BoolOp|BoolAndOp|BoolOrOp)
      e1=expression e2=expression) {exp = new SpinExpression(e1, e2, o.getText(), currentMethod);} 
    | ^(Assign e3=expression e4=expression) 
     { if ($e3.exp instanceof MethodCall) throw new SyntaxError("expected variable");
      if ($Assign.getText().equals(":=")){
        exp = new Assignment($e3.exp, e4, currentMethod); 
      }else{
        exp = new SpinExpression($e3.exp, e4, $Assign.getText(), currentMethod);
      }}
    | n=number 
      {if ($n.value.isfloat){
        exp = new AnonVariable($n.value.getValue(),true);
      }else{
        exp = new AnonVariable($n.value.getValue());
      }}
    | i=ident {exp =i;}
   // | bitnot  {exp = $bitnot.exp;}
    ;
     
setbits returns [Expression exp] throws Exception
    : id {exp = $id.exp;} 
      o=('~'{exp = new SpinExpression(exp, "setlow", currentMethod);}
    |    '~~'{exp = new SpinExpression(exp, "sethigh", currentMethod);}
    |    '?'{exp = new RandomExpression($id.exp,currentMethod);})?     
    ;
 
//a collection of things that can be expressions or part of expressions
ident returns [Expression exp] throws Exception
    : True            {exp = Variable.TRUE;}
    | False           {exp = Variable.FALSE;}
    | e1=objectConstant{exp = e1; }
    | e2=objMethodCall {exp = e2; }
    | e3=methodCall    {exp = e3; }
    | e4=string        {exp = e4; }
    | e5=strcomp       {exp = e5; } 
    | e6=character     {exp = e6; }
    | e7=strsize       {exp = e7; }
    | e8=lookup        {exp = e8; }
    | e9=lookdown      {exp = e9; }
    | e11=rand         {exp = e11;}
    | e12=setbits      {exp = e12;}
    | e13=assignment   {exp = e13;}
    //| e14=postincrement{exp = e14;}
   // | ^(ASSIGNMENT Assign ID array? expression)
    //There is also the possibility that this is a method call with no parameters
    //ie a single ID
    | a=('~'|'~~'|UnaryOp) sb=setbits     
      {if ($a.getText().equals("~")){
          Debug.debug("its a sign extend");
          //code for '~' in SpinTokenizer
          exp = new SignExtend($sb.exp,-59);
       }else if ($a.getText().equals("~~")){
          Debug.debug("its a sign extend");
          //code for '~~' in SpinTokenizer
          exp = new SignExtend($sb.exp,-60);
       }else if ($a.getText().equals("@")){
          Debug.debug("its an address");
          exp = new Address($sb.exp, program.getMemory());
       }
          
      }
    ; 
    
id returns [Expression exp] throws Exception
    : ID array?
    {if (program.isVariable($ID.getText())){
          //check if its an array access or not
          if ($array.exp != null){
              Debug.debug("its an array");
              exp = new ArrayAccess(program.getVariable($ID.getText()), $array.exp, program.getMemory());
          }else{
              Debug.debug("its not an array");
              exp = program.getVariable($ID.getText());
          }
          //now check if there is a sign extend
          
       }   
       else if (program.isConstant($ID.getText())){exp = program.getConstant($ID.getText());}
       else if (currentMethod.isVariable($ID.getText())){
          //check for an array access
          if ($array.exp != null){
              exp = new ArrayAccess($ID.getText(), $array.exp);
          }else{
              exp = new VariableExpression($ID.getText());
          }
          
      }else if (program.isMethod($ID.getText())){exp = new MethodCall($ID.getText(),null, program,currentMethod);}
      } 
    ;
    
assignment returns [Expression exp] throws Exception
    : ^(ASSIGNMENT Assign d=id e=expression)
   // | ^(Assign d= expression e=expression )
      { if ($d.exp instanceof MethodCall) throw new SyntaxError("expected variable");
      if ($Assign.getText().equals(":=")){
        exp = new Assignment($d.exp, e, currentMethod); 
      }else{
        exp = new SpinExpression($d.exp, e, $Assign.getText(), currentMethod);
      }}
    ;
    
postincrement returns [Expression exp] throws Exception
    : ^(POSTINCREMENT IncOp id)
      {exp = new SpinExpression($id.exp, "post"+$IncOp.getText(), currentMethod); }
    ;
  
rand returns [RandomExpression exp] throws Exception
    @init{Expression ex = null;}
    : //'?' id 
      ^(RAND id)
      {if ($id.exp instanceof MethodCall){throw new SyntaxError("Cannot call ? on method");}
       exp = new RandomExpression($id.exp,currentMethod);}
    ;
  
    
lookup returns [Expression exp] throws Exception
    : s=('lookup'|'lookupz') '('e=expression':' r=rangelist')'
      {boolean z = $s.getText().equals("lookupz");
       exp = new LookUp(e,r, z);}
    ;
    
lookdown returns [Expression exp] throws Exception
    : s=('lookdown'|'lookdownz') '('e=expression':' r=rangelist')'
      {boolean z = $s.getText().equals("lookdownz");
       exp = new LookDown(e,r, z);}
    ;
    
rangelist returns [SpinList list] throws Exception
      @init{list = new SpinList();}
    : r1=rangeitem {list.addItem(r1);}
      (','r2=rangeitem {list.addItem(r2);})*
    ;
    
rangeitem returns [SpinListItem item] throws Exception
    : e1=expression ('..'e2=expression)? 
      {if ($e2.exp == null){
        item = new ListItem($e1.exp);
      }else{
        item = new Range($e1.exp, $e2.exp);
      }}
    ;

character returns [Expression exp] throws Exception
    : c=CHARACTER {exp = new AnonVariable((byte)$c.getText().charAt(1));}
    ;
strcomp returns [Expression exp] throws Exception
    : 'strcomp''('e1=expression ',' e2=expression')'
      {$exp = new StrComp($e1.exp,$e2.exp,program.getMemory());}
    ;  

strsize returns [Expression exp] throws Exception
    : 'strsize''('e=expression')'
      {$exp = new StrSize($e.exp, program.getMemory());}
    ;
   
paramlist returns [Expression[\] exps] throws Exception
    :   '(' l=list ')' {exps = l;}
    ;
            
list returns [Expression[\] exps] throws Exception
@init{ArrayList<Expression> explist = new ArrayList<Expression>();}
    :   (e=expression {explist.add(e);})*
        {exps = new Expression[explist.size()];
        for (int i = 0; i< explist.size(); i ++){
            exps[i] = explist.get(i);
        }}
    ; 
    
methodCall returns [MethodCall mc] throws Exception
    : ID p=paramlist 
      {if (!program.isMethod($ID.getText())){
          throw new SyntaxError("Method "+$ID.getText()+" does not exist");
      }else{
          mc = new MethodCall($ID.getText(), p, program, currentMethod);
      }}
    ;
    
objMethodCall returns [MethodCall mc] throws Exception
    : o=ID '.' meth=ID p=paramlist?
      {Program prog = program.getObject(o.getText());
      mc = new MethodCall(meth.getText(),p,prog,currentMethod);
      }
    ;
 
objectConstant returns [Constant c] throws Exception
    :o=ID '#' con=ID
     {c = program.getObject(o.getText()).getConstant(con.getText());}
    ;
     
string returns [SpinStringWrapper str] throws Exception
@init{int memoryNeeded; String stringId;}
    : 'string' '(' s=(STRING|CHARACTER) ')'
      {memoryNeeded = s.getText().length()-1;
      stringId = SpinString.getCurrentId();
      str = new SpinStringWrapper(stringId);
      currentMethod.addExtraMemory(memoryNeeded);
      currentMethod.addLocalString(stringId, s.getText().substring(1,s.getText().length()-1));
      Debug.debug("current method for the string: "+s.getText().substring(1,s.getText().length()-1));
      Debug.debug(" "+currentMethod.getName());}
    ;


number returns [SpinNumber value] throws Exception
    :INT
    {StringBuffer sb = new StringBuffer($INT.getText());
      int index = sb.indexOf("_");
      while(index!= -1){
        sb.deleteCharAt(index);
        index = sb.indexOf("_");
      }
      String num = sb.toString();
      value = new SpinNumber((int)(Long.parseLong(num)\%(long)Math.pow(2,32)));
     }
    
    |f=FLOAT
    {StringBuffer sb = new StringBuffer($FLOAT.getText());
      int index = sb.indexOf("_");
      while(index!= -1){
        sb.deleteCharAt(index);
        index = sb.indexOf("_");
      }
      String num = sb.toString();
    
      value =  new SpinNumber(Float.parseFloat(num));
      }
    | b=BINARY
     {StringBuffer sb = new StringBuffer($b.getText());
      int index = sb.indexOf("_");
      while(index!= -1){
        sb.deleteCharAt(index);
        index = sb.indexOf("_");
      }
      String num = sb.toString();
    
      value =  new SpinNumber(Program.binaryToInt(num));
      }
    | q= QUATERNARY
     {StringBuffer sb = new StringBuffer($q.getText());
      int index = sb.indexOf("_");
      while(index!= -1){
        sb.deleteCharAt(index);
        index = sb.indexOf("_");
      }
      String num = sb.toString();
    
      value =  new SpinNumber(Program.quaternaryToInt(num));
      }
    | h=HEXADECIMAL
     {StringBuffer sb = new StringBuffer($h.getText());
      int index = sb.indexOf("_");
      while(index!= -1){
        sb.deleteCharAt(index);
        index = sb.indexOf("_");
      }
      String num = sb.toString();
    
      value =  new SpinNumber(Program.hexToInt(num));
      }
    ;

  