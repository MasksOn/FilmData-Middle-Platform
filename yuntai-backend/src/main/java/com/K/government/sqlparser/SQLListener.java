// Generated from C:/Users/masks/Desktop/yuntai/backhand/yuntai/yuntai-backend/src/main/java/com/K/government/sqlparser/test/SQL.g4 by ANTLR 4.13.2
package com.K.government.sqlparser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLParser}.
 */
public interface SQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SQLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(SQLParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(SQLParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(SQLParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(SQLParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectElement(SQLParser.SelectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectElement(SQLParser.SelectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SQLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SQLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(SQLParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(SQLParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(SQLParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(SQLParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#idPath}.
	 * @param ctx the parse tree
	 */
	void enterIdPath(SQLParser.IdPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#idPath}.
	 * @param ctx the parse tree
	 */
	void exitIdPath(SQLParser.IdPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(SQLParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(SQLParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(SQLParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(SQLParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(SQLParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(SQLParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#onClause}.
	 * @param ctx the parse tree
	 */
	void enterOnClause(SQLParser.OnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#onClause}.
	 * @param ctx the parse tree
	 */
	void exitOnClause(SQLParser.OnClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(SQLParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(SQLParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(SQLParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(SQLParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(SQLParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(SQLParser.OrderByClauseContext ctx);
}