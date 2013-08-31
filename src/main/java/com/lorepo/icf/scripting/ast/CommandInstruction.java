package com.lorepo.icf.scripting.ast;

import java.util.ArrayList;
import java.util.List;

public class CommandInstruction implements IInstruction{

	private final String command;
	private final String receiverName;
	private List<IASTNode> params = new ArrayList<IASTNode>();
	
	
	public CommandInstruction(String receiverName, String command){
		this.command = command.toLowerCase();
		this.receiverName = receiverName;
	}
	
	
	public String getCommand(){
		return command;
	}
	
	
	public String getReceiverName(){
		return receiverName;
	}


	public List<IASTNode> getParams() {
		return params;
	}


	public void setParams(List<IASTNode> params) {
		this.params = params;
	}
}
