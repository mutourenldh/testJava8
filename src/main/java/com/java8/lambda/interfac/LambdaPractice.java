package com.java8.lambda.interfac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.java8.lambda.entrty.Employee;

public class LambdaPractice {
	
	
	
	public String doPractice(String string,MyPractice my) {
		return my.getValue(string);
	}
	@Test
	public void name() {
		String doPractice = doPractice("ackfas", x -> x.toUpperCase());
		System.out.println(doPractice);
		
		
		String doPractice2 = doPractice("afsdfa",x -> x.substring(2,5));
		System.out.println(doPractice2);
	}
}
