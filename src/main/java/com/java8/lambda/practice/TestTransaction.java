package com.java8.lambda.practice;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.ldap.Rdn;

import org.junit.Before;
import org.junit.Test;

public class TestTransaction {
	
	
	List<Transaction> list = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		list = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
	}
	////1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
	@Test
	public void test1() {
		list.stream()
			.filter(tra-> tra.getYear()==2011)
			.sorted((t1,t2) -> Integer.compare(t1.getValue(), t2.getValue()))
			.forEach(System.out::println);
	}
	//2. 交易员都在哪些不同的城市工作过？(把城市名字集合写出来，去重)
	@Test
	public void test2() {
		list.stream().map(t -> t.getTrader().getCity())
					.distinct()
					 .forEach(System.out::println);
		
	}
	//3. 查找所有来自剑桥的交易员，并按姓名排序
	@Test
	public void test3() {
		list.stream()
			.filter(t-> t.getTrader().getCity().equals("Cambridge"))
			.map(t->t.getTrader().getName())
			.distinct()
			.sorted()
			.forEach(System.out::println);
		
		
	}
	//4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4() {
		list.stream().map(Transaction::getTrader)
					.map(Trader::getName)
					.sorted()
					.distinct()
					.sorted()
					.forEach(System.out::println);
		System.out.println("------------------");
		String reduce = list.stream().map(Transaction::getTrader)
					 .map(Trader::getName)
					 .sorted()
					 .reduce("",(s1,s2)->s1.concat(s2));
		System.out.println(reduce);
		System.out.println("----------------------");
		list.stream().map(t->t.getTrader().getName())
					 .flatMap(TestTransaction::StringToChar)
					 .map(t -> t.toLowerCase())
					 .distinct()
					 .sorted()
					 .forEach(System.out::printf);
		System.out.println("----------------------");
		list.stream().map(t->t.getTrader().getName())
					 .flatMap(TestTransaction::StringToChar)
					 .sorted((s1,s2) -> s1.compareToIgnoreCase(s2))
					 .forEach(System.out::printf);
	}
	
	public static Stream<String> StringToChar(String str) {
		
		List<String> list=new ArrayList<String>();
		for (Character character : str.toCharArray()) {
			list.add(character.toString());
		}
		return list.stream();
	}
	
	//5 有没有交易员是在米兰工作的
	@Test
	public void test5() {
		boolean match = list.stream()
			.anyMatch(t->t.getTrader().getCity().equals("Milan"));
		System.out.println(match);
	}
	//6. 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test6() {
		Optional<Integer> reduce = list.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.reduce(Integer::sum);
		System.out.println(reduce.get());
		
		
		 Integer collect = list.stream()
		.filter(t -> t.getTrader().getCity().equals("Cambridge"))
		.collect(Collectors.summingInt(Transaction::getValue));
		System.out.println(collect);
	}
	
	//7. 所有交易中，最高的交易额是多少
	@Test
	public void test7() {
		Optional<Integer> findFirst = list.stream()
				.map(Transaction::getValue).max(Integer::compare);
		System.out.println(findFirst.get());
	}
	
	//8.找到交易额最小的交易
	@Test
	public void test8() {
		Optional<Transaction> min = list.stream().min((t1,t2) -> Integer.compare(t1.getValue(), t2.getValue()));
		System.out.println(min.get());
	}
}
