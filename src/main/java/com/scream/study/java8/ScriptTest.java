package com.scream.study.java8;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptTest {
	@Test
	public void script() throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		System.out.println(engine.getClass().getName());
		System.out.println("result:" + engine.eval("function f() {return 1};f()+1"));
	}
}
