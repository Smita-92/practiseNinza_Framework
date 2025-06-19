package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertyFile(String key) throws IOException {
	FileInputStream Fis=new FileInputStream("./config_env_Data/configData.properties");
	Properties prop=new Properties();
	prop.load(Fis);
	String data=prop.getProperty(key);
	return data;

}
}