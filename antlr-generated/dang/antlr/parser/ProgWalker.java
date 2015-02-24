// $ANTLR 3.4 C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g 2012-09-05 23:18:53

package dang.antlr.parser;
import dang.robot.Robot;
import dang.program.Program;
import dang.program.Variable; 
import dang.program.ArrayAccess;
import dang.program.Variable.VariableType;
import dang.program.Method;
import dang.exceptions.compiler.CompilerError;
import dang.program.statements.Statement;
import dang.program.Block;
import dang.program.Range;
import dang.program.CaseItem;
import dang.program.expressions.Expression;
import dang.program.expressions.VariableExpression;
import dang.exceptions.compiler.SyntaxError;
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



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
 
@SuppressWarnings({"all", "warnings", "unchecked"})
public class ProgWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGNMENT", "Assign", "BINARYOP", "BitAndOp", "BitOrOp", "BoolAndOp", "BoolNotOp", "BoolOp", "BoolOrOp", "CHAR", "CHARACTER", "Dedent", "ESC_SEQ", "EXPONENT", "FLOAT", "False", "HEX_DIGIT", "HexDigit", "HexLiteral", "ID", "INT", "IncOp", "Indent", "LimitMaxOp", "Methodheader", "MultOp", "MultiLineComment", "Multiply", "NEGATION", "NEWLINE", "OCTAL_ESC", "POST", "POSTINCREMENT", "PRE", "RANGE", "RANGE_OR_INT", "Range", "STRING", "ShiftOp", "SingleLineComment", "True", "UNICODE_ESC", "Unary2Op", "UnaryOp", "WS", "'#'", "'('", "')'", "'+'", "','", "'-'", "'.'", "':'", "'='", "'?'", "'['", "']'", "'byte'", "'bytefill'", "'bytemove'", "'case'", "'con'", "'else'", "'elseif'", "'from'", "'if'", "'long'", "'longfill'", "'longmove'", "'lookdown'", "'lookdownz'", "'lookup'", "'lookupz'", "'obj'", "'repeat'", "'return'", "'step'", "'strcomp'", "'string'", "'strsize'", "'to'", "'until'", "'var'", "'waitcnt'", "'while'", "'word'", "'wordfill'", "'wordmove'", "'|'", "'||'", "GlobalVariable", "LocalVariable", "Method", "SpinObject", "'++'", "'--'"
    };

    public static final int EOF=-1;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int ASSIGNMENT=4;
    public static final int Assign=5;
    public static final int BINARYOP=6;
    public static final int BitAndOp=7;
    public static final int BitOrOp=8;
    public static final int BoolAndOp=9;
    public static final int BoolNotOp=10;
    public static final int BoolOp=11;
    public static final int BoolOrOp=12;
    public static final int CHAR=13;
    public static final int CHARACTER=14;
    public static final int Dedent=15;
    public static final int ESC_SEQ=16;
    public static final int EXPONENT=17;
    public static final int FLOAT=18;
    public static final int False=19;
    public static final int HEX_DIGIT=20;
    public static final int HexDigit=21;
    public static final int HexLiteral=22;
    public static final int ID=23;
    public static final int INT=24;
    public static final int IncOp=25;
    public static final int Indent=26;
    public static final int LimitMaxOp=27;
    public static final int Methodheader=28;
    public static final int MultOp=29;
    public static final int MultiLineComment=30;
    public static final int Multiply=31;
    public static final int NEGATION=32;
    public static final int NEWLINE=33;
    public static final int OCTAL_ESC=34;
    public static final int POST=35;
    public static final int POSTINCREMENT=36;
    public static final int PRE=37;
    public static final int RANGE=38;
    public static final int RANGE_OR_INT=39;
    public static final int Range=40;
    public static final int STRING=41;
    public static final int ShiftOp=42;
    public static final int SingleLineComment=43;
    public static final int True=44;
    public static final int UNICODE_ESC=45;
    public static final int Unary2Op=46;
    public static final int UnaryOp=47;
    public static final int WS=48;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int GlobalVariable=94;
    public static final int LocalVariable=95;
    public static final int Method=96;
    public static final int SpinObject=97;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public ProgWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public ProgWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return ProgWalker.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g"; }


      Robot robot;
      Program program;
      Method currentMethod;



    // $ANTLR start "prog"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:92:1: prog[Program prog] returns [Program program] : ( conblock | objblock | varblock | method )* ;
    public final Program prog(Program prog) throws Exception, RecognitionException {
        Program program = null;



            //the peculiarities of ANTLR necessitate a lot of sharing. Great program though
            robot = prog.getRobot();
            this.program = prog;
            program = prog;
            
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:4: ( ( conblock | objblock | varblock | method )* )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:7: ( conblock | objblock | varblock | method )*
            {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:7: ( conblock | objblock | varblock | method )*
            loop1:
            do {
                int alt1=5;
                switch ( input.LA(1) ) {
                case 65:
                    {
                    alt1=1;
                    }
                    break;
                case 77:
                    {
                    alt1=2;
                    }
                    break;
                case 86:
                    {
                    alt1=3;
                    }
                    break;
                case Methodheader:
                    {
                    alt1=4;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:8: conblock
            	    {
            	    pushFollow(FOLLOW_conblock_in_prog118);
            	    conblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:17: objblock
            	    {
            	    pushFollow(FOLLOW_objblock_in_prog120);
            	    objblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:26: varblock
            	    {
            	    pushFollow(FOLLOW_varblock_in_prog122);
            	    varblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:100:35: method
            	    {
            	    pushFollow(FOLLOW_method_in_prog124);
            	    method();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return program;
    }
    // $ANTLR end "prog"



    // $ANTLR start "conblock"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:104:1: conblock : ^( 'con' ( NEWLINE )+ ( constatement )* ) ;
    public final void conblock() throws Exception, RecognitionException {
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:5: ( ^( 'con' ( NEWLINE )+ ( constatement )* ) )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:7: ^( 'con' ( NEWLINE )+ ( constatement )* )
            {
            match(input,65,FOLLOW_65_in_conblock157); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:15: ( NEWLINE )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==NEWLINE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:15: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_conblock159); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:24: ( constatement )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:105:24: constatement
            	    {
            	    pushFollow(FOLLOW_constatement_in_conblock162);
            	    constatement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input, Token.UP, null); 


            Debug.debug("Conblock");

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "conblock"



    // $ANTLR start "constatement"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:108:1: constatement : i= ID '=' e= expression ;
    public final void constatement() throws Exception, RecognitionException {
        CommonTree i=null;
        Expression e =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:109:5: (i= ID '=' e= expression )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:109:8: i= ID '=' e= expression
            {
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_constatement189); 

            match(input,57,FOLLOW_57_in_constatement191); 

            pushFollow(FOLLOW_expression_in_constatement195);
            e=expression();

            state._fsp--;


            Debug.debug("Constatement: ");
                   Debug.debug("constant: "+ i.getText() + " Expression: "+e);
                   program.addConstant(i.getText(), e);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "constatement"



    // $ANTLR start "varblock"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:115:1: varblock : ^( 'var' ( vartype ( ID ( array )? )+ )* ) ;
    public final void varblock() throws Exception, RecognitionException {
        CommonTree ID3=null;
        VariableType vartype1 =null;

        Expression array2 =null;


        Expression ar = null;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:124:5: ( ^( 'var' ( vartype ( ID ( array )? )+ )* ) )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:124:8: ^( 'var' ( vartype ( ID ( array )? )+ )* )
            {
            match(input,86,FOLLOW_86_in_varblock236); 

            Debug.debug("Varblock");

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:9: ( vartype ( ID ( array )? )+ )*
                loop6:
                do {
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==61||LA6_0==70||LA6_0==89) ) {
                        alt6=1;
                    }


                    switch (alt6) {
                	case 1 :
                	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:10: vartype ( ID ( array )? )+
                	    {
                	    pushFollow(FOLLOW_vartype_in_varblock249);
                	    vartype1=vartype();

                	    state._fsp--;


                	    Debug.debug("Vartype: "+ vartype1);

                	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:61: ( ID ( array )? )+
                	    int cnt5=0;
                	    loop5:
                	    do {
                	        int alt5=2;
                	        int LA5_0 = input.LA(1);

                	        if ( (LA5_0==ID) ) {
                	            alt5=1;
                	        }


                	        switch (alt5) {
                	    	case 1 :
                	    	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:62: ID ( array )?
                	    	    {
                	    	    ID3=(CommonTree)match(input,ID,FOLLOW_ID_in_varblock253); 

                	    	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:65: ( array )?
                	    	    int alt4=2;
                	    	    int LA4_0 = input.LA(1);

                	    	    if ( (LA4_0==59) ) {
                	    	        alt4=1;
                	    	    }
                	    	    switch (alt4) {
                	    	        case 1 :
                	    	            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:125:66: array
                	    	            {
                	    	            pushFollow(FOLLOW_array_in_varblock256);
                	    	            array2=array();

                	    	            state._fsp--;


                	    	            ar = array2;

                	    	            }
                	    	            break;

                	    	    }


                	    	    if(ar != null){
                	    	                program.addGlobalVariable(ID3.getText(),vartype1,ar);
                	    	                ar = null;
                	    	            }else{
                	    	                program.addGlobalVariable(ID3.getText(),vartype1);
                	    	            }

                	    	    }
                	    	    break;

                	    	default :
                	    	    if ( cnt5 >= 1 ) break loop5;
                	                EarlyExitException eee =
                	                    new EarlyExitException(5, input);
                	                throw eee;
                	        }
                	        cnt5++;
                	    } while (true);


                	    }
                	    break;

                	default :
                	    break loop6;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "varblock"



    // $ANTLR start "vartype"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:135:1: vartype returns [VariableType mtype] : ( 'byte' | 'word' | 'long' );
    public final VariableType vartype() throws RecognitionException {
        VariableType mtype = null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:136:5: ( 'byte' | 'word' | 'long' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 61:
                {
                alt7=1;
                }
                break;
            case 89:
                {
                alt7=2;
                }
                break;
            case 70:
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:136:8: 'byte'
                    {
                    match(input,61,FOLLOW_61_in_vartype307); 

                    mtype = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:137:8: 'word'
                    {
                    match(input,89,FOLLOW_89_in_vartype318); 

                    mtype = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:138:8: 'long'
                    {
                    match(input,70,FOLLOW_70_in_vartype329); 

                    mtype = VariableType.LONG;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return mtype;
    }
    // $ANTLR end "vartype"



    // $ANTLR start "objblock"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:141:1: objblock : ^( 'obj' (i= ID s= STRING )* ) ;
    public final void objblock() throws Exception, RecognitionException {
        CommonTree i=null;
        CommonTree s=null;

        String n;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:143:5: ( ^( 'obj' (i= ID s= STRING )* ) )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:143:7: ^( 'obj' (i= ID s= STRING )* )
            {
            match(input,77,FOLLOW_77_in_objblock361); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:143:15: (i= ID s= STRING )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==ID) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:143:16: i= ID s= STRING
                	    {
                	    i=(CommonTree)match(input,ID,FOLLOW_ID_in_objblock366); 

                	    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_objblock370); 

                	    n=(s.getText().substring(1,s.getText().length()-1));
                	          System.out.println("Object: "+i.getText());
                	          System.out.println("Objectfile: "+n);
                	          if (n.equals("servocontrol")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new ServoControl(program));
                	            }else if (n.equals("blocksensor")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new BlockSensor(program));
                	            }else if (n.equals("floatstring")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new FloatString(program));
                	            }else if (n.equals("float32full")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Float32Full(program));
                	            }else if (n.equals("beeper")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Beeper(program));
                	            }else if (n.equals("dirrssensor")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new DirrsSensor(program));
                	            }else if (n.equals("pingsensor")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Sonar(program));
                	            }else if (n.equals("cmucam")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new CMUCam(program));
                	            }else if (n.equals("debugger")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Debugger(program));
                	            }else if (n.equals("rbc")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new RBC(program));
                	            }else if (n.equals("ir8sensorarray")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Ir8SensorArray(program));
                	            }else if (n.equals("encoders")){
                	              ADebug.debug("Adding object: "+i.getText());
                	              program.addObject(i.getText(), new Encoders(program));
                	            }else{
                	              //check the working directory for a matching file name
                	              //for now throw an exception
                	              throw new SyntaxError("unknown object: "+ n);
                	            }

                	    }
                	    break;

                	default :
                	    break loop8;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "objblock"



    // $ANTLR start "method"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:190:1: method : Methodheader i= ID ( block )? ;
    public final void method() throws Exception, RecognitionException {
        CommonTree i=null;
        Block block4 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:191:5: ( Methodheader i= ID ( block )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:191:7: Methodheader i= ID ( block )?
            {
            match(input,Methodheader,FOLLOW_Methodheader_in_method407); 

            i=(CommonTree)match(input,ID,FOLLOW_ID_in_method411); 

            if (program.isMethod(i.getText())){
                    currentMethod = program.getMethod(i.getText());
                   }else{
                    throw new CompilerError("There is no method " + i.getText());
                   }
                   ADebug.debug("Read in method header: "+ i.getText());

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:198:7: ( block )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==Indent) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:198:7: block
                    {
                    pushFollow(FOLLOW_block_in_method428);
                    block4=block();

                    state._fsp--;


                    }
                    break;

            }


            currentMethod.setBlock(block4);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "method"



    // $ANTLR start "block"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:218:1: block returns [Block block] : Indent ( NEWLINE |s= statement NEWLINE )* Dedent ;
    public final Block block() throws Exception, RecognitionException {
        Block block = null;


        Statement s =null;


         block = new Block(currentMethod);
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:220:5: ( Indent ( NEWLINE |s= statement NEWLINE )* Dedent )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:220:9: Indent ( NEWLINE |s= statement NEWLINE )* Dedent
            {
            match(input,Indent,FOLLOW_Indent_in_block473); 

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:221:8: ( NEWLINE |s= statement NEWLINE )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==NEWLINE) ) {
                    alt10=1;
                }
                else if ( (LA10_0==ASSIGNMENT||LA10_0==ID||LA10_0==POSTINCREMENT||LA10_0==58||(LA10_0 >= 62 && LA10_0 <= 64)||LA10_0==69||(LA10_0 >= 71 && LA10_0 <= 72)||(LA10_0 >= 78 && LA10_0 <= 79)||LA10_0==87||(LA10_0 >= 90 && LA10_0 <= 91)||LA10_0==93||(LA10_0 >= 98 && LA10_0 <= 99)) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:221:9: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_block483); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:221:19: s= statement NEWLINE
            	    {
            	    pushFollow(FOLLOW_statement_in_block489);
            	    s=statement();

            	    state._fsp--;


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_block491); 

            	    block.addStatement(s);
            	            ADebug.debug("Block adding statement: "+ s);

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match(input,Dedent,FOLLOW_Dedent_in_block505); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return block;
    }
    // $ANTLR end "block"



    // $ANTLR start "statement"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:226:1: statement returns [Statement statement] : (a= assignment |p= postincrement | rand | 'waitcnt' paramlist | 'return' ( expression )? |o= ( '--' | '++' ) id |b= if_statement | methodCall | ID | objMethodCall |c= case_statement |r1= repeat |r2= repeatconstanttimes |r3= repeatwhile |r4= repeatuntil |r5= repeatvariablefrom |r6= memmove |r7= memfill |r8= abs );
    public final Statement statement() throws Exception, RecognitionException {
        Statement statement = null;


        CommonTree o=null;
        CommonTree ID10=null;
        Expression a =null;

        Expression p =null;

        IfElse b =null;

        Case c =null;

        Repeat r1 =null;

        RepeatConstantTimes r2 =null;

        RepeatWhile r3 =null;

        RepeatUntil r4 =null;

        RepeatVariableFrom r5 =null;

        Statement r6 =null;

        Statement r7 =null;

        SpinExpression r8 =null;

        RandomExpression rand5 =null;

        Expression[] paramlist6 =null;

        Expression expression7 =null;

        Expression id8 =null;

        MethodCall methodCall9 =null;

        MethodCall objMethodCall11 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:227:5: (a= assignment |p= postincrement | rand | 'waitcnt' paramlist | 'return' ( expression )? |o= ( '--' | '++' ) id |b= if_statement | methodCall | ID | objMethodCall |c= case_statement |r1= repeat |r2= repeatconstanttimes |r3= repeatwhile |r4= repeatuntil |r5= repeatvariablefrom |r6= memmove |r7= memfill |r8= abs )
            int alt12=19;
            switch ( input.LA(1) ) {
            case ASSIGNMENT:
                {
                alt12=1;
                }
                break;
            case POSTINCREMENT:
                {
                alt12=2;
                }
                break;
            case 58:
                {
                alt12=3;
                }
                break;
            case 87:
                {
                alt12=4;
                }
                break;
            case 79:
                {
                alt12=5;
                }
                break;
            case 98:
            case 99:
                {
                alt12=6;
                }
                break;
            case 69:
                {
                alt12=7;
                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case 55:
                    {
                    alt12=10;
                    }
                    break;
                case 50:
                    {
                    alt12=8;
                    }
                    break;
                case NEWLINE:
                    {
                    alt12=9;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 8, input);

                    throw nvae;

                }

                }
                break;
            case 64:
                {
                alt12=11;
                }
                break;
            case 78:
                {
                switch ( input.LA(2) ) {
                case 88:
                    {
                    alt12=14;
                    }
                    break;
                case 85:
                    {
                    alt12=15;
                    }
                    break;
                case ID:
                    {
                    int LA12_19 = input.LA(3);

                    if ( (LA12_19==68) ) {
                        alt12=16;
                    }
                    else if ( (LA12_19==Indent||LA12_19==NEWLINE||(LA12_19 >= 49 && LA12_19 <= 50)||LA12_19==55||LA12_19==59) ) {
                        alt12=13;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 19, input);

                        throw nvae;

                    }
                    }
                    break;
                case Indent:
                case NEWLINE:
                    {
                    alt12=12;
                    }
                    break;
                case ASSIGNMENT:
                case Assign:
                case BitAndOp:
                case BoolAndOp:
                case BoolNotOp:
                case BoolOp:
                case BoolOrOp:
                case CHARACTER:
                case FLOAT:
                case False:
                case INT:
                case LimitMaxOp:
                case MultOp:
                case Multiply:
                case NEGATION:
                case POST:
                case POSTINCREMENT:
                case PRE:
                case ShiftOp:
                case True:
                case Unary2Op:
                case UnaryOp:
                case 52:
                case 54:
                case 58:
                case 73:
                case 74:
                case 75:
                case 76:
                case 81:
                case 82:
                case 83:
                case 93:
                    {
                    alt12=13;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 10, input);

                    throw nvae;

                }

                }
                break;
            case 63:
            case 72:
            case 91:
                {
                alt12=17;
                }
                break;
            case 62:
            case 71:
            case 90:
                {
                alt12=18;
                }
                break;
            case 93:
                {
                alt12=19;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:227:7: a= assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement537);
                    a=assignment();

                    state._fsp--;


                    if (a instanceof Assignment){
                            statement = (Assignment)a;
                         }else{ 
                            statement = (SpinExpression)a;
                         }

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:233:7: p= postincrement
                    {
                    pushFollow(FOLLOW_postincrement_in_statement562);
                    p=postincrement();

                    state._fsp--;


                    statement = (SpinExpression)p;

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:234:7: rand
                    {
                    pushFollow(FOLLOW_rand_in_statement576);
                    rand5=rand();

                    state._fsp--;


                    statement = rand5;

                    }
                    break;
                case 4 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:235:7: 'waitcnt' paramlist
                    {
                    match(input,87,FOLLOW_87_in_statement601); 

                    pushFollow(FOLLOW_paramlist_in_statement603);
                    paramlist6=paramlist();

                    state._fsp--;


                    statement = new Wait(paramlist6[0], currentMethod);

                    }
                    break;
                case 5 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:236:7: 'return' ( expression )?
                    {
                    match(input,79,FOLLOW_79_in_statement613); 

                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:236:16: ( expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= ASSIGNMENT && LA11_0 <= Assign)||LA11_0==BitAndOp||(LA11_0 >= BoolAndOp && LA11_0 <= BoolOrOp)||LA11_0==CHARACTER||(LA11_0 >= FLOAT && LA11_0 <= False)||(LA11_0 >= ID && LA11_0 <= INT)||LA11_0==LimitMaxOp||LA11_0==MultOp||(LA11_0 >= Multiply && LA11_0 <= NEGATION)||(LA11_0 >= POST && LA11_0 <= PRE)||LA11_0==ShiftOp||LA11_0==True||(LA11_0 >= Unary2Op && LA11_0 <= UnaryOp)||LA11_0==52||LA11_0==54||LA11_0==58||(LA11_0 >= 73 && LA11_0 <= 76)||(LA11_0 >= 81 && LA11_0 <= 83)||LA11_0==93) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:236:16: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement615);
                            expression7=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    statement = new Return(expression7, currentMethod);

                    }
                    break;
                case 6 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:237:8: o= ( '--' | '++' ) id
                    {
                    o=(CommonTree)input.LT(1);

                    if ( (input.LA(1) >= 98 && input.LA(1) <= 99) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_id_in_statement635);
                    id8=id();

                    state._fsp--;


                    statement = new SpinExpression(id8, o.getText(), currentMethod);

                    }
                    break;
                case 7 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:238:8: b= if_statement
                    {
                    pushFollow(FOLLOW_if_statement_in_statement648);
                    b=if_statement();

                    state._fsp--;


                    statement = b;

                    }
                    break;
                case 8 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:239:8: methodCall
                    {
                    pushFollow(FOLLOW_methodCall_in_statement659);
                    methodCall9=methodCall();

                    state._fsp--;


                    statement = methodCall9;

                    }
                    break;
                case 9 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:241:8: ID
                    {
                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_statement676); 

                    if (program.isMethod(ID10.getText())){statement = new MethodCall(ID10.getText(),null, program,currentMethod);}

                    }
                    break;
                case 10 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:243:7: objMethodCall
                    {
                    pushFollow(FOLLOW_objMethodCall_in_statement691);
                    objMethodCall11=objMethodCall();

                    state._fsp--;


                    statement = objMethodCall11;

                    }
                    break;
                case 11 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:244:7: c= case_statement
                    {
                    pushFollow(FOLLOW_case_statement_in_statement716);
                    c=case_statement();

                    state._fsp--;


                    statement = c;

                    }
                    break;
                case 12 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:245:7: r1= repeat
                    {
                    pushFollow(FOLLOW_repeat_in_statement737);
                    r1=repeat();

                    state._fsp--;


                    statement = r1;

                    }
                    break;
                case 13 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:246:7: r2= repeatconstanttimes
                    {
                    pushFollow(FOLLOW_repeatconstanttimes_in_statement765);
                    r2=repeatconstanttimes();

                    state._fsp--;


                    statement = r2;

                    }
                    break;
                case 14 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:247:7: r3= repeatwhile
                    {
                    pushFollow(FOLLOW_repeatwhile_in_statement780);
                    r3=repeatwhile();

                    state._fsp--;


                    statement = r3;

                    }
                    break;
                case 15 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:248:7: r4= repeatuntil
                    {
                    pushFollow(FOLLOW_repeatuntil_in_statement803);
                    r4=repeatuntil();

                    state._fsp--;


                    statement = r4;

                    }
                    break;
                case 16 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:249:7: r5= repeatvariablefrom
                    {
                    pushFollow(FOLLOW_repeatvariablefrom_in_statement826);
                    r5=repeatvariablefrom();

                    state._fsp--;


                    statement = r5;

                    }
                    break;
                case 17 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:250:7: r6= memmove
                    {
                    pushFollow(FOLLOW_memmove_in_statement842);
                    r6=memmove();

                    state._fsp--;


                    statement = r6;

                    }
                    break;
                case 18 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:251:7: r7= memfill
                    {
                    pushFollow(FOLLOW_memfill_in_statement869);
                    r7=memfill();

                    state._fsp--;


                    statement = r7;

                    }
                    break;
                case 19 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:252:7: r8= abs
                    {
                    pushFollow(FOLLOW_abs_in_statement896);
                    r8=abs();

                    state._fsp--;


                    statement = r8;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "statement"



    // $ANTLR start "memmove"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:261:1: memmove returns [Statement statement] : ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' ;
    public final Statement memmove() throws Exception, RecognitionException {
        Statement statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;


        VariableType type = VariableType.LONG;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:263:5: ( ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:263:7: ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')'
            {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:263:7: ( 'bytemove' | 'wordmove' | 'longmove' )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 63:
                {
                alt13=1;
                }
                break;
            case 91:
                {
                alt13=2;
                }
                break;
            case 72:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:263:8: 'bytemove'
                    {
                    match(input,63,FOLLOW_63_in_memmove965); 

                    type = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:264:8: 'wordmove'
                    {
                    match(input,91,FOLLOW_91_in_memmove975); 

                    type = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:265:8: 'longmove'
                    {
                    match(input,72,FOLLOW_72_in_memmove985); 

                    type = VariableType.LONG;

                    }
                    break;

            }


            match(input,50,FOLLOW_50_in_memmove995); 

            pushFollow(FOLLOW_expression_in_memmove998);
            e1=expression();

            state._fsp--;


            match(input,53,FOLLOW_53_in_memmove999); 

            pushFollow(FOLLOW_expression_in_memmove1002);
            e2=expression();

            state._fsp--;


            match(input,53,FOLLOW_53_in_memmove1003); 

            pushFollow(FOLLOW_expression_in_memmove1006);
            e3=expression();

            state._fsp--;


            match(input,51,FOLLOW_51_in_memmove1007); 

            statement = new MemMove(e1,e2,e3,type,currentMethod);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "memmove"



    // $ANTLR start "memfill"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:271:1: memfill returns [Statement statement] : ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' ;
    public final Statement memfill() throws Exception, RecognitionException {
        Statement statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;


        VariableType type = VariableType.LONG;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:273:5: ( ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:273:7: ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')'
            {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:273:7: ( 'bytefill' | 'wordfill' | 'longfill' )
            int alt14=3;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt14=1;
                }
                break;
            case 90:
                {
                alt14=2;
                }
                break;
            case 71:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:273:8: 'bytefill'
                    {
                    match(input,62,FOLLOW_62_in_memfill1058); 

                    type = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:274:8: 'wordfill'
                    {
                    match(input,90,FOLLOW_90_in_memfill1068); 

                    type = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:275:8: 'longfill'
                    {
                    match(input,71,FOLLOW_71_in_memfill1078); 

                    type = VariableType.LONG;

                    }
                    break;

            }


            match(input,50,FOLLOW_50_in_memfill1088); 

            pushFollow(FOLLOW_expression_in_memfill1091);
            e1=expression();

            state._fsp--;


            match(input,53,FOLLOW_53_in_memfill1092); 

            pushFollow(FOLLOW_expression_in_memfill1095);
            e2=expression();

            state._fsp--;


            match(input,53,FOLLOW_53_in_memfill1096); 

            pushFollow(FOLLOW_expression_in_memfill1099);
            e3=expression();

            state._fsp--;


            match(input,51,FOLLOW_51_in_memfill1100); 

            statement = new MemFill(e1,e2,e3,type,currentMethod);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "memfill"



    // $ANTLR start "if_statement"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:281:1: if_statement returns [IfElse statement] : 'if' r= expression (i= block )? ( NEWLINE 'elseif' s= expression (ei= block )? )* ( NEWLINE 'else' (e= block )? )? ;
    public final IfElse if_statement() throws Exception, RecognitionException {
        IfElse statement = null;


        Expression r =null;

        Block i =null;

        Expression s =null;

        Block ei =null;

        Block e =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:282:5: ( 'if' r= expression (i= block )? ( NEWLINE 'elseif' s= expression (ei= block )? )* ( NEWLINE 'else' (e= block )? )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:282:8: 'if' r= expression (i= block )? ( NEWLINE 'elseif' s= expression (ei= block )? )* ( NEWLINE 'else' (e= block )? )?
            {
            match(input,69,FOLLOW_69_in_if_statement1144); 

            pushFollow(FOLLOW_expression_in_if_statement1149);
            r=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:283:12: (i= block )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==Indent) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:283:12: i= block
                    {
                    pushFollow(FOLLOW_block_in_if_statement1164);
                    i=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new IfElse(r, i, currentMethod);

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:284:9: ( NEWLINE 'elseif' s= expression (ei= block )? )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==NEWLINE) ) {
                    int LA17_1 = input.LA(2);

                    if ( (LA17_1==67) ) {
                        alt17=1;
                    }


                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:284:10: NEWLINE 'elseif' s= expression (ei= block )?
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_if_statement1180); 

            	    match(input,67,FOLLOW_67_in_if_statement1182); 

            	    pushFollow(FOLLOW_expression_in_if_statement1187);
            	    s=expression();

            	    state._fsp--;


            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:285:12: (ei= block )?
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==Indent) ) {
            	        alt16=1;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:285:12: ei= block
            	            {
            	            pushFollow(FOLLOW_block_in_if_statement1201);
            	            ei=block();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    statement.setExp(s); statement.setBlock(ei);

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:286:9: ( NEWLINE 'else' (e= block )? )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NEWLINE) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==66) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:286:10: NEWLINE 'else' (e= block )?
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_if_statement1217); 

                    match(input,66,FOLLOW_66_in_if_statement1218); 

                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:287:12: (e= block )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==Indent) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:287:12: e= block
                            {
                            pushFollow(FOLLOW_block_in_if_statement1233);
                            e=block();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;

            }


            statement.setElseBlock(e);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "if_statement"



    // $ANTLR start "case_statement"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:290:1: case_statement returns [Case statement] : 'case' expression Indent (c= casechoice | NEWLINE )* Dedent ;
    public final Case case_statement() throws Exception, RecognitionException {
        Case statement = null;


        Pair<CaseItem, Block> c =null;

        Expression expression12 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:291:5: ( 'case' expression Indent (c= casechoice | NEWLINE )* Dedent )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:291:9: 'case' expression Indent (c= casechoice | NEWLINE )* Dedent
            {
            match(input,64,FOLLOW_64_in_case_statement1269); 

            pushFollow(FOLLOW_expression_in_case_statement1271);
            expression12=expression();

            state._fsp--;


            statement = new Case(expression12, currentMethod);

            match(input,Indent,FOLLOW_Indent_in_case_statement1275); 

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:292:9: (c= casechoice | NEWLINE )*
            loop20:
            do {
                int alt20=3;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0 >= ASSIGNMENT && LA20_0 <= Assign)||LA20_0==BitAndOp||(LA20_0 >= BoolAndOp && LA20_0 <= BoolOrOp)||LA20_0==CHARACTER||(LA20_0 >= FLOAT && LA20_0 <= False)||(LA20_0 >= ID && LA20_0 <= INT)||LA20_0==LimitMaxOp||LA20_0==MultOp||(LA20_0 >= Multiply && LA20_0 <= NEGATION)||(LA20_0 >= POST && LA20_0 <= PRE)||LA20_0==ShiftOp||LA20_0==True||(LA20_0 >= Unary2Op && LA20_0 <= UnaryOp)||LA20_0==52||LA20_0==54||LA20_0==58||(LA20_0 >= 73 && LA20_0 <= 76)||(LA20_0 >= 81 && LA20_0 <= 83)||LA20_0==93) ) {
                    alt20=1;
                }
                else if ( (LA20_0==NEWLINE) ) {
                    alt20=2;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:292:10: c= casechoice
            	    {
            	    pushFollow(FOLLOW_casechoice_in_case_statement1288);
            	    c=casechoice();

            	    state._fsp--;


            	    statement.addCase(c.car(), c.cdr());

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:292:81: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_case_statement1299); 

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            match(input,Dedent,FOLLOW_Dedent_in_case_statement1311); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "case_statement"



    // $ANTLR start "casechoice"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:296:1: casechoice returns [Pair<CaseItem, Block> pair] : e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block ;
    public final Pair<CaseItem, Block> casechoice() throws Exception, RecognitionException {
        Pair<CaseItem, Block> pair = null;


        Expression e =null;

        Expression e2 =null;

        Block block13 =null;


        CaseItem item;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:298:5: (e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:298:7: e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block
            {
            pushFollow(FOLLOW_expression_in_casechoice1344);
            e=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:298:20: ( Range e2= expression )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==Range) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:298:21: Range e2= expression
                    {
                    match(input,Range,FOLLOW_Range_in_casechoice1347); 

                    pushFollow(FOLLOW_expression_in_casechoice1351);
                    e2=expression();

                    state._fsp--;


                    }
                    break;

            }


            if (e2 == null){
                      item = new CaseItem(e);
                  }else{
                      item = new CaseItem(new Range(e, e2));
                  }

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:304:7: ( ',' e= expression ( Range e2= expression )? )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==53) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:304:8: ',' e= expression ( Range e2= expression )?
            	    {
            	    match(input,53,FOLLOW_53_in_casechoice1372); 

            	    pushFollow(FOLLOW_expression_in_casechoice1376);
            	    e=expression();

            	    state._fsp--;


            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:304:25: ( Range e2= expression )?
            	    int alt22=2;
            	    int LA22_0 = input.LA(1);

            	    if ( (LA22_0==Range) ) {
            	        alt22=1;
            	    }
            	    switch (alt22) {
            	        case 1 :
            	            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:304:26: Range e2= expression
            	            {
            	            match(input,Range,FOLLOW_Range_in_casechoice1379); 

            	            pushFollow(FOLLOW_expression_in_casechoice1383);
            	            e2=expression();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    if (e2 == null){
            	              item.addItem(e);
            	          }else{
            	              item.addItem(new Range(e, e2));
            	          }

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            match(input,56,FOLLOW_56_in_casechoice1396); 

            pushFollow(FOLLOW_block_in_casechoice1398);
            block13=block();

            state._fsp--;


            pair = new Pair(item, block13);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return pair;
    }
    // $ANTLR end "casechoice"



    // $ANTLR start "repeatvariablefrom"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:316:1: repeatvariablefrom returns [RepeatVariableFrom statement] : 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )? ;
    public final RepeatVariableFrom repeatvariablefrom() throws Exception, RecognitionException {
        RepeatVariableFrom statement = null;


        CommonTree ID14=null;
        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;

        Block block15 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:5: ( 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:7: 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )?
            {
            match(input,78,FOLLOW_78_in_repeatvariablefrom1431); 

            ID14=(CommonTree)match(input,ID,FOLLOW_ID_in_repeatvariablefrom1433); 

            match(input,68,FOLLOW_68_in_repeatvariablefrom1435); 

            pushFollow(FOLLOW_expression_in_repeatvariablefrom1439);
            e1=expression();

            state._fsp--;


            match(input,84,FOLLOW_84_in_repeatvariablefrom1441); 

            pushFollow(FOLLOW_expression_in_repeatvariablefrom1447);
            e2=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:61: ( 'step' e3= expression )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==80) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:62: 'step' e3= expression
                    {
                    match(input,80,FOLLOW_80_in_repeatvariablefrom1450); 

                    pushFollow(FOLLOW_expression_in_repeatvariablefrom1454);
                    e3=expression();

                    state._fsp--;


                    }
                    break;

            }


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:85: ( block )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==Indent) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:317:85: block
                    {
                    pushFollow(FOLLOW_block_in_repeatvariablefrom1458);
                    block15=block();

                    state._fsp--;


                    }
                    break;

            }


            if (e3 == null){
                      statement = new RepeatVariableFrom(e1, e2, ID14.getText(), block15, currentMethod);
                   }else{
                      statement = new RepeatVariableFrom(e1, e2, ID14.getText(), e3, block15, currentMethod);
                   }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "repeatvariablefrom"



    // $ANTLR start "repeatconstanttimes"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:327:1: repeatconstanttimes returns [RepeatConstantTimes statement] : 'repeat' e= expression ( block )? ;
    public final RepeatConstantTimes repeatconstanttimes() throws Exception, RecognitionException {
        RepeatConstantTimes statement = null;


        Expression e =null;

        Block block16 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:328:5: ( 'repeat' e= expression ( block )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:328:7: 'repeat' e= expression ( block )?
            {
            match(input,78,FOLLOW_78_in_repeatconstanttimes1503); 

            pushFollow(FOLLOW_expression_in_repeatconstanttimes1507);
            e=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:328:29: ( block )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==Indent) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:328:29: block
                    {
                    pushFollow(FOLLOW_block_in_repeatconstanttimes1509);
                    block16=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatConstantTimes(e, currentMethod);
                  statement.setBlock(block16);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "repeatconstanttimes"



    // $ANTLR start "repeatuntil"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:333:1: repeatuntil returns [RepeatUntil statement] : 'repeat' 'until' e= expression ( block )? ;
    public final RepeatUntil repeatuntil() throws Exception, RecognitionException {
        RepeatUntil statement = null;


        Expression e =null;

        Block block17 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:334:5: ( 'repeat' 'until' e= expression ( block )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:334:7: 'repeat' 'until' e= expression ( block )?
            {
            match(input,78,FOLLOW_78_in_repeatuntil1544); 

            match(input,85,FOLLOW_85_in_repeatuntil1546); 

            pushFollow(FOLLOW_expression_in_repeatuntil1550);
            e=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:334:37: ( block )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==Indent) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:334:37: block
                    {
                    pushFollow(FOLLOW_block_in_repeatuntil1552);
                    block17=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatUntil(e, currentMethod);
                   statement.setBlock(block17);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "repeatuntil"



    // $ANTLR start "repeatwhile"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:339:1: repeatwhile returns [RepeatWhile statement] : 'repeat' 'while' e= expression ( block )? ;
    public final RepeatWhile repeatwhile() throws Exception, RecognitionException {
        RepeatWhile statement = null;


        Expression e =null;

        Block block18 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:340:5: ( 'repeat' 'while' e= expression ( block )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:340:7: 'repeat' 'while' e= expression ( block )?
            {
            match(input,78,FOLLOW_78_in_repeatwhile1587); 

            match(input,88,FOLLOW_88_in_repeatwhile1589); 

            pushFollow(FOLLOW_expression_in_repeatwhile1593);
            e=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:340:37: ( block )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==Indent) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:340:37: block
                    {
                    pushFollow(FOLLOW_block_in_repeatwhile1595);
                    block18=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatWhile(e, currentMethod);
                   statement.setBlock(block18);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "repeatwhile"



    // $ANTLR start "repeat"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:345:1: repeat returns [Repeat statement] : 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )? ;
    public final Repeat repeat() throws Exception, RecognitionException {
        Repeat statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Block block19 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:5: ( 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:7: 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )?
            {
            match(input,78,FOLLOW_78_in_repeat1634); 

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:16: ( block | NEWLINE )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==Indent) ) {
                alt29=1;
            }
            else if ( (LA29_0==NEWLINE) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:17: block
                    {
                    pushFollow(FOLLOW_block_in_repeat1637);
                    block19=block();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:23: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat1639); 

                    }
                    break;

            }


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:32: ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==NEWLINE) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==85||LA31_1==88) ) {
                    alt31=1;
                }
            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:33: NEWLINE ( 'until' e1= expression | 'while' e2= expression )
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat1643); 

                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:40: ( 'until' e1= expression | 'while' e2= expression )
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==85) ) {
                        alt30=1;
                    }
                    else if ( (LA30_0==88) ) {
                        alt30=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 0, input);

                        throw nvae;

                    }
                    switch (alt30) {
                        case 1 :
                            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:41: 'until' e1= expression
                            {
                            match(input,85,FOLLOW_85_in_repeat1645); 

                            pushFollow(FOLLOW_expression_in_repeat1649);
                            e1=expression();

                            state._fsp--;


                            }
                            break;
                        case 2 :
                            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:347:63: 'while' e2= expression
                            {
                            match(input,88,FOLLOW_88_in_repeat1651); 

                            pushFollow(FOLLOW_expression_in_repeat1655);
                            e2=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;

            }


            if ((e1 == null)&&(e2 == null)){
                      statement = new Repeat(currentMethod);
                  }else if(e1 == null){
                      statement = new RepeatBlockWhile(e2, currentMethod);
                  }else if(e2 ==null){
                      statement = new RepeatBlockUntil(e1, currentMethod);
                  }else{ throw new SyntaxError("Using while and until concurrently");}
                  statement.setBlock(block19);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return statement;
    }
    // $ANTLR end "repeat"



    // $ANTLR start "array"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:359:1: array returns [Expression exp] : '[' expression ']' ;
    public final Expression array() throws Exception, RecognitionException {
        Expression exp = null;


        Expression expression20 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:360:5: ( '[' expression ']' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:360:7: '[' expression ']'
            {
            match(input,59,FOLLOW_59_in_array1698); 

            pushFollow(FOLLOW_expression_in_array1699);
            expression20=expression();

            state._fsp--;


            match(input,60,FOLLOW_60_in_array1700); 

            exp = expression20;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "array"



    // $ANTLR start "expression"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:381:1: expression returns [Expression exp] : ( ^( POST IncOp e= expression ) | ^( PRE IncOp e= expression ) | ^( NEGATION e= expression ) | ^(o= ( BoolNotOp | Unary2Op ) e= expression ) | ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression ) | ^( Assign e3= expression e4= expression ) |n= number |i= ident );
    public final Expression expression() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree o=null;
        CommonTree IncOp21=null;
        CommonTree IncOp22=null;
        Expression e =null;

        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;

        Expression e4 =null;

        SpinNumber n =null;

        Expression i =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:382:5: ( ^( POST IncOp e= expression ) | ^( PRE IncOp e= expression ) | ^( NEGATION e= expression ) | ^(o= ( BoolNotOp | Unary2Op ) e= expression ) | ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression ) | ^( Assign e3= expression e4= expression ) |n= number |i= ident )
            int alt32=8;
            switch ( input.LA(1) ) {
            case POST:
                {
                alt32=1;
                }
                break;
            case PRE:
                {
                alt32=2;
                }
                break;
            case NEGATION:
                {
                alt32=3;
                }
                break;
            case BoolNotOp:
            case Unary2Op:
                {
                alt32=4;
                }
                break;
            case BitAndOp:
            case BoolAndOp:
            case BoolOp:
            case BoolOrOp:
            case LimitMaxOp:
            case MultOp:
            case Multiply:
            case ShiftOp:
            case 52:
            case 54:
                {
                alt32=5;
                }
                break;
            case Assign:
                {
                alt32=6;
                }
                break;
            case FLOAT:
            case INT:
                {
                alt32=7;
                }
                break;
            case ASSIGNMENT:
            case CHARACTER:
            case False:
            case ID:
            case POSTINCREMENT:
            case True:
            case UnaryOp:
            case 58:
            case 73:
            case 74:
            case 75:
            case 76:
            case 81:
            case 82:
            case 83:
            case 93:
                {
                alt32=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:382:7: ^( POST IncOp e= expression )
                    {
                    match(input,POST,FOLLOW_POST_in_expression1738); 

                    match(input, Token.DOWN, null); 
                    IncOp21=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_expression1740); 

                    pushFollow(FOLLOW_expression_in_expression1744);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, "post"+IncOp21.getText(), currentMethod);

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:383:7: ^( PRE IncOp e= expression )
                    {
                    match(input,PRE,FOLLOW_PRE_in_expression1756); 

                    match(input, Token.DOWN, null); 
                    IncOp22=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_expression1758); 

                    pushFollow(FOLLOW_expression_in_expression1762);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, "pre"+IncOp22.getText(), currentMethod);

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:384:7: ^( NEGATION e= expression )
                    {
                    match(input,NEGATION,FOLLOW_NEGATION_in_expression1774); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1778);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Negation(e, currentMethod);

                    }
                    break;
                case 4 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:386:7: ^(o= ( BoolNotOp | Unary2Op ) e= expression )
                    {
                    o=(CommonTree)input.LT(1);

                    if ( input.LA(1)==BoolNotOp||input.LA(1)==Unary2Op ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1805);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, o.getText(), currentMethod);

                    }
                    break;
                case 5 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:388:7: ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression )
                    {
                    o=(CommonTree)input.LT(1);

                    if ( input.LA(1)==BitAndOp||input.LA(1)==BoolAndOp||(input.LA(1) >= BoolOp && input.LA(1) <= BoolOrOp)||input.LA(1)==LimitMaxOp||input.LA(1)==MultOp||input.LA(1)==Multiply||input.LA(1)==ShiftOp||input.LA(1)==52||input.LA(1)==54 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1858);
                    e1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1862);
                    e2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e1, e2, o.getText(), currentMethod);

                    }
                    break;
                case 6 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:390:7: ^( Assign e3= expression e4= expression )
                    {
                    match(input,Assign,FOLLOW_Assign_in_expression1875); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1879);
                    e3=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1883);
                    e4=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Assignment(e3,e4,currentMethod);

                    }
                    break;
                case 7 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:391:7: n= number
                    {
                    pushFollow(FOLLOW_number_in_expression1896);
                    n=number();

                    state._fsp--;


                    if (n.isfloat){
                            exp = new AnonVariable(n.getValue(),true);
                          }else{
                            exp = new AnonVariable(n.getValue());
                          }

                    }
                    break;
                case 8 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:397:7: i= ident
                    {
                    pushFollow(FOLLOW_ident_in_expression1915);
                    i=ident();

                    state._fsp--;


                    exp =i;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "expression"



    // $ANTLR start "ident"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:401:1: ident returns [Expression exp] : ( True | False |e1= objectConstant |e2= objMethodCall |e3= methodCall |e4= string |e5= strcomp |e6= character |e7= strsize |e8= lookup |e9= lookdown |e10= abs |e11= rand |e12= id |e13= assignment |e14= postincrement |a= UnaryOp id );
    public final Expression ident() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree a=null;
        Constant e1 =null;

        MethodCall e2 =null;

        MethodCall e3 =null;

        SpinStringWrapper e4 =null;

        Expression e5 =null;

        Expression e6 =null;

        Expression e7 =null;

        Expression e8 =null;

        Expression e9 =null;

        SpinExpression e10 =null;

        RandomExpression e11 =null;

        Expression e12 =null;

        Expression e13 =null;

        Expression e14 =null;

        Expression id23 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:402:5: ( True | False |e1= objectConstant |e2= objMethodCall |e3= methodCall |e4= string |e5= strcomp |e6= character |e7= strsize |e8= lookup |e9= lookdown |e10= abs |e11= rand |e12= id |e13= assignment |e14= postincrement |a= UnaryOp id )
            int alt33=17;
            switch ( input.LA(1) ) {
            case True:
                {
                alt33=1;
                }
                break;
            case False:
                {
                alt33=2;
                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case 49:
                    {
                    alt33=3;
                    }
                    break;
                case 55:
                    {
                    alt33=4;
                    }
                    break;
                case 50:
                    {
                    alt33=5;
                    }
                    break;
                case UP:
                case ASSIGNMENT:
                case Assign:
                case BitAndOp:
                case BoolAndOp:
                case BoolNotOp:
                case BoolOp:
                case BoolOrOp:
                case CHARACTER:
                case FLOAT:
                case False:
                case ID:
                case INT:
                case Indent:
                case LimitMaxOp:
                case MultOp:
                case Multiply:
                case NEGATION:
                case NEWLINE:
                case POST:
                case POSTINCREMENT:
                case PRE:
                case Range:
                case ShiftOp:
                case True:
                case Unary2Op:
                case UnaryOp:
                case 51:
                case 52:
                case 53:
                case 54:
                case 56:
                case 58:
                case 59:
                case 60:
                case 73:
                case 74:
                case 75:
                case 76:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 93:
                    {
                    alt33=14;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 3, input);

                    throw nvae;

                }

                }
                break;
            case 82:
                {
                alt33=6;
                }
                break;
            case 81:
                {
                alt33=7;
                }
                break;
            case CHARACTER:
                {
                alt33=8;
                }
                break;
            case 83:
                {
                alt33=9;
                }
                break;
            case 75:
            case 76:
                {
                alt33=10;
                }
                break;
            case 73:
            case 74:
                {
                alt33=11;
                }
                break;
            case 93:
                {
                alt33=12;
                }
                break;
            case 58:
                {
                alt33=13;
                }
                break;
            case ASSIGNMENT:
                {
                alt33=15;
                }
                break;
            case POSTINCREMENT:
                {
                alt33=16;
                }
                break;
            case UnaryOp:
                {
                alt33=17;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }

            switch (alt33) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:402:7: True
                    {
                    match(input,True,FOLLOW_True_in_ident1943); 

                    exp = Variable.TRUE;

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:403:7: False
                    {
                    match(input,False,FOLLOW_False_in_ident1964); 

                    exp = Variable.FALSE;

                    }
                    break;
                case 3 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:404:7: e1= objectConstant
                    {
                    pushFollow(FOLLOW_objectConstant_in_ident1986);
                    e1=objectConstant();

                    state._fsp--;


                    exp = e1; 

                    }
                    break;
                case 4 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:405:7: e2= objMethodCall
                    {
                    pushFollow(FOLLOW_objMethodCall_in_ident1997);
                    e2=objMethodCall();

                    state._fsp--;


                    exp = e2; 

                    }
                    break;
                case 5 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:406:7: e3= methodCall
                    {
                    pushFollow(FOLLOW_methodCall_in_ident2009);
                    e3=methodCall();

                    state._fsp--;


                    exp = e3; 

                    }
                    break;
                case 6 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:407:7: e4= string
                    {
                    pushFollow(FOLLOW_string_in_ident2024);
                    e4=string();

                    state._fsp--;


                    exp = e4; 

                    }
                    break;
                case 7 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:408:7: e5= strcomp
                    {
                    pushFollow(FOLLOW_strcomp_in_ident2043);
                    e5=strcomp();

                    state._fsp--;


                    exp = e5; 

                    }
                    break;
                case 8 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:409:7: e6= character
                    {
                    pushFollow(FOLLOW_character_in_ident2062);
                    e6=character();

                    state._fsp--;


                    exp = e6; 

                    }
                    break;
                case 9 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:410:7: e7= strsize
                    {
                    pushFollow(FOLLOW_strsize_in_ident2078);
                    e7=strsize();

                    state._fsp--;


                    exp = e7; 

                    }
                    break;
                case 10 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:411:7: e8= lookup
                    {
                    pushFollow(FOLLOW_lookup_in_ident2096);
                    e8=lookup();

                    state._fsp--;


                    exp = e8; 

                    }
                    break;
                case 11 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:412:7: e9= lookdown
                    {
                    pushFollow(FOLLOW_lookdown_in_ident2115);
                    e9=lookdown();

                    state._fsp--;


                    exp = e9; 

                    }
                    break;
                case 12 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:413:7: e10= abs
                    {
                    pushFollow(FOLLOW_abs_in_ident2132);
                    e10=abs();

                    state._fsp--;


                    exp = e10;

                    }
                    break;
                case 13 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:414:7: e11= rand
                    {
                    pushFollow(FOLLOW_rand_in_ident2153);
                    e11=rand();

                    state._fsp--;


                    exp = e11;

                    }
                    break;
                case 14 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:415:7: e12= id
                    {
                    pushFollow(FOLLOW_id_in_ident2173);
                    e12=id();

                    state._fsp--;


                    exp = e12;

                    }
                    break;
                case 15 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:416:7: e13= assignment
                    {
                    pushFollow(FOLLOW_assignment_in_ident2195);
                    e13=assignment();

                    state._fsp--;


                    exp = e13;

                    }
                    break;
                case 16 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:417:7: e14= postincrement
                    {
                    pushFollow(FOLLOW_postincrement_in_ident2209);
                    e14=postincrement();

                    state._fsp--;


                    exp = e14;

                    }
                    break;
                case 17 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:421:7: a= UnaryOp id
                    {
                    a=(CommonTree)match(input,UnaryOp,FOLLOW_UnaryOp_in_ident2234); 

                    pushFollow(FOLLOW_id_in_ident2236);
                    id23=id();

                    state._fsp--;


                    if (a.getText().equals("~")){
                              Debug.debug("its a sign extend");
                              //code for '~' in SpinTokenizer
                              exp = new SignExtend(id23,-59);
                           }else if (a.getText().equals("~~")){
                              Debug.debug("its a sign extend");
                              //code for '~~' in SpinTokenizer
                              exp = new SignExtend(id23,-60);
                           }else if (a.getText().equals("@")){
                              Debug.debug("its an address");
                              exp = new Address(id23, program.getMemory());
                           }
                              
                          

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "ident"



    // $ANTLR start "id"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:438:1: id returns [Expression exp] : ID ( array )? ;
    public final Expression id() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree ID24=null;
        Expression array25 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:439:5: ( ID ( array )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:439:7: ID ( array )?
            {
            ID24=(CommonTree)match(input,ID,FOLLOW_ID_in_id2279); 

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:439:10: ( array )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==59) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:439:10: array
                    {
                    pushFollow(FOLLOW_array_in_id2281);
                    array25=array();

                    state._fsp--;


                    }
                    break;

            }


            if (program.isVariable(ID24.getText())){
                      //check if its an array access or not
                      if (array25 != null){
                          Debug.debug("its an array");
                          exp = new ArrayAccess(program.getVariable(ID24.getText()), array25, program.getMemory());
                      }else{
                          Debug.debug("its not an array");
                          exp = program.getVariable(ID24.getText());
                      }
                      //now check if there is a sign extend
                      
                   }   
                   else if (program.isConstant(ID24.getText())){exp = program.getConstant(ID24.getText());}
                   else if (currentMethod.isVariable(ID24.getText())){
                      //check for an array access
                      if (array25 != null){
                          exp = new ArrayAccess(ID24.getText(), array25);
                      }else{
                          exp = new VariableExpression(ID24.getText());
                      }
                      
                  }else if (program.isMethod(ID24.getText())){exp = new MethodCall(ID24.getText(),null, program,currentMethod);}
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "id"



    // $ANTLR start "assignment"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:465:1: assignment returns [Expression exp] : ^( ASSIGNMENT Assign id e= expression ) ;
    public final Expression assignment() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree Assign27=null;
        Expression e =null;

        Expression id26 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:466:5: ( ^( ASSIGNMENT Assign id e= expression ) )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:466:7: ^( ASSIGNMENT Assign id e= expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment2318); 

            match(input, Token.DOWN, null); 
            Assign27=(CommonTree)match(input,Assign,FOLLOW_Assign_in_assignment2320); 

            pushFollow(FOLLOW_id_in_assignment2322);
            id26=id();

            state._fsp--;


            pushFollow(FOLLOW_expression_in_assignment2326);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 


             if (id26 instanceof MethodCall) throw new SyntaxError("expected variable");
                  if (Assign27.getText().equals(":=")){
                    exp = new Assignment(id26, e, currentMethod); 
                  }else{
                    exp = new SpinExpression(id26, e, Assign27.getText(), currentMethod);
                  }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "assignment"



    // $ANTLR start "postincrement"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:475:1: postincrement returns [Expression exp] : ^( POSTINCREMENT IncOp id ) ;
    public final Expression postincrement() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree IncOp29=null;
        Expression id28 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:476:5: ( ^( POSTINCREMENT IncOp id ) )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:476:7: ^( POSTINCREMENT IncOp id )
            {
            match(input,POSTINCREMENT,FOLLOW_POSTINCREMENT_in_postincrement2365); 

            match(input, Token.DOWN, null); 
            IncOp29=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_postincrement2367); 

            pushFollow(FOLLOW_id_in_postincrement2369);
            id28=id();

            state._fsp--;


            match(input, Token.UP, null); 


             exp = new SpinExpression(id28, "post"+IncOp29.getText(), currentMethod); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "postincrement"



    // $ANTLR start "rand"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:480:1: rand returns [RandomExpression exp] : '?' id ;
    public final RandomExpression rand() throws Exception, RecognitionException {
        RandomExpression exp = null;


        Expression id30 =null;


        Expression ex = null;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:482:5: ( '?' id )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:482:7: '?' id
            {
            match(input,58,FOLLOW_58_in_rand2413); 

            pushFollow(FOLLOW_id_in_rand2415);
            id30=id();

            state._fsp--;


            if (id30 instanceof MethodCall){throw new SyntaxError("Cannot call ? on method");}
                   exp = new RandomExpression(id30,currentMethod);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "rand"



    // $ANTLR start "abs"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:487:1: abs returns [SpinExpression exp] : '||' ident ;
    public final SpinExpression abs() throws Exception, RecognitionException {
        SpinExpression exp = null;


        Expression ident31 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:488:5: ( '||' ident )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:488:6: '||' ident
            {
            match(input,93,FOLLOW_93_in_abs2452); 

            pushFollow(FOLLOW_ident_in_abs2454);
            ident31=ident();

            state._fsp--;


            exp = new SpinExpression(ident31, "||", currentMethod);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "abs"



    // $ANTLR start "lookup"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:491:1: lookup returns [Expression exp] : s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')' ;
    public final Expression lookup() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree s=null;
        Expression e =null;

        SpinList r =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:492:5: (s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:492:7: s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')'
            {
            s=(CommonTree)input.LT(1);

            if ( (input.LA(1) >= 75 && input.LA(1) <= 76) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,50,FOLLOW_50_in_lookup2493); 

            pushFollow(FOLLOW_expression_in_lookup2496);
            e=expression();

            state._fsp--;


            match(input,56,FOLLOW_56_in_lookup2497); 

            pushFollow(FOLLOW_rangelist_in_lookup2501);
            r=rangelist();

            state._fsp--;


            match(input,51,FOLLOW_51_in_lookup2502); 

            boolean z = s.getText().equals("lookupz");
                   exp = new LookUp(e,r, z);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "lookup"



    // $ANTLR start "lookdown"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:497:1: lookdown returns [Expression exp] : s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')' ;
    public final Expression lookdown() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree s=null;
        Expression e =null;

        SpinList r =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:498:5: (s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:498:7: s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')'
            {
            s=(CommonTree)input.LT(1);

            if ( (input.LA(1) >= 73 && input.LA(1) <= 74) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,50,FOLLOW_50_in_lookdown2547); 

            pushFollow(FOLLOW_expression_in_lookdown2550);
            e=expression();

            state._fsp--;


            match(input,56,FOLLOW_56_in_lookdown2551); 

            pushFollow(FOLLOW_rangelist_in_lookdown2555);
            r=rangelist();

            state._fsp--;


            match(input,51,FOLLOW_51_in_lookdown2556); 

            boolean z = s.getText().equals("lookdownz");
                   exp = new LookDown(e,r, z);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "lookdown"



    // $ANTLR start "rangelist"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:503:1: rangelist returns [SpinList list] : r1= rangeitem ( ',' r2= rangeitem )* ;
    public final SpinList rangelist() throws Exception, RecognitionException {
        SpinList list = null;


        SpinListItem r1 =null;

        SpinListItem r2 =null;


        list = new SpinList();
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:505:5: (r1= rangeitem ( ',' r2= rangeitem )* )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:505:7: r1= rangeitem ( ',' r2= rangeitem )*
            {
            pushFollow(FOLLOW_rangeitem_in_rangelist2605);
            r1=rangeitem();

            state._fsp--;


            list.addItem(r1);

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:506:7: ( ',' r2= rangeitem )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==53) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:506:8: ',' r2= rangeitem
            	    {
            	    match(input,53,FOLLOW_53_in_rangelist2616); 

            	    pushFollow(FOLLOW_rangeitem_in_rangelist2619);
            	    r2=rangeitem();

            	    state._fsp--;


            	    list.addItem(r2);

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return list;
    }
    // $ANTLR end "rangelist"



    // $ANTLR start "rangeitem"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:509:1: rangeitem returns [SpinListItem item] : e1= expression ( '..' e2= expression )? ;
    public final SpinListItem rangeitem() throws Exception, RecognitionException {
        SpinListItem item = null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:510:5: (e1= expression ( '..' e2= expression )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:510:7: e1= expression ( '..' e2= expression )?
            {
            pushFollow(FOLLOW_expression_in_rangeitem2654);
            e1=expression();

            state._fsp--;


            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:510:21: ( '..' e2= expression )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Range) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:510:22: '..' e2= expression
                    {
                    match(input,Range,FOLLOW_Range_in_rangeitem2657); 

                    pushFollow(FOLLOW_expression_in_rangeitem2660);
                    e2=expression();

                    state._fsp--;


                    }
                    break;

            }


            if (e2 == null){
                    item = new ListItem(e1);
                  }else{
                    item = new Range(e1, e2);
                  }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return item;
    }
    // $ANTLR end "rangeitem"



    // $ANTLR start "character"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:518:1: character returns [Expression exp] : c= CHARACTER ;
    public final Expression character() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree c=null;

        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:519:5: (c= CHARACTER )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:519:7: c= CHARACTER
            {
            c=(CommonTree)match(input,CHARACTER,FOLLOW_CHARACTER_in_character2698); 

            exp = new AnonVariable((byte)c.getText().charAt(1));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "character"



    // $ANTLR start "strcomp"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:521:1: strcomp returns [Expression exp] : 'strcomp' '(' e1= expression ',' e2= expression ')' ;
    public final Expression strcomp() throws Exception, RecognitionException {
        Expression exp = null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:522:5: ( 'strcomp' '(' e1= expression ',' e2= expression ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:522:7: 'strcomp' '(' e1= expression ',' e2= expression ')'
            {
            match(input,81,FOLLOW_81_in_strcomp2724); 

            match(input,50,FOLLOW_50_in_strcomp2725); 

            pushFollow(FOLLOW_expression_in_strcomp2728);
            e1=expression();

            state._fsp--;


            match(input,53,FOLLOW_53_in_strcomp2730); 

            pushFollow(FOLLOW_expression_in_strcomp2734);
            e2=expression();

            state._fsp--;


            match(input,51,FOLLOW_51_in_strcomp2735); 

            exp = new StrComp(e1,e2,program.getMemory());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "strcomp"



    // $ANTLR start "strsize"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:526:1: strsize returns [Expression exp] : 'strsize' '(' e= expression ')' ;
    public final Expression strsize() throws Exception, RecognitionException {
        Expression exp = null;


        Expression e =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:527:5: ( 'strsize' '(' e= expression ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:527:7: 'strsize' '(' e= expression ')'
            {
            match(input,83,FOLLOW_83_in_strsize2770); 

            match(input,50,FOLLOW_50_in_strsize2771); 

            pushFollow(FOLLOW_expression_in_strsize2774);
            e=expression();

            state._fsp--;


            match(input,51,FOLLOW_51_in_strsize2775); 

            exp = new StrSize(e, program.getMemory());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "strsize"



    // $ANTLR start "paramlist"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:531:1: paramlist returns [Expression[] exps] : '(' l= list ')' ;
    public final Expression[] paramlist() throws Exception, RecognitionException {
        Expression[] exps = null;


        Expression[] l =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:532:5: ( '(' l= list ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:532:9: '(' l= list ')'
            {
            match(input,50,FOLLOW_50_in_paramlist2813); 

            pushFollow(FOLLOW_list_in_paramlist2817);
            l=list();

            state._fsp--;


            match(input,51,FOLLOW_51_in_paramlist2819); 

            exps = l;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exps;
    }
    // $ANTLR end "paramlist"



    // $ANTLR start "list"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:535:1: list returns [Expression[] exps] : (e= expression )* ;
    public final Expression[] list() throws Exception, RecognitionException {
        Expression[] exps = null;


        Expression e =null;


        ArrayList<Expression> explist = new ArrayList<Expression>();
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:537:5: ( (e= expression )* )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:537:9: (e= expression )*
            {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:537:9: (e= expression )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( ((LA37_0 >= ASSIGNMENT && LA37_0 <= Assign)||LA37_0==BitAndOp||(LA37_0 >= BoolAndOp && LA37_0 <= BoolOrOp)||LA37_0==CHARACTER||(LA37_0 >= FLOAT && LA37_0 <= False)||(LA37_0 >= ID && LA37_0 <= INT)||LA37_0==LimitMaxOp||LA37_0==MultOp||(LA37_0 >= Multiply && LA37_0 <= NEGATION)||(LA37_0 >= POST && LA37_0 <= PRE)||LA37_0==ShiftOp||LA37_0==True||(LA37_0 >= Unary2Op && LA37_0 <= UnaryOp)||LA37_0==52||LA37_0==54||LA37_0==58||(LA37_0 >= 73 && LA37_0 <= 76)||(LA37_0 >= 81 && LA37_0 <= 83)||LA37_0==93) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:537:10: e= expression
            	    {
            	    pushFollow(FOLLOW_expression_in_list2867);
            	    e=expression();

            	    state._fsp--;


            	    explist.add(e);

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            exps = new Expression[explist.size()];
                    for (int i = 0; i< explist.size(); i ++){
                        exps[i] = explist.get(i);
                    }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exps;
    }
    // $ANTLR end "list"



    // $ANTLR start "methodCall"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:544:1: methodCall returns [MethodCall mc] : ID p= paramlist ;
    public final MethodCall methodCall() throws Exception, RecognitionException {
        MethodCall mc = null;


        CommonTree ID32=null;
        Expression[] p =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:545:5: ( ID p= paramlist )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:545:7: ID p= paramlist
            {
            ID32=(CommonTree)match(input,ID,FOLLOW_ID_in_methodCall2911); 

            pushFollow(FOLLOW_paramlist_in_methodCall2915);
            p=paramlist();

            state._fsp--;


            if (!program.isMethod(ID32.getText())){
                      throw new SyntaxError("Method "+ID32.getText()+" does not exist");
                  }else{
                      mc = new MethodCall(ID32.getText(), p, program, currentMethod);
                  }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return mc;
    }
    // $ANTLR end "methodCall"



    // $ANTLR start "objMethodCall"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:553:1: objMethodCall returns [MethodCall mc] : o= ID '.' meth= ID (p= paramlist )? ;
    public final MethodCall objMethodCall() throws Exception, RecognitionException {
        MethodCall mc = null;


        CommonTree o=null;
        CommonTree meth=null;
        Expression[] p =null;


        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:554:5: (o= ID '.' meth= ID (p= paramlist )? )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:554:7: o= ID '.' meth= ID (p= paramlist )?
            {
            o=(CommonTree)match(input,ID,FOLLOW_ID_in_objMethodCall2955); 

            match(input,55,FOLLOW_55_in_objMethodCall2957); 

            meth=(CommonTree)match(input,ID,FOLLOW_ID_in_objMethodCall2961); 

            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:554:25: (p= paramlist )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==50) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:554:25: p= paramlist
                    {
                    pushFollow(FOLLOW_paramlist_in_objMethodCall2965);
                    p=paramlist();

                    state._fsp--;


                    }
                    break;

            }


            Program prog = program.getObject(o.getText());
                  mc = new MethodCall(meth.getText(),p,prog,currentMethod);
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return mc;
    }
    // $ANTLR end "objMethodCall"



    // $ANTLR start "objectConstant"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:560:1: objectConstant returns [Constant c] : o= ID '#' con= ID ;
    public final Constant objectConstant() throws Exception, RecognitionException {
        Constant c = null;


        CommonTree o=null;
        CommonTree con=null;

        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:561:5: (o= ID '#' con= ID )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:561:6: o= ID '#' con= ID
            {
            o=(CommonTree)match(input,ID,FOLLOW_ID_in_objectConstant3001); 

            match(input,49,FOLLOW_49_in_objectConstant3003); 

            con=(CommonTree)match(input,ID,FOLLOW_ID_in_objectConstant3007); 

            c = program.getObject(o.getText()).getConstant(con.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return c;
    }
    // $ANTLR end "objectConstant"



    // $ANTLR start "string"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:565:1: string returns [SpinStringWrapper str] : 'string' '(' s= STRING ')' ;
    public final SpinStringWrapper string() throws Exception, RecognitionException {
        SpinStringWrapper str = null;


        CommonTree s=null;

        int memoryNeeded; String stringId;
        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:567:5: ( 'string' '(' s= STRING ')' )
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:567:7: 'string' '(' s= STRING ')'
            {
            match(input,82,FOLLOW_82_in_string3047); 

            match(input,50,FOLLOW_50_in_string3049); 

            s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string3053); 

            match(input,51,FOLLOW_51_in_string3055); 

            memoryNeeded = s.getText().length()-1;
                  stringId = SpinString.getCurrentId();
                  str = new SpinStringWrapper(stringId);
                  currentMethod.addExtraMemory(memoryNeeded);
                  currentMethod.addLocalString(stringId, s.getText().substring(1,s.getText().length()-1));
                  Debug.debug("current method for the string(\"abc\"):");
                  Debug.debug(" "+currentMethod.getName());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return str;
    }
    // $ANTLR end "string"



    // $ANTLR start "number"
    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:578:1: number returns [SpinNumber value] : ( INT |f= FLOAT );
    public final SpinNumber number() throws Exception, RecognitionException {
        SpinNumber value = null;


        CommonTree f=null;
        CommonTree INT33=null;

        try {
            // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:579:5: ( INT |f= FLOAT )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==INT) ) {
                alt39=1;
            }
            else if ( (LA39_0==FLOAT) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;

            }
            switch (alt39) {
                case 1 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:579:6: INT
                    {
                    INT33=(CommonTree)match(input,INT,FOLLOW_INT_in_number3088); 

                    StringBuffer sb = new StringBuffer(INT33.getText());
                          int index = sb.indexOf("_");
                          while(index!= -1){
                            sb.deleteCharAt(index);
                            index = sb.indexOf("_");
                          }
                          String num = sb.toString();
                          value = new SpinNumber((int)(Long.parseLong(num)%(long)Math.pow(2,32)));
                         

                    }
                    break;
                case 2 :
                    // C:\\Users\\darryl\\workspace\\emulator\\src\\dang\\antlr\\parser\\ProgWalker.g:590:6: f= FLOAT
                    {
                    f=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_number3108); 

                    StringBuffer sb = new StringBuffer(f.getText());
                          int index = sb.indexOf("_");
                          while(index!= -1){
                            sb.deleteCharAt(index);
                            index = sb.indexOf("_");
                          }
                          String num = sb.toString();
                        
                          value =  new SpinNumber(Float.parseFloat(num));
                          

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "number"

    // Delegated rules


 

    public static final BitSet FOLLOW_conblock_in_prog118 = new BitSet(new long[]{0x0000000010000002L,0x0000000000402002L});
    public static final BitSet FOLLOW_objblock_in_prog120 = new BitSet(new long[]{0x0000000010000002L,0x0000000000402002L});
    public static final BitSet FOLLOW_varblock_in_prog122 = new BitSet(new long[]{0x0000000010000002L,0x0000000000402002L});
    public static final BitSet FOLLOW_method_in_prog124 = new BitSet(new long[]{0x0000000010000002L,0x0000000000402002L});
    public static final BitSet FOLLOW_65_in_conblock157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NEWLINE_in_conblock159 = new BitSet(new long[]{0x0000000200800008L});
    public static final BitSet FOLLOW_constatement_in_conblock162 = new BitSet(new long[]{0x0000000000800008L});
    public static final BitSet FOLLOW_ID_in_constatement189 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_constatement191 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_constatement195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_varblock236 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_vartype_in_varblock249 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_varblock253 = new BitSet(new long[]{0x2800000000800008L,0x0000000002000040L});
    public static final BitSet FOLLOW_array_in_varblock256 = new BitSet(new long[]{0x2000000000800008L,0x0000000002000040L});
    public static final BitSet FOLLOW_61_in_vartype307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_vartype318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_vartype329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_objblock361 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_objblock366 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_STRING_in_objblock370 = new BitSet(new long[]{0x0000000000800008L});
    public static final BitSet FOLLOW_Methodheader_in_method407 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_method411 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_method428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Indent_in_block473 = new BitSet(new long[]{0xC400001200808010L,0x0000000C2C80C1A1L});
    public static final BitSet FOLLOW_NEWLINE_in_block483 = new BitSet(new long[]{0xC400001200808010L,0x0000000C2C80C1A1L});
    public static final BitSet FOLLOW_statement_in_block489 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_NEWLINE_in_block491 = new BitSet(new long[]{0xC400001200808010L,0x0000000C2C80C1A1L});
    public static final BitSet FOLLOW_Dedent_in_block505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postincrement_in_statement562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_statement576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_statement601 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_paramlist_in_statement603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_statement613 = new BitSet(new long[]{0x0450D439A98C5EB2L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_statement615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_statement629 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_id_in_statement635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_statement659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_statement676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_statement691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case_statement_in_statement716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeat_in_statement737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatconstanttimes_in_statement765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatwhile_in_statement780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatuntil_in_statement803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatvariablefrom_in_statement826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memmove_in_statement842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memfill_in_statement869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abs_in_statement896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_memmove965 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_91_in_memmove975 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_72_in_memmove985 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_memmove995 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memmove998 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_memmove999 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memmove1002 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_memmove1003 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memmove1006 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_memmove1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_memfill1058 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_90_in_memfill1068 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_71_in_memfill1078 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_memfill1088 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memfill1091 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_memfill1092 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memfill1095 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_memfill1096 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_memfill1099 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_memfill1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_if_statement1144 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_if_statement1149 = new BitSet(new long[]{0x0000000204000002L});
    public static final BitSet FOLLOW_block_in_if_statement1164 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_NEWLINE_in_if_statement1180 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_if_statement1182 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_if_statement1187 = new BitSet(new long[]{0x0000000204000002L});
    public static final BitSet FOLLOW_block_in_if_statement1201 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_NEWLINE_in_if_statement1217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_if_statement1218 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_if_statement1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_case_statement1269 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_case_statement1271 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_Indent_in_case_statement1275 = new BitSet(new long[]{0x0450D43BA98CDEB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_casechoice_in_case_statement1288 = new BitSet(new long[]{0x0450D43BA98CDEB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_NEWLINE_in_case_statement1299 = new BitSet(new long[]{0x0450D43BA98CDEB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_Dedent_in_case_statement1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_casechoice1344 = new BitSet(new long[]{0x0120010000000000L});
    public static final BitSet FOLLOW_Range_in_casechoice1347 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_casechoice1351 = new BitSet(new long[]{0x0120000000000000L});
    public static final BitSet FOLLOW_53_in_casechoice1372 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_casechoice1376 = new BitSet(new long[]{0x0120010000000000L});
    public static final BitSet FOLLOW_Range_in_casechoice1379 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_casechoice1383 = new BitSet(new long[]{0x0120000000000000L});
    public static final BitSet FOLLOW_56_in_casechoice1396 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_block_in_casechoice1398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_repeatvariablefrom1431 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_repeatvariablefrom1433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_repeatvariablefrom1435 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1439 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_repeatvariablefrom1441 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1447 = new BitSet(new long[]{0x0000000004000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_repeatvariablefrom1450 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1454 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_repeatvariablefrom1458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_repeatconstanttimes1503 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatconstanttimes1507 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_repeatconstanttimes1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_repeatuntil1544 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_repeatuntil1546 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatuntil1550 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_repeatuntil1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_repeatwhile1587 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_repeatwhile1589 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeatwhile1593 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_block_in_repeatwhile1595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_repeat1634 = new BitSet(new long[]{0x0000000204000000L});
    public static final BitSet FOLLOW_block_in_repeat1637 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat1639 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat1643 = new BitSet(new long[]{0x0000000000000000L,0x0000000001200000L});
    public static final BitSet FOLLOW_85_in_repeat1645 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeat1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeat1651 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_repeat1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_array1698 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_array1699 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_array1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POST_in_expression1738 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_expression1740 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_expression1744 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRE_in_expression1756 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_expression1758 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_expression1762 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATION_in_expression1774 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1778 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expression1797 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1805 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expression1828 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1858 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_expression1862 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Assign_in_expression1875 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1879 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_expression1883 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_number_in_expression1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ident_in_expression1915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_True_in_ident1943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_False_in_ident1964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectConstant_in_ident1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_ident1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_ident2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_ident2024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strcomp_in_ident2043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_character_in_ident2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strsize_in_ident2078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_ident2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookdown_in_ident2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abs_in_ident2132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_ident2153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_ident2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_ident2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postincrement_in_ident2209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UnaryOp_in_ident2234 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_id_in_ident2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id2279 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_array_in_id2281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment2318 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assign_in_assignment2320 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_id_in_assignment2322 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_assignment2326 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POSTINCREMENT_in_postincrement2365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_postincrement2367 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_id_in_postincrement2369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_58_in_rand2413 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_id_in_rand2415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_abs2452 = new BitSet(new long[]{0x0400901000884010L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_ident_in_abs2454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_lookup2487 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_lookup2493 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_lookup2496 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_lookup2497 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_rangelist_in_lookup2501 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_lookup2502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_lookdown2541 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_lookdown2547 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_lookdown2550 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_lookdown2551 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_rangelist_in_lookdown2555 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_lookdown2556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist2605 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_53_in_rangelist2616 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist2619 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_expression_in_rangeitem2654 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_Range_in_rangeitem2657 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_rangeitem2660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_in_character2698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_strcomp2724 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_strcomp2725 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_strcomp2728 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_strcomp2730 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_strcomp2734 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_strcomp2735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_strsize2770 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_strsize2771 = new BitSet(new long[]{0x0450D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_expression_in_strsize2774 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_strsize2775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_paramlist2813 = new BitSet(new long[]{0x0458D439A98C5EB0L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_list_in_paramlist2817 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_paramlist2819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_list2867 = new BitSet(new long[]{0x0450D439A98C5EB2L,0x00000000200E1E00L});
    public static final BitSet FOLLOW_ID_in_methodCall2911 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_paramlist_in_methodCall2915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objMethodCall2955 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_objMethodCall2957 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_objMethodCall2961 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_paramlist_in_objMethodCall2965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objectConstant3001 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_objectConstant3003 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_objectConstant3007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_string3047 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_string3049 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_STRING_in_string3053 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_string3055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_number3088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_number3108 = new BitSet(new long[]{0x0000000000000002L});

}