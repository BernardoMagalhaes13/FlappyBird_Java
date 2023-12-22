package com.flappybirdg07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Util {

	private static HashMap<String, char[][]> cache = new HashMap<>();

	public static char[][] loadImageAsCharArray(String path) {
		char[][] image = null;

		if (cache.containsKey(path)) {
			return cache.get(path);
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			// Assuming each line of the file represents a row of characters
			// Adjust this based on your actual image format
			String line;
			int numRows = 0;
			int numCols = 0;

			while ((line = reader.readLine()) != null) {
				numCols = Math.max(numCols, line.length());
				numRows++;
			}

			image = new char[numRows][numCols];

			reader.close();
			reader = new BufferedReader(new FileReader(path));

			int row = 0;
			while ((line = reader.readLine()) != null) {
				char[] chars = line.toCharArray();
				System.arraycopy(chars, 0, image[row], 0, chars.length);
				row++;
			}

			if (!cache.containsKey(path)) {
				cache.put(path, image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
}
