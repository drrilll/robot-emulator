package dang.program.statements;

import java.util.ArrayList;

import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.*;
import dang.program.expressions.Expression;
import dang.program.statements.*;

public class IfElse extends Statement {

	private Block elseBlock;
	private ArrayList<Block> ifBlock = new ArrayList<Block>();
	private ArrayList<Expression> exp = new ArrayList<Expression>();
	private int tab;
	
	public ArrayList<Block> getIfBlock() {
		return ifBlock;
	}

	public void setIfBlock(Block ifBlock) {
		this.ifBlock.add(ifBlock);
	}

	public Block getElseBlock() {
		return elseBlock;
	}

	public void setElseBlock(Block elseBlock) {
		this.elseBlock = elseBlock;
	}

	public ArrayList<Expression> getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp.add(exp);
	}
	
	public IfElse(Expression exp, Block ifBlock, Block elseBlock, Method method){
		super(method);
		setExp(exp);
		this.elseBlock = elseBlock;
		setIfBlock(ifBlock);
	}
	
	public IfElse(Expression exp, Block ifBlock, Method method){
		super(method);
		setExp(exp);
		setIfBlock(ifBlock);
	}
	
	public IfElse(Expression exp, Method method){
		super(method);
		setExp(exp);
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		for (int i = 0; i < exp.size(); i++){
			if (!exp.get(i).evaluate(method).equals(Expression.FALSE, method)){
				if (ifBlock.get(i)!=null){
					ifBlock.get(i).execute(method);
					return 1;
				}else{
					return 0;
				}
			}
		}
		if(elseBlock != null){
			//there is an else block, so execute it
			elseBlock.execute(method);
			return 1;			
		}else{
			return 0;
		}
	}

	/**
	 * Expressions are added before blocks, so as long as
	 * the expressions set is larger than the block set,
	 * we can add a new block
	 */
	@Override
	public void setBlock(Block block) throws CompilerError {
		if (exp.size()>ifBlock.size()){
			ifBlock.add(block);
		}else if (elseBlock == null){
			elseBlock = block;
		}else{
			throw new CompilerError("IfElse blocks are already set");
		}
	}
	
	@Override
	public Block getBlock() throws SpinError{
		//TODO this is potentially error prone
		throw new CompilerError("trying to grab a block from ifelse block collection");
		//return ifBlock.get(0);
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
	public String toString(){
		StringBuffer str = new StringBuffer("IfElse statement \n Expressions:\n");
		for (Expression e: exp){
			str.append(e+"\n");
		}
		return str.toString();
	}

}
