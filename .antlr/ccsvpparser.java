// Generated from /home/aflori/Desktop/Projects/CompilerValueCCS/ccsvpparser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ccsvpparser extends Parser {
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
	public static final int
		RULE_expr = 0, RULE_term = 1, RULE_factor = 2, RULE_boolbinop = 3, RULE_bterm = 4, 
		RULE_exprbinop = 5, RULE_constant = 6, RULE_ccsvp = 7, RULE_program = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"expr", "term", "factor", "boolbinop", "bterm", "exprbinop", "constant", 
			"ccsvp", "program"
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

	@Override
	public String getGrammarFileName() { return "ccsvpparser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ccsvpparser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> SUM() { return getTokens(ccsvpparser.SUM); }
		public TerminalNode SUM(int i) {
			return getToken(ccsvpparser.SUM, i);
		}
		public List<TerminalNode> SUB() { return getTokens(ccsvpparser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(ccsvpparser.SUB, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			term();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUM || _la==SUB) {
				{
				{
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==SUM || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(20);
				term();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(ccsvpparser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(ccsvpparser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(ccsvpparser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(ccsvpparser.DIV, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			factor();
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(27);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(28);
				factor();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ccsvpparser.IDENTIFIER, 0); }
		public TerminalNode INTEGER() { return getToken(ccsvpparser.INTEGER, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_factor);
		try {
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				match(LBRACKET);
				setState(35);
				expr();
				setState(36);
				match(RBRACKET);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(IDENTIFIER);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolbinopContext extends ParserRuleContext {
		public List<BtermContext> bterm() {
			return getRuleContexts(BtermContext.class);
		}
		public BtermContext bterm(int i) {
			return getRuleContext(BtermContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(ccsvpparser.AND); }
		public TerminalNode AND(int i) {
			return getToken(ccsvpparser.AND, i);
		}
		public List<TerminalNode> OR() { return getTokens(ccsvpparser.OR); }
		public TerminalNode OR(int i) {
			return getToken(ccsvpparser.OR, i);
		}
		public BoolbinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolbinop; }
	}

	public final BoolbinopContext boolbinop() throws RecognitionException {
		BoolbinopContext _localctx = new BoolbinopContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_boolbinop);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			bterm();
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(43);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(44);
					bterm();
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BtermContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(ccsvpparser.NOT, 0); }
		public BoolbinopContext boolbinop() {
			return getRuleContext(BoolbinopContext.class,0);
		}
		public ExprbinopContext exprbinop() {
			return getRuleContext(ExprbinopContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public BtermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bterm; }
	}

	public final BtermContext bterm() throws RecognitionException {
		BtermContext _localctx = new BtermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bterm);
		try {
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(NOT);
				setState(51);
				boolbinop();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				exprbinop();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				match(LBRACKET);
				setState(54);
				boolbinop();
				setState(55);
				match(RBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprbinopContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LE() { return getToken(ccsvpparser.LE, 0); }
		public TerminalNode GE() { return getToken(ccsvpparser.GE, 0); }
		public TerminalNode GEQ() { return getToken(ccsvpparser.GEQ, 0); }
		public TerminalNode LEQ() { return getToken(ccsvpparser.LEQ, 0); }
		public TerminalNode EQUALS() { return getToken(ccsvpparser.EQUALS, 0); }
		public ExprbinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprbinop; }
	}

	public final ExprbinopContext exprbinop() throws RecognitionException {
		ExprbinopContext _localctx = new ExprbinopContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprbinop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			expr();
			setState(60);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << LE) | (1L << GEQ) | (1L << GE) | (1L << EQUALS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(61);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ccsvpparser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ccsvpparser.IDENTIFIER, i);
		}
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ccsvpparser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ccsvpparser.COMMA, i);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(IDENTIFIER);
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(64);
				match(LBRACKET);
				setState(65);
				match(IDENTIFIER);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(66);
					match(COMMA);
					setState(67);
					match(IDENTIFIER);
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(73);
				match(RBRACKET);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CcsvpContext extends ParserRuleContext {
		public CcsvpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ccsvp; }
	 
		public CcsvpContext() { }
		public void copyFrom(CcsvpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TauchContext extends CcsvpContext {
		public TerminalNode TAU() { return getToken(ccsvpparser.TAU, 0); }
		public TerminalNode SEPARATOR() { return getToken(ccsvpparser.SEPARATOR, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public TauchContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class ParContext extends CcsvpContext {
		public List<CcsvpContext> ccsvp() {
			return getRuleContexts(CcsvpContext.class);
		}
		public CcsvpContext ccsvp(int i) {
			return getRuleContext(CcsvpContext.class,i);
		}
		public TerminalNode PAR() { return getToken(ccsvpparser.PAR, 0); }
		public ParContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class RestrictionContext extends CcsvpContext {
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public TerminalNode RESTR() { return getToken(ccsvpparser.RESTR, 0); }
		public TerminalNode CURLY_LBRACKET() { return getToken(ccsvpparser.CURLY_LBRACKET, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(ccsvpparser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ccsvpparser.IDENTIFIER, i);
		}
		public TerminalNode CURLY_RBRACKET() { return getToken(ccsvpparser.CURLY_RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ccsvpparser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ccsvpparser.COMMA, i);
		}
		public RestrictionContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class InputchContext extends CcsvpContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ccsvpparser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ccsvpparser.IDENTIFIER, i);
		}
		public TerminalNode SEPARATOR() { return getToken(ccsvpparser.SEPARATOR, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ccsvpparser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ccsvpparser.COMMA, i);
		}
		public InputchContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class ParenthesisContext extends CcsvpContext {
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public ParenthesisContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class RedirectionContext extends CcsvpContext {
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public List<TerminalNode> SQUARED_LBRACKET() { return getTokens(ccsvpparser.SQUARED_LBRACKET); }
		public TerminalNode SQUARED_LBRACKET(int i) {
			return getToken(ccsvpparser.SQUARED_LBRACKET, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(ccsvpparser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ccsvpparser.IDENTIFIER, i);
		}
		public List<TerminalNode> DIV() { return getTokens(ccsvpparser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(ccsvpparser.DIV, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ccsvpparser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ccsvpparser.COMMA, i);
		}
		public RedirectionContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class ConstContext extends CcsvpContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class SumContext extends CcsvpContext {
		public List<CcsvpContext> ccsvp() {
			return getRuleContexts(CcsvpContext.class);
		}
		public CcsvpContext ccsvp(int i) {
			return getRuleContext(CcsvpContext.class,i);
		}
		public List<TerminalNode> SUM() { return getTokens(ccsvpparser.SUM); }
		public TerminalNode SUM(int i) {
			return getToken(ccsvpparser.SUM, i);
		}
		public SumContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class IfthenContext extends CcsvpContext {
		public TerminalNode IF() { return getToken(ccsvpparser.IF, 0); }
		public BoolbinopContext boolbinop() {
			return getRuleContext(BoolbinopContext.class,0);
		}
		public TerminalNode THEN() { return getToken(ccsvpparser.THEN, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public IfthenContext(CcsvpContext ctx) { copyFrom(ctx); }
	}
	public static class OutputchContext extends CcsvpContext {
		public TerminalNode OUT() { return getToken(ccsvpparser.OUT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ccsvpparser.IDENTIFIER, 0); }
		public TerminalNode SEPARATOR() { return getToken(ccsvpparser.SEPARATOR, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(ccsvpparser.LBRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(ccsvpparser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ccsvpparser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ccsvpparser.COMMA, i);
		}
		public OutputchContext(CcsvpContext ctx) { copyFrom(ctx); }
	}

	public final CcsvpContext ccsvp() throws RecognitionException {
		return ccsvp(0);
	}

	private CcsvpContext ccsvp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CcsvpContext _localctx = new CcsvpContext(_ctx, _parentState);
		CcsvpContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_ccsvp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new ConstContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(77);
				constant();
				}
				break;
			case 2:
				{
				_localctx = new IfthenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				match(IF);
				setState(79);
				boolbinop();
				setState(80);
				match(THEN);
				setState(81);
				ccsvp(9);
				}
				break;
			case 3:
				{
				_localctx = new InputchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(IDENTIFIER);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(84);
					match(LBRACKET);
					setState(85);
					match(IDENTIFIER);
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(86);
						match(COMMA);
						setState(87);
						match(IDENTIFIER);
						}
						}
						setState(92);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(93);
					match(RBRACKET);
					}
				}

				setState(96);
				match(SEPARATOR);
				setState(97);
				ccsvp(6);
				}
				break;
			case 4:
				{
				_localctx = new OutputchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				match(OUT);
				setState(99);
				match(IDENTIFIER);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LBRACKET) {
					{
					setState(100);
					match(LBRACKET);
					setState(101);
					expr();
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(102);
						match(COMMA);
						setState(103);
						expr();
						}
						}
						setState(108);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(109);
					match(RBRACKET);
					}
				}

				setState(113);
				match(SEPARATOR);
				setState(114);
				ccsvp(5);
				}
				break;
			case 5:
				{
				_localctx = new TauchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(TAU);
				setState(116);
				match(SEPARATOR);
				setState(117);
				ccsvp(4);
				}
				break;
			case 6:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(LBRACKET);
				setState(119);
				ccsvp(0);
				setState(120);
				match(RBRACKET);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(163);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(161);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ParContext(new CcsvpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ccsvp);
						setState(124);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(125);
						match(PAR);
						setState(126);
						ccsvp(3);
						}
						break;
					case 2:
						{
						_localctx = new RestrictionContext(new CcsvpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ccsvp);
						setState(127);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(128);
						match(RESTR);
						setState(129);
						match(CURLY_LBRACKET);
						setState(130);
						match(IDENTIFIER);
						setState(135);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(131);
							match(COMMA);
							setState(132);
							match(IDENTIFIER);
							}
							}
							setState(137);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(138);
						match(CURLY_RBRACKET);
						}
						break;
					case 3:
						{
						_localctx = new RedirectionContext(new CcsvpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ccsvp);
						setState(139);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(140);
						match(SQUARED_LBRACKET);
						setState(141);
						match(IDENTIFIER);
						setState(142);
						match(DIV);
						setState(143);
						match(IDENTIFIER);
						setState(150);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(144);
							match(COMMA);
							setState(145);
							match(IDENTIFIER);
							setState(146);
							match(DIV);
							setState(147);
							match(IDENTIFIER);
							}
							}
							setState(152);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(153);
						match(SQUARED_LBRACKET);
						}
						break;
					case 4:
						{
						_localctx = new SumContext(new CcsvpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_ccsvp);
						setState(154);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(157); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(155);
								match(SUM);
								setState(156);
								ccsvp(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(159); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode DEFINE() { return getToken(ccsvpparser.DEFINE, 0); }
		public CcsvpContext ccsvp() {
			return getRuleContext(CcsvpContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			constant();
			setState(167);
			match(DEFINE);
			setState(168);
			ccsvp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return ccsvp_sempred((CcsvpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ccsvp_sempred(CcsvpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00ad\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4+\n\4\3\5\3\5\3\5\7\5\60\n\5\f\5\16\5\63\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6<\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\7\bG\n\b\f\b\16\bJ\13\b\3\b\5\bM\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\7\t[\n\t\f\t\16\t^\13\t\3\t\5\ta\n\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\7\tk\n\t\f\t\16\tn\13\t\3\t\3\t\5\tr\n\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t}\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\7\t\u0088\n\t\f\t\16\t\u008b\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\t\3\t\3\t\3\t\6\t\u00a0"+
		"\n\t\r\t\16\t\u00a1\7\t\u00a4\n\t\f\t\16\t\u00a7\13\t\3\n\3\n\3\n\3\n"+
		"\3\n\2\3\20\13\2\4\6\b\n\f\16\20\22\2\6\3\2\20\21\3\2\22\23\3\2\32\33"+
		"\3\2\24\30\2\u00bc\2\24\3\2\2\2\4\34\3\2\2\2\6*\3\2\2\2\b,\3\2\2\2\n;"+
		"\3\2\2\2\f=\3\2\2\2\16A\3\2\2\2\20|\3\2\2\2\22\u00a8\3\2\2\2\24\31\5\4"+
		"\3\2\25\26\t\2\2\2\26\30\5\4\3\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3\2"+
		"\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\31\3\2\2\2\34!\5\6\4\2\35\36\t\3\2"+
		"\2\36 \5\6\4\2\37\35\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\5\3\2"+
		"\2\2#!\3\2\2\2$%\7\n\2\2%&\5\2\2\2&\'\7\13\2\2\'+\3\2\2\2(+\7\5\2\2)+"+
		"\7\4\2\2*$\3\2\2\2*(\3\2\2\2*)\3\2\2\2+\7\3\2\2\2,\61\5\n\6\2-.\t\4\2"+
		"\2.\60\5\n\6\2/-\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\t"+
		"\3\2\2\2\63\61\3\2\2\2\64\65\7\31\2\2\65<\5\b\5\2\66<\5\f\7\2\678\7\n"+
		"\2\289\5\b\5\29:\7\13\2\2:<\3\2\2\2;\64\3\2\2\2;\66\3\2\2\2;\67\3\2\2"+
		"\2<\13\3\2\2\2=>\5\2\2\2>?\t\5\2\2?@\5\2\2\2@\r\3\2\2\2AL\7\5\2\2BC\7"+
		"\n\2\2CH\7\5\2\2DE\7\b\2\2EG\7\5\2\2FD\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3"+
		"\2\2\2IK\3\2\2\2JH\3\2\2\2KM\7\13\2\2LB\3\2\2\2LM\3\2\2\2M\17\3\2\2\2"+
		"NO\b\t\1\2O}\5\16\b\2PQ\7\35\2\2QR\5\b\5\2RS\7\36\2\2ST\5\20\t\13T}\3"+
		"\2\2\2U`\7\5\2\2VW\7\n\2\2W\\\7\5\2\2XY\7\b\2\2Y[\7\5\2\2ZX\3\2\2\2[^"+
		"\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_a\7\13\2\2`V\3\2\2"+
		"\2`a\3\2\2\2ab\3\2\2\2bc\7\7\2\2c}\5\20\t\bde\7\t\2\2eq\7\5\2\2fg\7\n"+
		"\2\2gl\5\2\2\2hi\7\b\2\2ik\5\2\2\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2"+
		"\2\2mo\3\2\2\2nl\3\2\2\2op\7\13\2\2pr\3\2\2\2qf\3\2\2\2qr\3\2\2\2rs\3"+
		"\2\2\2st\7\7\2\2t}\5\20\t\7uv\7 \2\2vw\7\7\2\2w}\5\20\t\6xy\7\n\2\2yz"+
		"\5\20\t\2z{\7\13\2\2{}\3\2\2\2|N\3\2\2\2|P\3\2\2\2|U\3\2\2\2|d\3\2\2\2"+
		"|u\3\2\2\2|x\3\2\2\2}\u00a5\3\2\2\2~\177\f\4\2\2\177\u0080\7\34\2\2\u0080"+
		"\u00a4\5\20\t\5\u0081\u0082\f\n\2\2\u0082\u0083\7\37\2\2\u0083\u0084\7"+
		"\f\2\2\u0084\u0089\7\5\2\2\u0085\u0086\7\b\2\2\u0086\u0088\7\5\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u00a4\7\r\2\2\u008d"+
		"\u008e\f\t\2\2\u008e\u008f\7\16\2\2\u008f\u0090\7\5\2\2\u0090\u0091\7"+
		"\23\2\2\u0091\u0098\7\5\2\2\u0092\u0093\7\b\2\2\u0093\u0094\7\5\2\2\u0094"+
		"\u0095\7\23\2\2\u0095\u0097\7\5\2\2\u0096\u0092\3\2\2\2\u0097\u009a\3"+
		"\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\u00a4\7\16\2\2\u009c\u009f\f\5\2\2\u009d\u009e\7"+
		"\20\2\2\u009e\u00a0\5\20\t\2\u009f\u009d\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3~\3\2\2\2"+
		"\u00a3\u0081\3\2\2\2\u00a3\u008d\3\2\2\2\u00a3\u009c\3\2\2\2\u00a4\u00a7"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\21\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8\u00a9\5\16\b\2\u00a9\u00aa\7\6\2\2\u00aa\u00ab\5"+
		"\20\t\2\u00ab\23\3\2\2\2\23\31!*\61;HL\\`lq|\u0089\u0098\u00a1\u00a3\u00a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}