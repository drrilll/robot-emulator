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

public class RepeatConstantTimes extends Repeat {
	
	private Block block;
	private int tab;

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public RepeatConstantTimes(Expression exp, Method method) {
		super(exp,method);
	}
	
	public void addStatement(Statement s){
		block.addStatement(s);
	}
	
	@Override
	public void setTab(int tab) {
		this.tab = tab;
	}

	@Override
	public int getTab() {
		return tab;
	}


	@Override
	public int execute(MethodInstance method) throws Exception {
		long number = getExp().evaluateToValue(method);
		Debug.debug("repeating "+ number+" times");
		for (int i = 0; i < number; i++){
			Debug.debug("**********************************"+i);
			for (Statement s: block.getStatements()){
				s.execute(method);
			}
		}
		return 1;
	}

}
