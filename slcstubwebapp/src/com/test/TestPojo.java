package com.test;

import org.apache.camel.Exchange;
/**
 * @author Kartheek.Tk
 */
public class TestPojo {
	
	public void display(Exchange e)
	{
		System.out.println("called:"+e.getIn().getBody());
	}

}
