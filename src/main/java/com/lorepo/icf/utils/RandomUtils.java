/*
  The MIT License
  
  Copyright (c) 2009 Krzysztof Langner
  
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
package com.lorepo.icf.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class RandomUtils {

	/**
	 * Helper function for getting random permutation from 0 to (size-1) numbers
	 */
	public static List<Integer> singlePermutation(int size){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < size; i ++){
			list.add(i);
		}
		
		RandomUtils.shuffle(list);
		return list;
	}
	
	
	/**
	 * Shuffle list. This function guarantees that  the random order is different 
	 * then starting order
	 */
	public static List<?> shuffle(List<?> list){

		for(int index = 0; index < list.size()-1; index += 1) {  
			int start = index;
		    Collections.swap(list, index, start + random.nextInt(list.size() - start));
		}		
		
		return list;
	}
	
	// we shouldn't recreate Random every time we need to use shuffle() function, but reuse it - 
	// it will give much more random results
	final static private Random random = new Random(System.currentTimeMillis());
}
