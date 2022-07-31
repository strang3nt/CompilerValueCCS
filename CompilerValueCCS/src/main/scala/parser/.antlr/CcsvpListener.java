// Generated from Ccsvp.g4 by ANTLR 4.10.1
package main.scala.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CcsvpParser}.
 */
public interface CcsvpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#summation}.
	 * @param ctx the parse tree
	 */
	void enterSummation(CcsvpParser.SummationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#summation}.
	 * @param ctx the parse tree
	 */
	void exitSummation(CcsvpParser.SummationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(CcsvpParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(CcsvpParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CcsvpParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CcsvpParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(CcsvpParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(CcsvpParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(CcsvpParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(CcsvpParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#logicop}.
	 * @param ctx the parse tree
	 */
	void enterLogicop(CcsvpParser.LogicopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#logicop}.
	 * @param ctx the parse tree
	 */
	void exitLogicop(CcsvpParser.LogicopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#boolop}.
	 * @param ctx the parse tree
	 */
	void enterBoolop(CcsvpParser.BoolopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#boolop}.
	 * @param ctx the parse tree
	 */
	void exitBoolop(CcsvpParser.BoolopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#boolbinop}.
	 * @param ctx the parse tree
	 */
	void enterBoolbinop(CcsvpParser.BoolbinopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#boolbinop}.
	 * @param ctx the parse tree
	 */
	void exitBoolbinop(CcsvpParser.BoolbinopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#bterm}.
	 * @param ctx the parse tree
	 */
	void enterBterm(CcsvpParser.BtermContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#bterm}.
	 * @param ctx the parse tree
	 */
	void exitBterm(CcsvpParser.BtermContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#exprbinop}.
	 * @param ctx the parse tree
	 */
	void enterExprbinop(CcsvpParser.ExprbinopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#exprbinop}.
	 * @param ctx the parse tree
	 */
	void exitExprbinop(CcsvpParser.ExprbinopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(CcsvpParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(CcsvpParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Tauch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterTauch(CcsvpParser.TauchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Tauch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitTauch(CcsvpParser.TauchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Par}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterPar(CcsvpParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Par}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitPar(CcsvpParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Restriction}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterRestriction(CcsvpParser.RestrictionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Restriction}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitRestriction(CcsvpParser.RestrictionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Inputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterInputch(CcsvpParser.InputchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Inputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitInputch(CcsvpParser.InputchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(CcsvpParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(CcsvpParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Redirection}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterRedirection(CcsvpParser.RedirectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Redirection}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitRedirection(CcsvpParser.RedirectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Const}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterConst(CcsvpParser.ConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitConst(CcsvpParser.ConstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterSum(CcsvpParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitSum(CcsvpParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ifthen}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterIfthen(CcsvpParser.IfthenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ifthen}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitIfthen(CcsvpParser.IfthenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Outputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void enterOutputch(CcsvpParser.OutputchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Outputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 */
	void exitOutputch(CcsvpParser.OutputchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CcsvpParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CcsvpParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CcsvpParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CcsvpParser.ProgramContext ctx);
}