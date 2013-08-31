package com.lorepo.icf.scripting.ast;

public class VariableNode implements IASTNode{

	private final String name;
	
	public VariableNode(String name){
		this.name = name;
	}
	
	
	public String getName(){
		return name;
	}

	
	public String toString(){
		return name;
	}
	
}
