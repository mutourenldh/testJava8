package com.java8.lambda.interfac;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * lambda表达式的各种实现形式
 * @author LDH
 * 一、java8中引入了一个新的操作符"->",该操作符称为箭头操作符或者lambda操作符。
 * 箭头操作符将lambda表达式分成了两部分
 * 左侧：lambda表达式的参数列表
 * 右侧：lambda表达式中所需要执行的功能，即lambda体。
 * 二、lambda表达式的变体
 * 1.无参无返回值
 * () -> System.out.println("lambda表达式的第一种形式");
 * 
 *
 */
public class TestLambda2 {
	/**
	 *  1.无参无返回值
	 * 	() -> System.out.println("lambda表达式的第一种形式");
	 */
	@Test
	public void test1() {
		Runnable runnable = ()-> System.out.println("lambda表达式的第一种形式");
		runnable.run();
	}
	/**
	 * 2.有一个参数，无返回值,
	 * (x) -> System.out.println(x);
	 * 	如果只有一个参数，则参数列表中的小括号可以省略不写
	 * x -> System.out.println(x);
	 */
	@Test
	public void test2() {
		Consumer<String> comConsumer=(x) -> System.out.println(x);
		comConsumer.accept("第二种形式");
		Consumer<String> aaConsumer= x -> System.out.println(x);
		aaConsumer.accept("123");
	}
	/**
	 * 3.有两个参数，并且有多条实现语句，且有返回值
	 */
	@Test
	public void test3() {
//		//有两个参数，并且有多条实现语句，切有返回值
		Comparator<Integer> aComparator=(x,y) -> {
			System.out.println(x);
			System.out.println(y);
			return Integer.compare(x, y);
		};
		int compare = aComparator.compare(1, 2);
		System.out.println(compare);
		System.out.println("------------------------------");
		//有两个参数，但是只有一条实现语句的时候，lambda表达式实现体中的大括号{}和return可以省略不写
		Comparator<Integer> bComparator=(x,y) ->  Integer.compare(x, y);
		int compare2 = bComparator.compare(1, 2);
		System.out.println(compare2);
	}
	@Test
	public void test4() {
		//lambda表达式中参数列表中的类型可以省略不写，因为jvm编译器可以通过上下文推断得出，称之为类型推断
		Comparator<Integer> comparator= (Integer x,Integer y) -> Integer.compare(x, y);
		int compare = comparator.compare(4, 3);
		System.out.println(compare);
	}
	
	//需求：对于两个Long型的数据进行处理
	public void op11(Long l1,Long l2,MyFunction2< Long, Long> mf) {
		System.out.println(mf.getValue(l1, l2));
		
	}
	@Test
	public void name() {
		op11(100L, 200L, (x,y) -> x+y);
		
		op11(100L,200L, (x,y) -> x*y);
		
	}
	

}
