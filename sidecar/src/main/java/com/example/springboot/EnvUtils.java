package com.example.springboot;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EnvUtils {
	public static String getEnv(String name, String defaultValue) {
		String value = System.getenv(name);
		if (value.isEmpty()) return defaultValue;
		return value;
	}

	public static String getEnv(String name) {
		return System.getenv(name);
	}

	public static Integer getEnvAsInt(String name) {
		try {
			return Integer.parseInt(System.getenv(name));
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer getEnvAsInt(String name, int defaultValue) {
		String value = System.getenv(name);
		if (value.isEmpty()) return defaultValue;
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static Double getEnvAsDouble(String name) {
		try {
			return Double.parseDouble(System.getenv(name));
		} catch (Exception e) {
			return null;
		}
	}

	public static Double getEnvAsDouble(String name, double defaultValue) {
		String value = System.getenv(name);
		if (value.isEmpty()) return defaultValue;
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> List<T> ArrayToListConversion(T array[])
	{
		List<T> list = new ArrayList<>();
		for (T t : array)
		{
			list.add(t);
		}
		return list;
	}

	public static Map<String, String> getAllEnvs() {
		return System.getenv();
	}

	// semi-comma separated list
	public static List<String> getEnvAsList(String name) {
		String value = System.getenv(name);
		if (value.isEmpty()) return new ArrayList<String>();
		return ArrayToListConversion(value.split(";"));
	}
}
