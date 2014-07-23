package com.hcl.slc.stub.poc.clients;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.DynaProperty;

import com.hcl.slc.stub.poc.base.util.ConfigurationUtil;
import com.hcl.slc.stub.poc.controller.StubparamController;
import com.hcl.slc.stub.poc.dynamic.DynamicBeanCreator;
import com.hcl.slc.stub.poc.dynamic.DynamicPropertyCreator;
import com.hcl.slc.stub.poc.dynamic.FieldSetterGetterBean;
import com.hcl.slc.stub.poc.entity.Address;
import com.hcl.slc.stub.poc.entity.Stubparamconfig;
import com.hcl.slc.stub.poc.service.AddressService;

public class DataGenClient {

	/**
	 * @param args
	 * @author Kartheek.Tk
	 */
	public static void main(String[] args) {
		try {
			
			/*Reading Configurations*/
			ConfigurationUtil util=new ConfigurationUtil();
			Properties prop=util.readConfigurations();
			String destination = (String) prop.get("destination");
			System.out.println("Destination:" + destination);
			int records_no=Integer.parseInt((String)prop.get("records"));
			/*Reading the stubparam table configurations*/
			StubparamController bean = new StubparamController();
			List<Stubparamconfig> stublist = bean.getFullDetails();

			DynamicPropertyCreator propcreator = new DynamicPropertyCreator();
			DynaProperty[] dynaprop = propcreator
					.createDynamicProperty(stublist);

			DynamicBeanCreator beancreator = new DynamicBeanCreator();
			Object obj =null; 
			FieldSetterGetterBean fieldsetterbean = new FieldSetterGetterBean();
			List<Address> addresslist = new ArrayList<Address>();
			for(int i=0;i<records_no;i++)
			{
				obj=beancreator.createDymanicBean(dynaprop, stublist);
				Address address = (Address) fieldsetterbean.assignValues2Fields(obj, stublist);
				addresslist.add(address);
			}	
			System.out.println("Total no of records created:"+addresslist.size());
			
			AddressService service=new AddressService();
			for(Address a:addresslist)
			{
			//	service.save(a);
			}
			/*CamelClient client=new CamelClient();
			client.sendToMQ(addresslist, destination);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
