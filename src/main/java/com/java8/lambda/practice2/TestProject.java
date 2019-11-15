package com.java8.lambda.practice2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestProject {

	List<OrderItem> items = Arrays.asList(
			new OrderItem(1, "   1    "),
			new OrderItem(2, "          "),
			new OrderItem(3, "   3    "), 
			new OrderItem(4, "   4    "), 
			new OrderItem(5, "   5    "),
			new OrderItem(6, "   6    "), 
			new OrderItem(7, "   7    ")
			);

	@Test
	public void test1() {
		
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		String regEx3 = "[0-9]";
		  List<OrderItem> collect = items.stream()
					.filter(orderItem -> orderItem.getPriceUnit()!=null&&orderItem.getPriceUnit().length()>0)
					.filter(orderItem -> !pattern.matcher(orderItem.getPriceUnit().trim()).matches())
					.map(orderItem -> {
						String aa=orderItem.getPriceUnit().trim();
						aa=matchResult(Pattern.compile(regEx3), aa);
						orderItem.setPriceUnit(aa);
						return orderItem;
					})
					.collect(Collectors.toList());
		  System.out.println(collect);
	}
	
	 public static String matchResult(Pattern p,String str){
	        StringBuilder sb = new StringBuilder();
	        Matcher m = p.matcher(str);
	        while (m.find())
	        for (int i = 0; i <= m.groupCount(); i++)
	        {
	            sb.append(m.group());   
	        }
	        return sb.toString();
	    }

}
