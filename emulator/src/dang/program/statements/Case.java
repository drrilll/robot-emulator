package dang.program.statements;

import java.util.ArrayList;

import dang.exceptions.CompilerError;
import dang.exceptions.SpinError;
import dang.program.AnonVariable;
import dang.program.Block;
import dang.program.CaseItem;
import dang.program.ListItem;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.expressions.Expression;

public class Case extends Statement {
	Expression exp;
	ArrayList<CaseItem> conditions;
	ArrayList<Block> blocks;
	
	public Case (Expression exp, Method method){
		super(method);
		this.exp = exp;
		conditions = new ArrayList<CaseItem>();
		blocks = new ArrayList<Block>();
	}
	
	public void addCase(CaseItem condition, Block block){
		conditions.add(condition);
		blocks.add(block);
	}

	@Override
	public int execute(MethodInstance method) throws Exception {
		
		int ret = 0;
		
		//SPIN case statement only executes first match
		for (int i = 0; i < conditions.size(); i ++){
			if (conditions.get(i).equals(exp, method)){
				ret = blocks.get(i).execute(method);
				break;
			}
		}
		return ret;	
	}

	@Override
	public void setBlock(Block block) throws SpinError {
		throw new CompilerError("Case statements don't use setBlock");

	}

	@Override
	public Block getBlock() throws SpinError {
		return blocks.get(0);

	}
	
	public ArrayList<Block> getBlocks(){
		return blocks;
	}

}
