package com.lorepo.icf.scripting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.lorepo.icf.scripting.mockup.CommandReceiverMockup;

public class ScriptEngineTestCase{

	@Test
	public void checkCommand() throws ScriptParserException {
		
		String script = "text2.hide()";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		
		assertEquals("hide", receiver.getExecutedCommand());
	}


	@Test
	public void checkParams() throws ScriptParserException {
		
		String script = "text2.hide('abc')";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		assertEquals("hide", receiver.getExecutedCommand());
		
		List<String> params = receiver.getParams(); 
		assertNotNull(params);
		assertEquals(1, params.size());
		assertEquals("abc", params.get(0));
	}


	@Test
	public void assign() throws ScriptParserException {
		
		String script = "var a = text2.indexOf('abc')";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		assertEquals("indexof", receiver.getExecutedCommand());
		assertEquals("ok", engine.getSymbolMap().get("a"));		
	}
	
	@Test
	public void checkVariableAsParams() throws ScriptParserException {
		
		String script = " var a = text2.text();text2.hide(a)";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		assertEquals("hide", receiver.getExecutedCommand());
		
		List<String> params = receiver.getParams(); 
		assertNotNull(params);
		assertEquals(1, params.size());
		assertEquals("ok", params.get(0));
	}

	@Test
	public void checkVariableAsNotParams() throws ScriptParserException {
		
		String script = " var a = text2.text();text2.hide('a')";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		assertEquals("hide", receiver.getExecutedCommand());
		
		List<String> params = receiver.getParams(); 
		assertNotNull(params);
		assertEquals(1, params.size());
		assertEquals("a", params.get(0));
	}

	@Test
	public void newLineCharacter() throws ScriptParserException {
		
		String script = " var a = text2.text(); \ntext2.hide('a')";
		CommandReceiverMockup receiver = new CommandReceiverMockup("text2");
		ScriptingEngine engine = new ScriptingEngine();

		engine.addReceiver(receiver);
		engine.execute(script);
		
		assertEquals("hide", receiver.getExecutedCommand());
		
		List<String> params = receiver.getParams(); 
		assertNotNull(params);
		assertEquals(1, params.size());
		assertEquals("a", params.get(0));
	}

}
