// Generated from Ccsvp.g4 by ANTLR 4.10.1
package main.scala.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CcsvpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CcsvpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#summation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSummation(CcsvpParser.SummationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#product}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduct(CcsvpParser.ProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CcsvpParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(CcsvpParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(CcsvpParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#logicop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicop(CcsvpParser.LogicopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#boolop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolop(CcsvpParser.BoolopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#boolbinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolbinop(CcsvpParser.BoolbinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#bterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBterm(CcsvpParser.BtermContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#exprbinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprbinop(CcsvpParser.ExprbinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(CcsvpParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Tauch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTauch(CcsvpParser.TauchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Par}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(CcsvpParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Restriction}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestriction(CcsvpParser.RestrictionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputch(CcsvpParser.InputchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(CcsvpParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Redirection}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedirection(CcsvpParser.RedirectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(CcsvpParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(CcsvpParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ifthen}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfthen(CcsvpParser.IfthenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Outputch}
	 * labeled alternative in {@link CcsvpParser#ccsvp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputch(CcsvpParser.OutputchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CcsvpParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CcsvpParser.ProgramContext ctx);
}