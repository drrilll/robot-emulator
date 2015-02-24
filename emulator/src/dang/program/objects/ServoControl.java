package dang.program.objects;

import java.io.IOException;

import dang.exceptions.RobotNotRunningException;
import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.AnonVariable;
import dang.program.Constant;
import dang.program.Debug;
import dang.program.Method;
import dang.program.Method.MethodType;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class ServoControl extends SpinObject {
	
	private boolean started = false;
	private boolean useWheels, useGrippers, usePitch, useYaw;

	//for hardware methods, only the number of parameters matter, since we already
	//know their names and purpose.
	public ServoControl(Program main) throws Exception {
		super("ServoControl", main);
		Method servoMethod = new Start("start",this,"rservostop","lservostop","c","d","e","f");
		addMethod(servoMethod);
		servoMethod = new Start("stop", this);
		addMethod(servoMethod);
		servoMethod = new SetSpeeds("setspeeds",MethodType.PUB,null, this, "left", "right");
		addMethod(servoMethod);
		servoMethod = new SetSpeeds("setrightspeed",MethodType.PUB,null, this, "speed");
		addMethod(servoMethod);
		servoMethod = new SetSpeeds("setleftspeed",MethodType.PUB,null, this, "speed");
		addMethod(servoMethod);
		servoMethod = new SetGripper("setleftgripper", this, "value");
		addMethod(servoMethod);
		servoMethod = new SetGripper("setrightgripper", this, "value");
		addMethod(servoMethod);
		servoMethod = new SetHead("setheadyaw", this, "value");
		addMethod(servoMethod);
		servoMethod = new SetHead("setheadpitch", this, "value");
		addMethod(servoMethod);
		servoMethod = new WheelsAreStopped("wheelsarestopped", this);
		addMethod(servoMethod);

		
		/*
		 *  LEFT_GRIPPER_MIN = 215
  LEFT_GRIPPER_MID = 170
  LEFT_GRIPPER_MAX = 140
  RIGHT_GRIPPER_MIN = 104
  RIGHT_GRIPPER_MID = 150
  RIGHT_GRIPPER_MAX = 181
  PITCH_MIN = 95
  PITCH_MID = 137
  PITCH_MAX = 170
  YAW_MIN = 61
  YAW_MID = 146
  YAW_MAX = 225
		 */
		addConstant(new Constant("left_gripper_min", 215, getMemory()));
		addConstant(new Constant("left_gripper_mid", 170, getMemory()));
		addConstant(new Constant("left_gripper_max", 140, getMemory()));
		addConstant(new Constant("right_gripper_min", 104, getMemory()));
		addConstant(new Constant("right_gripper_mid", 150, getMemory()));
		addConstant(new Constant("right_gripper_max", 181, getMemory()));
		addConstant(new Constant("pitch_min", 95, getMemory()));
		addConstant(new Constant("pitch_mid", 137, getMemory()));
		addConstant(new Constant("pitch_max", 170, getMemory()));
		addConstant(new Constant("yaw_min", 61, getMemory()));
		addConstant(new Constant("yaw_mid", 146, getMemory()));
		addConstant(new Constant("yaw_max", 225, getMemory()));



	}
	
	@Override
	public Variable callMethod(String methodName, Expression[] parameters, 
			MethodInstance callingInstance) 
			throws Exception{
		
		//make sure that the servocontrol is started. It runs on its own COG I believe.
		//Implementing COG's does not seem as far-fetched as it once did. I'm not going to do it
		//just seems like it wouldn't be too hard
		if (methodName.equals("start")){
			started = true;
		}
		if (!started)return Variable.FALSE;
		return super.callMethod(methodName,parameters, callingInstance);
	}
	/*
	 * PUB Start(leftServoStoppedValue, rightServoStoppedValue, useWheels, useGrippers, usePitch, useYaw)
 PUB SetLeftSpeed(speed)
  {{ Set the speed of the left servo. }}
  leftSpeed := speed

  
PUB SetRightSpeed(speed)
  {{ Set the speed of the right servo. }}
  rightSpeed := speed
   

PUB SetSpeeds(lSpeed, rSpeed)
  {{ Set the speeds of both servos. }}
  leftSpeed := lSpeed
  rightSpeed := rSpeed


PUB SetHeadPitch(value)
  {{ Set the pitch of the head servo. }}
  headPitch := value

  
PUB SetHeadYaw(value)
  {{ Set the yaw of the head servo. }}
  headYaw := value


PUB SetLeftGripper(value)
  {{ Set the position of the left gripper servo. }}
  leftGripper := value

  
PUB SetRightGripper(value)
  {{ Set the position of the right gripper servo. }}
  rightGripper := value


PUB Stop
  {{ Stop the cog that was controlling the servos. }}
  if cog > 0
    cogstop(cog-1)

	 */

	
	private class SetSpeeds extends HardwareMethod {
		
		
		/**
		 * the method signature is setSpeeds(leftSpeed, rightSpeed).
		 * So we take care of the parameter list ourselves since it
		 * is a predefined method
		 * 
		 * @param globalVariables
		 * @param name
		 * @param type
		 * @param parameters
		 * @param localVariables
		 * @throws DuplicateVariableNameException
		 */
		public SetSpeeds(String name, MethodType type, 
				String[] localVariables, Program program, String...parameters) 
						throws SyntaxError{
			super(name, type, parameters, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters) 
						throws Exception {

					//is the wheel servo active?
					Debug.debug("are we using wheels?");
					if (!useWheels) return Variable.FALSE;
					Debug.debug("we are using wheels");
					/*
					int popStack = getMemory().getStackPointer();
					Variable ret = initializeMemory(parameters);*/

					//it is, so execute
					Debug.debug("Executing setSpeeds, robot: "+getRobot());
					if (getName().equals("setspeeds")){
						getRobot().setSpeeds((int)parameters[0].evaluateToValue(getCallingInstance()), 
								(int)parameters[1].evaluateToValue(getCallingInstance()));
						//return getVariable("result");
					}else if (getName().equals("setleftspeed")){
						getRobot().setLeftSpeed(parameters[0].evaluateToValue(getCallingInstance()));
						//return getVariable("result");
					}else if (getName().equals("setrightspeed")){
						getRobot().setRightSpeed(parameters[0].evaluateToValue(getCallingInstance()));
						//return getVariable("result");
					}
					Variable ret = getVariable(getReturnVariable());
					
					//put the return variable into an anonymous variable
					AnonVariable av = new AnonVariable(ret.getType(), ret.evaluateToValue(getCallingInstance()));

					//return the anonymous variable
					return av;
				}
			};
		}
	}

	private class Start extends HardwareMethod{
		
		public Start(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					if (getName().equals("start")){
						started = true;
						Debug.debug("starting servos ...");

						//the first two params are left and right wheel stop values, which
						//we don't care about. We will assume they are always correct. The 
						//next four are setting up which servos are being used. 
						useWheels = parameters[2].evaluateToValue(getCallingInstance()) == 0 ? false :  true ;
						useGrippers = parameters[3].evaluateToValue(getCallingInstance()) == 0 ? false :  true ;
						usePitch = parameters[4].evaluateToValue(getCallingInstance()) == 0 ? false :  true ;
						useYaw = parameters[5].evaluateToValue(getCallingInstance()) == 0 ? false :  true ;
						return Variable.TRUE;
					}else if (getName().equals("stop")){
						started = false;
						return Variable.TRUE;
					}
					return Variable.FALSE;
				}
			};
		}
	}
	
	private class SetHead extends HardwareMethod{
		public SetHead(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					//if its this method, and this servo has been enabled
					if ((getName().equals("setheadpitch"))&&(usePitch)){
						getRobot().setHeadPitch(parameters[0].evaluateToValue(getCallingInstance()));
						return Variable.TRUE;
					}else if ((getName().equals("setheadyaw"))&&(useYaw)){
						int value = parameters[0].evaluateToValue(getCallingInstance());
						//Debug.debug("setting yaw: "+value,"yaw");
						getRobot().setHeadYaw(value);
						return Variable.TRUE;
					}
					return Variable.FALSE;

				}
			};
		}
	}
	
	private class WheelsAreStopped extends HardwareMethod{
		public WheelsAreStopped(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		
		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					if ((getRobot().getLeftSpeed()==0)&&(getRobot().getRightSpeed()==0)){
						return new AnonVariable(1);
					}else{
						return Variable.FALSE;
					}
				}
			};
		}
	}

	private class SetGripper extends HardwareMethod{
		public SetGripper(String name, Program program, String...params) throws SyntaxError{
			super(name, MethodType.PUB, params, null, program);
		}

		
		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					if (useGrippers){
						if (getName().equals("setrightgripper")){
							getRobot().setRightGripper(parameters[0].evaluateToValue(getCallingInstance()));
						}else if (getName().equals("setleftgripper")){
							getRobot().setLeftGripper(parameters[0].evaluateToValue(getCallingInstance()));
						}else{
							return Variable.FALSE;
						}
						return Variable.TRUE;
					}
					return Variable.FALSE;

				}
			};
		}
	}
}
