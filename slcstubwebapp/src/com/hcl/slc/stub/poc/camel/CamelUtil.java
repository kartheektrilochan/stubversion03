package com.hcl.slc.stub.poc.camel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.hcl.slc.stub.poc.entity.Address;

/**
 * @author Kartheek.Tk
 */
public class CamelUtil {

	public void sendToRoutes(CamelContext camelcontext,List<Address> cmdlist, String destination) throws Exception {
		
		/*The below lines are send a message directly to Queue*/
		/*uncomment this if you need to send a message directly without using camel-context.xml*/
		
		/*CamelContext context = new DefaultCamelContext();
		ConnectionFactory connection = new ActiveMQConnectionFactory("tcp://localhost:61616");
		context.addComponent("slc-jms", JmsComponent.jmsComponentAutoAcknowledge(connection));
		context.addRoutes(new Router());
		*/
		System.out.println("context started");
		CamelContext context =camelcontext;
		context.start();
		context.addRoutes(new Router());
		ProducerTemplate temp = context.createProducerTemplate();
		System.out.println(">>>>>>>>>>>>>Available EndPoints:"+context.getEndpoints());
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("destination",destination);
		/*temp.asyncSendBody("direct:startRq", cmdlist);*/
		temp.asyncRequestBodyAndHeaders("direct:startpoint",cmdlist,properties);
		ConsumerTemplate cons = context.createConsumerTemplate();
		cons.start();
		Thread.sleep(2000);
		System.out.println("Context stopped");
		context.stop();
	}

}
