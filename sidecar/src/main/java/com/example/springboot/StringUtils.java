package com.example.springboot;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
	public static <T> void println(T[] arr) {
		printlnArray(arr, " ");
	}
	public static <T> void printlnArray(T[] arr, String sep) {
		System.out.println(joinArray(arr, sep));
	}
	public static <T> void printlnList(List<T> arr, String sep) {
		System.out.println(joinList(arr, sep));
	}

	public static <T> String toString(T s) {
		if (s == null) return null;
		if (s instanceof Object) return s.toString();
		return String.valueOf(s);
	}

	public static <T> String joinArray(T[] arr, String sep) {
		if (arr == null) {
			System.out.println("WARNING: Null array, ignore joining");
			return "null";
		}
		String str = Arrays.stream(arr)
			.map(StringUtils::toString)
			.collect(Collectors.joining(sep));
		return str;
	}
	public static <T> String joinList(List<T> arr, String sep) {
		if (arr == null) {
			System.out.println("WARNING: Null list, ignore joining");
			return "null";
		}
		String str = arr.stream()
			.map(StringUtils::toString)
			.collect(Collectors.joining(sep));
		return str;
	}
}
