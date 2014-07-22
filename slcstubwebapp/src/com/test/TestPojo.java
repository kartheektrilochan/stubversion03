package com.test;

import org.apache.camel.Exchange;

public class TestPojo {
	
	public void display(Exchange e)
	{
		System.out.println("called:"+e.getIn().getBody());
	}

}
