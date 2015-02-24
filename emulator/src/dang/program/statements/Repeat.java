/**
 * 
 */
package dang.program.statements;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Block;
import dang.program.Debug;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

/**
 * @author darrylhill
 * The generic repeat statement, ie, repeat indefinitely
 * Due to poor planning and laziness, this will also be the
 * class the implements the while at the end of the block
 */
public class Repeat extends Statement {
	
	private Block block;
	private Expression exp;
	private int tab;
	private boolean whileStatement= false;
	private boolean untilStatement = false;

	public boolean isUntilStatement() {
		return untilStatement;
	}

	public void setUntilStatement(boolean untilStatement) {
		this.untilStatement = untilStatement;
	}

	public boolean isWhileStatement() {
		return whileStatement;
	}

	public void setWhileStatement(boolean whileStatement) {
		this.whileStatement = whileStatement;
	}

	@Override
	public void setTab(int tab) {
		this.tab = tab;
	}

	@Override
	public int getTab() {
		return tab;
	}


	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	/**
	 * You can pass in a block, or add to it statement by statement
	 */
	
	public Repeat(Expression exp, Method callingMethod){
		super(callingMethod);
		this.exp = exp;
	}
	
	public Repeat(Method callingMethod){
		super(callingMethod);
	}
	
	public void addStatement(Statement s){
		block.addStatement(s);
	}
	
	public String toString(){
		if (whileStatement){
			return "repeat block while "+exp;
		}else if(untilStatement){
			return "repeat block until "+exp;
		}else if(exp == null){
			return "repeat";
		}else{
			return "repeat expression "+exp;
		}
	}
	

	/* (non-Javadoc)
	 * @see dang.program.statements.Statement#execute(dang.program.Method)
	 */
	/**
	 * Handle three different cases, the plain repeat, the repeat a
	 * number of times based on constant, variable, or expression, and a
	 * repeat, block, while (expression) statement.
	 * Edit: I beleive that last one is an error on my part, and probably
	 * unused. Possibly the last two cases
	 */
	@Override
	public int execute(MethodInstance method) throws Exception {
		if (whileStatement){
			Debug.debug("executing while expression");
			do {
				getBlock().execute(method);
			}while (getExp().evaluate(method)!=Expression.FALSE);
			return 1;
		}else if (untilStatement){
			Debug.debug("executing until expression");
			do {
				getBlock().execute(method);
			}while (getExp().evaluate(method)==Expression.FALSE);
			return 1;
		}else{
			int ret = 0;
			if (exp == null){
				while (true){
					block.execute(method);
				}
			}else{
				int i = exp.evaluateToValue(method);
				for (int j = 0; j< i; j++){
					ret = block.execute(method);
				}
			}
			return ret;
		}
	}

}
