package com.hcl.slc.stub.poc.clients;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.camel.CamelContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hcl.slc.stub.poc.camel.CamelUtil;
import com.hcl.slc.stub.poc.controller.AddressController;
import com.hcl.slc.stub.poc.entity.Address;

public class RoutingClient {

	public static void main(String[] args) {
		try {
			/*Reading the property files*/
			RoutingClient client = new RoutingClient();
			FileInputStream propfile = new FileInputStream("config\\config.properties");
			Properties prop = new Properties();
			prop.load(propfile);
			String destination = (String) prop.get("destination");
			System.out.println("Destination:" + destination);
			
			/*Reading the camel-context.xml file*/
			
			AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/camelconfig/camelContext.xml");
			CamelContext camelContext = (CamelContext) context.getBean("camelContext");  
			
			//CamelClient camelclient=new CamelClient();
			//camelclient.sendToMQ(camelContext, "hi", "QUEUE");
			System.out.println("called complete");
		
			AddressController controller = new AddressController();
			List<Address> addresslist = controller.getFullDetails();
			client.sendToMQ(camelContext,addresslist, destination);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendToMQ(CamelContext camelContext,List<Address> addresslist, String destination) {
		try {
			CamelUtil util = new CamelUtil();
			util.sendToRoutes(camelContext,addresslist, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
