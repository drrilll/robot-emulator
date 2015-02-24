package dang.tracker;




// Represents the position of a robot
public class RobotPose extends Pose {
	public int 	id;		// Robot's ID

	public RobotPose(int ID, int iX, int iY, int iAngle) {
		super(iX,iY,iAngle);
		id = ID;
	}
	public String toString() {
		return id + " " + super.toString();
	}
}