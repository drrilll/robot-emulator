package dang.antlr.parser;

public class SpinNumber {
	int ival;
	float fval;
	public boolean isfloat = false;
	
	public SpinNumber(int value){
		ival = value;
	}
	
	public SpinNumber(float value){
		fval = value;
		isfloat = true;
	}

	
	public int getValue(){
		if (isfloat){
			return Float.floatToIntBits(fval);
		}
		return ival;
	}
}
