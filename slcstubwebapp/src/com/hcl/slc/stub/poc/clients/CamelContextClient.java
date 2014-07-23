package com.hcl.slc.stub.poc.clients;

import java.io.InputStream;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kartheek.Tk
 */

public class CamelContextClient {
	   public static void main(String[] args) throws Exception {
	      AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/camelconfig/camelContext.xml");
	      System.out.println(">>>>>>>>>>>>>>>>>>>Starting the Camel Container<<<<<<<<<<<<<<<<<<<<");
	      Main m=new Main();
	      m.setApplicationContext(context);
	      m.run(args);
	   }
	}