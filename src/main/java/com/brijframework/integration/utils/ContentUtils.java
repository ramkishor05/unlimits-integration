package com.brijframework.integration.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Base64;

public class ContentUtils {

	public static void writeFromBytes(byte[] parseAsByteArray, java.io.File f) throws IOException {
		com.google.common.io.Files.write(parseAsByteArray, f);
	}

	public static byte[] parseAsByteArray(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			return null;
		}
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			com.google.common.io.ByteStreams.copy(inputStream, outputStream);
			return Base64.getEncoder().encode(outputStream.toByteArray());
		}
	}

	public static void writeFromString(String parseAsString, java.io.File toFile, Charset charset) throws IOException {
		com.google.common.io.Files.asCharSink(toFile, charset).write(parseAsString);
	}

	public static void writeFromStream(InputStream is, java.io.File f) throws IOException, FileNotFoundException {
		try (OutputStream os = new FileOutputStream(f)) {
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
		}
	}

	public static String parseAsString(InputStream inputStream) throws IOException {
		return new String(parseAsByteArray(inputStream));
	}
}
