package com.java8.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.java8.lambda.entrty.Employee;

/**
 * 一、Stream的三个操作步骤
 * 1.创建stream 
 * 2.中间操作
 * 3.终止操作(终端操作)
 * @author LDH
 */
public class TestStream1 {
	
	List<Employee> emps=Arrays.asList(
			new Employee(101, "张三", 18, 9999.99),
			new Employee(102, "李四", 59, 6666.66),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55),
			new Employee(105, "田七", 38, 5555.55),
			new Employee(105, "田七", 38, 5555.55),
			new Employee(105, "田七", 38, 5555.55)
			);
	
	
	//测试创建strema流的几种操作
	@Test
	public void test1() {
//		1.可以通过Collection系列集合提供的stream()或者parallelStream()
		List<String> list= new ArrayList<String>();
		//串行流
		Stream<String> stream = list.stream();
		//并行流
		Stream<String> parallelStream = list.parallelStream();

//		2.通过arrays中的静态方法stream()获取数组流
		Employee[] emps=new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(emps);
		
//		3.通过Stream类中的静态方法of()，该方法可以接收任意数量的参数
		Stream<String> stream3 = Stream.of("aa","bb","cc");
		
//		4.创建无限流
		Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);
		stream4.limit(10).forEach(System.out::println);
		System.out.println("--------------------------");
//		5.生成
		Stream.generate(() -> Math.random())
			  .limit(5)
			  .forEach(System.out::println);
	}
	//测试stream流中的一系列            中间操作
	/**
	 *筛选和切片
	 *filter 接收lambda，从流中排除某些元素
	 *limit————截断流，使其元素不超过给定的数量
	 *skip(n) ————跳过元素，返回一个扔掉了前N个元素的流。如果流中的元素不满足n个，则返回一个空流。与limit(n)进行互补
	 *distinct 筛选，通过流所生成的hashcode()和equals()去除重复元素
	 * 
	 */
	//内部迭代：迭代操作由Stream API完成
	@Test
	public void test2() {
		//中间操作，不会执行任何操作
		Stream<Employee>  stream= emps.stream().filter(e -> {
			System.out.println("Stream API的中间操作");
			return e.getAge()>35;
		});
		//终止操作，一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
		
	}
	@Test
	public void test3() {
		//测试limit方法
		emps.stream().filter(e -> {
			System.out.println("短路");
			return e.getSalary()>=5000;
		}).limit(2)
		.forEach(System.out::println);
		
	}
	@Test
	public void test4() {
		//测试skip方法和distinct方法
		emps.stream().filter(e -> {
			System.out.println("短路");
			return e.getSalary()>=5000;
		}).skip(2)
		.distinct()
		.forEach(System.out::println);
	}
	//中间操作：映射
	/**
	 * map ——接收lambda，将元素转换成其他形式或者提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
	 * flatMap——接受一个函数作为参数，将流中的每一个值都转换成另一个流，然后把所有流连接成一个流 
	 *
	 */
	@Test
	public void test5() {
		//测试map方法
		List<String> list=Arrays.asList("aaa","bbb","ccc","ddd","eee");
		list.stream().map((str) -> str.toUpperCase())
			.forEach(System.out::println);
		System.out.println("--------------------------");
		Stream<Employee> stream=emps.stream();
		Stream<String> map2 = stream.map(Employee::getName);
		map2.distinct().forEach(System.out::println);
		System.out.println("------------------------");
		System.out.println("---------测试map 和flatMap---------------");
		//测试map 和flatMap
		//区别：map方法会把aaa，bbb分别加到执行中间操作之后创建的新流中
		//flatMap会把 “aaa”拆分成三个新的元素a加入到新流中
		
		Stream<Stream<Character>> stream3=list.stream().map(TestStream1::filterCharter);
		stream3.forEach(sm -> sm.forEach(System.out::println));
		
		System.out.println("---------测试flatMap---------------");
		Stream<Character> sm = list.stream().flatMap(TestStream1::filterCharter);
		sm.forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharter(String str) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (Character character : str.toCharArray()) {
			list.add(character);
		}
		return list.stream();
	}
	//测试排序方法
	@Test
	public void test7() {
		List<String> list = Arrays.asList("ccc","aaa","bbb","ddd");
		list.stream()
			.sorted()//sorted无参方法：自然排序
			.forEach(System.out::printf);

		//定制化排序，在sorted接口中传入比较器实现类，进行排序
		emps.stream()
			.sorted((e1,e2) -> {
				if (e1.getAge()==e2.getAge()) {
					return e1.getName().compareTo(e2.getName());

				}else {
					return Integer.compare(e1.getAge(), e2.getAge());
				}
			});
	}
	
	
	
	
}
