package io.gp.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public static Properties getProperties(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;

		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
}
