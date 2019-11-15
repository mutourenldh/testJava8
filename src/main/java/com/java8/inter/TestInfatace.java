package com.java8.inter;

import org.junit.Test;

/**
 * 类优先原则：
 * 1.如果一个类，既继承了父类A，又实现了接口B，并且父类A和接口B中有一个同名方法，则调用该类中的这个方法时，
 * 实际调用的是父类中的该实现方法。接口中的同名方法会被忽略
 * 2.如果一个类实现了两个接口A和B，并且A和B中有一个同名和同参的方法，则在该类中必须覆盖这个方法
 * 来解决冲突
 *
 */
public class TestInfatace {
	
	@Test
	public void test1() {
		//调用接口中的默认方法
		MyClass myClass = new MyClass();
		String name = myClass.getName();
		System.out.println(name);
		
		MyInterface.show();
	}

}
