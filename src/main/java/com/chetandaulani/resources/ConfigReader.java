package com.chetandaulani.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	// Static block to load properties at the start
	static {
		try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\chetandaulani\\resources\\config.properties")) {
			properties = new Properties();
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load configuration file.");
		}
	}

	public static String getURL(String application, String environment) {
		return getProperty(application.toUpperCase() + "_URL_" + environment.toUpperCase());
	}

	// Method to get a property by key
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	// Example usage
	public static void main(String[] args) {
		System.out.println("Base URL: " + ConfigReader.getProperty("baseUrl"));
		System.out.println("Browser: " + ConfigReader.getProperty("browser"));
	}
}
