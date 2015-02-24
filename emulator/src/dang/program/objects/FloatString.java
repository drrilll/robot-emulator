package dang.program.objects;

import java.io.IOException;

import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Debug;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Method;
import dang.program.SpinString;
import dang.program.Variable;
import dang.program.expressions.Expression;


public class FloatString extends SpinObject {
	//FloatToString(a)
	//setprecision(<1-7>)
	//setseparatorchrs(thousands, thousanths)
	
	
	/*
	 * Looks like each string is probably 15 characters long. Well, each
	 * one is 15 characters long, there is no other way unless with dynamic 
	 * memory.
	 */
	
	public FloatString(Program program) throws Exception{
		super ("FloatString", program);
		addMethod(new FloatToString("floattostring",program,"a"));
	}
	
	private class FloatToString extends HardwareMethod{
		
		SpinString spinString;
		//one argument of a float value
		public FloatToString(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
			//we are taking shortcuts here, "hacking" if you will
			spinString = new SpinString("00","$$$$$$$$$$$$$$$", getMemory());
			//15 characters, doesn't matter what they are they are
			//to be overwritten. For debugging we make them recognisable,
			//so to easily identify memory corruption
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					/*
					 * fun stuff, I need to dynamically allocate a string here. I should
					 * be able to do that. But then what, it sticks around forever? How does
					 * a real program do it? Just keep it simple, make it a standard size.
					 * Well there we go, there is a setprecision method, from 1 to 7 significant
					 * digits. So each floattostring allocates 7 digits, plus a decimal for 8 bytes.
					 */
					int popStack = getMemory().getStackPointer();
					//this also advances the pointer
					float fl = Float.intBitsToFloat(parameters[0].evaluateToValue(getCallingInstance()));
					spinString.setStr(Float.toString(fl));
					if (spinString.getStr().length()>14){
						spinString.setStr(spinString.getStr().substring(0, 13));
					}
					spinString.putInMemory();
					//we pop the stack but it will still be there on return. Creates 
					//a temporary string
					getMemory().setStackPointer(popStack);
					return spinString;
				}
			};
		}
		
		private class SetPrecision extends HardwareMethod{
			public SetPrecision(String name, Program program,String...params ) throws SyntaxError{
				super(name, MethodType.PUB, params, null, program);
			}
			
			@Override 
			public MethodInstance getMethodInstance(MethodInstance method){
				return new MethodInstance(this, method){
					@Override
					public Variable execute(Expression[] parameters)
							throws Exception {
						return Variable.TRUE;
					}
				};
			}
		}

		/**
		 * This will cause memory to be reserved in the calling method
		 * for this string.
		 */
		public void setCallingMethod(Method callinMethod){
			//addExtraMemory(spinString.getSize());
			//addLocalString(spinString);
			/*
			 * Each call to this should instantiate a new FloattoString object.
			 */
		}
	}
	
}
