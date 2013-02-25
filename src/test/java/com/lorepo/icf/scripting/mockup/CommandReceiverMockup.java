package com.lorepo.icf.scripting.mockup;

import java.util.List;

import com.lorepo.icf.scripting.ICommandReceiver;

public class CommandReceiverMockup implements ICommandReceiver {

	private String name;
	private String executedCommand;
	private List<String> params;
	
	
	public CommandReceiverMockup(String name){
		this.name = name;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String executeCommand(String commandName, List<String> params) {
		this.executedCommand = commandName;
		this.params = params;
		return "ok";
	}
	
	
	public String getExecutedCommand(){
		return executedCommand;
	}
	
	
	public List<String> getParams(){
		return params;
	}

}
