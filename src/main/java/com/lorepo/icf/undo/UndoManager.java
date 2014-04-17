package com.lorepo.icf.undo;

import java.util.ArrayList;
import java.util.List;

public class UndoManager {
	
	class State{
		String name;
		String value;
		State(String n, String v){ name = n; value = v; }
	}
	
	private List<State> states = new ArrayList<State>();
	private int index = 0;

	
	public UndoManager(String state){
		addState("init", state);
	}
	
	/** Add new state and set it as active */
	public void addState(String name, String value){
		while(states.size() > index+1){
			states.remove(states.size()-1);
		}
		states.add(new State(name, value));
		index = states.size()-1;
	}


	public String getStateName(){
		if(index > -1 && index < states.size()){
			return states.get(index).name;
		}
		else{
			return null;
		}
	}
	
	/** Return previous state value or null if there are no actions to undo */
	public String undo(){
		if(index > 0 && index < states.size()){
			index -= 1;
			return states.get(index).value;
		}
		else{
			return null;
		}
	}
	
	/** Return last undone action value. Return null if there are no actions to redo */
	public String redo(){
		if(index < states.size()-1){
			index += 1;
			return states.get(index).value;
		}
		else{
			return null;
		}
	}
	
	public boolean canUndo(){
		return index > 0;
	}
	
	public boolean canRedo(){
		return index < states.size()-1;
	}
}
