package com.lorepo.icf.scripting.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TokenizerTestCase{

	@Test
	public void emptyCommand() {
		
		Token token;
		String script = "text2.Hide()";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(".", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("Hide", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals("(", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(")", token.getText());
	}


	@Test
	public void singleParamCommand() {
		
		Token token;
		String script = "text2.Hide(2)";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(".", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("Hide", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals("(", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof NumberToken);
		assertEquals("2", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(")", token.getText());
	}

	@Test
	public void twoParamsCommand() {
		
		Token token;
		String script = "text2.Hide(2, 'my text')";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(".", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("Hide", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals("(", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof NumberToken);
		assertEquals("2", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(",", token.getText());

		token = tokenizer.nextToken();
		assertTrue(token instanceof StringToken);
		assertEquals("my text", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(")", token.getText());
	}

	
	@Test
	public void countTokens() {
		
		Token token;
		String script = "text2.hide();text1.show()";
		Tokenizer tokenizer = new Tokenizer(script);
		
		int count = 0;
		while(tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			assertNotNull(token);
			count ++;
		}
		
		assertEquals(11, count);
	}

	
	@Test
	public void countTokens2() {
		
		Token token;
		String script = "text2.hide(\"a\")";
		Tokenizer tokenizer = new Tokenizer(script);
		
		int count = 0;
		while(tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			assertNotNull(token);
			count ++;
		}
		
		assertEquals(6, count);
	}

	
	@Test
	public void unserscore() {
		
		Token token;
		String script = "text_2.hide()";
		Tokenizer tokenizer = new Tokenizer(script);
		
		int count = 0;
		while(tokenizer.hasMoreTokens()){
			token = tokenizer.nextToken();
			assertNotNull(token);
			count ++;
		}
		
		assertEquals(5, count);
	}
	
	@Test
	public void varKeyword() {
		
		Token token;
		String script = "var index = text2.getPosition()";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof KeywordToken);
		assertEquals("var", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("index", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof OperatorToken);
		assertEquals("=", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());
	}

	
	@Test
	public void newLine() {
		
		Token token;
		String script = " a \n \t \r b";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertEquals("a", token.getText());
		
		token = tokenizer.nextToken();
		assertEquals("b", token.getText());
	}
	
	@Test
	public void trueKeyword() {
		
		Token token;
		String script = "text2.hide(True)";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());

		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(".", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("hide", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals("(", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof KeywordToken);
		assertEquals("True", token.getText());
		
	}
	
	@Test
	public void falseKeyword() {
		
		Token token;
		String script = "text2.hide(False)";
		Tokenizer tokenizer = new Tokenizer(script);
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("text2", token.getText());

		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals(".", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof IdentifierToken);
		assertEquals("hide", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof SymbolToken);
		assertEquals("(", token.getText());
		
		token = tokenizer.nextToken();
		assertTrue(token instanceof KeywordToken);
		assertEquals("False", token.getText());
		
	}
	
}
