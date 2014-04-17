package com.lorepo.icf.undo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UndoTestCase{

	@Test
	public void initManager() {
		
		UndoManager undo = new UndoManager("init state");
		assertFalse(undo.canUndo());
		assertFalse(undo.canRedo());
		assertEquals(null, undo.undo());
		assertEquals(null, undo.redo());
	}

	@Test
	public void addSingleState() {
		
		UndoManager undo = new UndoManager("init state");
		undo.addState("op1", "value");
		assertTrue(undo.canUndo());
		assertFalse(undo.canRedo());
		assertEquals("op1", undo.getStateName());
		assertEquals("init state", undo.undo());
	}

	@Test
	public void undoAction() {
		
		UndoManager undo = new UndoManager("init state");
		undo.addState("op1", "state1");
		undo.addState("op2", "state2");
		assertEquals("op2", undo.getStateName());
		assertEquals("state1", undo.undo());
		assertEquals("op1", undo.getStateName());
	}

	@Test
	public void redoAction() {
		
		UndoManager undo = new UndoManager("init state");
		undo.addState("op1", "state1");
		undo.addState("op2", "state2");
		assertEquals("state1", undo.undo());
		assertEquals("state2", undo.redo());
	}

	@Test
	public void clearRedo() {
		
		UndoManager undo = new UndoManager("init state");
		undo.addState("op1", "state1");
		undo.addState("op2", "state2");
		assertEquals("state1", undo.undo());
		undo.addState("op3", "state3");
		assertFalse(undo.canRedo());
		assertEquals(null, undo.redo());
	}

	@Test
	public void undo2() {
		
		UndoManager undo = new UndoManager("init state");
		undo.addState("op1", "state1");
		undo.addState("op2", "state2");
		assertEquals("state1", undo.undo());
		undo.addState("op3", "state3");
		assertEquals("state1", undo.undo());
	}

}
