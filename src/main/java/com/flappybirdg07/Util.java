package com.flappybirdg07;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

public class Util {

	private static HashMap<String, List<char[]>> imageCache = new HashMap<>();

	public static List<char[]> loadImageAsCharArray(String path) {
		List<char[]> charArray = imageCache.get(path);

		if (charArray != null) {
			return charArray;
		}

		try {
			BufferedImage image = ImageIO.read(new File(path));
			charArray = convertImageToCharArray(image);
			imageCache.put(path, charArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return charArray;
	}

	private static List<char[]> convertImageToCharArray(BufferedImage image) {
		List<char[]> result = new ArrayList<>();

		for (int y = 0; y < image.getHeight(); y++) {
			char[] row = new char[image.getWidth()];
			for (int x = 0; x < image.getWidth(); x++) {
				int rgb = image.getRGB(x, y);
				row[x] = convertRGBToChar(rgb);
			}
			result.add(row);
		}

		return result;
	}

	private static char convertRGBToChar(int rgb) {
		// Lógica para converter o valor RGB para um caractere
		// Ajuste conforme necessário
		return 'X';
	}
}
