
package dang.tools;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TraceFixer {
	
	ArrayList<Trace> trace;
	StringBuffer target;
	String path = "/Users/darrylhill/Documents/comp4807/mycode/";
	//String path = "C:\\Users\\COMP4807 Student\\Desktop\\mycode\\";
	String[] traceFiles = { "BorderMap.trc","NavigateMap.trc"};//"BorderMap.trc",
	String outputFile = "BestMap.trc";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TraceFixer fixer = new TraceFixer();
		fixer.improveMap();
		fixer.optimize();
		fixer.writeFile();

	}
	
	public TraceFixer(){
		target = new StringBuffer();
		trace = new ArrayList<Trace>();
	}
	
	public void optimize(){
		loadLinkedList();
		for (int i = 0; i < trace.size(); i++){
			if (trace.get(i).equals(trace.get(i+1))){
				
			}
		}
	}
	
	public void loadLinkedList(){
		StringTokenizer st = new StringTokenizer(target.toString(), "\n");
		for (int i = 0; i < st.countTokens(); i++){
			trace.add(new Trace(st.nextToken()));		
		}
	}
	
	public void parseLine(String line){
		StringTokenizer st;
		final String COMMA = ",";		
		st = new StringTokenizer(line, COMMA);
		int count = st.countTokens();
		if (count<6){
			//only the position data or else the position
			//plus one set of sensor reading, which is good
			target.append(line+"\n");
		}else{
			//we have more data than we should, so
			//spread it out	
			int x,y,angle;
			
			x= Integer.parseInt(st.nextToken());
			y= Integer.parseInt(st.nextToken());
			angle= Integer.parseInt(st.nextToken());
			for (int i =0; i< ((count - 3)/3); i ++){
				target.append(x+","+y+","+angle+",");					
				int temp = Integer.parseInt(st.nextToken());
				if ((temp == 255)||(temp > 80))
					temp = 0;
				target.append(temp+",");
				temp = Integer.parseInt(st.nextToken());
				if ((temp == 255)||(temp > 100))
					temp = 0;
				target.append(temp+",");
				temp = Integer.parseInt(st.nextToken());
				if (temp == 255)
					temp = 0;
				target.append(temp+"\n");
			}
			
		}
	}
	
	public void writeFile(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path + outputFile));
			out.write(target.toString());
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Exception ");
		}

	}
	
	public void improveMap(){
		for (int i = 0; i<traceFiles.length; i++){
			try{
				FileInputStream fstream = new FileInputStream(path + traceFiles[i]);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				//Read File Line By Line
				//first line is the header
				//but only on the first file. Otherwise throw it away
				if(i==0)
					target.append(br.readLine() + "\n");
				else
					br.readLine();
				while ((strLine = br.readLine()) != null)   {
					parseLine(strLine);
				}
				//Close the input stream
				in.close();
			}catch (IOException e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

}
