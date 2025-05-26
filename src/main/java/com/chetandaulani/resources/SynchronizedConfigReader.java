package com.chetandaulani.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SynchronizedConfigReader {
	private static final Map<String, Boolean> credentials = new HashMap<>();

	static {
		try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\chetandaulani\\resources\\config.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();

			int index = 1;
			while (properties.containsKey("username" + index)) {
				String username = properties.getProperty("username" + index);
				String password = properties.getProperty("password" + index);
				credentials.put(username + ":" + password, false); // Initialize credentials
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Synchronized method to fetch an available credential
	public static synchronized String getAvailableCredential() {
		for (Map.Entry<String, Boolean> entry : credentials.entrySet()) {
			if (!entry.getValue()) {
				credentials.put(entry.getKey(), true); // Lock credential
				return entry.getKey();
			}
		}
		return null; // No available credentials
	}

	// Synchronized method to release a credential
	public static synchronized void releaseCredential(String credential) {
		if (credential != null) {
			credentials.put(credential, false); // Unlock credential
		}
	}
}
