package com.lorepo.icf.scripting.ast;


public class AssignInstruction implements IInstruction{

	private final String varName;
	private final CommandInstruction command;
	
	
	public AssignInstruction(String variableName, CommandInstruction command){
		this.varName = variableName;
		this.command = command;
	}
	
	public CommandInstruction getCommand(){
		return command;
	}
	
	public String getVariableName(){
		return varName;
	}
}
