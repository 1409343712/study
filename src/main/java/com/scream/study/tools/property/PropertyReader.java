package com.scream.study.tools.property;

import java.io.FileReader;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyReader {
	public static void main(String[] args) {
		loadProperty();
	}
	private static void loadProperty() {
		try {
			Properties pros = new Properties();
			pros.load(new FileReader("D:\\test.properties"));
			Enumeration enumras = pros.propertyNames();
			while(enumras.hasMoreElements()) {
				String key =(String)enumras.nextElement();
				String value = (String)pros.get(key);
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
