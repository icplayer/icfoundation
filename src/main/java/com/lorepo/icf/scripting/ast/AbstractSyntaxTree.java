package com.lorepo.icf.scripting.ast;

import java.util.ArrayList;
import java.util.List;

public class AbstractSyntaxTree {

	private List<IInstruction> instructions = new ArrayList<IInstruction>();
	
	
	public int getInstructionCount(){
		return instructions.size();
	}
	
	public IInstruction getInstruction(int index){
		return instructions.get(index);
	}
	
	public void addInstruction(IInstruction command){
		instructions.add(command);
	}
}
