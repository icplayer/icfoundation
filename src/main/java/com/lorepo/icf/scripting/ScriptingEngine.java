package com.lorepo.icf.scripting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.lorepo.icf.scripting.ast.AbstractSyntaxTree;
import com.lorepo.icf.scripting.ast.AssignInstruction;
import com.lorepo.icf.scripting.ast.CommandInstruction;
import com.lorepo.icf.scripting.ast.IASTNode;
import com.lorepo.icf.scripting.ast.IInstruction;
import com.lorepo.icf.scripting.ast.StringNode;
import com.lorepo.icf.scripting.ast.VariableNode;
import com.lorepo.icf.scripting.parser.ScriptParser;

public class ScriptingEngine {

	private HashMap<String,ICommandReceiver> receivers = new HashMap<String, ICommandReceiver>();
	private HashMap<String,String> variables = new HashMap<String, String>();
	
	
	public void addReceiver(ICommandReceiver receiver){
		receivers.put(receiver.getName(), receiver);
	}
	
	
	public void execute(String script) throws ScriptParserException{
		
		ScriptParser parser = new ScriptParser();
		AbstractSyntaxTree ast;
		ast = parser.parse(script);
		variables.clear();
		for(int i = 0; i < ast.getInstructionCount(); i++){
			
			IInstruction instruction = ast.getInstruction(i);
			if(instruction instanceof CommandInstruction){
				executeCommand((CommandInstruction) instruction);
			}
			else if(instruction instanceof AssignInstruction){
				executeAssign((AssignInstruction) instruction);
			}
		}
	}


	private String executeCommand(CommandInstruction command) {
		
		try{
			ICommandReceiver receiver = receivers.get(command.getReceiverName());
			if(receiver != null){
				List<String> paramValues = prepareParamValues(command.getParams());
				return receiver.executeCommand(command.getCommand(), paramValues);
			}
		}
		catch(Exception e){
			Window.alert("Exeption in command:" + command.getCommand());
		}
		
		return null;
	}


	private List<String> prepareParamValues(List<IASTNode> params) {
		
		List<String> values = new ArrayList<String>();
		
		for(IASTNode param : params){
			
			if(param instanceof StringNode){
				values.add(((StringNode)param).getValue());
			}
			else if(param instanceof VariableNode){
				String name = ((VariableNode) param).getName();
				if(variables.containsKey(name)){
					values.add(variables.get(name));
				}
			}
		}
		
		return values;
	}


	private void executeAssign(AssignInstruction assign) {
		
		String varName = assign.getVariableName();
		String varValue = executeCommand(assign.getCommand());
		
		variables.put(varName, varValue);
	}


	public void reset() {
		receivers.clear();
	}
	

	protected HashMap<String,String> getSymbolMap(){
		return variables;
	}
}
