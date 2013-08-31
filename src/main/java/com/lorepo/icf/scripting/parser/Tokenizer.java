/*
  The MIT License
  
  Copyright (c) 2009 smath project
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
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

/**
 * Returns tokens based on the list.
 * If character in input is not on the token list then it is returned as symbol.

 * @author Krzysztof Langner
 */
class Tokenizer {

	private static final String[] OPERATORS = {
		"+", "-", "*", "/",
		">=", "<=", "=", "!=", ">", "<"
	};

	private static final String[] SYMBOL = {
		"(", ")", ".", ",", ";"
	};
	
	private static final String[] KEYWORDS = {
		"var"
	};
	
	private final String  input;
	private int		index;
	private Token	pushbackToken;
	
	
	public Tokenizer(String in){
		
		input = in.trim();
		index = 0;
		pushbackToken = null;
	}

	
	/**
	 * Check if there are more tokens
	 */
	public boolean hasMoreTokens(){
		
		return (pushbackToken != null || index < input.length());
	}

	
	/**
	 * Return token and move pointer to the next one
	 */
	public Token nextToken(){
		
		Token 	token;
		String	in;
		
		if(pushbackToken != null){
			token = pushbackToken;
			pushbackToken = null;
			return token;
		}
			
		// Remove white spaces
		while(index < input.length() && isWhitespace(input.charAt(index))){
			index ++;
		}
			
		if(!hasMoreTokens()){
			return null;
		}
		
		in = input.substring(index);

		token = readOperator(in);
		
		if(token == null){
			token = readKeyword(in);
		}

		if(token == null){
			token = readSymbol(in);
		}

		if(token == null){
			token = readNumber(in);
		}

		if(token == null){
			token = readNumber(in);
		}

		if(token == null){
			token = readString(in);
		}

		if(token == null){
			token = readIdentifier(in);
		}

		return token;
	}
	
	
	private static boolean isWhitespace(char letter) {

		if(letter == ' ' || letter == '\n' || letter == '\r' || letter == '\t'){
			return true;
		}
		
		return false;
	}


	/**
	 * Cofniecie ostatnio pobranego tokena
	 * @param token
	 */
	public void pushback(Token token){
		pushbackToken = token;
	}
	

	private Token readKeyword(String in){
	
		for(String keyword : KEYWORDS){
			
			if(in.startsWith(keyword + " ")){
				index += keyword.length();
				return new KeywordToken(keyword);
			}
		}
		
		return null;
	}


	private Token readSymbol(String in){

		for(String bracket : SYMBOL){
			
			if(in.startsWith(bracket)){
				index += bracket.length();
				return new SymbolToken(bracket);
			}
		}
		
		return null;
	}
	
	
	/**
	 * Próbuje zintepretować dane wejściowe jako operator.
	 * Jeżeli nie ma operatora na wejściu to zwraca null
	 */
	private Token readOperator(String in){

		for(String op : OPERATORS){
			
			if(in.startsWith(op)){
				index += op.length();
				return new OperatorToken(op);
			}
		}
		
		return null;
	}
	
	/**
	 * Próbuje zintepretować dane wejściowe jako liczbe.
	 * Jeżeli nie ma liczby na wejściu to zwraca null
	 */
	private Token readNumber(String in){

		int i = 0;
		
		while( i < in.length() && 
				(Character.isDigit(in.charAt(i)) || in.charAt(i) == '.') )
		{
			i++;
		}
		
		if(i > 0){
			index += i;
			return new NumberToken(in.substring(0, i));
		}
		
		return null;
	}


	/**
	 * Simple string: 
	 * 'This is string'
	 */
	private Token readString(String in){

		Token token = null;
		
		if(in.charAt(0) == '\'' || in.charAt(0) == '\"'){
			Character ch = in.charAt(0);
			int i = 1;
			while( i < in.length() ){
				if(in.charAt(i) == ch){
					break;
				}
				i++;
			}
			
			String text = in.substring(1, i);
			token = new StringToken(text);
			index += i+1;
		}
		
		return token;
	}


	private Token readIdentifier(String in){
		
		int i = 0;
		Token token = null;
		
		while( i < in.length() )
		{
			Character ch = in.charAt(i);
			if(Character.isLetter(ch) || ch == '_'){
				i++;
			}
			else if(i > 0 && Character.isDigit(ch)){
				i++;
			}
			else{
				break;
			}
		}
		
		if(i > 0){
			index += i;
			String symbol = in.substring(0, i);
			token = new IdentifierToken(symbol);
		}
		
		return token;	
	}
}
