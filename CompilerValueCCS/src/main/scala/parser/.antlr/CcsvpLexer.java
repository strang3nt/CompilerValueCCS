// Generated from Ccsvp.g4 by ANTLR 4.10.1
package main.scala.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CcsvpLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, DEFINE=2, SEPARATOR=3, COMMA=4, OUT=5, LBRACKET=6, RBRACKET=7, CURLY_LBRACKET=8, 
		CURLY_RBRACKET=9, SQUARED_LBRACKET=10, SQUARED_RBRACKET=11, SUM=12, SUB=13, 
		MUL=14, DIV=15, LEQ=16, LE=17, GEQ=18, GE=19, EQUALS=20, NOT=21, AND=22, 
		OR=23, PAR=24, IF=25, THEN=26, RESTR=27, TAU=28, INTEGER=29, IDENTIFIER=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "DEFINE", "SEPARATOR", "COMMA", "OUT", "LBRACKET", "RBRACKET", 
			"CURLY_LBRACKET", "CURLY_RBRACKET", "SQUARED_LBRACKET", "SQUARED_RBRACKET", 
			"SUM", "SUB", "MUL", "DIV", "LEQ", "LE", "GEQ", "GE", "EQUALS", "NOT", 
			"AND", "OR", "PAR", "IF", "THEN", "RESTR", "TAU", "INTEGER", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'='", "'.'", "','", "'''", "'('", "')'", "'{'", "'}'", "'['", 
			"']'", "'+'", "'-'", "'*'", "'/'", "'<='", "'<'", "'>='", "'>'", "'=='", 
			"'!'", "'&&'", "'||'", "'|'", "'if'", "'then'", "'\\'", "'tau'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "DEFINE", "SEPARATOR", "COMMA", "OUT", "LBRACKET", "RBRACKET", 
			"CURLY_LBRACKET", "CURLY_RBRACKET", "SQUARED_LBRACKET", "SQUARED_RBRACKET", 
			"SUM", "SUB", "MUL", "DIV", "LEQ", "LE", "GEQ", "GE", "EQUALS", "NOT", 
			"AND", "OR", "PAR", "IF", "THEN", "RESTR", "TAU", "INTEGER", "IDENTIFIER"
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


	public CcsvpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Ccsvp.g4"; }

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
		"\u0004\u0000\u001e\u0091\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0004\u0000?\b\u0000\u000b\u0000\f\u0000@\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0004\u001c\u0087\b\u001c\u000b"+
		"\u001c\f\u001c\u0088\u0001\u001d\u0001\u001d\u0005\u001d\u008d\b\u001d"+
		"\n\u001d\f\u001d\u0090\t\u001d\u0000\u0000\u001e\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e\u0001\u0000\u0004\u0002\u0000\t\n  \u0001\u0000"+
		"09\u0002\u0000AZaz\u0004\u000009AZ__az\u0093\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0001>\u0001\u0000\u0000\u0000\u0003D"+
		"\u0001\u0000\u0000\u0000\u0005F\u0001\u0000\u0000\u0000\u0007H\u0001\u0000"+
		"\u0000\u0000\tJ\u0001\u0000\u0000\u0000\u000bL\u0001\u0000\u0000\u0000"+
		"\rN\u0001\u0000\u0000\u0000\u000fP\u0001\u0000\u0000\u0000\u0011R\u0001"+
		"\u0000\u0000\u0000\u0013T\u0001\u0000\u0000\u0000\u0015V\u0001\u0000\u0000"+
		"\u0000\u0017X\u0001\u0000\u0000\u0000\u0019Z\u0001\u0000\u0000\u0000\u001b"+
		"\\\u0001\u0000\u0000\u0000\u001d^\u0001\u0000\u0000\u0000\u001f`\u0001"+
		"\u0000\u0000\u0000!c\u0001\u0000\u0000\u0000#e\u0001\u0000\u0000\u0000"+
		"%h\u0001\u0000\u0000\u0000\'j\u0001\u0000\u0000\u0000)m\u0001\u0000\u0000"+
		"\u0000+o\u0001\u0000\u0000\u0000-r\u0001\u0000\u0000\u0000/u\u0001\u0000"+
		"\u0000\u00001w\u0001\u0000\u0000\u00003z\u0001\u0000\u0000\u00005\u007f"+
		"\u0001\u0000\u0000\u00007\u0081\u0001\u0000\u0000\u00009\u0086\u0001\u0000"+
		"\u0000\u0000;\u008a\u0001\u0000\u0000\u0000=?\u0007\u0000\u0000\u0000"+
		">=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000"+
		"\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0006\u0000"+
		"\u0000\u0000C\u0002\u0001\u0000\u0000\u0000DE\u0005=\u0000\u0000E\u0004"+
		"\u0001\u0000\u0000\u0000FG\u0005.\u0000\u0000G\u0006\u0001\u0000\u0000"+
		"\u0000HI\u0005,\u0000\u0000I\b\u0001\u0000\u0000\u0000JK\u0005\'\u0000"+
		"\u0000K\n\u0001\u0000\u0000\u0000LM\u0005(\u0000\u0000M\f\u0001\u0000"+
		"\u0000\u0000NO\u0005)\u0000\u0000O\u000e\u0001\u0000\u0000\u0000PQ\u0005"+
		"{\u0000\u0000Q\u0010\u0001\u0000\u0000\u0000RS\u0005}\u0000\u0000S\u0012"+
		"\u0001\u0000\u0000\u0000TU\u0005[\u0000\u0000U\u0014\u0001\u0000\u0000"+
		"\u0000VW\u0005]\u0000\u0000W\u0016\u0001\u0000\u0000\u0000XY\u0005+\u0000"+
		"\u0000Y\u0018\u0001\u0000\u0000\u0000Z[\u0005-\u0000\u0000[\u001a\u0001"+
		"\u0000\u0000\u0000\\]\u0005*\u0000\u0000]\u001c\u0001\u0000\u0000\u0000"+
		"^_\u0005/\u0000\u0000_\u001e\u0001\u0000\u0000\u0000`a\u0005<\u0000\u0000"+
		"ab\u0005=\u0000\u0000b \u0001\u0000\u0000\u0000cd\u0005<\u0000\u0000d"+
		"\"\u0001\u0000\u0000\u0000ef\u0005>\u0000\u0000fg\u0005=\u0000\u0000g"+
		"$\u0001\u0000\u0000\u0000hi\u0005>\u0000\u0000i&\u0001\u0000\u0000\u0000"+
		"jk\u0005=\u0000\u0000kl\u0005=\u0000\u0000l(\u0001\u0000\u0000\u0000m"+
		"n\u0005!\u0000\u0000n*\u0001\u0000\u0000\u0000op\u0005&\u0000\u0000pq"+
		"\u0005&\u0000\u0000q,\u0001\u0000\u0000\u0000rs\u0005|\u0000\u0000st\u0005"+
		"|\u0000\u0000t.\u0001\u0000\u0000\u0000uv\u0005|\u0000\u0000v0\u0001\u0000"+
		"\u0000\u0000wx\u0005i\u0000\u0000xy\u0005f\u0000\u0000y2\u0001\u0000\u0000"+
		"\u0000z{\u0005t\u0000\u0000{|\u0005h\u0000\u0000|}\u0005e\u0000\u0000"+
		"}~\u0005n\u0000\u0000~4\u0001\u0000\u0000\u0000\u007f\u0080\u0005\\\u0000"+
		"\u0000\u00806\u0001\u0000\u0000\u0000\u0081\u0082\u0005t\u0000\u0000\u0082"+
		"\u0083\u0005a\u0000\u0000\u0083\u0084\u0005u\u0000\u0000\u00848\u0001"+
		"\u0000\u0000\u0000\u0085\u0087\u0007\u0001\u0000\u0000\u0086\u0085\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0086\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089:\u0001\u0000"+
		"\u0000\u0000\u008a\u008e\u0007\u0002\u0000\u0000\u008b\u008d\u0007\u0003"+
		"\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000"+
		"\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000"+
		"\u0000\u0000\u008f<\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000"+
		"\u0000\u0004\u0000@\u0088\u008e\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}