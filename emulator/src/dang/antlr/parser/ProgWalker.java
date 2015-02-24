// $ANTLR 3.4 /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g 2012-09-13 07:39:49

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ProgWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGNMENT", "Assign", "BINARY", "BINARYOP", "BINARY_DIGIT", "BITNOT", "BITNOTEXP", "BitAndOp", "BitOrOp", "BoolAndOp", "BoolNotOp", "BoolOp", "BoolOrOp", "CHAR", "CHARACTER", "Dedent", "ESC_SEQ", "EXPONENT", "FLOAT", "False", "HEXADECIMAL", "HEX_DIGIT", "ID", "INT", "IncOp", "Indent", "LimitMaxOp", "Methodheader", "MultOp", "MultiLineComment", "Multiply", "NEGATION", "NEWLINE", "NOT_OP", "OCTAL_ESC", "POST", "POSTINCREMENT", "PRE", "QUATERNARY", "QUAT_DIGIT", "RAND", "RANGE", "RANGE_OR_INT", "Range", "SETHIGH", "SETLOW", "STRING", "ShiftOp", "SingleLineComment", "True", "UNARYOP", "UNICODE_ESC", "Unary2Op", "UnaryOp", "WS", "'#'", "'('", "')'", "'+'", "','", "'-'", "'.'", "':'", "'='", "'?'", "'['", "']'", "'byte'", "'bytefill'", "'bytemove'", "'case'", "'con'", "'else'", "'elseif'", "'from'", "'if'", "'long'", "'longfill'", "'longmove'", "'lookdown'", "'lookdownz'", "'lookup'", "'lookupz'", "'obj'", "'repeat'", "'return'", "'step'", "'strcomp'", "'string'", "'strsize'", "'to'", "'until'", "'var'", "'waitcnt'", "'while'", "'word'", "'wordfill'", "'wordmove'", "'|'", "'~'", "'~~'", "GlobalVariable", "LocalVariable", "Method", "SpinObject", "'++'", "'--'"
    };

    public static final int EOF=-1;
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
    public static final int T__94=94;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
    public static final int T__102=102;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int ASSIGNMENT=4;
    public static final int Assign=5;
    public static final int BINARY=6;
    public static final int BINARYOP=7;
    public static final int BINARY_DIGIT=8;
    public static final int BITNOT=9;
    public static final int BITNOTEXP=10;
    public static final int BitAndOp=11;
    public static final int BitOrOp=12;
    public static final int BoolAndOp=13;
    public static final int BoolNotOp=14;
    public static final int BoolOp=15;
    public static final int BoolOrOp=16;
    public static final int CHAR=17;
    public static final int CHARACTER=18;
    public static final int Dedent=19;
    public static final int ESC_SEQ=20;
    public static final int EXPONENT=21;
    public static final int FLOAT=22;
    public static final int False=23;
    public static final int HEXADECIMAL=24;
    public static final int HEX_DIGIT=25;
    public static final int ID=26;
    public static final int INT=27;
    public static final int IncOp=28;
    public static final int Indent=29;
    public static final int LimitMaxOp=30;
    public static final int Methodheader=31;
    public static final int MultOp=32;
    public static final int MultiLineComment=33;
    public static final int Multiply=34;
    public static final int NEGATION=35;
    public static final int NEWLINE=36;
    public static final int NOT_OP=37;
    public static final int OCTAL_ESC=38;
    public static final int POST=39;
    public static final int POSTINCREMENT=40;
    public static final int PRE=41;
    public static final int QUATERNARY=42;
    public static final int QUAT_DIGIT=43;
    public static final int RAND=44;
    public static final int RANGE=45;
    public static final int RANGE_OR_INT=46;
    public static final int Range=47;
    public static final int SETHIGH=48;
    public static final int SETLOW=49;
    public static final int STRING=50;
    public static final int ShiftOp=51;
    public static final int SingleLineComment=52;
    public static final int True=53;
    public static final int UNARYOP=54;
    public static final int UNICODE_ESC=55;
    public static final int Unary2Op=56;
    public static final int UnaryOp=57;
    public static final int WS=58;
    public static final int T__109=109;
    public static final int T__110=110;
    public static final int GlobalVariable=105;
    public static final int LocalVariable=106;
    public static final int Method=107;
    public static final int SpinObject=108;

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
    public String getGrammarFileName() { return "/home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g"; }


      Robot robot;
      Program program;
      Method currentMethod;
      JLabel message;
      String workingDirectory;
      IDE ide;



    // $ANTLR start "prog"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:98:1: prog[Program prog, JLabel message, String workingDirectory, IDE ide] returns [Program program] : ( conblock | objblock | varblock | method )* ;
    public final Program prog(Program prog, JLabel message, String workingDirectory, IDE ide) throws Exception, RecognitionException {
        Program program = null;



            //the peculiarities of ANTLR necessitate a lot of sharing. Great program though
            robot = prog.getRobot();
            this.program = prog;
            program = prog;
            this.workingDirectory = workingDirectory;
            this.message = message;
            this.ide = ide;
            
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:4: ( ( conblock | objblock | varblock | method )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:7: ( conblock | objblock | varblock | method )*
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:7: ( conblock | objblock | varblock | method )*
            loop1:
            do {
                int alt1=5;
                switch ( input.LA(1) ) {
                case 75:
                    {
                    alt1=1;
                    }
                    break;
                case 87:
                    {
                    alt1=2;
                    }
                    break;
                case 96:
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
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:8: conblock
            	    {
            	    pushFollow(FOLLOW_conblock_in_prog120);
            	    conblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:17: objblock
            	    {
            	    pushFollow(FOLLOW_objblock_in_prog122);
            	    objblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:26: varblock
            	    {
            	    pushFollow(FOLLOW_varblock_in_prog124);
            	    varblock();

            	    state._fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:109:35: method
            	    {
            	    pushFollow(FOLLOW_method_in_prog126);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:113:1: conblock : ^( 'con' ( NEWLINE )+ ( constatement )* ) ;
    public final void conblock() throws Exception, RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:5: ( ^( 'con' ( NEWLINE )+ ( constatement )* ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:7: ^( 'con' ( NEWLINE )+ ( constatement )* )
            {
            match(input,75,FOLLOW_75_in_conblock159); 

            match(input, Token.DOWN, null); 
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:15: ( NEWLINE )+
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
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:15: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_conblock161); 

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


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:24: ( constatement )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:114:24: constatement
            	    {
            	    pushFollow(FOLLOW_constatement_in_conblock164);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:117:1: constatement : i= ID '=' e= expression ;
    public final void constatement() throws Exception, RecognitionException {
        CommonTree i=null;
        Expression e =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:118:5: (i= ID '=' e= expression )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:118:8: i= ID '=' e= expression
            {
            i=(CommonTree)match(input,ID,FOLLOW_ID_in_constatement191); 

            match(input,67,FOLLOW_67_in_constatement193); 

            pushFollow(FOLLOW_expression_in_constatement197);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:124:1: varblock : ^( 'var' ( vartype ( ID ( array )? )+ )* ) ;
    public final void varblock() throws Exception, RecognitionException {
        CommonTree ID3=null;
        VariableType vartype1 =null;

        Expression array2 =null;


        Expression ar = null;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:133:5: ( ^( 'var' ( vartype ( ID ( array )? )+ )* ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:133:8: ^( 'var' ( vartype ( ID ( array )? )+ )* )
            {
            match(input,96,FOLLOW_96_in_varblock238); 

            Debug.debug("Varblock");

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:9: ( vartype ( ID ( array )? )+ )*
                loop6:
                do {
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==71||LA6_0==80||LA6_0==99) ) {
                        alt6=1;
                    }


                    switch (alt6) {
                	case 1 :
                	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:10: vartype ( ID ( array )? )+
                	    {
                	    pushFollow(FOLLOW_vartype_in_varblock251);
                	    vartype1=vartype();

                	    state._fsp--;


                	    Debug.debug("Vartype: "+ vartype1);

                	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:61: ( ID ( array )? )+
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
                	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:62: ID ( array )?
                	    	    {
                	    	    ID3=(CommonTree)match(input,ID,FOLLOW_ID_in_varblock255); 

                	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:65: ( array )?
                	    	    int alt4=2;
                	    	    int LA4_0 = input.LA(1);

                	    	    if ( (LA4_0==69) ) {
                	    	        alt4=1;
                	    	    }
                	    	    switch (alt4) {
                	    	        case 1 :
                	    	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:134:66: array
                	    	            {
                	    	            pushFollow(FOLLOW_array_in_varblock258);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:144:1: vartype returns [VariableType mtype] : ( 'byte' | 'word' | 'long' );
    public final VariableType vartype() throws RecognitionException {
        VariableType mtype = null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:145:5: ( 'byte' | 'word' | 'long' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 71:
                {
                alt7=1;
                }
                break;
            case 99:
                {
                alt7=2;
                }
                break;
            case 80:
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
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:145:8: 'byte'
                    {
                    match(input,71,FOLLOW_71_in_vartype309); 

                    mtype = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:146:8: 'word'
                    {
                    match(input,99,FOLLOW_99_in_vartype320); 

                    mtype = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:147:8: 'long'
                    {
                    match(input,80,FOLLOW_80_in_vartype331); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:150:1: objblock : ^( 'obj' (i= ID s= STRING )* ) ;
    public final void objblock() throws Exception, RecognitionException {
        CommonTree i=null;
        CommonTree s=null;

        String n;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:152:5: ( ^( 'obj' (i= ID s= STRING )* ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:152:7: ^( 'obj' (i= ID s= STRING )* )
            {
            match(input,87,FOLLOW_87_in_objblock363); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:152:15: (i= ID s= STRING )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==ID) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:152:16: i= ID s= STRING
                	    {
                	    i=(CommonTree)match(input,ID,FOLLOW_ID_in_objblock368); 

                	    s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_objblock372); 

                	    n=(s.getText().substring(1,s.getText().length()-1));
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
                	              String separator = System.getProperty("file.separator");
                	              File file = new File(workingDirectory+separator+n+".spin");
                	              Debug.debug("looking for file "+file.getAbsolutePath(),"file");
                	              if (!file.exists()){throw new SyntaxError("file "+n+".spin not found");}
                	              SpinObject object = new SpinObject(n, program);
                	              Compiler compiler = new Compiler(robot, message, ide);
                	              compiler.compileSpinObject(n,file, object);
                	              program.addObject(i.getText(), object);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:204:1: method : Methodheader i= ID ( block )? ;
    public final void method() throws Exception, RecognitionException {
        CommonTree i=null;
        Block block4 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:205:5: ( Methodheader i= ID ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:205:7: Methodheader i= ID ( block )?
            {
            match(input,Methodheader,FOLLOW_Methodheader_in_method409); 

            i=(CommonTree)match(input,ID,FOLLOW_ID_in_method413); 

            if (program.isMethod(i.getText())){
                    currentMethod = program.getMethod(i.getText());
                   }else{
                    throw new CompilerError("There is no method " + i.getText());
                   }
                   ADebug.debug("Read in method header: "+ i.getText());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:212:7: ( block )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==Indent) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:212:7: block
                    {
                    pushFollow(FOLLOW_block_in_method430);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:215:1: block returns [Block block] : Indent ( NEWLINE |s= statement NEWLINE |b= block_statement )* Dedent ;
    public final Block block() throws Exception, RecognitionException {
        Block block = null;


        Statement s =null;

        Statement b =null;


         block = new Block(currentMethod);
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:217:5: ( Indent ( NEWLINE |s= statement NEWLINE |b= block_statement )* Dedent )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:217:9: Indent ( NEWLINE |s= statement NEWLINE |b= block_statement )* Dedent
            {
            match(input,Indent,FOLLOW_Indent_in_block470); 

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:218:8: ( NEWLINE |s= statement NEWLINE |b= block_statement )*
            loop10:
            do {
                int alt10=4;
                switch ( input.LA(1) ) {
                case NEWLINE:
                    {
                    alt10=1;
                    }
                    break;
                case ASSIGNMENT:
                case ID:
                case NOT_OP:
                case POSTINCREMENT:
                case RAND:
                case SETHIGH:
                case SETLOW:
                case UNARYOP:
                case 64:
                case 72:
                case 73:
                case 81:
                case 82:
                case 89:
                case 97:
                case 100:
                case 101:
                case 109:
                case 110:
                    {
                    alt10=2;
                    }
                    break;
                case 74:
                case 79:
                case 88:
                    {
                    alt10=3;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:218:9: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_block480); 

            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:219:10: s= statement NEWLINE
            	    {
            	    pushFollow(FOLLOW_statement_in_block494);
            	    s=statement();

            	    state._fsp--;


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_block496); 

            	    block.addStatement(s);
            	            ADebug.debug("Block adding statement: "+ s);

            	    }
            	    break;
            	case 3 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:221:10: b= block_statement
            	    {
            	    pushFollow(FOLLOW_block_statement_in_block513);
            	    b=block_statement();

            	    state._fsp--;


            	    block.addStatement(b);

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match(input,Dedent,FOLLOW_Dedent_in_block528); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:225:1: statement returns [Statement statement] : (a= assignment |p= postincrement | rand | 'waitcnt' paramlist | 'return' ( expression )? |o= ( '--' | '++' ) id | methodCall | ID |s= set_bits_high |s2= set_bits_low | objMethodCall |r6= memmove |r7= memfill |uu= unary_statement |ns= not_statement | '-' ii= id );
    public final Statement statement() throws Exception, RecognitionException {
        Statement statement = null;


        CommonTree o=null;
        CommonTree ID10=null;
        Expression a =null;

        Expression p =null;

        Expression s =null;

        Expression s2 =null;

        Statement r6 =null;

        Statement r7 =null;

        Pair<SpinExpression, ArrayList<SpinExpression>> uu =null;

        Pair<SpinExpression, ArrayList<SpinExpression>> ns =null;

        Expression ii =null;

        RandomExpression rand5 =null;

        Expression[] paramlist6 =null;

        Expression expression7 =null;

        Expression id8 =null;

        MethodCall methodCall9 =null;

        MethodCall objMethodCall11 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:226:5: (a= assignment |p= postincrement | rand | 'waitcnt' paramlist | 'return' ( expression )? |o= ( '--' | '++' ) id | methodCall | ID |s= set_bits_high |s2= set_bits_low | objMethodCall |r6= memmove |r7= memfill |uu= unary_statement |ns= not_statement | '-' ii= id )
            int alt12=16;
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
            case RAND:
                {
                alt12=3;
                }
                break;
            case 97:
                {
                alt12=4;
                }
                break;
            case 89:
                {
                alt12=5;
                }
                break;
            case 109:
            case 110:
                {
                alt12=6;
                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case 65:
                    {
                    alt12=11;
                    }
                    break;
                case 60:
                    {
                    alt12=7;
                    }
                    break;
                case NEWLINE:
                    {
                    alt12=8;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 7, input);

                    throw nvae;

                }

                }
                break;
            case SETHIGH:
                {
                alt12=9;
                }
                break;
            case SETLOW:
                {
                alt12=10;
                }
                break;
            case 73:
            case 82:
            case 101:
                {
                alt12=12;
                }
                break;
            case 72:
            case 81:
            case 100:
                {
                alt12=13;
                }
                break;
            case UNARYOP:
                {
                alt12=14;
                }
                break;
            case NOT_OP:
                {
                alt12=15;
                }
                break;
            case 64:
                {
                alt12=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:226:7: a= assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement560);
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
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:232:7: p= postincrement
                    {
                    pushFollow(FOLLOW_postincrement_in_statement585);
                    p=postincrement();

                    state._fsp--;


                    statement = (SpinExpression)p;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:233:7: rand
                    {
                    pushFollow(FOLLOW_rand_in_statement599);
                    rand5=rand();

                    state._fsp--;


                    statement = rand5;

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:234:7: 'waitcnt' paramlist
                    {
                    match(input,97,FOLLOW_97_in_statement624); 

                    pushFollow(FOLLOW_paramlist_in_statement626);
                    paramlist6=paramlist();

                    state._fsp--;


                    statement = new Wait(paramlist6[0], currentMethod);

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:235:7: 'return' ( expression )?
                    {
                    match(input,89,FOLLOW_89_in_statement636); 

                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:235:16: ( expression )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= ASSIGNMENT && LA11_0 <= BINARY)||LA11_0==BitAndOp||LA11_0==BoolAndOp||(LA11_0 >= BoolOp && LA11_0 <= BoolOrOp)||LA11_0==CHARACTER||(LA11_0 >= FLOAT && LA11_0 <= HEXADECIMAL)||(LA11_0 >= ID && LA11_0 <= INT)||LA11_0==LimitMaxOp||LA11_0==MultOp||(LA11_0 >= Multiply && LA11_0 <= NEGATION)||LA11_0==NOT_OP||LA11_0==POST||(LA11_0 >= PRE && LA11_0 <= QUATERNARY)||LA11_0==RAND||LA11_0==ShiftOp||(LA11_0 >= True && LA11_0 <= UNARYOP)||(LA11_0 >= Unary2Op && LA11_0 <= UnaryOp)||LA11_0==62||LA11_0==64||(LA11_0 >= 83 && LA11_0 <= 86)||(LA11_0 >= 91 && LA11_0 <= 93)||(LA11_0 >= 103 && LA11_0 <= 104)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:235:16: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement638);
                            expression7=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    statement = new Return(expression7, currentMethod);

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:236:8: o= ( '--' | '++' ) id
                    {
                    o=(CommonTree)input.LT(1);

                    if ( (input.LA(1) >= 109 && input.LA(1) <= 110) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_id_in_statement658);
                    id8=id();

                    state._fsp--;


                    statement = new SpinExpression(id8, o.getText(), currentMethod);

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:237:8: methodCall
                    {
                    pushFollow(FOLLOW_methodCall_in_statement669);
                    methodCall9=methodCall();

                    state._fsp--;


                    statement = methodCall9;

                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:239:8: ID
                    {
                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_statement686); 

                    if (program.isMethod(ID10.getText())){
                                  statement = new MethodCall(ID10.getText(),null, program,currentMethod);
                              }else{
                                  throw new SyntaxError("No method found: "+ID10.getText());}

                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:244:7: s= set_bits_high
                    {
                    pushFollow(FOLLOW_set_bits_high_in_statement703);
                    s=set_bits_high();

                    state._fsp--;


                    statement = (Statement)s;

                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:245:7: s2= set_bits_low
                    {
                    pushFollow(FOLLOW_set_bits_low_in_statement725);
                    s2=set_bits_low();

                    state._fsp--;


                    statement = (Statement)s2;

                    }
                    break;
                case 11 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:246:7: objMethodCall
                    {
                    pushFollow(FOLLOW_objMethodCall_in_statement745);
                    objMethodCall11=objMethodCall();

                    state._fsp--;


                    statement = objMethodCall11;

                    }
                    break;
                case 12 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:247:7: r6= memmove
                    {
                    pushFollow(FOLLOW_memmove_in_statement770);
                    r6=memmove();

                    state._fsp--;


                    statement = r6;

                    }
                    break;
                case 13 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:248:7: r7= memfill
                    {
                    pushFollow(FOLLOW_memfill_in_statement797);
                    r7=memfill();

                    state._fsp--;


                    statement = r7;

                    }
                    break;
                case 14 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:249:7: uu= unary_statement
                    {
                    pushFollow(FOLLOW_unary_statement_in_statement824);
                    uu=unary_statement();

                    state._fsp--;


                    for(SpinExpression exp: uu.cdr()){
                            exp.setEquals(true); 
                          }
                          statement = uu.car();

                    }
                    break;
                case 15 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:254:7: ns= not_statement
                    {
                    pushFollow(FOLLOW_not_statement_in_statement849);
                    ns=not_statement();

                    state._fsp--;


                    for(SpinExpression exp: ns.cdr()){
                            exp.setEquals(true); 
                          }
                          statement = ns.car();

                    }
                    break;
                case 16 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:259:7: '-' ii= id
                    {
                    match(input,64,FOLLOW_64_in_statement872); 

                    pushFollow(FOLLOW_id_in_statement876);
                    ii=id();

                    state._fsp--;


                    statement = new SpinExpression(ii,"-", currentMethod, true);

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



    // $ANTLR start "block_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:263:1: block_statement returns [Statement statement] : (b= if_statement |c= case_statement |r1= repeat |r2= repeatconstanttimes |r3= repeatwhile |r4= repeatuntil |r5= repeatvariablefrom );
    public final Statement block_statement() throws Exception, RecognitionException {
        Statement statement = null;


        IfElse b =null;

        Case c =null;

        Repeat r1 =null;

        RepeatConstantTimes r2 =null;

        RepeatWhile r3 =null;

        RepeatUntil r4 =null;

        RepeatVariableFrom r5 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:264:5: (b= if_statement |c= case_statement |r1= repeat |r2= repeatconstanttimes |r3= repeatwhile |r4= repeatuntil |r5= repeatvariablefrom )
            int alt13=7;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt13=1;
                }
                break;
            case 74:
                {
                alt13=2;
                }
                break;
            case 88:
                {
                switch ( input.LA(2) ) {
                case 98:
                    {
                    alt13=5;
                    }
                    break;
                case 95:
                    {
                    alt13=6;
                    }
                    break;
                case ID:
                    {
                    int LA13_6 = input.LA(3);

                    if ( (LA13_6==78) ) {
                        alt13=7;
                    }
                    else if ( (LA13_6==ASSIGNMENT||LA13_6==Dedent||LA13_6==ID||LA13_6==Indent||(LA13_6 >= NEWLINE && LA13_6 <= NOT_OP)||LA13_6==POSTINCREMENT||LA13_6==RAND||(LA13_6 >= SETHIGH && LA13_6 <= SETLOW)||LA13_6==UNARYOP||(LA13_6 >= 59 && LA13_6 <= 60)||(LA13_6 >= 64 && LA13_6 <= 65)||(LA13_6 >= 68 && LA13_6 <= 69)||(LA13_6 >= 72 && LA13_6 <= 74)||LA13_6==79||(LA13_6 >= 81 && LA13_6 <= 82)||(LA13_6 >= 88 && LA13_6 <= 89)||LA13_6==97||(LA13_6 >= 100 && LA13_6 <= 101)||(LA13_6 >= 103 && LA13_6 <= 104)||(LA13_6 >= 109 && LA13_6 <= 110)) ) {
                        alt13=4;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 6, input);

                        throw nvae;

                    }
                    }
                    break;
                case Indent:
                case NEWLINE:
                    {
                    alt13=3;
                    }
                    break;
                case ASSIGNMENT:
                case Assign:
                case BINARY:
                case BitAndOp:
                case BoolAndOp:
                case BoolOp:
                case BoolOrOp:
                case CHARACTER:
                case FLOAT:
                case False:
                case HEXADECIMAL:
                case INT:
                case LimitMaxOp:
                case MultOp:
                case Multiply:
                case NEGATION:
                case NOT_OP:
                case POST:
                case PRE:
                case QUATERNARY:
                case RAND:
                case ShiftOp:
                case True:
                case UNARYOP:
                case Unary2Op:
                case UnaryOp:
                case 62:
                case 64:
                case 83:
                case 84:
                case 85:
                case 86:
                case 91:
                case 92:
                case 93:
                case 103:
                case 104:
                    {
                    alt13=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 3, input);

                    throw nvae;

                }

                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:264:7: b= if_statement
                    {
                    pushFollow(FOLLOW_if_statement_in_block_statement926);
                    b=if_statement();

                    state._fsp--;


                    statement = b;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:265:7: c= case_statement
                    {
                    pushFollow(FOLLOW_case_statement_in_block_statement949);
                    c=case_statement();

                    state._fsp--;


                    statement = c;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:266:7: r1= repeat
                    {
                    pushFollow(FOLLOW_repeat_in_block_statement970);
                    r1=repeat();

                    state._fsp--;


                    statement = r1;

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:267:7: r2= repeatconstanttimes
                    {
                    pushFollow(FOLLOW_repeatconstanttimes_in_block_statement998);
                    r2=repeatconstanttimes();

                    state._fsp--;


                    statement = r2;

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:268:7: r3= repeatwhile
                    {
                    pushFollow(FOLLOW_repeatwhile_in_block_statement1013);
                    r3=repeatwhile();

                    state._fsp--;


                    statement = r3;

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:269:7: r4= repeatuntil
                    {
                    pushFollow(FOLLOW_repeatuntil_in_block_statement1036);
                    r4=repeatuntil();

                    state._fsp--;


                    statement = r4;

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:270:7: r5= repeatvariablefrom
                    {
                    pushFollow(FOLLOW_repeatvariablefrom_in_block_statement1059);
                    r5=repeatvariablefrom();

                    state._fsp--;


                    statement = r5;

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
    // $ANTLR end "block_statement"



    // $ANTLR start "unary_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:273:1: unary_statement returns [Pair<SpinExpression, ArrayList<SpinExpression>> pair] : ^( UNARYOP e= expression u= Unary2Op (u2= Unary2Op )* ) ;
    public final Pair<SpinExpression, ArrayList<SpinExpression>> unary_statement() throws Exception, RecognitionException {
        Pair<SpinExpression, ArrayList<SpinExpression>> pair = null;


        CommonTree u=null;
        CommonTree u2=null;
        Expression e =null;


        SpinExpression exp; 
                  ArrayList<SpinExpression> list = new ArrayList<SpinExpression>();
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:276:5: ( ^( UNARYOP e= expression u= Unary2Op (u2= Unary2Op )* ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:276:7: ^( UNARYOP e= expression u= Unary2Op (u2= Unary2Op )* )
            {
            match(input,UNARYOP,FOLLOW_UNARYOP_in_unary_statement1102); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_unary_statement1106);
            e=expression();

            state._fsp--;


            u=(CommonTree)match(input,Unary2Op,FOLLOW_Unary2Op_in_unary_statement1110); 

            exp = new SpinExpression(e,u.getText(),currentMethod); list.add(exp);

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:278:7: (u2= Unary2Op )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==Unary2Op) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:278:8: u2= Unary2Op
            	    {
            	    u2=(CommonTree)match(input,Unary2Op,FOLLOW_Unary2Op_in_unary_statement1129); 

            	    exp = new SpinExpression(exp,u2.getText(),currentMethod);
            	          list.add(exp);

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match(input, Token.UP, null); 


            pair = new Pair<SpinExpression, ArrayList<SpinExpression>>(exp,list);

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
    // $ANTLR end "unary_statement"



    // $ANTLR start "not_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:283:1: not_statement returns [Pair<SpinExpression, ArrayList<SpinExpression>> pair] : ^( NOT_OP e= expression u= BoolNotOp (u2= BoolNotOp )* ) ;
    public final Pair<SpinExpression, ArrayList<SpinExpression>> not_statement() throws Exception, RecognitionException {
        Pair<SpinExpression, ArrayList<SpinExpression>> pair = null;


        CommonTree u=null;
        CommonTree u2=null;
        Expression e =null;


        SpinExpression exp; 
                  ArrayList<SpinExpression> list = new ArrayList<SpinExpression>();
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:286:5: ( ^( NOT_OP e= expression u= BoolNotOp (u2= BoolNotOp )* ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:286:7: ^( NOT_OP e= expression u= BoolNotOp (u2= BoolNotOp )* )
            {
            match(input,NOT_OP,FOLLOW_NOT_OP_in_not_statement1181); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_not_statement1185);
            e=expression();

            state._fsp--;


            u=(CommonTree)match(input,BoolNotOp,FOLLOW_BoolNotOp_in_not_statement1189); 

            exp = new SpinExpression(e,u.getText(),currentMethod); list.add(exp);

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:288:7: (u2= BoolNotOp )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==BoolNotOp) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:288:8: u2= BoolNotOp
            	    {
            	    u2=(CommonTree)match(input,BoolNotOp,FOLLOW_BoolNotOp_in_not_statement1208); 

            	    exp = new SpinExpression(exp,u2.getText(),currentMethod);
            	          list.add(exp);

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            match(input, Token.UP, null); 


            pair = new Pair<SpinExpression, ArrayList<SpinExpression>>(exp,list);

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
    // $ANTLR end "not_statement"



    // $ANTLR start "set_bits_high"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:293:1: set_bits_high returns [Expression exp] : ^( SETHIGH id ) ;
    public final Expression set_bits_high() throws Exception, RecognitionException {
        Expression exp = null;


        Expression id12 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:294:5: ( ^( SETHIGH id ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:294:7: ^( SETHIGH id )
            {
            match(input,SETHIGH,FOLLOW_SETHIGH_in_set_bits_high1252); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_id_in_set_bits_high1254);
            id12=id();

            state._fsp--;


            match(input, Token.UP, null); 


            exp = new SpinExpression(id12, "sethigh", currentMethod);

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
    // $ANTLR end "set_bits_high"



    // $ANTLR start "set_bits_low"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:298:1: set_bits_low returns [Expression exp] : ^( SETLOW id ) ;
    public final Expression set_bits_low() throws Exception, RecognitionException {
        Expression exp = null;


        Expression id13 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:299:5: ( ^( SETLOW id ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:299:7: ^( SETLOW id )
            {
            match(input,SETLOW,FOLLOW_SETLOW_in_set_bits_low1292); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_id_in_set_bits_low1294);
            id13=id();

            state._fsp--;


            match(input, Token.UP, null); 


            exp = new SpinExpression(id13, "setlow", currentMethod);

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
    // $ANTLR end "set_bits_low"



    // $ANTLR start "memmove"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:303:1: memmove returns [Statement statement] : ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' ;
    public final Statement memmove() throws Exception, RecognitionException {
        Statement statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;


        VariableType type = VariableType.LONG;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:305:5: ( ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:305:7: ( 'bytemove' | 'wordmove' | 'longmove' ) '(' e1= expression ',' e2= expression ',' e3= expression ')'
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:305:7: ( 'bytemove' | 'wordmove' | 'longmove' )
            int alt16=3;
            switch ( input.LA(1) ) {
            case 73:
                {
                alt16=1;
                }
                break;
            case 101:
                {
                alt16=2;
                }
                break;
            case 82:
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:305:8: 'bytemove'
                    {
                    match(input,73,FOLLOW_73_in_memmove1340); 

                    type = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:306:8: 'wordmove'
                    {
                    match(input,101,FOLLOW_101_in_memmove1350); 

                    type = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:307:8: 'longmove'
                    {
                    match(input,82,FOLLOW_82_in_memmove1360); 

                    type = VariableType.LONG;

                    }
                    break;

            }


            match(input,60,FOLLOW_60_in_memmove1370); 

            pushFollow(FOLLOW_expression_in_memmove1373);
            e1=expression();

            state._fsp--;


            match(input,63,FOLLOW_63_in_memmove1374); 

            pushFollow(FOLLOW_expression_in_memmove1377);
            e2=expression();

            state._fsp--;


            match(input,63,FOLLOW_63_in_memmove1378); 

            pushFollow(FOLLOW_expression_in_memmove1381);
            e3=expression();

            state._fsp--;


            match(input,61,FOLLOW_61_in_memmove1382); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:313:1: memfill returns [Statement statement] : ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' ;
    public final Statement memfill() throws Exception, RecognitionException {
        Statement statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;


        VariableType type = VariableType.LONG;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:315:5: ( ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:315:7: ( 'bytefill' | 'wordfill' | 'longfill' ) '(' e1= expression ',' e2= expression ',' e3= expression ')'
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:315:7: ( 'bytefill' | 'wordfill' | 'longfill' )
            int alt17=3;
            switch ( input.LA(1) ) {
            case 72:
                {
                alt17=1;
                }
                break;
            case 100:
                {
                alt17=2;
                }
                break;
            case 81:
                {
                alt17=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:315:8: 'bytefill'
                    {
                    match(input,72,FOLLOW_72_in_memfill1433); 

                    type = VariableType.BYTE;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:316:8: 'wordfill'
                    {
                    match(input,100,FOLLOW_100_in_memfill1443); 

                    type = VariableType.WORD;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:317:8: 'longfill'
                    {
                    match(input,81,FOLLOW_81_in_memfill1453); 

                    type = VariableType.LONG;

                    }
                    break;

            }


            match(input,60,FOLLOW_60_in_memfill1463); 

            pushFollow(FOLLOW_expression_in_memfill1466);
            e1=expression();

            state._fsp--;


            match(input,63,FOLLOW_63_in_memfill1467); 

            pushFollow(FOLLOW_expression_in_memfill1470);
            e2=expression();

            state._fsp--;


            match(input,63,FOLLOW_63_in_memfill1471); 

            pushFollow(FOLLOW_expression_in_memfill1474);
            e3=expression();

            state._fsp--;


            match(input,61,FOLLOW_61_in_memfill1475); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:323:1: if_statement returns [IfElse statement] : 'if' r= expression (i= block )? ( 'elseif' s= expression (ei= block )? )* ( 'else' (e= block )? )? ;
    public final IfElse if_statement() throws Exception, RecognitionException {
        IfElse statement = null;


        Expression r =null;

        Block i =null;

        Expression s =null;

        Block ei =null;

        Block e =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:324:5: ( 'if' r= expression (i= block )? ( 'elseif' s= expression (ei= block )? )* ( 'else' (e= block )? )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:324:8: 'if' r= expression (i= block )? ( 'elseif' s= expression (ei= block )? )* ( 'else' (e= block )? )?
            {
            match(input,79,FOLLOW_79_in_if_statement1520); 

            pushFollow(FOLLOW_expression_in_if_statement1525);
            r=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:325:12: (i= block )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==Indent) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:325:12: i= block
                    {
                    pushFollow(FOLLOW_block_in_if_statement1540);
                    i=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new IfElse(r, i, currentMethod);

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:326:9: ( 'elseif' s= expression (ei= block )? )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==77) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:326:11: 'elseif' s= expression (ei= block )?
            	    {
            	    match(input,77,FOLLOW_77_in_if_statement1557); 

            	    pushFollow(FOLLOW_expression_in_if_statement1562);
            	    s=expression();

            	    state._fsp--;


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:327:12: (ei= block )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==Indent) ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:327:12: ei= block
            	            {
            	            pushFollow(FOLLOW_block_in_if_statement1576);
            	            ei=block();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    statement.setExp(s); statement.setBlock(ei);

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:328:9: ( 'else' (e= block )? )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==76) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:328:11: 'else' (e= block )?
                    {
                    match(input,76,FOLLOW_76_in_if_statement1593); 

                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:329:12: (e= block )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==Indent) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:329:12: e= block
                            {
                            pushFollow(FOLLOW_block_in_if_statement1608);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:332:1: case_statement returns [Case statement] : 'case' expression Indent (c= casechoice | NEWLINE )* Dedent ;
    public final Case case_statement() throws Exception, RecognitionException {
        Case statement = null;


        Pair<CaseItem, Block> c =null;

        Expression expression14 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:333:5: ( 'case' expression Indent (c= casechoice | NEWLINE )* Dedent )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:333:9: 'case' expression Indent (c= casechoice | NEWLINE )* Dedent
            {
            match(input,74,FOLLOW_74_in_case_statement1644); 

            pushFollow(FOLLOW_expression_in_case_statement1646);
            expression14=expression();

            state._fsp--;


            statement = new Case(expression14, currentMethod);

            match(input,Indent,FOLLOW_Indent_in_case_statement1650); 

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:334:9: (c= casechoice | NEWLINE )*
            loop23:
            do {
                int alt23=3;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0 >= ASSIGNMENT && LA23_0 <= BINARY)||LA23_0==BitAndOp||LA23_0==BoolAndOp||(LA23_0 >= BoolOp && LA23_0 <= BoolOrOp)||LA23_0==CHARACTER||(LA23_0 >= FLOAT && LA23_0 <= HEXADECIMAL)||(LA23_0 >= ID && LA23_0 <= INT)||LA23_0==LimitMaxOp||LA23_0==MultOp||(LA23_0 >= Multiply && LA23_0 <= NEGATION)||LA23_0==NOT_OP||LA23_0==POST||(LA23_0 >= PRE && LA23_0 <= QUATERNARY)||LA23_0==RAND||LA23_0==ShiftOp||(LA23_0 >= True && LA23_0 <= UNARYOP)||(LA23_0 >= Unary2Op && LA23_0 <= UnaryOp)||LA23_0==62||LA23_0==64||(LA23_0 >= 83 && LA23_0 <= 86)||(LA23_0 >= 91 && LA23_0 <= 93)||(LA23_0 >= 103 && LA23_0 <= 104)) ) {
                    alt23=1;
                }
                else if ( (LA23_0==NEWLINE) ) {
                    alt23=2;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:334:10: c= casechoice
            	    {
            	    pushFollow(FOLLOW_casechoice_in_case_statement1663);
            	    c=casechoice();

            	    state._fsp--;


            	    statement.addCase(c.car(), c.cdr());

            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:334:81: NEWLINE
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_case_statement1674); 

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            match(input,Dedent,FOLLOW_Dedent_in_case_statement1686); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:338:1: casechoice returns [Pair<CaseItem, Block> pair] : e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block ;
    public final Pair<CaseItem, Block> casechoice() throws Exception, RecognitionException {
        Pair<CaseItem, Block> pair = null;


        Expression e =null;

        Expression e2 =null;

        Block block15 =null;


        CaseItem item;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:340:5: (e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:340:7: e= expression ( Range e2= expression )? ( ',' e= expression ( Range e2= expression )? )* ':' block
            {
            pushFollow(FOLLOW_expression_in_casechoice1719);
            e=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:340:20: ( Range e2= expression )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==Range) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:340:21: Range e2= expression
                    {
                    match(input,Range,FOLLOW_Range_in_casechoice1722); 

                    pushFollow(FOLLOW_expression_in_casechoice1726);
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

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:346:7: ( ',' e= expression ( Range e2= expression )? )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==63) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:346:8: ',' e= expression ( Range e2= expression )?
            	    {
            	    match(input,63,FOLLOW_63_in_casechoice1747); 

            	    pushFollow(FOLLOW_expression_in_casechoice1751);
            	    e=expression();

            	    state._fsp--;


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:346:25: ( Range e2= expression )?
            	    int alt25=2;
            	    int LA25_0 = input.LA(1);

            	    if ( (LA25_0==Range) ) {
            	        alt25=1;
            	    }
            	    switch (alt25) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:346:26: Range e2= expression
            	            {
            	            match(input,Range,FOLLOW_Range_in_casechoice1754); 

            	            pushFollow(FOLLOW_expression_in_casechoice1758);
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
            	    break loop26;
                }
            } while (true);


            match(input,66,FOLLOW_66_in_casechoice1771); 

            pushFollow(FOLLOW_block_in_casechoice1773);
            block15=block();

            state._fsp--;


            pair = new Pair(item, block15);

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:358:1: repeatvariablefrom returns [RepeatVariableFrom statement] : 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )? ;
    public final RepeatVariableFrom repeatvariablefrom() throws Exception, RecognitionException {
        RepeatVariableFrom statement = null;


        CommonTree ID16=null;
        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;

        Block block17 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:5: ( 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:7: 'repeat' ID 'from' e1= expression 'to' e2= expression ( 'step' e3= expression )? ( block )?
            {
            match(input,88,FOLLOW_88_in_repeatvariablefrom1806); 

            ID16=(CommonTree)match(input,ID,FOLLOW_ID_in_repeatvariablefrom1808); 

            match(input,78,FOLLOW_78_in_repeatvariablefrom1810); 

            pushFollow(FOLLOW_expression_in_repeatvariablefrom1814);
            e1=expression();

            state._fsp--;


            match(input,94,FOLLOW_94_in_repeatvariablefrom1816); 

            pushFollow(FOLLOW_expression_in_repeatvariablefrom1822);
            e2=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:61: ( 'step' e3= expression )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==90) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:62: 'step' e3= expression
                    {
                    match(input,90,FOLLOW_90_in_repeatvariablefrom1825); 

                    pushFollow(FOLLOW_expression_in_repeatvariablefrom1829);
                    e3=expression();

                    state._fsp--;


                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:85: ( block )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==Indent) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:359:85: block
                    {
                    pushFollow(FOLLOW_block_in_repeatvariablefrom1833);
                    block17=block();

                    state._fsp--;


                    }
                    break;

            }


            if (e3 == null){
                      statement = new RepeatVariableFrom(e1, e2, ID16.getText(), block17, currentMethod);
                   }else{
                      statement = new RepeatVariableFrom(e1, e2, ID16.getText(), e3, block17, currentMethod);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:369:1: repeatconstanttimes returns [RepeatConstantTimes statement] : 'repeat' e= expression ( block )? ;
    public final RepeatConstantTimes repeatconstanttimes() throws Exception, RecognitionException {
        RepeatConstantTimes statement = null;


        Expression e =null;

        Block block18 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:370:5: ( 'repeat' e= expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:370:7: 'repeat' e= expression ( block )?
            {
            match(input,88,FOLLOW_88_in_repeatconstanttimes1877); 

            pushFollow(FOLLOW_expression_in_repeatconstanttimes1881);
            e=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:370:29: ( block )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==Indent) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:370:29: block
                    {
                    pushFollow(FOLLOW_block_in_repeatconstanttimes1883);
                    block18=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatConstantTimes(e, currentMethod);
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
    // $ANTLR end "repeatconstanttimes"



    // $ANTLR start "repeatuntil"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:375:1: repeatuntil returns [RepeatUntil statement] : 'repeat' 'until' e= expression ( block )? ;
    public final RepeatUntil repeatuntil() throws Exception, RecognitionException {
        RepeatUntil statement = null;


        Expression e =null;

        Block block19 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:376:5: ( 'repeat' 'until' e= expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:376:7: 'repeat' 'until' e= expression ( block )?
            {
            match(input,88,FOLLOW_88_in_repeatuntil1922); 

            match(input,95,FOLLOW_95_in_repeatuntil1924); 

            pushFollow(FOLLOW_expression_in_repeatuntil1928);
            e=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:376:37: ( block )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==Indent) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:376:37: block
                    {
                    pushFollow(FOLLOW_block_in_repeatuntil1930);
                    block19=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatUntil(e, currentMethod);
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
    // $ANTLR end "repeatuntil"



    // $ANTLR start "repeatwhile"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:381:1: repeatwhile returns [RepeatWhile statement] : 'repeat' 'while' e= expression ( block )? ;
    public final RepeatWhile repeatwhile() throws Exception, RecognitionException {
        RepeatWhile statement = null;


        Expression e =null;

        Block block20 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:382:5: ( 'repeat' 'while' e= expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:382:7: 'repeat' 'while' e= expression ( block )?
            {
            match(input,88,FOLLOW_88_in_repeatwhile1965); 

            match(input,98,FOLLOW_98_in_repeatwhile1967); 

            pushFollow(FOLLOW_expression_in_repeatwhile1971);
            e=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:382:37: ( block )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==Indent) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:382:37: block
                    {
                    pushFollow(FOLLOW_block_in_repeatwhile1973);
                    block20=block();

                    state._fsp--;


                    }
                    break;

            }


            statement = new RepeatWhile(e, currentMethod);
                   statement.setBlock(block20);

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:387:1: repeat returns [Repeat statement] : 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )? ;
    public final Repeat repeat() throws Exception, RecognitionException {
        Repeat statement = null;


        Expression e1 =null;

        Expression e2 =null;

        Block block21 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:5: ( 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:7: 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )?
            {
            match(input,88,FOLLOW_88_in_repeat2012); 

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:16: ( block | NEWLINE )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Indent) ) {
                alt32=1;
            }
            else if ( (LA32_0==NEWLINE) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:17: block
                    {
                    pushFollow(FOLLOW_block_in_repeat2015);
                    block21=block();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:23: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat2017); 

                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:32: ( NEWLINE ( 'until' e1= expression | 'while' e2= expression ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==NEWLINE) ) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==95||LA34_1==98) ) {
                    alt34=1;
                }
            }
            switch (alt34) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:33: NEWLINE ( 'until' e1= expression | 'while' e2= expression )
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat2021); 

                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:40: ( 'until' e1= expression | 'while' e2= expression )
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==95) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==98) ) {
                        alt33=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;

                    }
                    switch (alt33) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:41: 'until' e1= expression
                            {
                            match(input,95,FOLLOW_95_in_repeat2023); 

                            pushFollow(FOLLOW_expression_in_repeat2027);
                            e1=expression();

                            state._fsp--;


                            }
                            break;
                        case 2 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:389:63: 'while' e2= expression
                            {
                            match(input,98,FOLLOW_98_in_repeat2029); 

                            pushFollow(FOLLOW_expression_in_repeat2033);
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
                  statement.setBlock(block21);

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:401:1: array returns [Expression exp] : '[' expression ']' ;
    public final Expression array() throws Exception, RecognitionException {
        Expression exp = null;


        Expression expression22 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:402:5: ( '[' expression ']' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:402:7: '[' expression ']'
            {
            match(input,69,FOLLOW_69_in_array2076); 

            pushFollow(FOLLOW_expression_in_array2077);
            expression22=expression();

            state._fsp--;


            match(input,70,FOLLOW_70_in_array2078); 

            exp = expression22;

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:423:1: expression returns [Expression exp] : ( ^( POST IncOp e= expression ) | ^( PRE IncOp e= expression ) | ^( NEGATION e= expression ) |u= unary_statement |v= not_statement | ^(o= ( Unary2Op ) e= expression ) | ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression ) | ^( Assign e3= expression e4= expression ) |n= number |i= ident );
    public final Expression expression() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree o=null;
        CommonTree IncOp23=null;
        CommonTree IncOp24=null;
        CommonTree Assign25=null;
        Expression e =null;

        Pair<SpinExpression, ArrayList<SpinExpression>> u =null;

        Pair<SpinExpression, ArrayList<SpinExpression>> v =null;

        Expression e1 =null;

        Expression e2 =null;

        Expression e3 =null;

        Expression e4 =null;

        SpinNumber n =null;

        Expression i =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:424:5: ( ^( POST IncOp e= expression ) | ^( PRE IncOp e= expression ) | ^( NEGATION e= expression ) |u= unary_statement |v= not_statement | ^(o= ( Unary2Op ) e= expression ) | ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression ) | ^( Assign e3= expression e4= expression ) |n= number |i= ident )
            int alt35=10;
            switch ( input.LA(1) ) {
            case POST:
                {
                alt35=1;
                }
                break;
            case PRE:
                {
                alt35=2;
                }
                break;
            case NEGATION:
                {
                alt35=3;
                }
                break;
            case UNARYOP:
                {
                alt35=4;
                }
                break;
            case NOT_OP:
                {
                alt35=5;
                }
                break;
            case Unary2Op:
                {
                alt35=6;
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
            case 62:
            case 64:
                {
                alt35=7;
                }
                break;
            case Assign:
                {
                alt35=8;
                }
                break;
            case BINARY:
            case FLOAT:
            case HEXADECIMAL:
            case INT:
            case QUATERNARY:
                {
                alt35=9;
                }
                break;
            case ASSIGNMENT:
            case CHARACTER:
            case False:
            case ID:
            case RAND:
            case True:
            case UnaryOp:
            case 83:
            case 84:
            case 85:
            case 86:
            case 91:
            case 92:
            case 93:
            case 103:
            case 104:
                {
                alt35=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }

            switch (alt35) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:424:7: ^( POST IncOp e= expression )
                    {
                    match(input,POST,FOLLOW_POST_in_expression2117); 

                    match(input, Token.DOWN, null); 
                    IncOp23=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_expression2119); 

                    pushFollow(FOLLOW_expression_in_expression2123);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, "post"+IncOp23.getText(), currentMethod);

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:425:7: ^( PRE IncOp e= expression )
                    {
                    match(input,PRE,FOLLOW_PRE_in_expression2135); 

                    match(input, Token.DOWN, null); 
                    IncOp24=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_expression2137); 

                    pushFollow(FOLLOW_expression_in_expression2141);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, "pre"+IncOp24.getText(), currentMethod);

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:426:7: ^( NEGATION e= expression )
                    {
                    match(input,NEGATION,FOLLOW_NEGATION_in_expression2153); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2157);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new Negation(e, currentMethod);

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:428:7: u= unary_statement
                    {
                    pushFollow(FOLLOW_unary_statement_in_expression2175);
                    u=unary_statement();

                    state._fsp--;


                    exp = u.car();

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:429:7: v= not_statement
                    {
                    pushFollow(FOLLOW_not_statement_in_expression2197);
                    v=not_statement();

                    state._fsp--;


                    exp = v.car();

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:430:7: ^(o= ( Unary2Op ) e= expression )
                    {
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:430:11: ( Unary2Op )
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:430:12: Unary2Op
                    {
                    match(input,Unary2Op,FOLLOW_Unary2Op_in_expression2223); 

                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2228);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e, o.getText(), currentMethod);

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:432:7: ^(o= ( '+' | '-' | ShiftOp | BitAndOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolAndOp | BoolOrOp ) e1= expression e2= expression )
                    {
                    o=(CommonTree)input.LT(1);

                    if ( input.LA(1)==BitAndOp||input.LA(1)==BoolAndOp||(input.LA(1) >= BoolOp && input.LA(1) <= BoolOrOp)||input.LA(1)==LimitMaxOp||input.LA(1)==MultOp||input.LA(1)==Multiply||input.LA(1)==ShiftOp||input.LA(1)==62||input.LA(1)==64 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2281);
                    e1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2285);
                    e2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    exp = new SpinExpression(e1, e2, o.getText(), currentMethod);

                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:434:7: ^( Assign e3= expression e4= expression )
                    {
                    Assign25=(CommonTree)match(input,Assign,FOLLOW_Assign_in_expression2298); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2302);
                    e3=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2306);
                    e4=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     if (e3 instanceof MethodCall) throw new SyntaxError("expected variable");
                          if (Assign25.getText().equals(":=")){
                            exp = new Assignment(e3, e4, currentMethod); 
                          }else{
                            exp = new SpinExpression(e3, e4, Assign25.getText(), currentMethod);
                          }

                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:441:7: n= number
                    {
                    pushFollow(FOLLOW_number_in_expression2325);
                    n=number();

                    state._fsp--;


                    if (n.isfloat){
                            exp = new AnonVariable(n.getValue(),true);
                          }else{
                            exp = new AnonVariable(n.getValue());
                          }

                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:447:7: i= ident
                    {
                    pushFollow(FOLLOW_ident_in_expression2344);
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



    // $ANTLR start "setbits"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:451:1: setbits returns [Expression exp] : id (o= ( '~' | '~~' | '?' ) )? ;
    public final Expression setbits() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree o=null;
        Expression id26 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:452:5: ( id (o= ( '~' | '~~' | '?' ) )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:452:7: id (o= ( '~' | '~~' | '?' ) )?
            {
            pushFollow(FOLLOW_id_in_setbits2380);
            id26=id();

            state._fsp--;


            exp = id26;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:453:8: (o= ( '~' | '~~' | '?' ) )?
            int alt37=2;
            switch ( input.LA(1) ) {
                case 103:
                    {
                    int LA37_1 = input.LA(2);

                    if ( (LA37_1==UP) ) {
                        alt37=1;
                    }
                    else if ( ((LA37_1 >= ASSIGNMENT && LA37_1 <= BINARY)||LA37_1==BitAndOp||(LA37_1 >= BoolAndOp && LA37_1 <= BoolOrOp)||(LA37_1 >= CHARACTER && LA37_1 <= Dedent)||(LA37_1 >= FLOAT && LA37_1 <= HEXADECIMAL)||(LA37_1 >= ID && LA37_1 <= INT)||(LA37_1 >= Indent && LA37_1 <= LimitMaxOp)||LA37_1==MultOp||(LA37_1 >= Multiply && LA37_1 <= NOT_OP)||(LA37_1 >= POST && LA37_1 <= QUATERNARY)||LA37_1==RAND||(LA37_1 >= Range && LA37_1 <= SETLOW)||LA37_1==ShiftOp||(LA37_1 >= True && LA37_1 <= UNARYOP)||(LA37_1 >= Unary2Op && LA37_1 <= UnaryOp)||(LA37_1 >= 61 && LA37_1 <= 64)||LA37_1==66||LA37_1==70||(LA37_1 >= 72 && LA37_1 <= 74)||(LA37_1 >= 76 && LA37_1 <= 77)||LA37_1==79||(LA37_1 >= 81 && LA37_1 <= 86)||(LA37_1 >= 88 && LA37_1 <= 94)||LA37_1==97||(LA37_1 >= 100 && LA37_1 <= 101)||(LA37_1 >= 103 && LA37_1 <= 104)||(LA37_1 >= 109 && LA37_1 <= 110)) ) {
                        alt37=1;
                    }
                    }
                    break;
                case 104:
                    {
                    int LA37_2 = input.LA(2);

                    if ( ((LA37_2 >= UP && LA37_2 <= BINARY)||LA37_2==BitAndOp||(LA37_2 >= BoolAndOp && LA37_2 <= BoolOrOp)||(LA37_2 >= CHARACTER && LA37_2 <= Dedent)||(LA37_2 >= FLOAT && LA37_2 <= HEXADECIMAL)||(LA37_2 >= ID && LA37_2 <= INT)||(LA37_2 >= Indent && LA37_2 <= LimitMaxOp)||LA37_2==MultOp||(LA37_2 >= Multiply && LA37_2 <= NOT_OP)||(LA37_2 >= POST && LA37_2 <= QUATERNARY)||LA37_2==RAND||(LA37_2 >= Range && LA37_2 <= SETLOW)||LA37_2==ShiftOp||(LA37_2 >= True && LA37_2 <= UNARYOP)||(LA37_2 >= Unary2Op && LA37_2 <= UnaryOp)||(LA37_2 >= 61 && LA37_2 <= 64)||LA37_2==66||LA37_2==70||(LA37_2 >= 72 && LA37_2 <= 74)||(LA37_2 >= 76 && LA37_2 <= 77)||LA37_2==79||(LA37_2 >= 81 && LA37_2 <= 86)||(LA37_2 >= 88 && LA37_2 <= 94)||LA37_2==97||(LA37_2 >= 100 && LA37_2 <= 101)||(LA37_2 >= 103 && LA37_2 <= 104)||(LA37_2 >= 109 && LA37_2 <= 110)) ) {
                        alt37=1;
                    }
                    }
                    break;
                case 68:
                    {
                    alt37=1;
                    }
                    break;
            }

            switch (alt37) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:453:8: o= ( '~' | '~~' | '?' )
                    {
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:453:9: ( '~' | '~~' | '?' )
                    int alt36=3;
                    switch ( input.LA(1) ) {
                    case 103:
                        {
                        alt36=1;
                        }
                        break;
                    case 104:
                        {
                        alt36=2;
                        }
                        break;
                    case 68:
                        {
                        alt36=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 36, 0, input);

                        throw nvae;

                    }

                    switch (alt36) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:453:10: '~'
                            {
                            match(input,103,FOLLOW_103_in_setbits2394); 

                            exp = new SpinExpression(exp, "setlow", currentMethod);

                            }
                            break;
                        case 2 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:454:10: '~~'
                            {
                            match(input,104,FOLLOW_104_in_setbits2406); 

                            exp = new SpinExpression(exp, "sethigh", currentMethod);

                            }
                            break;
                        case 3 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:455:10: '?'
                            {
                            match(input,68,FOLLOW_68_in_setbits2418); 

                            exp = new RandomExpression(id26,currentMethod);

                            }
                            break;

                    }


                    }
                    break;

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
    // $ANTLR end "setbits"



    // $ANTLR start "ident"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:459:1: ident returns [Expression exp] : ( True | False |e1= objectConstant |e2= objMethodCall |e3= methodCall |e4= string |e5= strcomp |e6= character |e7= strsize |e8= lookup |e9= lookdown |e11= rand |e12= setbits |e13= assignment |a= ( '~' | '~~' | UnaryOp ) sb= setbits );
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

        RandomExpression e11 =null;

        Expression e12 =null;

        Expression e13 =null;

        Expression sb =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:460:5: ( True | False |e1= objectConstant |e2= objMethodCall |e3= methodCall |e4= string |e5= strcomp |e6= character |e7= strsize |e8= lookup |e9= lookdown |e11= rand |e12= setbits |e13= assignment |a= ( '~' | '~~' | UnaryOp ) sb= setbits )
            int alt38=15;
            switch ( input.LA(1) ) {
            case True:
                {
                alt38=1;
                }
                break;
            case False:
                {
                alt38=2;
                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case 59:
                    {
                    alt38=3;
                    }
                    break;
                case 65:
                    {
                    alt38=4;
                    }
                    break;
                case 60:
                    {
                    alt38=5;
                    }
                    break;
                case UP:
                case ASSIGNMENT:
                case Assign:
                case BINARY:
                case BitAndOp:
                case BoolAndOp:
                case BoolNotOp:
                case BoolOp:
                case BoolOrOp:
                case CHARACTER:
                case Dedent:
                case FLOAT:
                case False:
                case HEXADECIMAL:
                case ID:
                case INT:
                case Indent:
                case LimitMaxOp:
                case MultOp:
                case Multiply:
                case NEGATION:
                case NEWLINE:
                case NOT_OP:
                case POST:
                case POSTINCREMENT:
                case PRE:
                case QUATERNARY:
                case RAND:
                case Range:
                case SETHIGH:
                case SETLOW:
                case ShiftOp:
                case True:
                case UNARYOP:
                case Unary2Op:
                case UnaryOp:
                case 61:
                case 62:
                case 63:
                case 64:
                case 66:
                case 68:
                case 69:
                case 70:
                case 72:
                case 73:
                case 74:
                case 76:
                case 77:
                case 79:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 97:
                case 100:
                case 101:
                case 103:
                case 104:
                case 109:
                case 110:
                    {
                    alt38=13;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 3, input);

                    throw nvae;

                }

                }
                break;
            case 92:
                {
                alt38=6;
                }
                break;
            case 91:
                {
                alt38=7;
                }
                break;
            case CHARACTER:
                {
                alt38=8;
                }
                break;
            case 93:
                {
                alt38=9;
                }
                break;
            case 85:
            case 86:
                {
                alt38=10;
                }
                break;
            case 83:
            case 84:
                {
                alt38=11;
                }
                break;
            case RAND:
                {
                alt38=12;
                }
                break;
            case ASSIGNMENT:
                {
                alt38=14;
                }
                break;
            case UnaryOp:
            case 103:
            case 104:
                {
                alt38=15;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }

            switch (alt38) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:460:7: True
                    {
                    match(input,True,FOLLOW_True_in_ident2453); 

                    exp = Variable.TRUE;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:461:7: False
                    {
                    match(input,False,FOLLOW_False_in_ident2474); 

                    exp = Variable.FALSE;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:462:7: e1= objectConstant
                    {
                    pushFollow(FOLLOW_objectConstant_in_ident2496);
                    e1=objectConstant();

                    state._fsp--;


                    exp = e1; 

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:463:7: e2= objMethodCall
                    {
                    pushFollow(FOLLOW_objMethodCall_in_ident2507);
                    e2=objMethodCall();

                    state._fsp--;


                    exp = e2; 

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:464:7: e3= methodCall
                    {
                    pushFollow(FOLLOW_methodCall_in_ident2519);
                    e3=methodCall();

                    state._fsp--;


                    exp = e3; 

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:465:7: e4= string
                    {
                    pushFollow(FOLLOW_string_in_ident2534);
                    e4=string();

                    state._fsp--;


                    exp = e4; 

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:466:7: e5= strcomp
                    {
                    pushFollow(FOLLOW_strcomp_in_ident2553);
                    e5=strcomp();

                    state._fsp--;


                    exp = e5; 

                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:467:7: e6= character
                    {
                    pushFollow(FOLLOW_character_in_ident2572);
                    e6=character();

                    state._fsp--;


                    exp = e6; 

                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:468:7: e7= strsize
                    {
                    pushFollow(FOLLOW_strsize_in_ident2588);
                    e7=strsize();

                    state._fsp--;


                    exp = e7; 

                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:469:7: e8= lookup
                    {
                    pushFollow(FOLLOW_lookup_in_ident2606);
                    e8=lookup();

                    state._fsp--;


                    exp = e8; 

                    }
                    break;
                case 11 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:470:7: e9= lookdown
                    {
                    pushFollow(FOLLOW_lookdown_in_ident2625);
                    e9=lookdown();

                    state._fsp--;


                    exp = e9; 

                    }
                    break;
                case 12 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:471:7: e11= rand
                    {
                    pushFollow(FOLLOW_rand_in_ident2642);
                    e11=rand();

                    state._fsp--;


                    exp = e11;

                    }
                    break;
                case 13 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:472:7: e12= setbits
                    {
                    pushFollow(FOLLOW_setbits_in_ident2662);
                    e12=setbits();

                    state._fsp--;


                    exp = e12;

                    }
                    break;
                case 14 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:473:7: e13= assignment
                    {
                    pushFollow(FOLLOW_assignment_in_ident2679);
                    e13=assignment();

                    state._fsp--;


                    exp = e13;

                    }
                    break;
                case 15 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:478:7: a= ( '~' | '~~' | UnaryOp ) sb= setbits
                    {
                    a=(CommonTree)input.LT(1);

                    if ( input.LA(1)==UnaryOp||(input.LA(1) >= 103 && input.LA(1) <= 104) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_setbits_in_ident2722);
                    sb=setbits();

                    state._fsp--;


                    if (a.getText().equals("~")){
                              Debug.debug("its a sign extend");
                              //code for '~' in SpinTokenizer
                              exp = new SignExtend(sb,-59);
                           }else if (a.getText().equals("~~")){
                              Debug.debug("its a sign extend");
                              //code for '~~' in SpinTokenizer
                              exp = new SignExtend(sb,-60);
                           }else if (a.getText().equals("@")){
                              Debug.debug("its an address");
                              exp = new Address(sb, program.getMemory());
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:495:1: id returns [Expression exp] : ID ( array )? ;
    public final Expression id() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree ID27=null;
        Expression array28 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:496:5: ( ID ( array )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:496:7: ID ( array )?
            {
            ID27=(CommonTree)match(input,ID,FOLLOW_ID_in_id2765); 

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:496:10: ( array )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==69) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:496:10: array
                    {
                    pushFollow(FOLLOW_array_in_id2767);
                    array28=array();

                    state._fsp--;


                    }
                    break;

            }


            if (program.isVariable(ID27.getText())){
                      //check if its an array access or not
                      if (array28 != null){
                          Debug.debug("its an array");
                          exp = new ArrayAccess(program.getVariable(ID27.getText()), array28, program.getMemory());
                      }else{
                          Debug.debug("its not an array");
                          exp = program.getVariable(ID27.getText());
                      }
                      //now check if there is a sign extend
                      
                   }   
                   else if (program.isConstant(ID27.getText())){exp = program.getConstant(ID27.getText());}
                   else if (currentMethod.isVariable(ID27.getText())){
                      //check for an array access
                      if (array28 != null){
                          exp = new ArrayAccess(ID27.getText(), array28);
                      }else{
                          exp = new VariableExpression(ID27.getText());
                      }
                      
                  }else if (program.isMethod(ID27.getText())){exp = new MethodCall(ID27.getText(),null, program,currentMethod);}
                  

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:522:1: assignment returns [Expression exp] : ^( ASSIGNMENT Assign d= id e= expression ) ;
    public final Expression assignment() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree Assign29=null;
        Expression d =null;

        Expression e =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:523:5: ( ^( ASSIGNMENT Assign d= id e= expression ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:523:7: ^( ASSIGNMENT Assign d= id e= expression )
            {
            match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment2805); 

            match(input, Token.DOWN, null); 
            Assign29=(CommonTree)match(input,Assign,FOLLOW_Assign_in_assignment2807); 

            pushFollow(FOLLOW_id_in_assignment2811);
            d=id();

            state._fsp--;


            pushFollow(FOLLOW_expression_in_assignment2815);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 


             if (d instanceof MethodCall) throw new SyntaxError("expected variable");
                  if (Assign29.getText().equals(":=")){
                    exp = new Assignment(d, e, currentMethod); 
                  }else{
                    exp = new SpinExpression(d, e, Assign29.getText(), currentMethod);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:533:1: postincrement returns [Expression exp] : ^( POSTINCREMENT IncOp id ) ;
    public final Expression postincrement() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree IncOp31=null;
        Expression id30 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:534:5: ( ^( POSTINCREMENT IncOp id ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:534:7: ^( POSTINCREMENT IncOp id )
            {
            match(input,POSTINCREMENT,FOLLOW_POSTINCREMENT_in_postincrement2858); 

            match(input, Token.DOWN, null); 
            IncOp31=(CommonTree)match(input,IncOp,FOLLOW_IncOp_in_postincrement2860); 

            pushFollow(FOLLOW_id_in_postincrement2862);
            id30=id();

            state._fsp--;


            match(input, Token.UP, null); 


            exp = new SpinExpression(id30, "post"+IncOp31.getText(), currentMethod); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:538:1: rand returns [RandomExpression exp] : ^( RAND id ) ;
    public final RandomExpression rand() throws Exception, RecognitionException {
        RandomExpression exp = null;


        Expression id32 =null;


        Expression ex = null;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:540:5: ( ^( RAND id ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:541:7: ^( RAND id )
            {
            match(input,RAND,FOLLOW_RAND_in_rand2914); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_id_in_rand2916);
            id32=id();

            state._fsp--;


            match(input, Token.UP, null); 


            if (id32 instanceof MethodCall){throw new SyntaxError("Cannot call ? on method");}
                   exp = new RandomExpression(id32,currentMethod);

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



    // $ANTLR start "lookup"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:547:1: lookup returns [Expression exp] : s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')' ;
    public final Expression lookup() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree s=null;
        Expression e =null;

        SpinList r =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:548:5: (s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:548:7: s= ( 'lookup' | 'lookupz' ) '(' e= expression ':' r= rangelist ')'
            {
            s=(CommonTree)input.LT(1);

            if ( (input.LA(1) >= 85 && input.LA(1) <= 86) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,60,FOLLOW_60_in_lookup2965); 

            pushFollow(FOLLOW_expression_in_lookup2968);
            e=expression();

            state._fsp--;


            match(input,66,FOLLOW_66_in_lookup2969); 

            pushFollow(FOLLOW_rangelist_in_lookup2973);
            r=rangelist();

            state._fsp--;


            match(input,61,FOLLOW_61_in_lookup2974); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:553:1: lookdown returns [Expression exp] : s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')' ;
    public final Expression lookdown() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree s=null;
        Expression e =null;

        SpinList r =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:554:5: (s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:554:7: s= ( 'lookdown' | 'lookdownz' ) '(' e= expression ':' r= rangelist ')'
            {
            s=(CommonTree)input.LT(1);

            if ( (input.LA(1) >= 83 && input.LA(1) <= 84) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,60,FOLLOW_60_in_lookdown3019); 

            pushFollow(FOLLOW_expression_in_lookdown3022);
            e=expression();

            state._fsp--;


            match(input,66,FOLLOW_66_in_lookdown3023); 

            pushFollow(FOLLOW_rangelist_in_lookdown3027);
            r=rangelist();

            state._fsp--;


            match(input,61,FOLLOW_61_in_lookdown3028); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:559:1: rangelist returns [SpinList list] : r1= rangeitem ( ',' r2= rangeitem )* ;
    public final SpinList rangelist() throws Exception, RecognitionException {
        SpinList list = null;


        SpinListItem r1 =null;

        SpinListItem r2 =null;


        list = new SpinList();
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:561:5: (r1= rangeitem ( ',' r2= rangeitem )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:561:7: r1= rangeitem ( ',' r2= rangeitem )*
            {
            pushFollow(FOLLOW_rangeitem_in_rangelist3077);
            r1=rangeitem();

            state._fsp--;


            list.addItem(r1);

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:562:7: ( ',' r2= rangeitem )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==63) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:562:8: ',' r2= rangeitem
            	    {
            	    match(input,63,FOLLOW_63_in_rangelist3088); 

            	    pushFollow(FOLLOW_rangeitem_in_rangelist3091);
            	    r2=rangeitem();

            	    state._fsp--;


            	    list.addItem(r2);

            	    }
            	    break;

            	default :
            	    break loop40;
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:565:1: rangeitem returns [SpinListItem item] : e1= expression ( '..' e2= expression )? ;
    public final SpinListItem rangeitem() throws Exception, RecognitionException {
        SpinListItem item = null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:566:5: (e1= expression ( '..' e2= expression )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:566:7: e1= expression ( '..' e2= expression )?
            {
            pushFollow(FOLLOW_expression_in_rangeitem3126);
            e1=expression();

            state._fsp--;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:566:21: ( '..' e2= expression )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==Range) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:566:22: '..' e2= expression
                    {
                    match(input,Range,FOLLOW_Range_in_rangeitem3129); 

                    pushFollow(FOLLOW_expression_in_rangeitem3132);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:574:1: character returns [Expression exp] : c= CHARACTER ;
    public final Expression character() throws Exception, RecognitionException {
        Expression exp = null;


        CommonTree c=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:575:5: (c= CHARACTER )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:575:7: c= CHARACTER
            {
            c=(CommonTree)match(input,CHARACTER,FOLLOW_CHARACTER_in_character3170); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:577:1: strcomp returns [Expression exp] : 'strcomp' '(' e1= expression ',' e2= expression ')' ;
    public final Expression strcomp() throws Exception, RecognitionException {
        Expression exp = null;


        Expression e1 =null;

        Expression e2 =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:578:5: ( 'strcomp' '(' e1= expression ',' e2= expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:578:7: 'strcomp' '(' e1= expression ',' e2= expression ')'
            {
            match(input,91,FOLLOW_91_in_strcomp3196); 

            match(input,60,FOLLOW_60_in_strcomp3197); 

            pushFollow(FOLLOW_expression_in_strcomp3200);
            e1=expression();

            state._fsp--;


            match(input,63,FOLLOW_63_in_strcomp3202); 

            pushFollow(FOLLOW_expression_in_strcomp3206);
            e2=expression();

            state._fsp--;


            match(input,61,FOLLOW_61_in_strcomp3207); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:582:1: strsize returns [Expression exp] : 'strsize' '(' e= expression ')' ;
    public final Expression strsize() throws Exception, RecognitionException {
        Expression exp = null;


        Expression e =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:583:5: ( 'strsize' '(' e= expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:583:7: 'strsize' '(' e= expression ')'
            {
            match(input,93,FOLLOW_93_in_strsize3242); 

            match(input,60,FOLLOW_60_in_strsize3243); 

            pushFollow(FOLLOW_expression_in_strsize3246);
            e=expression();

            state._fsp--;


            match(input,61,FOLLOW_61_in_strsize3247); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:587:1: paramlist returns [Expression[] exps] : '(' l= list ')' ;
    public final Expression[] paramlist() throws Exception, RecognitionException {
        Expression[] exps = null;


        Expression[] l =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:588:5: ( '(' l= list ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:588:9: '(' l= list ')'
            {
            match(input,60,FOLLOW_60_in_paramlist3285); 

            pushFollow(FOLLOW_list_in_paramlist3289);
            l=list();

            state._fsp--;


            match(input,61,FOLLOW_61_in_paramlist3291); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:591:1: list returns [Expression[] exps] : (e= expression )* ;
    public final Expression[] list() throws Exception, RecognitionException {
        Expression[] exps = null;


        Expression e =null;


        ArrayList<Expression> explist = new ArrayList<Expression>();
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:593:5: ( (e= expression )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:593:9: (e= expression )*
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:593:9: (e= expression )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0 >= ASSIGNMENT && LA42_0 <= BINARY)||LA42_0==BitAndOp||LA42_0==BoolAndOp||(LA42_0 >= BoolOp && LA42_0 <= BoolOrOp)||LA42_0==CHARACTER||(LA42_0 >= FLOAT && LA42_0 <= HEXADECIMAL)||(LA42_0 >= ID && LA42_0 <= INT)||LA42_0==LimitMaxOp||LA42_0==MultOp||(LA42_0 >= Multiply && LA42_0 <= NEGATION)||LA42_0==NOT_OP||LA42_0==POST||(LA42_0 >= PRE && LA42_0 <= QUATERNARY)||LA42_0==RAND||LA42_0==ShiftOp||(LA42_0 >= True && LA42_0 <= UNARYOP)||(LA42_0 >= Unary2Op && LA42_0 <= UnaryOp)||LA42_0==62||LA42_0==64||(LA42_0 >= 83 && LA42_0 <= 86)||(LA42_0 >= 91 && LA42_0 <= 93)||(LA42_0 >= 103 && LA42_0 <= 104)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:593:10: e= expression
            	    {
            	    pushFollow(FOLLOW_expression_in_list3339);
            	    e=expression();

            	    state._fsp--;


            	    explist.add(e);

            	    }
            	    break;

            	default :
            	    break loop42;
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:600:1: methodCall returns [MethodCall mc] : ID p= paramlist ;
    public final MethodCall methodCall() throws Exception, RecognitionException {
        MethodCall mc = null;


        CommonTree ID33=null;
        Expression[] p =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:601:5: ( ID p= paramlist )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:601:7: ID p= paramlist
            {
            ID33=(CommonTree)match(input,ID,FOLLOW_ID_in_methodCall3383); 

            pushFollow(FOLLOW_paramlist_in_methodCall3387);
            p=paramlist();

            state._fsp--;


            if (!program.isMethod(ID33.getText())){
                      throw new SyntaxError("Method "+ID33.getText()+" does not exist");
                  }else{
                      mc = new MethodCall(ID33.getText(), p, program, currentMethod);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:609:1: objMethodCall returns [MethodCall mc] : o= ID '.' meth= ID (p= paramlist )? ;
    public final MethodCall objMethodCall() throws Exception, RecognitionException {
        MethodCall mc = null;


        CommonTree o=null;
        CommonTree meth=null;
        Expression[] p =null;


        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:610:5: (o= ID '.' meth= ID (p= paramlist )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:610:7: o= ID '.' meth= ID (p= paramlist )?
            {
            o=(CommonTree)match(input,ID,FOLLOW_ID_in_objMethodCall3427); 

            match(input,65,FOLLOW_65_in_objMethodCall3429); 

            meth=(CommonTree)match(input,ID,FOLLOW_ID_in_objMethodCall3433); 

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:610:25: (p= paramlist )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==60) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:610:25: p= paramlist
                    {
                    pushFollow(FOLLOW_paramlist_in_objMethodCall3437);
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:616:1: objectConstant returns [Constant c] : o= ID '#' con= ID ;
    public final Constant objectConstant() throws Exception, RecognitionException {
        Constant c = null;


        CommonTree o=null;
        CommonTree con=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:617:5: (o= ID '#' con= ID )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:617:6: o= ID '#' con= ID
            {
            o=(CommonTree)match(input,ID,FOLLOW_ID_in_objectConstant3473); 

            match(input,59,FOLLOW_59_in_objectConstant3475); 

            con=(CommonTree)match(input,ID,FOLLOW_ID_in_objectConstant3479); 

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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:621:1: string returns [SpinStringWrapper str] : 'string' '(' s= ( STRING | CHARACTER ) ')' ;
    public final SpinStringWrapper string() throws Exception, RecognitionException {
        SpinStringWrapper str = null;


        CommonTree s=null;

        int memoryNeeded; String stringId;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:623:5: ( 'string' '(' s= ( STRING | CHARACTER ) ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:623:7: 'string' '(' s= ( STRING | CHARACTER ) ')'
            {
            match(input,92,FOLLOW_92_in_string3520); 

            match(input,60,FOLLOW_60_in_string3522); 

            s=(CommonTree)input.LT(1);

            if ( input.LA(1)==CHARACTER||input.LA(1)==STRING ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            match(input,61,FOLLOW_61_in_string3532); 

            memoryNeeded = s.getText().length()-1;
                  stringId = SpinString.getCurrentId();
                  str = new SpinStringWrapper(stringId);
                  currentMethod.addExtraMemory(memoryNeeded);
                  currentMethod.addLocalString(stringId, s.getText().substring(1,s.getText().length()-1));
                  Debug.debug("current method for the string: "+s.getText().substring(1,s.getText().length()-1));
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
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:634:1: number returns [SpinNumber value] : ( INT |f= FLOAT |b= BINARY |q= QUATERNARY |h= HEXADECIMAL );
    public final SpinNumber number() throws Exception, RecognitionException {
        SpinNumber value = null;


        CommonTree f=null;
        CommonTree b=null;
        CommonTree q=null;
        CommonTree h=null;
        CommonTree INT34=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:635:5: ( INT |f= FLOAT |b= BINARY |q= QUATERNARY |h= HEXADECIMAL )
            int alt44=5;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt44=1;
                }
                break;
            case FLOAT:
                {
                alt44=2;
                }
                break;
            case BINARY:
                {
                alt44=3;
                }
                break;
            case QUATERNARY:
                {
                alt44=4;
                }
                break;
            case HEXADECIMAL:
                {
                alt44=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }

            switch (alt44) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:635:6: INT
                    {
                    INT34=(CommonTree)match(input,INT,FOLLOW_INT_in_number3565); 

                    StringBuffer sb = new StringBuffer(INT34.getText());
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
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:646:6: f= FLOAT
                    {
                    f=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_number3585); 

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
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:657:7: b= BINARY
                    {
                    b=(CommonTree)match(input,BINARY,FOLLOW_BINARY_in_number3601); 

                    StringBuffer sb = new StringBuffer(b.getText());
                          int index = sb.indexOf("_");
                          while(index!= -1){
                            sb.deleteCharAt(index);
                            index = sb.indexOf("_");
                          }
                          String num = sb.toString();
                        
                          value =  new SpinNumber(Program.binaryToInt(num));
                          

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:668:7: q= QUATERNARY
                    {
                    q=(CommonTree)match(input,QUATERNARY,FOLLOW_QUATERNARY_in_number3619); 

                    StringBuffer sb = new StringBuffer(q.getText());
                          int index = sb.indexOf("_");
                          while(index!= -1){
                            sb.deleteCharAt(index);
                            index = sb.indexOf("_");
                          }
                          String num = sb.toString();
                        
                          value =  new SpinNumber(Program.quaternaryToInt(num));
                          

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/ProgWalker.g:679:7: h= HEXADECIMAL
                    {
                    h=(CommonTree)match(input,HEXADECIMAL,FOLLOW_HEXADECIMAL_in_number3636); 

                    StringBuffer sb = new StringBuffer(h.getText());
                          int index = sb.indexOf("_");
                          while(index!= -1){
                            sb.deleteCharAt(index);
                            index = sb.indexOf("_");
                          }
                          String num = sb.toString();
                        
                          value =  new SpinNumber(Program.hexToInt(num));
                          

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


 

    public static final BitSet FOLLOW_conblock_in_prog120 = new BitSet(new long[]{0x0000000080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_objblock_in_prog122 = new BitSet(new long[]{0x0000000080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_varblock_in_prog124 = new BitSet(new long[]{0x0000000080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_method_in_prog126 = new BitSet(new long[]{0x0000000080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_75_in_conblock159 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NEWLINE_in_conblock161 = new BitSet(new long[]{0x0000001004000008L});
    public static final BitSet FOLLOW_constatement_in_conblock164 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_ID_in_constatement191 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_constatement193 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_constatement197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_varblock238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_vartype_in_varblock251 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_varblock255 = new BitSet(new long[]{0x0000000004000008L,0x00000008000100A0L});
    public static final BitSet FOLLOW_array_in_varblock258 = new BitSet(new long[]{0x0000000004000008L,0x0000000800010080L});
    public static final BitSet FOLLOW_71_in_vartype309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_vartype320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_vartype331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_objblock363 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_objblock368 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_STRING_in_objblock372 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_Methodheader_in_method409 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_method413 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_method430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Indent_in_block470 = new BitSet(new long[]{0x0043113004080010L,0x0000603203068701L});
    public static final BitSet FOLLOW_NEWLINE_in_block480 = new BitSet(new long[]{0x0043113004080010L,0x0000603203068701L});
    public static final BitSet FOLLOW_statement_in_block494 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_block496 = new BitSet(new long[]{0x0043113004080010L,0x0000603203068701L});
    public static final BitSet FOLLOW_block_statement_in_block513 = new BitSet(new long[]{0x0043113004080010L,0x0000603203068701L});
    public static final BitSet FOLLOW_Dedent_in_block528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_postincrement_in_statement585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_statement599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_statement624 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_paramlist_in_statement626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_statement636 = new BitSet(new long[]{0x436816AD4DC5A872L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_statement638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_statement652 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_statement658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_statement669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_statement686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_bits_high_in_statement703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_bits_low_in_statement725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_statement745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memmove_in_statement770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memfill_in_statement797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_statement_in_statement824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_statement_in_statement849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_statement872 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_statement876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_block_statement926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case_statement_in_block_statement949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeat_in_block_statement970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatconstanttimes_in_block_statement998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatwhile_in_block_statement1013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatuntil_in_block_statement1036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatvariablefrom_in_block_statement1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNARYOP_in_unary_statement1102 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_unary_statement1106 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_Unary2Op_in_unary_statement1110 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_Unary2Op_in_unary_statement1129 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_NOT_OP_in_not_statement1181 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_not_statement1185 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_BoolNotOp_in_not_statement1189 = new BitSet(new long[]{0x0000000000004008L});
    public static final BitSet FOLLOW_BoolNotOp_in_not_statement1208 = new BitSet(new long[]{0x0000000000004008L});
    public static final BitSet FOLLOW_SETHIGH_in_set_bits_high1252 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_set_bits_high1254 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SETLOW_in_set_bits_low1292 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_set_bits_low1294 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_73_in_memmove1340 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_101_in_memmove1350 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_82_in_memmove1360 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_memmove1370 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memmove1373 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memmove1374 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memmove1377 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memmove1378 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memmove1381 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_memmove1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_memfill1433 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_100_in_memfill1443 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_81_in_memfill1453 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_memfill1463 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memfill1466 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memfill1467 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memfill1470 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memfill1471 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_memfill1474 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_memfill1475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_if_statement1520 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_if_statement1525 = new BitSet(new long[]{0x0000000020000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_block_in_if_statement1540 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_77_in_if_statement1557 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_if_statement1562 = new BitSet(new long[]{0x0000000020000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_block_in_if_statement1576 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_76_in_if_statement1593 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_if_statement1608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_case_statement1644 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_case_statement1646 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_Indent_in_case_statement1650 = new BitSet(new long[]{0x436816BD4DCDA870L,0x0000018038780001L});
    public static final BitSet FOLLOW_casechoice_in_case_statement1663 = new BitSet(new long[]{0x436816BD4DCDA870L,0x0000018038780001L});
    public static final BitSet FOLLOW_NEWLINE_in_case_statement1674 = new BitSet(new long[]{0x436816BD4DCDA870L,0x0000018038780001L});
    public static final BitSet FOLLOW_Dedent_in_case_statement1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_casechoice1719 = new BitSet(new long[]{0x8000800000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_Range_in_casechoice1722 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_casechoice1726 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_63_in_casechoice1747 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_casechoice1751 = new BitSet(new long[]{0x8000800000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_Range_in_casechoice1754 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_casechoice1758 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_casechoice1771 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_block_in_casechoice1773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatvariablefrom1806 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_repeatvariablefrom1808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_repeatvariablefrom1810 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1814 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_repeatvariablefrom1816 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1822 = new BitSet(new long[]{0x0000000020000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_repeatvariablefrom1825 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1829 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatvariablefrom1833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatconstanttimes1877 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatconstanttimes1881 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatconstanttimes1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatuntil1922 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_repeatuntil1924 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatuntil1928 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatuntil1930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatwhile1965 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_repeatwhile1967 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeatwhile1971 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatwhile1973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeat2012 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_block_in_repeat2015 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat2017 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat2021 = new BitSet(new long[]{0x0000000000000000L,0x0000000480000000L});
    public static final BitSet FOLLOW_95_in_repeat2023 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeat2027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_repeat2029 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_repeat2033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_array2076 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_array2077 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_array2078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POST_in_expression2117 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_expression2119 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_expression2123 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRE_in_expression2135 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_expression2137 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_expression2141 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATION_in_expression2153 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2157 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_unary_statement_in_expression2175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_statement_in_expression2197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Unary2Op_in_expression2223 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2228 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expression2251 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2281 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_expression2285 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_Assign_in_expression2298 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2302 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_expression2306 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_number_in_expression2325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ident_in_expression2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_setbits2380 = new BitSet(new long[]{0x0000000000000002L,0x0000018000000010L});
    public static final BitSet FOLLOW_103_in_setbits2394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_setbits2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_setbits2418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_True_in_ident2453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_False_in_ident2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectConstant_in_ident2496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_ident2507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_ident2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_ident2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strcomp_in_ident2553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_character_in_ident2572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strsize_in_ident2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_ident2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookdown_in_ident2625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_ident2642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_setbits_in_ident2662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_ident2679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ident2712 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_setbits_in_ident2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id2765 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_array_in_id2767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_assignment2805 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_Assign_in_assignment2807 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_assignment2811 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_assignment2815 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_POSTINCREMENT_in_postincrement2858 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IncOp_in_postincrement2860 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_postincrement2862 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RAND_in_rand2914 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_rand2916 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_lookup2959 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_lookup2965 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_lookup2968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_lookup2969 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_rangelist_in_lookup2973 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_lookup2974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_lookdown3013 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_lookdown3019 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_lookdown3022 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_lookdown3023 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_rangelist_in_lookdown3027 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_lookdown3028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist3077 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_rangelist3088 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist3091 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_expression_in_rangeitem3126 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_Range_in_rangeitem3129 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_rangeitem3132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_in_character3170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_strcomp3196 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_strcomp3197 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_strcomp3200 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_strcomp3202 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_strcomp3206 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_strcomp3207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_strsize3242 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_strsize3243 = new BitSet(new long[]{0x436816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_expression_in_strsize3246 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_strsize3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_paramlist3285 = new BitSet(new long[]{0x636816AD4DC5A870L,0x0000018038780001L});
    public static final BitSet FOLLOW_list_in_paramlist3289 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_paramlist3291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_list3339 = new BitSet(new long[]{0x436816AD4DC5A872L,0x0000018038780001L});
    public static final BitSet FOLLOW_ID_in_methodCall3383 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_paramlist_in_methodCall3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objMethodCall3427 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_objMethodCall3429 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_objMethodCall3433 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_paramlist_in_objMethodCall3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objectConstant3473 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_objectConstant3475 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_objectConstant3479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_string3520 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_string3522 = new BitSet(new long[]{0x0004000000040000L});
    public static final BitSet FOLLOW_set_in_string3526 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_string3532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_number3565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_number3585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BINARY_in_number3601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUATERNARY_in_number3619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEXADECIMAL_in_number3636 = new BitSet(new long[]{0x0000000000000002L});

}