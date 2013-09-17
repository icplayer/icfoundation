package com.lorepo.icf.scripting.ast;

public class BooleanNode implements IASTNode{

	private final boolean value;
	
	public BooleanNode(boolean value){
		this.value = value;
	}
	
	
	public boolean getValue(){
		return value;
	}
	
	
	public String toString(){
		return Boolean.toString(value);
	}
}
