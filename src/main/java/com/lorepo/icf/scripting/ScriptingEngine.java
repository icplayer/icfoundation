package com.lorepo.icf.scripting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.lorepo.icf.scripting.ast.AbstractSyntaxTree;
import com.lorepo.icf.scripting.ast.AssignInstruction;
import com.lorepo.icf.scripting.ast.BooleanNode;
import com.lorepo.icf.scripting.ast.CommandInstruction;
import com.lorepo.icf.scripting.ast.IASTNode;
import com.lorepo.icf.scripting.ast.IInstruction;
import com.lorepo.icf.scripting.ast.StringNode;
import com.lorepo.icf.scripting.ast.VariableNode;
import com.lorepo.icf.scripting.parser.ScriptParser;

public class ScriptingEngine {

	class StringType implements IStringType{
		private final String value;
		StringType(String v){value = v;}
		public String getValue() {return value; }
		public String asString() {return value;}
	}
	
	class BoolType implements IBoolType{
		private final boolean value;
		BoolType(boolean v){value = v;}
		public boolean getValue() {return value; }
		public String asString() {return Boolean.toString(value);}
	}
	
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
				List<IType> paramValues = prepareParamValues(command.getParams());
				return receiver.executeCommand(command.getCommand(), paramValues);
			}
		}
		catch(Exception e){
			Window.alert("Exeption in command:" + command.getCommand());
		}
		
		return null;
	}


	private List<IType> prepareParamValues(List<IASTNode> params) {
		
		List<IType> values = new ArrayList<IType>();
		
		for(IASTNode param : params){
			
			if(param instanceof StringNode){
				String value = ((StringNode)param).getValue(); 
				values.add(new StringType(value));
			}
			else if(param instanceof BooleanNode){
				boolean value = ((BooleanNode)param).getValue(); 
				values.add(new BoolType(value));
			}
			else if(param instanceof VariableNode){
				String name = ((VariableNode) param).getName();
				if(variables.containsKey(name)){
					values.add(new StringType(variables.get(name)));
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
