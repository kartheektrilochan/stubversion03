package com.hcl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.hcl.slc.stub.poc.clients.RoutingClient;
import com.hcl.slc.stub.poc.controller.AddressController;
import com.hcl.slc.stub.poc.entity.Address;


public class LoginController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/camelconfig/camelContext.xml");
	     
	      CamelContext camelContext = (CamelContext) context.getBean("camelContext");        
	      System.out.println(camelContext.getEndpoints()); 
		RoutingClient client=new RoutingClient();
		AddressController controller = new AddressController();
		List<Address> addresslist = controller.getFullDetails();
		client.sendToMQ(camelContext,addresslist, "QUEUE");
		return new ModelAndView("welcome","login","Welcome Kartheek");
	}

}
