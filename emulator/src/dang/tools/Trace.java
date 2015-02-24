package dang.tools;

import java.util.StringTokenizer;


public class Trace {
	boolean header = false;
	StringBuffer line;
	int x,y,angle,dirrs,sonar,ir6;
	
	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public StringBuffer getLine() {
		return line;
	}

	public void setLine(StringBuffer line) {
		this.line = line;
	}

	public Trace(String line){
		if (this.line.charAt(0)=='x'){
			header = true;
			this.line = new StringBuffer(line);
		}else{
			this.line = parseLine(line);
		}
		
	}
	
	public boolean equals(Trace trace){
		return ((trace.x == x) && (trace.y == y) && (trace.angle == angle));
	}

	private StringBuffer parseLine(String line){
		StringBuffer target = new StringBuffer();
		StringTokenizer st;
		final String COMMA = ",";		
		st = new StringTokenizer(line, COMMA);
		int count = st.countTokens();
		
		x= Integer.parseInt(st.nextToken());
		y= Integer.parseInt(st.nextToken());
		angle= Integer.parseInt(st.nextToken());
		target.append(x+","+y+","+angle);	
		if (count == 3)
			target.append("\n");
		else{
			target.append(",");

			dirrs = Integer.parseInt(st.nextToken());
			if ((dirrs == 255)||(dirrs > 80))
				dirrs = 0;
			target.append(dirrs+",");

			sonar = Integer.parseInt(st.nextToken());
			if ((sonar == 255)||(sonar > 100))
				sonar = 0;
			target.append(sonar+",");

			ir6 = Integer.parseInt(st.nextToken());
			if (ir6 == 255)
				ir6 = 0;
			target.append(ir6+"\n");
		}

		return target;
	}

	
	

}
