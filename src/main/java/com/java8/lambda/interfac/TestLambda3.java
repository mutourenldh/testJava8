package com.java8.lambda.interfac;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * java8中内置了四大核心函数式接口
 * Consumer<T> 		消费型接口
 * 		void accept(T t)
 * Supplier<T> 		供给型接口
 * 		T  get();
 * Function<T,R>	函数型接口
 * 		R	apply(T,t)
 * Predicate<T>		断言型接口
 * 		boolean test(T t)
 * 
 * 
 * @author LDH
 *
 */
public class TestLambda3 {
	//1.测试消费型接口：一个参数，无返回值
	@Test
	public void testConsumer() {
		happy(100, x -> System.out.println("吃饭一共花了"+x+"元钱！"));
	}
	
	public void happy(double money,Consumer<Double> com) {
		com.accept(money);
	}
	//2.测试供给型接口，无参，有返回值
	@Test
	public void testSupplier() {
		List<Integer> list=getNumList(5, () -> (int)(Math.random()*100));
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
	
	//需求：产生指定个数的整数，并且放入集合中
	public List<Integer> getNumList(Integer num,Supplier<Integer> sup){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < num; i++) {
			Integer integer = sup.get();
			list.add(integer);
			
		}
		return list;
	}
//	3.测试函数型接口 声明两个参数，一个是入参，一个是返回值
	public String dealStr(String str,Function<String, String> func) {
		return func.apply(str);
	}
	@Test
	public void name() {
		String dealStr = dealStr("abcdefg", (str) -> str.substring(1,5));
		System.out.println(dealStr);
		String dealStr2 = dealStr("\t\t\t李东浩喜欢刘倩倩      ", (str) -> str.trim());
		System.out.println(dealStr2);
	}
//	4.测试断言型接口
//	需求：判断一个字符换集合中的每个字符串的长度，返回字符串长度大于2的字符串集合
	public List<String> filterStr(List<String> list,Predicate<String> pre){
		
		ArrayList<String> list2 = new ArrayList<String>();
		for (String string : list) {
			if (pre.test(string)) {
				list2.add(string);
			}
		}
		return list2;
	}
	@Test
	private void name3() {
		List<String> list = new ArrayList<String>();
		list.add("aaaa");
		list.add("bb");
		list.add("ccc");
		list.add("ddd");
		
		List<String> filterStr = filterStr(list, (s) -> s.length()>2);
		for (String string : filterStr) {
			System.out.println(string);
		}
	}

}
