package dang.program;

import dang.exceptions.SpinError;

public interface BlockHolder {
	
	public void setBlock(Block block) throws SpinError;
	
	public Block getBlock() throws SpinError;

}