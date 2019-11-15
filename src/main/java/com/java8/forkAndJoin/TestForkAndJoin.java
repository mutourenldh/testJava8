package com.java8.forkAndJoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

import org.junit.Test;


public class TestForkAndJoin {
	@Test
	public void test3(){
		Instant startInstant = Instant.now();
		long start = System.currentTimeMillis();
		//range，需要传入开始节点和结束节点两个参数，返回的是一个有序的LongStream。包含开始节点和结束节点两个参数之间所有的参数，间隔为1.
//		 rangeClosed的功能和range类似。
		//两者的区别：rangeClosed包含最后的节点，但是range不包含最后的节点
		//并行流就是把一个内容分成多个数据块，并且用不同 的线程分别处理每个数据块的流
		//java8中我们可以申明性地通过parallel()和sequential()在并行流和顺序流之间进行切换
		Long sum = LongStream.rangeClosed(0L, 100000000L)
							 .parallel()//并行计算
							 .sequential()//串行计算
							 .sum();
		System.out.println(sum);
		Instant endInstant = Instant.now();
		long end = System.currentTimeMillis();
		System.out.println("耗费的时间为: " + Duration.between(startInstant, endInstant).toMillis()); //2061-2053-2086-18926
	}

	@Test
	public void test4(){
//		rangeClosed包含最后的节点
		LongStream rangeClosed = LongStream.rangeClosed(2L, 5L);
		rangeClosed.forEach(System.out::print);
		System.out.println("------------------\n");
		//range不包含最后的节点
		LongStream range = LongStream.range(2L, 5L);
		range.forEach(System.out::print);
	}
}
