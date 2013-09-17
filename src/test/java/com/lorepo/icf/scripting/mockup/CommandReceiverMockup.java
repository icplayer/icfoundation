package com.lorepo.icf.scripting.mockup;

import java.util.List;

import com.lorepo.icf.scripting.ICommandReceiver;
import com.lorepo.icf.scripting.IType;

public class CommandReceiverMockup implements ICommandReceiver {

	private String name;
	private String executedCommand;
	private List<IType> params;
	
	
	public CommandReceiverMockup(String name){
		this.name = name;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String executeCommand(String commandName, List<IType> params) {
		this.executedCommand = commandName;
		this.params = params;
		return "ok";
	}
	
	
	public String getExecutedCommand(){
		return executedCommand;
	}
	
	
	public List<IType> getParams(){
		return params;
	}

}
