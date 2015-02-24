package dang.program.objects;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dang.exceptions.SpinError;
import dang.exceptions.SyntaxError;
import dang.program.Constant;
import dang.program.Memory;
import dang.program.Method;
import dang.program.MethodInstance;
import dang.program.Program;
import dang.program.SpinString;
import dang.program.Variable;
import dang.program.expressions.Expression;

public class Debugger extends SpinObject {



	public Debugger(Program main) throws Exception{
		super("debugger", main);
		String[] params = {"whatever"};
		addMethod(new MemoryMap(main, params));
		addMethod(new End(this));
	}

	private class MemoryMap extends HardwareMethod{
		Program main;
		public MemoryMap(Program main, String[] params) throws SyntaxError{
			super("memorymap",MethodType.PUB, params, null,main);
			this.main = main;
		}

		@Override 
		public MethodInstance getMethodInstance(MethodInstance method){
			return new MethodInstance(this, method){
				@Override
				public Variable execute(Expression[] parameters)
						throws Exception {
					int id = parameters[0].evaluateToValue(getCallingInstance());
					JFrame frame = new JFrame("Memory Map "+id);
					frame.setSize(400,400);
					frame.setLocation(50, 200);
					JTextArea textArea = new JTextArea();
					textArea.append("Local Strings\n");
					for (SpinString ss: getCallingInstance().getLocalStrings().values()){
						int location = ss.getLocation();
						int length = ss.getSize();
						Memory memory = main.getMemory();
						for (int i = 0; i < length; i ++){
							textArea.append(i+": "+(char)memory.get(location + i) +"\n");
						}
					}
					textArea.append("Constants\n");
					for (Constant c : main.getConstants().values()){
						c.setMemory(main.getMemory());
						textArea.append(c.memoryMap()+"\n");
					}
					textArea.append("Variables\n");
					for (Variable v: main.getGlobalVariables().values()){
						v.setMemory(main.getMemory());
						textArea.append(v.memoryMap()+"\n");
					}
					textArea.append("Objects\n");
					for (Program obj: main.getObjects().values()){
						textArea.append(obj.getProgramName()+"\n");
						for (Constant c : obj.getConstants().values()){
							c.setMemory(main.getMemory());
							textArea.append(c.memoryMap()+"\n");
						}
					}
					JScrollPane pane = new JScrollPane(textArea);
					frame.add(pane);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					return Variable.TRUE;
				}
			};
		}
	}
}
