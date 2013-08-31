package com.lorepo.icf.scripting.ast;

public class StringNode implements IASTNode{

	private final String value;
	
	public StringNode(String value){
		this.value = value;
	}
	
	
	public String getValue(){
		return value;
	}
	
	
	public String toString(){
		return value;
	}
}
