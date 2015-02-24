package dang.tracker;


import java.awt.Point;

public class Pose {
	public int	x;			// robot x-position
	public int	y;			// robot y-position
	public int	angle;		// robot angle in radians

	public Pose() {
		this(0, 0, 0);
	}

	public Pose(Point p, int a){
		x = p.x;
		y = p.y;
		angle = a;
	}

	public Pose(int x, int y, int a){
		this.x = x;
		this.y = y;
		angle = a;
	}

	public Pose(Pose p) {
		this.x = p.x;
		this.y = p.y;
		this.angle = p.angle;
	}

	public String toString() {
		return "(" + x + "," + y + "," + angle + ")";
	}

	public boolean equals(Pose p) {
		return (p.x == x && p.y == y && p.angle == angle);
	}

	public String toOffsetString(int xOff, int yOff) {
		return "(" + (x+xOff) + "," + (y+yOff) + "," + angle + ")";
	}
}