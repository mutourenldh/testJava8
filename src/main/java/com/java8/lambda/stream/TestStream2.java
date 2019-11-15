package com.java8.lambda.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.java8.lambda.entrty.Employee;
import com.java8.lambda.entrty.Employee.Status;

/*
  	测试流的终止操作
 	查找和匹配
 	allMatch（Predicate p）———检查是否匹配所有元素
 	anyMatch(Predicate p)———检查是否至少匹配一个元素
 	noneMatch(Predicate p)————检查是否没有匹配所有元素
 	findFirst————返回第一个元素
 	findAny————返回当前流中的任意元素
 	count————返回流中元素的总个数
 	max(Comparator c)————返回流中的最大值
 	min(Comparator c)—————返回流中的最小值
 	forEach(Consumer c) 内部迭代，使用Collection接口需要用户自己去做迭代，成为外部迭代，相反
 	Stream Api使用内部迭代。它帮你把迭代做了
 	注意：流进行了终止操作后，不能再次使用
 */
public class TestStream2 {
	
	List<Employee> emps=Arrays.asList(
			new Employee(101, "张三", 18, 9999.99,Status.BUSY),
			new Employee(102, "李四", 59, 6666.66,Status.FREE),
			new Employee(103, "王五", 28, 3333.33,Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77,Status.BUSY),
			new Employee(105, "田七", 38, 5555.55,Status.FREE)
			);
	@Test
	public void test1() {
		//测试allMatch方法
		boolean bl1 = emps.stream()
			.allMatch((e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(bl1);
		//anyMatch
		boolean anyMatch = emps.stream().anyMatch(e -> e.getStatus().equals(Status.FREE));
		System.out.println(anyMatch);
		//noneMatch
		boolean noneMatch = emps.stream().noneMatch(e -> e.getStatus().equals(Status.VOCATION));
		System.out.println(noneMatch);
		//findFirst
		Optional<Employee> findFirst = emps.stream()
										.sorted((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
										.findFirst();
		System.out.println(findFirst.get());
		//findAny
		Optional<Employee> findAny = emps.stream().filter(e1 -> e1.getAge()>30)
				.findAny();
		System.out.println(findAny.get());
		//count方法
		long count = emps.stream().filter(e-> e.getStatus().equals(Status.FREE)).count();
		System.out.println(count);
		//max方法   返回流中的最大值
		Optional<Employee> max = emps.stream().max((e1,e2) -> Integer.compare(e1.getAge(),e2.getAge()));
		System.out.println(max.get());
		//min方法 返回流中的最小值
		Optional<Double> max2 = emps.stream().map(Employee::getSalary).min(Double::compareTo);
		System.out.println(max2.get());
	
	}
	/**
	 * 归约
	 * 测试reduce方法(T identity,BinaryOperator)/reduce(BinaryOperator)
	 * ——可以将流中的元素反复结合起来得到一个值
	 */
	@Test
	public void test3() {

		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		//将list中的元素进行相加
		Optional<Integer> reduce = list.stream().reduce((x,y)-> x+y);
		System.out.println(reduce.get());
		
		//10为起始值，从10开始，加上list中的所有元素
		Integer reduce2 = list.stream().reduce(10,(x,y)-> x+y);
		System.out.println(reduce2);
		
		Optional<Double> reduce3 = emps.stream().map(Employee::getSalary)
					 .reduce(Double::sum);
		System.out.println(reduce3.get());
	}
	
	/**
	 * collect  收集
	 * 将流装换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test4() {
		//将emps集合数据收集到list集合中
		List<String> list = emps.stream().map(Employee::getName)
											.collect(Collectors.toList());
		list.forEach(System.out::printf);
		
		System.out.println("-----------------------------------------");
		//将emps集合数据收集到set集合中
		Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());
		set.forEach(System.out::println);
		
		System.out.println("---------------------------");
		//将emps集合数据收集到指定的集合中
		HashSet<String> collect = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
		collect.forEach(System.out::printf);
	}
	/**
	 * collect  收集
	 * 将流装换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test5() {
		//求流中的元素的总数
		Long count = emps.stream().collect(Collectors.counting());
		System.out.println(count);
		//平均数
		Double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);
		//求和
		Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		//summarizingDouble方法将平均数，总数，最大值，最小值都查出来了
		DoubleSummaryStatistics collect = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(collect.getSum());
	}
	/**
	 * collect  收集
	 * 将流装换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test6() {
		//测试收集最大值的操作
		Optional<Double> maxBy = emps.stream().map(Employee::getSalary)
					.collect(Collectors.maxBy(Double::compare));
		System.out.println(maxBy.get());
		
		Optional<Employee> collect = emps.stream()
		.collect(Collectors.maxBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary())));
		System.out.println(collect.get());
		
		Optional<Employee> collect2 = emps.stream().collect(Collectors.minBy((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(collect2.get());
		//测试收集最小值的操作
		Optional<Double> opMinBy = emps.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
		System.out.println(opMinBy.get());
	}
	//collect  收集：将流转换为其他形式，接受一个Collector接口的实现，用于给Stream中做元素汇总的方法
	//java8给Collector内置了一个接口实现Collectors,在这个接口中内置了很多方法，可以供我们方便调用
	//分组
	@Test
	public void test7() {
//		groupingBy中函数式接口实现要求一个参数，一个返回值
		Map<Status, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(e->e.getStatus()));
		System.out.println(collect);
		//多级分组1
		Map<Status, Map<Boolean, List<Employee>>> collect2 = emps.stream().
				collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> e.getSalary()>8000)));
		System.out.println(collect2);
		System.out.println("--------------------------");
		//多级分组2
		Map<Status, Map<String, List<Employee>>> collect3 = emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
			if (e.getAge() >=70) {
				return "老年";
			}else if(e.getAge() >=35) {
				return "中年";
			}else {
				return "少年";
			}
		})));
		System.out.println(collect3);
		
	}
	
	//collect 收集：将流转换为其他形式，接收一个Collectors接口的实现，用于给Stream中元素做汇总的方法
//	分区
	@Test
	public void test8() {
		Map<Boolean, List<Employee>> collect = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary()>7000));
		System.out.println(collect);
	}
	
	//collect 收集：将流转换为其他形式，接收一个Collectors接口的实现，用于给Stream中元素做汇总的方法
//	测试join方法
	@Test
	public void test10() {
		String collect = emps.stream()
		.map(Employee::getName)
		.collect(Collectors.joining());
		String collect1 = emps.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(","));
		String collect2 = emps.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(",","--","==="));
		System.err.println(collect);
		System.err.println(collect1);
		System.err.println(collect2);
	}
	//测试流在进行了终止操作之后就不可以继续使用
	@Test
	public void test2() {
		
		Stream<Employee> stream = emps.stream()
				 .filter((e) -> e.getStatus().equals(Status.FREE));
				
				long count = stream.count();
				
				stream.map(Employee::getSalary)
					.max(Double::compare);
	}
}
