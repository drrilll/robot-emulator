package dang.exceptions;

public class RobotNotRunningException extends Exception {

	public RobotNotRunningException(String message){
		super(message);
	}
	
	public RobotNotRunningException(){
		super();
	}
}
