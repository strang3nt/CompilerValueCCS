// Generated from /home/aflori/Desktop/Projects/CompilerValueCCS/ccsvplexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ccsvplexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, INTEGER=2, IDENTIFIER=3, DEFINE=4, SEPARATOR=5, COMMA=6, OUT=7, 
		LBRACKET=8, RBRACKET=9, CURLY_LBRACKET=10, CURLY_RBRACKET=11, SQUARED_LBRACKET=12, 
		SQUARED_RBRACKET=13, SUM=14, SUB=15, MUL=16, DIV=17, LEQ=18, LE=19, GEQ=20, 
		GE=21, EQUALS=22, NOT=23, AND=24, OR=25, PAR=26, IF=27, THEN=28, RESTR=29, 
		TAU=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "INTEGER", "IDENTIFIER", "DEFINE", "SEPARATOR", "COMMA", "OUT", 
			"LBRACKET", "RBRACKET", "CURLY_LBRACKET", "CURLY_RBRACKET", "SQUARED_LBRACKET", 
			"SQUARED_RBRACKET", "SUM", "SUB", "MUL", "DIV", "LEQ", "LE", "GEQ", "GE", 
			"EQUALS", "NOT", "AND", "OR", "PAR", "IF", "THEN", "RESTR", "TAU"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'='", "'.'", "','", "'''", "'('", "')'", "'{'", 
			"'}'", "'['", "']'", "'+'", "'-'", "'*'", "'/'", "'<='", "'<'", "'>='", 
			"'>'", "'=='", "'!'", "'&&'", "'||'", "'|'", "'if'", "'then'", "'\\'", 
			"'tau'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "INTEGER", "IDENTIFIER", "DEFINE", "SEPARATOR", "COMMA", 
			"OUT", "LBRACKET", "RBRACKET", "CURLY_LBRACKET", "CURLY_RBRACKET", "SQUARED_LBRACKET", 
			"SQUARED_RBRACKET", "SUM", "SUB", "MUL", "DIV", "LEQ", "LE", "GEQ", "GE", 
			"EQUALS", "NOT", "AND", "OR", "PAR", "IF", "THEN", "RESTR", "TAU"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ccsvplexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ccsvplexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u0093\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\6\2A"+
		"\n\2\r\2\16\2B\3\2\3\2\3\3\6\3H\n\3\r\3\16\3I\3\4\3\4\7\4N\n\4\f\4\16"+
		"\4Q\13\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\6\4\2\13\f\"\"\3\2\62"+
		";\4\2C\\c|\6\2\62;C\\aac|\2\u0095\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3@\3\2\2\2\5G\3\2\2"+
		"\2\7K\3\2\2\2\tR\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2\2\21Z\3\2\2"+
		"\2\23\\\3\2\2\2\25^\3\2\2\2\27`\3\2\2\2\31b\3\2\2\2\33d\3\2\2\2\35f\3"+
		"\2\2\2\37h\3\2\2\2!j\3\2\2\2#l\3\2\2\2%n\3\2\2\2\'q\3\2\2\2)s\3\2\2\2"+
		"+v\3\2\2\2-x\3\2\2\2/{\3\2\2\2\61}\3\2\2\2\63\u0080\3\2\2\2\65\u0083\3"+
		"\2\2\2\67\u0085\3\2\2\29\u0088\3\2\2\2;\u008d\3\2\2\2=\u008f\3\2\2\2?"+
		"A\t\2\2\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\b\2\2\2"+
		"E\4\3\2\2\2FH\t\3\2\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\6\3\2\2"+
		"\2KO\t\4\2\2LN\t\5\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\b\3\2"+
		"\2\2QO\3\2\2\2RS\7?\2\2S\n\3\2\2\2TU\7\60\2\2U\f\3\2\2\2VW\7.\2\2W\16"+
		"\3\2\2\2XY\7)\2\2Y\20\3\2\2\2Z[\7*\2\2[\22\3\2\2\2\\]\7+\2\2]\24\3\2\2"+
		"\2^_\7}\2\2_\26\3\2\2\2`a\7\177\2\2a\30\3\2\2\2bc\7]\2\2c\32\3\2\2\2d"+
		"e\7_\2\2e\34\3\2\2\2fg\7-\2\2g\36\3\2\2\2hi\7/\2\2i \3\2\2\2jk\7,\2\2"+
		"k\"\3\2\2\2lm\7\61\2\2m$\3\2\2\2no\7>\2\2op\7?\2\2p&\3\2\2\2qr\7>\2\2"+
		"r(\3\2\2\2st\7@\2\2tu\7?\2\2u*\3\2\2\2vw\7@\2\2w,\3\2\2\2xy\7?\2\2yz\7"+
		"?\2\2z.\3\2\2\2{|\7#\2\2|\60\3\2\2\2}~\7(\2\2~\177\7(\2\2\177\62\3\2\2"+
		"\2\u0080\u0081\7~\2\2\u0081\u0082\7~\2\2\u0082\64\3\2\2\2\u0083\u0084"+
		"\7~\2\2\u0084\66\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087\7h\2\2\u00878"+
		"\3\2\2\2\u0088\u0089\7v\2\2\u0089\u008a\7j\2\2\u008a\u008b\7g\2\2\u008b"+
		"\u008c\7p\2\2\u008c:\3\2\2\2\u008d\u008e\7^\2\2\u008e<\3\2\2\2\u008f\u0090"+
		"\7v\2\2\u0090\u0091\7c\2\2\u0091\u0092\7w\2\2\u0092>\3\2\2\2\6\2BIO\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}