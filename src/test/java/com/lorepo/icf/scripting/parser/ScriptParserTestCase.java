package com.lorepo.icf.scripting.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lorepo.icf.scripting.ScriptParserException;
import com.lorepo.icf.scripting.ast.AbstractSyntaxTree;
import com.lorepo.icf.scripting.ast.AssignInstruction;
import com.lorepo.icf.scripting.ast.BooleanNode;
import com.lorepo.icf.scripting.ast.CommandInstruction;
import com.lorepo.icf.scripting.ast.IASTNode;
import com.lorepo.icf.scripting.ast.StringNode;
import com.lorepo.icf.scripting.ast.VariableNode;

public class ScriptParserTestCase{

	@Test
	public void commandCount() throws ScriptParserException {
		
		String script = "text2.hide();";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(1, ast.getInstructionCount());
	}


	@Test
	public void commandCount2() throws ScriptParserException {
		
		String script = "text2.hide();text1.show();";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(2, ast.getInstructionCount());
	}

	
	@Test
	public void params() throws ScriptParserException {
		
		String script = "text2.hide(1, 'ala');";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(1, ast.getInstructionCount());
		
		CommandInstruction cmd = (CommandInstruction) ast.getInstruction(0);
		List<IASTNode> params = cmd.getParams();
		assertEquals(2, params.size());
		assertEquals("1", params.get(0).toString());
		assertEquals("ala", params.get(1).toString());
	}


	@Test
	public void params2() throws ScriptParserException {
		
		String script = "text2.hide(1, \"ala\");";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(1, ast.getInstructionCount());
		
		CommandInstruction cmd = (CommandInstruction) ast.getInstruction(0);
		List<IASTNode> params = cmd.getParams();
		assertEquals(2, params.size());
		assertEquals("1", params.get(0).toString());
		assertEquals("ala", params.get(1).toString());
	}
	
	@Test
	public void paramsVariableAndString() throws ScriptParserException {
		
		String script = "text2.hide(a, 'ala');";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(1, ast.getInstructionCount());
		
		CommandInstruction cmd = (CommandInstruction) ast.getInstruction(0);
		List<IASTNode> params = cmd.getParams();
		assertEquals(2, params.size());
		assertTrue(params.get(0) instanceof VariableNode);
		assertTrue(params.get(1) instanceof StringNode);
	}
	
	@Test
	public void paramsTrueAndString() throws ScriptParserException {
		
		String script = "text2.hide(True, 'ala');";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(1, ast.getInstructionCount());
		
		CommandInstruction cmd = (CommandInstruction) ast.getInstruction(0);
		List<IASTNode> params = cmd.getParams();
		assertEquals(2, params.size());
		assertTrue(params.get(0) instanceof BooleanNode);
		assertTrue(params.get(1) instanceof StringNode);
	}
	

	@Test
	public void varDeclaration() throws ScriptParserException {
		
		String script = "var a = text2.getPosition(); image.setFrame(a)";
		ScriptParser parser = new ScriptParser();
		
		AbstractSyntaxTree ast = parser.parse(script);
		
		assertEquals(2, ast.getInstructionCount());
		
		assertTrue(ast.getInstruction(0) instanceof AssignInstruction);
		assertTrue(ast.getInstruction(1) instanceof CommandInstruction);
	}
	
}
