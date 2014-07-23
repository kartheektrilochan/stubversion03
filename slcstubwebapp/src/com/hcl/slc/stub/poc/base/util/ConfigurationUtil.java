package com.hcl.slc.stub.poc.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Kartheek.Tk
 */
public class ConfigurationUtil {
	
	public Properties readConfigurations()
	{
		try {
		FileInputStream propfile = new FileInputStream("config\\config.properties");
		Properties prop = new Properties();
		prop.load(propfile);
		
		return prop;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		ConfigurationUtil util=new ConfigurationUtil();
		Properties prop=util.readConfigurations();
		
	}

}
