package dang.program.objects;

import java.io.IOException;
import java.nio.ByteBuffer;

import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class Float32Full extends SpinObject {

	boolean started = false;

	/**
	 * @param main
	 * @throws IOException
	 * @throws SpinError
	 */
	public Float32Full(Program main) throws Exception{
		super ("Float32Full", main);
		addMethod(new Start("start", main));
		addMethod(new Stop("stop", main));
		addMethod(new FAdd("fadd", main, "a","b"));
		addMethod(new FSub("fsub", main, "a","b"));
		addMethod(new FMul("fmul", main, "a","b"));
		addMethod(new FDiv("fdiv", main, "a","b"));
		addMethod(new FFloat("ffloat", main, "a"));
		addMethod(new FTrunc("ftrunc", main, "a"));
		addMethod(new FRound("fround", main, "a"));
		addMethod(new FSqr("fsqr", main, "a"));
		addMethod(new FCmp("fcmp", main, "a","b"));
		addMethod(new Sin("sin", main, "a"));
		addMethod(new Cos("cos", main, "a"));
		addMethod(new Tan("tan", main, "a"));
		addMethod(new Log("log", main, "a"));
		addMethod(new Log10("log10", main, "a"));
		addMethod(new Exp("exp", main, "a"));
		addMethod(new Exp10("exp10", main, "a"));
		addMethod(new Pow("pow", main, "a","b"));
		addMethod(new Frac("frac", main, "a"));
		addMethod(new FNeg("fneg", main, "a"));
		addMethod(new FAbs("fabs", main, "a"));
		addMethod(new Radians("radians", main, "a"));
		addMethod(new Degrees("degrees", main, "a"));
		addMethod(new FMin("fmin", main, "a","b"));
		addMethod(new FMax("fmax", main, "a","b"));
		addMethod(new FMod("fmod", main, "a","b"));
		addMethod(new ASin("asin", main, "a"));
		addMethod(new ACos("acos", main, "a"));
		addMethod(new ATan("atan", main, "a"));
		addMethod(new ATan2("atan2", main, "a","b"));
		addMethod(new Floor("floor", main, "a"));
		addMethod(new Ceil("ceil", main, "a"));




		/*
		 * 

PUB start : okay
PUB stop

PUB FAdd(a, b)
PUB FSub(a, b)
PUB FMul(a, b)
PUB FDiv(a, b)
PUB FFloat(n) 
PUB FTrunc(a)
PUB FRound(a)
PUB FSqr(a)
PUB FCmp(a, b)
PUB Sin(a)
PUB Cos(a)
PUB Tan(a)
PUB Log(a)
PUB Log10(a)
PUB Exp(a) 
PUB Exp10(a)
PUB Pow(a, b)
PUB Frac(a) 
PUB FNeg(a)
PUB FAbs(a) 
PUB Radians(a) | b, c
PUB Degrees(a) | b, c
PUB FMin(a, b)
PUB FMax(a, b)

 PUB FMod(a, b)
PUB ASin(a)
PUB ACos(a)
PUB ATan(a)
PUB ATan2(a, b)
PUB Floor(a)
PUB Ceil(a)

		 */
	}

	public float[] toFloat(MethodInstance method, Expression...exp) throws Exception{
		float[] fls = new float[exp.length];
		for (int i = 0; i < exp.length; i++){
			fls[i] =Float.intBitsToFloat(exp[i].evaluateToValue(method));
		}
		return fls;
	}

	public Variable toIntVar(float fl){
		return new AnonVariable(Float.floatToIntBits(fl));
	}

	private class Start extends HardwareMethod{

		public Start(String name, Program program) throws SyntaxError{
			super(name, MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					started = true;
					return Variable.TRUE;
				}
			};
		}
	}

	private class Stop extends HardwareMethod{

		public Stop(String name, Program program) throws SyntaxError{
			super(name, MethodType.PUB, null, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					started = false;
					return Variable.TRUE;
				}
			};
		}
	}

	private class FAdd extends HardwareMethod{

		public FAdd(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					float[] params = toFloat( getCallingInstance(), parameters);
					float val = params[0] + params[1];
					return toIntVar(val);
				}
			};
		}
	}

	private class FSub extends HardwareMethod{

		public FSub(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					float[] params = toFloat(getCallingInstance(), parameters);
					float val = params[0] - params[1];
					return toIntVar(val);
				}
			};
		}
	}

	private class FMul extends HardwareMethod{

		public FMul(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					float[] params = toFloat(getCallingInstance(), parameters);
					float val = params[0] * params[1];
					return toIntVar(val);
				}
			};
		}
	}

	private class FDiv extends HardwareMethod{

		public FDiv(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){				
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					float[] params = toFloat(getCallingInstance(), parameters);
					float val = params[0]/params[1];
					return toIntVar(val);
				}
			};
		}
	}

	private class FFloat extends HardwareMethod{

		public FFloat(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					/*
					 * I believe the purpose of this method is to compute an appropriate
					 * float value for an int (or long in spin)
					 */
					float val = parameters[0].evaluateToValue(getCallingInstance());
					return toIntVar(val);
				}
			};
		}
	}

	private class FTrunc extends HardwareMethod{

		public FTrunc(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){

				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					//take the float, return a truncated int
					float[] vals = toFloat(getCallingInstance(), parameters);
					return new AnonVariable((int)vals[0]);

				}
			};
		}
	}

	private class FRound extends HardwareMethod{

		public FRound(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				return new AnonVariable(Math.round(vals[0]));
			}
			};
		}
	}

	private class FSqr extends HardwareMethod{

		public FSqr(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.sqrt(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class FCmp extends HardwareMethod{

		public FCmp(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				//need lab testing for this
				float[] vals = toFloat(getCallingInstance(), parameters);
				if (vals[0] < vals[1]) return new AnonVariable(-1);
				if (vals[0] > vals[1]) return new AnonVariable(1);
				return new AnonVariable(0);
			}
			};
		}
	}

	private class Sin extends HardwareMethod{

		public Sin(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.sin(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Cos extends HardwareMethod{

		public Cos(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.cos(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Tan extends HardwareMethod{

		public Tan(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}


		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.tan(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Log extends HardwareMethod{

		public Log(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.log(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Log10 extends HardwareMethod{

		public Log10(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.log10(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Exp extends HardwareMethod{

		public Exp(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.exp(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Exp10 extends HardwareMethod{

		public Exp10(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.pow(10,vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Pow extends HardwareMethod{

		public Pow(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.pow(vals[0],vals[1]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Frac extends HardwareMethod{

		public Frac(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				//WTF?
				return Variable.FALSE;
			}
			};
		}
	}

	private class FNeg extends HardwareMethod{

		public FNeg(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = -vals[0];
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class FAbs extends HardwareMethod{

		public FAbs(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = Math.abs(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Radians extends HardwareMethod{

		public Radians(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.toRadians(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Degrees extends HardwareMethod{

		public Degrees(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.toDegrees(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class FMin extends HardwareMethod{

		public FMin(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				return (vals[0]<vals[1]) ? toIntVar(vals[0]) : toIntVar(vals[1]);
			}
			};
		}
	}

	private class FMax extends HardwareMethod{

		public FMax(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				return (vals[0]>vals[1]) ? toIntVar(vals[0]) : toIntVar(vals[1]);
			}
			};
		}
	}

	private class FMod extends HardwareMethod{

		public FMod(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				return toIntVar(vals[0]%vals[1]);
			}
			};
		}
	}

	private class ASin extends HardwareMethod{

		public ASin(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.asin(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class ACos extends HardwareMethod{

		public ACos(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.acos(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class ATan extends HardwareMethod{

		public ATan(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.atan(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class ATan2 extends HardwareMethod{

		public ATan2(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.atan2(vals[0], vals[1]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Floor extends HardwareMethod{

		public Floor(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.floor(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}

	private class Ceil extends HardwareMethod{

		public Ceil(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
				float[] vals = toFloat(getCallingInstance(), parameters);
				vals[0] = (float) Math.ceil(vals[0]);
				return toIntVar(vals[0]);
			}
			};
		}
	}
}

/*
Float32A routines
'------------------

PUB FMod(a, b)
PUB ASin(a)
PUB ACos(a)
PUB ATan(a)
PUB ATan2(a, b)
PUB Floor(a)
PUB Ceil(a)

PUB start : okay
PUB stop

PUB FAdd(a, b)
PUB FSub(a, b)
PUB FMul(a, b)
PUB FDiv(a, b)
PUB FFloat(n) 
PUB FTrunc(a)
PUB FRound(a)
PUB FSqr(a)
PUB FCmp(a, b)
PUB Sin(a)
PUB Cos(a)
PUB Tan(a)
PUB Log(a)
PUB Log10(a)
PUB Exp(a) 
PUB Exp10(a)
PUB Pow(a, b)
PUB Frac(a) 
PUB FNeg(a)
PUB FAbs(a) 
PUB Radians(a) | b, c
PUB Degrees(a) | b, c
PUB FMin(a, b)
PUB FMax(a, b)

' 

 */

