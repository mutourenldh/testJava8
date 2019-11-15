package com.java8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.java8.lambda.entrty.Employee;

/**
 * 方法引用：如果Lambda方法体中的内容已经有方法实现了，我们可以使用"方法引用"
 * 我们可以把它理解成为Lambda表达式的另一种实现形式
 * 主要的语法格式有三种形式：
 * 1.对象::实例方法名
 * 
 * 2.类::静态方法名
 * 
 * 3.类::实例方法名
 * 
 * 方法引用中需要注意的问题：1.lambda体中调用方法的参数列表和返回值类型需要和函数式接口
 * 中的抽象方法的参数列表和返回值类型保持一致
 * 
 * 
 * 
 * @author LDH
 *
 */
public class TestMethodRef {
	//测试方法引用中的对象::实例方法名
	@Test
	public void test1() {
//		消费型接口：有一个参数，但是没有返回值
		Consumer<String> com = (x) -> System.out.println(x);
		com.accept("123");
		//打印语句实际上是调用了PrintStream中的println方法
		PrintStream ps=System.out;
		Consumer<String> com1=ps::println;
		com1.accept("倩倩");
	}
	@Test
	public void test2() {
		Employee employee = new Employee("123");
		Supplier<String> su= () -> employee.getName();
		System.out.println(su.get());
		
		
		Supplier<String> sup= employee::getName;
		String name=sup.get();
		System.out.println(name);
		
	}
	
	
	
	//测试方法引用中的   类::静态方法名
	@Test
	public void test3() {
		//lambda体中内容已经有方法实现了，并且该方法是静态方法，那么我们可以使用
//		类::方法名的方式来进行方法引用
		Comparator<Integer> com=(x,y) -> Integer.compare(x, y);
		int a=com.compare(2, 3);
		System.out.println(a);
		
		Comparator<Integer> com1=Integer::compare;
		int b=com1.compare(4, 5);
		System.out.println(b);
	}
	//这种情况使用需要有一定的条件。即如果参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用  类名  ：：方法名这种格式的方法应用
	@Test//测试方法引用中的        类：实例方法名
	public void test4() {
		BiPredicate<String,String> bi=(x,y) -> x.equals(y);
		boolean test = bi.test("aa","bb");
		System.out.println(test);
		BiPredicate<String, String> bp=String::equals;
		boolean test2 = bp.test("mm", "n");
		System.out.println(test2);
	}
	
	/**
	 * 构造器引用
	 * 格式：  类名：new
	 */
	@Test
	public void test5() {
		Supplier<Employee> sup=() -> new Employee();
		Supplier<Employee> su1=Employee::new;
		Employee emp=su1.get();
		System.out.println(emp);
		
		Function<String, Employee> fnc=(x) -> new Employee(x);
		Function<String, Employee> fnc1=Employee::new;
		Employee emp1=fnc1.apply("小李");
		System.out.println(emp1);
		
		BiFunction<String, Integer, Employee> bf=Employee::new;
		Employee emp2=bf.apply("倩倩", 10);
		System.out.println(emp2);
	}
	/**
	 * 数组引用
	 * Type :: new 
	 */
	@Test
	public void test7() {
		
		Function<Integer, String[]> fun = (x) -> new String[x];
		String[] string1 = fun.apply(10);
		System.out.println(string1.length);
		
		Function<Integer, String[]> fun1 = String[]::new ;
		String[] string2 = fun1.apply(20);
		System.out.println(string2.length);
	}
	
}
