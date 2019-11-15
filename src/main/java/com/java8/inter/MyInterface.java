package com.java8.inter;

public interface MyInterface {

	default String getName() {
		return "lidonghao";
	}
	
	public static void show() {
		System.out.println("接口中的静态方法");
		
	}
	
}
