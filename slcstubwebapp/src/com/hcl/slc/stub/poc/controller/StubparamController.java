package com.hcl.slc.stub.poc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hcl.slc.stub.poc.entity.Stubparamconfig;
import com.hcl.slc.stub.poc.service.StubparamService;

/**
 * @author Kartheek.Tk
 */
public class StubparamController {

		private StubparamService service;
		
		public StubparamController(){
			super();
			service=new StubparamService();
		}
		
		public List<Stubparamconfig> getFullDetails()
		{
			List<Stubparamconfig> stublist=service.findAll();
			return stublist;
		}
		
		public List<Stubparamconfig> getSuitableDetails()
		{
			Map<Integer, Object> inputMap=new HashMap<Integer, Object>();
			inputMap.put(1, "ACCOUNT");
			//List<Stubparamconfig> stublist=service.findAllBySQLQuery("SELECT * FROM STUBFRAMEWORK.STUB_PARAMCONFIG", inputMap,Stubparamconfig.class);
			List<Stubparamconfig> stublist=service.findAllByNamedSQLQuery("Stubparamconfig.findbytablename", inputMap,Stubparamconfig.class);
			return stublist;
		}
		public static void main(String[] args) {
			StubparamController controller=new StubparamController();
			List<Stubparamconfig> stubparamconfiglist=controller.getFullDetails();
			List<Stubparamconfig> stublist1=controller.getSuitableDetails();
			System.out.println(stublist1.size());
			System.out.println(stubparamconfiglist.size());
		}




}
