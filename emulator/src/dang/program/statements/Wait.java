package dang.program.statements;


import dang.exceptions.CompilerError;
import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Block;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class Wait extends Statement {

	Expression time;
	
	public Wait(Expression time, Method method){
		//TODO a suitable conversion is needed for this
		super(method);
		this.time = time;
	}
	@Override
	public int execute(MethodInstance method) throws Exception {
		//this number will (or should) include the cnt value. So you need to find
		// the difference, taking into account the wrap around.
		long start = getCallingMethod().getRobot().getCnt();
		int end = time.evaluateToValue(method);
		long dif = 0;
		if (end < start){
			dif = ((long)Math.pow(2,32) - start) + end;  //fingers crossed
		}else{
			dif = end - start;
		}
		dif = dif/100000; //convert SPIN cycles to milliseconds (roughly)
		try {
			Thread.sleep(dif);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	@Override
	public void setBlock(Block block) throws SpinError {
		//throw new CompilerError("wait statements don't have blocks");	
		
	}
	@Override
	public void setTab(int tab) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getTab() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Block getBlock() throws SpinError {
		//throw new CompilerError("wait statements don't have blocks");	
		return null;
	}
}
