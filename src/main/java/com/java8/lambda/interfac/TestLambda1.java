package com.java8.lambda.interfac;

import org.junit.Test;

public class TestLambda1 {
	/**
	 * 首先定义一个函数式接口，然后将接口作为参数传进方法中。在方法中调用接口的抽象方法
	 * 在实际调用该方法的时候，传入的参数实际上是我们通过lambda实现的函数式接口实现
	 * @param num
	 * @param mf
	 * @return
	 */
	public Integer operation(Integer num,MyFunction mf) {
		return mf.getValue(num);
	}
	
	@Test
	public void name() {
		Integer operation = operation(100, (x) -> x*x);
		
		Integer operation2 = operation(2, x -> x+4);
		System.out.println(operation);
		System.out.println(operation2);
	}
}
