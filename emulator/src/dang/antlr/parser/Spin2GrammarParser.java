// $ANTLR 3.4 /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g 2012-09-13 07:39:47

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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class Spin2GrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGNMENT", "Assign", "BINARY", "BINARYOP", "BINARY_DIGIT", "BITNOT", "BITNOTEXP", "BitAndOp", "BitOrOp", "BoolAndOp", "BoolNotOp", "BoolOp", "BoolOrOp", "CHAR", "CHARACTER", "Dedent", "ESC_SEQ", "EXPONENT", "FLOAT", "False", "HEXADECIMAL", "HEX_DIGIT", "ID", "INT", "IncOp", "Indent", "LimitMaxOp", "Methodheader", "MultOp", "MultiLineComment", "Multiply", "NEGATION", "NEWLINE", "NOT_OP", "OCTAL_ESC", "POST", "POSTINCREMENT", "PRE", "QUATERNARY", "QUAT_DIGIT", "RAND", "RANGE", "RANGE_OR_INT", "Range", "SETHIGH", "SETLOW", "STRING", "ShiftOp", "SingleLineComment", "True", "UNARYOP", "UNICODE_ESC", "Unary2Op", "UnaryOp", "WS", "'#'", "'('", "')'", "'+'", "','", "'-'", "'.'", "':'", "'='", "'?'", "'['", "']'", "'byte'", "'bytefill'", "'bytemove'", "'case'", "'con'", "'else'", "'elseif'", "'from'", "'if'", "'long'", "'longfill'", "'longmove'", "'lookdown'", "'lookdownz'", "'lookup'", "'lookupz'", "'obj'", "'repeat'", "'return'", "'step'", "'strcomp'", "'string'", "'strsize'", "'to'", "'until'", "'var'", "'waitcnt'", "'while'", "'word'", "'wordfill'", "'wordmove'", "'|'", "'~'", "'~~'"
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

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public Spin2GrammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public Spin2GrammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return Spin2GrammarParser.tokenNames; }
    public String getGrammarFileName() { return "/home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g"; }


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


    public static class prog_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prog"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:144:1: prog[IDE ide] : ( conblock | objblock | varblock | method | NEWLINE !)* ;
    public final Spin2GrammarParser.prog_return prog(IDE ide) throws RecognitionException {
        Spin2GrammarParser.prog_return retval = new Spin2GrammarParser.prog_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NEWLINE5=null;
        Spin2GrammarParser.conblock_return conblock1 =null;

        Spin2GrammarParser.objblock_return objblock2 =null;

        Spin2GrammarParser.varblock_return varblock3 =null;

        Spin2GrammarParser.method_return method4 =null;


        CommonTree NEWLINE5_tree=null;

         this.ide = ide;
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:5: ( ( conblock | objblock | varblock | method | NEWLINE !)* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:7: ( conblock | objblock | varblock | method | NEWLINE !)*
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:7: ( conblock | objblock | varblock | method | NEWLINE !)*
            loop1:
            do {
                int alt1=6;
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
                case NEWLINE:
                    {
                    alt1=5;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:8: conblock
            	    {
            	    pushFollow(FOLLOW_conblock_in_prog143);
            	    conblock1=conblock();

            	    state._fsp--;

            	    adaptor.addChild(root_0, conblock1.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:17: objblock
            	    {
            	    pushFollow(FOLLOW_objblock_in_prog145);
            	    objblock2=objblock();

            	    state._fsp--;

            	    adaptor.addChild(root_0, objblock2.getTree());

            	    }
            	    break;
            	case 3 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:26: varblock
            	    {
            	    pushFollow(FOLLOW_varblock_in_prog147);
            	    varblock3=varblock();

            	    state._fsp--;

            	    adaptor.addChild(root_0, varblock3.getTree());

            	    }
            	    break;
            	case 4 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:35: method
            	    {
            	    pushFollow(FOLLOW_method_in_prog149);
            	    method4=method();

            	    state._fsp--;

            	    adaptor.addChild(root_0, method4.getTree());

            	    }
            	    break;
            	case 5 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:146:42: NEWLINE !
            	    {
            	    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_prog151); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "prog"


    public static class conblock_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conblock"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:151:1: conblock : 'con' ^ ( NEWLINE )+ ( constatement ( ',' ! constatement )* ( NEWLINE !)* )+ Dedent !;
    public final Spin2GrammarParser.conblock_return conblock() throws RecognitionException {
        Spin2GrammarParser.conblock_return retval = new Spin2GrammarParser.conblock_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal6=null;
        Token NEWLINE7=null;
        Token char_literal9=null;
        Token NEWLINE11=null;
        Token Dedent12=null;
        Spin2GrammarParser.constatement_return constatement8 =null;

        Spin2GrammarParser.constatement_return constatement10 =null;


        CommonTree string_literal6_tree=null;
        CommonTree NEWLINE7_tree=null;
        CommonTree char_literal9_tree=null;
        CommonTree NEWLINE11_tree=null;
        CommonTree Dedent12_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:152:5: ( 'con' ^ ( NEWLINE )+ ( constatement ( ',' ! constatement )* ( NEWLINE !)* )+ Dedent !)
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:152:8: 'con' ^ ( NEWLINE )+ ( constatement ( ',' ! constatement )* ( NEWLINE !)* )+ Dedent !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal6=(Token)match(input,75,FOLLOW_75_in_conblock179); 
            string_literal6_tree = 
            (CommonTree)adaptor.create(string_literal6)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal6_tree, root_0);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:152:15: ( NEWLINE )+
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
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:152:15: NEWLINE
            	    {
            	    NEWLINE7=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_conblock182); 
            	    NEWLINE7_tree = 
            	    (CommonTree)adaptor.create(NEWLINE7)
            	    ;
            	    adaptor.addChild(root_0, NEWLINE7_tree);


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


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:8: ( constatement ( ',' ! constatement )* ( NEWLINE !)* )+
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
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:9: constatement ( ',' ! constatement )* ( NEWLINE !)*
            	    {
            	    pushFollow(FOLLOW_constatement_in_conblock202);
            	    constatement8=constatement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, constatement8.getTree());

            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:22: ( ',' ! constatement )*
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==63) ) {
            	            alt3=1;
            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:23: ',' ! constatement
            	    	    {
            	    	    char_literal9=(Token)match(input,63,FOLLOW_63_in_conblock205); 

            	    	    pushFollow(FOLLOW_constatement_in_conblock208);
            	    	    constatement10=constatement();

            	    	    state._fsp--;

            	    	    adaptor.addChild(root_0, constatement10.getTree());

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop3;
            	        }
            	    } while (true);


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:50: ( NEWLINE !)*
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==NEWLINE) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:154:50: NEWLINE !
            	    	    {
            	    	    NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_conblock212); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop4;
            	        }
            	    } while (true);


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


            Dedent12=(Token)match(input,Dedent,FOLLOW_Dedent_in_conblock218); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "conblock"


    public static class constatement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constatement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:157:1: constatement : ID '=' expression ;
    public final Spin2GrammarParser.constatement_return constatement() throws RecognitionException {
        Spin2GrammarParser.constatement_return retval = new Spin2GrammarParser.constatement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID13=null;
        Token char_literal14=null;
        Spin2GrammarParser.expression_return expression15 =null;


        CommonTree ID13_tree=null;
        CommonTree char_literal14_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:158:5: ( ID '=' expression )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:158:8: ID '=' expression
            {
            root_0 = (CommonTree)adaptor.nil();


            ID13=(Token)match(input,ID,FOLLOW_ID_in_constatement237); 
            ID13_tree = 
            (CommonTree)adaptor.create(ID13)
            ;
            adaptor.addChild(root_0, ID13_tree);


            char_literal14=(Token)match(input,67,FOLLOW_67_in_constatement239); 
            char_literal14_tree = 
            (CommonTree)adaptor.create(char_literal14)
            ;
            adaptor.addChild(root_0, char_literal14_tree);


            pushFollow(FOLLOW_expression_in_constatement241);
            expression15=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression15.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "constatement"


    public static class varblock_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "varblock"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:163:1: varblock : 'var' ^ ( NEWLINE !)+ ( vartype ID ( array )? ( ',' ! ID ( array )? )* ( NEWLINE !)* )* Dedent !;
    public final Spin2GrammarParser.varblock_return varblock() throws RecognitionException {
        Spin2GrammarParser.varblock_return retval = new Spin2GrammarParser.varblock_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal16=null;
        Token NEWLINE17=null;
        Token ID19=null;
        Token char_literal21=null;
        Token ID22=null;
        Token NEWLINE24=null;
        Token Dedent25=null;
        Spin2GrammarParser.vartype_return vartype18 =null;

        Spin2GrammarParser.array_return array20 =null;

        Spin2GrammarParser.array_return array23 =null;


        CommonTree string_literal16_tree=null;
        CommonTree NEWLINE17_tree=null;
        CommonTree ID19_tree=null;
        CommonTree char_literal21_tree=null;
        CommonTree ID22_tree=null;
        CommonTree NEWLINE24_tree=null;
        CommonTree Dedent25_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:164:5: ( 'var' ^ ( NEWLINE !)+ ( vartype ID ( array )? ( ',' ! ID ( array )? )* ( NEWLINE !)* )* Dedent !)
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:164:8: 'var' ^ ( NEWLINE !)+ ( vartype ID ( array )? ( ',' ! ID ( array )? )* ( NEWLINE !)* )* Dedent !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal16=(Token)match(input,96,FOLLOW_96_in_varblock263); 
            string_literal16_tree = 
            (CommonTree)adaptor.create(string_literal16)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal16_tree, root_0);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:164:22: ( NEWLINE !)+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==NEWLINE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:164:22: NEWLINE !
            	    {
            	    NEWLINE17=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_varblock266); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:8: ( vartype ID ( array )? ( ',' ! ID ( array )? )* ( NEWLINE !)* )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==71||LA11_0==80||LA11_0==99) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:9: vartype ID ( array )? ( ',' ! ID ( array )? )* ( NEWLINE !)*
            	    {
            	    pushFollow(FOLLOW_vartype_in_varblock278);
            	    vartype18=vartype();

            	    state._fsp--;

            	    adaptor.addChild(root_0, vartype18.getTree());

            	    ID19=(Token)match(input,ID,FOLLOW_ID_in_varblock280); 
            	    ID19_tree = 
            	    (CommonTree)adaptor.create(ID19)
            	    ;
            	    adaptor.addChild(root_0, ID19_tree);


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:20: ( array )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==69) ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:20: array
            	            {
            	            pushFollow(FOLLOW_array_in_varblock282);
            	            array20=array();

            	            state._fsp--;

            	            adaptor.addChild(root_0, array20.getTree());

            	            }
            	            break;

            	    }


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:27: ( ',' ! ID ( array )? )*
            	    loop9:
            	    do {
            	        int alt9=2;
            	        int LA9_0 = input.LA(1);

            	        if ( (LA9_0==63) ) {
            	            alt9=1;
            	        }


            	        switch (alt9) {
            	    	case 1 :
            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:28: ',' ! ID ( array )?
            	    	    {
            	    	    char_literal21=(Token)match(input,63,FOLLOW_63_in_varblock286); 

            	    	    ID22=(Token)match(input,ID,FOLLOW_ID_in_varblock289); 
            	    	    ID22_tree = 
            	    	    (CommonTree)adaptor.create(ID22)
            	    	    ;
            	    	    adaptor.addChild(root_0, ID22_tree);


            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:36: ( array )?
            	    	    int alt8=2;
            	    	    int LA8_0 = input.LA(1);

            	    	    if ( (LA8_0==69) ) {
            	    	        alt8=1;
            	    	    }
            	    	    switch (alt8) {
            	    	        case 1 :
            	    	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:36: array
            	    	            {
            	    	            pushFollow(FOLLOW_array_in_varblock291);
            	    	            array23=array();

            	    	            state._fsp--;

            	    	            adaptor.addChild(root_0, array23.getTree());

            	    	            }
            	    	            break;

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop9;
            	        }
            	    } while (true);


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:52: ( NEWLINE !)*
            	    loop10:
            	    do {
            	        int alt10=2;
            	        int LA10_0 = input.LA(1);

            	        if ( (LA10_0==NEWLINE) ) {
            	            alt10=1;
            	        }


            	        switch (alt10) {
            	    	case 1 :
            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:165:52: NEWLINE !
            	    	    {
            	    	    NEWLINE24=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_varblock296); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop10;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            Dedent25=(Token)match(input,Dedent,FOLLOW_Dedent_in_varblock303); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "varblock"


    public static class vartype_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vartype"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:169:1: vartype : ( 'byte' | 'word' | 'long' );
    public final Spin2GrammarParser.vartype_return vartype() throws RecognitionException {
        Spin2GrammarParser.vartype_return retval = new Spin2GrammarParser.vartype_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set26=null;

        CommonTree set26_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:170:5: ( 'byte' | 'word' | 'long' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set26=(Token)input.LT(1);

            if ( input.LA(1)==71||input.LA(1)==80||input.LA(1)==99 ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set26)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "vartype"


    public static class objblock_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objblock"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:177:1: objblock : 'obj' ^ ( NEWLINE !)+ (i= ID ':' ! STRING ( NEWLINE !)+ )* Dedent !;
    public final Spin2GrammarParser.objblock_return objblock() throws RecognitionException {
        Spin2GrammarParser.objblock_return retval = new Spin2GrammarParser.objblock_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token i=null;
        Token string_literal27=null;
        Token NEWLINE28=null;
        Token char_literal29=null;
        Token STRING30=null;
        Token NEWLINE31=null;
        Token Dedent32=null;

        CommonTree i_tree=null;
        CommonTree string_literal27_tree=null;
        CommonTree NEWLINE28_tree=null;
        CommonTree char_literal29_tree=null;
        CommonTree STRING30_tree=null;
        CommonTree NEWLINE31_tree=null;
        CommonTree Dedent32_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:178:5: ( 'obj' ^ ( NEWLINE !)+ (i= ID ':' ! STRING ( NEWLINE !)+ )* Dedent !)
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:178:7: 'obj' ^ ( NEWLINE !)+ (i= ID ':' ! STRING ( NEWLINE !)+ )* Dedent !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal27=(Token)match(input,87,FOLLOW_87_in_objblock371); 
            string_literal27_tree = 
            (CommonTree)adaptor.create(string_literal27)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal27_tree, root_0);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:178:21: ( NEWLINE !)+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==NEWLINE) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:178:21: NEWLINE !
            	    {
            	    NEWLINE28=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_objblock374); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:179:7: (i= ID ':' ! STRING ( NEWLINE !)+ )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:179:8: i= ID ':' ! STRING ( NEWLINE !)+
            	    {
            	    i=(Token)match(input,ID,FOLLOW_ID_in_objblock387); 
            	    i_tree = 
            	    (CommonTree)adaptor.create(i)
            	    ;
            	    adaptor.addChild(root_0, i_tree);


            	    char_literal29=(Token)match(input,66,FOLLOW_66_in_objblock389); 

            	    STRING30=(Token)match(input,STRING,FOLLOW_STRING_in_objblock392); 
            	    STRING30_tree = 
            	    (CommonTree)adaptor.create(STRING30)
            	    ;
            	    adaptor.addChild(root_0, STRING30_tree);


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:179:32: ( NEWLINE !)+
            	    int cnt13=0;
            	    loop13:
            	    do {
            	        int alt13=2;
            	        int LA13_0 = input.LA(1);

            	        if ( (LA13_0==NEWLINE) ) {
            	            alt13=1;
            	        }


            	        switch (alt13) {
            	    	case 1 :
            	    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:179:32: NEWLINE !
            	    	    {
            	    	    NEWLINE31=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_objblock394); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt13 >= 1 ) break loop13;
            	                EarlyExitException eee =
            	                    new EarlyExitException(13, input);
            	                throw eee;
            	        }
            	        cnt13++;
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            Dedent32=(Token)match(input,Dedent,FOLLOW_Dedent_in_objblock400); 

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "objblock"


    public static class paramlist_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "paramlist"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:183:1: paramlist : '(' list ')' ;
    public final Spin2GrammarParser.paramlist_return paramlist() throws RecognitionException {
        Spin2GrammarParser.paramlist_return retval = new Spin2GrammarParser.paramlist_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal33=null;
        Token char_literal35=null;
        Spin2GrammarParser.list_return list34 =null;


        CommonTree char_literal33_tree=null;
        CommonTree char_literal35_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:184:5: ( '(' list ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:184:9: '(' list ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal33=(Token)match(input,60,FOLLOW_60_in_paramlist424); 
            char_literal33_tree = 
            (CommonTree)adaptor.create(char_literal33)
            ;
            adaptor.addChild(root_0, char_literal33_tree);


            pushFollow(FOLLOW_list_in_paramlist426);
            list34=list();

            state._fsp--;

            adaptor.addChild(root_0, list34.getTree());

            char_literal35=(Token)match(input,61,FOLLOW_61_in_paramlist428); 
            char_literal35_tree = 
            (CommonTree)adaptor.create(char_literal35)
            ;
            adaptor.addChild(root_0, char_literal35_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "paramlist"


    public static class list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "list"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:187:1: list : expression ( ',' ! expression )* ;
    public final Spin2GrammarParser.list_return list() throws RecognitionException {
        Spin2GrammarParser.list_return retval = new Spin2GrammarParser.list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal37=null;
        Spin2GrammarParser.expression_return expression36 =null;

        Spin2GrammarParser.expression_return expression38 =null;


        CommonTree char_literal37_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:188:5: ( expression ( ',' ! expression )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:188:9: expression ( ',' ! expression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expression_in_list460);
            expression36=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression36.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:188:20: ( ',' ! expression )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==63) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:188:21: ',' ! expression
            	    {
            	    char_literal37=(Token)match(input,63,FOLLOW_63_in_list463); 

            	    pushFollow(FOLLOW_expression_in_list466);
            	    expression38=expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expression38.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "list"


    public static class method_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "method"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:191:1: method : Methodheader ID ( paramlist !)? ( ':' ! ID !)? ( '|' ! ID ! ( ',' ! ID !)* )? ( block )? ;
    public final Spin2GrammarParser.method_return method() throws RecognitionException {
        Spin2GrammarParser.method_return retval = new Spin2GrammarParser.method_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Methodheader39=null;
        Token ID40=null;
        Token char_literal42=null;
        Token ID43=null;
        Token char_literal44=null;
        Token ID45=null;
        Token char_literal46=null;
        Token ID47=null;
        Spin2GrammarParser.paramlist_return paramlist41 =null;

        Spin2GrammarParser.block_return block48 =null;


        CommonTree Methodheader39_tree=null;
        CommonTree ID40_tree=null;
        CommonTree char_literal42_tree=null;
        CommonTree ID43_tree=null;
        CommonTree char_literal44_tree=null;
        CommonTree ID45_tree=null;
        CommonTree char_literal46_tree=null;
        CommonTree ID47_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:5: ( Methodheader ID ( paramlist !)? ( ':' ! ID !)? ( '|' ! ID ! ( ',' ! ID !)* )? ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:8: Methodheader ID ( paramlist !)? ( ':' ! ID !)? ( '|' ! ID ! ( ',' ! ID !)* )? ( block )?
            {
            root_0 = (CommonTree)adaptor.nil();


            Methodheader39=(Token)match(input,Methodheader,FOLLOW_Methodheader_in_method497); 
            Methodheader39_tree = 
            (CommonTree)adaptor.create(Methodheader39)
            ;
            adaptor.addChild(root_0, Methodheader39_tree);


            ID40=(Token)match(input,ID,FOLLOW_ID_in_method499); 
            ID40_tree = 
            (CommonTree)adaptor.create(ID40)
            ;
            adaptor.addChild(root_0, ID40_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:33: ( paramlist !)?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==60) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:33: paramlist !
                    {
                    pushFollow(FOLLOW_paramlist_in_method501);
                    paramlist41=paramlist();

                    state._fsp--;


                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:36: ( ':' ! ID !)?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==66) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:37: ':' ! ID !
                    {
                    char_literal42=(Token)match(input,66,FOLLOW_66_in_method506); 

                    ID43=(Token)match(input,ID,FOLLOW_ID_in_method509); 

                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:48: ( '|' ! ID ! ( ',' ! ID !)* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==102) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:49: '|' ! ID ! ( ',' ! ID !)*
                    {
                    char_literal44=(Token)match(input,102,FOLLOW_102_in_method515); 

                    ID45=(Token)match(input,ID,FOLLOW_ID_in_method518); 

                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:58: ( ',' ! ID !)*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==63) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:194:59: ',' ! ID !
                    	    {
                    	    char_literal46=(Token)match(input,63,FOLLOW_63_in_method522); 

                    	    ID47=(Token)match(input,ID,FOLLOW_ID_in_method525); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:195:8: ( block )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==Indent) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:195:8: block
                    {
                    pushFollow(FOLLOW_block_in_method540);
                    block48=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block48.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "method"


    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:202:1: statement : ( IncOp id | assignorpostinc | rand | unary_statement | not_statement | 'waitcnt' paramlist | 'return' ( expression )? | methodCall | ID | objMethodCall | neg_statement | memmove | memfill );
    public final Spin2GrammarParser.statement_return statement() throws RecognitionException {
        Spin2GrammarParser.statement_return retval = new Spin2GrammarParser.statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IncOp49=null;
        Token string_literal55=null;
        Token string_literal57=null;
        Token ID60=null;
        Spin2GrammarParser.id_return id50 =null;

        Spin2GrammarParser.assignorpostinc_return assignorpostinc51 =null;

        Spin2GrammarParser.rand_return rand52 =null;

        Spin2GrammarParser.unary_statement_return unary_statement53 =null;

        Spin2GrammarParser.not_statement_return not_statement54 =null;

        Spin2GrammarParser.paramlist_return paramlist56 =null;

        Spin2GrammarParser.expression_return expression58 =null;

        Spin2GrammarParser.methodCall_return methodCall59 =null;

        Spin2GrammarParser.objMethodCall_return objMethodCall61 =null;

        Spin2GrammarParser.neg_statement_return neg_statement62 =null;

        Spin2GrammarParser.memmove_return memmove63 =null;

        Spin2GrammarParser.memfill_return memfill64 =null;


        CommonTree IncOp49_tree=null;
        CommonTree string_literal55_tree=null;
        CommonTree string_literal57_tree=null;
        CommonTree ID60_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:203:5: ( IncOp id | assignorpostinc | rand | unary_statement | not_statement | 'waitcnt' paramlist | 'return' ( expression )? | methodCall | ID | objMethodCall | neg_statement | memmove | memfill )
            int alt22=13;
            switch ( input.LA(1) ) {
            case IncOp:
                {
                alt22=1;
                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case 65:
                    {
                    alt22=10;
                    }
                    break;
                case Assign:
                case IncOp:
                case 68:
                case 69:
                case 103:
                case 104:
                    {
                    alt22=2;
                    }
                    break;
                case 60:
                    {
                    alt22=8;
                    }
                    break;
                case NEWLINE:
                    {
                    alt22=9;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 2, input);

                    throw nvae;

                }

                }
                break;
            case 68:
                {
                alt22=3;
                }
                break;
            case Unary2Op:
                {
                alt22=4;
                }
                break;
            case BoolNotOp:
                {
                alt22=5;
                }
                break;
            case 97:
                {
                alt22=6;
                }
                break;
            case 89:
                {
                alt22=7;
                }
                break;
            case 64:
                {
                alt22=11;
                }
                break;
            case 73:
            case 82:
            case 101:
                {
                alt22=12;
                }
                break;
            case 72:
            case 81:
            case 100:
                {
                alt22=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:203:7: IncOp id
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    IncOp49=(Token)match(input,IncOp,FOLLOW_IncOp_in_statement566); 
                    IncOp49_tree = 
                    (CommonTree)adaptor.create(IncOp49)
                    ;
                    adaptor.addChild(root_0, IncOp49_tree);


                    pushFollow(FOLLOW_id_in_statement568);
                    id50=id();

                    state._fsp--;

                    adaptor.addChild(root_0, id50.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:204:7: assignorpostinc
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assignorpostinc_in_statement576);
                    assignorpostinc51=assignorpostinc();

                    state._fsp--;

                    adaptor.addChild(root_0, assignorpostinc51.getTree());

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:205:7: rand
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_rand_in_statement584);
                    rand52=rand();

                    state._fsp--;

                    adaptor.addChild(root_0, rand52.getTree());

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:206:7: unary_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_unary_statement_in_statement592);
                    unary_statement53=unary_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, unary_statement53.getTree());

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:207:7: not_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_not_statement_in_statement600);
                    not_statement54=not_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, not_statement54.getTree());

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:208:7: 'waitcnt' paramlist
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal55=(Token)match(input,97,FOLLOW_97_in_statement608); 
                    string_literal55_tree = 
                    (CommonTree)adaptor.create(string_literal55)
                    ;
                    adaptor.addChild(root_0, string_literal55_tree);


                    pushFollow(FOLLOW_paramlist_in_statement610);
                    paramlist56=paramlist();

                    state._fsp--;

                    adaptor.addChild(root_0, paramlist56.getTree());

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:209:7: 'return' ( expression )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal57=(Token)match(input,89,FOLLOW_89_in_statement618); 
                    string_literal57_tree = 
                    (CommonTree)adaptor.create(string_literal57)
                    ;
                    adaptor.addChild(root_0, string_literal57_tree);


                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:209:16: ( expression )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==BINARY||LA21_0==BoolNotOp||LA21_0==CHARACTER||(LA21_0 >= FLOAT && LA21_0 <= HEXADECIMAL)||(LA21_0 >= ID && LA21_0 <= IncOp)||LA21_0==QUATERNARY||LA21_0==True||(LA21_0 >= Unary2Op && LA21_0 <= UnaryOp)||LA21_0==60||LA21_0==62||LA21_0==64||LA21_0==68||(LA21_0 >= 83 && LA21_0 <= 86)||(LA21_0 >= 91 && LA21_0 <= 93)||(LA21_0 >= 103 && LA21_0 <= 104)) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:209:16: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement620);
                            expression58=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, expression58.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:210:8: methodCall
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_methodCall_in_statement631);
                    methodCall59=methodCall();

                    state._fsp--;

                    adaptor.addChild(root_0, methodCall59.getTree());

                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:212:7: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID60=(Token)match(input,ID,FOLLOW_ID_in_statement645); 
                    ID60_tree = 
                    (CommonTree)adaptor.create(ID60)
                    ;
                    adaptor.addChild(root_0, ID60_tree);


                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:213:7: objMethodCall
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_objMethodCall_in_statement653);
                    objMethodCall61=objMethodCall();

                    state._fsp--;

                    adaptor.addChild(root_0, objMethodCall61.getTree());

                    }
                    break;
                case 11 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:214:7: neg_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_neg_statement_in_statement661);
                    neg_statement62=neg_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, neg_statement62.getTree());

                    }
                    break;
                case 12 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:215:7: memmove
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_memmove_in_statement669);
                    memmove63=memmove();

                    state._fsp--;

                    adaptor.addChild(root_0, memmove63.getTree());

                    }
                    break;
                case 13 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:216:7: memfill
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_memfill_in_statement677);
                    memfill64=memfill();

                    state._fsp--;

                    adaptor.addChild(root_0, memfill64.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class block_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:219:1: block_statement : ( if_statement | case_statement | repeat | repeatconstanttimes | repeatwhile | repeatuntil | repeatvariablefrom );
    public final Spin2GrammarParser.block_statement_return block_statement() throws RecognitionException {
        Spin2GrammarParser.block_statement_return retval = new Spin2GrammarParser.block_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Spin2GrammarParser.if_statement_return if_statement65 =null;

        Spin2GrammarParser.case_statement_return case_statement66 =null;

        Spin2GrammarParser.repeat_return repeat67 =null;

        Spin2GrammarParser.repeatconstanttimes_return repeatconstanttimes68 =null;

        Spin2GrammarParser.repeatwhile_return repeatwhile69 =null;

        Spin2GrammarParser.repeatuntil_return repeatuntil70 =null;

        Spin2GrammarParser.repeatvariablefrom_return repeatvariablefrom71 =null;



        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:220:5: ( if_statement | case_statement | repeat | repeatconstanttimes | repeatwhile | repeatuntil | repeatvariablefrom )
            int alt23=7;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt23=1;
                }
                break;
            case 74:
                {
                alt23=2;
                }
                break;
            case 88:
                {
                switch ( input.LA(2) ) {
                case 98:
                    {
                    alt23=5;
                    }
                    break;
                case 95:
                    {
                    alt23=6;
                    }
                    break;
                case ID:
                    {
                    int LA23_6 = input.LA(3);

                    if ( (LA23_6==78) ) {
                        alt23=7;
                    }
                    else if ( (LA23_6==Assign||(LA23_6 >= BitAndOp && LA23_6 <= BoolOrOp)||LA23_6==Dedent||LA23_6==ID||(LA23_6 >= IncOp && LA23_6 <= LimitMaxOp)||LA23_6==MultOp||LA23_6==Multiply||LA23_6==NEWLINE||LA23_6==ShiftOp||LA23_6==Unary2Op||(LA23_6 >= 59 && LA23_6 <= 60)||LA23_6==62||(LA23_6 >= 64 && LA23_6 <= 65)||(LA23_6 >= 68 && LA23_6 <= 69)||(LA23_6 >= 72 && LA23_6 <= 74)||LA23_6==79||(LA23_6 >= 81 && LA23_6 <= 82)||(LA23_6 >= 88 && LA23_6 <= 89)||LA23_6==97||(LA23_6 >= 100 && LA23_6 <= 101)||(LA23_6 >= 103 && LA23_6 <= 104)) ) {
                        alt23=4;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 6, input);

                        throw nvae;

                    }
                    }
                    break;
                case Indent:
                case NEWLINE:
                    {
                    alt23=3;
                    }
                    break;
                case BINARY:
                case BoolNotOp:
                case CHARACTER:
                case FLOAT:
                case False:
                case HEXADECIMAL:
                case INT:
                case IncOp:
                case QUATERNARY:
                case True:
                case Unary2Op:
                case UnaryOp:
                case 60:
                case 62:
                case 64:
                case 68:
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
                    alt23=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 3, input);

                    throw nvae;

                }

                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:220:8: if_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_if_statement_in_block_statement695);
                    if_statement65=if_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, if_statement65.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:221:7: case_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_case_statement_in_block_statement704);
                    case_statement66=case_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, case_statement66.getTree());

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:222:7: repeat
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_repeat_in_block_statement713);
                    repeat67=repeat();

                    state._fsp--;

                    adaptor.addChild(root_0, repeat67.getTree());

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:223:7: repeatconstanttimes
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_repeatconstanttimes_in_block_statement722);
                    repeatconstanttimes68=repeatconstanttimes();

                    state._fsp--;

                    adaptor.addChild(root_0, repeatconstanttimes68.getTree());

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:224:7: repeatwhile
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_repeatwhile_in_block_statement730);
                    repeatwhile69=repeatwhile();

                    state._fsp--;

                    adaptor.addChild(root_0, repeatwhile69.getTree());

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:225:7: repeatuntil
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_repeatuntil_in_block_statement739);
                    repeatuntil70=repeatuntil();

                    state._fsp--;

                    adaptor.addChild(root_0, repeatuntil70.getTree());

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:226:7: repeatvariablefrom
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_repeatvariablefrom_in_block_statement748);
                    repeatvariablefrom71=repeatvariablefrom();

                    state._fsp--;

                    adaptor.addChild(root_0, repeatvariablefrom71.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block_statement"


    public static class assignorpostinc_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignorpostinc"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:230:1: assignorpostinc : id ( Assign expression -> ^( ASSIGNMENT Assign id expression ) | IncOp -> ^( POSTINCREMENT IncOp id ) | '~' -> ^( SETLOW id ) | '~~' -> ^( SETHIGH id ) | '?' -> ^( RAND id ) ) ;
    public final Spin2GrammarParser.assignorpostinc_return assignorpostinc() throws RecognitionException {
        Spin2GrammarParser.assignorpostinc_return retval = new Spin2GrammarParser.assignorpostinc_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Assign73=null;
        Token IncOp75=null;
        Token char_literal76=null;
        Token string_literal77=null;
        Token char_literal78=null;
        Spin2GrammarParser.id_return id72 =null;

        Spin2GrammarParser.expression_return expression74 =null;


        CommonTree Assign73_tree=null;
        CommonTree IncOp75_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree string_literal77_tree=null;
        CommonTree char_literal78_tree=null;
        RewriteRuleTokenStream stream_IncOp=new RewriteRuleTokenStream(adaptor,"token IncOp");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_Assign=new RewriteRuleTokenStream(adaptor,"token Assign");
        RewriteRuleTokenStream stream_104=new RewriteRuleTokenStream(adaptor,"token 104");
        RewriteRuleTokenStream stream_103=new RewriteRuleTokenStream(adaptor,"token 103");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:231:5: ( id ( Assign expression -> ^( ASSIGNMENT Assign id expression ) | IncOp -> ^( POSTINCREMENT IncOp id ) | '~' -> ^( SETLOW id ) | '~~' -> ^( SETHIGH id ) | '?' -> ^( RAND id ) ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:231:7: id ( Assign expression -> ^( ASSIGNMENT Assign id expression ) | IncOp -> ^( POSTINCREMENT IncOp id ) | '~' -> ^( SETLOW id ) | '~~' -> ^( SETHIGH id ) | '?' -> ^( RAND id ) )
            {
            pushFollow(FOLLOW_id_in_assignorpostinc771);
            id72=id();

            state._fsp--;

            stream_id.add(id72.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:231:10: ( Assign expression -> ^( ASSIGNMENT Assign id expression ) | IncOp -> ^( POSTINCREMENT IncOp id ) | '~' -> ^( SETLOW id ) | '~~' -> ^( SETHIGH id ) | '?' -> ^( RAND id ) )
            int alt24=5;
            switch ( input.LA(1) ) {
            case Assign:
                {
                alt24=1;
                }
                break;
            case IncOp:
                {
                alt24=2;
                }
                break;
            case 103:
                {
                alt24=3;
                }
                break;
            case 104:
                {
                alt24=4;
                }
                break;
            case 68:
                {
                alt24=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:231:11: Assign expression
                    {
                    Assign73=(Token)match(input,Assign,FOLLOW_Assign_in_assignorpostinc774);  
                    stream_Assign.add(Assign73);


                    pushFollow(FOLLOW_expression_in_assignorpostinc776);
                    expression74=expression();

                    state._fsp--;

                    stream_expression.add(expression74.getTree());

                    // AST REWRITE
                    // elements: id, expression, Assign
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 231:29: -> ^( ASSIGNMENT Assign id expression )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:231:32: ^( ASSIGNMENT Assign id expression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(ASSIGNMENT, "ASSIGNMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_Assign.nextNode()
                        );

                        adaptor.addChild(root_1, stream_id.nextTree());

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:232:7: IncOp
                    {
                    IncOp75=(Token)match(input,IncOp,FOLLOW_IncOp_in_assignorpostinc796);  
                    stream_IncOp.add(IncOp75);


                    // AST REWRITE
                    // elements: id, IncOp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 232:13: -> ^( POSTINCREMENT IncOp id )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:232:16: ^( POSTINCREMENT IncOp id )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(POSTINCREMENT, "POSTINCREMENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IncOp.nextNode()
                        );

                        adaptor.addChild(root_1, stream_id.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:233:7: '~'
                    {
                    char_literal76=(Token)match(input,103,FOLLOW_103_in_assignorpostinc814);  
                    stream_103.add(char_literal76);


                    // AST REWRITE
                    // elements: id
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 233:11: -> ^( SETLOW id )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:233:14: ^( SETLOW id )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(SETLOW, "SETLOW")
                        , root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:234:7: '~~'
                    {
                    string_literal77=(Token)match(input,104,FOLLOW_104_in_assignorpostinc830);  
                    stream_104.add(string_literal77);


                    // AST REWRITE
                    // elements: id
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 234:12: -> ^( SETHIGH id )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:234:15: ^( SETHIGH id )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(SETHIGH, "SETHIGH")
                        , root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:235:7: '?'
                    {
                    char_literal78=(Token)match(input,68,FOLLOW_68_in_assignorpostinc846);  
                    stream_68.add(char_literal78);


                    // AST REWRITE
                    // elements: id
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 235:11: -> ^( RAND id )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:235:14: ^( RAND id )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(RAND, "RAND")
                        , root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignorpostinc"


    public static class id_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "id"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:238:1: id : ID ( array )? ;
    public final Spin2GrammarParser.id_return id() throws RecognitionException {
        Spin2GrammarParser.id_return retval = new Spin2GrammarParser.id_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID79=null;
        Spin2GrammarParser.array_return array80 =null;


        CommonTree ID79_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:239:5: ( ID ( array )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:239:7: ID ( array )?
            {
            root_0 = (CommonTree)adaptor.nil();


            ID79=(Token)match(input,ID,FOLLOW_ID_in_id872); 
            ID79_tree = 
            (CommonTree)adaptor.create(ID79)
            ;
            adaptor.addChild(root_0, ID79_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:239:10: ( array )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==69) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:239:10: array
                    {
                    pushFollow(FOLLOW_array_in_id874);
                    array80=array();

                    state._fsp--;

                    adaptor.addChild(root_0, array80.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "id"


    public static class neg_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "neg_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:243:1: neg_statement : '-' id ;
    public final Spin2GrammarParser.neg_statement_return neg_statement() throws RecognitionException {
        Spin2GrammarParser.neg_statement_return retval = new Spin2GrammarParser.neg_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal81=null;
        Spin2GrammarParser.id_return id82 =null;


        CommonTree char_literal81_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:244:5: ( '-' id )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:244:7: '-' id
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal81=(Token)match(input,64,FOLLOW_64_in_neg_statement897); 
            char_literal81_tree = 
            (CommonTree)adaptor.create(char_literal81)
            ;
            adaptor.addChild(root_0, char_literal81_tree);


            pushFollow(FOLLOW_id_in_neg_statement899);
            id82=id();

            state._fsp--;

            adaptor.addChild(root_0, id82.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "neg_statement"


    public static class memmove_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "memmove"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:248:1: memmove : ( 'bytemove' | 'wordmove' | 'longmove' ) '(' expression ',' expression ',' expression ')' ;
    public final Spin2GrammarParser.memmove_return memmove() throws RecognitionException {
        Spin2GrammarParser.memmove_return retval = new Spin2GrammarParser.memmove_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set83=null;
        Token char_literal84=null;
        Token char_literal86=null;
        Token char_literal88=null;
        Token char_literal90=null;
        Spin2GrammarParser.expression_return expression85 =null;

        Spin2GrammarParser.expression_return expression87 =null;

        Spin2GrammarParser.expression_return expression89 =null;


        CommonTree set83_tree=null;
        CommonTree char_literal84_tree=null;
        CommonTree char_literal86_tree=null;
        CommonTree char_literal88_tree=null;
        CommonTree char_literal90_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:249:5: ( ( 'bytemove' | 'wordmove' | 'longmove' ) '(' expression ',' expression ',' expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:249:7: ( 'bytemove' | 'wordmove' | 'longmove' ) '(' expression ',' expression ',' expression ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            set83=(Token)input.LT(1);

            if ( input.LA(1)==73||input.LA(1)==82||input.LA(1)==101 ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set83)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            char_literal84=(Token)match(input,60,FOLLOW_60_in_memmove924); 
            char_literal84_tree = 
            (CommonTree)adaptor.create(char_literal84)
            ;
            adaptor.addChild(root_0, char_literal84_tree);


            pushFollow(FOLLOW_expression_in_memmove925);
            expression85=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression85.getTree());

            char_literal86=(Token)match(input,63,FOLLOW_63_in_memmove926); 
            char_literal86_tree = 
            (CommonTree)adaptor.create(char_literal86)
            ;
            adaptor.addChild(root_0, char_literal86_tree);


            pushFollow(FOLLOW_expression_in_memmove927);
            expression87=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression87.getTree());

            char_literal88=(Token)match(input,63,FOLLOW_63_in_memmove928); 
            char_literal88_tree = 
            (CommonTree)adaptor.create(char_literal88)
            ;
            adaptor.addChild(root_0, char_literal88_tree);


            pushFollow(FOLLOW_expression_in_memmove929);
            expression89=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression89.getTree());

            char_literal90=(Token)match(input,61,FOLLOW_61_in_memmove930); 
            char_literal90_tree = 
            (CommonTree)adaptor.create(char_literal90)
            ;
            adaptor.addChild(root_0, char_literal90_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "memmove"


    public static class memfill_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "memfill"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:253:1: memfill : ( 'bytefill' | 'wordfill' | 'longfill' ) '(' expression ',' expression ',' expression ')' ;
    public final Spin2GrammarParser.memfill_return memfill() throws RecognitionException {
        Spin2GrammarParser.memfill_return retval = new Spin2GrammarParser.memfill_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set91=null;
        Token char_literal92=null;
        Token char_literal94=null;
        Token char_literal96=null;
        Token char_literal98=null;
        Spin2GrammarParser.expression_return expression93 =null;

        Spin2GrammarParser.expression_return expression95 =null;

        Spin2GrammarParser.expression_return expression97 =null;


        CommonTree set91_tree=null;
        CommonTree char_literal92_tree=null;
        CommonTree char_literal94_tree=null;
        CommonTree char_literal96_tree=null;
        CommonTree char_literal98_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:254:5: ( ( 'bytefill' | 'wordfill' | 'longfill' ) '(' expression ',' expression ',' expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:254:7: ( 'bytefill' | 'wordfill' | 'longfill' ) '(' expression ',' expression ',' expression ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            set91=(Token)input.LT(1);

            if ( input.LA(1)==72||input.LA(1)==81||input.LA(1)==100 ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set91)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            char_literal92=(Token)match(input,60,FOLLOW_60_in_memfill963); 
            char_literal92_tree = 
            (CommonTree)adaptor.create(char_literal92)
            ;
            adaptor.addChild(root_0, char_literal92_tree);


            pushFollow(FOLLOW_expression_in_memfill964);
            expression93=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression93.getTree());

            char_literal94=(Token)match(input,63,FOLLOW_63_in_memfill965); 
            char_literal94_tree = 
            (CommonTree)adaptor.create(char_literal94)
            ;
            adaptor.addChild(root_0, char_literal94_tree);


            pushFollow(FOLLOW_expression_in_memfill966);
            expression95=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression95.getTree());

            char_literal96=(Token)match(input,63,FOLLOW_63_in_memfill967); 
            char_literal96_tree = 
            (CommonTree)adaptor.create(char_literal96)
            ;
            adaptor.addChild(root_0, char_literal96_tree);


            pushFollow(FOLLOW_expression_in_memfill968);
            expression97=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression97.getTree());

            char_literal98=(Token)match(input,61,FOLLOW_61_in_memfill969); 
            char_literal98_tree = 
            (CommonTree)adaptor.create(char_literal98)
            ;
            adaptor.addChild(root_0, char_literal98_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "memfill"


    public static class if_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "if_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:264:1: if_statement : 'if' expression ( block )? ( 'elseif' expression ( block )? )* ( 'else' ( block )? )? ;
    public final Spin2GrammarParser.if_statement_return if_statement() throws RecognitionException {
        Spin2GrammarParser.if_statement_return retval = new Spin2GrammarParser.if_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal99=null;
        Token string_literal102=null;
        Token string_literal105=null;
        Spin2GrammarParser.expression_return expression100 =null;

        Spin2GrammarParser.block_return block101 =null;

        Spin2GrammarParser.expression_return expression103 =null;

        Spin2GrammarParser.block_return block104 =null;

        Spin2GrammarParser.block_return block106 =null;


        CommonTree string_literal99_tree=null;
        CommonTree string_literal102_tree=null;
        CommonTree string_literal105_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:265:5: ( 'if' expression ( block )? ( 'elseif' expression ( block )? )* ( 'else' ( block )? )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:265:8: 'if' expression ( block )? ( 'elseif' expression ( block )? )* ( 'else' ( block )? )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal99=(Token)match(input,79,FOLLOW_79_in_if_statement999); 
            string_literal99_tree = 
            (CommonTree)adaptor.create(string_literal99)
            ;
            adaptor.addChild(root_0, string_literal99_tree);


            pushFollow(FOLLOW_expression_in_if_statement1001);
            expression100=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression100.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:265:24: ( block )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==Indent) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:265:24: block
                    {
                    pushFollow(FOLLOW_block_in_if_statement1003);
                    block101=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block101.getTree());

                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:266:9: ( 'elseif' expression ( block )? )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==77) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:266:11: 'elseif' expression ( block )?
            	    {
            	    string_literal102=(Token)match(input,77,FOLLOW_77_in_if_statement1018); 
            	    string_literal102_tree = 
            	    (CommonTree)adaptor.create(string_literal102)
            	    ;
            	    adaptor.addChild(root_0, string_literal102_tree);


            	    pushFollow(FOLLOW_expression_in_if_statement1020);
            	    expression103=expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expression103.getTree());

            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:266:31: ( block )?
            	    int alt27=2;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0==Indent) ) {
            	        alt27=1;
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:266:31: block
            	            {
            	            pushFollow(FOLLOW_block_in_if_statement1022);
            	            block104=block();

            	            state._fsp--;

            	            adaptor.addChild(root_0, block104.getTree());

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:267:9: ( 'else' ( block )? )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==76) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:267:11: 'else' ( block )?
                    {
                    string_literal105=(Token)match(input,76,FOLLOW_76_in_if_statement1037); 
                    string_literal105_tree = 
                    (CommonTree)adaptor.create(string_literal105)
                    ;
                    adaptor.addChild(root_0, string_literal105_tree);


                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:267:18: ( block )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==Indent) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:267:18: block
                            {
                            pushFollow(FOLLOW_block_in_if_statement1039);
                            block106=block();

                            state._fsp--;

                            adaptor.addChild(root_0, block106.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "if_statement"


    public static class case_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "case_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:269:1: case_statement : 'case' expression Indent ( casechoice | NEWLINE )* Dedent ;
    public final Spin2GrammarParser.case_statement_return case_statement() throws RecognitionException {
        Spin2GrammarParser.case_statement_return retval = new Spin2GrammarParser.case_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal107=null;
        Token Indent109=null;
        Token NEWLINE111=null;
        Token Dedent112=null;
        Spin2GrammarParser.expression_return expression108 =null;

        Spin2GrammarParser.casechoice_return casechoice110 =null;


        CommonTree string_literal107_tree=null;
        CommonTree Indent109_tree=null;
        CommonTree NEWLINE111_tree=null;
        CommonTree Dedent112_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:270:5: ( 'case' expression Indent ( casechoice | NEWLINE )* Dedent )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:270:9: 'case' expression Indent ( casechoice | NEWLINE )* Dedent
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal107=(Token)match(input,74,FOLLOW_74_in_case_statement1062); 
            string_literal107_tree = 
            (CommonTree)adaptor.create(string_literal107)
            ;
            adaptor.addChild(root_0, string_literal107_tree);


            pushFollow(FOLLOW_expression_in_case_statement1064);
            expression108=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression108.getTree());

            Indent109=(Token)match(input,Indent,FOLLOW_Indent_in_case_statement1066); 
            Indent109_tree = 
            (CommonTree)adaptor.create(Indent109)
            ;
            adaptor.addChild(root_0, Indent109_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:271:9: ( casechoice | NEWLINE )*
            loop31:
            do {
                int alt31=3;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==BINARY||LA31_0==BoolNotOp||LA31_0==CHARACTER||(LA31_0 >= FLOAT && LA31_0 <= HEXADECIMAL)||(LA31_0 >= ID && LA31_0 <= IncOp)||LA31_0==QUATERNARY||LA31_0==True||(LA31_0 >= Unary2Op && LA31_0 <= UnaryOp)||LA31_0==60||LA31_0==62||LA31_0==64||LA31_0==68||(LA31_0 >= 83 && LA31_0 <= 86)||(LA31_0 >= 91 && LA31_0 <= 93)||(LA31_0 >= 103 && LA31_0 <= 104)) ) {
                    alt31=1;
                }
                else if ( (LA31_0==NEWLINE) ) {
                    alt31=2;
                }


                switch (alt31) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:271:10: casechoice
            	    {
            	    pushFollow(FOLLOW_casechoice_in_case_statement1077);
            	    casechoice110=casechoice();

            	    state._fsp--;

            	    adaptor.addChild(root_0, casechoice110.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:271:23: NEWLINE
            	    {
            	    NEWLINE111=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_case_statement1081); 
            	    NEWLINE111_tree = 
            	    (CommonTree)adaptor.create(NEWLINE111)
            	    ;
            	    adaptor.addChild(root_0, NEWLINE111_tree);


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            Dedent112=(Token)match(input,Dedent,FOLLOW_Dedent_in_case_statement1093); 
            Dedent112_tree = 
            (CommonTree)adaptor.create(Dedent112)
            ;
            adaptor.addChild(root_0, Dedent112_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "case_statement"


    public static class casechoice_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "casechoice"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:275:1: casechoice : ( expression ( '..' expression )? ) ( ',' ( expression ( '..' expression )? ) )* ':' block ;
    public final Spin2GrammarParser.casechoice_return casechoice() throws RecognitionException {
        Spin2GrammarParser.casechoice_return retval = new Spin2GrammarParser.casechoice_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal114=null;
        Token char_literal116=null;
        Token string_literal118=null;
        Token char_literal120=null;
        Spin2GrammarParser.expression_return expression113 =null;

        Spin2GrammarParser.expression_return expression115 =null;

        Spin2GrammarParser.expression_return expression117 =null;

        Spin2GrammarParser.expression_return expression119 =null;

        Spin2GrammarParser.block_return block121 =null;


        CommonTree string_literal114_tree=null;
        CommonTree char_literal116_tree=null;
        CommonTree string_literal118_tree=null;
        CommonTree char_literal120_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:5: ( ( expression ( '..' expression )? ) ( ',' ( expression ( '..' expression )? ) )* ':' block )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:7: ( expression ( '..' expression )? ) ( ',' ( expression ( '..' expression )? ) )* ':' block
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:7: ( expression ( '..' expression )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:8: expression ( '..' expression )?
            {
            pushFollow(FOLLOW_expression_in_casechoice1113);
            expression113=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression113.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:19: ( '..' expression )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==Range) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:276:20: '..' expression
                    {
                    string_literal114=(Token)match(input,Range,FOLLOW_Range_in_casechoice1116); 
                    string_literal114_tree = 
                    (CommonTree)adaptor.create(string_literal114)
                    ;
                    adaptor.addChild(root_0, string_literal114_tree);


                    pushFollow(FOLLOW_expression_in_casechoice1118);
                    expression115=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression115.getTree());

                    }
                    break;

            }


            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:7: ( ',' ( expression ( '..' expression )? ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==63) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:8: ',' ( expression ( '..' expression )? )
            	    {
            	    char_literal116=(Token)match(input,63,FOLLOW_63_in_casechoice1131); 
            	    char_literal116_tree = 
            	    (CommonTree)adaptor.create(char_literal116)
            	    ;
            	    adaptor.addChild(root_0, char_literal116_tree);


            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:11: ( expression ( '..' expression )? )
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:12: expression ( '..' expression )?
            	    {
            	    pushFollow(FOLLOW_expression_in_casechoice1133);
            	    expression117=expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expression117.getTree());

            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:23: ( '..' expression )?
            	    int alt33=2;
            	    int LA33_0 = input.LA(1);

            	    if ( (LA33_0==Range) ) {
            	        alt33=1;
            	    }
            	    switch (alt33) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:277:24: '..' expression
            	            {
            	            string_literal118=(Token)match(input,Range,FOLLOW_Range_in_casechoice1136); 
            	            string_literal118_tree = 
            	            (CommonTree)adaptor.create(string_literal118)
            	            ;
            	            adaptor.addChild(root_0, string_literal118_tree);


            	            pushFollow(FOLLOW_expression_in_casechoice1138);
            	            expression119=expression();

            	            state._fsp--;

            	            adaptor.addChild(root_0, expression119.getTree());

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            char_literal120=(Token)match(input,66,FOLLOW_66_in_casechoice1146); 
            char_literal120_tree = 
            (CommonTree)adaptor.create(char_literal120)
            ;
            adaptor.addChild(root_0, char_literal120_tree);


            pushFollow(FOLLOW_block_in_casechoice1148);
            block121=block();

            state._fsp--;

            adaptor.addChild(root_0, block121.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "casechoice"


    public static class repeatvariablefrom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "repeatvariablefrom"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:284:1: repeatvariablefrom : 'repeat' ID 'from' expression 'to' expression ( 'step' expression )? ( block )? ;
    public final Spin2GrammarParser.repeatvariablefrom_return repeatvariablefrom() throws RecognitionException {
        Spin2GrammarParser.repeatvariablefrom_return retval = new Spin2GrammarParser.repeatvariablefrom_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal122=null;
        Token ID123=null;
        Token string_literal124=null;
        Token string_literal126=null;
        Token string_literal128=null;
        Spin2GrammarParser.expression_return expression125 =null;

        Spin2GrammarParser.expression_return expression127 =null;

        Spin2GrammarParser.expression_return expression129 =null;

        Spin2GrammarParser.block_return block130 =null;


        CommonTree string_literal122_tree=null;
        CommonTree ID123_tree=null;
        CommonTree string_literal124_tree=null;
        CommonTree string_literal126_tree=null;
        CommonTree string_literal128_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:5: ( 'repeat' ID 'from' expression 'to' expression ( 'step' expression )? ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:7: 'repeat' ID 'from' expression 'to' expression ( 'step' expression )? ( block )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal122=(Token)match(input,88,FOLLOW_88_in_repeatvariablefrom1170); 
            string_literal122_tree = 
            (CommonTree)adaptor.create(string_literal122)
            ;
            adaptor.addChild(root_0, string_literal122_tree);


            ID123=(Token)match(input,ID,FOLLOW_ID_in_repeatvariablefrom1172); 
            ID123_tree = 
            (CommonTree)adaptor.create(ID123)
            ;
            adaptor.addChild(root_0, ID123_tree);


            string_literal124=(Token)match(input,78,FOLLOW_78_in_repeatvariablefrom1174); 
            string_literal124_tree = 
            (CommonTree)adaptor.create(string_literal124)
            ;
            adaptor.addChild(root_0, string_literal124_tree);


            pushFollow(FOLLOW_expression_in_repeatvariablefrom1176);
            expression125=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression125.getTree());

            string_literal126=(Token)match(input,94,FOLLOW_94_in_repeatvariablefrom1178); 
            string_literal126_tree = 
            (CommonTree)adaptor.create(string_literal126)
            ;
            adaptor.addChild(root_0, string_literal126_tree);


            pushFollow(FOLLOW_expression_in_repeatvariablefrom1180);
            expression127=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression127.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:53: ( 'step' expression )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==90) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:54: 'step' expression
                    {
                    string_literal128=(Token)match(input,90,FOLLOW_90_in_repeatvariablefrom1183); 
                    string_literal128_tree = 
                    (CommonTree)adaptor.create(string_literal128)
                    ;
                    adaptor.addChild(root_0, string_literal128_tree);


                    pushFollow(FOLLOW_expression_in_repeatvariablefrom1185);
                    expression129=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression129.getTree());

                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:74: ( block )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==Indent) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:285:74: block
                    {
                    pushFollow(FOLLOW_block_in_repeatvariablefrom1189);
                    block130=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block130.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "repeatvariablefrom"


    public static class repeatconstanttimes_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "repeatconstanttimes"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:289:1: repeatconstanttimes : 'repeat' e= expression ( block )? ;
    public final Spin2GrammarParser.repeatconstanttimes_return repeatconstanttimes() throws RecognitionException {
        Spin2GrammarParser.repeatconstanttimes_return retval = new Spin2GrammarParser.repeatconstanttimes_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal131=null;
        Spin2GrammarParser.expression_return e =null;

        Spin2GrammarParser.block_return block132 =null;


        CommonTree string_literal131_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:290:5: ( 'repeat' e= expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:290:7: 'repeat' e= expression ( block )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal131=(Token)match(input,88,FOLLOW_88_in_repeatconstanttimes1209); 
            string_literal131_tree = 
            (CommonTree)adaptor.create(string_literal131)
            ;
            adaptor.addChild(root_0, string_literal131_tree);


            pushFollow(FOLLOW_expression_in_repeatconstanttimes1213);
            e=expression();

            state._fsp--;

            adaptor.addChild(root_0, e.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:290:29: ( block )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==Indent) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:290:29: block
                    {
                    pushFollow(FOLLOW_block_in_repeatconstanttimes1215);
                    block132=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block132.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "repeatconstanttimes"


    public static class repeatuntil_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "repeatuntil"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:293:1: repeatuntil : 'repeat' 'until' expression ( block )? ;
    public final Spin2GrammarParser.repeatuntil_return repeatuntil() throws RecognitionException {
        Spin2GrammarParser.repeatuntil_return retval = new Spin2GrammarParser.repeatuntil_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal133=null;
        Token string_literal134=null;
        Spin2GrammarParser.expression_return expression135 =null;

        Spin2GrammarParser.block_return block136 =null;


        CommonTree string_literal133_tree=null;
        CommonTree string_literal134_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:294:5: ( 'repeat' 'until' expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:294:7: 'repeat' 'until' expression ( block )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal133=(Token)match(input,88,FOLLOW_88_in_repeatuntil1235); 
            string_literal133_tree = 
            (CommonTree)adaptor.create(string_literal133)
            ;
            adaptor.addChild(root_0, string_literal133_tree);


            string_literal134=(Token)match(input,95,FOLLOW_95_in_repeatuntil1237); 
            string_literal134_tree = 
            (CommonTree)adaptor.create(string_literal134)
            ;
            adaptor.addChild(root_0, string_literal134_tree);


            pushFollow(FOLLOW_expression_in_repeatuntil1239);
            expression135=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression135.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:294:35: ( block )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==Indent) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:294:35: block
                    {
                    pushFollow(FOLLOW_block_in_repeatuntil1241);
                    block136=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block136.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "repeatuntil"


    public static class repeatwhile_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "repeatwhile"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:297:1: repeatwhile : 'repeat' 'while' expression ( block )? ;
    public final Spin2GrammarParser.repeatwhile_return repeatwhile() throws RecognitionException {
        Spin2GrammarParser.repeatwhile_return retval = new Spin2GrammarParser.repeatwhile_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal137=null;
        Token string_literal138=null;
        Spin2GrammarParser.expression_return expression139 =null;

        Spin2GrammarParser.block_return block140 =null;


        CommonTree string_literal137_tree=null;
        CommonTree string_literal138_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:298:5: ( 'repeat' 'while' expression ( block )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:298:7: 'repeat' 'while' expression ( block )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal137=(Token)match(input,88,FOLLOW_88_in_repeatwhile1260); 
            string_literal137_tree = 
            (CommonTree)adaptor.create(string_literal137)
            ;
            adaptor.addChild(root_0, string_literal137_tree);


            string_literal138=(Token)match(input,98,FOLLOW_98_in_repeatwhile1262); 
            string_literal138_tree = 
            (CommonTree)adaptor.create(string_literal138)
            ;
            adaptor.addChild(root_0, string_literal138_tree);


            pushFollow(FOLLOW_expression_in_repeatwhile1264);
            expression139=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression139.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:298:35: ( block )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==Indent) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:298:35: block
                    {
                    pushFollow(FOLLOW_block_in_repeatwhile1266);
                    block140=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block140.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "repeatwhile"


    public static class repeat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "repeat"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:301:1: repeat : 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' expression | 'while' expression ) )? ;
    public final Spin2GrammarParser.repeat_return repeat() throws RecognitionException {
        Spin2GrammarParser.repeat_return retval = new Spin2GrammarParser.repeat_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal141=null;
        Token NEWLINE143=null;
        Token NEWLINE144=null;
        Token string_literal145=null;
        Token string_literal147=null;
        Spin2GrammarParser.block_return block142 =null;

        Spin2GrammarParser.expression_return expression146 =null;

        Spin2GrammarParser.expression_return expression148 =null;


        CommonTree string_literal141_tree=null;
        CommonTree NEWLINE143_tree=null;
        CommonTree NEWLINE144_tree=null;
        CommonTree string_literal145_tree=null;
        CommonTree string_literal147_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:5: ( 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' expression | 'while' expression ) )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:7: 'repeat' ( block | NEWLINE ) ( NEWLINE ( 'until' expression | 'while' expression ) )?
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal141=(Token)match(input,88,FOLLOW_88_in_repeat1287); 
            string_literal141_tree = 
            (CommonTree)adaptor.create(string_literal141)
            ;
            adaptor.addChild(root_0, string_literal141_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:16: ( block | NEWLINE )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==Indent) ) {
                alt40=1;
            }
            else if ( (LA40_0==NEWLINE) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:17: block
                    {
                    pushFollow(FOLLOW_block_in_repeat1290);
                    block142=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block142.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:23: NEWLINE
                    {
                    NEWLINE143=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat1292); 
                    NEWLINE143_tree = 
                    (CommonTree)adaptor.create(NEWLINE143)
                    ;
                    adaptor.addChild(root_0, NEWLINE143_tree);


                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:32: ( NEWLINE ( 'until' expression | 'while' expression ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==NEWLINE) ) {
                int LA42_1 = input.LA(2);

                if ( (LA42_1==95||LA42_1==98) ) {
                    alt42=1;
                }
            }
            switch (alt42) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:33: NEWLINE ( 'until' expression | 'while' expression )
                    {
                    NEWLINE144=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_repeat1296); 
                    NEWLINE144_tree = 
                    (CommonTree)adaptor.create(NEWLINE144)
                    ;
                    adaptor.addChild(root_0, NEWLINE144_tree);


                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:40: ( 'until' expression | 'while' expression )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==95) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==98) ) {
                        alt41=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;

                    }
                    switch (alt41) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:41: 'until' expression
                            {
                            string_literal145=(Token)match(input,95,FOLLOW_95_in_repeat1298); 
                            string_literal145_tree = 
                            (CommonTree)adaptor.create(string_literal145)
                            ;
                            adaptor.addChild(root_0, string_literal145_tree);


                            pushFollow(FOLLOW_expression_in_repeat1300);
                            expression146=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, expression146.getTree());

                            }
                            break;
                        case 2 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:302:60: 'while' expression
                            {
                            string_literal147=(Token)match(input,98,FOLLOW_98_in_repeat1302); 
                            string_literal147_tree = 
                            (CommonTree)adaptor.create(string_literal147)
                            ;
                            adaptor.addChild(root_0, string_literal147_tree);


                            pushFollow(FOLLOW_expression_in_repeat1304);
                            expression148=expression();

                            state._fsp--;

                            adaptor.addChild(root_0, expression148.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "repeat"


    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:307:1: block : Indent ( NEWLINE | statement NEWLINE | block_statement )* Dedent ;
    public final Spin2GrammarParser.block_return block() throws RecognitionException {
        Spin2GrammarParser.block_return retval = new Spin2GrammarParser.block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Indent149=null;
        Token NEWLINE150=null;
        Token NEWLINE152=null;
        Token Dedent154=null;
        Spin2GrammarParser.statement_return statement151 =null;

        Spin2GrammarParser.block_statement_return block_statement153 =null;


        CommonTree Indent149_tree=null;
        CommonTree NEWLINE150_tree=null;
        CommonTree NEWLINE152_tree=null;
        CommonTree Dedent154_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:308:5: ( Indent ( NEWLINE | statement NEWLINE | block_statement )* Dedent )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:308:8: Indent ( NEWLINE | statement NEWLINE | block_statement )* Dedent
            {
            root_0 = (CommonTree)adaptor.nil();


            Indent149=(Token)match(input,Indent,FOLLOW_Indent_in_block1333); 
            Indent149_tree = 
            (CommonTree)adaptor.create(Indent149)
            ;
            adaptor.addChild(root_0, Indent149_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:309:8: ( NEWLINE | statement NEWLINE | block_statement )*
            loop43:
            do {
                int alt43=4;
                switch ( input.LA(1) ) {
                case NEWLINE:
                    {
                    alt43=1;
                    }
                    break;
                case BoolNotOp:
                case ID:
                case IncOp:
                case Unary2Op:
                case 64:
                case 68:
                case 72:
                case 73:
                case 81:
                case 82:
                case 89:
                case 97:
                case 100:
                case 101:
                    {
                    alt43=2;
                    }
                    break;
                case 74:
                case 79:
                case 88:
                    {
                    alt43=3;
                    }
                    break;

                }

                switch (alt43) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:309:9: NEWLINE
            	    {
            	    NEWLINE150=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_block1343); 
            	    NEWLINE150_tree = 
            	    (CommonTree)adaptor.create(NEWLINE150)
            	    ;
            	    adaptor.addChild(root_0, NEWLINE150_tree);


            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:309:19: statement NEWLINE
            	    {
            	    pushFollow(FOLLOW_statement_in_block1347);
            	    statement151=statement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, statement151.getTree());

            	    NEWLINE152=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_block1349); 
            	    NEWLINE152_tree = 
            	    (CommonTree)adaptor.create(NEWLINE152)
            	    ;
            	    adaptor.addChild(root_0, NEWLINE152_tree);


            	    }
            	    break;
            	case 3 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:309:39: block_statement
            	    {
            	    pushFollow(FOLLOW_block_statement_in_block1353);
            	    block_statement153=block_statement();

            	    state._fsp--;

            	    adaptor.addChild(root_0, block_statement153.getTree());

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            Dedent154=(Token)match(input,Dedent,FOLLOW_Dedent_in_block1368); 
            Dedent154_tree = 
            (CommonTree)adaptor.create(Dedent154)
            ;
            adaptor.addChild(root_0, Dedent154_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block"


    public static class array_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "array"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:316:1: array : '[' expression ']' ;
    public final Spin2GrammarParser.array_return array() throws RecognitionException {
        Spin2GrammarParser.array_return retval = new Spin2GrammarParser.array_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal155=null;
        Token char_literal157=null;
        Spin2GrammarParser.expression_return expression156 =null;


        CommonTree char_literal155_tree=null;
        CommonTree char_literal157_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:317:5: ( '[' expression ']' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:317:7: '[' expression ']'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal155=(Token)match(input,69,FOLLOW_69_in_array1396); 
            char_literal155_tree = 
            (CommonTree)adaptor.create(char_literal155)
            ;
            adaptor.addChild(root_0, char_literal155_tree);


            pushFollow(FOLLOW_expression_in_array1397);
            expression156=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression156.getTree());

            char_literal157=(Token)match(input,70,FOLLOW_70_in_array1398); 
            char_literal157_tree = 
            (CommonTree)adaptor.create(char_literal157)
            ;
            adaptor.addChild(root_0, char_literal157_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "array"


    public static class methodCall_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodCall"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:320:1: methodCall : ID paramlist ;
    public final Spin2GrammarParser.methodCall_return methodCall() throws RecognitionException {
        Spin2GrammarParser.methodCall_return retval = new Spin2GrammarParser.methodCall_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID158=null;
        Spin2GrammarParser.paramlist_return paramlist159 =null;


        CommonTree ID158_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:321:5: ( ID paramlist )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:321:7: ID paramlist
            {
            root_0 = (CommonTree)adaptor.nil();


            ID158=(Token)match(input,ID,FOLLOW_ID_in_methodCall1415); 
            ID158_tree = 
            (CommonTree)adaptor.create(ID158)
            ;
            adaptor.addChild(root_0, ID158_tree);


            pushFollow(FOLLOW_paramlist_in_methodCall1417);
            paramlist159=paramlist();

            state._fsp--;

            adaptor.addChild(root_0, paramlist159.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "methodCall"


    public static class objMethodCall_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objMethodCall"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:324:1: objMethodCall : ID '.' ID ( paramlist )? ;
    public final Spin2GrammarParser.objMethodCall_return objMethodCall() throws RecognitionException {
        Spin2GrammarParser.objMethodCall_return retval = new Spin2GrammarParser.objMethodCall_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID160=null;
        Token char_literal161=null;
        Token ID162=null;
        Spin2GrammarParser.paramlist_return paramlist163 =null;


        CommonTree ID160_tree=null;
        CommonTree char_literal161_tree=null;
        CommonTree ID162_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:325:5: ( ID '.' ID ( paramlist )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:325:7: ID '.' ID ( paramlist )?
            {
            root_0 = (CommonTree)adaptor.nil();


            ID160=(Token)match(input,ID,FOLLOW_ID_in_objMethodCall1438); 
            ID160_tree = 
            (CommonTree)adaptor.create(ID160)
            ;
            adaptor.addChild(root_0, ID160_tree);


            char_literal161=(Token)match(input,65,FOLLOW_65_in_objMethodCall1440); 
            char_literal161_tree = 
            (CommonTree)adaptor.create(char_literal161)
            ;
            adaptor.addChild(root_0, char_literal161_tree);


            ID162=(Token)match(input,ID,FOLLOW_ID_in_objMethodCall1442); 
            ID162_tree = 
            (CommonTree)adaptor.create(ID162)
            ;
            adaptor.addChild(root_0, ID162_tree);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:325:17: ( paramlist )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==60) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:325:17: paramlist
                    {
                    pushFollow(FOLLOW_paramlist_in_objMethodCall1444);
                    paramlist163=paramlist();

                    state._fsp--;

                    adaptor.addChild(root_0, paramlist163.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "objMethodCall"


    public static class objectConstant_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objectConstant"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:328:1: objectConstant : ID '#' ID ;
    public final Spin2GrammarParser.objectConstant_return objectConstant() throws RecognitionException {
        Spin2GrammarParser.objectConstant_return retval = new Spin2GrammarParser.objectConstant_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID164=null;
        Token char_literal165=null;
        Token ID166=null;

        CommonTree ID164_tree=null;
        CommonTree char_literal165_tree=null;
        CommonTree ID166_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:329:5: ( ID '#' ID )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:329:6: ID '#' ID
            {
            root_0 = (CommonTree)adaptor.nil();


            ID164=(Token)match(input,ID,FOLLOW_ID_in_objectConstant1465); 
            ID164_tree = 
            (CommonTree)adaptor.create(ID164)
            ;
            adaptor.addChild(root_0, ID164_tree);


            char_literal165=(Token)match(input,59,FOLLOW_59_in_objectConstant1467); 
            char_literal165_tree = 
            (CommonTree)adaptor.create(char_literal165)
            ;
            adaptor.addChild(root_0, char_literal165_tree);


            ID166=(Token)match(input,ID,FOLLOW_ID_in_objectConstant1469); 
            ID166_tree = 
            (CommonTree)adaptor.create(ID166)
            ;
            adaptor.addChild(root_0, ID166_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "objectConstant"


    public static class string_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "string"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:332:1: string : 'string' '(' ( STRING | CHARACTER ) ')' ;
    public final Spin2GrammarParser.string_return string() throws RecognitionException {
        Spin2GrammarParser.string_return retval = new Spin2GrammarParser.string_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal167=null;
        Token char_literal168=null;
        Token set169=null;
        Token char_literal170=null;

        CommonTree string_literal167_tree=null;
        CommonTree char_literal168_tree=null;
        CommonTree set169_tree=null;
        CommonTree char_literal170_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:333:5: ( 'string' '(' ( STRING | CHARACTER ) ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:333:7: 'string' '(' ( STRING | CHARACTER ) ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal167=(Token)match(input,92,FOLLOW_92_in_string1490); 
            string_literal167_tree = 
            (CommonTree)adaptor.create(string_literal167)
            ;
            adaptor.addChild(root_0, string_literal167_tree);


            char_literal168=(Token)match(input,60,FOLLOW_60_in_string1492); 
            char_literal168_tree = 
            (CommonTree)adaptor.create(char_literal168)
            ;
            adaptor.addChild(root_0, char_literal168_tree);


            set169=(Token)input.LT(1);

            if ( input.LA(1)==CHARACTER||input.LA(1)==STRING ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set169)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            char_literal170=(Token)match(input,61,FOLLOW_61_in_string1499); 
            char_literal170_tree = 
            (CommonTree)adaptor.create(char_literal170)
            ;
            adaptor.addChild(root_0, char_literal170_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "string"


    public static class setbits_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "setbits"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:336:1: setbits : id ( '~' | '~~' | '?' )? ;
    public final Spin2GrammarParser.setbits_return setbits() throws RecognitionException {
        Spin2GrammarParser.setbits_return retval = new Spin2GrammarParser.setbits_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set172=null;
        Spin2GrammarParser.id_return id171 =null;


        CommonTree set172_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:337:5: ( id ( '~' | '~~' | '?' )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:337:7: id ( '~' | '~~' | '?' )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_id_in_setbits1520);
            id171=id();

            state._fsp--;

            adaptor.addChild(root_0, id171.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:337:10: ( '~' | '~~' | '?' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==68) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==Assign||(LA45_1 >= BitAndOp && LA45_1 <= BoolAndOp)||(LA45_1 >= BoolOp && LA45_1 <= BoolOrOp)||LA45_1==Dedent||LA45_1==IncOp||LA45_1==LimitMaxOp||LA45_1==MultOp||LA45_1==Multiply||LA45_1==NEWLINE||LA45_1==ShiftOp||(LA45_1 >= 62 && LA45_1 <= 64)) ) {
                    alt45=1;
                }
                else if ( (LA45_1==EOF||LA45_1==BoolNotOp||LA45_1==ID||LA45_1==Indent||LA45_1==Range||LA45_1==Unary2Op||LA45_1==61||LA45_1==66||LA45_1==68||LA45_1==70||(LA45_1 >= 72 && LA45_1 <= 74)||(LA45_1 >= 76 && LA45_1 <= 77)||LA45_1==79||(LA45_1 >= 81 && LA45_1 <= 82)||(LA45_1 >= 88 && LA45_1 <= 90)||LA45_1==94||LA45_1==97||(LA45_1 >= 100 && LA45_1 <= 101)) ) {
                    alt45=1;
                }
            }
            else if ( ((LA45_0 >= 103 && LA45_0 <= 104)) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
                    {
                    set172=(Token)input.LT(1);

                    if ( input.LA(1)==68||(input.LA(1) >= 103 && input.LA(1) <= 104) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set172)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "setbits"


    public static class identifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "identifier"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:340:1: identifier : ( UnaryOp | '~' | '~~' )? setbits ;
    public final Spin2GrammarParser.identifier_return identifier() throws RecognitionException {
        Spin2GrammarParser.identifier_return retval = new Spin2GrammarParser.identifier_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set173=null;
        Spin2GrammarParser.setbits_return setbits174 =null;


        CommonTree set173_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:341:5: ( ( UnaryOp | '~' | '~~' )? setbits )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:341:7: ( UnaryOp | '~' | '~~' )? setbits
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:341:7: ( UnaryOp | '~' | '~~' )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==UnaryOp||(LA46_0 >= 103 && LA46_0 <= 104)) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
                    {
                    set173=(Token)input.LT(1);

                    if ( input.LA(1)==UnaryOp||(input.LA(1) >= 103 && input.LA(1) <= 104) ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set173)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_setbits_in_identifier1559);
            setbits174=setbits();

            state._fsp--;

            adaptor.addChild(root_0, setbits174.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "identifier"


    public static class rand_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rand"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:344:1: rand : '?' id -> ^( RAND id ) ;
    public final Spin2GrammarParser.rand_return rand() throws RecognitionException {
        Spin2GrammarParser.rand_return retval = new Spin2GrammarParser.rand_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal175=null;
        Spin2GrammarParser.id_return id176 =null;


        CommonTree char_literal175_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:345:5: ( '?' id -> ^( RAND id ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:345:7: '?' id
            {
            char_literal175=(Token)match(input,68,FOLLOW_68_in_rand1581);  
            stream_68.add(char_literal175);


            pushFollow(FOLLOW_id_in_rand1583);
            id176=id();

            state._fsp--;

            stream_id.add(id176.getTree());

            // AST REWRITE
            // elements: id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 345:14: -> ^( RAND id )
            {
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:345:17: ^( RAND id )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(RAND, "RAND")
                , root_1);

                adaptor.addChild(root_1, stream_id.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rand"


    public static class unary_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:348:1: unary_statement : ( Unary2Op )+ unary2 -> ^( UNARYOP unary2 ( Unary2Op )+ ) ;
    public final Spin2GrammarParser.unary_statement_return unary_statement() throws RecognitionException {
        Spin2GrammarParser.unary_statement_return retval = new Spin2GrammarParser.unary_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Unary2Op177=null;
        Spin2GrammarParser.unary2_return unary2178 =null;


        CommonTree Unary2Op177_tree=null;
        RewriteRuleTokenStream stream_Unary2Op=new RewriteRuleTokenStream(adaptor,"token Unary2Op");
        RewriteRuleSubtreeStream stream_unary2=new RewriteRuleSubtreeStream(adaptor,"rule unary2");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:349:5: ( ( Unary2Op )+ unary2 -> ^( UNARYOP unary2 ( Unary2Op )+ ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:349:7: ( Unary2Op )+ unary2
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:349:7: ( Unary2Op )+
            int cnt47=0;
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==Unary2Op) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:349:8: Unary2Op
            	    {
            	    Unary2Op177=(Token)match(input,Unary2Op,FOLLOW_Unary2Op_in_unary_statement1610);  
            	    stream_Unary2Op.add(Unary2Op177);


            	    }
            	    break;

            	default :
            	    if ( cnt47 >= 1 ) break loop47;
                        EarlyExitException eee =
                            new EarlyExitException(47, input);
                        throw eee;
                }
                cnt47++;
            } while (true);


            pushFollow(FOLLOW_unary2_in_unary_statement1614);
            unary2178=unary2();

            state._fsp--;

            stream_unary2.add(unary2178.getTree());

            // AST REWRITE
            // elements: unary2, Unary2Op
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 349:26: -> ^( UNARYOP unary2 ( Unary2Op )+ )
            {
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:349:28: ^( UNARYOP unary2 ( Unary2Op )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(UNARYOP, "UNARYOP")
                , root_1);

                adaptor.addChild(root_1, stream_unary2.nextTree());

                if ( !(stream_Unary2Op.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_Unary2Op.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_Unary2Op.nextNode()
                    );

                }
                stream_Unary2Op.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary_statement"


    public static class strcomp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "strcomp"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:354:1: strcomp : 'strcomp' '(' expression ',' expression ')' ;
    public final Spin2GrammarParser.strcomp_return strcomp() throws RecognitionException {
        Spin2GrammarParser.strcomp_return retval = new Spin2GrammarParser.strcomp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal179=null;
        Token char_literal180=null;
        Token char_literal182=null;
        Token char_literal184=null;
        Spin2GrammarParser.expression_return expression181 =null;

        Spin2GrammarParser.expression_return expression183 =null;


        CommonTree string_literal179_tree=null;
        CommonTree char_literal180_tree=null;
        CommonTree char_literal182_tree=null;
        CommonTree char_literal184_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:355:5: ( 'strcomp' '(' expression ',' expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:355:7: 'strcomp' '(' expression ',' expression ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal179=(Token)match(input,91,FOLLOW_91_in_strcomp1649); 
            string_literal179_tree = 
            (CommonTree)adaptor.create(string_literal179)
            ;
            adaptor.addChild(root_0, string_literal179_tree);


            char_literal180=(Token)match(input,60,FOLLOW_60_in_strcomp1650); 
            char_literal180_tree = 
            (CommonTree)adaptor.create(char_literal180)
            ;
            adaptor.addChild(root_0, char_literal180_tree);


            pushFollow(FOLLOW_expression_in_strcomp1651);
            expression181=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression181.getTree());

            char_literal182=(Token)match(input,63,FOLLOW_63_in_strcomp1653); 
            char_literal182_tree = 
            (CommonTree)adaptor.create(char_literal182)
            ;
            adaptor.addChild(root_0, char_literal182_tree);


            pushFollow(FOLLOW_expression_in_strcomp1655);
            expression183=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression183.getTree());

            char_literal184=(Token)match(input,61,FOLLOW_61_in_strcomp1656); 
            char_literal184_tree = 
            (CommonTree)adaptor.create(char_literal184)
            ;
            adaptor.addChild(root_0, char_literal184_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "strcomp"


    public static class strsize_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "strsize"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:358:1: strsize : 'strsize' '(' expression ')' ;
    public final Spin2GrammarParser.strsize_return strsize() throws RecognitionException {
        Spin2GrammarParser.strsize_return retval = new Spin2GrammarParser.strsize_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal185=null;
        Token char_literal186=null;
        Token char_literal188=null;
        Spin2GrammarParser.expression_return expression187 =null;


        CommonTree string_literal185_tree=null;
        CommonTree char_literal186_tree=null;
        CommonTree char_literal188_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:359:5: ( 'strsize' '(' expression ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:359:7: 'strsize' '(' expression ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal185=(Token)match(input,93,FOLLOW_93_in_strsize1677); 
            string_literal185_tree = 
            (CommonTree)adaptor.create(string_literal185)
            ;
            adaptor.addChild(root_0, string_literal185_tree);


            char_literal186=(Token)match(input,60,FOLLOW_60_in_strsize1678); 
            char_literal186_tree = 
            (CommonTree)adaptor.create(char_literal186)
            ;
            adaptor.addChild(root_0, char_literal186_tree);


            pushFollow(FOLLOW_expression_in_strsize1679);
            expression187=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression187.getTree());

            char_literal188=(Token)match(input,61,FOLLOW_61_in_strsize1680); 
            char_literal188_tree = 
            (CommonTree)adaptor.create(char_literal188)
            ;
            adaptor.addChild(root_0, char_literal188_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "strsize"


    public static class lookup_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lookup"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:362:1: lookup : ( 'lookup' | 'lookupz' ) '(' expression ':' rangelist ')' ;
    public final Spin2GrammarParser.lookup_return lookup() throws RecognitionException {
        Spin2GrammarParser.lookup_return retval = new Spin2GrammarParser.lookup_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set189=null;
        Token char_literal190=null;
        Token char_literal192=null;
        Token char_literal194=null;
        Spin2GrammarParser.expression_return expression191 =null;

        Spin2GrammarParser.rangelist_return rangelist193 =null;


        CommonTree set189_tree=null;
        CommonTree char_literal190_tree=null;
        CommonTree char_literal192_tree=null;
        CommonTree char_literal194_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:363:5: ( ( 'lookup' | 'lookupz' ) '(' expression ':' rangelist ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:363:7: ( 'lookup' | 'lookupz' ) '(' expression ':' rangelist ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            set189=(Token)input.LT(1);

            if ( (input.LA(1) >= 85 && input.LA(1) <= 86) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set189)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            char_literal190=(Token)match(input,60,FOLLOW_60_in_lookup1707); 
            char_literal190_tree = 
            (CommonTree)adaptor.create(char_literal190)
            ;
            adaptor.addChild(root_0, char_literal190_tree);


            pushFollow(FOLLOW_expression_in_lookup1708);
            expression191=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression191.getTree());

            char_literal192=(Token)match(input,66,FOLLOW_66_in_lookup1709); 
            char_literal192_tree = 
            (CommonTree)adaptor.create(char_literal192)
            ;
            adaptor.addChild(root_0, char_literal192_tree);


            pushFollow(FOLLOW_rangelist_in_lookup1711);
            rangelist193=rangelist();

            state._fsp--;

            adaptor.addChild(root_0, rangelist193.getTree());

            char_literal194=(Token)match(input,61,FOLLOW_61_in_lookup1712); 
            char_literal194_tree = 
            (CommonTree)adaptor.create(char_literal194)
            ;
            adaptor.addChild(root_0, char_literal194_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "lookup"


    public static class lookdown_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lookdown"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:366:1: lookdown : ( 'lookdown' | 'lookdownz' ) '(' expression ':' rangelist ')' ;
    public final Spin2GrammarParser.lookdown_return lookdown() throws RecognitionException {
        Spin2GrammarParser.lookdown_return retval = new Spin2GrammarParser.lookdown_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set195=null;
        Token char_literal196=null;
        Token char_literal198=null;
        Token char_literal200=null;
        Spin2GrammarParser.expression_return expression197 =null;

        Spin2GrammarParser.rangelist_return rangelist199 =null;


        CommonTree set195_tree=null;
        CommonTree char_literal196_tree=null;
        CommonTree char_literal198_tree=null;
        CommonTree char_literal200_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:367:5: ( ( 'lookdown' | 'lookdownz' ) '(' expression ':' rangelist ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:367:7: ( 'lookdown' | 'lookdownz' ) '(' expression ':' rangelist ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            set195=(Token)input.LT(1);

            if ( (input.LA(1) >= 83 && input.LA(1) <= 84) ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set195)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            char_literal196=(Token)match(input,60,FOLLOW_60_in_lookdown1739); 
            char_literal196_tree = 
            (CommonTree)adaptor.create(char_literal196)
            ;
            adaptor.addChild(root_0, char_literal196_tree);


            pushFollow(FOLLOW_expression_in_lookdown1740);
            expression197=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression197.getTree());

            char_literal198=(Token)match(input,66,FOLLOW_66_in_lookdown1741); 
            char_literal198_tree = 
            (CommonTree)adaptor.create(char_literal198)
            ;
            adaptor.addChild(root_0, char_literal198_tree);


            pushFollow(FOLLOW_rangelist_in_lookdown1743);
            rangelist199=rangelist();

            state._fsp--;

            adaptor.addChild(root_0, rangelist199.getTree());

            char_literal200=(Token)match(input,61,FOLLOW_61_in_lookdown1744); 
            char_literal200_tree = 
            (CommonTree)adaptor.create(char_literal200)
            ;
            adaptor.addChild(root_0, char_literal200_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "lookdown"


    public static class rangelist_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rangelist"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:370:1: rangelist : rangeitem ( ',' rangeitem )* ;
    public final Spin2GrammarParser.rangelist_return rangelist() throws RecognitionException {
        Spin2GrammarParser.rangelist_return retval = new Spin2GrammarParser.rangelist_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal202=null;
        Spin2GrammarParser.rangeitem_return rangeitem201 =null;

        Spin2GrammarParser.rangeitem_return rangeitem203 =null;


        CommonTree char_literal202_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:371:5: ( rangeitem ( ',' rangeitem )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:371:7: rangeitem ( ',' rangeitem )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_rangeitem_in_rangelist1764);
            rangeitem201=rangeitem();

            state._fsp--;

            adaptor.addChild(root_0, rangeitem201.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:371:17: ( ',' rangeitem )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==63) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:371:18: ',' rangeitem
            	    {
            	    char_literal202=(Token)match(input,63,FOLLOW_63_in_rangelist1767); 
            	    char_literal202_tree = 
            	    (CommonTree)adaptor.create(char_literal202)
            	    ;
            	    adaptor.addChild(root_0, char_literal202_tree);


            	    pushFollow(FOLLOW_rangeitem_in_rangelist1768);
            	    rangeitem203=rangeitem();

            	    state._fsp--;

            	    adaptor.addChild(root_0, rangeitem203.getTree());

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rangelist"


    public static class rangeitem_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rangeitem"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:374:1: rangeitem : expression ( '..' expression )? ;
    public final Spin2GrammarParser.rangeitem_return rangeitem() throws RecognitionException {
        Spin2GrammarParser.rangeitem_return retval = new Spin2GrammarParser.rangeitem_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal205=null;
        Spin2GrammarParser.expression_return expression204 =null;

        Spin2GrammarParser.expression_return expression206 =null;


        CommonTree string_literal205_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:375:5: ( expression ( '..' expression )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:375:7: expression ( '..' expression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expression_in_rangeitem1791);
            expression204=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression204.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:375:18: ( '..' expression )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==Range) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:375:19: '..' expression
                    {
                    string_literal205=(Token)match(input,Range,FOLLOW_Range_in_rangeitem1794); 
                    string_literal205_tree = 
                    (CommonTree)adaptor.create(string_literal205)
                    ;
                    adaptor.addChild(root_0, string_literal205_tree);


                    pushFollow(FOLLOW_expression_in_rangeitem1795);
                    expression206=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression206.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "rangeitem"


    public static class assignment_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:378:1: assignment : id Assign expression ;
    public final Spin2GrammarParser.assignment_return assignment() throws RecognitionException {
        Spin2GrammarParser.assignment_return retval = new Spin2GrammarParser.assignment_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Assign208=null;
        Spin2GrammarParser.id_return id207 =null;

        Spin2GrammarParser.expression_return expression209 =null;


        CommonTree Assign208_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:379:5: ( id Assign expression )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:379:7: id Assign expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_id_in_assignment1818);
            id207=id();

            state._fsp--;

            adaptor.addChild(root_0, id207.getTree());

            Assign208=(Token)match(input,Assign,FOLLOW_Assign_in_assignment1820); 
            Assign208_tree = 
            (CommonTree)adaptor.create(Assign208)
            ;
            adaptor.addChild(root_0, Assign208_tree);


            pushFollow(FOLLOW_expression_in_assignment1822);
            expression209=expression();

            state._fsp--;

            adaptor.addChild(root_0, expression209.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment"


    public static class ident_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ident"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:386:1: ident : ( objMethodCall | methodCall | objectConstant | string | identifier | rand | strcomp | strsize | lookup | lookdown | CHARACTER | True | False );
    public final Spin2GrammarParser.ident_return ident() throws RecognitionException {
        Spin2GrammarParser.ident_return retval = new Spin2GrammarParser.ident_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token CHARACTER220=null;
        Token True221=null;
        Token False222=null;
        Spin2GrammarParser.objMethodCall_return objMethodCall210 =null;

        Spin2GrammarParser.methodCall_return methodCall211 =null;

        Spin2GrammarParser.objectConstant_return objectConstant212 =null;

        Spin2GrammarParser.string_return string213 =null;

        Spin2GrammarParser.identifier_return identifier214 =null;

        Spin2GrammarParser.rand_return rand215 =null;

        Spin2GrammarParser.strcomp_return strcomp216 =null;

        Spin2GrammarParser.strsize_return strsize217 =null;

        Spin2GrammarParser.lookup_return lookup218 =null;

        Spin2GrammarParser.lookdown_return lookdown219 =null;


        CommonTree CHARACTER220_tree=null;
        CommonTree True221_tree=null;
        CommonTree False222_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:387:5: ( objMethodCall | methodCall | objectConstant | string | identifier | rand | strcomp | strsize | lookup | lookdown | CHARACTER | True | False )
            int alt50=13;
            switch ( input.LA(1) ) {
            case ID:
                {
                switch ( input.LA(2) ) {
                case 65:
                    {
                    alt50=1;
                    }
                    break;
                case 59:
                    {
                    alt50=3;
                    }
                    break;
                case 60:
                    {
                    alt50=2;
                    }
                    break;
                case EOF:
                case Assign:
                case BitAndOp:
                case BitOrOp:
                case BoolAndOp:
                case BoolNotOp:
                case BoolOp:
                case BoolOrOp:
                case Dedent:
                case ID:
                case IncOp:
                case Indent:
                case LimitMaxOp:
                case MultOp:
                case Multiply:
                case NEWLINE:
                case Range:
                case ShiftOp:
                case Unary2Op:
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
                case 88:
                case 89:
                case 90:
                case 94:
                case 97:
                case 100:
                case 101:
                case 103:
                case 104:
                    {
                    alt50=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    throw nvae;

                }

                }
                break;
            case 92:
                {
                alt50=4;
                }
                break;
            case UnaryOp:
            case 103:
            case 104:
                {
                alt50=5;
                }
                break;
            case 68:
                {
                alt50=6;
                }
                break;
            case 91:
                {
                alt50=7;
                }
                break;
            case 93:
                {
                alt50=8;
                }
                break;
            case 85:
            case 86:
                {
                alt50=9;
                }
                break;
            case 83:
            case 84:
                {
                alt50=10;
                }
                break;
            case CHARACTER:
                {
                alt50=11;
                }
                break;
            case True:
                {
                alt50=12;
                }
                break;
            case False:
                {
                alt50=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }

            switch (alt50) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:387:7: objMethodCall
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_objMethodCall_in_ident1847);
                    objMethodCall210=objMethodCall();

                    state._fsp--;

                    adaptor.addChild(root_0, objMethodCall210.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:388:7: methodCall
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_methodCall_in_ident1856);
                    methodCall211=methodCall();

                    state._fsp--;

                    adaptor.addChild(root_0, methodCall211.getTree());

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:389:7: objectConstant
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_objectConstant_in_ident1865);
                    objectConstant212=objectConstant();

                    state._fsp--;

                    adaptor.addChild(root_0, objectConstant212.getTree());

                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:390:7: string
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_string_in_ident1873);
                    string213=string();

                    state._fsp--;

                    adaptor.addChild(root_0, string213.getTree());

                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:391:7: identifier
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_identifier_in_ident1883);
                    identifier214=identifier();

                    state._fsp--;

                    adaptor.addChild(root_0, identifier214.getTree());

                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:392:7: rand
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_rand_in_ident1891);
                    rand215=rand();

                    state._fsp--;

                    adaptor.addChild(root_0, rand215.getTree());

                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:393:7: strcomp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_strcomp_in_ident1899);
                    strcomp216=strcomp();

                    state._fsp--;

                    adaptor.addChild(root_0, strcomp216.getTree());

                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:394:7: strsize
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_strsize_in_ident1907);
                    strsize217=strsize();

                    state._fsp--;

                    adaptor.addChild(root_0, strsize217.getTree());

                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:395:7: lookup
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_lookup_in_ident1915);
                    lookup218=lookup();

                    state._fsp--;

                    adaptor.addChild(root_0, lookup218.getTree());

                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:396:7: lookdown
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_lookdown_in_ident1923);
                    lookdown219=lookdown();

                    state._fsp--;

                    adaptor.addChild(root_0, lookdown219.getTree());

                    }
                    break;
                case 11 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:397:7: CHARACTER
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    CHARACTER220=(Token)match(input,CHARACTER,FOLLOW_CHARACTER_in_ident1931); 
                    CHARACTER220_tree = 
                    (CommonTree)adaptor.create(CHARACTER220)
                    ;
                    adaptor.addChild(root_0, CHARACTER220_tree);


                    }
                    break;
                case 12 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:398:7: True
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    True221=(Token)match(input,True,FOLLOW_True_in_ident1939); 
                    True221_tree = 
                    (CommonTree)adaptor.create(True221)
                    ;
                    adaptor.addChild(root_0, True221_tree);


                    }
                    break;
                case 13 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:399:7: False
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    False222=(Token)match(input,False,FOLLOW_False_in_ident1948); 
                    False222_tree = 
                    (CommonTree)adaptor.create(False222)
                    ;
                    adaptor.addChild(root_0, False222_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ident"


    public static class number_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "number"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:429:2: number : ( INT | FLOAT | BINARY | QUATERNARY | HEXADECIMAL );
    public final Spin2GrammarParser.number_return number() throws RecognitionException {
        Spin2GrammarParser.number_return retval = new Spin2GrammarParser.number_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set223=null;

        CommonTree set223_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:430:5: ( INT | FLOAT | BINARY | QUATERNARY | HEXADECIMAL )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set223=(Token)input.LT(1);

            if ( input.LA(1)==BINARY||input.LA(1)==FLOAT||input.LA(1)==HEXADECIMAL||input.LA(1)==INT||input.LA(1)==QUATERNARY ) {
                input.consume();
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set223)
                );
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "number"


    public static class term_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "term"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:437:1: term : ( ident | '(' ! expression ')' !| number );
    public final Spin2GrammarParser.term_return term() throws RecognitionException {
        Spin2GrammarParser.term_return retval = new Spin2GrammarParser.term_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal225=null;
        Token char_literal227=null;
        Spin2GrammarParser.ident_return ident224 =null;

        Spin2GrammarParser.expression_return expression226 =null;

        Spin2GrammarParser.number_return number228 =null;


        CommonTree char_literal225_tree=null;
        CommonTree char_literal227_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:438:5: ( ident | '(' ! expression ')' !| number )
            int alt51=3;
            switch ( input.LA(1) ) {
            case CHARACTER:
            case False:
            case ID:
            case True:
            case UnaryOp:
            case 68:
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
                alt51=1;
                }
                break;
            case 60:
                {
                alt51=2;
                }
                break;
            case BINARY:
            case FLOAT:
            case HEXADECIMAL:
            case INT:
            case QUATERNARY:
                {
                alt51=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }

            switch (alt51) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:438:7: ident
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ident_in_term2256);
                    ident224=ident();

                    state._fsp--;

                    adaptor.addChild(root_0, ident224.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:439:7: '(' ! expression ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal225=(Token)match(input,60,FOLLOW_60_in_term2265); 

                    pushFollow(FOLLOW_expression_in_term2268);
                    expression226=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression226.getTree());

                    char_literal227=(Token)match(input,61,FOLLOW_61_in_term2270); 

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:440:7: number
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_number_in_term2280);
                    number228=number();

                    state._fsp--;

                    adaptor.addChild(root_0, number228.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "term"


    public static class preinc_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "preinc"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:444:1: preinc : ( IncOp term -> ^( PRE IncOp term ) | term );
    public final Spin2GrammarParser.preinc_return preinc() throws RecognitionException {
        Spin2GrammarParser.preinc_return retval = new Spin2GrammarParser.preinc_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IncOp229=null;
        Spin2GrammarParser.term_return term230 =null;

        Spin2GrammarParser.term_return term231 =null;


        CommonTree IncOp229_tree=null;
        RewriteRuleTokenStream stream_IncOp=new RewriteRuleTokenStream(adaptor,"token IncOp");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:445:5: ( IncOp term -> ^( PRE IncOp term ) | term )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==IncOp) ) {
                alt52=1;
            }
            else if ( (LA52_0==BINARY||LA52_0==CHARACTER||(LA52_0 >= FLOAT && LA52_0 <= HEXADECIMAL)||(LA52_0 >= ID && LA52_0 <= INT)||LA52_0==QUATERNARY||LA52_0==True||LA52_0==UnaryOp||LA52_0==60||LA52_0==68||(LA52_0 >= 83 && LA52_0 <= 86)||(LA52_0 >= 91 && LA52_0 <= 93)||(LA52_0 >= 103 && LA52_0 <= 104)) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:445:7: IncOp term
                    {
                    IncOp229=(Token)match(input,IncOp,FOLLOW_IncOp_in_preinc2308);  
                    stream_IncOp.add(IncOp229);


                    pushFollow(FOLLOW_term_in_preinc2310);
                    term230=term();

                    state._fsp--;

                    stream_term.add(term230.getTree());

                    // AST REWRITE
                    // elements: IncOp, term
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 445:18: -> ^( PRE IncOp term )
                    {
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:445:21: ^( PRE IncOp term )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PRE, "PRE")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_IncOp.nextNode()
                        );

                        adaptor.addChild(root_1, stream_term.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:446:7: term
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_term_in_preinc2329);
                    term231=term();

                    state._fsp--;

                    adaptor.addChild(root_0, term231.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "preinc"


    public static class postinc_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "postinc"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:449:1: postinc : (p= preinc -> preinc ) ( IncOp -> ^( POST IncOp $p) )* ;
    public final Spin2GrammarParser.postinc_return postinc() throws RecognitionException {
        Spin2GrammarParser.postinc_return retval = new Spin2GrammarParser.postinc_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token IncOp232=null;
        Spin2GrammarParser.preinc_return p =null;


        CommonTree IncOp232_tree=null;
        RewriteRuleTokenStream stream_IncOp=new RewriteRuleTokenStream(adaptor,"token IncOp");
        RewriteRuleSubtreeStream stream_preinc=new RewriteRuleSubtreeStream(adaptor,"rule preinc");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:450:5: ( (p= preinc -> preinc ) ( IncOp -> ^( POST IncOp $p) )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:450:7: (p= preinc -> preinc ) ( IncOp -> ^( POST IncOp $p) )*
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:450:7: (p= preinc -> preinc )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:450:8: p= preinc
            {
            pushFollow(FOLLOW_preinc_in_postinc2350);
            p=preinc();

            state._fsp--;

            stream_preinc.add(p.getTree());

            // AST REWRITE
            // elements: preinc
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 450:16: -> preinc
            {
                adaptor.addChild(root_0, stream_preinc.nextTree());

            }


            retval.tree = root_0;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:451:5: ( IncOp -> ^( POST IncOp $p) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==IncOp) ) {
                    int LA53_2 = input.LA(2);

                    if ( (LA53_2==EOF||LA53_2==Assign||(LA53_2 >= BitAndOp && LA53_2 <= BoolOrOp)||LA53_2==Dedent||LA53_2==ID||(LA53_2 >= IncOp && LA53_2 <= LimitMaxOp)||LA53_2==MultOp||LA53_2==Multiply||LA53_2==NEWLINE||LA53_2==Range||LA53_2==ShiftOp||LA53_2==Unary2Op||(LA53_2 >= 61 && LA53_2 <= 64)||LA53_2==66||LA53_2==68||LA53_2==70||(LA53_2 >= 72 && LA53_2 <= 74)||(LA53_2 >= 76 && LA53_2 <= 77)||LA53_2==79||(LA53_2 >= 81 && LA53_2 <= 82)||(LA53_2 >= 88 && LA53_2 <= 90)||LA53_2==94||LA53_2==97||(LA53_2 >= 100 && LA53_2 <= 101)) ) {
                        alt53=1;
                    }


                }


                switch (alt53) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:451:6: IncOp
            	    {
            	    IncOp232=(Token)match(input,IncOp,FOLLOW_IncOp_in_postinc2361);  
            	    stream_IncOp.add(IncOp232);


            	    // AST REWRITE
            	    // elements: p, IncOp
            	    // token labels: 
            	    // rule labels: retval, p
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            	    RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 451:12: -> ^( POST IncOp $p)
            	    {
            	        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:451:15: ^( POST IncOp $p)
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        (CommonTree)adaptor.create(POST, "POST")
            	        , root_1);

            	        adaptor.addChild(root_1, 
            	        stream_IncOp.nextNode()
            	        );

            	        adaptor.addChild(root_1, stream_p.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "postinc"


    public static class unary2_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary2"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:454:1: unary2 : ( '+' !| negation ^)* postinc ;
    public final Spin2GrammarParser.unary2_return unary2() throws RecognitionException {
        Spin2GrammarParser.unary2_return retval = new Spin2GrammarParser.unary2_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal233=null;
        Spin2GrammarParser.negation_return negation234 =null;

        Spin2GrammarParser.postinc_return postinc235 =null;


        CommonTree char_literal233_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:455:5: ( ( '+' !| negation ^)* postinc )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:455:7: ( '+' !| negation ^)* postinc
            {
            root_0 = (CommonTree)adaptor.nil();


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:455:7: ( '+' !| negation ^)*
            loop54:
            do {
                int alt54=3;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==62) ) {
                    alt54=1;
                }
                else if ( (LA54_0==64) ) {
                    alt54=2;
                }


                switch (alt54) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:455:8: '+' !
            	    {
            	    char_literal233=(Token)match(input,62,FOLLOW_62_in_unary22397); 

            	    }
            	    break;
            	case 2 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:455:13: negation ^
            	    {
            	    pushFollow(FOLLOW_negation_in_unary22400);
            	    negation234=negation();

            	    state._fsp--;

            	    root_0 = (CommonTree)adaptor.becomeRoot(negation234.getTree(), root_0);

            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);


            pushFollow(FOLLOW_postinc_in_unary22405);
            postinc235=postinc();

            state._fsp--;

            adaptor.addChild(root_0, postinc235.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary2"


    public static class negation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "negation"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:457:1: negation : '-' -> NEGATION ;
    public final Spin2GrammarParser.negation_return negation() throws RecognitionException {
        Spin2GrammarParser.negation_return retval = new Spin2GrammarParser.negation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal236=null;

        CommonTree char_literal236_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:458:3: ( '-' -> NEGATION )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:458:5: '-'
            {
            char_literal236=(Token)match(input,64,FOLLOW_64_in_negation2419);  
            stream_64.add(char_literal236);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 458:9: -> NEGATION
            {
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(NEGATION, "NEGATION")
                );

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "negation"


    public static class unary3_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary3"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:465:1: unary3 : ( unary2 | unary_statement );
    public final Spin2GrammarParser.unary3_return unary3() throws RecognitionException {
        Spin2GrammarParser.unary3_return retval = new Spin2GrammarParser.unary3_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Spin2GrammarParser.unary2_return unary2237 =null;

        Spin2GrammarParser.unary_statement_return unary_statement238 =null;



        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:466:5: ( unary2 | unary_statement )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==BINARY||LA55_0==CHARACTER||(LA55_0 >= FLOAT && LA55_0 <= HEXADECIMAL)||(LA55_0 >= ID && LA55_0 <= IncOp)||LA55_0==QUATERNARY||LA55_0==True||LA55_0==UnaryOp||LA55_0==60||LA55_0==62||LA55_0==64||LA55_0==68||(LA55_0 >= 83 && LA55_0 <= 86)||(LA55_0 >= 91 && LA55_0 <= 93)||(LA55_0 >= 103 && LA55_0 <= 104)) ) {
                alt55=1;
            }
            else if ( (LA55_0==Unary2Op) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:466:7: unary2
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_unary2_in_unary32441);
                    unary2237=unary2();

                    state._fsp--;

                    adaptor.addChild(root_0, unary2237.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:467:7: unary_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_unary_statement_in_unary32449);
                    unary_statement238=unary_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, unary_statement238.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary3"


    public static class shift_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shift"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:471:1: shift : unary3 ( ShiftOp ^ unary3 )* ;
    public final Spin2GrammarParser.shift_return shift() throws RecognitionException {
        Spin2GrammarParser.shift_return retval = new Spin2GrammarParser.shift_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ShiftOp240=null;
        Spin2GrammarParser.unary3_return unary3239 =null;

        Spin2GrammarParser.unary3_return unary3241 =null;


        CommonTree ShiftOp240_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:472:5: ( unary3 ( ShiftOp ^ unary3 )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:472:7: unary3 ( ShiftOp ^ unary3 )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unary3_in_shift2476);
            unary3239=unary3();

            state._fsp--;

            adaptor.addChild(root_0, unary3239.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:472:14: ( ShiftOp ^ unary3 )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==ShiftOp) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:472:15: ShiftOp ^ unary3
            	    {
            	    ShiftOp240=(Token)match(input,ShiftOp,FOLLOW_ShiftOp_in_shift2479); 
            	    ShiftOp240_tree = 
            	    (CommonTree)adaptor.create(ShiftOp240)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(ShiftOp240_tree, root_0);


            	    pushFollow(FOLLOW_unary3_in_shift2482);
            	    unary3241=unary3();

            	    state._fsp--;

            	    adaptor.addChild(root_0, unary3241.getTree());

            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "shift"


    public static class bitand_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bitand"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:475:1: bitand : shift ( BitAndOp ^ shift )* ;
    public final Spin2GrammarParser.bitand_return bitand() throws RecognitionException {
        Spin2GrammarParser.bitand_return retval = new Spin2GrammarParser.bitand_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BitAndOp243=null;
        Spin2GrammarParser.shift_return shift242 =null;

        Spin2GrammarParser.shift_return shift244 =null;


        CommonTree BitAndOp243_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:476:5: ( shift ( BitAndOp ^ shift )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:476:7: shift ( BitAndOp ^ shift )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_shift_in_bitand2507);
            shift242=shift();

            state._fsp--;

            adaptor.addChild(root_0, shift242.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:476:13: ( BitAndOp ^ shift )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==BitAndOp) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:476:14: BitAndOp ^ shift
            	    {
            	    BitAndOp243=(Token)match(input,BitAndOp,FOLLOW_BitAndOp_in_bitand2510); 
            	    BitAndOp243_tree = 
            	    (CommonTree)adaptor.create(BitAndOp243)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(BitAndOp243_tree, root_0);


            	    pushFollow(FOLLOW_shift_in_bitand2513);
            	    shift244=shift();

            	    state._fsp--;

            	    adaptor.addChild(root_0, shift244.getTree());

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bitand"


    public static class bitor_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bitor"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:479:1: bitor : bitand ( BitOrOp ^ bitand )* ;
    public final Spin2GrammarParser.bitor_return bitor() throws RecognitionException {
        Spin2GrammarParser.bitor_return retval = new Spin2GrammarParser.bitor_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BitOrOp246=null;
        Spin2GrammarParser.bitand_return bitand245 =null;

        Spin2GrammarParser.bitand_return bitand247 =null;


        CommonTree BitOrOp246_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:480:5: ( bitand ( BitOrOp ^ bitand )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:480:7: bitand ( BitOrOp ^ bitand )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_bitand_in_bitor2537);
            bitand245=bitand();

            state._fsp--;

            adaptor.addChild(root_0, bitand245.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:480:14: ( BitOrOp ^ bitand )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==BitOrOp) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:480:15: BitOrOp ^ bitand
            	    {
            	    BitOrOp246=(Token)match(input,BitOrOp,FOLLOW_BitOrOp_in_bitor2540); 
            	    BitOrOp246_tree = 
            	    (CommonTree)adaptor.create(BitOrOp246)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(BitOrOp246_tree, root_0);


            	    pushFollow(FOLLOW_bitand_in_bitor2543);
            	    bitand247=bitand();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bitand247.getTree());

            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bitor"


    public static class multhigh_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multhigh"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:483:1: multhigh : bitor ( Multiply ^ bitor )* ;
    public final Spin2GrammarParser.multhigh_return multhigh() throws RecognitionException {
        Spin2GrammarParser.multhigh_return retval = new Spin2GrammarParser.multhigh_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Multiply249=null;
        Spin2GrammarParser.bitor_return bitor248 =null;

        Spin2GrammarParser.bitor_return bitor250 =null;


        CommonTree Multiply249_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:484:5: ( bitor ( Multiply ^ bitor )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:484:7: bitor ( Multiply ^ bitor )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_bitor_in_multhigh2567);
            bitor248=bitor();

            state._fsp--;

            adaptor.addChild(root_0, bitor248.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:484:12: ( Multiply ^ bitor )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==Multiply) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:484:13: Multiply ^ bitor
            	    {
            	    Multiply249=(Token)match(input,Multiply,FOLLOW_Multiply_in_multhigh2569); 
            	    Multiply249_tree = 
            	    (CommonTree)adaptor.create(Multiply249)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(Multiply249_tree, root_0);


            	    pushFollow(FOLLOW_bitor_in_multhigh2572);
            	    bitor250=bitor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bitor250.getTree());

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multhigh"


    public static class mult_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mult"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:487:1: mult : multhigh ( MultOp ^ multhigh )* ;
    public final Spin2GrammarParser.mult_return mult() throws RecognitionException {
        Spin2GrammarParser.mult_return retval = new Spin2GrammarParser.mult_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token MultOp252=null;
        Spin2GrammarParser.multhigh_return multhigh251 =null;

        Spin2GrammarParser.multhigh_return multhigh253 =null;


        CommonTree MultOp252_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:488:5: ( multhigh ( MultOp ^ multhigh )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:488:7: multhigh ( MultOp ^ multhigh )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multhigh_in_mult2592);
            multhigh251=multhigh();

            state._fsp--;

            adaptor.addChild(root_0, multhigh251.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:488:15: ( MultOp ^ multhigh )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==MultOp) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:488:16: MultOp ^ multhigh
            	    {
            	    MultOp252=(Token)match(input,MultOp,FOLLOW_MultOp_in_mult2594); 
            	    MultOp252_tree = 
            	    (CommonTree)adaptor.create(MultOp252)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(MultOp252_tree, root_0);


            	    pushFollow(FOLLOW_multhigh_in_mult2597);
            	    multhigh253=multhigh();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multhigh253.getTree());

            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mult"


    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "add"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:490:1: add : mult ( ( '+' ^| '-' ^) mult )* ;
    public final Spin2GrammarParser.add_return add() throws RecognitionException {
        Spin2GrammarParser.add_return retval = new Spin2GrammarParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal255=null;
        Token char_literal256=null;
        Spin2GrammarParser.mult_return mult254 =null;

        Spin2GrammarParser.mult_return mult257 =null;


        CommonTree char_literal255_tree=null;
        CommonTree char_literal256_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:5: ( mult ( ( '+' ^| '-' ^) mult )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:7: mult ( ( '+' ^| '-' ^) mult )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_mult_in_add2616);
            mult254=mult();

            state._fsp--;

            adaptor.addChild(root_0, mult254.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:12: ( ( '+' ^| '-' ^) mult )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==64) ) {
                    int LA62_2 = input.LA(2);

                    if ( (LA62_2==BINARY||LA62_2==CHARACTER||(LA62_2 >= FLOAT && LA62_2 <= HEXADECIMAL)||(LA62_2 >= ID && LA62_2 <= IncOp)||LA62_2==QUATERNARY||LA62_2==True||(LA62_2 >= Unary2Op && LA62_2 <= UnaryOp)||LA62_2==60||LA62_2==62||LA62_2==64||LA62_2==68||(LA62_2 >= 83 && LA62_2 <= 86)||(LA62_2 >= 91 && LA62_2 <= 93)||(LA62_2 >= 103 && LA62_2 <= 104)) ) {
                        alt62=1;
                    }


                }
                else if ( (LA62_0==62) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:13: ( '+' ^| '-' ^) mult
            	    {
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:13: ( '+' ^| '-' ^)
            	    int alt61=2;
            	    int LA61_0 = input.LA(1);

            	    if ( (LA61_0==62) ) {
            	        alt61=1;
            	    }
            	    else if ( (LA61_0==64) ) {
            	        alt61=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 61, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt61) {
            	        case 1 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:14: '+' ^
            	            {
            	            char_literal255=(Token)match(input,62,FOLLOW_62_in_add2620); 
            	            char_literal255_tree = 
            	            (CommonTree)adaptor.create(char_literal255)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal255_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:491:19: '-' ^
            	            {
            	            char_literal256=(Token)match(input,64,FOLLOW_64_in_add2623); 
            	            char_literal256_tree = 
            	            (CommonTree)adaptor.create(char_literal256)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal256_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_mult_in_add2627);
            	    mult257=mult();

            	    state._fsp--;

            	    adaptor.addChild(root_0, mult257.getTree());

            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "add"


    public static class limitmax_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "limitmax"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:494:1: limitmax : add ( LimitMaxOp ^ add )* ;
    public final Spin2GrammarParser.limitmax_return limitmax() throws RecognitionException {
        Spin2GrammarParser.limitmax_return retval = new Spin2GrammarParser.limitmax_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LimitMaxOp259=null;
        Spin2GrammarParser.add_return add258 =null;

        Spin2GrammarParser.add_return add260 =null;


        CommonTree LimitMaxOp259_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:495:5: ( add ( LimitMaxOp ^ add )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:495:7: add ( LimitMaxOp ^ add )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_limitmax2651);
            add258=add();

            state._fsp--;

            adaptor.addChild(root_0, add258.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:495:11: ( LimitMaxOp ^ add )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==LimitMaxOp) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:495:12: LimitMaxOp ^ add
            	    {
            	    LimitMaxOp259=(Token)match(input,LimitMaxOp,FOLLOW_LimitMaxOp_in_limitmax2654); 
            	    LimitMaxOp259_tree = 
            	    (CommonTree)adaptor.create(LimitMaxOp259)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(LimitMaxOp259_tree, root_0);


            	    pushFollow(FOLLOW_add_in_limitmax2657);
            	    add260=add();

            	    state._fsp--;

            	    adaptor.addChild(root_0, add260.getTree());

            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "limitmax"


    public static class boolops_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "boolops"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:498:1: boolops : limitmax ( BoolOp ^ limitmax )* ;
    public final Spin2GrammarParser.boolops_return boolops() throws RecognitionException {
        Spin2GrammarParser.boolops_return retval = new Spin2GrammarParser.boolops_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BoolOp262=null;
        Spin2GrammarParser.limitmax_return limitmax261 =null;

        Spin2GrammarParser.limitmax_return limitmax263 =null;


        CommonTree BoolOp262_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:499:5: ( limitmax ( BoolOp ^ limitmax )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:499:7: limitmax ( BoolOp ^ limitmax )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_limitmax_in_boolops2681);
            limitmax261=limitmax();

            state._fsp--;

            adaptor.addChild(root_0, limitmax261.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:499:16: ( BoolOp ^ limitmax )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==BoolOp) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:499:17: BoolOp ^ limitmax
            	    {
            	    BoolOp262=(Token)match(input,BoolOp,FOLLOW_BoolOp_in_boolops2684); 
            	    BoolOp262_tree = 
            	    (CommonTree)adaptor.create(BoolOp262)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(BoolOp262_tree, root_0);


            	    pushFollow(FOLLOW_limitmax_in_boolops2687);
            	    limitmax263=limitmax();

            	    state._fsp--;

            	    adaptor.addChild(root_0, limitmax263.getTree());

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "boolops"


    public static class not_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not_statement"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:502:1: not_statement : ( BoolNotOp )+ boolops -> ^( NOT_OP boolops ( BoolNotOp )+ ) ;
    public final Spin2GrammarParser.not_statement_return not_statement() throws RecognitionException {
        Spin2GrammarParser.not_statement_return retval = new Spin2GrammarParser.not_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BoolNotOp264=null;
        Spin2GrammarParser.boolops_return boolops265 =null;


        CommonTree BoolNotOp264_tree=null;
        RewriteRuleTokenStream stream_BoolNotOp=new RewriteRuleTokenStream(adaptor,"token BoolNotOp");
        RewriteRuleSubtreeStream stream_boolops=new RewriteRuleSubtreeStream(adaptor,"rule boolops");
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:503:5: ( ( BoolNotOp )+ boolops -> ^( NOT_OP boolops ( BoolNotOp )+ ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:503:7: ( BoolNotOp )+ boolops
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:503:7: ( BoolNotOp )+
            int cnt65=0;
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==BoolNotOp) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:503:7: BoolNotOp
            	    {
            	    BoolNotOp264=(Token)match(input,BoolNotOp,FOLLOW_BoolNotOp_in_not_statement2710);  
            	    stream_BoolNotOp.add(BoolNotOp264);


            	    }
            	    break;

            	default :
            	    if ( cnt65 >= 1 ) break loop65;
                        EarlyExitException eee =
                            new EarlyExitException(65, input);
                        throw eee;
                }
                cnt65++;
            } while (true);


            pushFollow(FOLLOW_boolops_in_not_statement2713);
            boolops265=boolops();

            state._fsp--;

            stream_boolops.add(boolops265.getTree());

            // AST REWRITE
            // elements: boolops, BoolNotOp
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 503:26: -> ^( NOT_OP boolops ( BoolNotOp )+ )
            {
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:503:29: ^( NOT_OP boolops ( BoolNotOp )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(NOT_OP, "NOT_OP")
                , root_1);

                adaptor.addChild(root_1, stream_boolops.nextTree());

                if ( !(stream_BoolNotOp.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_BoolNotOp.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_BoolNotOp.nextNode()
                    );

                }
                stream_BoolNotOp.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not_statement"


    public static class boolnot_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "boolnot"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:506:1: boolnot : ( not_statement | boolops );
    public final Spin2GrammarParser.boolnot_return boolnot() throws RecognitionException {
        Spin2GrammarParser.boolnot_return retval = new Spin2GrammarParser.boolnot_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Spin2GrammarParser.not_statement_return not_statement266 =null;

        Spin2GrammarParser.boolops_return boolops267 =null;



        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:507:5: ( not_statement | boolops )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==BoolNotOp) ) {
                alt66=1;
            }
            else if ( (LA66_0==BINARY||LA66_0==CHARACTER||(LA66_0 >= FLOAT && LA66_0 <= HEXADECIMAL)||(LA66_0 >= ID && LA66_0 <= IncOp)||LA66_0==QUATERNARY||LA66_0==True||(LA66_0 >= Unary2Op && LA66_0 <= UnaryOp)||LA66_0==60||LA66_0==62||LA66_0==64||LA66_0==68||(LA66_0 >= 83 && LA66_0 <= 86)||(LA66_0 >= 91 && LA66_0 <= 93)||(LA66_0 >= 103 && LA66_0 <= 104)) ) {
                alt66=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }
            switch (alt66) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:507:7: not_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_not_statement_in_boolnot2746);
                    not_statement266=not_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, not_statement266.getTree());

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:508:7: boolops
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_boolops_in_boolnot2754);
                    boolops267=boolops();

                    state._fsp--;

                    adaptor.addChild(root_0, boolops267.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "boolnot"


    public static class booland_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "booland"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:511:1: booland : boolnot ( BoolAndOp ^ boolnot )* ;
    public final Spin2GrammarParser.booland_return booland() throws RecognitionException {
        Spin2GrammarParser.booland_return retval = new Spin2GrammarParser.booland_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BoolAndOp269=null;
        Spin2GrammarParser.boolnot_return boolnot268 =null;

        Spin2GrammarParser.boolnot_return boolnot270 =null;


        CommonTree BoolAndOp269_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:512:5: ( boolnot ( BoolAndOp ^ boolnot )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:512:7: boolnot ( BoolAndOp ^ boolnot )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_boolnot_in_booland2776);
            boolnot268=boolnot();

            state._fsp--;

            adaptor.addChild(root_0, boolnot268.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:512:15: ( BoolAndOp ^ boolnot )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==BoolAndOp) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:512:16: BoolAndOp ^ boolnot
            	    {
            	    BoolAndOp269=(Token)match(input,BoolAndOp,FOLLOW_BoolAndOp_in_booland2779); 
            	    BoolAndOp269_tree = 
            	    (CommonTree)adaptor.create(BoolAndOp269)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(BoolAndOp269_tree, root_0);


            	    pushFollow(FOLLOW_boolnot_in_booland2782);
            	    boolnot270=boolnot();

            	    state._fsp--;

            	    adaptor.addChild(root_0, boolnot270.getTree());

            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "booland"


    public static class boolor_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "boolor"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:516:1: boolor : booland ( BoolOrOp ^ booland )* ;
    public final Spin2GrammarParser.boolor_return boolor() throws RecognitionException {
        Spin2GrammarParser.boolor_return retval = new Spin2GrammarParser.boolor_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BoolOrOp272=null;
        Spin2GrammarParser.booland_return booland271 =null;

        Spin2GrammarParser.booland_return booland273 =null;


        CommonTree BoolOrOp272_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:517:5: ( booland ( BoolOrOp ^ booland )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:517:7: booland ( BoolOrOp ^ booland )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_booland_in_boolor2807);
            booland271=booland();

            state._fsp--;

            adaptor.addChild(root_0, booland271.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:517:15: ( BoolOrOp ^ booland )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==BoolOrOp) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:517:16: BoolOrOp ^ booland
            	    {
            	    BoolOrOp272=(Token)match(input,BoolOrOp,FOLLOW_BoolOrOp_in_boolor2810); 
            	    BoolOrOp272_tree = 
            	    (CommonTree)adaptor.create(BoolOrOp272)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(BoolOrOp272_tree, root_0);


            	    pushFollow(FOLLOW_booland_in_boolor2813);
            	    booland273=booland();

            	    state._fsp--;

            	    adaptor.addChild(root_0, booland273.getTree());

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "boolor"


    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:520:1: expression : boolor ( Assign ^ expression )? ;
    public final Spin2GrammarParser.expression_return expression() throws RecognitionException {
        Spin2GrammarParser.expression_return retval = new Spin2GrammarParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token Assign275=null;
        Spin2GrammarParser.boolor_return boolor274 =null;

        Spin2GrammarParser.expression_return expression276 =null;


        CommonTree Assign275_tree=null;

        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:521:5: ( boolor ( Assign ^ expression )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:521:7: boolor ( Assign ^ expression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_boolor_in_expression2837);
            boolor274=boolor();

            state._fsp--;

            adaptor.addChild(root_0, boolor274.getTree());

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:521:14: ( Assign ^ expression )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==Assign) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:521:15: Assign ^ expression
                    {
                    Assign275=(Token)match(input,Assign,FOLLOW_Assign_in_expression2840); 
                    Assign275_tree = 
                    (CommonTree)adaptor.create(Assign275)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(Assign275_tree, root_0);


                    pushFollow(FOLLOW_expression_in_expression2843);
                    expression276=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression276.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_conblock_in_prog143 = new BitSet(new long[]{0x0000001080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_objblock_in_prog145 = new BitSet(new long[]{0x0000001080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_varblock_in_prog147 = new BitSet(new long[]{0x0000001080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_method_in_prog149 = new BitSet(new long[]{0x0000001080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_NEWLINE_in_prog151 = new BitSet(new long[]{0x0000001080000002L,0x0000000100800800L});
    public static final BitSet FOLLOW_75_in_conblock179 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_conblock182 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_constatement_in_conblock202 = new BitSet(new long[]{0x8000001004080000L});
    public static final BitSet FOLLOW_63_in_conblock205 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_constatement_in_conblock208 = new BitSet(new long[]{0x8000001004080000L});
    public static final BitSet FOLLOW_NEWLINE_in_conblock212 = new BitSet(new long[]{0x0000001004080000L});
    public static final BitSet FOLLOW_Dedent_in_conblock218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_constatement237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_constatement239 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_constatement241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_varblock263 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_varblock266 = new BitSet(new long[]{0x0000001000080000L,0x0000000800010080L});
    public static final BitSet FOLLOW_vartype_in_varblock278 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_varblock280 = new BitSet(new long[]{0x8000001000080000L,0x00000008000100A0L});
    public static final BitSet FOLLOW_array_in_varblock282 = new BitSet(new long[]{0x8000001000080000L,0x0000000800010080L});
    public static final BitSet FOLLOW_63_in_varblock286 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_varblock289 = new BitSet(new long[]{0x8000001000080000L,0x00000008000100A0L});
    public static final BitSet FOLLOW_array_in_varblock291 = new BitSet(new long[]{0x8000001000080000L,0x0000000800010080L});
    public static final BitSet FOLLOW_NEWLINE_in_varblock296 = new BitSet(new long[]{0x0000001000080000L,0x0000000800010080L});
    public static final BitSet FOLLOW_Dedent_in_varblock303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_objblock371 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_objblock374 = new BitSet(new long[]{0x0000001004080000L});
    public static final BitSet FOLLOW_ID_in_objblock387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_objblock389 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_STRING_in_objblock392 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_objblock394 = new BitSet(new long[]{0x0000001004080000L});
    public static final BitSet FOLLOW_Dedent_in_objblock400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_paramlist424 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_list_in_paramlist426 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_paramlist428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_list460 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_list463 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_list466 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_Methodheader_in_method497 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_method499 = new BitSet(new long[]{0x1000000020000002L,0x0000004000000004L});
    public static final BitSet FOLLOW_paramlist_in_method501 = new BitSet(new long[]{0x0000000020000002L,0x0000004000000004L});
    public static final BitSet FOLLOW_66_in_method506 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_method509 = new BitSet(new long[]{0x0000000020000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_102_in_method515 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_method518 = new BitSet(new long[]{0x8000000020000002L});
    public static final BitSet FOLLOW_63_in_method522 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_method525 = new BitSet(new long[]{0x8000000020000002L});
    public static final BitSet FOLLOW_block_in_method540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IncOp_in_statement566 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_statement568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignorpostinc_in_statement576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_statement584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_statement_in_statement592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_statement_in_statement600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_statement608 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_paramlist_in_statement610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_statement618 = new BitSet(new long[]{0x532004001DC44042L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_statement620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_statement631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_statement645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_statement653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_neg_statement_in_statement661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memmove_in_statement669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memfill_in_statement677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_block_statement695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_case_statement_in_block_statement704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeat_in_block_statement713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatconstanttimes_in_block_statement722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatwhile_in_block_statement730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatuntil_in_block_statement739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeatvariablefrom_in_block_statement748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_assignorpostinc771 = new BitSet(new long[]{0x0000000010000020L,0x0000018000000010L});
    public static final BitSet FOLLOW_Assign_in_assignorpostinc774 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_assignorpostinc776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IncOp_in_assignorpostinc796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_assignorpostinc814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_assignorpostinc830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_assignorpostinc846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id872 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_array_in_id874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_neg_statement897 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_neg_statement899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_memmove917 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_memmove924 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memmove925 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memmove926 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memmove927 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memmove928 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memmove929 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_memmove930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_memfill956 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_memfill963 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memfill964 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memfill965 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memfill966 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_memfill967 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_memfill968 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_memfill969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_if_statement999 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_if_statement1001 = new BitSet(new long[]{0x0000000020000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_block_in_if_statement1003 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_77_in_if_statement1018 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_if_statement1020 = new BitSet(new long[]{0x0000000020000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_block_in_if_statement1022 = new BitSet(new long[]{0x0000000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_76_in_if_statement1037 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_if_statement1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_case_statement1062 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_case_statement1064 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_Indent_in_case_statement1066 = new BitSet(new long[]{0x532004101DCC4040L,0x0000018038780011L});
    public static final BitSet FOLLOW_casechoice_in_case_statement1077 = new BitSet(new long[]{0x532004101DCC4040L,0x0000018038780011L});
    public static final BitSet FOLLOW_NEWLINE_in_case_statement1081 = new BitSet(new long[]{0x532004101DCC4040L,0x0000018038780011L});
    public static final BitSet FOLLOW_Dedent_in_case_statement1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_casechoice1113 = new BitSet(new long[]{0x8000800000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_Range_in_casechoice1116 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_casechoice1118 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_63_in_casechoice1131 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_casechoice1133 = new BitSet(new long[]{0x8000800000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_Range_in_casechoice1136 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_casechoice1138 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_casechoice1146 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_block_in_casechoice1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatvariablefrom1170 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_repeatvariablefrom1172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_repeatvariablefrom1174 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1176 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_repeatvariablefrom1178 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1180 = new BitSet(new long[]{0x0000000020000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_repeatvariablefrom1183 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatvariablefrom1185 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatvariablefrom1189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatconstanttimes1209 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatconstanttimes1213 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatconstanttimes1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatuntil1235 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_repeatuntil1237 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatuntil1239 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatuntil1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeatwhile1260 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_repeatwhile1262 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeatwhile1264 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_block_in_repeatwhile1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_repeat1287 = new BitSet(new long[]{0x0000001020000000L});
    public static final BitSet FOLLOW_block_in_repeat1290 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat1292 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_repeat1296 = new BitSet(new long[]{0x0000000000000000L,0x0000000480000000L});
    public static final BitSet FOLLOW_95_in_repeat1298 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeat1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_repeat1302 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_repeat1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Indent_in_block1333 = new BitSet(new long[]{0x0100001014084000L,0x0000003203068711L});
    public static final BitSet FOLLOW_NEWLINE_in_block1343 = new BitSet(new long[]{0x0100001014084000L,0x0000003203068711L});
    public static final BitSet FOLLOW_statement_in_block1347 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_block1349 = new BitSet(new long[]{0x0100001014084000L,0x0000003203068711L});
    public static final BitSet FOLLOW_block_statement_in_block1353 = new BitSet(new long[]{0x0100001014084000L,0x0000003203068711L});
    public static final BitSet FOLLOW_Dedent_in_block1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_array1396 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_array1397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_array1398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_methodCall1415 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_paramlist_in_methodCall1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objMethodCall1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_objMethodCall1440 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_objMethodCall1442 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_paramlist_in_objMethodCall1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_objectConstant1465 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_objectConstant1467 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ID_in_objectConstant1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_string1490 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_string1492 = new BitSet(new long[]{0x0004000000040000L});
    public static final BitSet FOLLOW_set_in_string1493 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_string1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_setbits1520 = new BitSet(new long[]{0x0000000000000002L,0x0000018000000010L});
    public static final BitSet FOLLOW_setbits_in_identifier1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_rand1581 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_id_in_rand1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Unary2Op_in_unary_statement1610 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_unary2_in_unary_statement1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_strcomp1649 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_strcomp1650 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_strcomp1651 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_strcomp1653 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_strcomp1655 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_strcomp1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_strsize1677 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_strsize1678 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_strsize1679 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_strsize1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_lookup1701 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_lookup1707 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_lookup1708 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_lookup1709 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_rangelist_in_lookup1711 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_lookup1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_lookdown1733 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_lookdown1739 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_lookdown1740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_lookdown1741 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_rangelist_in_lookdown1743 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_lookdown1744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist1764 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_rangelist1767 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_rangeitem_in_rangelist1768 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_expression_in_rangeitem1791 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_Range_in_rangeitem1794 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_rangeitem1795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_assignment1818 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Assign_in_assignment1820 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_assignment1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objMethodCall_in_ident1847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_methodCall_in_ident1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectConstant_in_ident1865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_ident1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_ident1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rand_in_ident1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strcomp_in_ident1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_strsize_in_ident1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_ident1915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookdown_in_ident1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_in_ident1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_True_in_ident1939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_False_in_ident1948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ident_in_term2256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_term2265 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_term2268 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_term2270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_number_in_term2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IncOp_in_preinc2308 = new BitSet(new long[]{0x122004000DC40040L,0x0000018038780010L});
    public static final BitSet FOLLOW_term_in_preinc2310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_preinc2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_preinc_in_postinc2350 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_IncOp_in_postinc2361 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_62_in_unary22397 = new BitSet(new long[]{0x522004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_negation_in_unary22400 = new BitSet(new long[]{0x522004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_postinc_in_unary22405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_negation2419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary2_in_unary32441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_statement_in_unary32449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary3_in_shift2476 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_ShiftOp_in_shift2479 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_unary3_in_shift2482 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_shift_in_bitand2507 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_BitAndOp_in_bitand2510 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_shift_in_bitand2513 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_bitand_in_bitor2537 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_BitOrOp_in_bitor2540 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_bitand_in_bitor2543 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_bitor_in_multhigh2567 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_Multiply_in_multhigh2569 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_bitor_in_multhigh2572 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_multhigh_in_mult2592 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_MultOp_in_mult2594 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_multhigh_in_mult2597 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_mult_in_add2616 = new BitSet(new long[]{0x4000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_62_in_add2620 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_64_in_add2623 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_mult_in_add2627 = new BitSet(new long[]{0x4000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_add_in_limitmax2651 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_LimitMaxOp_in_limitmax2654 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_add_in_limitmax2657 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_limitmax_in_boolops2681 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_BoolOp_in_boolops2684 = new BitSet(new long[]{0x532004001DC40040L,0x0000018038780011L});
    public static final BitSet FOLLOW_limitmax_in_boolops2687 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_BoolNotOp_in_not_statement2710 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_boolops_in_not_statement2713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_statement_in_boolnot2746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolops_in_boolnot2754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolnot_in_booland2776 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_BoolAndOp_in_booland2779 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_boolnot_in_booland2782 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_booland_in_boolor2807 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_BoolOrOp_in_boolor2810 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_booland_in_boolor2813 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_boolor_in_expression2837 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_Assign_in_expression2840 = new BitSet(new long[]{0x532004001DC44040L,0x0000018038780011L});
    public static final BitSet FOLLOW_expression_in_expression2843 = new BitSet(new long[]{0x0000000000000002L});

}