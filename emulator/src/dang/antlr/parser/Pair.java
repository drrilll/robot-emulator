package dang.antlr.parser;
/**
 * There were a few places I wanted 2 return variables.
 * I borrowed the names car and cdr from lisp, but it is
 * functionally different from lisp
 */
public class Pair<T, S> {
	T t;
	S s;
	public Pair(T t, S s){
		this.t = t;
		this.s = s;
	}
	
	public Pair(T t){
		this.t = t;
	}
	
	public void setCdr(S s){
		this.s = s;
	}
	
	public T car(){
		return t;
	}
	
	/*
	 * strictly speaking, if you are familiar with Lisp, we are not 
	 * returning a list. Although maybe we should.
	 */
	public S cdr(){
		return s;
	}
}
