// Generated from C:/Users/masks/Desktop/yuntai/backhand/yuntai/yuntai-backend/src/main/java/com/K/government/sqlparser/test/SQL.g4 by ANTLR 4.13.2
package com.K.government.sqlparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T_INSERT=9, 
		T_INTO=10, T_OVERWRITE=11, T_TABLE=12, T_SELECT=13, T_FROM=14, T_JOIN=15, 
		T_LEFT=16, T_RIGHT=17, T_INNER=18, T_FULL=19, T_OUTER=20, T_ON=21, T_AND=22, 
		T_OR=23, T_WHERE=24, T_GROUP=25, T_BY=26, T_ORDER=27, T_DESC=28, T_ASC=29, 
		T_LIMIT=30, T_AS=31, T_CASE=32, T_WHEN=33, T_THEN=34, T_ELSE=35, T_END=36, 
		T_DISTINCT=37, T_LPAREN=38, T_RPAREN=39, T_DOT=40, T_COMMA=41, T_MULT=42, 
		T_NUMBER=43, T_STRING=44, T_IDENTIFIER=45, WS=46, COMMENT=47;
	public static final int
		RULE_statement = 0, RULE_insertStatement = 1, RULE_selectStatement = 2, 
		RULE_selectElements = 3, RULE_selectElement = 4, RULE_functionCall = 5, 
		RULE_caseStatement = 6, RULE_expression = 7, RULE_primaryExpression = 8, 
		RULE_idPath = 9, RULE_operator = 10, RULE_fromClause = 11, RULE_joinClause = 12, 
		RULE_onClause = 13, RULE_whereClause = 14, RULE_groupByClause = 15, RULE_orderByClause = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "insertStatement", "selectStatement", "selectElements", 
			"selectElement", "functionCall", "caseStatement", "expression", "primaryExpression", 
			"idPath", "operator", "fromClause", "joinClause", "onClause", "whereClause", 
			"groupByClause", "orderByClause"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'>'", "'<'", "'>='", "'<='", "'!='", "'<>'", null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "'('", "')'", "'.'", "','", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "T_INSERT", "T_INTO", 
			"T_OVERWRITE", "T_TABLE", "T_SELECT", "T_FROM", "T_JOIN", "T_LEFT", "T_RIGHT", 
			"T_INNER", "T_FULL", "T_OUTER", "T_ON", "T_AND", "T_OR", "T_WHERE", "T_GROUP", 
			"T_BY", "T_ORDER", "T_DESC", "T_ASC", "T_LIMIT", "T_AS", "T_CASE", "T_WHEN", 
			"T_THEN", "T_ELSE", "T_END", "T_DISTINCT", "T_LPAREN", "T_RPAREN", "T_DOT", 
			"T_COMMA", "T_MULT", "T_NUMBER", "T_STRING", "T_IDENTIFIER", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SQLParser.EOF, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_INSERT:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				insertStatement();
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(35);
					match(T__0);
					}
				}

				setState(38);
				match(EOF);
				}
				break;
			case T_SELECT:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				selectStatement();
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(41);
					match(T__0);
					}
				}

				setState(44);
				match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class InsertStatementContext extends ParserRuleContext {
		public TerminalNode T_INSERT() { return getToken(SQLParser.T_INSERT, 0); }
		public IdPathContext idPath() {
			return getRuleContext(IdPathContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode T_INTO() { return getToken(SQLParser.T_INTO, 0); }
		public TerminalNode T_OVERWRITE() { return getToken(SQLParser.T_OVERWRITE, 0); }
		public TerminalNode T_TABLE() { return getToken(SQLParser.T_TABLE, 0); }
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterInsertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitInsertStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitInsertStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(T_INSERT);
			setState(49);
			_la = _input.LA(1);
			if ( !(_la==T_INTO || _la==T_OVERWRITE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_TABLE) {
				{
				setState(50);
				match(T_TABLE);
				}
			}

			setState(53);
			idPath();
			setState(54);
			selectStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode T_SELECT() { return getToken(SQLParser.T_SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode T_FROM() { return getToken(SQLParser.T_FROM, 0); }
		public FromClauseContext fromClause() {
			return getRuleContext(FromClauseContext.class,0);
		}
		public TerminalNode T_WHERE() { return getToken(SQLParser.T_WHERE, 0); }
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode T_GROUP() { return getToken(SQLParser.T_GROUP, 0); }
		public List<TerminalNode> T_BY() { return getTokens(SQLParser.T_BY); }
		public TerminalNode T_BY(int i) {
			return getToken(SQLParser.T_BY, i);
		}
		public GroupByClauseContext groupByClause() {
			return getRuleContext(GroupByClauseContext.class,0);
		}
		public TerminalNode T_ORDER() { return getToken(SQLParser.T_ORDER, 0); }
		public OrderByClauseContext orderByClause() {
			return getRuleContext(OrderByClauseContext.class,0);
		}
		public TerminalNode T_LIMIT() { return getToken(SQLParser.T_LIMIT, 0); }
		public TerminalNode T_NUMBER() { return getToken(SQLParser.T_NUMBER, 0); }
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T_SELECT);
			setState(57);
			selectElements();
			setState(58);
			match(T_FROM);
			setState(59);
			fromClause();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_WHERE) {
				{
				setState(60);
				match(T_WHERE);
				setState(61);
				whereClause();
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_GROUP) {
				{
				setState(64);
				match(T_GROUP);
				setState(65);
				match(T_BY);
				setState(66);
				groupByClause();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_ORDER) {
				{
				setState(69);
				match(T_ORDER);
				setState(70);
				match(T_BY);
				setState(71);
				orderByClause();
				}
			}

			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_LIMIT) {
				{
				setState(74);
				match(T_LIMIT);
				setState(75);
				match(T_NUMBER);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementsContext extends ParserRuleContext {
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public List<TerminalNode> T_COMMA() { return getTokens(SQLParser.T_COMMA); }
		public TerminalNode T_COMMA(int i) {
			return getToken(SQLParser.T_COMMA, i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			selectElement();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T_COMMA) {
				{
				{
				setState(79);
				match(T_COMMA);
				setState(80);
				selectElement();
				}
				}
				setState(85);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementContext extends ParserRuleContext {
		public TerminalNode T_MULT() { return getToken(SQLParser.T_MULT, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public CaseStatementContext caseStatement() {
			return getRuleContext(CaseStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode T_IDENTIFIER() { return getToken(SQLParser.T_IDENTIFIER, 0); }
		public TerminalNode T_AS() { return getToken(SQLParser.T_AS, 0); }
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitSelectElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_selectElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(86);
				match(T_MULT);
				}
				break;
			case 2:
				{
				setState(87);
				functionCall();
				}
				break;
			case 3:
				{
				setState(88);
				caseStatement();
				}
				break;
			case 4:
				{
				setState(89);
				expression();
				}
				break;
			}
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_AS || _la==T_IDENTIFIER) {
				{
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T_AS) {
					{
					setState(92);
					match(T_AS);
					}
				}

				setState(95);
				match(T_IDENTIFIER);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode T_IDENTIFIER() { return getToken(SQLParser.T_IDENTIFIER, 0); }
		public TerminalNode T_LPAREN() { return getToken(SQLParser.T_LPAREN, 0); }
		public TerminalNode T_RPAREN() { return getToken(SQLParser.T_RPAREN, 0); }
		public TerminalNode T_DISTINCT() { return getToken(SQLParser.T_DISTINCT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T_IDENTIFIER);
			setState(99);
			match(T_LPAREN);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_DISTINCT) {
				{
				setState(100);
				match(T_DISTINCT);
				}
			}

			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 65974992633856L) != 0)) {
				{
				setState(103);
				selectElements();
				}
			}

			setState(106);
			match(T_RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CaseStatementContext extends ParserRuleContext {
		public TerminalNode T_CASE() { return getToken(SQLParser.T_CASE, 0); }
		public TerminalNode T_END() { return getToken(SQLParser.T_END, 0); }
		public List<TerminalNode> T_WHEN() { return getTokens(SQLParser.T_WHEN); }
		public TerminalNode T_WHEN(int i) {
			return getToken(SQLParser.T_WHEN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> T_THEN() { return getTokens(SQLParser.T_THEN); }
		public TerminalNode T_THEN(int i) {
			return getToken(SQLParser.T_THEN, i);
		}
		public TerminalNode T_ELSE() { return getToken(SQLParser.T_ELSE, 0); }
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterCaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitCaseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T_CASE);
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109);
				match(T_WHEN);
				setState(110);
				expression();
				setState(111);
				match(T_THEN);
				setState(112);
				expression();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T_WHEN );
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_ELSE) {
				{
				setState(118);
				match(T_ELSE);
				setState(119);
				expression();
				}
			}

			setState(122);
			match(T_END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<PrimaryExpressionContext> primaryExpression() {
			return getRuleContexts(PrimaryExpressionContext.class);
		}
		public PrimaryExpressionContext primaryExpression(int i) {
			return getRuleContext(PrimaryExpressionContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			primaryExpression();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 508L) != 0)) {
				{
				{
				setState(125);
				operator();
				setState(126);
				primaryExpression();
				}
				}
				setState(132);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ParserRuleContext {
		public IdPathContext idPath() {
			return getRuleContext(IdPathContext.class,0);
		}
		public TerminalNode T_NUMBER() { return getToken(SQLParser.T_NUMBER, 0); }
		public TerminalNode T_STRING() { return getToken(SQLParser.T_STRING, 0); }
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primaryExpression);
		try {
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				idPath();
				}
				break;
			case T_NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(T_NUMBER);
				}
				break;
			case T_STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				match(T_STRING);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdPathContext extends ParserRuleContext {
		public List<TerminalNode> T_IDENTIFIER() { return getTokens(SQLParser.T_IDENTIFIER); }
		public TerminalNode T_IDENTIFIER(int i) {
			return getToken(SQLParser.T_IDENTIFIER, i);
		}
		public List<TerminalNode> T_DOT() { return getTokens(SQLParser.T_DOT); }
		public TerminalNode T_DOT(int i) {
			return getToken(SQLParser.T_DOT, i);
		}
		public IdPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterIdPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitIdPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitIdPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdPathContext idPath() throws RecognitionException {
		IdPathContext _localctx = new IdPathContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_idPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T_IDENTIFIER);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T_DOT) {
				{
				{
				setState(139);
				match(T_DOT);
				setState(140);
				match(T_IDENTIFIER);
				}
				}
				setState(145);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 508L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class FromClauseContext extends ParserRuleContext {
		public TerminalNode T_LPAREN() { return getToken(SQLParser.T_LPAREN, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode T_RPAREN() { return getToken(SQLParser.T_RPAREN, 0); }
		public TerminalNode T_IDENTIFIER() { return getToken(SQLParser.T_IDENTIFIER, 0); }
		public TerminalNode T_AS() { return getToken(SQLParser.T_AS, 0); }
		public IdPathContext idPath() {
			return getRuleContext(IdPathContext.class,0);
		}
		public List<JoinClauseContext> joinClause() {
			return getRuleContexts(JoinClauseContext.class);
		}
		public JoinClauseContext joinClause(int i) {
			return getRuleContext(JoinClauseContext.class,i);
		}
		public TerminalNode T_ON() { return getToken(SQLParser.T_ON, 0); }
		public OnClauseContext onClause() {
			return getRuleContext(OnClauseContext.class,0);
		}
		public FromClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterFromClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitFromClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitFromClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromClauseContext fromClause() throws RecognitionException {
		FromClauseContext _localctx = new FromClauseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fromClause);
		int _la;
		try {
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(T_LPAREN);
				setState(149);
				selectStatement();
				setState(150);
				match(T_RPAREN);
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T_AS || _la==T_IDENTIFIER) {
					{
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T_AS) {
						{
						setState(151);
						match(T_AS);
						}
					}

					setState(154);
					match(T_IDENTIFIER);
					}
				}

				}
				break;
			case T_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				idPath();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T_AS || _la==T_IDENTIFIER) {
					{
					setState(159);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T_AS) {
						{
						setState(158);
						match(T_AS);
						}
					}

					setState(161);
					match(T_IDENTIFIER);
					}
				}

				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2064384L) != 0)) {
					{
					{
					setState(164);
					joinClause();
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T_ON) {
					{
					setState(170);
					match(T_ON);
					setState(171);
					onClause();
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class JoinClauseContext extends ParserRuleContext {
		public TerminalNode T_JOIN() { return getToken(SQLParser.T_JOIN, 0); }
		public IdPathContext idPath() {
			return getRuleContext(IdPathContext.class,0);
		}
		public TerminalNode T_IDENTIFIER() { return getToken(SQLParser.T_IDENTIFIER, 0); }
		public TerminalNode T_ON() { return getToken(SQLParser.T_ON, 0); }
		public OnClauseContext onClause() {
			return getRuleContext(OnClauseContext.class,0);
		}
		public TerminalNode T_LEFT() { return getToken(SQLParser.T_LEFT, 0); }
		public TerminalNode T_RIGHT() { return getToken(SQLParser.T_RIGHT, 0); }
		public TerminalNode T_INNER() { return getToken(SQLParser.T_INNER, 0); }
		public TerminalNode T_FULL() { return getToken(SQLParser.T_FULL, 0); }
		public TerminalNode T_OUTER() { return getToken(SQLParser.T_OUTER, 0); }
		public TerminalNode T_AS() { return getToken(SQLParser.T_AS, 0); }
		public JoinClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterJoinClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitJoinClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitJoinClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinClauseContext joinClause() throws RecognitionException {
		JoinClauseContext _localctx = new JoinClauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_joinClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2031616L) != 0)) {
				{
				setState(176);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2031616L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(179);
			match(T_JOIN);
			setState(180);
			idPath();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_AS || _la==T_IDENTIFIER) {
				{
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T_AS) {
					{
					setState(181);
					match(T_AS);
					}
				}

				setState(184);
				match(T_IDENTIFIER);
				}
			}

			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(187);
				match(T_ON);
				setState(188);
				onClause();
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

	@SuppressWarnings("CheckReturnValue")
	public static class OnClauseContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> T_AND() { return getTokens(SQLParser.T_AND); }
		public TerminalNode T_AND(int i) {
			return getToken(SQLParser.T_AND, i);
		}
		public List<TerminalNode> T_OR() { return getTokens(SQLParser.T_OR); }
		public TerminalNode T_OR(int i) {
			return getToken(SQLParser.T_OR, i);
		}
		public OnClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterOnClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitOnClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitOnClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnClauseContext onClause() throws RecognitionException {
		OnClauseContext _localctx = new OnClauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_onClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			expression();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T_AND || _la==T_OR) {
				{
				setState(196);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T_AND:
					{
					setState(192);
					match(T_AND);
					setState(193);
					expression();
					}
					break;
				case T_OR:
					{
					setState(194);
					match(T_OR);
					setState(195);
					expression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(200);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> T_AND() { return getTokens(SQLParser.T_AND); }
		public TerminalNode T_AND(int i) {
			return getToken(SQLParser.T_AND, i);
		}
		public List<TerminalNode> T_OR() { return getTokens(SQLParser.T_OR); }
		public TerminalNode T_OR(int i) {
			return getToken(SQLParser.T_OR, i);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_whereClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			expression();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T_AND || _la==T_OR) {
				{
				setState(206);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T_AND:
					{
					setState(202);
					match(T_AND);
					setState(203);
					expression();
					}
					break;
				case T_OR:
					{
					setState(204);
					match(T_OR);
					setState(205);
					expression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(210);
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

	@SuppressWarnings("CheckReturnValue")
	public static class GroupByClauseContext extends ParserRuleContext {
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public GroupByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterGroupByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitGroupByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitGroupByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByClauseContext groupByClause() throws RecognitionException {
		GroupByClauseContext _localctx = new GroupByClauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_groupByClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			selectElements();
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

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByClauseContext extends ParserRuleContext {
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode T_DESC() { return getToken(SQLParser.T_DESC, 0); }
		public TerminalNode T_ASC() { return getToken(SQLParser.T_ASC, 0); }
		public OrderByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).enterOrderByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener) ((SQLListener)listener).exitOrderByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor) return ((SQLVisitor<? extends T>)visitor).visitOrderByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByClauseContext orderByClause() throws RecognitionException {
		OrderByClauseContext _localctx = new OrderByClauseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			selectElements();
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T_DESC || _la==T_ASC) {
				{
				setState(214);
				_la = _input.LA(1);
				if ( !(_la==T_DESC || _la==T_ASC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
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

	public static final String _serializedATN =
		"\u0004\u0001/\u00da\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0003\u0000%\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000+\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000/\b\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u00014\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002?\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"D\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002I\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002M\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003R\b\u0003\n\u0003\f\u0003U\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004[\b\u0004\u0001\u0004\u0003"+
		"\u0004^\b\u0004\u0001\u0004\u0003\u0004a\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005f\b\u0005\u0001\u0005\u0003\u0005i\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0004\u0006s\b\u0006\u000b\u0006\f\u0006t\u0001\u0006"+
		"\u0001\u0006\u0003\u0006y\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0081\b\u0007\n\u0007"+
		"\f\u0007\u0084\t\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u0089\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u008e\b\t\n\t\f\t\u0091\t\t\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0099\b\u000b"+
		"\u0001\u000b\u0003\u000b\u009c\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00a0\b\u000b\u0001\u000b\u0003\u000b\u00a3\b\u000b\u0001\u000b\u0005"+
		"\u000b\u00a6\b\u000b\n\u000b\f\u000b\u00a9\t\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u00ad\b\u000b\u0003\u000b\u00af\b\u000b\u0001\f\u0003\f\u00b2"+
		"\b\f\u0001\f\u0001\f\u0001\f\u0003\f\u00b7\b\f\u0001\f\u0003\f\u00ba\b"+
		"\f\u0001\f\u0001\f\u0003\f\u00be\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u00c5\b\r\n\r\f\r\u00c8\t\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u00cf\b\u000e\n\u000e\f\u000e\u00d2"+
		"\t\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u00d8"+
		"\b\u0010\u0001\u0010\u0000\u0000\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000\u0004\u0001\u0000"+
		"\n\u000b\u0001\u0000\u0002\b\u0001\u0000\u0010\u0014\u0001\u0000\u001c"+
		"\u001d\u00ee\u0000.\u0001\u0000\u0000\u0000\u00020\u0001\u0000\u0000\u0000"+
		"\u00048\u0001\u0000\u0000\u0000\u0006N\u0001\u0000\u0000\u0000\bZ\u0001"+
		"\u0000\u0000\u0000\nb\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000"+
		"\u000e|\u0001\u0000\u0000\u0000\u0010\u0088\u0001\u0000\u0000\u0000\u0012"+
		"\u008a\u0001\u0000\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000\u0016"+
		"\u00ae\u0001\u0000\u0000\u0000\u0018\u00b1\u0001\u0000\u0000\u0000\u001a"+
		"\u00bf\u0001\u0000\u0000\u0000\u001c\u00c9\u0001\u0000\u0000\u0000\u001e"+
		"\u00d3\u0001\u0000\u0000\u0000 \u00d5\u0001\u0000\u0000\u0000\"$\u0003"+
		"\u0002\u0001\u0000#%\u0005\u0001\u0000\u0000$#\u0001\u0000\u0000\u0000"+
		"$%\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0005\u0000\u0000"+
		"\u0001\'/\u0001\u0000\u0000\u0000(*\u0003\u0004\u0002\u0000)+\u0005\u0001"+
		"\u0000\u0000*)\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,-\u0005\u0000\u0000\u0001-/\u0001\u0000\u0000\u0000"+
		".\"\u0001\u0000\u0000\u0000.(\u0001\u0000\u0000\u0000/\u0001\u0001\u0000"+
		"\u0000\u000001\u0005\t\u0000\u000013\u0007\u0000\u0000\u000024\u0005\f"+
		"\u0000\u000032\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u000056\u0003\u0012\t\u000067\u0003\u0004\u0002\u00007\u0003"+
		"\u0001\u0000\u0000\u000089\u0005\r\u0000\u00009:\u0003\u0006\u0003\u0000"+
		":;\u0005\u000e\u0000\u0000;>\u0003\u0016\u000b\u0000<=\u0005\u0018\u0000"+
		"\u0000=?\u0003\u001c\u000e\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?C\u0001\u0000\u0000\u0000@A\u0005\u0019\u0000\u0000AB\u0005"+
		"\u001a\u0000\u0000BD\u0003\u001e\u000f\u0000C@\u0001\u0000\u0000\u0000"+
		"CD\u0001\u0000\u0000\u0000DH\u0001\u0000\u0000\u0000EF\u0005\u001b\u0000"+
		"\u0000FG\u0005\u001a\u0000\u0000GI\u0003 \u0010\u0000HE\u0001\u0000\u0000"+
		"\u0000HI\u0001\u0000\u0000\u0000IL\u0001\u0000\u0000\u0000JK\u0005\u001e"+
		"\u0000\u0000KM\u0005+\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000"+
		"\u0000\u0000M\u0005\u0001\u0000\u0000\u0000NS\u0003\b\u0004\u0000OP\u0005"+
		")\u0000\u0000PR\u0003\b\u0004\u0000QO\u0001\u0000\u0000\u0000RU\u0001"+
		"\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"T\u0007\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000V[\u0005*\u0000"+
		"\u0000W[\u0003\n\u0005\u0000X[\u0003\f\u0006\u0000Y[\u0003\u000e\u0007"+
		"\u0000ZV\u0001\u0000\u0000\u0000ZW\u0001\u0000\u0000\u0000ZX\u0001\u0000"+
		"\u0000\u0000ZY\u0001\u0000\u0000\u0000[`\u0001\u0000\u0000\u0000\\^\u0005"+
		"\u001f\u0000\u0000]\\\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^_\u0001\u0000\u0000\u0000_a\u0005-\u0000\u0000`]\u0001\u0000\u0000\u0000"+
		"`a\u0001\u0000\u0000\u0000a\t\u0001\u0000\u0000\u0000bc\u0005-\u0000\u0000"+
		"ce\u0005&\u0000\u0000df\u0005%\u0000\u0000ed\u0001\u0000\u0000\u0000e"+
		"f\u0001\u0000\u0000\u0000fh\u0001\u0000\u0000\u0000gi\u0003\u0006\u0003"+
		"\u0000hg\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000"+
		"\u0000\u0000jk\u0005\'\u0000\u0000k\u000b\u0001\u0000\u0000\u0000lr\u0005"+
		" \u0000\u0000mn\u0005!\u0000\u0000no\u0003\u000e\u0007\u0000op\u0005\""+
		"\u0000\u0000pq\u0003\u000e\u0007\u0000qs\u0001\u0000\u0000\u0000rm\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000"+
		"tu\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000vw\u0005#\u0000\u0000"+
		"wy\u0003\u000e\u0007\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000yz\u0001\u0000\u0000\u0000z{\u0005$\u0000\u0000{\r\u0001\u0000\u0000"+
		"\u0000|\u0082\u0003\u0010\b\u0000}~\u0003\u0014\n\u0000~\u007f\u0003\u0010"+
		"\b\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080}\u0001\u0000\u0000"+
		"\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u000f\u0001\u0000\u0000"+
		"\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0085\u0089\u0003\u0012\t\u0000"+
		"\u0086\u0089\u0005+\u0000\u0000\u0087\u0089\u0005,\u0000\u0000\u0088\u0085"+
		"\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0087"+
		"\u0001\u0000\u0000\u0000\u0089\u0011\u0001\u0000\u0000\u0000\u008a\u008f"+
		"\u0005-\u0000\u0000\u008b\u008c\u0005(\u0000\u0000\u008c\u008e\u0005-"+
		"\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000"+
		"\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000"+
		"\u0000\u0000\u0090\u0013\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0007\u0001\u0000\u0000\u0093\u0015\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0005&\u0000\u0000\u0095\u0096\u0003\u0004\u0002"+
		"\u0000\u0096\u009b\u0005\'\u0000\u0000\u0097\u0099\u0005\u001f\u0000\u0000"+
		"\u0098\u0097\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c\u0005-\u0000\u0000\u009b"+
		"\u0098\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u00af\u0001\u0000\u0000\u0000\u009d\u00a2\u0003\u0012\t\u0000\u009e\u00a0"+
		"\u0005\u001f\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a3"+
		"\u0005-\u0000\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a7\u0001\u0000\u0000\u0000\u00a4\u00a6\u0003"+
		"\u0018\f\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a8\u00ac\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0005\u0015\u0000\u0000\u00ab\u00ad\u0003\u001a"+
		"\r\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ad\u00af\u0001\u0000\u0000\u0000\u00ae\u0094\u0001\u0000\u0000"+
		"\u0000\u00ae\u009d\u0001\u0000\u0000\u0000\u00af\u0017\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b2\u0007\u0002\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b4\u0005\u000f\u0000\u0000\u00b4\u00b9\u0003\u0012\t\u0000"+
		"\u00b5\u00b7\u0005\u001f\u0000\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b8\u00ba\u0005-\u0000\u0000\u00b9\u00b6\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005\u0015\u0000\u0000\u00bc\u00be\u0003\u001a\r\u0000\u00bd\u00bb"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u0019"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c6\u0003\u000e\u0007\u0000\u00c0\u00c1"+
		"\u0005\u0016\u0000\u0000\u00c1\u00c5\u0003\u000e\u0007\u0000\u00c2\u00c3"+
		"\u0005\u0017\u0000\u0000\u00c3\u00c5\u0003\u000e\u0007\u0000\u00c4\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c5\u00c8"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c7\u001b\u0001\u0000\u0000\u0000\u00c8\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c9\u00d0\u0003\u000e\u0007\u0000\u00ca\u00cb"+
		"\u0005\u0016\u0000\u0000\u00cb\u00cf\u0003\u000e\u0007\u0000\u00cc\u00cd"+
		"\u0005\u0017\u0000\u0000\u00cd\u00cf\u0003\u000e\u0007\u0000\u00ce\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d2"+
		"\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d1\u001d\u0001\u0000\u0000\u0000\u00d2\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d3\u00d4\u0003\u0006\u0003\u0000\u00d4\u001f"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d7\u0003\u0006\u0003\u0000\u00d6\u00d8"+
		"\u0007\u0003\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8"+
		"\u0001\u0000\u0000\u0000\u00d8!\u0001\u0000\u0000\u0000#$*.3>CHLSZ]`e"+
		"htx\u0082\u0088\u008f\u0098\u009b\u009f\u00a2\u00a7\u00ac\u00ae\u00b1"+
		"\u00b6\u00b9\u00bd\u00c4\u00c6\u00ce\u00d0\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}