package ucla.genetic.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.base.Strings;

public class FileIO {
	public static String readFiletoString(String path) {

		Path p1 = Paths.get(path);
		StringBuffer fileAsString = new StringBuffer("");

		try (InputStream in = Files.newInputStream(p1);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in))) {

			String tempLine = null;

			while ((tempLine = reader.readLine()) != null) {
				if (Strings.isNullOrEmpty(tempLine)) {
					// ignore this
				} else {
					fileAsString = fileAsString.append(tempLine + "\n");
				}

			}
		} catch (IOException x) {
			System.err.println(x);
		}

		return fileAsString.toString();
	}

	public static void writeStringToFile(String filepath, String dataString) {

		// this writes a given string to file

		Path file = Paths.get(filepath);
		Charset charset = Charset.forName("US-ASCII");

		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
			writer.write(dataString, 0, dataString.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
}
