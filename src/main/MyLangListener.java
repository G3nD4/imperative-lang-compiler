package main;
// Generated from MyLang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyLangParser}.
 */
public interface MyLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MyLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MyLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MyLangParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MyLangParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(MyLangParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(MyLangParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(MyLangParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MyLangParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MyLangParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(MyLangParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(MyLangParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MyLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MyLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#routineDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#routineDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(MyLangParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(MyLangParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MyLangParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MyLangParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(MyLangParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(MyLangParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MyLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MyLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MyLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MyLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#routineCall}.
	 * @param ctx the parse tree
	 */
	void enterRoutineCall(MyLangParser.RoutineCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#routineCall}.
	 * @param ctx the parse tree
	 */
	void exitRoutineCall(MyLangParser.RoutineCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(MyLangParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(MyLangParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(MyLangParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(MyLangParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(MyLangParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(MyLangParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MyLangParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MyLangParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MyLangParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MyLangParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MyLangParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MyLangParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#separator}.
	 * @param ctx the parse tree
	 */
	void enterSeparator(MyLangParser.SeparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#separator}.
	 * @param ctx the parse tree
	 */
	void exitSeparator(MyLangParser.SeparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#sepOrNot}.
	 * @param ctx the parse tree
	 */
	void enterSepOrNot(MyLangParser.SepOrNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#sepOrNot}.
	 * @param ctx the parse tree
	 */
	void exitSepOrNot(MyLangParser.SepOrNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MyLangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MyLangParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(MyLangParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(MyLangParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(MyLangParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(MyLangParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MyLangParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MyLangParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(MyLangParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(MyLangParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(MyLangParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(MyLangParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(MyLangParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(MyLangParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MyLangParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MyLangParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MyLangParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MyLangParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#modifiablePrimary}.
	 * @param ctx the parse tree
	 */
	void enterModifiablePrimary(MyLangParser.ModifiablePrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#modifiablePrimary}.
	 * @param ctx the parse tree
	 */
	void exitModifiablePrimary(MyLangParser.ModifiablePrimaryContext ctx);
}