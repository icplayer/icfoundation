/*
  The MIT License
  
  Copyright (c) 2009 smath project
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, andor sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
*/
package com.lorepo.icf.scripting.parser;

import java.util.ArrayList;
import java.util.List;

import com.lorepo.icf.scripting.ScriptParserException;
import com.lorepo.icf.scripting.ast.AbstractSyntaxTree;
import com.lorepo.icf.scripting.ast.AssignInstruction;
import com.lorepo.icf.scripting.ast.BooleanNode;
import com.lorepo.icf.scripting.ast.CommandInstruction;
import com.lorepo.icf.scripting.ast.IASTNode;
import com.lorepo.icf.scripting.ast.IInstruction;
import com.lorepo.icf.scripting.ast.StringNode;
import com.lorepo.icf.scripting.ast.VariableNode;


/**
 * Parsuje wyrazenie dostarczone jako tekst
 * @author klangner
 *
 */
public class ScriptParser {
	
	private Tokenizer	tokenizer;

	
	/**
	 * Parse given expression
	 * @return Root node
	 * @throws ScriptParserException 
	 */
	public AbstractSyntaxTree parse(String exp) throws ScriptParserException{

		tokenizer = new Tokenizer(exp);
		
		return parseInstructions();
	}

	/**
	 * listy komend do wykonania
	 * @throws ScriptParserException 
	 */
	private AbstractSyntaxTree parseInstructions() throws ScriptParserException
	{
		AbstractSyntaxTree ast = new AbstractSyntaxTree();
		Token token;

	    while(tokenizer.hasMoreTokens()){

	    	IInstruction instruction = parseAssign();
	    	if(instruction == null){
	    		instruction = parseCommand();
	    	}
	    	
	    	if(instruction != null){
	    		ast.addInstruction(instruction);
	    	}
	    	
	    	if(tokenizer.hasMoreTokens()){
	    		token = tokenizer.nextToken();
	    		assertTokenText(token, ";");
	    	}
	    }

	    return ast;
	}

	
	/**
	 * Parsowanie wyrażenia:
	 * 
	 * var a = obj.getIndex()
	 */
	private AssignInstruction parseAssign() throws ScriptParserException {

		AssignInstruction instruction;
		Token token;
		
		token = tokenizer.nextToken();
		if(token.getText().compareTo("var") != 0){
			tokenizer.pushback(token);
			return null;
		}
		
		token = parseIdentifier();
		String variableName = token.getText();

		token = tokenizer.nextToken();
		if(token.getText().charAt(0) != '='){
	    	throw new ScriptParserException("=", token.getText());			
		}
		
		CommandInstruction cmd = parseCommand();
		instruction = new AssignInstruction(variableName, cmd);
		
		return instruction;
	}

	
	/**
	 * Parsowanie wyrażenia:
	 * 
	 * obj.hide()
	 */
	private CommandInstruction parseCommand() throws ScriptParserException {

		CommandInstruction cmd;
		Token token;
		
		token = parseIdentifier();
		String objName = token.getText();
		
		token = tokenizer.nextToken();
		if(token.getText().charAt(0) != '.'){
	    	throw new ScriptParserException(".", token.getText());			
		}
		
		token = parseIdentifier();
		String cmdName = token.getText();
		cmd = new CommandInstruction(objName, cmdName);
		
		List<IASTNode> params = parseFunctionParams();
		cmd.setParams(params);
		
		return cmd;
	}

	
	private Token parseIdentifier() throws ScriptParserException {
		
		if(!tokenizer.hasMoreTokens()){
			throw new ScriptParserException("Identifier", null);	
		}
		
		Token token = tokenizer.nextToken();
		if(token instanceof IdentifierToken){
			return token;
		}
		else{
	    	throw new ScriptParserException("Identifier", token.getText());			
		}
	}

	private List<IASTNode> parseFunctionParams() throws ScriptParserException {
		
		List<IASTNode> params = new ArrayList<IASTNode>();
		
		if(!tokenizer.hasMoreTokens()){
			throw new ScriptParserException("(", null);
		}
		
		Token token = tokenizer.nextToken();
		
		if(token.getText().charAt(0) != '('){
			throw new ScriptParserException("(", token.getText());
		}
		
		while(tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			if(token.getText().charAt(0) == ')'){
				break;
			}
			tokenizer.pushback(token);

			IASTNode param = parseParam();
			params.add(param);
			
			token = tokenizer.nextToken();
			if(token.getText().charAt(0) != ','){
				tokenizer.pushback(token);
			}
		}
		
		return params;
	}

	private IASTNode parseParam() {
		
		IASTNode node = null;
		Token token = tokenizer.nextToken();
		if(token instanceof IdentifierToken){
			node = new VariableNode(token.getText());
		}
		else if(token instanceof KeywordToken){
			if(token.getText().equals("True")){
				node = new BooleanNode(true);
			}
			else if(token.getText().equals("False")){
				node = new BooleanNode(false);
			}
		}
		else{
			node = new StringNode(token.getText());
		}
		
		return node;
	}

	private void assertTokenText(Token token, String expected) throws ScriptParserException {

		if(token.getText().compareTo(expected) != 0){
			throw new ScriptParserException(expected, token.getText());
		}
		
	}
	
}
