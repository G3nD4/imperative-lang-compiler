// Generated from MyLang.g4 by ANTLR 4.10.1
package main;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MyLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VAR=2, TYPE=3, ROUTINE=4, IS=5, IN=6, END=7, WHILE=8, LOOP=9, 
		FOR=10, IF=11, ELSE=12, THEN=13, REVERSE=14, TRUE=15, FALSE=16, INTEGER=17, 
		REAL=18, BOOLEAN=19, ASSIGN=20, COLON=21, PLUS=22, MINUS=23, MULTIPLY=24, 
		DIVIDE=25, LPAREN=26, RPAREN=27, LBRACKET=28, RBRACKET=29, DOT=30, COMMA=31, 
		NEWLINE=32, INTEGER_LITERAL=33, REAL_LITERAL=34, IDENTIFIER=35, WS=36, 
		LINE_COMMENT=37;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_variableDeclaration = 2, 
		RULE_typeDeclaration = 3, RULE_type = 4, RULE_routineDeclaration = 5, 
		RULE_parameters = 6, RULE_parameter = 7, RULE_body = 8, RULE_statement = 9, 
		RULE_assignment = 10, RULE_routineCall = 11, RULE_whileLoop = 12, RULE_forLoop = 13, 
		RULE_range = 14, RULE_ifStatement = 15, RULE_expression = 16, RULE_modifiablePrimary = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declaration", "variableDeclaration", "typeDeclaration", "type", 
			"routineDeclaration", "parameters", "parameter", "body", "statement", 
			"assignment", "routineCall", "whileLoop", "forLoop", "range", "ifStatement", 
			"expression", "modifiablePrimary"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'..'", "'var'", "'type'", "'routine'", "'is'", "'in'", "'end'", 
			"'while'", "'loop'", "'for'", "'if'", "'else'", "'then'", "'reverse'", 
			"'true'", "'false'", "'integer'", "'real'", "'boolean'", "':='", "':'", 
			"'+'", "'-'", "'*'", "'/'", "'('", "')'", "'['", "']'", "'.'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "VAR", "TYPE", "ROUTINE", "IS", "IN", "END", "WHILE", "LOOP", 
			"FOR", "IF", "ELSE", "THEN", "REVERSE", "TRUE", "FALSE", "INTEGER", "REAL", 
			"BOOLEAN", "ASSIGN", "COLON", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
			"LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "DOT", "COMMA", "NEWLINE", 
			"INTEGER_LITERAL", "REAL_LITERAL", "IDENTIFIER", "WS", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "MyLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MyLangParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<RoutineDeclarationContext> routineDeclaration() {
			return getRuleContexts(RoutineDeclarationContext.class);
		}
		public RoutineDeclarationContext routineDeclaration(int i) {
			return getRuleContext(RoutineDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << TYPE) | (1L << ROUTINE))) != 0)) {
				{
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
				case TYPE:
					{
					setState(36);
					declaration();
					}
					break;
				case ROUTINE:
					{
					setState(37);
					routineDeclaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			match(EOF);
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

	public static class DeclarationContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				variableDeclaration();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				typeDeclaration();
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

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(MyLangParser.VAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(MyLangParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IS() { return getToken(MyLangParser.IS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variableDeclaration);
		int _la;
		try {
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				match(VAR);
				setState(50);
				match(IDENTIFIER);
				setState(51);
				match(COLON);
				setState(52);
				type();
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IS) {
					{
					setState(53);
					match(IS);
					setState(54);
					expression(0);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(VAR);
				setState(58);
				match(IDENTIFIER);
				setState(59);
				match(IS);
				setState(60);
				expression(0);
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

	public static class TypeDeclarationContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(MyLangParser.TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode IS() { return getToken(MyLangParser.IS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitTypeDeclaration(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(TYPE);
			setState(64);
			match(IDENTIFIER);
			setState(65);
			match(IS);
			setState(66);
			type();
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MyLangParser.INTEGER, 0); }
		public TerminalNode REAL() { return getToken(MyLangParser.REAL, 0); }
		public TerminalNode BOOLEAN() { return getToken(MyLangParser.BOOLEAN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << REAL) | (1L << BOOLEAN) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class RoutineDeclarationContext extends ParserRuleContext {
		public TerminalNode ROUTINE() { return getToken(MyLangParser.ROUTINE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(MyLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyLangParser.RPAREN, 0); }
		public TerminalNode IS() { return getToken(MyLangParser.IS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode END() { return getToken(MyLangParser.END, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MyLangParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public RoutineDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routineDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterRoutineDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitRoutineDeclaration(this);
		}
	}

	public final RoutineDeclarationContext routineDeclaration() throws RecognitionException {
		RoutineDeclarationContext _localctx = new RoutineDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_routineDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(ROUTINE);
			setState(71);
			match(IDENTIFIER);
			setState(72);
			match(LPAREN);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(73);
				parameters();
				}
			}

			setState(76);
			match(RPAREN);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(77);
				match(COLON);
				setState(78);
				type();
				}
			}

			setState(81);
			match(IS);
			setState(82);
			body();
			setState(83);
			match(END);
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

	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyLangParser.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitParameters(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			parameter();
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(86);
				match(COMMA);
				setState(87);
				parameter();
				}
				}
				setState(92);
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

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(MyLangParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(IDENTIFIER);
			setState(94);
			match(COLON);
			setState(95);
			type();
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

	public static class BodyContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VAR) | (1L << TYPE) | (1L << WHILE) | (1L << FOR) | (1L << IF) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(99);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
				case TYPE:
					{
					setState(97);
					declaration();
					}
					break;
				case WHILE:
				case FOR:
				case IF:
				case IDENTIFIER:
					{
					setState(98);
					statement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(103);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public RoutineCallContext routineCall() {
			return getRuleContext(RoutineCallContext.class,0);
		}
		public WhileLoopContext whileLoop() {
			return getRuleContext(WhileLoopContext.class,0);
		}
		public ForLoopContext forLoop() {
			return getRuleContext(ForLoopContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				routineCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				whileLoop();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				forLoop();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(108);
				ifStatement();
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

	public static class AssignmentContext extends ParserRuleContext {
		public ModifiablePrimaryContext modifiablePrimary() {
			return getRuleContext(ModifiablePrimaryContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MyLangParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			modifiablePrimary();
			setState(112);
			match(ASSIGN);
			setState(113);
			expression(0);
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

	public static class RoutineCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(MyLangParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MyLangParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MyLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyLangParser.COMMA, i);
		}
		public RoutineCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routineCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterRoutineCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitRoutineCall(this);
		}
	}

	public final RoutineCallContext routineCall() throws RecognitionException {
		RoutineCallContext _localctx = new RoutineCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_routineCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(IDENTIFIER);
			setState(116);
			match(LPAREN);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAREN) | (1L << INTEGER_LITERAL) | (1L << REAL_LITERAL) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(117);
				expression(0);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(118);
					match(COMMA);
					setState(119);
					expression(0);
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(127);
			match(RPAREN);
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

	public static class WhileLoopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MyLangParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LOOP() { return getToken(MyLangParser.LOOP, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode END() { return getToken(MyLangParser.END, 0); }
		public WhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitWhileLoop(this);
		}
	}

	public final WhileLoopContext whileLoop() throws RecognitionException {
		WhileLoopContext _localctx = new WhileLoopContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(WHILE);
			setState(130);
			expression(0);
			setState(131);
			match(LOOP);
			setState(132);
			body();
			setState(133);
			match(END);
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

	public static class ForLoopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MyLangParser.FOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MyLangParser.IDENTIFIER, 0); }
		public TerminalNode IN() { return getToken(MyLangParser.IN, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode LOOP() { return getToken(MyLangParser.LOOP, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode END() { return getToken(MyLangParser.END, 0); }
		public TerminalNode REVERSE() { return getToken(MyLangParser.REVERSE, 0); }
		public ForLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitForLoop(this);
		}
	}

	public final ForLoopContext forLoop() throws RecognitionException {
		ForLoopContext _localctx = new ForLoopContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forLoop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(FOR);
			setState(136);
			match(IDENTIFIER);
			setState(137);
			match(IN);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REVERSE) {
				{
				setState(138);
				match(REVERSE);
				}
			}

			setState(141);
			range();
			setState(142);
			match(LOOP);
			setState(143);
			body();
			setState(144);
			match(END);
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

	public static class RangeContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			expression(0);
			setState(147);
			match(T__0);
			setState(148);
			expression(0);
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

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MyLangParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(MyLangParser.THEN, 0); }
		public List<BodyContext> body() {
			return getRuleContexts(BodyContext.class);
		}
		public BodyContext body(int i) {
			return getRuleContext(BodyContext.class,i);
		}
		public TerminalNode END() { return getToken(MyLangParser.END, 0); }
		public TerminalNode ELSE() { return getToken(MyLangParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(IF);
			setState(151);
			expression(0);
			setState(152);
			match(THEN);
			setState(153);
			body();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(154);
				match(ELSE);
				setState(155);
				body();
				}
			}

			setState(158);
			match(END);
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(MyLangParser.INTEGER_LITERAL, 0); }
		public TerminalNode REAL_LITERAL() { return getToken(MyLangParser.REAL_LITERAL, 0); }
		public TerminalNode TRUE() { return getToken(MyLangParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MyLangParser.FALSE, 0); }
		public ModifiablePrimaryContext modifiablePrimary() {
			return getRuleContext(ModifiablePrimaryContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MyLangParser.LPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MyLangParser.RPAREN, 0); }
		public TerminalNode PLUS() { return getToken(MyLangParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MyLangParser.MINUS, 0); }
		public TerminalNode MULTIPLY() { return getToken(MyLangParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(MyLangParser.DIVIDE, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER_LITERAL:
				{
				setState(161);
				match(INTEGER_LITERAL);
				}
				break;
			case REAL_LITERAL:
				{
				setState(162);
				match(REAL_LITERAL);
				}
				break;
			case TRUE:
				{
				setState(163);
				match(TRUE);
				}
				break;
			case FALSE:
				{
				setState(164);
				match(FALSE);
				}
				break;
			case IDENTIFIER:
				{
				setState(165);
				modifiablePrimary();
				}
				break;
			case LPAREN:
				{
				setState(166);
				match(LPAREN);
				setState(167);
				expression(0);
				setState(168);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(184);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(173);
						match(PLUS);
						setState(174);
						expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(176);
						match(MINUS);
						setState(177);
						expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(179);
						match(MULTIPLY);
						setState(180);
						expression(9);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(182);
						match(DIVIDE);
						setState(183);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class ModifiablePrimaryContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(MyLangParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MyLangParser.IDENTIFIER, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MyLangParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MyLangParser.DOT, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(MyLangParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MyLangParser.LBRACKET, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MyLangParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MyLangParser.RBRACKET, i);
		}
		public ModifiablePrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifiablePrimary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).enterModifiablePrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyLangListener ) ((MyLangListener)listener).exitModifiablePrimary(this);
		}
	}

	public final ModifiablePrimaryContext modifiablePrimary() throws RecognitionException {
		ModifiablePrimaryContext _localctx = new ModifiablePrimaryContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_modifiablePrimary);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(IDENTIFIER);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(196);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case DOT:
						{
						setState(190);
						match(DOT);
						setState(191);
						match(IDENTIFIER);
						}
						break;
					case LBRACKET:
						{
						setState(192);
						match(LBRACKET);
						setState(193);
						expression(0);
						setState(194);
						match(RBRACKET);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 16:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u00ca\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0003\u00010\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00028\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002>\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"K\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006Y\b\u0006\n\u0006\f\u0006\\\t\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005\bd\b\b\n\b\f\bg\t"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tn\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0005\u000by\b\u000b\n\u000b\f\u000b|\t\u000b\u0003\u000b~\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u008c\b\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u009d\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u00ab\b\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00b9\b\u0010\n"+
		"\u0010\f\u0010\u00bc\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00c5\b\u0011\n"+
		"\u0011\f\u0011\u00c8\t\u0011\u0001\u0011\u0000\u0001 \u0012\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"\u0000\u0001\u0002\u0000\u0011\u0013##\u00d4\u0000(\u0001\u0000\u0000"+
		"\u0000\u0002/\u0001\u0000\u0000\u0000\u0004=\u0001\u0000\u0000\u0000\u0006"+
		"?\u0001\u0000\u0000\u0000\bD\u0001\u0000\u0000\u0000\nF\u0001\u0000\u0000"+
		"\u0000\fU\u0001\u0000\u0000\u0000\u000e]\u0001\u0000\u0000\u0000\u0010"+
		"e\u0001\u0000\u0000\u0000\u0012m\u0001\u0000\u0000\u0000\u0014o\u0001"+
		"\u0000\u0000\u0000\u0016s\u0001\u0000\u0000\u0000\u0018\u0081\u0001\u0000"+
		"\u0000\u0000\u001a\u0087\u0001\u0000\u0000\u0000\u001c\u0092\u0001\u0000"+
		"\u0000\u0000\u001e\u0096\u0001\u0000\u0000\u0000 \u00aa\u0001\u0000\u0000"+
		"\u0000\"\u00bd\u0001\u0000\u0000\u0000$\'\u0003\u0002\u0001\u0000%\'\u0003"+
		"\n\u0005\u0000&$\u0001\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000\'*"+
		"\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)+\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000+,\u0005\u0000"+
		"\u0000\u0001,\u0001\u0001\u0000\u0000\u0000-0\u0003\u0004\u0002\u0000"+
		".0\u0003\u0006\u0003\u0000/-\u0001\u0000\u0000\u0000/.\u0001\u0000\u0000"+
		"\u00000\u0003\u0001\u0000\u0000\u000012\u0005\u0002\u0000\u000023\u0005"+
		"#\u0000\u000034\u0005\u0015\u0000\u000047\u0003\b\u0004\u000056\u0005"+
		"\u0005\u0000\u000068\u0003 \u0010\u000075\u0001\u0000\u0000\u000078\u0001"+
		"\u0000\u0000\u00008>\u0001\u0000\u0000\u00009:\u0005\u0002\u0000\u0000"+
		":;\u0005#\u0000\u0000;<\u0005\u0005\u0000\u0000<>\u0003 \u0010\u0000="+
		"1\u0001\u0000\u0000\u0000=9\u0001\u0000\u0000\u0000>\u0005\u0001\u0000"+
		"\u0000\u0000?@\u0005\u0003\u0000\u0000@A\u0005#\u0000\u0000AB\u0005\u0005"+
		"\u0000\u0000BC\u0003\b\u0004\u0000C\u0007\u0001\u0000\u0000\u0000DE\u0007"+
		"\u0000\u0000\u0000E\t\u0001\u0000\u0000\u0000FG\u0005\u0004\u0000\u0000"+
		"GH\u0005#\u0000\u0000HJ\u0005\u001a\u0000\u0000IK\u0003\f\u0006\u0000"+
		"JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000"+
		"\u0000LO\u0005\u001b\u0000\u0000MN\u0005\u0015\u0000\u0000NP\u0003\b\u0004"+
		"\u0000OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QR\u0005\u0005\u0000\u0000RS\u0003\u0010\b\u0000ST\u0005\u0007"+
		"\u0000\u0000T\u000b\u0001\u0000\u0000\u0000UZ\u0003\u000e\u0007\u0000"+
		"VW\u0005\u001f\u0000\u0000WY\u0003\u000e\u0007\u0000XV\u0001\u0000\u0000"+
		"\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[\r\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005"+
		"#\u0000\u0000^_\u0005\u0015\u0000\u0000_`\u0003\b\u0004\u0000`\u000f\u0001"+
		"\u0000\u0000\u0000ad\u0003\u0002\u0001\u0000bd\u0003\u0012\t\u0000ca\u0001"+
		"\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000"+
		"ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000f\u0011\u0001\u0000"+
		"\u0000\u0000ge\u0001\u0000\u0000\u0000hn\u0003\u0014\n\u0000in\u0003\u0016"+
		"\u000b\u0000jn\u0003\u0018\f\u0000kn\u0003\u001a\r\u0000ln\u0003\u001e"+
		"\u000f\u0000mh\u0001\u0000\u0000\u0000mi\u0001\u0000\u0000\u0000mj\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000ml\u0001\u0000\u0000\u0000"+
		"n\u0013\u0001\u0000\u0000\u0000op\u0003\"\u0011\u0000pq\u0005\u0014\u0000"+
		"\u0000qr\u0003 \u0010\u0000r\u0015\u0001\u0000\u0000\u0000st\u0005#\u0000"+
		"\u0000t}\u0005\u001a\u0000\u0000uz\u0003 \u0010\u0000vw\u0005\u001f\u0000"+
		"\u0000wy\u0003 \u0010\u0000xv\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000"+
		"\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{~\u0001\u0000"+
		"\u0000\u0000|z\u0001\u0000\u0000\u0000}u\u0001\u0000\u0000\u0000}~\u0001"+
		"\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u001b"+
		"\u0000\u0000\u0080\u0017\u0001\u0000\u0000\u0000\u0081\u0082\u0005\b\u0000"+
		"\u0000\u0082\u0083\u0003 \u0010\u0000\u0083\u0084\u0005\t\u0000\u0000"+
		"\u0084\u0085\u0003\u0010\b\u0000\u0085\u0086\u0005\u0007\u0000\u0000\u0086"+
		"\u0019\u0001\u0000\u0000\u0000\u0087\u0088\u0005\n\u0000\u0000\u0088\u0089"+
		"\u0005#\u0000\u0000\u0089\u008b\u0005\u0006\u0000\u0000\u008a\u008c\u0005"+
		"\u000e\u0000\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008b\u008c\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0003"+
		"\u001c\u000e\u0000\u008e\u008f\u0005\t\u0000\u0000\u008f\u0090\u0003\u0010"+
		"\b\u0000\u0090\u0091\u0005\u0007\u0000\u0000\u0091\u001b\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0003 \u0010\u0000\u0093\u0094\u0005\u0001\u0000\u0000"+
		"\u0094\u0095\u0003 \u0010\u0000\u0095\u001d\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0005\u000b\u0000\u0000\u0097\u0098\u0003 \u0010\u0000\u0098\u0099"+
		"\u0005\r\u0000\u0000\u0099\u009c\u0003\u0010\b\u0000\u009a\u009b\u0005"+
		"\f\u0000\u0000\u009b\u009d\u0003\u0010\b\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0005\u0007\u0000\u0000\u009f\u001f\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0006\u0010\uffff\uffff\u0000\u00a1\u00ab\u0005"+
		"!\u0000\u0000\u00a2\u00ab\u0005\"\u0000\u0000\u00a3\u00ab\u0005\u000f"+
		"\u0000\u0000\u00a4\u00ab\u0005\u0010\u0000\u0000\u00a5\u00ab\u0003\"\u0011"+
		"\u0000\u00a6\u00a7\u0005\u001a\u0000\u0000\u00a7\u00a8\u0003 \u0010\u0000"+
		"\u00a8\u00a9\u0005\u001b\u0000\u0000\u00a9\u00ab\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a0\u0001\u0000\u0000\u0000\u00aa\u00a2\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a3\u0001\u0000\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a5\u0001\u0000\u0000\u0000\u00aa\u00a6\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ba\u0001\u0000\u0000\u0000\u00ac\u00ad\n\n\u0000\u0000\u00ad"+
		"\u00ae\u0005\u0016\u0000\u0000\u00ae\u00b9\u0003 \u0010\u000b\u00af\u00b0"+
		"\n\t\u0000\u0000\u00b0\u00b1\u0005\u0017\u0000\u0000\u00b1\u00b9\u0003"+
		" \u0010\n\u00b2\u00b3\n\b\u0000\u0000\u00b3\u00b4\u0005\u0018\u0000\u0000"+
		"\u00b4\u00b9\u0003 \u0010\t\u00b5\u00b6\n\u0007\u0000\u0000\u00b6\u00b7"+
		"\u0005\u0019\u0000\u0000\u00b7\u00b9\u0003 \u0010\b\u00b8\u00ac\u0001"+
		"\u0000\u0000\u0000\u00b8\u00af\u0001\u0000\u0000\u0000\u00b8\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b5\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001"+
		"\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001"+
		"\u0000\u0000\u0000\u00bb!\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bd\u00c6\u0005#\u0000\u0000\u00be\u00bf\u0005\u001e\u0000"+
		"\u0000\u00bf\u00c5\u0005#\u0000\u0000\u00c0\u00c1\u0005\u001c\u0000\u0000"+
		"\u00c1\u00c2\u0003 \u0010\u0000\u00c2\u00c3\u0005\u001d\u0000\u0000\u00c3"+
		"\u00c5\u0001\u0000\u0000\u0000\u00c4\u00be\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7"+
		"#\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u0014&("+
		"/7=JOZcemz}\u008b\u009c\u00aa\u00b8\u00ba\u00c4\u00c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}