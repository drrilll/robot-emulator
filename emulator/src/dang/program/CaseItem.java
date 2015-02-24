package dang.program;

import java.util.ArrayList;

import dang.program.expressions.Expression;

/**
 * 
 * @author darrylhill
 *
 */
public class CaseItem {
	
	ArrayList<SpinListItem> exps = new ArrayList<SpinListItem>();
	
	public CaseItem(){}
	
	public CaseItem(Expression exp){
		exps.add(new ListItem(exp));
	}
	
	public CaseItem(SpinListItem item){
		exps.add(item);
	}
	
	public void addItem(Expression exp){
		exps.add(new ListItem(exp));
	}
	
	public void addItem(SpinListItem item){
		exps.add(item);
	}
	
	public boolean equals (Expression item, MethodInstance context) throws Exception{
		
		for (SpinListItem listItem: exps){
			if (listItem.equals(item, context)) return true;
		}
		return false;
	}

}
