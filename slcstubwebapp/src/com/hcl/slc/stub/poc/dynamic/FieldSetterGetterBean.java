package com.hcl.slc.stub.poc.dynamic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.hcl.slc.stub.poc.entity.Address;
import com.hcl.slc.stub.poc.entity.Stubparamconfig;
/**
 * @author Kartheek.Tk
 */

public class FieldSetterGetterBean {
	static Logger logger = Logger.getLogger(FieldSetterGetterBean.class);

	public Object assignValues2Fields(Object obj,List<Stubparamconfig> stublist,Class clstype) {
		try {

			Class cls = clstype;
			Object target_obj = clstype.newInstance();
			Field[] fields = cls.getDeclaredFields();
			logger.info("fields.length:" + fields.length);
			System.out.println("fields.length:" + fields.length);
			/*
			 * Search for the available fields from the database and assign it
			 * to the fields
			 */
			/*for(int p=0;p<fields.length;p++)
			{
				for (Stubparamconfig s : stublist)
				{
						System.out.println(">>>>"+BeanUtils.getProperty(obj,s.getSourcefield()));
				}
			}
			*/
			for (int k = 0; k < fields.length; k++) {
				for (Stubparamconfig s : stublist)
				{
					if (s.getDestfieldname().equalsIgnoreCase(fields[k].getName())) 
					{
						//System.out.println("Class type address:"+fields[k].getType());
						fields[k].setAccessible(true);
						Object value = null;
						if(fields[k].getType().toString().equals("class java.math.BigDecimal"))
						{
							System.out.println("bigger");
							value=new BigDecimal(BeanUtils.getProperty(obj,s.getSourcefieldname()));
						}
						else if(fields[k].getType().toString().equals("class java.lang.Integer"))
						{
							value=Integer.parseInt(BeanUtils.getProperty(obj,s.getSourcefieldname()));
						}
						else
						{
							value=BeanUtils.getProperty(obj,s.getSourcefieldname());
						}
						fields[k].set(target_obj, value);
					}
				}
			}
			return target_obj;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stublist;
	}
}
