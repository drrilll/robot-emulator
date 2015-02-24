package dang.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import dang.program.expressions.Expression;

public class SpinList implements SList {
	
	ArrayList<Expression> list = new ArrayList<Expression>();
	ArrayList<SpinListItem> listItems = new ArrayList<SpinListItem>();
	//Apparently treemaps are sorted? good to know
	TreeMap<Integer, Range> rangeList = new TreeMap<Integer, Range>();
	
	@Override
	public Variable getIndex(Expression value,MethodInstance method) throws Exception {
		return new AnonVariable(getIndexForValue(value,method));
	}

	@Override
	public int getIndexForValue(Expression value,MethodInstance method) throws Exception {
		expandList(method);
		int val = value.evaluateToValue(method);
		int itemval;
		for (int i = 0; i < list.size(); i ++){
			itemval = list.get(i).evaluateToValue(method);
			if (itemval == val){
				return i;
			}
		}
		return 0;
	}

	@Override
	public Variable getValue(Expression index,MethodInstance method) throws Exception {
		return new AnonVariable(getValueValue(index,method));
	}

	@Override
	public int getValueValue(Expression index,MethodInstance method) throws Exception {
		expandList(method);
		int ind = index.evaluateToValue(method);
		//are we outside the range?
		if(ind >= list.size()){
			return 0;
		}else{
			return list.get(ind).evaluateToValue(method);
		}
	}

	@Override
	public void addItem(SpinListItem item) throws Exception {
		if (item instanceof Range){
			addRange((Range)item);
		}else{
			listItems.add(item);
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public SpinListItem remove(int i) {
		return listItems.remove(i);
	}

	@Override
	public boolean isInList(Expression value,MethodInstance method) throws Exception {
		expandList(method);
		int val = value.evaluateToValue(method);
		int itemval;
		for (int i = 0; i < val; i ++){
			itemval = list.get(i).evaluateToValue(method);
			if (itemval == val){
				return true;
			}
		}
		return false;
	}

	@Override
	public void addRange(Range range) throws Exception {
		int position = listItems.size();
		//keep track of where the ranges are with this hashmap
		rangeList.put(position, range);
		listItems.add(range);
		
	}
	
	/**
	 * take our listitems, expand any ranges, and put it in the expression list.
	 * This must be called before any other operations are performed, otherwise
	 * you will be dealing with an old list. Good luck finding that error
	 * @throws Exception 
	 */
	private void expandList(MethodInstance method) throws Exception{
		list.clear();
		if (rangeList.size()>0){
			Set<Integer> rangePositions = rangeList.keySet();
			int position = 0;
			for (int rangePos: rangePositions){
				//or until we are at the first range item
				while (position != rangePos){
					list.add(listItems.get(position).getExpression());
					position ++;
				}
				//we are at the first range, we get the rangelist and expand it
				ArrayList<Expression> range = rangeList.get(rangePos).getList(method);
				//add them all to our list
				list.addAll(range);
				//advance 1 to the next element after the range
				position ++;
			}
		}else{
			//there are no ranges, so move the ItemList into the List
			for (int i = 0; i < listItems.size(); i++){
				list.add(listItems.get(i).getExpression());
			}
			//piece of cake. What are the odds there is a bug in that code?
		}
		Debug.debug("printing list: ");
		for (Expression e:list){
			Debug.debug(""+e);
		}
		Debug.debug("end list");
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer("List:\n");
		for (SpinListItem l : listItems){
			str.append(l+"\n");
		}
		return str.toString();
	}

}
