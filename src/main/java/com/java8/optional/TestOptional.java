package com.java8.optional;

import java.util.Optional;

import org.junit.Test;

import com.java8.lambda.entrty.Employee;

//Optional<T>是一个容器类，代表一个值存在或者不存在。
//之前用null来表示一个值是不是存在，现在我们可以通过Optional<T>来更好的 表示这个概念，并且可以避免空指针异常
public class TestOptional {
	@Test
	public void test1() {
//		//1.of方法，创建一个optional实例
//		Optional<String> opt1 = Optional.of("123");
//		System.out.println(opt1.get());
////		2.empty()方法，创建一个空的optional实例
		Optional<Employee> opt2 = Optional.empty();
//		System.out.println(opt2.get());
//		//3.ofNullable<T t>方法  如果t不为空，则创建optional实例，否则创建空实例 
		Optional<String> opt3 = Optional.ofNullable("456");
//		System.out.println(opt3.get());
////		4.isPresent()方法判断是否包含值
//		System.out.println(opt3.isPresent());
//		System.out.println(opt2.isPresent());
		
		//orElse(T t) 如果调用对象包含值，则返回该值。否则返回t
		Employee orElse = opt2.orElse(new Employee());
		System.out.println(orElse);
		
		//orElseGet(Supplier s) 如果调用对象包含值，则返回该值，否则返回s获取的值
		Employee orElseGet = opt2.orElseGet(() -> new Employee("123"));
		System.out.println(orElseGet);
//		map(Function f)如果有值对其进行处理，并返回处理后的optional,否则返回Optional.empty()
		
//		flatMap(Function mapper) 与map类似，要求返回的值必须是Optional
	}
	
	
	@Test
	public void test2() {
		//1.of方法，创建一个optional实例
		Optional<Employee> ofNullable = Optional.ofNullable(new Employee("倩宝贝", 18));
//		map(Function f)如果有值对其进行处理，并返回处理后的optional,否则返回Optional.empty()
//		Optional<String> map = ofNullable.map(e -> e.getName());
//		System.out.println(map.get());

//		flatMap(Function mapper) 与map类似，要求返回的值必须是Optional
		Optional<String> flatMap = ofNullable.flatMap(e -> Optional.of(e.getName()));
		System.out.println(flatMap.get());
	}
	

}
