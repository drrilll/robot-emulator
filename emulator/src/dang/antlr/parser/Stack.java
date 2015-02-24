package dang.antlr.parser;

import java.util.ArrayList;

public class Stack{
	ArrayList<Integer> stack = new ArrayList<Integer>();
	ArrayList<Integer> stateStack = new ArrayList<Integer>();
	public Stack(){
		
	}
	
	public void add(int tab, int statement){
		stack.add(tab);
		stateStack.add(statement);
	}
	
	public int size(){
		return stack.size();
	}
	
	public ArrayList<Integer> getTabStack(){
		return stack;
	}
	
	public int lastTab(){
		return stack.get(stack.size()-1);
	}
	
	public int pop(){
		stateStack.remove(stateStack.size()-1);
		return stack.remove(stack.size()-1);
	}
	
	public int lastStatement(){
		return stateStack.get(stateStack.size()-1);
	}
}