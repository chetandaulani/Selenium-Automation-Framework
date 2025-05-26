package com.chetandaulani.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToHashMap {

	public static Object[][] getJsonToHashMapData(String filePath) throws IOException {
		// Read data from the JSON file
		 List<HashMap<String, String>> dataList = getJsonData(System.getProperty("user.dir") + filePath);

		// Convert List<HashMap> to Object[][]
		Object[][] data = new Object[dataList.size()][1];
		for (int i = 0; i < dataList.size(); i++) {
			data[i][0] = dataList.get(i);
		}

		return data;
	}

	private static List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		// Use Jackson's ObjectMapper to read JSON file into a list of HashMaps
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new File(filePath), new TypeReference<List<HashMap<String, String>>>() {
		});
	}
}
