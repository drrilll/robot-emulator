// $ANTLR 3.4 /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g 2012-09-13 07:39:48

package dang.antlr.parser;
import dang.program.Debug;
import dang.interpreter.IDE;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class Spin2GrammarLexer extends Lexer {
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


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public Spin2GrammarLexer() {} 
    public Spin2GrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public Spin2GrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g"; }

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:36:7: ( '#' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:36:9: '#'
            {
            match('#'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:37:7: ( '(' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:37:9: '('
            {
            match('('); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:38:7: ( ')' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:38:9: ')'
            {
            match(')'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:39:7: ( '+' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:39:9: '+'
            {
            match('+'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:40:7: ( ',' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:40:9: ','
            {
            match(','); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:41:7: ( '-' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:41:9: '-'
            {
            match('-'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:42:7: ( '.' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:42:9: '.'
            {
            match('.'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:43:7: ( ':' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:43:9: ':'
            {
            match(':'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:44:7: ( '=' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:44:9: '='
            {
            match('='); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:45:7: ( '?' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:45:9: '?'
            {
            match('?'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:46:7: ( '[' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:46:9: '['
            {
            match('['); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:47:7: ( ']' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:47:9: ']'
            {
            match(']'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:48:7: ( 'byte' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:48:9: 'byte'
            {
            match("byte"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:49:7: ( 'bytefill' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:49:9: 'bytefill'
            {
            match("bytefill"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:50:7: ( 'bytemove' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:50:9: 'bytemove'
            {
            match("bytemove"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:51:7: ( 'case' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:51:9: 'case'
            {
            match("case"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:52:7: ( 'con' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:52:9: 'con'
            {
            match("con"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:53:7: ( 'else' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:53:9: 'else'
            {
            match("else"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:54:7: ( 'elseif' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:54:9: 'elseif'
            {
            match("elseif"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:55:7: ( 'from' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:55:9: 'from'
            {
            match("from"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:56:7: ( 'if' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:56:9: 'if'
            {
            match("if"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:57:7: ( 'long' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:57:9: 'long'
            {
            match("long"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:58:7: ( 'longfill' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:58:9: 'longfill'
            {
            match("longfill"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:59:7: ( 'longmove' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:59:9: 'longmove'
            {
            match("longmove"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:60:7: ( 'lookdown' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:60:9: 'lookdown'
            {
            match("lookdown"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:61:7: ( 'lookdownz' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:61:9: 'lookdownz'
            {
            match("lookdownz"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:62:7: ( 'lookup' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:62:9: 'lookup'
            {
            match("lookup"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:63:7: ( 'lookupz' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:63:9: 'lookupz'
            {
            match("lookupz"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:64:7: ( 'obj' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:64:9: 'obj'
            {
            match("obj"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:65:7: ( 'repeat' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:65:9: 'repeat'
            {
            match("repeat"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:66:7: ( 'return' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:66:9: 'return'
            {
            match("return"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:67:7: ( 'step' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:67:9: 'step'
            {
            match("step"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:68:7: ( 'strcomp' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:68:9: 'strcomp'
            {
            match("strcomp"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:69:7: ( 'string' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:69:9: 'string'
            {
            match("string"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:70:7: ( 'strsize' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:70:9: 'strsize'
            {
            match("strsize"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:71:7: ( 'to' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:71:9: 'to'
            {
            match("to"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:72:7: ( 'until' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:72:9: 'until'
            {
            match("until"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:73:7: ( 'var' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:73:9: 'var'
            {
            match("var"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:74:7: ( 'waitcnt' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:74:9: 'waitcnt'
            {
            match("waitcnt"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:75:7: ( 'while' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:75:9: 'while'
            {
            match("while"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:76:7: ( 'word' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:76:9: 'word'
            {
            match("word"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:77:8: ( 'wordfill' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:77:10: 'wordfill'
            {
            match("wordfill"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:78:8: ( 'wordmove' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:78:10: 'wordmove'
            {
            match("wordmove"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:79:8: ( '|' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:79:10: '|'
            {
            match('|'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:80:8: ( '~' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:80:10: '~'
            {
            match('~'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:81:8: ( '~~' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:81:10: '~~'
            {
            match("~~"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "IncOp"
    public final void mIncOp() throws RecognitionException {
        try {
            int _type = IncOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:402:6: ( '++' | '--' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+') ) {
                alt1=1;
            }
            else if ( (LA1_0=='-') ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:402:8: '++'
                    {
                    match("++"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:402:13: '--'
                    {
                    match("--"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IncOp"

    // $ANTLR start "UnaryOp"
    public final void mUnaryOp() throws RecognitionException {
        try {
            int _type = UnaryOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:404:8: ( '@' | '?' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            if ( (input.LA(1) >= '?' && input.LA(1) <= '@') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UnaryOp"

    // $ANTLR start "Unary2Op"
    public final void mUnary2Op() throws RecognitionException {
        try {
            int _type = Unary2Op;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:9: ( '+' | '-' | '^^' | '||' | '|<' | '>|' | '!' )
            int alt2=7;
            switch ( input.LA(1) ) {
            case '+':
                {
                alt2=1;
                }
                break;
            case '-':
                {
                alt2=2;
                }
                break;
            case '^':
                {
                alt2=3;
                }
                break;
            case '|':
                {
                int LA2_4 = input.LA(2);

                if ( (LA2_4=='|') ) {
                    alt2=4;
                }
                else if ( (LA2_4=='<') ) {
                    alt2=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 4, input);

                    throw nvae;

                }
                }
                break;
            case '>':
                {
                alt2=6;
                }
                break;
            case '!':
                {
                alt2=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:11: '+'
                    {
                    match('+'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:15: '-'
                    {
                    match('-'); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:19: '^^'
                    {
                    match("^^"); if (state.failed) return ;



                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:24: '||'
                    {
                    match("||"); if (state.failed) return ;



                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:29: '|<'
                    {
                    match("|<"); if (state.failed) return ;



                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:35: '>|'
                    {
                    match(">|"); if (state.failed) return ;



                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:406:41: '!'
                    {
                    match('!'); if (state.failed) return ;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Unary2Op"

    // $ANTLR start "ShiftOp"
    public final void mShiftOp() throws RecognitionException {
        try {
            int _type = ShiftOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:8: ( '->' | '<-' | '>>' | '<<' | '~>' | '><' )
            int alt3=6;
            switch ( input.LA(1) ) {
            case '-':
                {
                alt3=1;
                }
                break;
            case '<':
                {
                int LA3_2 = input.LA(2);

                if ( (LA3_2=='-') ) {
                    alt3=2;
                }
                else if ( (LA3_2=='<') ) {
                    alt3=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case '>':
                {
                int LA3_3 = input.LA(2);

                if ( (LA3_3=='>') ) {
                    alt3=3;
                }
                else if ( (LA3_3=='<') ) {
                    alt3=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 3, input);

                    throw nvae;

                }
                }
                break;
            case '~':
                {
                alt3=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:10: '->'
                    {
                    match("->"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:16: '<-'
                    {
                    match("<-"); if (state.failed) return ;



                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:22: '>>'
                    {
                    match(">>"); if (state.failed) return ;



                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:28: '<<'
                    {
                    match("<<"); if (state.failed) return ;



                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:34: '~>'
                    {
                    match("~>"); if (state.failed) return ;



                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:407:40: '><'
                    {
                    match("><"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ShiftOp"

    // $ANTLR start "BitAndOp"
    public final void mBitAndOp() throws RecognitionException {
        try {
            int _type = BitAndOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:408:9: ( '&' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:408:11: '&'
            {
            match('&'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BitAndOp"

    // $ANTLR start "BitOrOp"
    public final void mBitOrOp() throws RecognitionException {
        try {
            int _type = BitOrOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:409:8: ( '|' | '^' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            if ( input.LA(1)=='^'||input.LA(1)=='|' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BitOrOp"

    // $ANTLR start "Multiply"
    public final void mMultiply() throws RecognitionException {
        try {
            int _type = Multiply;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:410:9: ( '**' | '//' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='*') ) {
                alt4=1;
            }
            else if ( (LA4_0=='/') ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:410:11: '**'
                    {
                    match("**"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:410:16: '//'
                    {
                    match("//"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Multiply"

    // $ANTLR start "MultOp"
    public final void mMultOp() throws RecognitionException {
        try {
            int _type = MultOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:411:7: ( '/' | '*' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            if ( input.LA(1)=='*'||input.LA(1)=='/' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MultOp"

    // $ANTLR start "LimitMaxOp"
    public final void mLimitMaxOp() throws RecognitionException {
        try {
            int _type = LimitMaxOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:412:11: ( '<#' | '#>' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='<') ) {
                alt5=1;
            }
            else if ( (LA5_0=='#') ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:412:13: '<#'
                    {
                    match("<#"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:412:18: '#>'
                    {
                    match("#>"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LimitMaxOp"

    // $ANTLR start "BoolOp"
    public final void mBoolOp() throws RecognitionException {
        try {
            int _type = BoolOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:7: ( '<' | '>' | '=<' | '=>' | '<>' | '==' )
            int alt6=6;
            switch ( input.LA(1) ) {
            case '<':
                {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='>') ) {
                    alt6=5;
                }
                else {
                    alt6=1;
                }
                }
                break;
            case '>':
                {
                alt6=2;
                }
                break;
            case '=':
                {
                switch ( input.LA(2) ) {
                case '<':
                    {
                    alt6=3;
                    }
                    break;
                case '>':
                    {
                    alt6=4;
                    }
                    break;
                case '=':
                    {
                    alt6=6;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;

                }

                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:9: '<'
                    {
                    match('<'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:13: '>'
                    {
                    match('>'); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:17: '=<'
                    {
                    match("=<"); if (state.failed) return ;



                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:22: '=>'
                    {
                    match("=>"); if (state.failed) return ;



                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:27: '<>'
                    {
                    match("<>"); if (state.failed) return ;



                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:413:32: '=='
                    {
                    match("=="); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BoolOp"

    // $ANTLR start "BoolNotOp"
    public final void mBoolNotOp() throws RecognitionException {
        try {
            int _type = BoolNotOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:414:10: ( 'not' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:414:12: 'not'
            {
            match("not"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BoolNotOp"

    // $ANTLR start "BoolAndOp"
    public final void mBoolAndOp() throws RecognitionException {
        try {
            int _type = BoolAndOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:415:10: ( 'and' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:415:12: 'and'
            {
            match("and"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BoolAndOp"

    // $ANTLR start "BoolOrOp"
    public final void mBoolOrOp() throws RecognitionException {
        try {
            int _type = BoolOrOp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:416:9: ( 'or' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:416:11: 'or'
            {
            match("or"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BoolOrOp"

    // $ANTLR start "Assign"
    public final void mAssign() throws RecognitionException {
        try {
            int _type = Assign;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:7: ( ':=' | '+=' | '-=' | '*=' | '**=' | '/=' | '//=' | '#>=' | '<#=' | '~>=' | '->=' | '<-= ' | '<<=' | '>>=' | '><=' | '&=' | '|=' | '^=' | 'and=' | 'or=' | '<>=' | '>=' | '<=' | '===' | '=>=' | '=<=' )
            int alt7=26;
            switch ( input.LA(1) ) {
            case ':':
                {
                alt7=1;
                }
                break;
            case '+':
                {
                alt7=2;
                }
                break;
            case '-':
                {
                int LA7_3 = input.LA(2);

                if ( (LA7_3=='=') ) {
                    alt7=3;
                }
                else if ( (LA7_3=='>') ) {
                    alt7=11;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            case '*':
                {
                int LA7_4 = input.LA(2);

                if ( (LA7_4=='=') ) {
                    alt7=4;
                }
                else if ( (LA7_4=='*') ) {
                    alt7=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 4, input);

                    throw nvae;

                }
                }
                break;
            case '/':
                {
                int LA7_5 = input.LA(2);

                if ( (LA7_5=='=') ) {
                    alt7=6;
                }
                else if ( (LA7_5=='/') ) {
                    alt7=7;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 5, input);

                    throw nvae;

                }
                }
                break;
            case '#':
                {
                alt7=8;
                }
                break;
            case '<':
                {
                switch ( input.LA(2) ) {
                case '#':
                    {
                    alt7=9;
                    }
                    break;
                case '-':
                    {
                    alt7=12;
                    }
                    break;
                case '<':
                    {
                    alt7=13;
                    }
                    break;
                case '>':
                    {
                    alt7=21;
                    }
                    break;
                case '=':
                    {
                    alt7=23;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 7, input);

                    throw nvae;

                }

                }
                break;
            case '~':
                {
                alt7=10;
                }
                break;
            case '>':
                {
                switch ( input.LA(2) ) {
                case '>':
                    {
                    alt7=14;
                    }
                    break;
                case '<':
                    {
                    alt7=15;
                    }
                    break;
                case '=':
                    {
                    alt7=22;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 9, input);

                    throw nvae;

                }

                }
                break;
            case '&':
                {
                alt7=16;
                }
                break;
            case '|':
                {
                alt7=17;
                }
                break;
            case '^':
                {
                alt7=18;
                }
                break;
            case 'a':
                {
                alt7=19;
                }
                break;
            case 'o':
                {
                alt7=20;
                }
                break;
            case '=':
                {
                switch ( input.LA(2) ) {
                case '=':
                    {
                    alt7=24;
                    }
                    break;
                case '>':
                    {
                    alt7=25;
                    }
                    break;
                case '<':
                    {
                    alt7=26;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 15, input);

                    throw nvae;

                }

                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:9: ':='
                    {
                    match(":="); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:14: '+='
                    {
                    match("+="); if (state.failed) return ;



                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:19: '-='
                    {
                    match("-="); if (state.failed) return ;



                    }
                    break;
                case 4 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:24: '*='
                    {
                    match("*="); if (state.failed) return ;



                    }
                    break;
                case 5 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:29: '**='
                    {
                    match("**="); if (state.failed) return ;



                    }
                    break;
                case 6 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:35: '/='
                    {
                    match("/="); if (state.failed) return ;



                    }
                    break;
                case 7 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:40: '//='
                    {
                    match("//="); if (state.failed) return ;



                    }
                    break;
                case 8 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:46: '#>='
                    {
                    match("#>="); if (state.failed) return ;



                    }
                    break;
                case 9 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:52: '<#='
                    {
                    match("<#="); if (state.failed) return ;



                    }
                    break;
                case 10 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:58: '~>='
                    {
                    match("~>="); if (state.failed) return ;



                    }
                    break;
                case 11 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:64: '->='
                    {
                    match("->="); if (state.failed) return ;



                    }
                    break;
                case 12 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:417:70: '<-= '
                    {
                    match("<-= "); if (state.failed) return ;



                    }
                    break;
                case 13 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:11: '<<='
                    {
                    match("<<="); if (state.failed) return ;



                    }
                    break;
                case 14 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:17: '>>='
                    {
                    match(">>="); if (state.failed) return ;



                    }
                    break;
                case 15 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:23: '><='
                    {
                    match("><="); if (state.failed) return ;



                    }
                    break;
                case 16 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:29: '&='
                    {
                    match("&="); if (state.failed) return ;



                    }
                    break;
                case 17 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:34: '|='
                    {
                    match("|="); if (state.failed) return ;



                    }
                    break;
                case 18 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:39: '^='
                    {
                    match("^="); if (state.failed) return ;



                    }
                    break;
                case 19 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:44: 'and='
                    {
                    match("and="); if (state.failed) return ;



                    }
                    break;
                case 20 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:51: 'or='
                    {
                    match("or="); if (state.failed) return ;



                    }
                    break;
                case 21 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:57: '<>='
                    {
                    match("<>="); if (state.failed) return ;



                    }
                    break;
                case 22 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:63: '>='
                    {
                    match(">="); if (state.failed) return ;



                    }
                    break;
                case 23 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:68: '<='
                    {
                    match("<="); if (state.failed) return ;



                    }
                    break;
                case 24 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:418:73: '==='
                    {
                    match("==="); if (state.failed) return ;



                    }
                    break;
                case 25 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:419:11: '=>='
                    {
                    match("=>="); if (state.failed) return ;



                    }
                    break;
                case 26 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:419:17: '=<='
                    {
                    match("=<="); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Assign"

    // $ANTLR start "Indent"
    public final void mIndent() throws RecognitionException {
        try {
            int _type = Indent;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:530:5: ( ',<' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:530:8: ',<'
            {
            match(",<"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Indent"

    // $ANTLR start "Dedent"
    public final void mDedent() throws RecognitionException {
        try {
            int _type = Dedent;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:533:5: ( ',>' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:533:8: ',>'
            {
            match(",>"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Dedent"

    // $ANTLR start "True"
    public final void mTrue() throws RecognitionException {
        try {
            int _type = True;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:538:5: ( 'true' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:538:7: 'true'
            {
            match("true"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "True"

    // $ANTLR start "False"
    public final void mFalse() throws RecognitionException {
        try {
            int _type = False;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:542:5: ( 'false' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:542:7: 'false'
            {
            match("false"); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "False"

    // $ANTLR start "Methodheader"
    public final void mMethodheader() throws RecognitionException {
        try {
            int _type = Methodheader;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:546:2: ( 'pub' | 'pri' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='p') ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1=='u') ) {
                    alt8=1;
                }
                else if ( (LA8_1=='r') ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:546:4: 'pub'
                    {
                    match("pub"); if (state.failed) return ;



                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:546:12: 'pri'
                    {
                    match("pri"); if (state.failed) return ;



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Methodheader"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:548:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:548:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:548:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:552:5: ( ( ' ' | '\\t' ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:552:9: ( ' ' | '\\t' )
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            if ( state.backtracking==0 ) {_channel=HIDDEN;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "MultiLineComment"
    public final void mMultiLineComment() throws RecognitionException {
        try {
            int _type = MultiLineComment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:554:18: ( ( '{' ) ( options {greedy=false; } : . )* ( '}' ( '}' )? ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:3: ( '{' ) ( options {greedy=false; } : . )* ( '}' ( '}' )? )
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:3: ( '{' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:4: '{'
            {
            match('{'); if (state.failed) return ;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:9: ( options {greedy=false; } : . )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='}') ) {
                    alt10=2;
                }
                else if ( ((LA10_0 >= '\u0000' && LA10_0 <= '|')||(LA10_0 >= '~' && LA10_0 <= '\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:37: .
            	    {
            	    matchAny(); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:42: ( '}' ( '}' )? )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:43: '}' ( '}' )?
            {
            match('}'); if (state.failed) return ;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:46: ( '}' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='}') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:555:46: '}'
                    {
                    match('}'); if (state.failed) return ;

                    }
                    break;

            }


            }


            if ( state.backtracking==0 ) {_channel=HIDDEN;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MultiLineComment"

    // $ANTLR start "SingleLineComment"
    public final void mSingleLineComment() throws RecognitionException {
        try {
            int _type = SingleLineComment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:557:19: ( '\\'' (~ ( '\\n' | '\\r' ) )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:558:3: '\\'' (~ ( '\\n' | '\\r' ) )*
            {
            match('\''); if (state.failed) return ;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:558:8: (~ ( '\\n' | '\\r' ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= '\u0000' && LA12_0 <= '\t')||(LA12_0 >= '\u000B' && LA12_0 <= '\f')||(LA12_0 >= '\u000E' && LA12_0 <= '\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            if ( state.backtracking==0 ) {_channel=HIDDEN;}

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SingleLineComment"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:561:20: ( ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' ) ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' | '_' )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:562:3: ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' ) ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' | '_' )*
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:562:31: ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' | '_' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')||(LA13_0 >= 'A' && LA13_0 <= 'F')||LA13_0=='_'||(LA13_0 >= 'a' && LA13_0 <= 'f')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:568:5: ( ( '0' .. '9' ) ( '0' .. '9' | '_' )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:568:7: ( '0' .. '9' ) ( '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:568:17: ( '0' .. '9' | '_' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '0' && LA14_0 <= '9')||LA14_0=='_') ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='_' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:573:5: ( INT '.' INT ( EXPONENT )? | INT EXPONENT )
            int alt16=2;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:573:9: INT '.' INT ( EXPONENT )?
                    {
                    mINT(); if (state.failed) return ;


                    match('.'); if (state.failed) return ;

                    mINT(); if (state.failed) return ;


                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:573:21: ( EXPONENT )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='E'||LA15_0=='e') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:573:21: EXPONENT
                            {
                            mEXPONENT(); if (state.failed) return ;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:574:9: INT EXPONENT
                    {
                    mINT(); if (state.failed) return ;


                    mEXPONENT(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "RANGE_OR_INT"
    public final void mRANGE_OR_INT() throws RecognitionException {
        try {
            int _type = RANGE_OR_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:577:5: ( ( INT '..' )=> INT | ( INT '.' INT ( EXPONENT )? | INT EXPONENT )=> FLOAT | INT )
            int alt17=3;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0 >= '0' && LA17_0 <= '9')) ) {
                int LA17_1 = input.LA(2);

                if ( ((LA17_1 >= '0' && LA17_1 <= '9')||LA17_1=='_') && (synpred2_Spin2Grammar())) {
                    alt17=2;
                }
                else if ( (LA17_1=='.') && (synpred2_Spin2Grammar())) {
                    alt17=2;
                }
                else if ( (LA17_1=='E'||LA17_1=='e') && (synpred2_Spin2Grammar())) {
                    alt17=2;
                }
                else if ( (synpred1_Spin2Grammar()) ) {
                    alt17=1;
                }
                else if ( (true) ) {
                    alt17=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:577:9: ( INT '..' )=> INT
                    {
                    mINT(); if (state.failed) return ;


                    if ( state.backtracking==0 ) { _type=INT; }

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:9: ( INT '.' INT ( EXPONENT )? | INT EXPONENT )=> FLOAT
                    {
                    mFLOAT(); if (state.failed) return ;


                    if ( state.backtracking==0 ) { _type=FLOAT; }

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:579:9: INT
                    {
                    mINT(); if (state.failed) return ;


                    if ( state.backtracking==0 ) { _type = INT; }

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RANGE_OR_INT"

    // $ANTLR start "Range"
    public final void mRange() throws RecognitionException {
        try {
            int _type = Range;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:583:5: ( '..' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:583:7: '..'
            {
            match(".."); if (state.failed) return ;



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Range"

    // $ANTLR start "BINARY"
    public final void mBINARY() throws RecognitionException {
        try {
            int _type = BINARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:588:5: ( '%' BINARY_DIGIT ( BINARY_DIGIT | '_' )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:588:7: '%' BINARY_DIGIT ( BINARY_DIGIT | '_' )*
            {
            match('%'); if (state.failed) return ;

            mBINARY_DIGIT(); if (state.failed) return ;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:588:23: ( BINARY_DIGIT | '_' )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0 >= '0' && LA18_0 <= '1')||LA18_0=='_') ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '1')||input.LA(1)=='_' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BINARY"

    // $ANTLR start "BINARY_DIGIT"
    public final void mBINARY_DIGIT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:594:5: ( ( '0' | '1' ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BINARY_DIGIT"

    // $ANTLR start "QUATERNARY"
    public final void mQUATERNARY() throws RecognitionException {
        try {
            int _type = QUATERNARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:597:5: ( '%%' QUAT_DIGIT ( QUAT_DIGIT | '_' )* )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:597:6: '%%' QUAT_DIGIT ( QUAT_DIGIT | '_' )*
            {
            match("%%"); if (state.failed) return ;



            mQUAT_DIGIT(); if (state.failed) return ;


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:597:22: ( QUAT_DIGIT | '_' )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0 >= '0' && LA19_0 <= '4')||LA19_0=='_') ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '4')||input.LA(1)=='_' ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUATERNARY"

    // $ANTLR start "QUAT_DIGIT"
    public final void mQUAT_DIGIT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:603:5: ( ( '0' .. '4' ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '4') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUAT_DIGIT"

    // $ANTLR start "HEXADECIMAL"
    public final void mHEXADECIMAL() throws RecognitionException {
        try {
            int _type = HEXADECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:606:5: ( '$' ( HEX_DIGIT )+ )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:606:7: '$' ( HEX_DIGIT )+
            {
            match('$'); if (state.failed) return ;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:606:11: ( HEX_DIGIT )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0 >= '0' && LA20_0 <= '9')||(LA20_0 >= 'A' && LA20_0 <= 'F')||(LA20_0 >= 'a' && LA20_0 <= 'f')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:606:11: HEX_DIGIT
            	    {
            	    mHEX_DIGIT(); if (state.failed) return ;


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEXADECIMAL"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:609:9: ( ( '\\r' '\\n' | '\\n' | '\\f' ) )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:610:3: ( '\\r' '\\n' | '\\n' | '\\f' )
            {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:610:3: ( '\\r' '\\n' | '\\n' | '\\f' )
            int alt21=3;
            switch ( input.LA(1) ) {
            case '\r':
                {
                alt21=1;
                }
                break;
            case '\n':
                {
                alt21=2;
                }
                break;
            case '\f':
                {
                alt21=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:610:4: '\\r' '\\n'
                    {
                    match('\r'); if (state.failed) return ;

                    match('\n'); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:610:27: '\\n'
                    {
                    match('\n'); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:610:32: '\\f'
                    {
                    match('\f'); if (state.failed) return ;

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "CHARACTER"
    public final void mCHARACTER() throws RecognitionException {
        try {
            int _type = CHARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:612:11: ( '\"' (~ ( '\"' | '\\n' | '\\r' ) ) '\"' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:613:3: '\"' (~ ( '\"' | '\\n' | '\\r' ) ) '\"'
            {
            match('\"'); if (state.failed) return ;

            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            match('\"'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHARACTER"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:615:8: ( '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:616:3: '\"' (~ ( '\"' | '\\n' | '\\r' ) )* '\"'
            {
            match('\"'); if (state.failed) return ;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:616:7: (~ ( '\"' | '\\n' | '\\r' ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0 >= '\u0000' && LA22_0 <= '\t')||(LA22_0 >= '\u000B' && LA22_0 <= '\f')||(LA22_0 >= '\u000E' && LA22_0 <= '!')||(LA22_0 >= '#' && LA22_0 <= '\uFFFF')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            match('\"'); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:618:5: ( '\\'' ( ESC_SEQ |~ ( '\\'' | '\\\\' ) ) '\\'' )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:618:8: '\\'' ( ESC_SEQ |~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); if (state.failed) return ;

            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:618:13: ( ESC_SEQ |~ ( '\\'' | '\\\\' ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                alt23=1;
            }
            else if ( ((LA23_0 >= '\u0000' && LA23_0 <= '&')||(LA23_0 >= '(' && LA23_0 <= '[')||(LA23_0 >= ']' && LA23_0 <= '\uFFFF')) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:618:15: ESC_SEQ
                    {
                    mESC_SEQ(); if (state.failed) return ;


                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:618:25: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            match('\''); if (state.failed) return ;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:623:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:623:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:623:22: ( '+' | '-' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='+'||LA24_0=='-') ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:623:33: ( '0' .. '9' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0 >= '0' && LA25_0 <= '9')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:628:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt26=3;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt26=1;
                    }
                    break;
                case 'u':
                    {
                    alt26=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt26=3;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:628:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); if (state.failed) return ;

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:629:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); if (state.failed) return ;


                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:630:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); if (state.failed) return ;


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:635:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt27=3;
            int LA27_0 = input.LA(1);

            if ( (LA27_0=='\\') ) {
                int LA27_1 = input.LA(2);

                if ( ((LA27_1 >= '0' && LA27_1 <= '3')) ) {
                    int LA27_2 = input.LA(3);

                    if ( ((LA27_2 >= '0' && LA27_2 <= '7')) ) {
                        int LA27_4 = input.LA(4);

                        if ( ((LA27_4 >= '0' && LA27_4 <= '7')) ) {
                            alt27=1;
                        }
                        else {
                            alt27=2;
                        }
                    }
                    else {
                        alt27=3;
                    }
                }
                else if ( ((LA27_1 >= '4' && LA27_1 <= '7')) ) {
                    int LA27_3 = input.LA(3);

                    if ( ((LA27_3 >= '0' && LA27_3 <= '7')) ) {
                        alt27=2;
                    }
                    else {
                        alt27=3;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;

            }
            switch (alt27) {
                case 1 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:635:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); if (state.failed) return ;

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:636:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); if (state.failed) return ;

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:637:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); if (state.failed) return ;

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:642:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:642:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); if (state.failed) return ;

            match('u'); if (state.failed) return ;

            mHEX_DIGIT(); if (state.failed) return ;


            mHEX_DIGIT(); if (state.failed) return ;


            mHEX_DIGIT(); if (state.failed) return ;


            mHEX_DIGIT(); if (state.failed) return ;


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNICODE_ESC"

    public void mTokens() throws RecognitionException {
        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:8: ( T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | IncOp | UnaryOp | Unary2Op | ShiftOp | BitAndOp | BitOrOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolNotOp | BoolAndOp | BoolOrOp | Assign | Indent | Dedent | True | False | Methodheader | ID | WS | MultiLineComment | SingleLineComment | RANGE_OR_INT | Range | BINARY | QUATERNARY | HEXADECIMAL | NEWLINE | CHARACTER | STRING | CHAR )
        int alt28=78;
        alt28 = dfa28.predict(input);
        switch (alt28) {
            case 1 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:10: T__59
                {
                mT__59(); if (state.failed) return ;


                }
                break;
            case 2 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:16: T__60
                {
                mT__60(); if (state.failed) return ;


                }
                break;
            case 3 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:22: T__61
                {
                mT__61(); if (state.failed) return ;


                }
                break;
            case 4 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:28: T__62
                {
                mT__62(); if (state.failed) return ;


                }
                break;
            case 5 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:34: T__63
                {
                mT__63(); if (state.failed) return ;


                }
                break;
            case 6 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:40: T__64
                {
                mT__64(); if (state.failed) return ;


                }
                break;
            case 7 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:46: T__65
                {
                mT__65(); if (state.failed) return ;


                }
                break;
            case 8 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:52: T__66
                {
                mT__66(); if (state.failed) return ;


                }
                break;
            case 9 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:58: T__67
                {
                mT__67(); if (state.failed) return ;


                }
                break;
            case 10 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:64: T__68
                {
                mT__68(); if (state.failed) return ;


                }
                break;
            case 11 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:70: T__69
                {
                mT__69(); if (state.failed) return ;


                }
                break;
            case 12 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:76: T__70
                {
                mT__70(); if (state.failed) return ;


                }
                break;
            case 13 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:82: T__71
                {
                mT__71(); if (state.failed) return ;


                }
                break;
            case 14 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:88: T__72
                {
                mT__72(); if (state.failed) return ;


                }
                break;
            case 15 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:94: T__73
                {
                mT__73(); if (state.failed) return ;


                }
                break;
            case 16 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:100: T__74
                {
                mT__74(); if (state.failed) return ;


                }
                break;
            case 17 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:106: T__75
                {
                mT__75(); if (state.failed) return ;


                }
                break;
            case 18 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:112: T__76
                {
                mT__76(); if (state.failed) return ;


                }
                break;
            case 19 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:118: T__77
                {
                mT__77(); if (state.failed) return ;


                }
                break;
            case 20 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:124: T__78
                {
                mT__78(); if (state.failed) return ;


                }
                break;
            case 21 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:130: T__79
                {
                mT__79(); if (state.failed) return ;


                }
                break;
            case 22 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:136: T__80
                {
                mT__80(); if (state.failed) return ;


                }
                break;
            case 23 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:142: T__81
                {
                mT__81(); if (state.failed) return ;


                }
                break;
            case 24 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:148: T__82
                {
                mT__82(); if (state.failed) return ;


                }
                break;
            case 25 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:154: T__83
                {
                mT__83(); if (state.failed) return ;


                }
                break;
            case 26 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:160: T__84
                {
                mT__84(); if (state.failed) return ;


                }
                break;
            case 27 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:166: T__85
                {
                mT__85(); if (state.failed) return ;


                }
                break;
            case 28 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:172: T__86
                {
                mT__86(); if (state.failed) return ;


                }
                break;
            case 29 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:178: T__87
                {
                mT__87(); if (state.failed) return ;


                }
                break;
            case 30 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:184: T__88
                {
                mT__88(); if (state.failed) return ;


                }
                break;
            case 31 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:190: T__89
                {
                mT__89(); if (state.failed) return ;


                }
                break;
            case 32 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:196: T__90
                {
                mT__90(); if (state.failed) return ;


                }
                break;
            case 33 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:202: T__91
                {
                mT__91(); if (state.failed) return ;


                }
                break;
            case 34 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:208: T__92
                {
                mT__92(); if (state.failed) return ;


                }
                break;
            case 35 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:214: T__93
                {
                mT__93(); if (state.failed) return ;


                }
                break;
            case 36 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:220: T__94
                {
                mT__94(); if (state.failed) return ;


                }
                break;
            case 37 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:226: T__95
                {
                mT__95(); if (state.failed) return ;


                }
                break;
            case 38 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:232: T__96
                {
                mT__96(); if (state.failed) return ;


                }
                break;
            case 39 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:238: T__97
                {
                mT__97(); if (state.failed) return ;


                }
                break;
            case 40 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:244: T__98
                {
                mT__98(); if (state.failed) return ;


                }
                break;
            case 41 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:250: T__99
                {
                mT__99(); if (state.failed) return ;


                }
                break;
            case 42 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:256: T__100
                {
                mT__100(); if (state.failed) return ;


                }
                break;
            case 43 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:263: T__101
                {
                mT__101(); if (state.failed) return ;


                }
                break;
            case 44 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:270: T__102
                {
                mT__102(); if (state.failed) return ;


                }
                break;
            case 45 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:277: T__103
                {
                mT__103(); if (state.failed) return ;


                }
                break;
            case 46 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:284: T__104
                {
                mT__104(); if (state.failed) return ;


                }
                break;
            case 47 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:291: IncOp
                {
                mIncOp(); if (state.failed) return ;


                }
                break;
            case 48 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:297: UnaryOp
                {
                mUnaryOp(); if (state.failed) return ;


                }
                break;
            case 49 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:305: Unary2Op
                {
                mUnary2Op(); if (state.failed) return ;


                }
                break;
            case 50 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:314: ShiftOp
                {
                mShiftOp(); if (state.failed) return ;


                }
                break;
            case 51 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:322: BitAndOp
                {
                mBitAndOp(); if (state.failed) return ;


                }
                break;
            case 52 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:331: BitOrOp
                {
                mBitOrOp(); if (state.failed) return ;


                }
                break;
            case 53 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:339: Multiply
                {
                mMultiply(); if (state.failed) return ;


                }
                break;
            case 54 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:348: MultOp
                {
                mMultOp(); if (state.failed) return ;


                }
                break;
            case 55 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:355: LimitMaxOp
                {
                mLimitMaxOp(); if (state.failed) return ;


                }
                break;
            case 56 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:366: BoolOp
                {
                mBoolOp(); if (state.failed) return ;


                }
                break;
            case 57 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:373: BoolNotOp
                {
                mBoolNotOp(); if (state.failed) return ;


                }
                break;
            case 58 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:383: BoolAndOp
                {
                mBoolAndOp(); if (state.failed) return ;


                }
                break;
            case 59 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:393: BoolOrOp
                {
                mBoolOrOp(); if (state.failed) return ;


                }
                break;
            case 60 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:402: Assign
                {
                mAssign(); if (state.failed) return ;


                }
                break;
            case 61 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:409: Indent
                {
                mIndent(); if (state.failed) return ;


                }
                break;
            case 62 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:416: Dedent
                {
                mDedent(); if (state.failed) return ;


                }
                break;
            case 63 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:423: True
                {
                mTrue(); if (state.failed) return ;


                }
                break;
            case 64 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:428: False
                {
                mFalse(); if (state.failed) return ;


                }
                break;
            case 65 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:434: Methodheader
                {
                mMethodheader(); if (state.failed) return ;


                }
                break;
            case 66 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:447: ID
                {
                mID(); if (state.failed) return ;


                }
                break;
            case 67 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:450: WS
                {
                mWS(); if (state.failed) return ;


                }
                break;
            case 68 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:453: MultiLineComment
                {
                mMultiLineComment(); if (state.failed) return ;


                }
                break;
            case 69 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:470: SingleLineComment
                {
                mSingleLineComment(); if (state.failed) return ;


                }
                break;
            case 70 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:488: RANGE_OR_INT
                {
                mRANGE_OR_INT(); if (state.failed) return ;


                }
                break;
            case 71 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:501: Range
                {
                mRange(); if (state.failed) return ;


                }
                break;
            case 72 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:507: BINARY
                {
                mBINARY(); if (state.failed) return ;


                }
                break;
            case 73 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:514: QUATERNARY
                {
                mQUATERNARY(); if (state.failed) return ;


                }
                break;
            case 74 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:525: HEXADECIMAL
                {
                mHEXADECIMAL(); if (state.failed) return ;


                }
                break;
            case 75 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:537: NEWLINE
                {
                mNEWLINE(); if (state.failed) return ;


                }
                break;
            case 76 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:545: CHARACTER
                {
                mCHARACTER(); if (state.failed) return ;


                }
                break;
            case 77 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:555: STRING
                {
                mSTRING(); if (state.failed) return ;


                }
                break;
            case 78 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:1:562: CHAR
                {
                mCHAR(); if (state.failed) return ;


                }
                break;

        }

    }

    // $ANTLR start synpred1_Spin2Grammar
    public final void synpred1_Spin2Grammar_fragment() throws RecognitionException {
        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:577:9: ( INT '..' )
        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:577:11: INT '..'
        {
        mINT(); if (state.failed) return ;


        match(".."); if (state.failed) return ;



        }

    }
    // $ANTLR end synpred1_Spin2Grammar

    // $ANTLR start synpred2_Spin2Grammar
    public final void synpred2_Spin2Grammar_fragment() throws RecognitionException {
        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:9: ( INT '.' INT ( EXPONENT )? | INT EXPONENT )
        int alt30=2;
        alt30 = dfa30.predict(input);
        switch (alt30) {
            case 1 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:10: INT '.' INT ( EXPONENT )?
                {
                mINT(); if (state.failed) return ;


                match('.'); if (state.failed) return ;

                mINT(); if (state.failed) return ;


                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:22: ( EXPONENT )?
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0=='E'||LA29_0=='e') ) {
                    alt29=1;
                }
                switch (alt29) {
                    case 1 :
                        // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:22: EXPONENT
                        {
                        mEXPONENT(); if (state.failed) return ;


                        }
                        break;

                }


                }
                break;
            case 2 :
                // /home/darrylhill/Dropbox/workspace2/emulator/src/dang/antlr/parser/Spin2Grammar.g:578:34: INT EXPONENT
                {
                mINT(); if (state.failed) return ;


                mEXPONENT(); if (state.failed) return ;


                }
                break;

        }
    }
    // $ANTLR end synpred2_Spin2Grammar

    public final boolean synpred1_Spin2Grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Spin2Grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Spin2Grammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Spin2Grammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA16 dfa16 = new DFA16(this);
    protected DFA28 dfa28 = new DFA28(this);
    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA16_eotS =
        "\5\uffff";
    static final String DFA16_eofS =
        "\5\uffff";
    static final String DFA16_minS =
        "\1\60\2\56\2\uffff";
    static final String DFA16_maxS =
        "\1\71\2\145\2\uffff";
    static final String DFA16_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA16_specialS =
        "\5\uffff}>";
    static final String[] DFA16_transitionS = {
            "\12\1",
            "\1\3\1\uffff\12\2\13\uffff\1\4\31\uffff\1\2\5\uffff\1\4",
            "\1\3\1\uffff\12\2\13\uffff\1\4\31\uffff\1\2\5\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "572:1: fragment FLOAT : ( INT '.' INT ( EXPONENT )? | INT EXPONENT );";
        }
    }
    static final String DFA28_eotS =
        "\1\uffff\1\61\2\uffff\1\64\1\67\1\71\1\73\1\74\1\100\3\uffff\15"+
        "\47\1\125\1\130\1\uffff\1\131\1\134\1\uffff\1\134\1\141\2\143\3"+
        "\47\3\uffff\1\152\5\uffff\1\161\7\uffff\1\162\4\uffff\3\134\2\uffff"+
        "\6\47\1\171\2\47\1\175\2\47\1\u0082\6\47\2\uffff\1\162\2\uffff\2"+
        "\162\1\uffff\2\162\1\161\1\134\1\uffff\1\u0089\1\uffff\1\u0089\4"+
        "\47\1\152\1\uffff\1\152\7\uffff\2\47\1\u0096\3\47\1\uffff\2\47\1"+
        "\u009c\1\uffff\4\47\1\uffff\2\47\1\u00a5\3\47\1\uffff\1\u00a9\1"+
        "\u00aa\2\u00ab\4\152\2\uffff\1\u00b2\1\u00b3\1\uffff\1\u00b5\1\u00b6"+
        "\1\47\1\u00ba\1\47\1\uffff\2\47\1\u00bf\3\47\1\u00c3\1\47\1\uffff"+
        "\2\47\1\u00c9\3\uffff\3\152\1\uffff\2\47\2\uffff\1\47\2\uffff\1"+
        "\u00d0\2\47\1\uffff\4\47\1\uffff\3\47\1\uffff\1\u00da\1\47\1\u00dc"+
        "\2\47\1\uffff\3\152\2\47\1\u00e3\1\uffff\3\47\1\u00e8\1\u00e9\1"+
        "\u00ea\1\47\1\u00ec\1\47\1\uffff\1\47\1\uffff\2\47\2\152\2\47\1"+
        "\uffff\3\47\1\u00f8\3\uffff\1\u00f9\1\uffff\1\u00fa\1\u00fb\2\47"+
        "\2\152\1\u0100\1\u0101\1\u0102\1\u0103\1\u0105\4\uffff\1\u0106\1"+
        "\u0107\2\152\4\uffff\1\u0108\4\uffff";
    static final String DFA28_eofS =
        "\u0109\uffff";
    static final String DFA28_minS =
        "\1\11\1\76\2\uffff\1\53\1\74\1\55\1\56\1\75\1\74\3\uffff\1\171\1"+
        "\141\1\154\1\141\1\146\1\157\1\142\1\145\1\164\1\157\1\156\2\141"+
        "\1\74\1\76\1\uffff\1\75\1\74\1\uffff\1\43\1\75\1\52\1\57\1\157\1"+
        "\156\1\162\3\uffff\1\0\1\uffff\1\45\2\uffff\1\0\1\75\7\uffff\1\75"+
        "\4\uffff\3\75\2\uffff\1\164\1\163\1\156\1\163\1\157\1\154\1\60\1"+
        "\156\1\152\1\60\1\160\1\145\1\60\1\165\1\164\1\162\2\151\1\162\2"+
        "\uffff\1\75\2\uffff\2\75\1\uffff\4\75\1\uffff\1\75\1\uffff\1\75"+
        "\1\164\1\144\1\142\1\151\1\42\1\uffff\1\47\3\uffff\1\0\3\uffff\2"+
        "\145\1\60\1\145\1\155\1\163\1\uffff\1\147\1\153\1\60\1\uffff\1\145"+
        "\1\165\1\160\1\143\1\uffff\1\145\1\151\1\60\1\164\1\154\1\144\1"+
        "\uffff\4\60\1\47\1\60\2\47\2\uffff\2\60\1\uffff\2\60\1\145\1\60"+
        "\1\144\1\uffff\1\141\1\162\1\60\1\157\1\156\1\151\1\60\1\154\1\uffff"+
        "\1\143\1\145\1\60\3\uffff\1\60\2\47\1\uffff\1\151\1\157\2\uffff"+
        "\1\146\2\uffff\1\60\1\151\1\157\1\uffff\1\157\1\160\1\164\1\156"+
        "\1\uffff\1\155\1\147\1\172\1\uffff\1\60\1\156\1\60\1\151\1\157\1"+
        "\uffff\2\60\1\47\1\154\1\166\1\60\1\uffff\1\154\1\166\1\167\3\60"+
        "\1\160\1\60\1\145\1\uffff\1\164\1\uffff\1\154\1\166\2\60\1\154\1"+
        "\145\1\uffff\1\154\1\145\1\156\1\60\3\uffff\1\60\1\uffff\2\60\1"+
        "\154\1\145\1\47\6\60\4\uffff\2\60\2\47\4\uffff\1\60\4\uffff";
    static final String DFA28_maxS =
        "\1\176\1\76\2\uffff\1\75\2\76\1\56\1\75\1\76\3\uffff\1\171\1\157"+
        "\1\154\1\162\1\146\1\157\1\162\1\145\1\164\1\162\1\156\1\141\1\157"+
        "\1\174\1\176\1\uffff\1\136\1\174\1\uffff\1\76\3\75\1\157\1\156\1"+
        "\165\3\uffff\1\uffff\1\uffff\1\61\2\uffff\1\uffff\1\75\7\uffff\1"+
        "\75\4\uffff\3\75\2\uffff\1\164\1\163\1\156\1\163\1\157\1\154\1\172"+
        "\1\157\1\152\1\172\1\164\1\162\1\172\1\165\1\164\1\162\2\151\1\162"+
        "\2\uffff\1\75\2\uffff\2\75\1\uffff\4\75\1\uffff\1\75\1\uffff\1\75"+
        "\1\164\1\144\1\142\1\151\1\165\1\uffff\1\47\3\uffff\1\uffff\3\uffff"+
        "\2\145\1\172\1\145\1\155\1\163\1\uffff\1\147\1\153\1\172\1\uffff"+
        "\1\145\1\165\1\160\1\163\1\uffff\1\145\1\151\1\172\1\164\1\154\1"+
        "\144\1\uffff\4\172\1\47\1\146\2\67\2\uffff\2\172\1\uffff\2\172\1"+
        "\145\1\172\1\165\1\uffff\1\141\1\162\1\172\1\157\1\156\1\151\1\172"+
        "\1\154\1\uffff\1\143\1\145\1\172\3\uffff\1\146\1\67\1\47\1\uffff"+
        "\1\151\1\157\2\uffff\1\146\2\uffff\1\172\1\151\1\157\1\uffff\1\157"+
        "\1\160\1\164\1\156\1\uffff\1\155\1\147\1\172\1\uffff\1\172\1\156"+
        "\1\172\1\151\1\157\1\uffff\2\146\1\47\1\154\1\166\1\172\1\uffff"+
        "\1\154\1\166\1\167\3\172\1\160\1\172\1\145\1\uffff\1\164\1\uffff"+
        "\1\154\1\166\2\146\1\154\1\145\1\uffff\1\154\1\145\1\156\1\172\3"+
        "\uffff\1\172\1\uffff\2\172\1\154\1\145\2\146\5\172\4\uffff\2\172"+
        "\2\146\4\uffff\1\172\4\uffff";
    static final String DFA28_acceptS =
        "\2\uffff\1\2\1\3\6\uffff\1\12\1\13\1\14\17\uffff\1\60\2\uffff\1"+
        "\61\7\uffff\1\102\1\103\1\104\1\uffff\1\106\1\uffff\1\112\1\113"+
        "\2\uffff\1\1\1\57\1\74\1\4\1\75\1\76\1\5\1\uffff\1\6\1\107\1\7\1"+
        "\10\3\uffff\1\11\1\12\23\uffff\1\54\1\56\1\uffff\1\55\1\64\2\uffff"+
        "\1\70\4\uffff\1\63\1\uffff\1\66\6\uffff\1\105\1\uffff\1\116\1\111"+
        "\1\110\1\uffff\1\115\1\67\1\62\6\uffff\1\25\3\uffff\1\73\4\uffff"+
        "\1\44\6\uffff\1\65\10\uffff\1\105\1\114\2\uffff\1\21\5\uffff\1\35"+
        "\10\uffff\1\46\3\uffff\1\71\1\72\1\101\3\uffff\1\114\2\uffff\1\15"+
        "\1\20\1\uffff\1\22\1\24\3\uffff\1\26\4\uffff\1\40\3\uffff\1\77\5"+
        "\uffff\1\51\6\uffff\1\100\11\uffff\1\45\1\uffff\1\50\6\uffff\1\23"+
        "\4\uffff\1\33\1\36\1\37\1\uffff\1\42\13\uffff\1\34\1\41\1\43\1\47"+
        "\4\uffff\1\16\1\17\1\27\1\30\1\uffff\1\31\1\52\1\53\1\32";
    static final String DFA28_specialS =
        "\52\uffff\1\1\4\uffff\1\2\77\uffff\1\0\u0099\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\50\1\56\1\uffff\2\56\22\uffff\1\50\1\37\1\57\1\1\1\55\1\54"+
            "\1\41\1\52\1\2\1\3\1\42\1\4\1\5\1\6\1\7\1\43\12\53\1\10\1\uffff"+
            "\1\40\1\11\1\36\1\12\1\34\32\47\1\13\1\uffff\1\14\1\35\1\47"+
            "\1\uffff\1\45\1\15\1\16\1\47\1\17\1\20\2\47\1\21\2\47\1\22\1"+
            "\47\1\44\1\23\1\46\1\47\1\24\1\25\1\26\1\27\1\30\1\31\3\47\1"+
            "\51\1\32\1\uffff\1\33",
            "\1\60",
            "",
            "",
            "\1\62\21\uffff\1\63",
            "\1\65\1\uffff\1\66",
            "\1\62\17\uffff\1\63\1\70",
            "\1\72",
            "\1\63",
            "\1\75\1\77\1\76",
            "",
            "",
            "",
            "\1\102",
            "\1\103\15\uffff\1\104",
            "\1\105",
            "\1\107\20\uffff\1\106",
            "\1\110",
            "\1\111",
            "\1\112\17\uffff\1\113",
            "\1\114",
            "\1\115",
            "\1\116\2\uffff\1\117",
            "\1\120",
            "\1\121",
            "\1\122\6\uffff\1\123\6\uffff\1\124",
            "\1\37\1\63\76\uffff\1\37",
            "\1\127\77\uffff\1\126",
            "",
            "\1\63\40\uffff\1\37",
            "\1\133\1\63\1\132\75\uffff\1\37",
            "",
            "\1\137\11\uffff\1\135\16\uffff\1\136\1\63\1\140",
            "\1\63",
            "\1\142\22\uffff\1\63",
            "\1\144\15\uffff\1\63",
            "\1\145",
            "\1\146",
            "\1\150\2\uffff\1\147",
            "",
            "",
            "",
            "\12\153\1\154\2\153\1\154\31\153\1\uffff\64\153\1\151\uffa3"+
            "\153",
            "",
            "\1\155\12\uffff\2\156",
            "",
            "",
            "\12\157\1\uffff\2\157\1\uffff\24\157\1\160\uffdd\157",
            "\1\63",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\63",
            "",
            "",
            "",
            "",
            "\1\63",
            "\1\63",
            "\1\63",
            "",
            "",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\172\1\173",
            "\1\174",
            "\12\47\3\uffff\1\63\3\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\176\3\uffff\1\177",
            "\1\u0080\14\uffff\1\u0081",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "",
            "",
            "\1\63",
            "",
            "",
            "\1\63",
            "\1\63",
            "",
            "\1\63",
            "\1\63",
            "\1\63",
            "\1\63",
            "",
            "\1\63",
            "",
            "\1\63",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e\4\uffff\1\u008e\10\uffff\4\u0090\4\u0091\44\uffff"+
            "\1\u008e\5\uffff\1\u008e\3\uffff\1\u008e\7\uffff\1\u008e\3\uffff"+
            "\1\u008e\1\uffff\1\u008e\1\u008f",
            "",
            "\1\u0092",
            "",
            "",
            "",
            "\12\160\1\uffff\2\160\1\uffff\24\160\1\u0093\uffdd\160",
            "",
            "",
            "",
            "\1\u0094",
            "\1\u0095",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "",
            "\1\u009a",
            "\1\u009b",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0\5\uffff\1\u00a1\11\uffff\1\u00a2",
            "",
            "\1\u00a3",
            "\1\u00a4",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\3\uffff\1\63\3\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u0092",
            "\12\u00ac\7\uffff\6\u00ac\32\uffff\6\u00ac",
            "\1\u0092\10\uffff\10\u00ad",
            "\1\u0092\10\uffff\10\u00ae",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\5\47\1\u00b0\6\47"+
            "\1\u00b1\15\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\10\47\1\u00b4\21"+
            "\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00b7",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\5\47\1\u00b8\6\47"+
            "\1\u00b9\15\47",
            "\1\u00bb\20\uffff\1\u00bc",
            "",
            "\1\u00bd",
            "\1\u00be",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00c4",
            "",
            "\1\u00c5",
            "\1\u00c6",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\5\47\1\u00c7\6\47"+
            "\1\u00c8\15\47",
            "",
            "",
            "",
            "\12\u00ca\7\uffff\6\u00ca\30\uffff\1\u00cb\1\uffff\6\u00ca",
            "\1\u0092\10\uffff\10\u00cc",
            "\1\u0092",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "",
            "",
            "\1\u00cf",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00d1",
            "\1\u00d2",
            "",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00db",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00dd",
            "\1\u00de",
            "",
            "\12\u00df\7\uffff\6\u00df\30\uffff\1\u00e0\1\uffff\6\u00df",
            "\12\u00ca\7\uffff\6\u00ca\30\uffff\1\u00cb\1\uffff\6\u00ca",
            "\1\u0092",
            "\1\u00e1",
            "\1\u00e2",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\31\47\1\u00e7",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00eb",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00ed",
            "",
            "\1\u00ee",
            "",
            "\1\u00ef",
            "\1\u00f0",
            "\12\u00f1\7\uffff\6\u00f1\30\uffff\1\u00f2\1\uffff\6\u00f1",
            "\12\u00df\7\uffff\6\u00df\30\uffff\1\u00e0\1\uffff\6\u00df",
            "\1\u00f3",
            "\1\u00f4",
            "",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u0092\10\uffff\12\u00fe\7\uffff\6\u00fe\30\uffff\1\u00ff"+
            "\1\uffff\6\u00fe",
            "\12\u00f1\7\uffff\6\u00f1\30\uffff\1\u00f2\1\uffff\6\u00f1",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\31\47\1\u0104",
            "",
            "",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "\1\u0092\10\uffff\12\u00fe\7\uffff\6\u00fe\30\uffff\1\u00ff"+
            "\1\uffff\6\u00fe",
            "\1\u0092\10\uffff\12\u00fe\7\uffff\6\u00fe\30\uffff\1\u00ff"+
            "\1\uffff\6\u00fe",
            "",
            "",
            "",
            "",
            "\12\47\7\uffff\32\47\4\uffff\1\47\1\uffff\32\47",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | IncOp | UnaryOp | Unary2Op | ShiftOp | BitAndOp | BitOrOp | Multiply | MultOp | LimitMaxOp | BoolOp | BoolNotOp | BoolAndOp | BoolOrOp | Assign | Indent | Dedent | True | False | Methodheader | ID | WS | MultiLineComment | SingleLineComment | RANGE_OR_INT | Range | BINARY | QUATERNARY | HEXADECIMAL | NEWLINE | CHARACTER | STRING | CHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA28_111 = input.LA(1);

                        s = -1;
                        if ( (LA28_111=='\"') ) {s = 147;}

                        else if ( ((LA28_111 >= '\u0000' && LA28_111 <= '\t')||(LA28_111 >= '\u000B' && LA28_111 <= '\f')||(LA28_111 >= '\u000E' && LA28_111 <= '!')||(LA28_111 >= '#' && LA28_111 <= '\uFFFF')) ) {s = 112;}

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA28_42 = input.LA(1);

                        s = -1;
                        if ( (LA28_42=='\\') ) {s = 105;}

                        else if ( ((LA28_42 >= '\u0000' && LA28_42 <= '\t')||(LA28_42 >= '\u000B' && LA28_42 <= '\f')||(LA28_42 >= '\u000E' && LA28_42 <= '&')||(LA28_42 >= '(' && LA28_42 <= '[')||(LA28_42 >= ']' && LA28_42 <= '\uFFFF')) ) {s = 107;}

                        else if ( (LA28_42=='\n'||LA28_42=='\r') ) {s = 108;}

                        else s = 106;

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA28_47 = input.LA(1);

                        s = -1;
                        if ( ((LA28_47 >= '\u0000' && LA28_47 <= '\t')||(LA28_47 >= '\u000B' && LA28_47 <= '\f')||(LA28_47 >= '\u000E' && LA28_47 <= '!')||(LA28_47 >= '#' && LA28_47 <= '\uFFFF')) ) {s = 111;}

                        else if ( (LA28_47=='\"') ) {s = 112;}

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 28, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA30_eotS =
        "\5\uffff";
    static final String DFA30_eofS =
        "\5\uffff";
    static final String DFA30_minS =
        "\1\60\2\56\2\uffff";
    static final String DFA30_maxS =
        "\1\71\2\145\2\uffff";
    static final String DFA30_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA30_specialS =
        "\5\uffff}>";
    static final String[] DFA30_transitionS = {
            "\12\1",
            "\1\3\1\uffff\12\2\13\uffff\1\4\31\uffff\1\2\5\uffff\1\4",
            "\1\3\1\uffff\12\2\13\uffff\1\4\31\uffff\1\2\5\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "578:9: fragment synpred2_Spin2Grammar : ( INT '.' INT ( EXPONENT )? | INT EXPONENT );";
        }
    }
 

}