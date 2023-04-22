package com.example.springboot;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@SpringBootApplication
public class Application {

	public static <T> int indexOf(T[] arr, T elem) {
		if (arr == null) return -1;
		for (int i=0; i<arr.length; ++i) {
			if (Objects.equals(arr[i], elem)) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		if (indexOf(args, "--sidecar") >= 0) {
			MultiApplication.executeSidecars();
		} else {
			SpringApplication.run(Application.class, args);
		}
	}
}

