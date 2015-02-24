package dang.tracker;


public class MapPose {
	private double	time;		// time at this pose
	private double 	x;			// robot x-position
	private double	y;			// robot y-position
	private double	degrees;	// robot angle in radians
	private double  radians;

	public MapPose() {
		this(0.0, 0.0, 0.0, 0.0);
	}
	public MapPose(double t, double x, double y, double degrees) {
		time = t;
		this.x = x;
		this.y = y;
		this.degrees = degrees;
		radians = Math.toRadians(degrees);
	}
	public MapPose(MapPose p) {
		time = p.time;
		this.x = p.x;
		this.y = p.y;
		this.degrees = p.degrees;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDegrees() {
		return degrees;
	}

	public double getRadians() {
		return radians;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setDegrees(double degrees) {
		this.degrees = degrees;
		radians = Math.toRadians(degrees);
	}

	public void setRadians(double radians) {
		this.radians = radians;
		degrees = Math.toDegrees(radians);
	}

	public String toString() {
		return "Pose @" + time + " sec: (" + x + "," + y + "), " + Math.toDegrees(degrees) + " degrees";
	}
}